package com.gildedgames.aether.common.entities.ai.moa;

import com.gildedgames.aether.common.entities.living.mounts.EntityMoa;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class AIStayNearNest extends EntityAIBase
{

	public World world;
	
	public EntityMoa moa;

	public float moveSpeed;
	
	public int nestX, nestY, nestZ, stayCloseDist;
	
	public AIStayNearNest(EntityMoa moa, int stayCloseDist, float moveSpeed)
	{
		this.world = moa.worldObj;
		this.moveSpeed = moveSpeed;
		this.moa = moa;
		this.stayCloseDist = stayCloseDist;
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.moa == null || this.world == null)
		{
			return false;
		}

		if (this.moa.getFamilyNest() == null)
		{
			return false;
		}
		else if (!this.moa.getFamilyNest().hasInitialized)
		{
			return false;
		}
		
		this.nestX = this.moa.getFamilyNest().pos.getX();
		this.nestY = this.moa.getFamilyNest().pos.getY() + 1;
		this.nestZ = this.moa.getFamilyNest().pos.getZ();

		if (this.moa.getDistance(this.nestX, this.nestY, this.nestZ) < this.stayCloseDist)
		{
			return false;
		}
		
		return true;
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		this.moa.getNavigator().tryMoveToXYZ(this.nestX - 1, this.nestY, this.nestZ - 1, this.moveSpeed);
	}

	@Override
	public boolean continueExecuting()
	{
		return this.shouldExecute();
	}
	
}
