package com.gildedgames.aether.client;

import com.gildedgames.aether.api.AetherAPI;
import com.gildedgames.aether.client.gui.menu.BossBattleOverlay;
import com.gildedgames.aether.client.gui.menu.PortalOverlay;
import com.gildedgames.aether.client.gui.menu.WorldAetherOptionsOverlay;
import com.gildedgames.aether.client.gui.tab.TabBugReport;
import com.gildedgames.aether.client.gui.tab.TabClientEvents;
import com.gildedgames.aether.client.gui.tab.TabEquipment;
import com.gildedgames.aether.client.models.blocks.AetherBlockModels;
import com.gildedgames.aether.client.models.items.ItemModelsAether;
import com.gildedgames.aether.client.renderer.AetherRenderers;
import com.gildedgames.aether.client.renderer.ClientRenderHandler;
import com.gildedgames.aether.client.renderer.items.AetherSpawnEggColorHandler;
import com.gildedgames.aether.client.renderer.items.ItemMoaEggColorHandler;
import com.gildedgames.aether.client.renderer.items.LeatherGlovesColorHandler;
import com.gildedgames.aether.client.renderer.items.WrappingPaperColorHandler;
import com.gildedgames.aether.client.sound.AetherMusicManager;
import com.gildedgames.aether.client.ui.UiManager;
import com.gildedgames.aether.client.ui.minecraft.viewing.MinecraftGuiViewer;
import com.gildedgames.aether.common.CommonProxy;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.registry.minecraft.CreativeTabsAether;
import com.gildedgames.aether.common.util.structure.StructureInjectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{

	public static PlayerControllerAetherMP clientPlayerController;

	@Override
	public EntityPlayer getPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);

		AetherBlockModels.preInit();
		ItemModelsAether.preInit();
		AetherRenderers.preInit();

		CreativeTabsAether.registerTabIcons();

		UiManager.inst().registerOverlay("worldAetherOptionsOverlay", WorldAetherOptionsOverlay::new, MinecraftGuiViewer.instance());

		UiManager.inst().registerOverlay("aetherPortalOverlay", PortalOverlay::new, MinecraftGuiViewer.instance(), RenderGameOverlayEvent.ElementType.PORTAL);

		UiManager.inst().registerOverlay("bossBattleOverlay", BossBattleOverlay::new, MinecraftGuiViewer.instance(), RenderGameOverlayEvent.ElementType.HOTBAR);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);

		AetherRenderers.init();

		MinecraftForge.EVENT_BUS.register(AetherMusicManager.INSTANCE);

		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		MinecraftForge.EVENT_BUS.register(new ClientRenderHandler());
		MinecraftForge.EVENT_BUS.register(StructureInjectionEvents.class);

		MinecraftForge.EVENT_BUS.register(MinecraftGuiViewer.instance().getTickInfo());
		MinecraftForge.EVENT_BUS.register(TabClientEvents.class);

		AetherAPI.tabs().getInventoryGroup().registerClientTab(new TabEquipment.Client());
		AetherAPI.tabs().getInventoryGroup().registerClientTab(new TabBugReport.Client());

		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemMoaEggColorHandler(), ItemsAether.moa_egg);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new LeatherGlovesColorHandler(), ItemsAether.leather_gloves);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new WrappingPaperColorHandler(), ItemsAether.wrapping_paper);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new AetherSpawnEggColorHandler(), ItemsAether.aether_spawn_egg);
	}

	@Override
	public boolean tryEquipEquipment(EntityPlayer player, ItemStack stack, EnumHand hand)
	{
		boolean result = super.tryEquipEquipment(player, stack, hand);

		if (result)
		{
			// Unfortunately we have to play the equip animation manually...
			if (player.worldObj.isRemote)
			{
				Minecraft.getMinecraft().getItemRenderer().resetEquippedProgress(EnumHand.MAIN_HAND);
			}

			player.worldObj.playSound(player, player.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.NEUTRAL, 1.0f, 1.0f);
		}

		return result;
	}

	@Override
	public void setExtendedReachDistance(EntityPlayer entity, float distance)
	{
		if (entity.worldObj instanceof WorldClient)
		{
			ClientProxy.clientPlayerController.setExtendedBlockReachDistance(distance);

			return;
		}

		super.setExtendedReachDistance(entity, distance);
	}

	@Override
	public void displayDismountMessage(EntityPlayer player)
	{
		if (player == Minecraft.getMinecraft().thePlayer)
		{
			Minecraft.getMinecraft().ingameGUI.setRecordPlaying(I18n.format("mount.onboard", Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName()), false);
		}
	}

}
