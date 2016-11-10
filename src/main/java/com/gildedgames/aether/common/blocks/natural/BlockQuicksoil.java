package com.gildedgames.aether.common.blocks.natural;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockQuicksoil extends Block
{
	public BlockQuicksoil()
	{
		super(Material.SAND);

		this.setHardness(1.5f);

		this.setSoundType(SoundType.SAND);
	}
}
