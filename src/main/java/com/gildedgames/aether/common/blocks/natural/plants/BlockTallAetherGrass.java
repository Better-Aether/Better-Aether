package com.gildedgames.aether.common.blocks.natural.plants;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.natural.BlockAetherGrass;
import com.gildedgames.aether.common.blocks.util.variants.IBlockVariants;
import com.gildedgames.aether.common.blocks.util.variants.blockstates.BlockVariant;
import com.gildedgames.aether.common.blocks.util.variants.blockstates.PropertyVariant;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockTallAetherGrass extends BlockAetherPlant implements IShearable, IBlockVariants
{

	private static final AxisAlignedBB GRASS_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.3D, 0.9D);

	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 4);

	public static final int DEFAULT = 0, ENCHANTED = 1, KURA = 2, FROSTROOT = 3, BLIGHTED = 4;

	public static final BlockVariant SHORT = new BlockVariant(0, "short"), NORMAL = new BlockVariant(1, "normal"), LONG = new BlockVariant(2, "long");

	public static final PropertyVariant PROPERTY_VARIANT = PropertyVariant.create("variant", SHORT, NORMAL, LONG);

	public BlockTallAetherGrass()
	{
		super(Material.PLANTS);

		this.setSoundType(SoundType.PLANT);

		this.setDefaultState(this.getBlockState().getBaseState().withProperty(TYPE, 0).withProperty(PROPERTY_VARIANT, SHORT));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (BlockVariant variant : PROPERTY_VARIANT.getAllowedValues())
		{
			list.add(new ItemStack(item, 1, variant.getMeta()));
		}
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		IBlockState block = worldIn.getBlockState(pos.down());

		int type = DEFAULT;

		if (block == BlocksAether.aether_grass.getStateFromMeta(BlockAetherGrass.ENCHANTED.getMeta()))
		{
			type = ENCHANTED;
		}

		/*if (block == BlocksAether.aether_grass.getStateFromMeta(BlockAetherGrass.KURA.getMeta()))
		{
			type = KURA;
		}

		if (block == BlocksAether.short_aether_grass.getStateFromMeta(BlockAetherGrass.FROSTROOT.getMeta()))
		{
			type = FROSTROOT;
		}

		if (block == BlocksAether.short_aether_grass.getStateFromMeta(BlockAetherGrass.BLIGHTED.getMeta()))
		{
			type = BLIGHTED;
		}*/

		return state.withProperty(TYPE, type);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		List<ItemStack> drops = new ArrayList<>();
		drops.add(new ItemStack(this));

		return drops;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType()
	{
		return EnumOffsetType.XZ;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return GRASS_AABB;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		BlockVariant variant = PROPERTY_VARIANT.fromMeta(meta);

		return this.getDefaultState().withProperty(PROPERTY_VARIANT, variant);
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(PROPERTY_VARIANT).getMeta();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE, PROPERTY_VARIANT);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return PROPERTY_VARIANT.fromMeta(stack.getMetadata()).getName();
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return state.getValue(PROPERTY_VARIANT).getMeta();
	}

	@Override
	public void addItemsToCreativeTab(Item item, CreativeTabs tab, List<ItemStack> stackList)
	{

	}

}
