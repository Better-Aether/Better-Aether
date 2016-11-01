package com.gildedgames.aether.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.gildedgames.aether.common.util.helpers.BlockUtil;

public class TileEntityWildcard extends TileEntitySchematicBlock
{

	@Override
	public void onSchematicGeneration()
	{
		int contentSize = 0;

		for (ItemStack stack : this.contents)
		{
			if (stack != null && (stack.getItem() instanceof ItemBlock || stack.getItem() == Items.STRING))
			{
				contentSize += stack.stackSize;
			}
		}

		if (contentSize == 0)
		{
			this.getWorld().setBlockToAir(this.getPos());
			this.getWorld().scheduleUpdate(this.getPos(), this.blockType, 0);

			return;
		}

		int currentIndex = this.getWorld().rand.nextInt(contentSize);
		ItemStack chosenStack = null;

		for (ItemStack stack : this.contents)
		{
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemBlock || stack.getItem() == Items.STRING)
				{
					if (stack.stackSize > currentIndex)
					{
						chosenStack = stack;
						break;
					}
					else
					{
						currentIndex -= stack.stackSize;
					}
				}
			}
		}

		Block block;
		int damage = 0;

		if (chosenStack.getItem() == Items.STRING)
		{
			block = Blocks.AIR;
		}
		else
		{
			ItemBlock itemBlock = (ItemBlock)chosenStack.getItem();

			block = itemBlock.getBlock();
			damage = chosenStack.getItemDamage();
		}

		if (block == Blocks.AIR)
		{
			return;
		}

		this.getWorld().setBlockState(this.getPos(), block.getStateFromMeta(damage));
		BlockUtil.setTileEntityNBT(this.getWorld(), this.getPos(), chosenStack);
		this.getWorld().scheduleUpdate(this.getPos(), block, 0);
	}

	@Override
	public boolean shouldInvalidateTEOnGen()
	{
		return true;
	}

}
