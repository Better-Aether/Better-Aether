package com.gildedgames.aether.common.entities.effects.processors;

import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectInstance;
import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectProcessor;
import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectRule;
import com.gildedgames.aether.common.entities.effects.processors.ModifySpeedEffect.Instance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;
import java.util.UUID;

public class ModifySpeedEffect implements EntityEffectProcessor<Instance>
{

	public static class Instance extends EntityEffectInstance
	{

		private AttributeModifier modifier;

		public Instance(float movementSpeedMod, EntityEffectRule... rules)
		{
			super(rules);

			this.getAttributes().setFloat("movementSpeedMod", movementSpeedMod);
			this.modifier = new AttributeModifier("Extra Movement Speed", movementSpeedMod, 2).setSaved(false);
		}

		public AttributeModifier getModifier()
		{
			return this.modifier;
		}

		@Override
		public EntityEffectInstance cloneInstance()
		{
			return new Instance(this.getAttributes().getFloat("movementSpeedMod"), this.getRules());
		}
		
	}

	public ModifySpeedEffect()
	{

	}

	@Override
	public String getUnlocalizedName(Entity source, Instance instance)
	{
		return "ability.movementSpeedMod.localizedName";
	}

	@Override
	public String[] getUnlocalizedDesc(Entity source, Instance instance)
	{
		return new String[] { "ability.movementSpeedMod.desc" };
	}

	@Override
	public String[] getFormatParameters(Entity source, Instance instance)
	{
		float movementSpeedMod = instance.getAttributes().getFloat("movementSpeedMod");

		String prefix = movementSpeedMod > 0 ? (TextFormatting.BLUE + "+") : (TextFormatting.RED + "");

		float value = (float) (movementSpeedMod / SharedMonsterAttributes.MOVEMENT_SPEED.getDefaultValue()) * 100;

		String par = prefix + (int) (value) + "%";

		return new String[] { par };
	}

	@Override
	public void apply(Entity source, Instance instance, List<Instance> all)
	{
		if (!(source instanceof EntityLivingBase))
		{
			return;
		}

		EntityLivingBase living = (EntityLivingBase) source;

		living.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(instance.getModifier());
	}

	@Override
	public void tick(Entity source, List<Instance> all)
	{

	}

	@Override
	public void cancel(Entity source, Instance instance, List<Instance> all)
	{
		if (!(source instanceof EntityLivingBase))
		{
			return;
		}

		EntityLivingBase living = (EntityLivingBase) source;

		living.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(instance.getModifier());
	}

	@Override
	public void onKill(LivingDropsEvent event, Entity source, List<Instance> all)
	{

	}

	@Override
	public void onHurt(LivingHurtEvent event, Entity source, List<Instance> all)
	{

	}

	@Override
	public void onAttacked(LivingAttackEvent event, Entity source, List<Instance> all)
	{

	}

}
