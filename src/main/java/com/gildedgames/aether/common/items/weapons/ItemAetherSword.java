package com.gildedgames.aether.common.items.weapons;

import net.minecraft.item.ItemSword;

import com.gildedgames.aether.common.AetherCore;

public class ItemAetherSword extends ItemSword
{
	public ItemAetherSword(ToolMaterial material)
	{
		super(material);

		this.setCreativeTab(AetherCore.PROXY.TabWeapons);
	}
}
