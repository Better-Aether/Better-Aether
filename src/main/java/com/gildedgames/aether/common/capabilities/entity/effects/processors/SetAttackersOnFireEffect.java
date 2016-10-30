package com.gildedgames.aether.common.capabilities.entity.effects.processors;

import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectInstance;
import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectRule;
import com.gildedgames.aether.common.capabilities.entity.effects.AbstractEffectProcessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class SetAttackersOnFireEffect extends AbstractEffectProcessor<SetAttackersOnFireEffect.Instance>
{

	public static class Instance extends EntityEffectInstance
	{

		public Instance(double percentChance, EntityEffectRule... rules)
		{
			super(rules);

			this.getAttributes().setDouble("percentChance", percentChance);
		}

		public double getPercentChance()
		{
			return this.getAttributes().getDouble("percentChance");
		}

		@Override
		public EntityEffectInstance cloneInstance()
		{
			return new Instance(this.getPercentChance(), this.getRules());
		}

	}

	public SetAttackersOnFireEffect()
	{
		super("ability.setAttackersOnFire.name", "ability.setAttackersOnFire.desc1", "ability.setAttackersOnFire.desc2");
	}

	@Override
	public String[] getFormatParameters(Entity source, Instance instance)
	{
		return new String[] { "\u2022" + TextFormatting.ITALIC + " +" + String.valueOf((int) (instance.getPercentChance() * 100.0D))  };
	}

	@Override
	public void onHurt(LivingHurtEvent event, Entity source, List<Instance> all)
	{
		if (!(source instanceof EntityLivingBase))
		{
			return;
		}

		EntityLivingBase living = (EntityLivingBase)source;

		double overallChance = 0.0D;

		for (Instance instance : all)
		{
			overallChance += instance.getPercentChance();
		}

		overallChance *= 100;

		if (event.getSource().getSourceOfDamage() != null && living.getRNG().nextInt(100) <= (int)overallChance)
		{
			event.getSource().getSourceOfDamage().setFire(6);
		}
	}

}
