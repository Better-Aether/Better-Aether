package com.gildedgames.aether.common.world.biome;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.natural.BlockAercloud;
import com.gildedgames.aether.common.blocks.natural.BlockAercloud.AercloudVariant;
import com.gildedgames.aether.common.blocks.natural.BlockHolystone;
import com.gildedgames.aether.common.blocks.natural.plants.BlockAetherFlower;
import com.gildedgames.aether.common.blocks.natural.plants.BlockBlueberryBush;
import com.gildedgames.aether.common.entities.util.MoaNest;
import com.gildedgames.aether.common.world.features.*;
import com.gildedgames.aether.common.world.features.aerclouds.WorldGenAercloud;
import com.gildedgames.aether.common.world.features.aerclouds.WorldGenPurpleAercloud;
import com.gildedgames.aether.common.world.features.trees.WorldGenOrangeTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeAetherDecorator
{

	public static final TemplateManager MANAGER = new TemplateManager("structures");

	protected WorldGenAetherTallGrass genAetherGrass;

	protected WorldGenMinable genAmbrosium, genZanite, genGravitite, genContinuum, genIcestone, genArkenium;

	protected WorldGenMinable genMossyHolystone;

	protected WorldGenAetherFlowers genPurpleFlowers, genWhiteRoses;

	protected WorldGenOrangeTree genOrangeTree;

	protected WorldGenAetherFlowers genBlueberryBushes;

	protected WorldGenQuicksoil genQuicksoil;

	protected WorldGenAetherLakes genAetherLakes;

	protected WorldGenAercloud genColdColumbusAercloud, genColdFlatAercloud, genBlueAercloud;

	protected WorldGenPurpleAercloud genPurpleAercloud;
	
	protected WorldGenTemplate genSliderLabyrinthEntrance;

	protected TemplatePipeline templatePipeline;

	public BiomeAetherDecorator()
	{
		this.genAetherGrass = new WorldGenAetherTallGrass();

		BlockMatcher holystoneMatcher = BlockMatcher.forBlock(BlocksAether.holystone);

		this.genAmbrosium = new WorldGenMinable(BlocksAether.ambrosium_ore.getDefaultState(), 16, holystoneMatcher);
		this.genZanite = new WorldGenMinable(BlocksAether.zanite_ore.getDefaultState(), 8, holystoneMatcher);
		this.genGravitite = new WorldGenMinable(BlocksAether.gravitite_ore.getDefaultState(), 4, holystoneMatcher);
		this.genContinuum = new WorldGenMinable(BlocksAether.continuum_ore.getDefaultState(), 4, holystoneMatcher);
		this.genIcestone = new WorldGenMinable(BlocksAether.icestone_ore.getDefaultState(), 10, holystoneMatcher);
		this.genArkenium = new WorldGenMinable(BlocksAether.arkenium_ore.getDefaultState(), 8, holystoneMatcher);

		this.genMossyHolystone = new WorldGenMinable(BlocksAether.holystone.getDefaultState().withProperty(BlockHolystone.PROPERTY_VARIANT, BlockHolystone.MOSSY_HOLYSTONE), 20, holystoneMatcher);

		this.genPurpleFlowers = new WorldGenAetherFlowers(BlocksAether.aether_flower.getDefaultState().withProperty(BlockAetherFlower.PROPERTY_VARIANT, BlockAetherFlower.PURPLE_FLOWER), 64);
		this.genWhiteRoses = new WorldGenAetherFlowers(BlocksAether.aether_flower.getDefaultState().withProperty(BlockAetherFlower.PROPERTY_VARIANT, BlockAetherFlower.WHITE_ROSE), 64);

		this.genOrangeTree = new WorldGenOrangeTree();

		this.genBlueberryBushes = new WorldGenAetherFlowers(BlocksAether.blueberry_bush.getDefaultState().withProperty(BlockBlueberryBush.PROPERTY_HARVESTABLE, true), 32);

		this.genQuicksoil = new WorldGenQuicksoil();
		this.genAetherLakes = new WorldGenAetherLakes(Blocks.WATER.getDefaultState());

		this.genColdFlatAercloud = new WorldGenAercloud(this.getAercloudState(BlockAercloud.COLD_AERCLOUD), 64, true);
		this.genColdColumbusAercloud = new WorldGenAercloud(this.getAercloudState(BlockAercloud.COLD_AERCLOUD), 16, false);
		this.genBlueAercloud = new WorldGenAercloud(this.getAercloudState(BlockAercloud.BLUE_AERCLOUD), 8, false);

		this.genPurpleAercloud = new WorldGenPurpleAercloud(this.getAercloudState(BlockAercloud.PURPLE_AERCLOUD), 4, false);

		this.templatePipeline = new TemplatePipeline();
	}

	protected IBlockState getAercloudState(AercloudVariant variant)
	{
		return BlocksAether.aercloud.getDefaultState().withProperty(BlockAercloud.PROPERTY_VARIANT, variant);
	}

	protected void genDecorations(final World world, Random random, BlockPos pos, Biome genBase)
	{
		if (world instanceof WorldServer)
		{
			WorldServer worldServer = (WorldServer)world;
			MinecraftServer server = worldServer.getMinecraftServer();

			this.genSliderLabyrinthEntrance = new WorldGenTemplate(this.templatePipeline, MANAGER.func_189942_b(server, new ResourceLocation(AetherCore.MOD_ID, "labyrinth_entrance")));
		}

		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, random, pos));

		int chunkX = pos.getX() >> 4;
		int chunkZ = pos.getZ() >> 4;

		this.generateOres(world, random, pos);

		int x, y, z;

		int count;

		// Moa Nests
		for (count = 0; count < 3; count++)
		{
			x = random.nextInt(16) + 8;
			y = random.nextInt(128);
			z = random.nextInt(16) + 8;

			new MoaNest(world, pos.add(x, y, z)).generate(world, random, 1 + random.nextInt(2), BlocksAether.woven_skyroot_sticks.getDefaultState());
		}

		// Tree Generator
		for (count = 0; count < 4; count++)
		{
			x = random.nextInt(16) + 8;
			z = random.nextInt(16) + 8;

			WorldGenerator treeGen = genBase.genBigTreeChance(random);
			BlockPos randPos = world.getHeight(pos.add(x, 0, z));

			if (treeGen != null)
			{
				treeGen.generate(world, random, randPos);
			}
		}

		// Orange Tree Generator
		for (count = 0; count < 2; count++)
		{
			x = random.nextInt(16) + 8;
			y = random.nextInt(128);
			z = random.nextInt(16) + 8;

			this.genOrangeTree.generate(world, random, pos.add(x, y, z));
		}

		// Blueberry Bush Generator
		for (count = 0; count < 2; count++)
		{
			x = random.nextInt(16) + 8;
			y = random.nextInt(128);
			z = random.nextInt(16) + 8;

			this.genBlueberryBushes.generate(world, random, pos.add(x, y, z));
		}

		// Aether Tall Grass Generator
		for (count = 0; count < 4; count++)
		{
			x = random.nextInt(16) + 8;
			z = random.nextInt(16) + 8;
			y = this.nextInt(random, world.getHeight(pos.add(x, 0, z)).getY() * 2);

			this.genAetherGrass.generate(world, random, pos.add(x, y, z));
		}

		// Purple Flowers Generator
		for (count = 0; count < 6; count++)
		{
			if (random.nextInt(2) == 0)
			{
				x = random.nextInt(16) + 8;
				y = random.nextInt(128);
				z = random.nextInt(16) + 8;

				this.genPurpleFlowers.generate(world, random, pos.add(x, y, z));
			}
		}

		// White Rose Generator
		for (count = 0; count < 2; count++)
		{
			x = random.nextInt(16) + 8;
			y = random.nextInt(128);
			z = random.nextInt(16) + 8;

			this.genWhiteRoses.generate(world, random, pos.add(x, y, z));
		}

		// Quicksoil Generator
		if (random.nextInt(5) == 0)
		{
			for (x = pos.getX(); x < pos.getX() + 16; x++)
			{
				for (z = pos.getZ(); z < pos.getZ() + 16; z++)
				{
					BlockPos top = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));

					for (y = top.getY(); y > 12 && y < 48; y--)
					{
						BlockPos randPos = new BlockPos(x, y, z);

						if (world.isAirBlock(randPos) && world.getBlockState(randPos.up()).getBlock() == BlocksAether.aether_grass && world.isAirBlock(randPos.up(2)))
						{
							this.genQuicksoil.generate(world, random, randPos);
						}
					}
				}
			}
		}

		//Entrance Generator
		for (int n = 0; n < 60; n++)
		{
			x = random.nextInt(16) + 8;
			y = random.nextInt(128);
			z = random.nextInt(16) + 8;

			final BlockPos totemPos = pos.add(x + 4, y + 3, z + 4);

			this.genSliderLabyrinthEntrance.generate(world, random, pos.add(x, y, z), new Runnable()
			{

				@Override public void run()
				{
					world.setBlockState(totemPos, BlocksAether.labyrinth_totem.getDefaultState());
				}

			});
		}
		
		this.generateClouds(world, random, new BlockPos(pos.getX(), 0, pos.getZ()));

		this.templatePipeline.constructChunk(world, chunkX, chunkZ);

		// Lake Generator
		if (random.nextInt(4) == 0)
		{
			x = random.nextInt(16) + 8;
			y = random.nextInt(128);
			z = random.nextInt(16) + 8;

			this.genAetherLakes.generate(world, random, pos.add(x, y, z));
		}
	}

	public BlockPos getTopBlock(World world, BlockPos pos)
	{
		BlockPos searchPos = new BlockPos(pos.getX(), world.getActualHeight(), pos.getZ());

		while (!world.isAirBlock(searchPos.down()))
		{
			searchPos = searchPos.down();
		}

		return searchPos;
	}

	private void generateMineable(WorldGenMinable minable, World world, Random random, BlockPos pos, int minY, int maxY, int attempts)
	{
		for (int count = 0; count < attempts; count++)
		{
			BlockPos randomPos = pos.add(random.nextInt(16), random.nextInt(maxY - minY) + minY, random.nextInt(16));

			minable.generate(world, random, randomPos);
		}
	}

	private void generateCaveMineable(WorldGenMinable minable, World world, Random random, BlockPos pos, int minY, int maxY, int attempts)
	{
		for (int count = 0; count < attempts; count++)
		{
			BlockPos randomPos = pos.add(random.nextInt(16), random.nextInt(maxY - minY) + minY, random.nextInt(16));
//			randomPos = world.getTopBlockHeight(randomPos);

			if (world.getLightFor(EnumSkyBlock.SKY, randomPos) <= 7)
			{
				minable.generate(world, random, randomPos);
			}
		}
	}

	private void generateCloud(WorldGenAercloud gen, World world, BlockPos pos, Random rand, int rarity, int width, int height)
	{
		if (rand.nextInt(rarity) == 0)
		{
			BlockPos nextPos = pos.add(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width));

			gen.generate(world, rand, nextPos);
		}
	}

	protected int nextInt(Random random, int i)
	{
		if (i <= 1)
		{
			return 0;
		}

		return random.nextInt(i);
	}

	protected void generateOres(World world, Random random, BlockPos pos)
	{
		this.generateMineable(this.genAmbrosium, world, random, pos, 0, 128, 20);
		this.generateMineable(this.genZanite, world, random, pos, 0, 64, 15);
		this.generateMineable(this.genGravitite, world, random, pos, 0, 32, 6);
		this.generateMineable(this.genContinuum, world, random, pos, 0, 128, 4);
		this.generateMineable(this.genIcestone, world, random, pos, 0, 128, 10);
		this.generateMineable(this.genArkenium, world, random, pos, 0, 128, 20);
		this.generateCaveMineable(this.genMossyHolystone, world, random, pos, 0, 90, 45);
	}

	protected void generateClouds(World world, Random random, BlockPos pos)
	{
		this.generateCloud(this.genBlueAercloud, world, pos, random, 15, 16, 65);
//		this.generateCloud(this.genColdFlatAercloud, world, pos, random, 10, 16, 32);
		this.generateCloud(this.genColdColumbusAercloud, world, pos, random, 30, 16, 65);

		this.generateCloud(this.genPurpleAercloud, world, pos, random, 50, 4, 32);
	}
}
