package com.gildedgames.aether.common.capabilities.entity.effects.processors;

import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectInstance;
import com.gildedgames.aether.common.capabilities.entity.effects.AbstractEffectProcessor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import java.util.List;

/**
 * Sets player's "air" level to 300, which is one bubble worth of air. This means allows
 * them to breathe under water.
 * @author Brandon Pearce
 */
public class BreatheUnderwaterEffect extends AbstractEffectProcessor<EntityEffectInstance>
{

	public BreatheUnderwaterEffect()
	{
		super("ability.breatheUnderwater.localizedName", "ability.breatheUnderwater.desc");
	}

	@Override
	public void tick(Entity source, List<EntityEffectInstance> all)
	{
		if (!(source instanceof EntityLivingBase))
		{
			return;
		}

		EntityLivingBase living = (EntityLivingBase)source;

		living.setAir(300);

		if (living.isInsideOfMaterial(Material.WATER))
		{
			living.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 2, 0, false, false));
		}
	}

}
