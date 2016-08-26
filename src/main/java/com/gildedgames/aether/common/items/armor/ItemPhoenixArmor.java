package com.gildedgames.aether.common.items.armor;

import com.gildedgames.aether.common.capabilities.item.properties.IPhoenixChillable;
import com.gildedgames.aether.common.items.ItemsAether;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPhoenixArmor extends ItemAetherArmor implements IPhoenixChillable
{
	public ItemPhoenixArmor(EntityEquipmentSlot armorType)
	{
		super(ArmorMaterial.DIAMOND, "phoenix", armorType);
	}

	@Override
	public boolean canChillItemstack(ItemStack stack)
	{
		return this.getChilledItemstack(stack) != null;
	}

	@Override
	public ItemStack getChilledItemstack(ItemStack stack)
	{
		Item chilledItem = this.getChilledItem(stack.getItem());

		if (chilledItem == null)
		{
			return null;
		}

		return new ItemStack(chilledItem, stack.stackSize, stack.getItemDamage());
	}

	private Item getChilledItem(Item item)
	{
		if (item == ItemsAether.phoenix_helmet)
		{
			return ItemsAether.obsidian_helmet;
		}
		else if (item == ItemsAether.phoenix_chestplate)
		{
			return ItemsAether.obsidian_chestplate;
		}
		else if (item == ItemsAether.phoenix_leggings)
		{
			return ItemsAether.obsidian_leggings;
		}
		else if (item == ItemsAether.phoenix_boots)
		{
			return ItemsAether.obsidian_boots;
		}

		return null;
	}
}
