package com.gildedgames.aether.blocks;

import com.gildedgames.aether.AetherCreativeTabs;
import com.gildedgames.aether.blocks.natural.BlockAercloud;
import com.gildedgames.aether.blocks.natural.BlockAetherDirt;
import com.gildedgames.aether.blocks.natural.BlockAetherLeaves;
import com.gildedgames.aether.blocks.natural.BlockAetherLog;
import com.gildedgames.aether.blocks.natural.BlockAmbrosiumOre;
import com.gildedgames.aether.blocks.natural.BlockContinuumOre;
import com.gildedgames.aether.blocks.natural.BlockGravititeOre;
import com.gildedgames.aether.blocks.natural.BlockHolystone;
import com.gildedgames.aether.blocks.natural.BlockQuicksoil;
import com.gildedgames.aether.blocks.natural.BlockTallAetherGrass;
import com.gildedgames.aether.blocks.natural.BlockZaniteOre;
import com.gildedgames.aether.blocks.util.BlockCustom;
import com.gildedgames.aether.items.itemblocks.ItemBlockVariants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlocksAether
{
	public static Block aether_dirt;

	public static Block holystone;

	public static Block aercloud;

	public static Block aether_log;

	public static Block ambrosium_ore;

	public static Block zanite_ore;

	public static Block gravitite_ore;

	public static Block continuum_ore;

	public static Block skyroot_planks;

	public static Block aether_leaves;

	public static Block aether_portal;

	public static Block tall_aether_grass;

	public static Block quicksoil;

	public static void preInit()
	{
		aether_dirt = registerBlock("aether_dirt", ItemBlockVariants.class, new BlockAetherDirt());

		holystone = registerBlock("holystone", ItemBlockVariants.class, new BlockHolystone());

		aercloud = registerBlock("aercloud", ItemBlockVariants.class, new BlockAercloud());

		aether_log = registerBlock("aether_log", ItemBlockVariants.class, new BlockAetherLog());

		skyroot_planks = registerBlock("skyroot_planks", new BlockCustom(Material.wood).setStepSound(Block.soundTypeWood).setHardness(2.0f)
				.setResistance(5.0f).setCreativeTab(AetherCreativeTabs.tabBlocks));

		ambrosium_ore = registerBlock("ambrosium_ore", new BlockAmbrosiumOre());

		zanite_ore = registerBlock("zanite_ore", new BlockZaniteOre());

		gravitite_ore = registerBlock("gravitite_ore", new BlockGravititeOre());

		continuum_ore = registerBlock("continuum_ore", new BlockContinuumOre());

		aether_leaves = registerBlock("aether_leaves", ItemBlockVariants.class, new BlockAetherLeaves());

		aether_portal = registerBlock("aether_portal", new BlockAetherPortal());

		tall_aether_grass = registerBlock("tall_aether_grass", new BlockTallAetherGrass());

		quicksoil = registerBlock("quicksoil", new BlockQuicksoil());
	}

	private static Block registerBlock(String name, Block block)
	{
		block.setUnlocalizedName(name);
		GameRegistry.registerBlock(block, name);

		return block;
	}

	private static Block registerBlock(String name, Class<? extends ItemBlock> itemblock, Block block)
	{
		block.setUnlocalizedName(name);
		GameRegistry.registerBlock(block, itemblock, name);

		return block;
	}
}
