package com.gildedgames.aether.common.entities.effects.rules;

import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectRule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class WhenPoisonedRule implements EntityEffectRule
{

	@Override
	public boolean isMet(Entity source)
	{
		return source instanceof EntityLivingBase && ((EntityLivingBase) source).isPotionActive(MobEffects.POISON);
	}

	@Override
	public String[] getUnlocalizedDesc()
	{
		return new String[] { "When Poisoned" };
	}

	@Override
	public boolean blockLivingAttack(Entity source, LivingAttackEvent event)
	{
		return false;
	}

	@Override
	public boolean blockLivingHurt(Entity source, LivingHurtEvent event)
	{
		return false;
	}

}
