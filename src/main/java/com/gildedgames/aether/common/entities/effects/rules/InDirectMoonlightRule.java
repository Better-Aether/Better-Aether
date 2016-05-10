package com.gildedgames.aether.common.entities.effects.rules;

import com.gildedgames.aether.entities.effects.EntityEffectRule;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class InDirectMoonlightRule implements EntityEffectRule
{

	@Override
	public boolean isMet(Entity source)
	{
		return source.worldObj.getLight(source.getPosition()) >= 15 && !source.worldObj.isDaytime();
	}

	@Override
	public String[] getUnlocalizedDesc()
	{
		return new String[] { "In Direct Moonlight" };
	}

	@Override
	public boolean blockLivingAttackAbility(Entity source, LivingHurtEvent event)
	{
		return false;
	}

}
