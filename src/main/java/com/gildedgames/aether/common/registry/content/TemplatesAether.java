package com.gildedgames.aether.common.registry.content;

import com.gildedgames.aether.common.AetherCore;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class TemplatesAether
{
	public static final TemplateManager MANAGER = new TemplateManager("structures");

	public static Template blue_skyroot_tree_1, blue_skyroot_tree_2, blue_skyroot_tree_3;

	public static Template green_skyroot_tree_1, green_skyroot_tree_2, green_skyroot_tree_3;

	public static Template green_skyroot_oak_1, green_skyroot_oak_2;

	public static Template golden_oak_1, golden_oak_2;

	public static Template green_skyroot_windswept_1, green_skyroot_windswept_2, green_skyroot_windswept_3, green_skyroot_windswept_4, green_skyroot_windswept_5, green_skyroot_windswept_6, green_skyroot_windswept_7;

	public static Template dark_blue_skyroot_tree_1, dark_blue_skyroot_tree_2, dark_blue_skyroot_tree_3;

	public static Template dark_blue_skyroot_oak_1, dark_blue_skyroot_oak_2;

	public static Template large_green_skyroot_pine_1, large_green_skyroot_pine_2;

	public static Template green_skyroot_pine_1, green_skyroot_pine_2, green_skyroot_pine_3, green_skyroot_pine_4, green_skyroot_pine_5;

	public static Template labyrinth_ruins_1, labyrinth_ruins_2, labyrinth_ruins_3, labyrinth_ruins_4, labyrinth_ruins_5;

	public static Template skyroot_moa_nest_tree_1, skyroot_moa_nest_1, skyroot_moa_nest_2;

	public static Template labyrinth_entrance_1, labyrinth_entrance_underground_1;

	public static Template aether_portal, nether_portal, end_portal;

	public static Template outpost;

	public static Template kura_tree_1, kura_tree_2, kura_tree_3, kura_tree_4, kura_tree_5;

	public static Template large_kura_tree_1;

	public static Template kura_bush_1, kura_bush_2, kura_bush_3, kura_bush_4;

	private TemplatesAether()
	{

	}

	public static void init()
	{
		blue_skyroot_tree_1 = register("highlands/trees/normal/blue/blue_skyroot_tree_1");
		blue_skyroot_tree_2 = register("highlands/trees/normal/blue/blue_skyroot_tree_2");
		blue_skyroot_tree_3 = register("highlands/trees/normal/blue/blue_skyroot_tree_3");

		green_skyroot_tree_1 = register("highlands/trees/normal/green/green_skyroot_tree_1");
		green_skyroot_tree_2 = register("highlands/trees/normal/green/green_skyroot_tree_2");
		green_skyroot_tree_3 = register("highlands/trees/normal/green/green_skyroot_tree_3");

		green_skyroot_oak_1 = register("highlands/trees/oak/green/green_skyroot_oak_1");
		green_skyroot_oak_2 = register("highlands/trees/oak/green/green_skyroot_oak_2");

		golden_oak_1 = register("highlands/trees/oak/golden/golden_oak_1");
		golden_oak_2 = register("highlands/trees/oak/golden/golden_oak_2");

		green_skyroot_windswept_1 = register("highlands/trees/windswept/green/green_skyroot_windswept_1");
		green_skyroot_windswept_2 = register("highlands/trees/windswept/green/green_skyroot_windswept_2");
		green_skyroot_windswept_3 = register("highlands/trees/windswept/green/green_skyroot_windswept_3");
		green_skyroot_windswept_4 = register("highlands/trees/windswept/green/green_skyroot_windswept_4");
		green_skyroot_windswept_5 = register("highlands/trees/windswept/green/green_skyroot_windswept_5");
		green_skyroot_windswept_6 = register("highlands/trees/windswept/green/green_skyroot_windswept_6");
		green_skyroot_windswept_7 = register("highlands/trees/windswept/green/green_skyroot_windswept_7");

		labyrinth_ruins_1 = register("labyrinth_ruins_1");
		labyrinth_ruins_2 = register("labyrinth_ruins_2");
		labyrinth_ruins_3 = register("labyrinth_ruins_3");
		labyrinth_ruins_4 = register("labyrinth_ruins_4");
		labyrinth_ruins_5 = register("labyrinth_ruins_5");

		skyroot_moa_nest_tree_1 = register("moa_nest/skyroot_moa_nest_tree_1");
		skyroot_moa_nest_1 = register("moa_nest/skyroot_moa_nest_1");
		skyroot_moa_nest_2 = register("moa_nest/skyroot_moa_nest_2");

		large_green_skyroot_pine_1 = register("highlands/trees/pine/green/large_green_skyroot_pine_1");
		large_green_skyroot_pine_2 = register("highlands/trees/pine/green/large_green_skyroot_pine_2");

		green_skyroot_pine_1 = register("highlands/trees/pine/green/green_skyroot_pine_1");
		green_skyroot_pine_2 = register("highlands/trees/pine/green/green_skyroot_pine_2");
		green_skyroot_pine_3 = register("highlands/trees/pine/green/green_skyroot_pine_3");
		green_skyroot_pine_4 = register("highlands/trees/pine/green/green_skyroot_pine_4");
		green_skyroot_pine_5 = register("highlands/trees/pine/green/green_skyroot_pine_5");

		dark_blue_skyroot_tree_1 = register("highlands/trees/normal/dark_blue/dark_blue_skyroot_tree_1");
		dark_blue_skyroot_tree_2 = register("highlands/trees/normal/dark_blue/dark_blue_skyroot_tree_2");
		dark_blue_skyroot_tree_3 = register("highlands/trees/normal/dark_blue/dark_blue_skyroot_tree_3");

		dark_blue_skyroot_oak_1 = register("highlands/trees/oak/dark_blue/dark_blue_skyroot_oak_1");
		dark_blue_skyroot_oak_2 = register("highlands/trees/oak/dark_blue/dark_blue_skyroot_oak_2");

		labyrinth_entrance_1 = register("labyrinth_entrance_1");
		labyrinth_entrance_underground_1 = register("labyrinth_entrance_underground_1");

		aether_portal = register("aether_portal");
		nether_portal = register("nether_portal");
		end_portal = register("end_portal");

		outpost = register("outpost");

		kura_tree_1 = register("kura/trees/small/kura_tree_1");
		kura_tree_2 = register("kura/trees/small/kura_tree_2");
		kura_tree_3 = register("kura/trees/small/kura_tree_3");
		kura_tree_4 = register("kura/trees/small/kura_tree_4");
		kura_tree_5 = register("kura/trees/small/kura_tree_5");

		large_kura_tree_1 = register("kura/trees/large/large_kura_tree_1");

		kura_bush_1 = register("kura/trees/small/kura_bush_1");
		kura_bush_2 = register("kura/trees/small/kura_bush_2");
		kura_bush_3 = register("kura/trees/small/kura_bush_3");
		kura_bush_4 = register("kura/trees/small/kura_bush_4");
	}

	private static Template register(String resource)
	{
		return register(AetherCore.getResource(resource));
	}

	private static Template register(ResourceLocation resourceLocation)
	{
		return TemplatesAether.MANAGER.getTemplate(null, resourceLocation);
	}

}
