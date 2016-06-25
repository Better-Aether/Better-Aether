package com.gildedgames.aether.common.blocks.natural.ores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAetherOre extends Block
{
	public BlockAetherOre(Material material)
	{
		super(material);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune))
		{
			int rand = random.nextInt(fortune + 2) - 1;

			if (rand < 0)
			{
				rand = 0;
			}

			return this.quantityDropped(random) * (rand + 1);
		}

		return this.quantityDropped(random);
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
	{
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
		{
			return this.getUnmodifiedExpDrop(rand);
		}

		return 0;
	}

	protected int getUnmodifiedExpDrop(Random rand)
	{
		return 0;
	}
}
