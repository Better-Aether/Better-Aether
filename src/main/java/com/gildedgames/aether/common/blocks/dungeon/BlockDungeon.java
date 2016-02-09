package com.gildedgames.aether.common.blocks.dungeon;

import com.gildedgames.aether.common.blocks.util.variants.IAetherBlockWithVariants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockDungeon extends Block implements IAetherBlockWithVariants
{
	public static final int
			NORMAL = 0,
			DIVINE = 1;

	public static final PropertyBool PROPERTY_IS_DIVINE = PropertyBool.create("is_divine");

	public BlockDungeon(Material material)
	{
		super(material);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, NORMAL));
		list.add(new ItemStack(item, 1, DIVINE));
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		return state.getBlock() == this && state.getValue(PROPERTY_IS_DIVINE) ? -1.0f : this.blockHardness;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(PROPERTY_IS_DIVINE, meta == 1);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(PROPERTY_IS_DIVINE) ? DIVINE : NORMAL;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, PROPERTY_IS_DIVINE);
	}

	@Override
	public String getSubtypeUnlocalizedName(ItemStack stack)
	{
		switch (stack.getMetadata())
		{
		case DIVINE:
			return "divine";
		case NORMAL:
			return "normal";
		default:
			return "missingno";
		}
	}

	public BlockDungeon setGlows(boolean glows)
	{
		this.setLightLevel(glows ? 0.75f : 0.0f);

		return this;
	}
}
