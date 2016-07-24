package com.gildedgames.aether.common.player;

import com.gildedgames.aether.api.items.properties.ItemEquipmentType;
import com.gildedgames.aether.common.entities.blocks.EntityMovingBlock;
import com.gildedgames.aether.common.entities.companions.EntityCompanion;
import com.gildedgames.aether.common.items.ItemCompanion;
import com.gildedgames.aether.common.items.tools.ItemGravititeTool;
import com.gildedgames.aether.common.network.NetworkingAether;
import com.gildedgames.aether.common.network.packets.EquipmentChangedPacket;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

import com.gildedgames.aether.api.capabilites.AetherCapabilities;
import com.gildedgames.aether.api.player.IPlayerAetherCapability;
import com.gildedgames.aether.api.player.inventory.IInventoryEquipment;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.containers.inventory.InventoryEquipment;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.items.armor.ItemAetherArmor;
import com.gildedgames.aether.common.items.armor.ItemGravititeArmor;
import com.gildedgames.aether.common.items.armor.ItemNeptuneArmor;
import com.gildedgames.aether.common.items.tools.ItemValkyrieTool;
import com.gildedgames.aether.common.util.PlayerUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class PlayerAether implements IPlayerAetherCapability
{
	private final EntityPlayer player;

	private InventoryEquipment equipmentInventory;

	private EntityMovingBlock heldBlock;

	private BlockPos linkingSchematicBoundary;

	private EntityCompanion companion;

	private boolean hasDoubleJumped;

	private int ticksAirborne;

	public PlayerAether(EntityPlayer player)
	{
		this.player = player;
		this.equipmentInventory = new InventoryEquipment(this);
	}

	public static IPlayerAetherCapability getPlayer(Entity player)
	{
		return player.getCapability(AetherCapabilities.PLAYER_DATA, null);
	}
	
	@Override
	public void setLinkingSchematicBoundary(BlockPos pos)
	{
		this.linkingSchematicBoundary = pos;
	}
	
	@Override
	public BlockPos getLinkingSchematicBoundary()
	{
		return this.linkingSchematicBoundary;
	}

	@Override
	public void onUpdate(LivingUpdateEvent event)
	{
		float extendedReach = 0.0f;

		if (this.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) != null)
		{
			Item item = this.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem();

			if (item instanceof ItemValkyrieTool || item == ItemsAether.valkyrie_lance)
			{
				extendedReach = 3.5f;
			}
		}

		if (this.getPlayer().onGround)
		{
			this.hasDoubleJumped = false;
			this.ticksAirborne = 0;
		}
		else
		{
			this.ticksAirborne++;
		}

		if (this.heldBlock != null)
		{
			if (this.heldBlock.isDead || this.heldBlock.isFalling())
			{
				this.heldBlock = null;
			}
			else
			{
				ItemStack stack = this.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);

				// Check if the player is still using a gravitite tool
				if (!(stack != null && stack.getItem() instanceof ItemGravititeTool))
				{
					this.heldBlock.setHoldingPlayer(null);
				}
				else if (this.heldBlock.ticksExisted % 20 == 0)
				{
					// Does damage 2 damage/sec, increasing the amount of damage by 1 every 3 seconds,
					// for a maximum of 8 damage/sec

					int extra = (int) Math.floor(Math.min(6, this.heldBlock.ticksExisted / 60));

					stack.damageItem(2 + extra, this.player);
				}
			}
		}

		if (!this.player.worldObj.isRemote)
		{
			ItemStack companionStack = this.equipmentInventory.getStackInSlot(6);

			if (companionStack != null && companionStack.getItem() instanceof ItemCompanion)
			{
				if (this.companion == null)
				{
					long respawnTimer = ItemCompanion.getTicksUntilRespawn(companionStack, this.player.worldObj);

					if (respawnTimer <= 0)
					{
						this.companion = ((ItemCompanion) companionStack.getItem()).spawnAndLink(this);

						this.companion.setPosition(this.getPlayer().posX, this.getPlayer().posY, this.getPlayer().posZ);

						this.player.worldObj.spawnEntityInWorld(this.companion);
					}
				}
				else if (this.companion.isDead)
				{
					this.companion = null;

					ItemCompanion.setRespawnTimer(companionStack, this.player.worldObj, 20 * 120);
				}
				else
				{
					this.companion.tickEffects(this);
				}
			}
			else
			{
				if (this.companion != null)
				{
					this.companion.setDead();

					this.companion = null;
				}
			}
		}

		if (!this.player.worldObj.isRemote && this.equipmentInventory.getDirties().size() > 0)
		{
			List<Pair<Integer, ItemStack>> changes = new ArrayList<>();

			for (int i : this.equipmentInventory.getDirties())
			{
				ItemStack stack = this.equipmentInventory.getStackInSlot(i);

				changes.add(Pair.of(i, stack));
			}

			NetworkingAether.sendPacketToWatching(new EquipmentChangedPacket(changes), this.player);

			this.equipmentInventory.getDirties().clear();
		}

		AetherCore.PROXY.setExtendedReachDistance(this.player, extendedReach);
	}

	@Override
	public void onDeath(LivingDeathEvent event)
	{
		this.dropHeldBlock();
	}

	@Override
	public void onDrops(LivingDropsEvent event)
	{
		if (!this.player.getEntityWorld().getGameRules().getBoolean("keepInventory"))
		{
			this.player.captureDrops = true;

			this.equipmentInventory.dropAllItems();

			this.player.captureDrops = false;
		}
	}

	@Override
	public void onHurt(LivingHurtEvent event)
	{
		if (!event.getSource().isUnblockable())
		{
			for (ItemStack stack : this.player.inventory.armorInventory)
			{
				if (stack != null && stack.getItem() instanceof ItemAetherArmor)
				{
					event.setAmount(event.getAmount() - ((ItemAetherArmor) stack.getItem()).getExtraDamageReduction(stack));
				}
			}
		}
	}

	@Override
	public void onFall(LivingFallEvent event)
	{
		Class<? extends Item> fullSet = PlayerUtil.findArmorSet(this.player);

		if (fullSet == ItemGravititeArmor.class)
		{
			event.setResult(Result.DENY);
		}
	}

	@Override
	public void onJump(LivingJumpEvent event)
	{

	}

	@Override
	public IInventoryEquipment getEquipmentInventory()
	{
		return this.equipmentInventory;
	}

	@Override
	public float getMiningSpeedMultiplier()
	{
		if (PlayerUtil.isWearingFullSet(this.player, ItemNeptuneArmor.class))
		{
			if (!EnchantmentHelper.getAquaAffinityModifier(this.player) && this.player.isInsideOfMaterial(Material.WATER))
			{
				return 5.0f;
			}
		}

		return 1.0f;
	}

	@Override
	public EntityPlayer getPlayer()
	{
		return this.player;
	}

	public boolean performDoubleJump()
	{
		if (!this.hasDoubleJumped && this.ticksAirborne > 2)
		{
			AetherCore.PROXY.spawnJumpParticles(this.getPlayer().worldObj, this.getPlayer().posX, this.getPlayer().posY, this.getPlayer().posZ, 1.5D, 12);

			this.getPlayer().motionY = 0.55D;
			this.getPlayer().fallDistance = -4;

			this.getPlayer().motionX *= 1.4D;
			this.getPlayer().motionZ *= 1.4D;

			this.hasDoubleJumped = true;

			return true;
		}

		return false;
	}

	@Override
	public int getTicksAirborne()
	{
		return this.ticksAirborne;
	}

	public boolean pickupBlock(BlockPos pos, World world)
	{
		if (this.heldBlock == null)
		{
			if (world.isBlockModifiable(this.player, pos))
			{
				IBlockState state = world.getBlockState(pos);

				EntityMovingBlock movingBlock = new EntityMovingBlock(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, state);
				world.spawnEntityInWorld(movingBlock);

				this.holdBlock(movingBlock);

				return true;
			}
		}

		return false;
	}

	private void holdBlock(EntityMovingBlock entity)
	{
		this.dropHeldBlock();

		this.heldBlock = entity;
		this.heldBlock.setHoldingPlayer(this.player);
	}

	public void dropHeldBlock()
	{
		if (this.getHeldBlock() != null)
		{
			this.getHeldBlock().setHoldingPlayer(null);
		}
	}

	public EntityMovingBlock getHeldBlock()
	{
		return this.heldBlock;
	}

	public static class Storage implements IStorage<IPlayerAetherCapability>
	{
		@Override
		public NBTBase writeNBT(Capability<IPlayerAetherCapability> capability, IPlayerAetherCapability instance, EnumFacing side)
		{
			NBTTagCompound compound = new NBTTagCompound();

			NBTTagCompound equipment = new NBTTagCompound();
			instance.getEquipmentInventory().write(equipment);

			compound.setTag("equipment", equipment);

			return compound;
		}

		@Override
		public void readNBT(Capability<IPlayerAetherCapability> capability, IPlayerAetherCapability instance, EnumFacing side, NBTBase nbt)
		{
			NBTTagCompound compound = (NBTTagCompound) nbt;

			if (compound.hasKey("equipment"))
			{
				instance.getEquipmentInventory().read(compound.getCompoundTag("equipment"));
			}
		}
	}
}
