package com.gildedgames.aether.api;

import com.gildedgames.aether.api.capabilites.instances.IInstanceRegistry;
import com.gildedgames.aether.api.registry.altar.IAltarRecipeRegistry;
import com.gildedgames.aether.api.registry.cooler.ITemperatureRegistry;
import com.gildedgames.aether.api.registry.equipment.IEquipmentRegistry;
import com.gildedgames.aether.api.registry.tab.ITabRegistry;

public interface IAetherServices
{

	IAltarRecipeRegistry getAltarRecipeRegistry();

	IEquipmentRegistry getEquipmentRegistry();

	ITemperatureRegistry getTemperatureRegistry();

	ITabRegistry getTabRegistry();

	IInstanceRegistry getInstanceRegistry();

}
