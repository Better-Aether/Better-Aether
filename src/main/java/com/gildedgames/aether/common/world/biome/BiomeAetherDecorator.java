package com.gildedgames.aether.common.world.biome;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.natural.BlockAercloud;
import com.gildedgames.aether.common.blocks.natural.BlockAercloud.AercloudVariant;
import com.gildedgames.aether.common.blocks.natural.BlockHolystone;
import com.gildedgames.aether.common.blocks.natural.plants.BlockAetherFlower;
import com.gildedgames.aether.common.blocks.natural.plants.BlockBlueberryBush;
import com.gildedgames.aether.common.world.features.TemplatePipeline;
import com.gildedgames.aether.common.world.features.WorldGenAetherFlowers;
import com.gildedgames.aether.common.world.features.WorldGenAetherLakes;
import com.gildedgames.aether.common.world.features.WorldGenAetherMinable;
import com.gildedgames.aether.common.world.features.WorldGenAetherTallGrass;
import com.gildedgames.aether.common.world.features.WorldGenDungeonEntrance;
import com.gildedgames.aether.common.world.features.WorldGenMoaNest;
import com.gildedgames.aether.common.world.features.WorldGenQuicksoil;
import com.gildedgames.aether.common.world.features.WorldGenTemplate;
import com.gildedgames.aether.common.world.features.aerclouds.WorldGenAercloud;
import com.gildedgames.aether.common.world.features.aerclouds.WorldGenPurpleAercloud;
import com.gildedgames.aether.common.world.features.placement_conditions.FlatGroundPlacementCondition;
import com.gildedgames.aether.common.world.features.placement_conditions.InsideGroundPlacementCondition;
import com.gildedgames.aether.common.world.features.placement_conditions.ReplaceablePlacementCondition;
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
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeAetherDecorator
{

	public static final TemplateManager MANAGER = new TemplateManager("structures");

	protected WorldGenAetherTallGrass genAetherGrass;

	protected WorldGenAetherMinable genAmbrosium, genZanite, genGravitite, genIcestone, genArkenium;

	protected WorldGenAetherMinable genMossyHolystone, genCrudeScatterglass;

	protected WorldGenAetherFlowers genPurpleFlowers, genWhiteRoses, genBurstblossom;

	protected WorldGenOrangeTree genOrangeTree;

	protected WorldGenAetherFlowers genBlueberryBushes;

	protected WorldGenQuicksoil genQuicksoil;

	protected WorldGenAetherLakes genAetherLakes;

	protected WorldGenAercloud genColdColumbusAercloud, genColdFlatAercloud, genBlueAercloud;

	protected WorldGenPurpleAercloud genPurpleAercloud;
	
	protected WorldGenTemplate genSliderLabyrinthEntrance;

	protected WorldGenMoaNest genMoaNestTree, genMoaNest1, genMoaNest2;

	protected TemplatePipeline templatePipeline;

	private boolean hasInit = false;

	public boolean generateBushes = true;

	public BiomeAetherDecorator()
	{
		this.genAetherGrass = new WorldGenAetherTallGrass();

		BlockMatcher holystoneMatcher = BlockMatcher.forBlock(BlocksAether.holystone);

		this.genAmbrosium = new WorldGenAetherMinable(BlocksAether.ambrosium_ore.getDefaultState(), 16, holystoneMatcher);
		this.genZanite = new WorldGenAetherMinable(BlocksAether.zanite_ore.getDefaultState(), 8, holystoneMatcher);
		this.genGravitite = new WorldGenAetherMinable(BlocksAether.gravitite_ore.getDefaultState(), 4, holystoneMatcher);
		this.genIcestone = new WorldGenAetherMinable(BlocksAether.icestone_ore.getDefaultState(), 10, holystoneMatcher);
		this.genArkenium = new WorldGenAetherMinable(BlocksAether.arkenium_ore.getDefaultState(), 8, holystoneMatcher);

		this.genMossyHolystone = new WorldGenAetherMinable(BlocksAether.holystone.getDefaultState().withProperty(BlockHolystone.PROPERTY_VARIANT, BlockHolystone.MOSSY_HOLYSTONE), 20, holystoneMatcher);
		this.genCrudeScatterglass = new WorldGenAetherMinable(BlocksAether.crude_scatterglass.getDefaultState(), 20, holystoneMatcher);

		this.genPurpleFlowers = new WorldGenAetherFlowers(BlocksAether.aether_flower.getDefaultState().withProperty(BlockAetherFlower.PROPERTY_VARIANT, BlockAetherFlower.PURPLE_FLOWER), 64);
		this.genWhiteRoses = new WorldGenAetherFlowers(BlocksAether.aether_flower.getDefaultState().withProperty(BlockAetherFlower.PROPERTY_VARIANT, BlockAetherFlower.WHITE_ROSE), 64);
		this.genBurstblossom = new WorldGenAetherFlowers(BlocksAether.aether_flower.getDefaultState().withProperty(BlockAetherFlower.PROPERTY_VARIANT, BlockAetherFlower.BURSTBLOSSOM), 64);

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
			WorldServer worldServer = (WorldServer) world;

			if (genBase instanceof BiomeAetherBase)
			{
				BiomeAetherBase biome = (BiomeAetherBase) genBase;

				if (!biome.areTemplatesInitiated())
				{
					biome.initTemplates(worldServer, this.templatePipeline, MANAGER);

					biome.markTemplatesInitiated();
				}
			}

			if (!this.hasInit)
			{
				MinecraftServer server = worldServer.getMinecraftServer();

				this.genSliderLabyrinthEntrance = new WorldGenDungeonEntrance(this.templatePipeline, MANAGER.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "labyrinth_entrance")));
				this.genMoaNestTree = new WorldGenMoaNest(this.templatePipeline, MANAGER.getTemplate(server, AetherCore.getResource("moa_nest/skyroot_moa_nest_tree_1")), new BlockPos(4, 5, 4), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());
				this.genMoaNest1 = new WorldGenMoaNest(this.templatePipeline, MANAGER.getTemplate(server, AetherCore.getResource("moa_nest/skyroot_moa_nest_1")), new BlockPos(2, 0, 2), new InsideGroundPlacementCondition(), new ReplaceablePlacementCondition());
				this.genMoaNest2 = new WorldGenMoaNest(this.templatePipeline, MANAGER.getTemplate(server, AetherCore.getResource("moa_nest/skyroot_moa_nest_2")), new BlockPos(3, 0, 3), new InsideGroundPlacementCondition(), new ReplaceablePlacementCondition());

				this.hasInit = true;
			}
		}

		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, random, pos));

		int chunkX = pos.getX() >> 4;
		int chunkZ = pos.getZ() >> 4;

		this.generateOres(world, random, pos);

		int x, y, z;

		int count;

		// Moa Nests In Tree
		if (random.nextInt(2) == 0)
		{
			x = random.nextInt(16) + 8;
			z = random.nextInt(16) + 8;

			BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos.add(x, 0, z));

			this.genMoaNestTree.generate(world, random, pos2);
		}

		// Moa Nests
		if (random.nextInt(4) == 0)
		{
			x = random.nextInt(16) + 8;
			z = random.nextInt(16) + 8;

			BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos.add(x, 0, z)).add(0, -1, 0);

			if (random.nextBoolean())
			{
				this.genMoaNest1.generate(world, random, pos2);
			}
			else
			{
				this.genMoaNest2.generate(world, random, pos2);
			}
		}

		// Entrance Generator
		if (random.nextInt(5) == 0)
		{
			x = random.nextInt(16) + 8;
			z = random.nextInt(16) + 8;

			BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos.add(x, 0, z));

			this.genSliderLabyrinthEntrance.generate(world, random, pos2);
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

		//Template Generator
		if (genBase instanceof BiomeAetherBase)
		{
			BiomeAetherBase biome = (BiomeAetherBase)genBase;

			for (count = 0; count < 4; count++)
			{
				x = random.nextInt(16) + 8;
				z = random.nextInt(16) + 8;

				WorldGenTemplate template = biome.fetchTemplateToGenerate(random);
				BlockPos randPos = world.getHeight(pos.add(x, 0, z));

				if (template != null)
				{
					template.generate(world, random, randPos);
				}
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
		if (this.generateBushes)
		{
			for (count = 0; count < 2; count++)
			{
				x = random.nextInt(16) + 8;
				y = random.nextInt(128);
				z = random.nextInt(16) + 8;

				this.genBlueberryBushes.generate(world, random, pos.add(x, y, z));
			}
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

		// Burstblossom Generator
		for (count = 0; count < 2; count++)
		{
			x = random.nextInt(16) + 8;
			y = random.nextInt(128);
			z = random.nextInt(16) + 8;

			this.genBurstblossom.generate(world, random, pos.add(x, y, z));
		}

		this.generateClouds(world, random, new BlockPos(pos.getX(), 0, pos.getZ()));

		this.templatePipeline.constructChunk(world, chunkX, chunkZ);

		// Lake Generator
		/*if (random.nextInt(4) == 0)
		{
			x = random.nextInt(16) + 8;
			y = random.nextInt(128);
			z = random.nextInt(16) + 8;

			this.genAetherLakes.generate(world, random, pos.add(x, y, z));
		}*/
	}

	private void generateMineable(WorldGenAetherMinable minable, World world, Random random, BlockPos pos, int minY, int maxY, int attempts)
	{
		for (int count = 0; count < attempts; count++)
		{
			BlockPos randomPos = pos.add(random.nextInt(16), random.nextInt(maxY - minY) + minY, random.nextInt(16));

			minable.generate(world, random, randomPos);
		}
	}

	private void generateCaveMineable(WorldGenAetherMinable minable, World world, Random random, BlockPos pos, int minY, int maxY, int attempts)
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

	private void generateCloud(WorldGenAercloud gen, World world, BlockPos pos, Random rand, int rarity, int width, int minY, int maxY)
	{
		if (rand.nextInt(rarity) == 0)
		{
			BlockPos nextPos = pos.add(rand.nextInt(width), minY + rand.nextInt(maxY - minY), rand.nextInt(width));

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
		this.generateMineable(this.genZanite, world, random, pos, 0, 128, 15);
		this.generateMineable(this.genGravitite, world, random, pos, 0, 100, 6);
		this.generateMineable(this.genIcestone, world, random, pos, 0, 128, 10);
		this.generateMineable(this.genArkenium, world, random, pos, 0, 128, 20);
		this.generateCaveMineable(this.genMossyHolystone, world, random, pos, 0, 90, 45);
		this.generateCaveMineable(this.genCrudeScatterglass, world, random, pos, 0, 90, 45);
	}

	protected void generateClouds(World world, Random random, BlockPos pos)
	{
		this.generateCloud(this.genBlueAercloud, world, pos, random, 15, 16, 90, 130);
//		this.generateCloud(this.genColdFlatAercloud, world, pos, random, 10, 16, 32);
		this.generateCloud(this.genColdColumbusAercloud, world, pos, random, 30, 16, 90, 130);

		this.generateCloud(this.genPurpleAercloud, world, pos, random, 50, 4, 90, 130);
	}
}
