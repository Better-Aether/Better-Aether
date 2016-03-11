package com.gildedgames.aether.common.items;

public enum AccessoryType
{
	RING("gui.slot.ring"),
	NECKWEAR("gui.slot.neckwear"),
	COMPANION("gui.slot.companion"),
	SHIELD("gui.slot.shield"),
	HANDWEAR("gui.slot.handwear"),
	RELIC("gui.slot.relic"),
	CHARM("gui.slot.charm"),
	ARTIFACT("gui.slot.artifact");
	
	private final String unlocalizedName;

	AccessoryType(String unlocalizedName)
	{
		this.unlocalizedName = unlocalizedName;
	}

	public String getUnlocalizedName()
	{
		return this.unlocalizedName;
	}
}
