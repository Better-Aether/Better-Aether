package com.gildedgames.aether.common.network;

import com.gildedgames.aether.client.gui.container.GuiEquipment;
import com.gildedgames.aether.client.gui.container.GuiFrostpineCooler;
import com.gildedgames.aether.client.gui.container.GuiIncubator;
import com.gildedgames.aether.common.capabilities.player.PlayerAetherImpl;
import com.gildedgames.aether.common.containers.ContainerEquipment;
import com.gildedgames.aether.common.containers.tiles.ContainerFrostpineCooler;
import com.gildedgames.aether.common.containers.tiles.ContainerIncubator;
import com.gildedgames.aether.common.containers.ContainerSkyrootWorkbench;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AetherGuiHandler implements IGuiHandler
{

	public static final int SKYROOT_WORKBENCH_ID = 1;

	public static final int INVENTORY_EQUIPMENT_ID = 2;

	public static final int FROSTPINE_COOLER_ID = 3;

	public static final int INCUBATOR_ID = 4;

	@Override
	public Container getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (id)
		{
		case SKYROOT_WORKBENCH_ID:
			return new ContainerSkyrootWorkbench(player.inventory, world, new BlockPos(x, y, z));
		case INVENTORY_EQUIPMENT_ID:
			return new ContainerEquipment(PlayerAetherImpl.getPlayer(player));
		case FROSTPINE_COOLER_ID:
			return new ContainerFrostpineCooler(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
		case INCUBATOR_ID:
			return new ContainerIncubator(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (id)
		{
		case SKYROOT_WORKBENCH_ID:
			return new GuiCrafting(player.inventory, world, new BlockPos(x, y, z));
		case INVENTORY_EQUIPMENT_ID:
			return new GuiEquipment(PlayerAetherImpl.getPlayer(player));
		case FROSTPINE_COOLER_ID:
			return new GuiFrostpineCooler(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
		case INCUBATOR_ID:
			return new GuiIncubator(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}
}
