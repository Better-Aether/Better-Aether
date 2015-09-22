package com.gildedgames.aether.common.blocks.natural;

import com.gildedgames.aether.common.blocks.util.BlockWithDoubleDrops;
import com.gildedgames.aether.common.blocks.util.variants.IAetherBlockWithVariants;
import com.gildedgames.aether.common.blocks.util.variants.blockstates.BlockVariant;
import com.gildedgames.aether.common.blocks.util.variants.blockstates.PropertyVariant;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockHolystone extends BlockWithDoubleDrops implements IAetherBlockWithVariants
{
	public static final BlockVariant
			NORMAL_HOLYSTONE = new BlockVariant(0, "normal"),
			MOSSY_HOLYSTONE = new BlockVariant(1, "mossy"),
			BLOOD_MOSS_HOLYSTONE = new BlockVariant(2, "blood_moss");

	public static final PropertyVariant PROPERTY_VARIANT = PropertyVariant.create("variant", NORMAL_HOLYSTONE, MOSSY_HOLYSTONE, BLOOD_MOSS_HOLYSTONE);

	public BlockHolystone()
	{
		super(Material.rock);

		this.setHardness(2.0F);

		this.setStepSound(Block.soundTypeStone);

		this.setDefaultState(this.getBlockState().getBaseState().withProperty(PROPERTY_VARIANT, NORMAL_HOLYSTONE));
	}

	@Override
	public boolean isReplaceableOreGen(World world, BlockPos pos, Predicate<IBlockState> target)
	{
		if (target.apply(Blocks.stone.getDefaultState()))
		{
			IBlockState state = world.getBlockState(pos);

			if (state.getBlock() == this && state.getValue(PROPERTY_VARIANT) == NORMAL_HOLYSTONE)
			{
				return true;
			}
		}

		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		for (BlockVariant variant : PROPERTY_VARIANT.getAllowedValues())
		{
			if (variant != BLOOD_MOSS_HOLYSTONE)
			{
				list.add(new ItemStack(itemIn, 1, variant.getMeta()));
			}
		}
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		return state.getBlock() == this && state.getValue(PROPERTY_VARIANT) == BLOOD_MOSS_HOLYSTONE ? -1.0f : this.blockHardness;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(PROPERTY_VARIANT, PROPERTY_VARIANT.fromMeta(meta & 7)).withProperty(PROPERTY_WAS_MINED, (meta & 8) == 8);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int meta = ((BlockVariant) state.getValue(PROPERTY_VARIANT)).getMeta();

		if (state.getValue(PROPERTY_WAS_MINED) == Boolean.TRUE)
		{
			meta |= 8;
		}

		return meta;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, PROPERTY_VARIANT, PROPERTY_WAS_MINED);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state.withProperty(PROPERTY_WAS_MINED, Boolean.TRUE));
	}

	@Override
	public String getSubtypeUnlocalizedName(ItemStack stack)
	{
		return PROPERTY_VARIANT.fromMeta(stack.getMetadata()).getName();
	}
}
