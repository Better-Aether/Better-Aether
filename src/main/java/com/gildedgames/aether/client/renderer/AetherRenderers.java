package com.gildedgames.aether.client.renderer;

import com.gildedgames.aether.client.renderer.entities.AetherRenderFactory;
import com.gildedgames.aether.client.renderer.entities.RenderFloatingBlock;
import com.gildedgames.aether.client.renderer.entities.RenderMovingBlock;
import com.gildedgames.aether.client.renderer.entities.living.RenderAechorPlant;
import com.gildedgames.aether.client.renderer.entities.living.RenderAerbunny;
import com.gildedgames.aether.client.renderer.entities.living.RenderCarrionSprout;
import com.gildedgames.aether.client.renderer.entities.living.RenderFlyingCow;
import com.gildedgames.aether.client.renderer.entities.living.RenderPhyg;
import com.gildedgames.aether.client.renderer.entities.living.RenderSheepuff;
import com.gildedgames.aether.client.renderer.entities.projectiles.RenderBolt;
import com.gildedgames.aether.client.renderer.entities.projectiles.RenderDaggerfrostSnowball;
import com.gildedgames.aether.client.renderer.entities.projectiles.RenderDart;
import com.gildedgames.aether.client.renderer.tile_entities.TileEntityAltarRenderer;
import com.gildedgames.aether.client.renderer.tile_entities.TileEntityLabyrinthDoorRenderer;
import com.gildedgames.aether.client.renderer.tile_entities.TileEntityLabyrinthTotemRenderer;
import com.gildedgames.aether.client.renderer.tile_entities.TileEntitySkyrootChestRenderer;
import com.gildedgames.aether.client.renderer.tile_entities.TileEntitySkyrootSignRenderer;
import com.gildedgames.aether.common.entities.blocks.EntityFloatingBlock;
import com.gildedgames.aether.common.entities.blocks.EntityMovingBlock;
import com.gildedgames.aether.common.entities.item.EntityPhoenixItem;
import com.gildedgames.aether.common.entities.living.EntityAechorPlant;
import com.gildedgames.aether.common.entities.living.EntityAerbunny;
import com.gildedgames.aether.common.entities.living.EntityCarrionSprout;
import com.gildedgames.aether.common.entities.living.EntitySheepuff;
import com.gildedgames.aether.common.entities.living.mounts.EntityFlyingCow;
import com.gildedgames.aether.common.entities.living.mounts.EntityPhyg;
import com.gildedgames.aether.common.entities.projectiles.EntityBolt;
import com.gildedgames.aether.common.entities.projectiles.EntityDaggerfrostSnowball;
import com.gildedgames.aether.common.entities.projectiles.EntityDart;
import com.gildedgames.aether.common.tile_entities.TileEntityAltar;
import com.gildedgames.aether.common.tile_entities.TileEntityLabyrinthDoor;
import com.gildedgames.aether.common.tile_entities.TileEntityLabyrinthTotem;
import com.gildedgames.aether.common.tile_entities.TileEntitySkyrootChest;
import com.gildedgames.aether.common.tile_entities.TileEntitySkyrootSign;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class AetherRenderers
{
	public static void preInit()
	{
		registerEntityRenderers();
	}

	public static void init()
	{
		registerTESRs();
	}

	private static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityFloatingBlock.class, new AetherRenderFactory<>(RenderFloatingBlock.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityMovingBlock.class, new AetherRenderFactory<>(RenderMovingBlock.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new AetherRenderFactory<>(RenderDart.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class, new AetherRenderFactory<>(RenderBolt.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityDaggerfrostSnowball.class, new AetherRenderFactory<>(RenderDaggerfrostSnowball.class));

		RenderingRegistry.registerEntityRenderingHandler(EntityPhyg.class, new AetherRenderFactory<>(RenderPhyg.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityFlyingCow.class, new AetherRenderFactory<>(RenderFlyingCow.class));
		RenderingRegistry.registerEntityRenderingHandler(EntitySheepuff.class, new AetherRenderFactory<>(RenderSheepuff.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityAechorPlant.class, new AetherRenderFactory<>(RenderAechorPlant.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityAerbunny.class, new AetherRenderFactory<>(RenderAerbunny.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityCarrionSprout.class, new AetherRenderFactory<>(RenderCarrionSprout.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityPhoenixItem.class, new IRenderFactory<EntityPhoenixItem>()
		{
			@Override
			public Render<? super EntityPhoenixItem> createRenderFor(RenderManager manager)
			{
				return new RenderEntityItem(manager, Minecraft.getMinecraft().getRenderItem());
			}
		});
	}

	private static void registerTESRs()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltar.class, new TileEntityAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkyrootChest.class, new TileEntitySkyrootChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkyrootSign.class, new TileEntitySkyrootSignRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLabyrinthTotem.class, new TileEntityLabyrinthTotemRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLabyrinthDoor.class, new TileEntityLabyrinthDoorRenderer());
	}
}
