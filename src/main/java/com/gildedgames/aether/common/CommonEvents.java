package com.gildedgames.aether.common;

import java.util.Random;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.construction.BlockAetherPortal;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.items.armor.ItemZaniteArmor;
import com.gildedgames.util.universe.common.util.TeleporterGeneric;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEvents
{
	@SubscribeEvent
	public void onPlayerUseBucket(FillBucketEvent event)
	{
		if (event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			if (FluidContainerRegistry.isFilledContainer(event.current))
			{
				final EntityPlayer player = event.entityPlayer;

				final BlockPos pos = event.target.getBlockPos().offset(event.target.sideHit);

				if (FluidContainerRegistry.getFluidForFilledItem(event.current).getFluidID() == FluidRegistry.WATER.getID())
				{
					this.onWaterPlaced(event, player, pos);
				}
				else if (FluidContainerRegistry.getFluidForFilledItem(event.current).getFluidID() == FluidRegistry.LAVA.getID())
				{
					this.onLavaPlaced(event, player, pos);
				}
			}
		}
	}

	private boolean tryToCreatePortal(World world, BlockPos pos)
	{
		if (world.getBlockState(pos.down()).getBlock() == Blocks.glowstone)
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
		final Entity entity = event.entity;
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

		if (entity.dimension == AetherCore.getAetherDimID() && entity.posY < -2 && !entity.worldObj.isRemote)
		{
			final double posX = entity.posX;
			final double posZ = entity.posZ;

			final WorldServer server = MinecraftServer.getServer().worldServerForDimension(0);

			final Entity transferMount = this.cutFromDim(entity.ridingEntity, server);
			final Entity transferMountedBy = this.cutFromDim(entity.riddenByEntity, server);

			final ServerConfigurationManager scm = MinecraftServer.getServer().getConfigurationManager();
			final Teleporter teleporter = new TeleporterGeneric(server);

			if (entity instanceof EntityPlayerMP)
			{
				scm.transferPlayerToDimension((EntityPlayerMP) entity, 0, teleporter);
			}
			else
			{
				scm.transferEntityToWorld(entity, 0, MinecraftServer.getServer().worldServerForDimension(AetherCore.getAetherDimID()), server, teleporter);
			}

			entity.setPositionAndUpdate(posX, 256, posZ);

			if (transferMount != null)
			{
				this.teleportToSurface(transferMount, entity);
				entity.mountEntity(transferMount);
			}

			if (transferMountedBy != null)
			{
				transferMountedBy.mountEntity(null);
				this.teleportToSurface(transferMountedBy, entity);
				transferMountedBy.mountEntity(entity);
			}
		}
	}

	private Entity cutFromDim(Entity entity, WorldServer server)
	{
		if (entity == null)
		{
			return null;
		}
		final Entity newEntity = EntityList.createEntityByName(EntityList.getEntityString(entity), server);
		newEntity.copyDataFromOld(entity);
		entity.worldObj.removeEntity(entity);
		return newEntity;
	}

	private void teleportToSurface(Entity mount, Entity riding)
	{
		mount.dimension = riding.dimension;
		mount.timeUntilPortal = mount.getPortalCooldown();
		mount.setPosition(riding.posX, 256, riding.posZ);
		mount.forceSpawn = true;
		riding.worldObj.spawnEntityInWorld(mount);
	}

	@SubscribeEvent
	public void onPlayerInteract(EntityInteractEvent event)
	{
		final ItemStack itemstack = event.entityPlayer.inventory.getCurrentItem();

		if (itemstack != null && itemstack.getItem() == ItemsAether.skyroot_bucket && !event.entityPlayer.capabilities.isCreativeMode)
		{
			if (itemstack.stackSize-- == 1)
			{
				event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(ItemsAether.skyroot_milk_bucket));
			}
			else if (!event.entityPlayer.inventory.addItemStackToInventory(new ItemStack(ItemsAether.skyroot_milk_bucket)))
			{
				event.entityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(ItemsAether.skyroot_milk_bucket, 1, 0), false);
			}
		}
	}

	private void onWaterPlaced(FillBucketEvent event, EntityPlayer player, BlockPos pos)
	{
		if (event.world.getBlockState(event.target.getBlockPos()).getBlock() == Blocks.glowstone)
		{
			if (this.tryToCreatePortal(event.world, pos))
			{
				if (!event.entityPlayer.capabilities.isCreativeMode)
				{
					event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, FluidContainerRegistry.drainFluidContainer(event.current));
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
				final ItemStack newStack = FluidContainerRegistry.drainFluidContainer(event.current);

				player.inventory.setInventorySlotContents(player.inventory.currentItem, newStack);
			}

			if (event.world.isRemote)
			{
				final Random rand = event.world.rand;

				for (int count = 0; count < 8; count++)
				{
					final double parX = pos.getX() + rand.nextDouble();
					final double parY = pos.getY() + rand.nextDouble();
					final double parZ = pos.getZ() + rand.nextDouble();

					event.world.spawnParticle(EnumParticleTypes.CLOUD, parX, parY, parZ, 0, 0, 0);
				}

				event.world.playSound(pos.getX(), pos.getY(), pos.getZ(), "random.fizz", 0.8f, 1.2f + (rand.nextFloat() * 0.2f), false);
			}

			event.setCanceled(true);
		}
	}
}
