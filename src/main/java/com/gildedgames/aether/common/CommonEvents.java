package com.gildedgames.aether.common;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.construction.BlockAetherPortal;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.util.PlayerUtil;
import com.gildedgames.util.modules.universe.common.util.TeleporterGeneric;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class CommonEvents
{

	@SubscribeEvent
	public void onPlayerUseBucket(FillBucketEvent event)
	{
		if (event.getTarget().typeOfHit == RayTraceResult.Type.BLOCK)
		{
			if (FluidContainerRegistry.isFilledContainer(event.getEmptyBucket()))
			{
				final EntityPlayer player = event.getEntityPlayer();

				final BlockPos pos = event.getTarget().getBlockPos().offset(event.getTarget().sideHit);

				FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(event.getEmptyBucket());

				if (fluidStack.getFluid().getName().equals(FluidRegistry.WATER.getName()))
				{
					this.onWaterPlaced(event, player, pos);
				}
				else if (fluidStack.getFluid().getName().equals(FluidRegistry.LAVA.getName()))
				{
					this.onLavaPlaced(event, player, pos);
				}
			}
		}
	}

	private boolean tryToCreatePortal(World world, BlockPos pos)
	{
		if (world.getBlockState(pos.down()).getBlock() == Blocks.GLOWSTONE)
		{
			BlockAetherPortal.Size size = new BlockAetherPortal.Size(world, pos, EnumFacing.Axis.X);

			if (size.isWithinSizeBounds() && size.getPortalBlocks() == 0)
			{
				size.createPortal();

				return true;
			}
			else
			{
				size = new BlockAetherPortal.Size(world, pos, EnumFacing.Axis.Z);

				if (size.isWithinSizeBounds() && size.getPortalBlocks() == 0)
				{
					size.createPortal();

					return true;
				}
			}
		}

		return false;
	}

	@SubscribeEvent
	public void onLivingEntityUpdate(LivingEvent.LivingUpdateEvent event)
	{
		final Entity entity = event.getEntity();
		final Block blockBeneath = entity.worldObj.getBlockState(entity.getPosition().down()).getBlock();

		if (blockBeneath == BlocksAether.quicksoil)
		{
			// This doesn't work all the time, because it's only called when the player is
			// directly on top of Quicksoil. If you go slow enough, during the next player update, it
			// will see the player is on top of an air block instead, and this won't be called.

			if (entity.isSneaking())
			{
				entity.onGround = false;

				entity.motionX *= 1.03D;
				entity.motionZ *= 1.03D;
			}
		}

		// Checks whether or not an entity is in the Aether's void
		if (entity.dimension == AetherCore.getAetherDimID() && entity.posY < -4 && !entity.worldObj.isRemote)
		{
			this.onFallenFromAether(entity);
		}
	}

	private void onFallenFromAether(Entity entity)
	{
		// Don't teleport entities which are being ridden.
		if (entity.getRidingEntity() != null)
		{
			return;
		}

		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

		PlayerList playerList = server.getPlayerList();

		WorldServer toWorld = DimensionManager.getWorld(0);

		Teleporter teleporter = new TeleporterGeneric(toWorld);

		// TODO: Recrusive teleporting, new in 1.9/1.10
		Entity mount = entity.getRidingEntity();

		if (mount != null)
		{
			// Dismounts the entity it's riding first as teleporting will cause updates to the entities
			entity.startRiding(null, true);

			// Teleports entity first, then what it's riding
			Entity teleportedEntity = this.teleportEntity(playerList, entity, toWorld, teleporter);

			Entity teleportedMount= this.teleportEntity(playerList, mount, toWorld, teleporter);

			// Re-mount what it was previously riding
			teleportedEntity.startRiding(teleportedMount, true);
		}
		else
		{
			this.teleportEntity(playerList, entity, toWorld, teleporter);
		}
	}

	/**
	 * Teleports any entity by duplicating it and . the old one. If {@param entity} is a player,
	 * the entity will be transferred instead of duplicated.
	 *
	 * @return A new entity if {@param entity} wasn't a player, or the same entity if it was a player
	 */
	private Entity teleportEntity(PlayerList playerList, Entity entity, WorldServer toWorld, Teleporter teleporter)
	{
		if (entity == null)
		{
			return null;
		}

		if (entity instanceof EntityPlayer)
		{
			// Players require special magic to be teleported correctly, and are not duplicated
			playerList.transferPlayerToDimension((EntityPlayerMP) entity, 0, teleporter);
			entity.setPositionAndUpdate(entity.posX, 200 + entity.posY, entity.posZ);

			return entity;
		}
		else
		{
			Entity newEntity = entity.changeDimension(toWorld.provider.getDimension());

			// Forces the entity to be sent to clients as early as possible
			newEntity.forceSpawn = true;
			newEntity.setPositionAndUpdate(entity.posX, 200 + entity.posY, entity.posZ);

			return newEntity;
		}
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent.EntityInteract event)
	{
		if (event.getTarget() instanceof EntityCow && !((EntityCow) event.getTarget()).isChild())
		{
			final ItemStack stack = event.getEntityPlayer().inventory.getCurrentItem();

			if (stack != null && stack.getItem() == ItemsAether.skyroot_bucket)
			{
				PlayerUtil.fillBucketInHand(event.getEntityPlayer(), new ItemStack(ItemsAether.skyroot_milk_bucket));
			}
		}
	}

	private void onWaterPlaced(FillBucketEvent event, EntityPlayer player, BlockPos pos)
	{
		if (event.getWorld().getBlockState(event.getTarget().getBlockPos()).getBlock() == Blocks.GLOWSTONE)
		{
			if (this.tryToCreatePortal(event.getWorld(), pos))
			{
				if (!event.getEntityPlayer().capabilities.isCreativeMode)
				{
					event.getEntityPlayer().inventory.setInventorySlotContents(event.getEntityPlayer().inventory.currentItem, FluidContainerRegistry.drainFluidContainer(event.getEmptyBucket()));
				}

				event.setCanceled(true);
			}
		}
	}

	private void onLavaPlaced(FillBucketEvent event, EntityPlayer player, BlockPos pos)
	{
		if (player.dimension == AetherCore.getAetherDimID())
		{
			player.worldObj.setBlockState(pos, BlocksAether.aerogel.getDefaultState());

			if (!player.capabilities.isCreativeMode)
			{
				final ItemStack newStack = FluidContainerRegistry.drainFluidContainer(event.getEmptyBucket());

				player.inventory.setInventorySlotContents(player.inventory.currentItem, newStack);
			}

			if (event.getWorld().isRemote)
			{
				final Random rand = event.getWorld().rand;

				for (int count = 0; count < 8; count++)
				{
					final double parX = pos.getX() + rand.nextDouble();
					final double parY = pos.getY() + rand.nextDouble();
					final double parZ = pos.getZ() + rand.nextDouble();

					event.getWorld().spawnParticle(EnumParticleTypes.CLOUD, parX, parY, parZ, 0, 0, 0);
				}

				event.getWorld().playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_BURN, SoundCategory.NEUTRAL, 0.8f, 1.2f + (rand.nextFloat() * 0.2f), false);
			}

			event.setCanceled(true);
		}
	}

}
