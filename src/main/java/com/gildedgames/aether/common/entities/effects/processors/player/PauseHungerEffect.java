package com.gildedgames.aether.common.entities.effects.processors.player;

import com.gildedgames.aether.common.entities.effects.EffectInstance;
import com.gildedgames.aether.common.entities.effects.EffectProcessorPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;

public class PauseHungerEffect implements EffectProcessorPlayer<EffectInstance>
{

	@Override
	public String getUnlocalizedName(Entity source, EffectInstance instance)
	{
		return "ability.pauseHunger.name";
	}

	@Override
	public String[] getUnlocalizedDesc(Entity source, EffectInstance instance)
	{
		return new String[] { "ability.pauseHunger.desc" };
	}

	@Override
	public void apply(Entity source, EffectInstance instance, List<EffectInstance> all)
	{
		if (!(source instanceof EntityPlayer))
		{
			return;
		}

		EntityPlayer player = (EntityPlayer) source;

		instance.getAttributes().setInteger("foodLevel", player.getFoodStats().getFoodLevel());
		instance.getAttributes().setFloat("foodSaturation", player.getFoodStats().getSaturationLevel());
	}

	@Override
	public void tick(Entity source, List<EffectInstance> all)
	{
		if (!(source instanceof EntityPlayer))
		{
			return;
		}

		EntityPlayer player = (EntityPlayer) source;

		World world = player.worldObj;

		if (!world.isRemote)
		{
			for (EffectInstance instance : all)
			{
				if (player.getFoodStats().getFoodLevel() > instance.getAttributes().getInteger("foodLevel") || player.getFoodStats().getSaturationLevel() > instance.getAttributes().getFloat("foodSaturation"))
				{
					instance.getAttributes().setInteger("foodLevel", player.getFoodStats().getFoodLevel());
					instance.getAttributes().setFloat("foodSaturation", player.getFoodStats().getSaturationLevel());
				}

				ObfuscationReflectionHelper.setPrivateValue(FoodStats.class, player.getFoodStats(), instance.getAttributes().getInteger("foodLevel"), "field_75127_a", "foodLevel");
				ObfuscationReflectionHelper.setPrivateValue(FoodStats.class, player.getFoodStats(), instance.getAttributes().getFloat("foodSaturation"), "field_75125_b", "foodSaturationLevel");
			}
		}
	}

	@Override
	public void cancel(Entity source, EffectInstance instance, List<EffectInstance> all)
	{
		instance.getAttributes().setInteger("foodLevel", 0);
		instance.getAttributes().setFloat("foodSaturation", 0);
	}

	@Override
	public void onInteract(PlayerInteractEvent event, EntityPlayer source, List<EffectInstance> all)
	{
	}

	@Override
	public void onKill(LivingDropsEvent event, Entity source, List<EffectInstance> all)
	{
	}

	@Override
	public void onAttack(LivingHurtEvent event, Entity source, List<EffectInstance> all)
	{
	}

	@Override
	public String[] getFormatParameters(Entity source, EffectInstance instance)
	{
		return new String[] {};
	}

	@Override
	public void onPickupXP(PlayerPickupXpEvent event, EntityPlayer source, List<EffectInstance> all)
	{
	}

}
