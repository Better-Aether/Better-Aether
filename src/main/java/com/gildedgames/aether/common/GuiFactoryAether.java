package com.gildedgames.aether.common;

import com.gildedgames.aether.client.gui.GuiConfigAether;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

public class GuiFactoryAether implements IModGuiFactory
{

	@Override public void initialize(Minecraft minecraftInstance)
	{

	}

	@Override public Class<? extends GuiScreen> mainConfigGuiClass()
	{
		return GuiConfigAether.class;
	}

	@Override public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

	@Override public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
	{
		return null;
	}
}
