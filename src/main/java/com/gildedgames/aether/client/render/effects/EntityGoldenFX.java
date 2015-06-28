package com.gildedgames.aether.client.render.effects;

import net.minecraft.world.World;

public class EntityGoldenFX extends EntityAetherPortalFX
{
	public EntityGoldenFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		super(world, posX, posY, posZ, motionX, motionY, motionZ);

		this.particleBlue = 0.0f;
		this.particleRed = 0.976f;
		this.particleGreen = 0.745f;
	}
}
