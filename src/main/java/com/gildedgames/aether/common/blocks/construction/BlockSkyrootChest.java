package com.gildedgames.aether.common.blocks.construction;

import com.gildedgames.aether.common.tile_entities.TileEntitySkyrootChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Iterator;

public class BlockSkyrootChest extends BlockChest
{
	private static final BlockChest.Type TYPE = EnumHelper.addEnum(BlockChest.Type.class, "AETHER_SKYROOT", new Class<?>[0]);

	public BlockSkyrootChest()
	{
		super(BlockSkyrootChest.TYPE);

		this.setHardness(2.5f);

		this.setSoundType(SoundType.WOOD);
	}

	@Override
	public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean isPlayer)
	{
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntitySkyrootChest)
		{
			ILockableContainer container = (ILockableContainer) tileentity;

			if (!this.isBlocked(worldIn, pos))
			{
				for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL)
				{
					BlockPos adjPos = pos.offset(facing);
					Block adjBlock = worldIn.getBlockState(adjPos).getBlock();

					if (adjBlock == this)
					{
						if (!isPlayer && this.isBlocked(worldIn, adjPos))
						{
							return null;
						}

						TileEntity adjTileEntity = worldIn.getTileEntity(adjPos);

						if (adjTileEntity instanceof TileEntityChest)
						{
							if (facing != EnumFacing.WEST && facing != EnumFacing.NORTH)
							{
								container = new InventoryLargeChest("container.skyroot_double_chest", container, (ILockableContainer) adjTileEntity);
							}
							else
							{
								container = new InventoryLargeChest("container.skyroot_double_chest", (ILockableContainer) adjTileEntity, container);
							}
						}
					}
				}

				return container;
			}
		}

		return null;
	}

	private boolean isBlocked(World worldIn, BlockPos pos)
	{
		return this.isBelowSolidBlock(worldIn, pos) || this.isOcelotSittingOnChest(worldIn, pos);
	}

	private boolean isBelowSolidBlock(World worldIn, BlockPos pos)
	{
		return worldIn.isSideSolid(pos.up(), EnumFacing.DOWN, false);
	}

	private boolean isOcelotSittingOnChest(World worldIn, BlockPos pos)
	{
		Iterator<EntityOcelot> iterator = worldIn.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1)).iterator();

		EntityOcelot ocelot;

		while (iterator.hasNext())
		{
			ocelot = iterator.next();

			if (ocelot.isSitting())
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntitySkyrootChest();
	}
}
