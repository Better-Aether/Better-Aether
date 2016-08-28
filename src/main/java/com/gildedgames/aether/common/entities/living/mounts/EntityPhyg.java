package com.gildedgames.aether.common.entities.living.mounts;

import com.gildedgames.aether.api.entity.IMount;
import com.gildedgames.aether.api.entity.IMountProcessor;
import com.gildedgames.aether.common.entities.living.enemies.EntityCockatrice;
import com.gildedgames.aether.common.entities.util.mounts.FlyingMount;
import com.gildedgames.aether.common.entities.util.mounts.IFlyingMountData;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPhyg extends EntityFlyingAnimal implements IMount, IFlyingMountData
{

	private static final DataParameter<Float> AIRBORNE_TIME = EntityDataManager.createKey(EntityPhyg.class, DataSerializers.FLOAT);

	private IMountProcessor mountProcessor = new FlyingMount(this);

	public EntityPhyg(World world)
	{
		super(world);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Items.CARROT, false));
		this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));

		this.setSize(0.9F, 1.3F);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.register(EntityPhyg.AIRBORNE_TIME, 5.0F);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_PIG_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound()
	{
		return SoundEvents.ENTITY_PIG_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_PIG_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
	}

	@Override
	protected int getItemQuantityDropped()
	{
		return this.rand.nextInt(3);
	}

	@Override
	protected Item getDropItem()
	{
		return this.isBurning() ? Items.COOKED_PORKCHOP : Items.PORKCHOP;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return new EntityPhyg(this.worldObj);
	}

	@Override
	public IMountProcessor getMountProcessor()
	{
		return this.mountProcessor;
	}

	@Override
	public void resetRemainingAirborneTime()
	{
		this.dataManager.set(EntityPhyg.AIRBORNE_TIME, 5.0F);
	}

	@Override
	public float getRemainingAirborneTime()
	{
		return this.dataManager.get(EntityPhyg.AIRBORNE_TIME);
	}

	@Override
	public void setRemainingAirborneTime(float set)
	{
		this.dataManager.set(EntityPhyg.AIRBORNE_TIME, set);
	}

	@Override
	public void addRemainingAirborneTime(float add)
	{
		this.setRemainingAirborneTime(this.getRemainingAirborneTime() + add);
	}

	@Override
	public double getMountedYOffset()
	{
		return 0.65D;
	}

}
