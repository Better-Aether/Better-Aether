package com.gildedgames.aether.api.capabilites.instances;

import net.minecraft.entity.player.EntityPlayer;

import java.util.List;
import java.util.UUID;

public interface IInstanceRegistry
{

	List<IInstanceHandler> getInstanceHandlers();

	void registerInstanceHandler(IInstanceHandler handler);

	<T extends Instance> IInstanceHandler<T> createAndRegisterInstanceHandler(IInstanceFactory<T> factory);

	IPlayerInstances getPlayer(EntityPlayer player);

	IPlayerInstances getPlayer(UUID uuid);

}
