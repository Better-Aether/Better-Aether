package com.gildedgames.aether.common.entities.living.mounts;

import com.gildedgames.aether.common.genes.util.GeneUtil;
import com.gildedgames.aether.common.entities.ai.moa.*;
import com.gildedgames.aether.common.genes.moa.MoaGenePool;
import com.gildedgames.aether.common.entities.util.*;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.items.misc.ItemMoaEgg;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMoa extends EntityGeneticAnimal<MoaGenePool> implements EntityGroupMember
{

	private static final DataParameter<Integer> REMAINING_JUMPS = EntityDataManager.createKey(EntityMoa.class, DataSerializers.VARINT);

	private static final DataParameter<Boolean> EGG_STOLEN = EntityDataManager.createKey(EntityMoa.class, DataSerializers.BOOLEAN);

	private static final DataParameter<Boolean> RAISED_BY_PLAYER = EntityDataManager.createKey(EntityMoa.class, DataSerializers.BOOLEAN);

	private static final DataParameter<Boolean> GENDER = EntityDataManager.createKey(EntityMoa.class, DataSerializers.BOOLEAN);

	public float wingRotation, destPos, prevDestPos, prevWingRotation;

	public int ticksUntilFlap;

	private EntityGroup pack;

	private MoaNest familyNest;

	private boolean addedBreedingTask;

	public EntityMoa(World world)
	{
		super(world);

		this.initAI();

		this.familyNest = new MoaNest(world);

		this.setSize(1.0F, 2.0F);
		this.stepHeight = 1.0F;
	}

	public EntityMoa(World world, int geneticSeed)
	{
		this(world);

		this.getGenePool().transformFromSeed(geneticSeed);
	}

	public EntityMoa(World world, MoaNest familyNest)
	{
		this(world, familyNest.familyGeneticSeed);

		this.familyNest = familyNest;
		this.initAI();
	}

	public EntityMoa(World world, MoaNest familyNest, int fatherSeed, int motherSeed)
	{
		this(world, familyNest);

		this.getGenePool().transformFromParents(GeneUtil.getRandomSeed(world), fatherSeed, motherSeed);
	}

	private void initAI()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new AIPanicPack(this, 0.55F));
		this.tasks.addTask(2, new EntityAIWander(this, 0.50F));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIMate(this, 0.65F));
		this.tasks.addTask(8, new AIAnimalPack(this, 0.55F));
		this.tasks.addTask(10, new AIStayNearNest(this, 8, 0.55F));
		this.tasks.addTask(12, new AIAvoidEntityAsChild(this, EntityPlayer.class, 5.0F, 0.3D, 0.3D));
		this.tasks.addTask(13, new EntityAITempt(this, 0.25F, Items.WHEAT, false));
		this.tasks.addTask(14, new EntityAIAttackMelee(this, 0.7D, true));

		this.targetTasks.addTask(1, new AIProtectPack(this));
	}

	@Override
	public MoaGenePool createNewGenePool()
	{
		return new MoaGenePool(new EntityGeneStorage(this));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
	}

	@Override
	public void entityInit()
	{
		super.entityInit();

		this.dataManager.register(EntityMoa.REMAINING_JUMPS, 0);
		this.dataManager.register(EntityMoa.EGG_STOLEN, Boolean.FALSE);
		this.dataManager.register(EntityMoa.RAISED_BY_PLAYER, Boolean.FALSE);
		this.dataManager.register(EntityMoa.GENDER, Boolean.FALSE);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.addedBreedingTask)
		{
			this.tasks.addTask(9, new AIMoaPackBreeding(this, 0.25F));
			this.addedBreedingTask = true;
		}

		if (this.isJumping)
		{
			this.motionY += 0.05F;
		}

		if (this.pack == null)
		{
			this.pack = this.familyNest.getAnimalPack();
		}

		this.updateWingRotation();

		this.fallSlowly();
	}

	private void fallSlowly()
	{
		this.fallDistance = 0;

		if (!this.onGround && this.motionY < 0.0D)
		{
			this.motionY *= 0.63749999999999996D;
		}
	}

	public void updateWingRotation()
	{
		if (!this.onGround)
		{
			if (this.ticksUntilFlap == 0)
			{
				//this.worldObj.playSoundAtEntity(this, "mob.bat.takeoff", 0.15f, MathHelper.clamp_float(this.rand.nextFloat(), 0.7f, 1.0f) + MathHelper.clamp_float(this.rand.nextFloat(), 0f, 0.3f));

				this.ticksUntilFlap = 11;
			}
			else
			{
				this.ticksUntilFlap--;
			}
		}

		this.prevWingRotation = this.wingRotation;
		this.prevDestPos = this.destPos;

		this.destPos += 0.2D;
		this.destPos = Math.min(1.0F, Math.max(0.01F, this.destPos));

		if (this.onGround)
		{
			this.destPos = 0.0F;
		}

		this.wingRotation += 0.533F;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;

			if (player.capabilities.isCreativeMode)
			{
				return super.attackEntityAsMob(entity);
			}
		}

		entity.motionY = 0.8F;
		entity.motionZ = this.getLookVec().zCoord;
		entity.motionX = this.getLookVec().xCoord;
		entity.velocityChanged = true;

		entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.isGroupLeader() ? 3 : 2);

		return super.attackEntityAsMob(entity);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setBoolean("playerGrown", this.isRaisedByPlayer());

		if (this.getGender() != null)
		{
			nbt.setString("creatureGender", this.getGender().name());
		}

		nbt.setInteger("remainingJumps", this.getRemainingJumps());

		this.familyNest.writeToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		this.setRaisedByPlayer(nbt.getBoolean("playerGrown"));

		if (nbt.getString("creatureGender") != null)
		{
			this.setGender(AnimalGender.get(nbt.getString("creatureGender")));
		}

		this.setRemainingJumps(nbt.getInteger("remainingJumps"));

		this.familyNest.readFromNBT(nbt);
	}

	@Override
	public boolean isGroupLeader()
	{
		return this.getGender() == AnimalGender.MALE;
	}

	@Override
	public EntityGroup getGroup()
	{
		return this.familyNest != null ? this.familyNest.getAnimalPack() : null;
	}

	@Override
	public boolean isProtective()
	{
		return this.isGroupLeader() || this.hasEggBeenStolen();
	}

	public boolean hasEggBeenStolen()
	{
		return this.dataManager.get(EntityMoa.EGG_STOLEN);
	}

	public void setEggStolen(boolean flag)
	{
		this.dataManager.set(EntityMoa.EGG_STOLEN, flag);
	}

	public boolean isRaisedByPlayer()
	{
		return this.dataManager.get(EntityMoa.RAISED_BY_PLAYER);
	}

	public void setRaisedByPlayer(boolean flag)
	{
		this.dataManager.set(EntityMoa.RAISED_BY_PLAYER, flag);
	}

	public AnimalGender getGender()
	{
		return this.dataManager.get(EntityMoa.GENDER) ? AnimalGender.MALE : AnimalGender.FEMALE;
	}

	public void setGender(AnimalGender gender)
	{
		this.dataManager.set(EntityMoa.GENDER, gender == AnimalGender.MALE);
	}

	public int getRemainingJumps()
	{
		return this.dataManager.get(EntityMoa.REMAINING_JUMPS);
	}

	private void setRemainingJumps(int jumps)
	{
		this.dataManager.set(EntityMoa.REMAINING_JUMPS, jumps);
	}

	public MoaNest getFamilyNest()
	{
		return this.familyNest;
	}

	public EntityGroup getAnimalPack()
	{
		return this.pack;
	}

	public void setAnimalPack(EntityGroup pack)
	{
		this.pack = pack;
		this.pack.onAnimalJoin(this);
	}

	@Override
	public void onDeath(DamageSource damageSource)
	{
		if (this.pack != null)
		{
			this.pack.onAnimalDeath(this);
		}

		super.onDeath(damageSource);
	}

	@Override
	public void setRevengeTarget(EntityLivingBase entity)
	{
		super.setRevengeTarget(entity);

		if (this.pack != null && entity != null)
		{
			this.pack.addOrRenewAggressor(entity);
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable matingAnimal)
	{
		if (matingAnimal instanceof EntityMoa)
		{
			EntityMoa mate = (EntityMoa) matingAnimal;

			this.tasks.addTask(7, new AIMoaLayEgg(this, 0.35F));

			this.setGrowingAge(6000);
			mate.setGrowingAge(6000);
			this.resetInLove();

			this.setAttackTarget(null);
			mate.setAttackTarget(null);

			mate.resetInLove();
		}

		return null;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		ItemStack moaEgg = new ItemStack(ItemsAether.moa_egg);

		MoaGenePool stackGenes = ItemMoaEgg.getGenePool(moaEgg);

		stackGenes.transformFromParents(this.getGenePool().getStorage().getSeed(), this.getGenePool().getStorage().getFatherSeed(), this.getGenePool().getStorage().getMotherSeed());

		return moaEgg;
	}

}
