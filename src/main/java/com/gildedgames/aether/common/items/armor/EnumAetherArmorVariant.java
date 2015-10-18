package com.gildedgames.aether.common.items.armor;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.AetherMaterials;
import com.gildedgames.aether.common.items.ItemsAether;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public enum EnumAetherArmorVariant
{
	ZANITE("zanite"), GRAVITITE("gravitite"), OBSIDIAN("obsidian"), NEPTUNE("neptune"), PHOENIX("phoenix"), VALKYRIE("valkyrie");

	public final String name;

	EnumAetherArmorVariant(String name)
	{
		this.name = name;
	}

	public String getResourceForSlot(int layerIndex)
	{
		return AetherCore.getResourcePath("textures/armor/" + this.name + "_layer_" + (layerIndex == 2 ? 2 : 1) + ".png");
	}

	public ArmorMaterial getArmorMaterial()
	{
		switch (this)
		{
		case ZANITE:
			return ArmorMaterial.IRON;
		case GRAVITITE:
			return ArmorMaterial.DIAMOND;
		case OBSIDIAN:
			return AetherMaterials.OBSIDIAN_ARMOR;
		case NEPTUNE:
			return ArmorMaterial.DIAMOND;
		case PHOENIX:
			return ArmorMaterial.DIAMOND;
		case VALKYRIE:
			return AetherMaterials.VALKYRIE_ARMOR;
		default:
			return ArmorMaterial.LEATHER;
		}
	}

	public Item getRepairItem()
	{
		switch (this)
		{
		case ZANITE:
			return ItemsAether.zanite_gemstone;
		case OBSIDIAN:
			return Item.getItemFromBlock(Blocks.obsidian);
		default:
			return null;
		}
	}
}
