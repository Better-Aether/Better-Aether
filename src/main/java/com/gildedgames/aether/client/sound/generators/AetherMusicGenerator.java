package com.gildedgames.aether.client.sound.generators;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.entities.player.PlayerAetherBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class AetherMusicGenerator implements IMusicGenerator
{
	@Override
	public boolean isPlayable(PlayerAetherBase aePlayer)
	{
		return aePlayer.getPlayer().dimension == AetherCore.getAetherDimID();
	}

	@Override
	public ResourceLocation getMusicResource(PlayerAetherBase player)
	{
		World world = player.getPlayer().getEntityWorld();

		if (world.getWorldTime() > 12800L || world.getWorldTime() < 22300L)
		{
			return new ResourceLocation("aether:aemusic.day");
		}
		else
		{
			return new ResourceLocation("aether:aemusic.night");
		}
	}

	@Override
	public int getQuietPeriod(PlayerAetherBase player)
	{
		return player.getPlayer().getEntityWorld().rand.nextInt(800) + 1800;
	}
}
