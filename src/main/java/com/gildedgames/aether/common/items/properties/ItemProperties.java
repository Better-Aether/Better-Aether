package com.gildedgames.aether.common.items.properties;

import com.gildedgames.aether.api.items.properties.ItemEquipmentType;
import com.gildedgames.aether.api.items.properties.ItemRarity;
import com.gildedgames.aether.api.items.IItemPropertiesCapability;
import com.gildedgames.aether.api.registry.equipment.IEquipmentProperties;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class ItemProperties implements IItemPropertiesCapability
{
	private IEquipmentProperties properties;

	public ItemProperties(IEquipmentProperties properties)
	{
		this.properties = properties;
	}

	@Override
	public IEquipmentProperties getProperties()
	{
		return this.properties;
	}

	@Override
	public ItemRarity getRarity()
	{
		return this.properties == null ? ItemRarity.NONE : this.properties.getRarity();
	}

	@Override
	public ItemEquipmentType getEquipmentType()
	{
		return this.properties == null ? null : this.properties.getEquipmentType();
	}

	@Override
	public boolean isEquippable()
	{
		return this.getEquipmentType() != null;
	}

	public static class Storage implements Capability.IStorage<IItemPropertiesCapability>
	{
		@Override
		public NBTBase writeNBT(Capability<IItemPropertiesCapability> capability, IItemPropertiesCapability instance, EnumFacing side)
		{
			return null;
		}

		@Override
		public void readNBT(Capability<IItemPropertiesCapability> capability, IItemPropertiesCapability instance, EnumFacing side, NBTBase nbt)
		{

		}
	}
}

