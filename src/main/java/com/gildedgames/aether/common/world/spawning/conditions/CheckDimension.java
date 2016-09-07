package com.gildedgames.aether.common.world.spawning.conditions;

import com.gildedgames.aether.common.world.spawning.PosCondition;
import com.gildedgames.aether.common.world.spawning.WorldCondition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class CheckDimension implements PosCondition, WorldCondition
{

	private DimensionType dimensionTypeToCheckFor;

	public CheckDimension(DimensionType dimensionTypeToCheckFor)
	{
		this.dimensionTypeToCheckFor = dimensionTypeToCheckFor;
	}

	@Override
	public boolean isMet(World world, BlockPos spawnAt, BlockPos underneath)
	{
		return this.isMet(world);
	}

	@Override
	public boolean isMet(World world)
	{
		return world.provider.getDimensionType() == this.dimensionTypeToCheckFor;
	}
}
