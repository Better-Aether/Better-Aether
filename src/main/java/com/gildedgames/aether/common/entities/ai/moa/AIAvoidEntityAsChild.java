package com.gildedgames.aether.common.entities.ai.moa;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

public class AIAvoidEntityAsChild extends EntityAIAvoidEntity
{
	private EntityCreature entity;
    
	public AIAvoidEntityAsChild(EntityCreature creature, Class entityToAvoid, float par3, double par4, double par6)
    {
		super(creature, entityToAvoid, par3, par4, par6);
		
		this.entity = creature;
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.entity.isChild())
		{
			return super.shouldExecute();
		}
		
		return false;
	}

}
