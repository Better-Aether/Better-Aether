package com.gildedgames.aether.blocks.natural;

import com.gildedgames.aether.AetherCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGravititeOre extends Block
{

	public BlockGravititeOre()
	{
		super(Material.rock);

		this.setHardness(3.0F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);

		this.setCreativeTab(AetherCreativeTabs.tabBlocks);
	}

}
