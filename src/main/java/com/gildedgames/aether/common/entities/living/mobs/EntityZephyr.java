package com.gildedgames.aether.common.entities.living.mobs;

import com.gildedgames.aether.api.capabilites.entity.properties.ElementalState;
import com.gildedgames.aether.api.capabilites.entity.properties.IEntityProperties;
import com.gildedgames.aether.common.registry.content.SoundsAether;
import com.gildedgames.aether.common.entities.util.flying.EntityFlyingDayMob;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityZephyr extends EntityFlyingDayMob implements IEntityProperties
{

	public EntityZephyr(World world)
	{
		super(world);

		this.setSize(1.0F, 1.0F);

		this.experienceValue = 3;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundsAether.zephyr_ambient;
	}

	@Override
	protected SoundEvent getHurtSound()
	{
		return SoundsAether.zephyr_ambient;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundsAether.zephyr_ambient;
	}

	@Override
	public ElementalState getElementalState()
	{
		return ElementalState.AIR;
	}

	@Override
	public ElementalState getAttackElement()
	{
		return ElementalState.AIR;
	}
}
