package com.gildedgames.aether.common.recipes;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.items.weapons.ItemDartType;
import com.gildedgames.aether.common.items.weapons.crossbow.ItemBoltType;
import com.gildedgames.aether.common.recipes.altar.AltarEnchantRecipe;
import com.gildedgames.aether.common.recipes.altar.AltarRepairRecipe;
import com.gildedgames.aether.common.recipes.altar.IAltarRecipe;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesAether
{
	public static final AltarRegistry ALTAR_REGISTRY = new AltarRegistry();

	public static void preInit()
	{
		registerCraftingRecipes();
		registerToolRecipes();
		registerArmorRecipes();
		registerAltarRecipes();

		GameRegistry.registerFuelHandler(new AetherFuelHandler());
	}

	private static void registerCraftingRecipes()
	{
		// Skyroot Planks
		registerShapelessRecipe(new ItemStack(BlocksAether.skyroot_planks, 4),
				new ItemStack(BlocksAether.skyroot_log));

		// Skyroot Sticks
		registerShapedRecipe(new ItemStack(ItemsAether.skyroot_stick, 4), "X", "X",
				'X', new ItemStack(BlocksAether.skyroot_planks));

		// Skyroot Crafting Table
		registerShapedRecipe(new ItemStack(BlocksAether.skyroot_crafting_table), "XX", "XX",
				'X', new ItemStack(BlocksAether.skyroot_planks));

		// Skyroot Chest
		registerShapedRecipe(new ItemStack(BlocksAether.skyroot_chest), "XXX", "X X", "XXX",
				'X', new ItemStack(BlocksAether.skyroot_planks));

		registerShapedRecipe(new ItemStack(BlocksAether.altar), "XXX", "XYX", "XXX",
				'X', new ItemStack(BlocksAether.holystone), 'Y', new ItemStack(ItemsAether.zanite_gemstone));

		// Skyroot Door
		registerShapedRecipe(new ItemStack(ItemsAether.skyroot_door), "XX", "XX", "XX",
				'X', new ItemStack(BlocksAether.skyroot_planks));

		// Golden Dart Shooter
		registerShapedRecipe(new ItemStack(ItemsAether.dart_shooter, 1, ItemDartType.GOLDEN.ordinal()), "X", "X", "Y",
				'X', new ItemStack(BlocksAether.skyroot_planks), 'Y', new ItemStack(ItemsAether.golden_amber));

		// Poison Dart Shooter
		registerShapelessRecipe(new ItemStack(ItemsAether.dart_shooter, 1, ItemDartType.POISON.ordinal()),
				new ItemStack(ItemsAether.dart_shooter, 1, ItemDartType.GOLDEN.ordinal()), new ItemStack(ItemsAether.skyroot_poison_bucket));

		// Zanite Block
		registerShapedRecipe(new ItemStack(BlocksAether.zanite_block), "XXX", "XXX", "XXX",
				'X', new ItemStack(ItemsAether.zanite_gemstone));

		// Icestone Bricks
		registerShapedRecipe(new ItemStack(BlocksAether.icestone_bricks, 4), "XX", "XX",
				'X', new ItemStack(ItemsAether.icestone));

		// Fences and Walls
		registerShapedRecipe(new ItemStack(BlocksAether.skyroot_fence, 3), "XYX", "XYX",
				'X', new ItemStack(BlocksAether.skyroot_planks),
				'Y', new ItemStack(ItemsAether.skyroot_stick));

		registerShapedRecipe(new ItemStack(BlocksAether.holystone_wall, 6), "XXX", "XXX",
				'X', new ItemStack(BlocksAether.holystone));
		registerShapedRecipe(new ItemStack(BlocksAether.skyroot_log_wall, 6), "XXX", "XXX",
				'X', new ItemStack(BlocksAether.skyroot_log));
		registerShapedRecipe(new ItemStack(BlocksAether.icestone_wall, 6), "XXX", "XXX",
				'X', new ItemStack(BlocksAether.icestone_bricks));
		registerShapedRecipe(new ItemStack(BlocksAether.carved_stone_wall, 6), "XXX", "XXX",
				'X', new ItemStack(BlocksAether.carved_stone));
		registerShapedRecipe(new ItemStack(BlocksAether.aerogel_wall, 6), "XXX", "XXX",
				'X', new ItemStack(BlocksAether.aerogel));

		// Gates
		registerShapedRecipe(new ItemStack(BlocksAether.skyroot_fence_gate, 1), "YXY", "YXY",
				'Y', new ItemStack(ItemsAether.skyroot_stick),
				'X', new ItemStack(BlocksAether.skyroot_planks));

		// Sign
		registerShapedRecipe(new ItemStack(ItemsAether.skyroot_sign, 3), "XXX", "XXX", " Y ",
				'X', new ItemStack(BlocksAether.skyroot_planks),
				'Y', new ItemStack(ItemsAether.skyroot_stick));

		//Crossbow
		registerShapedRecipe(new ItemStack(ItemsAether.crossbow), " X ", " Y ", "ZZZ",
				'X', new ItemStack(ItemsAether.skyroot_stick),
				'Y', new ItemStack(Items.string),
				'Z', new ItemStack(BlocksAether.skyroot_planks));

		// Crossbow Bolts
		registerShapedRecipe(new ItemStack(ItemsAether.bolt, 4, ItemBoltType.STONE.ordinal()), " Y", "X ",
				'X', new ItemStack(ItemsAether.skyroot_stick),
				'Y', new ItemStack(BlocksAether.holystone));
		registerShapedRecipe(new ItemStack(ItemsAether.bolt, 4, ItemBoltType.ZANITE.ordinal()), " Y", "X ",
				'X', new ItemStack(ItemsAether.skyroot_stick),
				'Y', new ItemStack(ItemsAether.zanite_gemstone));
	}

	private static void registerToolRecipes()
	{
		ItemStack skyrootStick = new ItemStack(ItemsAether.skyroot_stick);

		ItemStack skyroot = new ItemStack(BlocksAether.skyroot_planks);
		ItemStack holystone = new ItemStack(BlocksAether.holystone);
		ItemStack zanite = new ItemStack(ItemsAether.zanite_gemstone);
		ItemStack gravitite = new ItemStack(BlocksAether.enchanted_gravitite);

		// Skyroot Tools
		registerShapedRecipe(new ItemStack(ItemsAether.skyroot_axe), "XX ", "XY ", " Y ",
				'X', skyroot, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.skyroot_pickaxe), "XXX", " Y ", " Y ",
				'X', skyroot, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.skyroot_shovel), "X", "Y", "Y",
				'X', skyroot, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.skyroot_sword), "X", "X", "Y",
				'X', skyroot, 'Y', skyrootStick);

		// Holystone Tools
		registerShapedRecipe(new ItemStack(ItemsAether.holystone_axe), "XX ", "XY ", " Y ",
				'X', holystone, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.holystone_pickaxe), "XXX", " Y ", " Y ",
				'X', holystone, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.holystone_shovel), "X", "Y", "Y",
				'X', holystone, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.holystone_sword), "X", "X", "Y",
				'X', holystone, 'Y', skyrootStick);

		// Zanite Tools
		registerShapedRecipe(new ItemStack(ItemsAether.zanite_axe), "XX ", "XY ", " Y ",
				'X', zanite, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.zanite_pickaxe), "XXX", " Y ", " Y ",
				'X', zanite, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.zanite_shovel), "X", "Y", "Y",
				'X', zanite, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.zanite_sword), "X", "X", "Y",
				'X', zanite, 'Y', skyrootStick);

		// Gravitite Tools
		registerShapedRecipe(new ItemStack(ItemsAether.gravitite_axe), "XX ", "XY ", " Y ",
				'X', gravitite, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.gravitite_pickaxe), "XXX", " Y ", " Y ",
				'X', gravitite, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.gravitite_shovel), "X", "Y", "Y",
				'X', gravitite, 'Y', skyrootStick);

		registerShapedRecipe(new ItemStack(ItemsAether.gravitite_sword), "X", "X", "Y",
				'X', gravitite, 'Y', skyrootStick);
	}

	private static void registerArmorRecipes()
	{
		ItemStack zanite = new ItemStack(ItemsAether.zanite_gemstone);
		ItemStack gravitite = new ItemStack(BlocksAether.enchanted_gravitite);

		// Zanite Armor
		registerShapedRecipe(new ItemStack(ItemsAether.zanite_helmet), "XXX", "X X",
				'X', zanite);

		registerShapedRecipe(new ItemStack(ItemsAether.zanite_chestplate), "X X", "XXX", "XXX",
				'X', zanite);

		registerShapedRecipe(new ItemStack(ItemsAether.zanite_leggings), "XXX", "X X", "X X",
				'X', zanite);

		registerShapedRecipe(new ItemStack(ItemsAether.zanite_boots), "X X", "X X",
				'X', zanite);

		// Gravitite Armor
		registerShapedRecipe(new ItemStack(ItemsAether.gravitite_helmet), "XXX", "X X",
				'X', gravitite);

		registerShapedRecipe(new ItemStack(ItemsAether.gravitite_chestplate), "X X", "XXX", "XXX",
				'X', gravitite);

		registerShapedRecipe(new ItemStack(ItemsAether.gravitite_leggings), "XXX", "X X", "X X",
				'X', gravitite);

		registerShapedRecipe(new ItemStack(ItemsAether.gravitite_boots), "X X", "X X",
				'X', gravitite);

	}

	private static void registerAltarRecipes()
	{
		// Healing Stone
		registerAltarRecipe(new AltarEnchantRecipe(4, new ItemStack(BlocksAether.holystone),
				new ItemStack(ItemsAether.healing_stone)));

		// Enchanted Gravitite
		registerAltarRecipe(new AltarEnchantRecipe(4, new ItemStack(BlocksAether.gravitite_ore),
				new ItemStack(BlocksAether.enchanted_gravitite)));

		// Enchanted Dart Shooter
		registerAltarRecipe(new AltarEnchantRecipe(4, new ItemStack(ItemsAether.dart_shooter, 1, ItemDartType.GOLDEN.ordinal()),
				new ItemStack(ItemsAether.dart_shooter, 1, ItemDartType.ENCHANTED.ordinal())));

		// Enchanted Darts
		registerAltarRecipe(new AltarEnchantRecipe(1, new ItemStack(ItemsAether.dart, 1, ItemDartType.GOLDEN.ordinal()),
				new ItemStack(ItemsAether.dart, 1, ItemDartType.ENCHANTED.ordinal())));

		// Tool Repair Recipes
		registerAltarRecipe(new AltarRepairRecipe());
	}

	private static void registerShapelessRecipe(ItemStack output, Object... stacks)
	{
		GameRegistry.addShapelessRecipe(output, stacks);
	}

	private static void registerShapedRecipe(ItemStack output, Object... params)
	{
		GameRegistry.addShapedRecipe(output, params);
	}

	private static void registerAltarRecipe(IAltarRecipe recipe)
	{
		RecipesAether.ALTAR_REGISTRY.addRecipe(recipe);
	}
}
