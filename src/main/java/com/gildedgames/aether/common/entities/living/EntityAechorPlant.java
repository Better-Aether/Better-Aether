package com.gildedgames.aether.common.entities.living;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.entities.ai.AechorPlantAI;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAechorPlant extends EntityMob
{
	private static final int canSeePreyID = 16, plantSizeID = 17;

	@SideOnly(Side.CLIENT)
	public float sinage;

	public EntityAechorPlant(World worldIn)
	{
		super(worldIn);

		this.tasks.addTask(0, new AechorPlantAI(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

		this.setSize(0.75F + (this.getPlantSize() * 0.125F), 0.5F + (this.getPlantSize() * 0.075F));

		this.sinage = this.rand.nextFloat() * 6F;
	}

	protected void entityInit()
	{
		super.entityInit();

		this.dataWatcher.addObject(EntityAechorPlant.canSeePreyID, (byte) 0);
		this.dataWatcher.addObject(EntityAechorPlant.plantSizeID, 0);

		this.setPlantSize(this.rand.nextInt(3));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(3.5F);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		this.motionX = 0.0D;
		this.motionZ = 0.0D;

		if (this.worldObj.isRemote)
		{
			this.tickAnimation();

			return;
		}

		boolean isTargetting = this.getAttackTarget() != null;

		if (this.canSeePrey() != isTargetting)
		{
			this.setCanSeePrey(isTargetting);
		}

		BlockPos beneathPos = new BlockPos(this.posX, this.getEntityBoundingBox().minY - 0.1D, this.posZ);
		Block blockBeneath = this.worldObj.getBlockState(beneathPos).getBlock();

		if (blockBeneath != BlocksAether.aether_grass && blockBeneath != BlocksAether.aether_dirt)
		{
			this.setHealth(0);
		}
	}

	@Override
	public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) { }

	@SideOnly(Side.CLIENT)
	private void tickAnimation()
	{
		if (this.hurtTime > 0)
		{
			this.sinage += 0.5F;
		}
		else
		{
			this.sinage += this.canSeePrey() ? 0.3F : 0.1F;
		}

		float pie2 = 3.141593F * 2F;

		if (this.sinage > pie2)
		{
			this.sinage -= pie2;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);

		tagCompound.setInteger("plantSize", this.getPlantSize());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		super.readEntityFromNBT(tagCompound);

		this.setPlantSize(tagCompound.getInteger("plantSize"));
	}

	public boolean canSeePrey()
	{
		return this.dataWatcher.getWatchableObjectByte(EntityAechorPlant.canSeePreyID) == 1;
	}

	public void setCanSeePrey(boolean canSee)
	{
		this.dataWatcher.updateObject(EntityAechorPlant.canSeePreyID, (byte) (canSee ? 1 : 0));
	}

	public int getPlantSize()
	{
		return this.dataWatcher.getWatchableObjectInt(EntityAechorPlant.plantSizeID);
	}

	public void setPlantSize(int size)
	{
		this.dataWatcher.updateObject(EntityAechorPlant.plantSizeID, size);
	}
}
