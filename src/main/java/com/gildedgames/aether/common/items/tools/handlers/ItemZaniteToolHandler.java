package com.gildedgames.aether.common.items.tools.handlers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemZaniteToolHandler implements IToolEventHandler
{
	@Override
	public void onHarvestBlock(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityPlayer entity, List<ItemStack> drops)
	{

	}

	@Override
	public void onRightClickBlock(ItemStack stack, World world, BlockPos pos, EntityPlayer player, EnumFacing facing)
	{

	}

	@Override
	public void onRightClickItem(ItemStack stack, World world, EntityPlayer player)
	{

	}

	@Override
	public void addInformation(ItemStack stack, List<String> tooltip)
	{
		tooltip.add(1, String.format("%s: %s",
				TextFormatting.BLUE + I18n.format("item.aether.tooltip.ability"),
				TextFormatting.WHITE + I18n.format("item.aether.tool.zanite.ability.desc")));
	}

	@Override
	public float getBreakSpeed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityPlayer player, float original)
	{
		if (state.getBlock().canHarvestBlock(world, pos, player))
		{
			float scale = ((2.0F * ((float) stack.getItemDamage() / (float) stack.getItem().getMaxDamage())) + 1.0F);

			return original * scale;
		}

		return original;
	}
}
