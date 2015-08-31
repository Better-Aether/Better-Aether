package com.gildedgames.aether.common.world.biome;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.natural.plants.BlockAetherFlower;
import com.gildedgames.aether.common.blocks.natural.plants.BlockBlueberryBush;
import com.gildedgames.aether.common.world.features.WorldGenAetherFlowers;
import com.gildedgames.aether.common.world.features.WorldGenAetherTallGrass;
import com.gildedgames.aether.common.world.features.trees.WorldGenOrangeTree;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeDecoratorAether extends BiomeDecorator
{
	private WorldGenAetherTallGrass genAetherGrass;

	private WorldGenMinable genAmbrosium, genZanite, genGravitite, genContinuum;

	private WorldGenAetherFlowers genPurpleFlowers, genWhiteRoses;

	private WorldGenOrangeTree genOrangeTree;

	private WorldGenAetherFlowers genBlueberryBushes;

	@Override
	public void decorate(World world, Random random, BiomeGenBase genBase, BlockPos pos)
	{
		if (this.currentWorld != null)
		{
			throw new RuntimeException("Already decorating!");
		}

		this.currentWorld = world;
		this.randomGenerator = random;
		this.field_180294_c = pos;

		this.genAetherGrass = new WorldGenAetherTallGrass();

		this.genAmbrosium = new WorldGenMinable(BlocksAether.ambrosium_ore.getDefaultState(), 16);
		this.genZanite = new WorldGenMinable(BlocksAether.zanite_ore.getDefaultState(), 8);
		this.genGravitite = new WorldGenMinable(BlocksAether.gravitite_ore.getDefaultState(), 4);
		this.genContinuum = new WorldGenMinable(BlocksAether.continuum_ore.getDefaultState(), 4);

		this.genPurpleFlowers = new WorldGenAetherFlowers(BlocksAether.aether_flower, BlocksAether.aether_flower.getDefaultState().withProperty(BlockAetherFlower.PROPERTY_VARIANT, BlockAetherFlower.PURPLE_FLOWER), 64);
		this.genWhiteRoses = new WorldGenAetherFlowers(BlocksAether.aether_flower, BlocksAether.aether_flower.getDefaultState().withProperty(BlockAetherFlower.PROPERTY_VARIANT, BlockAetherFlower.WHITE_ROSE), 64);

		this.genOrangeTree = new WorldGenOrangeTree();

		this.genBlueberryBushes = new WorldGenAetherFlowers(BlocksAether.blueberry_bush, BlocksAether.blueberry_bush.getDefaultState().withProperty(BlockBlueberryBush.PROPERTY_HARVESTABLE, true), 32);

		this.genDecorations(genBase);

		this.randomGenerator = null;
		this.currentWorld = null;
	}

	@Override
	protected void genDecorations(BiomeGenBase genBase)
	{
		this.generateOres();

		int x, y, z;

		int count;

		for (count = 0; count < 1; count++)
		{
			x = this.randomGenerator.nextInt(16) + 8;
			z = this.randomGenerator.nextInt(16) + 8;
			y = this.nextInt(this.currentWorld.getHeight(this.field_180294_c.add(x, 0, z)).getY() * 2);

			this.genAetherGrass.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(x, y, z));
		}

		for (count = 0; count < 6; count++)
		{
			x = this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.randomGenerator.nextInt(16) + 8;

			this.genWhiteRoses.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(x, y, z));
		}

		for (count = 0; count < 6; count++)
		{
			if (this.randomGenerator.nextInt(2) == 0)
			{
				x = this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(128);
				z = this.randomGenerator.nextInt(16) + 8;

				this.genPurpleFlowers.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(x, y, z));
			}
		}

		for (count = 0; count < 2; count ++)
		{
			x = this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.randomGenerator.nextInt(16) + 8;

			this.genWhiteRoses.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(x, y, z));
		}

		for (count = 0; count < 3; count++)
		{
			x = this.randomGenerator.nextInt(16) + 8;
			z = this.randomGenerator.nextInt(16) + 8;

			WorldGenerator treeGen = genBase.genBigTreeChance(this.randomGenerator);
			BlockPos pos = this.currentWorld.getHeight(this.field_180294_c.add(x, 0, z));

			treeGen.generate(this.currentWorld, this.randomGenerator, pos);
		}

		for (count = 0; count < 2; count++)
		{
			x = this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.randomGenerator.nextInt(16) + 8;

			this.genOrangeTree.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(x, y, z));
		}

		for (count = 0; count < 2; count++)
		{
			x = this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.randomGenerator.nextInt(16) + 8;

			this.genBlueberryBushes.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(x, y, z));
		}
	}

	private void genMinableOre(WorldGenerator gen, World world, Random random, BlockPos pos, int minY, int maxY, int attempts)
	{
		for (int count = 0; count < attempts; count++)
		{
			BlockPos randomPos = pos.add(random.nextInt(16), random.nextInt(maxY - minY) + minY, random.nextInt(16));

			gen.generate(world, random, randomPos);
		}
	}

	protected int nextInt(int i)
	{
		if (i <= 1)
		{
			return 0;
		}

		return this.randomGenerator.nextInt(i);
	}

	@Override
	protected void generateOres()
	{
		this.genMinableOre(this.genAmbrosium, this.currentWorld, this.randomGenerator, this.field_180294_c, 0, 128, 20);
		this.genMinableOre(this.genZanite, this.currentWorld, this.randomGenerator, this.field_180294_c, 0, 64, 15);
		this.genMinableOre(this.genGravitite, this.currentWorld, this.randomGenerator, this.field_180294_c, 0, 32, 6);
		this.genMinableOre(this.genContinuum, this.currentWorld, this.randomGenerator, this.field_180294_c, 0, 128, 4);
	}
}
