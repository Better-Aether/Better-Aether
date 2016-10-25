package com.gildedgames.aether.common.world.features;

import com.gildedgames.aether.common.entities.util.MoaNest;
import com.gildedgames.aether.common.world.GenUtil;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;

import java.util.Random;

public class WorldGenMoaNest extends WorldGenTemplate
{

	private BlockPos familySpawnOffset;

	public WorldGenMoaNest(TemplatePipeline pipeline, Template template, BlockPos familySpawnOffset, PlacementCondition condition, PlacementCondition... placementConditions)
	{
		super(pipeline, template, condition, placementConditions);

		this.familySpawnOffset = familySpawnOffset;
	}

	@Override
	public boolean generate(World world, Random random, BlockPos pos)
	{
		Rotation rotation = ROTATIONS[random.nextInt(ROTATIONS.length)];

		boolean result = this.placeTemplate(world, random, pos, rotation);

		if (result)
		{
			MoaNest nest = new MoaNest(world, GenUtil.rotate(pos, pos.add(familySpawnOffset), rotation));

			nest.spawnMoaFamily(world, 2 + world.rand.nextInt(4));
		}

		return result;
	}
}
