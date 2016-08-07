package com.gildedgames.aether.client;

import com.gildedgames.aether.api.capabilites.AetherCapabilities;
import com.gildedgames.aether.api.entities.effects.EntityEffectInstance;
import com.gildedgames.aether.api.entities.effects.EntityEffectProcessor;
import com.gildedgames.aether.api.entities.effects.EntityEffectRule;
import com.gildedgames.aether.api.items.IItemEffectsCapability;
import com.gildedgames.aether.api.items.IItemPropertiesCapability;
import com.gildedgames.aether.api.items.properties.ItemRarity;
import com.gildedgames.aether.client.sound.AetherMusicManager;
import com.gildedgames.aether.common.containers.slots.SlotEquipment;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.items.armor.ItemGravititeArmor;
import com.gildedgames.aether.common.items.armor.ItemObsidianArmor;
import com.gildedgames.aether.common.network.NetworkingAether;
import com.gildedgames.aether.common.network.packets.AetherMovementPacket;
import com.gildedgames.aether.common.player.PlayerAether;
import com.gildedgames.aether.common.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.ibm.icu.util.LocalePriorityList.add;

public class ClientEventHandler
{
	private boolean prevJumpBindState;

	@SubscribeEvent
	public void onTooltipConstruction(ItemTooltipEvent event)
	{
		ItemStack stack = event.getItemStack();

		if (stack != null)
		{
			if (stack.hasCapability(AetherCapabilities.ITEM_PROPERTIES, null))
			{
				IItemPropertiesCapability props = stack.getCapability(AetherCapabilities.ITEM_PROPERTIES, null);

				if (props != null)
				{
					if (props.getRarity() != ItemRarity.NONE)
					{
						event.getToolTip().add(I18n.format(props.getRarity().getUnlocalizedName()));
					}
				}
			}

			if (stack.hasCapability(AetherCapabilities.ITEM_EFFECTS, null))
			{
				IItemEffectsCapability effects = stack.getCapability(AetherCapabilities.ITEM_EFFECTS, null);

				if (effects.getEffectPairs() == null || effects.getEffectPairs().size() <= 0)
				{
					event.getToolTip().add(I18n.format("ability.cosmetic"));
				}
				else
				{
					for (Pair<EntityEffectProcessor, EntityEffectInstance> effect : effects.getEffectPairs())
					{
						EntityEffectProcessor processor = effect.getLeft();
						EntityEffectInstance instance = effect.getRight();

						List<String> localizedDesc = new ArrayList<>();

						for (String line : processor.getUnlocalizedDesc(event.getEntityPlayer(), instance))
						{
							localizedDesc.add(I18n.format(line, (Object[]) processor.getFormatParameters(event.getEntityPlayer(), instance)));
						}

						event.getToolTip().addAll(localizedDesc);

						for (EntityEffectRule rule : instance.getRules())
						{
							for (String line : rule.getUnlocalizedDesc())
							{
								event.getToolTip().add(TextFormatting.GRAY + "\u2022 " + I18n.format(line));
							}
						}
					}
				}
			}

			if (stack.hasCapability(AetherCapabilities.ITEM_PROPERTIES, null))
			{
				IItemPropertiesCapability props = stack.getCapability(AetherCapabilities.ITEM_PROPERTIES, null);

				if (props != null)
				{
					if (props.isEquippable())
					{
						if (props.getRarity() != ItemRarity.NONE)
						{
							event.getToolTip().add("");
						}

						event.getToolTip().add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format(props.getEquipmentType().getUnlocalizedName()));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		if (event.phase != TickEvent.Phase.END)
		{
			return;
		}

		EntityPlayerSP player = FMLClientHandler.instance().getClientPlayerEntity();

		if (player != null)
		{
			PlayerAether aePlayer = PlayerAether.getPlayer(player);

			Minecraft mc = Minecraft.getMinecraft();

			if (PlayerUtil.isWearingEquipment(aePlayer, ItemsAether.obsidian_armor_set))
			{
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
			}
			else if (PlayerUtil.isWearingEquipment(aePlayer, ItemsAether.gravitite_armor_set))
			{
				if (mc.gameSettings.keyBindJump.isKeyDown() && !this.prevJumpBindState)
				{
					if (!aePlayer.getPlayer().isInWater() && aePlayer.getTicksAirborne() > 2 && !aePlayer.getPlayer().capabilities.isCreativeMode)
					{
						if (aePlayer.performDoubleJump())
						{
							NetworkingAether.sendPacketToServer(new AetherMovementPacket(AetherMovementPacket.Action.EXTRA_JUMP));

							player.worldObj.playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERDRAGON_FLAP, SoundCategory.PLAYERS, 0.4f, 0.8f + (player.worldObj.rand.nextFloat() * 0.6f), false);
						}
					}
				}
			}

			this.prevJumpBindState = mc.gameSettings.keyBindJump.isKeyDown();

			AetherMusicManager.INSTANCE.update(aePlayer);
		}
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event)
	{
		if (event.getWorld() instanceof WorldClient)
		{
			Minecraft mc = Minecraft.getMinecraft();

			if (!(mc.playerController instanceof PlayerControllerAetherMP))
			{
				Minecraft.getMinecraft().playerController = PlayerControllerAetherMP.create(mc.playerController);
			}

			ClientProxy.clientPlayerController = (PlayerControllerAetherMP) mc.playerController;
		}
	}

	@SubscribeEvent
	public void onTextureStitchPre(TextureStitchEvent.Pre event)
	{
		SlotEquipment.registerIcons(event);
	}
}
