package com.gildedgames.aether.common.items;

import com.gildedgames.aether.common.AetherCore;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAetherRecord extends ItemRecord
{
	public ItemAetherRecord(String recordName, SoundEvent sound)
	{
		super(recordName, sound);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getRecordNameLocal()
	{
		return I18n.translateToLocal(this.getUnlocalizedName() + ".desc");
	}

	@Override
	public ResourceLocation getRecordResource(String name)
	{
		return AetherCore.getResource(name);
	}
}
