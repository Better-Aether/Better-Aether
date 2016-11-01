package com.gildedgames.aether.common.world.chunk.hooks.capabilities;

import com.gildedgames.aether.api.capabilites.AetherCapabilities;
import com.gildedgames.aether.api.capabilites.chunk.IPlacementFlagCapability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class PlacementFlagProvider implements ICapabilitySerializable<NBTBase>
{
	private IPlacementFlagCapability capability;

	public PlacementFlagProvider(IPlacementFlagCapability capability)
	{
		this.capability = capability;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == AetherCapabilities.CHUNK_PLACEMENT_FLAG && this.capability != null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		return this.hasCapability(capability, facing) ? (T) this.capability : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		if (this.capability == null)
		{
			return null;
		}

		NBTTagCompound tag = new NBTTagCompound();

		this.capability.write(tag);

		return tag;
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		if (this.capability == null)
		{
			return;
		}

		this.capability.read((NBTTagCompound) nbt);
	}
}
