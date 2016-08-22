package com.gildedgames.aether.common.entities.ai.cockatrice;

import com.gildedgames.aether.common.entities.living.enemies.EntityCockatrice;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

import java.util.List;

public class EntityAICockatriceSneakAttack extends EntityAIBase
{

	private EntityCockatrice entity;

	protected EntityLivingBase attack;

	protected int attackTick;

	Class<? extends EntityLivingBase> attackClass;

	public EntityAICockatriceSneakAttack(EntityCockatrice entity, Class<? extends EntityLivingBase> clazz)
	{
		this.entity = entity;
		this.attackClass = clazz;
	}

	@Override
	public boolean shouldExecute()
	{
		if (!this.entity.isAttacking() && this.entity.isHidden())
		{
			List entities = this.entity.worldObj.getEntitiesWithinAABB(this.attackClass, this.entity.getEntityBoundingBox().expand(16.0D, 16.0D, 16.0D));

			if (entities.isEmpty())
			{
				return false;
			}

			EntityLivingBase attack = null;

			for (Object o : entities)
			{
				if (o instanceof EntityLivingBase)
				{
					attack = (EntityLivingBase) o;
				}
			}

			if (attack == null)
			{
				return false;
			}

			this.attack = attack;

			if (this.attack instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) this.attack;

				if (player.isCreative())
				{
					return false;
				}
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean continueExecuting()
	{
		return this.entity.isAttacking() && this.attack.isEntityAlive();
	}

	@Override
	public void startExecuting()
	{
		this.entity.setAttacking(true);
		this.entity.getNavigator().tryMoveToEntityLiving(this.attack, 1.2D);
		this.attackTick = 0;
	}

	@Override
	public void resetTask()
	{
		//this.entity.setAttacking(false);
		//this.attackTick = 0;
	}

	@Override
	public void updateTask()
	{
		this.entity.getLookHelper().setLookPositionWithEntity(this.attack, 30.0F, 30.0F);
		double distanceBetweenTarget = this.entity.getDistanceSq(this.attack.posX, this.attack.getEntityBoundingBox().minY, this.attack.posZ);

		this.entity.getNavigator().tryMoveToEntityLiving(this.attack, 1.2D);

		this.attackTick = Math.max(this.attackTick - 1, 0);

		if (distanceBetweenTarget <= this.getAttackReachSqr(this.attack) && this.attackTick <= 0)
		{
			this.attackTick = 20;
			this.entity.swingArm(EnumHand.MAIN_HAND);
			this.entity.attackEntityAsMob(this.attack);

			this.entity.setAttacking(false);
		}
	}

	protected double getAttackReachSqr(EntityLivingBase attackTarget)
	{
		return (double)(this.entity.width * 2.0F * this.entity.width * 2.0F + attackTarget.width);
	}

}
