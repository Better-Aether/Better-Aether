package com.gildedgames.aether.common.entities.living;

import com.gildedgames.aether.common.entities.living.mounts.EntityFlyingAnimal;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityFlyingCow extends EntityFlyingAnimal
{
	public EntityFlyingCow(World world)
	{
		super(world);

		((PathNavigateGround) this.getNavigator()).setAvoidsWater(true);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.wheat, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));

		this.setSize(0.9F, 1.3F);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.cow.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.cow.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.cow.hurt";
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound("mob.cow.step", 0.15F, 1.0F);
	}


	@Override
	protected Item getDropItem()
	{
		return this.isBurning() ? Items.cooked_beef : Items.beef;
	}

	@Override
	protected void dropFewItems(boolean p_70628_1_, int looting)
	{
		int amount = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + looting);

		for (int count = 0; count < amount; ++count)
		{
			this.dropItem(this.getDropItem(), 1);
		}

		super.dropFewItems(p_70628_1_, looting);
	}
	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return new EntityFlyingCow(this.worldObj);
	}
}
