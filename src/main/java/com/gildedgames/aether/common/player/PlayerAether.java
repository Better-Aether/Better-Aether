package com.gildedgames.aether.common.player;

import com.gildedgames.aether.api.capabilites.AetherCapabilities;
import com.gildedgames.aether.api.player.IPlayerAetherCapability;
import com.gildedgames.aether.api.player.inventory.IInventoryEquipment;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.containers.inventory.InventoryEquipment;
import com.gildedgames.aether.common.entities.blocks.EntityMovingBlock;
import com.gildedgames.aether.common.entities.companions.EntityCompanion;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.items.armor.ItemAetherArmor;
import com.gildedgames.aether.common.items.armor.ItemGravititeArmor;
import com.gildedgames.aether.common.items.armor.ItemNeptuneArmor;
import com.gildedgames.aether.common.items.companions.ItemCompanion;
import com.gildedgames.aether.common.items.companions.ItemDeathSeal;
import com.gildedgames.aether.common.items.tools.ItemGravititeTool;
import com.gildedgames.aether.common.items.tools.ItemValkyrieTool;
import com.gildedgames.aether.common.network.NetworkingAether;
import com.gildedgames.aether.common.network.packets.CompanionChangedPacket;
import com.gildedgames.aether.common.network.packets.EquipmentChangedPacket;
import com.gildedgames.aether.common.util.PlayerUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class PlayerAether implements IPlayerAetherCapability
{
	private final EntityPlayer player;

	private InventoryEquipment equipmentInventory;

	private EntityMovingBlock heldBlock;

	private BlockPos linkingSchematicBoundary;

	private int companionId = -1;

	private boolean hasDoubleJumped;

	private int ticksAirborne;

	public PlayerAether(EntityPlayer player)
	{
		this.player = player;
		this.equipmentInventory = new InventoryEquipment(this);
	}

	public static PlayerAether getPlayer(Entity player)
	{
		return (PlayerAether) player.getCapability(AetherCapabilities.PLAYER_DATA, null);
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
		this.updateAbilities();

		if (!this.player.worldObj.isRemote)
		{
			this.sendEquipmentChanges();

			if (!this.player.isDead)
			{
				this.updatePickedBlock();
				this.updateCompanion();
			}
		}

		AetherCore.PROXY.setExtendedReachDistance(this.player, this.calculateExtendedReach());
	}

	private float calculateExtendedReach()
	{
		if (this.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) != null)
		{
			Item item = this.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem();

			if (item instanceof ItemValkyrieTool || item == ItemsAether.valkyrie_lance)
			{
				return 3.5f;
			}
		}

		return 0.0f;
	}

	private void updateAbilities()
	{
		if (this.getPlayer().onGround)
		{
			this.hasDoubleJumped = false;
			this.ticksAirborne = 0;
		}
		else
		{
			this.ticksAirborne++;
		}
	}

	private void updateCompanion()
	{
		ItemStack companionStack = this.getCompanionItem();

		EntityCompanion companion = this.getCompanionEntity();

		if (companionStack != null)
		{
			if (companion != null)
			{
				if (companion.isDead)
				{
					if (!companion.wasDespawned())
					{
						ItemCompanion.setRespawnTimer(companionStack, this.player.worldObj, 20 * 240);
					}

					this.removeCompanion(true);
				}
				else if (((ItemCompanion) companionStack.getItem()).getCompanionClass() != companion.getClass())
				{
					this.removeCompanion(true);
				}
				else
				{
					companion.tickEffects(this);
				}
			}

			if (companion == null)
			{
				long respawnTimer = ItemCompanion.getTicksUntilRespawn(companionStack, this.player.worldObj);

				if (respawnTimer <= 0)
				{
					this.spawnCompanion((ItemCompanion) companionStack.getItem());
				}
			}
		}
		else if (companion != null)
		{
			this.removeCompanion(true);
		}
	}

	private void updatePickedBlock()
	{
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
	}

	private void spawnCompanion(ItemCompanion item)
	{
		EntityCompanion companion = item.createCompanionEntity(this);

		companion.setOwner(this.getPlayer());
		companion.setPosition(this.getPlayer().posX, this.getPlayer().posY, this.getPlayer().posZ);

		this.player.worldObj.spawnEntityInWorld(companion);
		this.companionId = companion.getEntityId();

		NetworkingAether.sendPacketToPlayer(new CompanionChangedPacket(this.companionId), (EntityPlayerMP) this.player);

		companion.addEffects(this);
	}

	private void removeCompanion(boolean notifyClient)
	{
		EntityCompanion companion = this.getCompanionEntity();

		if (companion != null)
		{
			companion.removeEffects(this);

			companion.setOwner(null);
			companion.setDead();

			this.companionId = -1;

			if (notifyClient)
			{
				NetworkingAether.sendPacketToWatching(new CompanionChangedPacket(this.companionId), this.getPlayer());
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void handleCompanionChange(int id)
	{
		this.companionId = id;
	}

	private void sendEquipmentChanges()
	{
		if (this.equipmentInventory.getDirties().size() > 0)
		{
			List<Pair<Integer, ItemStack>> changes = new ArrayList<>();

			for (int i : this.equipmentInventory.getDirties())
			{
				ItemStack stack = this.equipmentInventory.getStackInSlot(i);

				changes.add(Pair.of(i, stack));
			}

			NetworkingAether.sendPacketToWatching(new EquipmentChangedPacket(this.player, changes), this.player);

			this.equipmentInventory.getDirties().clear();
		}
	}

	@Override
	public void onDeath(LivingDeathEvent event)
	{
		ItemStack companionItem = this.getCompanionItem();

		if (companionItem != null && companionItem.getItem() instanceof ItemDeathSeal)
		{
			long ticksUntilUsable = ItemDeathSeal.getTicksUntilEnabled(companionItem, this.getPlayer().worldObj);

			if (ticksUntilUsable <= 0)
			{
				event.setCanceled(true);

				this.player.setHealth(0.5f);

				this.player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 20 * 7, 4));
				this.player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("regeneration"), 20 * 7, 3));

				ItemDeathSeal.setDisabledTimer(companionItem, this.player.worldObj, 20 * 60 * 15);

				FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendChatMsg(new TextComponentTranslation("chat.aether.resurrected", this.player.getDisplayName()));

				return;
			}
		}

		this.removeCompanion(true);

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
	public void onTeleport(PlayerEvent.PlayerChangedDimensionEvent event)
	{
		this.removeCompanion(true);

		this.updateCompanion();
	}

	@Override
	public void onSpawned(PlayerEvent.PlayerLoggedInEvent event)
	{
		this.updateCompanion();
	}

	@Override
	public void onDespawn(PlayerEvent.PlayerLoggedOutEvent event)
	{
		this.removeCompanion(false);
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

	public EntityCompanion getCompanionEntity()
	{
		Entity entity = this.player.worldObj.getEntityByID(this.companionId);

		if (entity != null && entity instanceof EntityCompanion)
		{
			return (EntityCompanion) entity;
		}

		return null;
	}

	public ItemStack getCompanionItem()
	{
		ItemStack companionStack = this.equipmentInventory.getStackInSlot(6);

		if (companionStack != null && companionStack.getItem() instanceof ItemCompanion)
		{
			return companionStack;
		}

		return null;
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
