package com.gildedgames.aether.common.blocks;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.AetherCreativeTabs;
import com.gildedgames.aether.common.blocks.construction.BlockAetherPortal;
import com.gildedgames.aether.common.blocks.construction.BlockAltar;
import com.gildedgames.aether.common.blocks.construction.BlockAmbrosiumTorch;
import com.gildedgames.aether.common.blocks.construction.BlockQuicksoilGlass;
import com.gildedgames.aether.common.blocks.construction.BlockSkyrootChest;
import com.gildedgames.aether.common.blocks.construction.BlockSkyrootDoor;
import com.gildedgames.aether.common.blocks.construction.BlockSkyrootFence;
import com.gildedgames.aether.common.blocks.construction.BlockSkyrootFenceGate;
import com.gildedgames.aether.common.blocks.construction.BlockSkyrootLadder;
import com.gildedgames.aether.common.blocks.construction.BlockSkyrootTrapDoor;
import com.gildedgames.aether.common.blocks.construction.redstone.BlockHolystoneButton;
import com.gildedgames.aether.common.blocks.construction.redstone.BlockHolystonePressurePlate;
import com.gildedgames.aether.common.blocks.construction.redstone.BlockSkyrootButton;
import com.gildedgames.aether.common.blocks.construction.redstone.BlockSkyrootPressurePlate;
import com.gildedgames.aether.common.blocks.construction.signs.BlockStandingSkyrootSign;
import com.gildedgames.aether.common.blocks.construction.signs.BlockWallSkyrootSign;
import com.gildedgames.aether.common.blocks.construction.walls.BlockAerogelWall;
import com.gildedgames.aether.common.blocks.construction.walls.BlockAetherWall;
import com.gildedgames.aether.common.blocks.construction.walls.BlockDivineWall;
import com.gildedgames.aether.common.blocks.construction.walls.BlockSkyrootWall;
import com.gildedgames.aether.common.blocks.containers.BlockHolystoneFurnace;
import com.gildedgames.aether.common.blocks.containers.BlockSkyrootWorkbench;
import com.gildedgames.aether.common.blocks.dungeon.BlockDivine;
import com.gildedgames.aether.common.blocks.dungeon.BlockLabyrinth;
import com.gildedgames.aether.common.blocks.dungeon.BlockLabyrinthChest;
import com.gildedgames.aether.common.blocks.dungeon.BlockLabyrinthDoor;
import com.gildedgames.aether.common.blocks.dungeon.BlockLabyrinthPillar;
import com.gildedgames.aether.common.blocks.dungeon.BlockTeleporter;
import com.gildedgames.aether.common.blocks.natural.BlockAercloud;
import com.gildedgames.aether.common.blocks.natural.BlockAerogel;
import com.gildedgames.aether.common.blocks.natural.BlockAetherGrass;
import com.gildedgames.aether.common.blocks.natural.BlockAetherLeaves;
import com.gildedgames.aether.common.blocks.natural.BlockAetherLog;
import com.gildedgames.aether.common.blocks.natural.BlockGoldenOakLog;
import com.gildedgames.aether.common.blocks.natural.BlockHolystone;
import com.gildedgames.aether.common.blocks.natural.BlockIcestoneBricks;
import com.gildedgames.aether.common.blocks.natural.BlockQuicksoil;
import com.gildedgames.aether.common.blocks.natural.ores.BlockAmbrosiumOre;
import com.gildedgames.aether.common.blocks.natural.ores.BlockArkeniumOre;
import com.gildedgames.aether.common.blocks.natural.ores.BlockContinuumOre;
import com.gildedgames.aether.common.blocks.natural.ores.BlockGravititeOre;
import com.gildedgames.aether.common.blocks.natural.ores.BlockIcestoneOre;
import com.gildedgames.aether.common.blocks.natural.ores.BlockZaniteOre;
import com.gildedgames.aether.common.blocks.natural.plants.BlockAetherFlower;
import com.gildedgames.aether.common.blocks.natural.plants.BlockAetherSapling;
import com.gildedgames.aether.common.blocks.natural.plants.BlockBlueberryBush;
import com.gildedgames.aether.common.blocks.natural.plants.BlockOrangeTree;
import com.gildedgames.aether.common.blocks.natural.plants.BlockTallAetherGrass;
import com.gildedgames.aether.common.blocks.util.BlockCustom;
import com.gildedgames.aether.common.blocks.util.multiblock.BlockMultiDummy;
import com.gildedgames.aether.common.blocks.util.variants.IBlockVariants;
import com.gildedgames.aether.common.items.blocks.ItemBlockVariants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlocksAether
{
	public static Block aether_dirt;

	public static BlockAetherGrass aether_grass;

	public static BlockHolystone holystone;

    public static Block holystone_brick;

	public static BlockAercloud aercloud;

	public static Block ambrosium_ore;

	public static BlockZaniteOre zanite_ore;

	public static BlockGravititeOre gravitite_ore;

	public static BlockContinuumOre continuum_ore;

	public static BlockArkeniumOre arkenium_ore;

	public static Block skyroot_planks;

	public static BlockAetherLog skyroot_log, golden_oak_log;

	public static BlockAetherLeaves blue_skyroot_leaves, green_skyroot_leaves, dark_blue_skyroot_leaves,
			golden_oak_leaves, purple_crystal_leaves, purple_fruit_leaves;

	public static BlockAetherPortal aether_portal;

	public static BlockTallAetherGrass tall_aether_grass;

	public static BlockQuicksoil quicksoil;

	public static BlockSkyrootWorkbench skyroot_crafting_table;

	public static BlockBlueberryBush blueberry_bush;

	public static BlockOrangeTree orange_tree;

	public static BlockAetherFlower aether_flower;

	public static BlockAltar altar;

	public static BlockIcestoneOre icestone_ore;

	public static BlockAerogel aerogel;

	public static Block zanite_block;

	public static Block enchanted_gravitite;

	public static BlockAetherSapling aether_sapling;

	public static BlockLabyrinth carved_stone, sentry_stone;

	public static BlockHolystoneFurnace holystone_furnace;

	public static BlockQuicksoilGlass quicksoil_glass;

	public static BlockSkyrootDoor skyroot_door;

	public static BlockSkyrootChest skyroot_chest;

	public static BlockAmbrosiumTorch ambrosium_torch;

	public static BlockIcestoneBricks icestone_bricks;

	public static BlockSkyrootFence skyroot_fence;

	public static BlockSkyrootFenceGate skyroot_fence_gate;

	public static BlockStandingSkyrootSign standing_skyroot_sign;

	public static BlockWallSkyrootSign wall_skyroot_sign;

	public static BlockSkyrootTrapDoor skyroot_trapdoor;

	public static BlockSkyrootPressurePlate skyroot_pressure_plate;

	public static BlockHolystonePressurePlate holystone_pressure_plate;

	public static BlockSkyrootButton skyroot_button;

	public static BlockButtonStone holystone_button;

	public static BlockSkyrootLadder skyroot_ladder;

	public static BlockAetherWall holystone_wall, mossy_holystone_wall, holystone_brick_wall, carved_stone_wall, icestone_wall, aerogel_wall, skyroot_log_wall,
									divine_stone_wall, sentry_stone_wall, divine_sentry_wall;

	public static BlockTeleporter labyrinth_totem;

	public static Block multiblock_dummy;

	public static BlockLabyrinth carved_capstone;

	public static Block labyrinth_glowing_pillar, labyrinth_pillar;

	public static BlockLabyrinthDoor labyrinth_door;

	public static BlockLabyrinth labyrinth_wall, labyrinth_lightstone, labyrinth_base, labyrinth_headstone;
	
	public static Block labyrinth_eye, wildcard, schematicBoundary, linkedSchematicBoundary;

	public static BlockLabyrinthChest labyrinth_chest;

	public static void preInit()
	{
		aether_dirt = registerBlock("aether_dirt", new BlockCustom(Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.5f), AetherCreativeTabs.tabBlocks);

		aether_grass = registerBlock("aether_grass", new BlockAetherGrass(), AetherCreativeTabs.tabBlocks);

		holystone = registerBlock("holystone", new BlockHolystone(), AetherCreativeTabs.tabBlocks);

		aercloud = registerBlock("aercloud", new BlockAercloud(), AetherCreativeTabs.tabBlocks);

		skyroot_planks = registerBlock("skyroot_planks", new BlockCustom(Material.WOOD)
				.setSoundType(SoundType.WOOD).setHardness(2.0f).setResistance(5.0f), AetherCreativeTabs.tabBlocks);

		ambrosium_ore = registerBlock("ambrosium_ore", new BlockAmbrosiumOre().setLightLevel(0.4f), AetherCreativeTabs.tabBlocks);

		zanite_ore = registerBlock("zanite_ore", new BlockZaniteOre(), AetherCreativeTabs.tabBlocks);

		gravitite_ore = registerBlock("gravitite_ore", new BlockGravititeOre(), AetherCreativeTabs.tabBlocks);

		continuum_ore = registerBlock("continuum_ore", new BlockContinuumOre(), AetherCreativeTabs.tabBlocks);

		arkenium_ore = registerBlock("arkenium_ore", new BlockArkeniumOre(), AetherCreativeTabs.tabBlocks);

		skyroot_log = registerBlock("skyroot_log", new BlockAetherLog(), AetherCreativeTabs.tabBlocks);

		golden_oak_log = registerBlock("golden_oak_log", new BlockGoldenOakLog(), AetherCreativeTabs.tabBlocks);

		blue_skyroot_leaves = registerBlock("blue_skyroot_leaves", new BlockAetherLeaves(BlockAetherSapling.BLUE_SKYROOT_SAPLING.getMeta()), AetherCreativeTabs.tabBlocks);

		green_skyroot_leaves = registerBlock("green_skyroot_leaves", new BlockAetherLeaves(BlockAetherSapling.GREEN_SKYROOT_SAPLING.getMeta()), AetherCreativeTabs.tabBlocks);

		dark_blue_skyroot_leaves = registerBlock("dark_blue_skyroot_leaves", new BlockAetherLeaves(BlockAetherSapling.DARK_BLUE_SKYROOT_SAPLING.getMeta()), AetherCreativeTabs.tabBlocks);

		golden_oak_leaves = registerBlock("golden_oak_leaves", new BlockAetherLeaves(BlockAetherSapling.GOLDEN_OAK_SAPLING.getMeta()), AetherCreativeTabs.tabBlocks);

		purple_crystal_leaves = registerBlock("purple_crystal_leaves", new BlockAetherLeaves(BlockAetherSapling.PURPLE_CRYSTAL_SAPLING.getMeta()), AetherCreativeTabs.tabBlocks);

		purple_fruit_leaves = registerBlock("purple_fruit_leaves", new BlockAetherLeaves(BlockAetherSapling.PURPLE_CRYSTAL_SAPLING.getMeta()), AetherCreativeTabs.tabBlocks);

		aether_portal = registerBlock("aether_portal", new BlockAetherPortal());

		tall_aether_grass = registerBlock("tall_aether_grass", new BlockTallAetherGrass(), AetherCreativeTabs.tabBlocks);

		quicksoil = registerBlock("quicksoil", new BlockQuicksoil(), AetherCreativeTabs.tabBlocks);

		skyroot_crafting_table = registerBlock("skyroot_crafting_table", new BlockSkyrootWorkbench(), AetherCreativeTabs.tabBlocks);

		blueberry_bush = registerBlock("blueberry_bush", new BlockBlueberryBush(), AetherCreativeTabs.tabBlocks);

		orange_tree = registerBlock("orange_tree", new BlockOrangeTree(), AetherCreativeTabs.tabBlocks);

		aether_flower = registerBlock("aether_flower", new BlockAetherFlower(), AetherCreativeTabs.tabBlocks);

		altar = registerBlock("altar", new BlockAltar(), AetherCreativeTabs.tabBlocks);

		icestone_ore = registerBlock("icestone_ore", new BlockIcestoneOre(), AetherCreativeTabs.tabBlocks);

		aerogel = registerBlock("aerogel", new BlockAerogel(), AetherCreativeTabs.tabBlocks);

		zanite_block = registerBlock("zanite_block", new BlockCustom(Material.IRON).setSoundType(SoundType.METAL).setHardness(5f), AetherCreativeTabs.tabBlocks);

		enchanted_gravitite = registerBlock("enchanted_gravitite", new BlockCustom(Material.ROCK).setSoundType(SoundType.STONE).setHardness(5f), AetherCreativeTabs.tabBlocks);

		aether_sapling = registerBlock("aether_sapling", new BlockAetherSapling(), AetherCreativeTabs.tabBlocks);

		carved_stone = registerBlock("carved_stone", new BlockDivine(), AetherCreativeTabs.tabBlocks);

		sentry_stone = registerBlock("sentry_stone", new BlockDivine().setGlows(true), AetherCreativeTabs.tabBlocks);

		holystone_brick = registerBlock("holystone_brick", new BlockCustom(Material.ROCK).setSoundType(SoundType.STONE).setHardness(2f), AetherCreativeTabs.tabBlocks);

		holystone_furnace = registerBlock("holystone_furnace", new BlockHolystoneFurnace(), AetherCreativeTabs.tabBlocks);

		quicksoil_glass = registerBlock("quicksoil_glass", new BlockQuicksoilGlass(), AetherCreativeTabs.tabBlocks);

		skyroot_door = registerBlock("skyroot_door", new BlockSkyrootDoor());

		skyroot_chest = registerBlock("skyroot_chest", new BlockSkyrootChest(), AetherCreativeTabs.tabBlocks);

		ambrosium_torch = registerBlock("ambrosium_torch", new BlockAmbrosiumTorch(), AetherCreativeTabs.tabBlocks);

		icestone_bricks = registerBlock("icestone_bricks", new BlockIcestoneBricks(), AetherCreativeTabs.tabBlocks);

		skyroot_fence = registerBlock("skyroot_fence", new BlockSkyrootFence(), AetherCreativeTabs.tabBlocks);

		skyroot_fence_gate = registerBlock("skyroot_fence_gate", new BlockSkyrootFenceGate(), AetherCreativeTabs.tabBlocks);

		standing_skyroot_sign = registerBlock("standing_skyroot_sign", new BlockStandingSkyrootSign());

		skyroot_trapdoor = registerBlock("skyroot_trapdoor", new BlockSkyrootTrapDoor(), AetherCreativeTabs.tabBlocks);

		skyroot_button = registerBlock("skyroot_button", new BlockSkyrootButton(), AetherCreativeTabs.tabBlocks);

		skyroot_pressure_plate = registerBlock("skyroot_pressure_plate", new BlockSkyrootPressurePlate(), AetherCreativeTabs.tabBlocks);

		holystone_pressure_plate = registerBlock("holystone_pressure_plate", new BlockHolystonePressurePlate(), AetherCreativeTabs.tabBlocks);

		holystone_button = registerBlock("holystone_button", new BlockHolystoneButton(), AetherCreativeTabs.tabBlocks);

		skyroot_ladder = registerBlock("skyroot_ladder", new BlockSkyrootLadder(), AetherCreativeTabs.tabBlocks);

		wall_skyroot_sign = registerBlock("wall_skyroot_sign", new BlockWallSkyrootSign());

		labyrinth_chest = registerBlock("labyrinth_chest", new BlockLabyrinthChest(), AetherCreativeTabs.tabBlocks);

		holystone_wall = registerBlock("holystone_wall", new BlockAetherWall(BlocksAether.holystone.getDefaultState(), 1.0f, 10.0f), AetherCreativeTabs.tabBlocks);
		mossy_holystone_wall = registerBlock("mossy_holystone_wall", new BlockAetherWall(BlocksAether.holystone.getDefaultState(), 1.0f, 10.0f), AetherCreativeTabs.tabBlocks);
		holystone_brick_wall = registerBlock("holystone_brick_wall", new BlockAetherWall(BlocksAether.holystone.getDefaultState(), 1.0f, 10.0f), AetherCreativeTabs.tabBlocks);
		carved_stone_wall = registerBlock("carved_stone_wall", new BlockAetherWall(BlocksAether.carved_stone.getDefaultState(), 1.0f, 10.0f), AetherCreativeTabs.tabBlocks);
		icestone_wall = registerBlock("icestone_wall", new BlockAetherWall(BlocksAether.icestone_bricks.getDefaultState(), 3.0f, 10.0f), AetherCreativeTabs.tabBlocks);
		skyroot_log_wall = registerBlock("skyroot_log_wall", new BlockSkyrootWall(BlocksAether.skyroot_log.getDefaultState(), 2.0f, 10.0f), AetherCreativeTabs.tabBlocks);
		aerogel_wall = registerBlock("aerogel_wall", new BlockAerogelWall(BlocksAether.aerogel.getDefaultState(), 1.0f, 10.f), AetherCreativeTabs.tabBlocks);

		labyrinth_totem = registerBlock("labyrinth_totem", new BlockTeleporter(Material.IRON), AetherCreativeTabs.tabBlocks);
		labyrinth_door = registerBlock("labyrinth_door", new  BlockLabyrinthDoor (Material.ROCK), AetherCreativeTabs.tabBlocks);

		multiblock_dummy = registerBlock("multiblock_dummy", new BlockMultiDummy().setBlockUnbreakable());

		carved_capstone = registerBlock("carved_capstone", new BlockLabyrinth(), AetherCreativeTabs.tabBlocks);
		labyrinth_glowing_pillar = registerBlock("labyrinth_glowing_pillar", new BlockLabyrinthPillar().setGlows(true), AetherCreativeTabs.tabBlocks);
		labyrinth_pillar = registerBlock("labyrinth_pillar", new BlockLabyrinthPillar(), AetherCreativeTabs.tabBlocks);
		labyrinth_wall = registerBlock("labyrinth_wall", new BlockLabyrinth(), AetherCreativeTabs.tabBlocks);
		labyrinth_lightstone = registerBlock("labyrinth_lightstone", new BlockLabyrinth().setGlows(true), AetherCreativeTabs.tabBlocks);
		labyrinth_base = registerBlock("labyrinth_base", new BlockLabyrinth().setGlows(true), AetherCreativeTabs.tabBlocks);
		labyrinth_headstone = registerBlock("labyrinth_headstone", new BlockLabyrinth(), AetherCreativeTabs.tabBlocks);

		sentry_stone_wall = registerBlock("sentry_stone_wall", new BlockAetherWall(BlocksAether.labyrinth_lightstone.getDefaultState(), 1.0f, 10.0f).setGlows(true), AetherCreativeTabs.tabBlocks);
		divine_sentry_wall = registerBlock("divine_sentry_wall", new BlockDivineWall(BlocksAether.holystone.getDefaultState(), 1.0f, 10.0f), AetherCreativeTabs.tabBlocks);
		divine_stone_wall = registerBlock("divine_stone_wall", new BlockDivineWall(BlocksAether.holystone.getDefaultState(), 1.0f, 10.0f), AetherCreativeTabs.tabBlocks);
		
		registerHarvestLevels();
	}

	private static void registerHarvestLevels()
	{
		aether_dirt.setHarvestLevel("shovel", 0);
		aether_grass.setHarvestLevel("shovel", 0);
		holystone.setHarvestLevel("pickaxe", 0);
		holystone_brick.setHarvestLevel("pickaxe", 0);
		quicksoil.setHarvestLevel("shovel", 0);
		aercloud.setHarvestLevel("shovel", 0);

		skyroot_log.setHarvestLevel("axe", 0);
		golden_oak_log.setHarvestLevel("axe", 0);
		skyroot_planks.setHarvestLevel("axe", 0);

		ambrosium_ore.setHarvestLevel("pickaxe", 0);
		zanite_ore.setHarvestLevel("pickaxe", 1);
		gravitite_ore.setHarvestLevel("pickaxe", 2);
		continuum_ore.setHarvestLevel("pickaxe", 3);
		arkenium_ore.setHarvestLevel("pickaxe", 2);

		icestone_ore.setHarvestLevel("pickaxe", 1);
		icestone_bricks.setHarvestLevel("pickaxe", 1);
		aerogel.setHarvestLevel("pickaxe", 1);

		altar.setHarvestLevel("pickaxe", 0);
		holystone_furnace.setHarvestLevel("pickaxe", 0);
		skyroot_crafting_table.setHarvestLevel("axe", 0);

		zanite_block.setHarvestLevel("pickaxe", 1);
		enchanted_gravitite.setHarvestLevel("pickaxe", 2);

		carved_stone.setHarvestLevel("pickaxe", 0);
		labyrinth_lightstone.setHarvestLevel("pickaxe", 0);

		holystone_brick.setHarvestLevel("pickaxe", 0);

		skyroot_door.setHarvestLevel("axe", 0);
		skyroot_trapdoor.setHarvestLevel("axe", 0);

		holystone_wall.setHarvestLevel("pickaxe", 0);
		mossy_holystone_wall.setHarvestLevel("pickaxe", 0);
		carved_stone_wall.setHarvestLevel("pickaxe", 0);
		skyroot_log_wall.setHarvestLevel("axe", 0);
		icestone_wall.setHarvestLevel("pickaxe", 1);
		aerogel_wall.setHarvestLevel("pickaxe", 1);
	}

	private static <T extends Block> T registerBlock(String name, T block, CreativeTabs tab)
	{
		registerBlock(name, block.setCreativeTab(tab));

		return block;
	}

	private static <T extends Block> T registerBlock(String name, T block)
	{
		block.setUnlocalizedName(AetherCore.MOD_ID + "." + name);
		block.setRegistryName(name);

		GameRegistry.register(block);

		if (block instanceof IBlockVariants)
		{
			registerItemBlock(new ItemBlockVariants(block));
		}
		else
		{
			registerItemBlock(new ItemBlock(block));
		}

		return block;
	}

	private static void registerItemBlock(ItemBlock item)
	{
		item.setRegistryName(item.getBlock().getRegistryName());

		GameRegistry.register(item);
	}
}
