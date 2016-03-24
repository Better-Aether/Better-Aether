package com.gildedgames.aether.common.containers;

import com.gildedgames.aether.common.AetherCapabilities;
import com.gildedgames.aether.common.containers.inventory.InventoryEquipment;
import com.gildedgames.aether.common.containers.slots.SlotEquipment;
import com.gildedgames.aether.common.entities.effects.EffectInstance;
import com.gildedgames.aether.common.entities.effects.EffectProcessor;
import com.gildedgames.aether.common.entities.effects.EntityEffects;
import com.gildedgames.aether.common.items.ItemEffectsBase;
import com.gildedgames.aether.common.items.ItemEquipmentType;
import com.gildedgames.aether.common.items.ItemPropertiesBase;
import com.gildedgames.aether.common.player.PlayerAether;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

public class ContainerEquipment extends ContainerPlayer
{
	/** See {@link GuiContainerCreative#field_147060_v} **/
	private static InventoryBasic dumbInventory = new InventoryBasic("fake", true, 46);

	private final PlayerAether aePlayer;

	private final InventoryEquipment inventoryEquipment;

	private Slot binSlot;

	public ContainerEquipment(PlayerAether aePlayer)
	{
		super(aePlayer.getEntity().inventory, false, aePlayer.getEntity());

		this.aePlayer = aePlayer;
		this.inventoryEquipment = aePlayer.getEquipment();

		this.createSlots();
	}

	private void createSlots()
	{
		int widthOffset = 180;
		int heightOffset = -10;

		for (Slot slot : this.inventorySlots)
		{
			slot.xDisplayPosition += widthOffset;
			slot.yDisplayPosition += heightOffset;
		}

		Slot helmet = this.inventorySlots.get(5);
		Slot chestplate = this.inventorySlots.get(6);
		Slot leggings = this.inventorySlots.get(7);
		Slot boots = this.inventorySlots.get(8);

		helmet.xDisplayPosition = 43;
		helmet.yDisplayPosition = 33;

		chestplate.xDisplayPosition = 43;
		chestplate.yDisplayPosition = 54;

		leggings.xDisplayPosition = 43;
		leggings.yDisplayPosition = 75;

		boots.xDisplayPosition = 43;
		boots.yDisplayPosition = 96;

		Slot craftResult = this.inventorySlots.get(0);

		Slot craft1 = this.inventorySlots.get(1);
		Slot craft2 = this.inventorySlots.get(2);
		Slot craft3 = this.inventorySlots.get(3);
		Slot craft4 = this.inventorySlots.get(4);

		this.binSlot = new Slot(ContainerEquipment.dumbInventory, this.inventorySlots.size(), 213, 26);

		if (this.aePlayer.getEntity().capabilities.isCreativeMode)
		{
			this.addSlotToContainer(this.binSlot);

			craftResult.xDisplayPosition -= 17;

			craft1.xDisplayPosition -= 17;
			craft2.xDisplayPosition -= 17;
			craft3.xDisplayPosition -= 17;
			craft4.xDisplayPosition -= 17;
		}
		else
		{
			craftResult.xDisplayPosition -= 36;

			craft1.xDisplayPosition -= 36;
			craft2.xDisplayPosition -= 36;
			craft3.xDisplayPosition -= 36;
			craft4.xDisplayPosition -= 36;
		}

		int inventorySlotId = 0;

		this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.RELIC, inventorySlotId++, 18, 33));
		this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.RELIC, inventorySlotId++, 68, 33));

		this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.HANDWEAR, inventorySlotId++, 68, 54));

		//this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.SHIELD, inventorySlotId++, 62, 49));

		this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.RING, inventorySlotId++, 18, 75));
		this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.RING, inventorySlotId++, 18, 96));
		this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.NECKWEAR, inventorySlotId++, 18, 54));

		this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.AMMUNITION, inventorySlotId++, 68, 96));

		this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.COMPANION, inventorySlotId++, 68, 75));
		//this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.ARTIFACT, inventorySlotId++, 55, 101));
		
		/*for (int x = 0; x < 6; x++)
		{
			int x1 = 35 + (x * 18);
			int y1 = 132;

			this.addSlotToContainer(new SlotEquipment(this.inventoryEquipment, ItemEquipmentType.CHARM, inventorySlotId, x1, y1));
			inventorySlotId++;
		}*/
	}

	@Override
	public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer player)
	{
		if (slotId == this.binSlot.slotNumber && player.capabilities.isCreativeMode)
		{
			this.aePlayer.getEntity().inventory.setItemStack(null);
		}

		if (slotId < this.inventorySlots.size() && slotId > 0)
		{
			Slot slot = this.inventorySlots.get(slotId);

			if (slot != null && slot.getHasStack())
			{
				ItemStack stack = slot.getStack();

				if (slot instanceof SlotEquipment && stack.hasCapability(AetherCapabilities.ITEM_EFFECTS, null))
				{
					EntityEffects effects = EntityEffects.get(this.aePlayer.getEntity());
					ItemEffectsBase itemEffects = stack.getCapability(AetherCapabilities.ITEM_EFFECTS, null);

					if (itemEffects != null)
					{
						for (Pair<EffectProcessor, EffectInstance> effect : itemEffects.getEffectPairs())
						{
							EffectProcessor processor = effect.getLeft();
							EffectInstance instance = effect.getRight();

							effects.removeInstance(processor, instance);
						}
					}
				}
			}
		}

		return super.slotClick(slotId, clickedButton, mode, player);
	}

	private int getNextEmptySlot(ItemEquipmentType type)
	{
		for (int i = 0; i < this.inventorySlots.size(); i++)
		{
			Slot slot = this.inventorySlots.get(i);

			if (slot.getStack() == null && slot instanceof SlotEquipment)
			{
				if (((SlotEquipment) slot).getEquipmentType() == type)
				{
					return i;
				}
			}
		}

		return -1;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber)
	{
		Slot slot = this.inventorySlots.get(slotNumber);

		if (slotNumber == this.binSlot.slotNumber && this.aePlayer.getEntity().capabilities.isCreativeMode)
		{
			this.aePlayer.getEntity().inventory.clear();
			this.aePlayer.getEquipment().clear();
		}

		if (slot != null && slot.getHasStack())
		{
			ItemStack stack = slot.getStack();

			if (!(slot instanceof SlotEquipment) && !(slot instanceof SlotCrafting))
			{
				int destIndex = -1;

				if (stack.getItem() instanceof ItemPropertiesBase)
				{
					ItemPropertiesBase accessory = (ItemPropertiesBase) stack.getItem();

					destIndex = this.getNextEmptySlot(accessory.getEquipmentType());
				}

				if (destIndex != -1)
				{
					Slot accessorySlot = this.inventorySlots.get(destIndex);
					accessorySlot.putStack(stack);

					slot.putStack(null);

					return stack;
				}
			}
			else if (slot instanceof SlotEquipment && stack.hasCapability(AetherCapabilities.ITEM_EFFECTS, null))
			{
				EntityEffects effects = EntityEffects.get(this.aePlayer.getEntity());
				ItemEffectsBase itemEffects = stack.getCapability(AetherCapabilities.ITEM_EFFECTS, null);

				if (itemEffects != null)
				{
					for (Pair<EffectProcessor, EffectInstance> effect : itemEffects.getEffectPairs())
					{
						EffectProcessor processor = effect.getLeft();
						EffectInstance instance = effect.getRight();

						effects.removeInstance(processor, instance);
					}
				}
			}
		}

		return super.transferStackInSlot(player, slotNumber);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
}
