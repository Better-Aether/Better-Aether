package com.gildedgames.aether.common.player;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.items.armor.ItemAetherArmor;
import com.gildedgames.aether.common.items.armor.ItemGravititeArmor;
import com.gildedgames.aether.common.util.PlayerUtil;
import com.gildedgames.aether.common.world.chunk.PlacementFlagChunkData;
import com.gildedgames.util.chunk.ChunkCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerAetherEventHandler
{
	@SubscribeEvent
	public void onLivingEntityHurt(LivingHurtEvent event)
	{
		PlayerAether aePlayer = this.getAetherPlayer(event);

		if (aePlayer != null)
		{
			aePlayer.onHurt(event);
		}
	}

	@SubscribeEvent
	public void onPlayerFall(LivingFallEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if (PlayerUtil.wearingArmor(player, 0, ItemsAether.sentry_boots) || PlayerUtil.isWearingFullSet(player, ItemGravititeArmor.class))
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onLivingEntityJumped(LivingJumpEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			Class<? extends Item> fullSet = PlayerUtil.findArmorSet(player);

			if (fullSet == ItemGravititeArmor.class)
			{
				if (player.isSneaking())
				{
					player.motionY += 0.55F;

					AetherCore.PROXY.spawnJumpParticles(player.worldObj, player.posX, player.posY, player.posZ, 0.4D, 12);
				}
			}
		}
	}

	@SubscribeEvent
	public void onCalculateBreakSpeed(BreakSpeed event)
	{
		PlayerAether aePlayer = this.getAetherPlayer(event);

		if (aePlayer != null)
		{
			aePlayer.onCalculateBreakSpeed(event);
		}
	}

	@SubscribeEvent
	public void onPlaceBlockEvent(BlockEvent.PlaceEvent event)
	{
		PlacementFlagChunkData data = (PlacementFlagChunkData) ChunkCore.locate().getData(event.world, event.pos, PlacementFlagChunkData.class);

		if (data != null)
		{
			data.setPlaced(event.pos.getX() & 15, event.pos.getY() & 15, event.pos.getZ() & 15);
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		PlayerAether aePlayer = this.getAetherPlayer(event);

		if (aePlayer != null)
		{
			aePlayer.onUpdate();
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		PlayerAether aePlayer = this.getAetherPlayer(event);

		if (aePlayer != null)
		{
			aePlayer.onDeath();
		}
	}


	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event)
	{
		PlayerAether aePlayer = this.getAetherPlayer(event);

		if (aePlayer != null)
		{
			aePlayer.dropAccessories();
		}
	}

	private PlayerAether getAetherPlayer(LivingEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			return PlayerAether.get((EntityPlayer) event.entityLiving);
		}

		return null;
	}

	@SubscribeEvent
	public void onConstructEntity(EntityEvent.EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if (event.entity.getExtendedProperties(AetherCore.MOD_ID) == null)
			{
				event.entity.registerExtendedProperties(AetherCore.MOD_ID, new PlayerAether());
			}
		}
	}

	@SubscribeEvent
	public void onPlayerCloneEvent(PlayerEvent.Clone event)
	{
		if (event.entityPlayer.getExtendedProperties(AetherCore.MOD_ID) == null)
		{
			IExtendedEntityProperties oldProps = event.original.getExtendedProperties(AetherCore.MOD_ID);

			if (oldProps != null)
			{
				oldProps = new PlayerAether();
			}

			event.entityPlayer.registerExtendedProperties(AetherCore.MOD_ID, oldProps);
		}
	}
}
