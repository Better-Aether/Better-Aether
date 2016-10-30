package com.gildedgames.aether.common.entities.living.companions;

import com.gildedgames.aether.common.capabilities.player.PlayerAetherImpl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public abstract class EntityBasicCompanion extends EntityCompanion
{
	private Potion potion;

	private int strength;

	public EntityBasicCompanion(World worldIn)
	{
		super(worldIn);
	}

	protected void setPotion(Potion potion, int strength)
	{
		this.potion = potion;
		this.strength = strength;
	}

	@Override
	public void tickEffects(PlayerAetherImpl aePlayer)
	{
		// Avoid sending potion packets every tick
		if (this.ticksExisted % 100 == 0)
		{
			this.givePotion(aePlayer.getPlayer());
		}
	}

	@Override
	public void addEffects(PlayerAetherImpl aePlayer)
	{
		this.givePotion(aePlayer.getPlayer());
	}

	@Override
	public void removeEffects(PlayerAetherImpl aePlayer)
	{
		this.removePotion(aePlayer.getPlayer());
	}

	private void givePotion(EntityPlayer player)
	{
		player.addPotionEffect(new PotionEffect(this.potion, 600, this.strength, true, false));
	}

	private void removePotion(EntityPlayer player)
	{
		player.removePotionEffect(this.potion);
	}
}
