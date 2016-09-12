package com.gildedgames.aether.common.capabilities.entity.effects.processors;

import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectInstance;
import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectRule;
import com.gildedgames.aether.api.capabilites.entity.effects.IEntityEffectsCapability;
import com.gildedgames.aether.common.capabilities.entity.effects.AbstractEffectProcessor;
import com.gildedgames.aether.common.capabilities.entity.effects.EntityEffects;
import com.gildedgames.aether.common.capabilities.entity.effects.processors.ModifyMaxHealthEffect.Instance;
import com.gildedgames.aether.common.capabilities.player.PlayerAetherImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.UUID;

public class ModifyMaxHealthEffect extends AbstractEffectProcessor<Instance>
{

	public static class Instance extends EntityEffectInstance
	{

		private AttributeModifier modifier;

		public Instance(double maxHealthMod, EntityEffectRule... rules)
		{
			super(rules);

			this.getAttributes().setDouble("maxHealthMod", maxHealthMod);
			this.modifier = new AttributeModifier(UUID.randomUUID(), "Extra Max Health", maxHealthMod * 2, 0).setSaved(false);
		}

		public AttributeModifier getModifier()
		{
			return this.modifier;
		}

		@Override
		public EntityEffectInstance cloneInstance()
		{
			return new Instance(this.getAttributes().getDouble("maxHealthMod"), this.getRules());
		}

	}

	public ModifyMaxHealthEffect()
	{
		super("ability.maxHealthMod.name", "ability.maxHealthMod.desc");
	}

	@Override
	public String[] getFormatParameters(Entity source, Instance instance)
	{
		double maxHealthMod = instance.getAttributes().getDouble("maxHealthMod");

		String prefix = maxHealthMod > 0 ? (TextFormatting.BLUE + "+") : (TextFormatting.RED + "");

		String par = prefix + (maxHealthMod == (int) Math.floor(maxHealthMod) ? String.valueOf((int) Math.floor(maxHealthMod)) : String.valueOf(maxHealthMod));

		return new String[] { par };
	}

	@Override
	public void apply(Entity source, Instance instance, List<Instance> all)
	{
		if (!(source instanceof EntityLivingBase) || source.worldObj.isRemote)
		{
			return;
		}

		EntityLivingBase living = (EntityLivingBase) source;

		IAttributeInstance attribute = living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);

		if (!attribute.hasModifier(instance.getModifier()))
		{
			float prevMaxHealth = living.getMaxHealth();

			attribute.applyModifier(instance.getModifier());

			if (living.getHealth() == prevMaxHealth)
			{
				IEntityEffectsCapability effects = EntityEffects.get(source);

				if (effects.getTicksSinceAttacked() > 320)
				{
					living.setHealth(living.getMaxHealth());
				}
			}
		}
	}

	@Override
	public void cancel(Entity source, Instance instance, List<Instance> all)
	{
		if (!(source instanceof EntityLivingBase) || source.worldObj.isRemote)
		{
			return;
		}

		EntityLivingBase living = (EntityLivingBase) source;

		IAttributeInstance attribute = living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);

		if (attribute.hasModifier(instance.getModifier()))
		{
			attribute.removeModifier(instance.getModifier());

			if (living.getHealth() > living.getMaxHealth())
			{
				living.setHealth(living.getMaxHealth());
			}
		}
	}

}
