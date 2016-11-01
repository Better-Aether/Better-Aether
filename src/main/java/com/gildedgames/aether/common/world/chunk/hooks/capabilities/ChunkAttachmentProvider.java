package com.gildedgames.aether.common.world.chunk.hooks.capabilities;

import com.gildedgames.aether.api.capabilites.AetherCapabilities;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ChunkAttachmentProvider implements ICapabilityProvider
{
	private ChunkAttachmentCapability capability;

	public ChunkAttachmentProvider(ChunkAttachmentCapability capability)
	{
		this.capability = capability;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == AetherCapabilities.CHUNK_ATTACHMENTS && this.capability != null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
	{
		return this.hasCapability(capability, facing) ? (T) this.capability : null;
	}
}
