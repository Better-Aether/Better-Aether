package com.gildedgames.aether.api.genes;

import com.gildedgames.aether.api.capabilites.AetherCapabilities;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class GenePoolProvider implements ICapabilitySerializable<NBTBase>
{

	private final GenePoolStorage storage = new GenePoolStorage();

	private final IGenePool capability;

	public GenePoolProvider(IGenePool capability)
	{
		this.capability = capability;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == AetherCapabilities.GENE_POOL && this.capability != null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (this.hasCapability(capability, facing))
		{
			return (T) this.capability;
		}

		return null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return this.storage.writeNBT(AetherCapabilities.GENE_POOL, this.capability, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		this.storage.readNBT(AetherCapabilities.GENE_POOL, this.capability, null, nbt);
	}

}
