package com.gildedgames.aether.blocks;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.world.TeleporterAether;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockAetherPortal extends BlockBreakable
{
	public static final PropertyEnum PORTAL_AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);

	public BlockAetherPortal()
	{
		super(Material.portal, false);
		this.setStepSound(soundTypeGlass);
		this.setHardness(-1.0F);
		this.setLightLevel(0.75F);
		this.setTickRandomly(true);

		this.setDefaultState(this.blockState.getBaseState().withProperty(PORTAL_AXIS, EnumFacing.Axis.X));
	}

	public static boolean tryToCreatePortal(World world, BlockPos pos, EnumFacing facing)
	{
		if (world.getBlockState(pos).getBlock() == Blocks.glowstone)
		{
			BlockPos portalPos = pos.offset(facing);

			BlockAetherPortal.Size size = new BlockAetherPortal.Size(world, portalPos, EnumFacing.Axis.X);

			if (size.isWithinSizeBounds() && size.get_field_150864_e() == 0)
			{
				size.createPortal();

				return true;
			}
			else
			{
				BlockAetherPortal.Size size1 = new BlockAetherPortal.Size(world, portalPos, EnumFacing.Axis.Z);

				if (size1.isWithinSizeBounds() && size1.get_field_150864_e() == 0)
				{
					size1.createPortal();

					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
	{
		EnumFacing.Axis axis = (EnumFacing.Axis) world.getBlockState(pos).getValue(PORTAL_AXIS);

		float xThickness = 0.125F, zThickness = 0.125F;

		if (axis == EnumFacing.Axis.X)
		{
			xThickness = 0.5F;
		}

		if (axis == EnumFacing.Axis.Z)
		{
			zThickness = 0.5F;
		}

		this.setBlockBounds(0.5F - xThickness, 0.0F, 0.5F - zThickness, 0.5F + xThickness, 1.0F, 0.5F + zThickness);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (rand.nextInt(100) == 0)
		{
			world.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, Aether.getResource("aeportal.portal"), 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		EnumFacing.Axis axis = null;
		IBlockState state = world.getBlockState(pos);

		if (world.getBlockState(pos).getBlock() == this)
		{
			axis = (EnumFacing.Axis) state.getValue(PORTAL_AXIS);

			if (axis == null)
			{
				return false;
			}

			if (axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST)
			{
				return false;
			}

			if (axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH)
			{
				return false;
			}
		}

		boolean westFlag = world.getBlockState(pos.west()).getBlock() == this && world.getBlockState(pos.west(2)).getBlock() != this;
		boolean eastFlag = world.getBlockState(pos.east()).getBlock() == this && world.getBlockState(pos.east(2)).getBlock() != this;
		boolean northFlag = world.getBlockState(pos.north()).getBlock() == this && world.getBlockState(pos.north(2)).getBlock() != this;
		boolean southFlag = world.getBlockState(pos.south()).getBlock() == this && world.getBlockState(pos.south(2)).getBlock() != this;
		boolean wexFlag = westFlag || eastFlag || axis == EnumFacing.Axis.X;
		boolean nszFlag = northFlag || southFlag || axis == EnumFacing.Axis.Z;

		return wexFlag && side == EnumFacing.WEST || (wexFlag && side == EnumFacing.EAST || (nszFlag && side == EnumFacing.NORTH || nszFlag && side == EnumFacing.SOUTH));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			ServerConfigurationManager scm = MinecraftServer.getServer().getConfigurationManager();

			int transferToID = player.dimension == Aether.AETHER_DIM_ID ? 0 : Aether.AETHER_DIM_ID;
			scm.transferPlayerToDimension(player, transferToID , new TeleporterAether(MinecraftServer.getServer().worldServerForDimension(3)));
		}
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumFacing.Axis axis = (EnumFacing.Axis) state.getValue(PORTAL_AXIS);

		return axis == EnumFacing.Axis.X ? 1 : (axis == EnumFacing.Axis.Z ? 2 : 0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(PORTAL_AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		EnumFacing.Axis axis = (EnumFacing.Axis) state.getValue(PORTAL_AXIS);

		if (axis == EnumFacing.Axis.X || axis == EnumFacing.Axis.Z)
		{
			BlockAetherPortal.Size size = new BlockAetherPortal.Size(world, pos, axis);

			if (!size.isWithinSizeBounds() || size.field_150864_e < size.height * size.width)
			{
				world.setBlockState(pos, Blocks.air.getDefaultState());
			}
		}
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, PORTAL_AXIS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	public static class Size
	{
		private final World world;

		private final EnumFacing.Axis axis;

		private final EnumFacing rightSideFacing;

		private final EnumFacing leftSideFacing;

		private BlockPos portalPos;

		// ???
		private int field_150864_e = 0;

		private int height;

		private int width;

		public Size(World world, BlockPos pos, EnumFacing.Axis axis)
		{
			this.world = world;
			this.axis = axis;

			if (axis == EnumFacing.Axis.X)
			{
				this.leftSideFacing = EnumFacing.EAST;
				this.rightSideFacing = EnumFacing.WEST;
			}
			else
			{
				this.leftSideFacing = EnumFacing.NORTH;
				this.rightSideFacing = EnumFacing.SOUTH;
			}

			BlockPos offsetPos = pos;

			while (pos.getY() > offsetPos.getY() - 21 && pos.getY() > 0 && this.isBlockSuitable(world.getBlockState(pos.down()).getBlock()))
			{
				pos = pos.down();
			}

			int x = this.getWidth(pos, this.leftSideFacing) - 1;

			if (x >= 0)
			{
				this.portalPos = pos.offset(this.leftSideFacing, x);
				this.width = this.getWidth(this.portalPos, this.rightSideFacing);

				if (this.width < 2 || this.width > 21)
				{
					this.portalPos = null;
					this.width = 0;
				}
			}

			if (this.portalPos != null)
			{
				this.height = this.func_150858_a();
			}
		}

		protected int getWidth(BlockPos pos, EnumFacing facing)
		{
			int x;

			for (x = 0; x < 22; ++x)
			{
				BlockPos offsetPos = pos.offset(facing, x);

				if (!this.isBlockSuitable(this.world.getBlockState(offsetPos).getBlock()) || this.world.getBlockState(offsetPos.down()).getBlock() != Blocks.glowstone)
				{
					break;
				}
			}

			Block block = this.world.getBlockState(pos.offset(facing, x)).getBlock();
			return block == Blocks.glowstone ? x : 0;
		}

		protected int func_150858_a()
		{
			int x;
			loop:

			for (this.height = 0; this.height < 21; ++this.height)
			{
				for (x = 0; x < this.width; ++x)
				{
					BlockPos blockpos = this.portalPos.offset(this.rightSideFacing, x).up(this.height);
					Block block = this.world.getBlockState(blockpos).getBlock();

					if (!this.isBlockSuitable(block))
					{
						break loop;
					}

					if (block == Blocks.glowstone)
					{
						++this.field_150864_e;
					}

					if (x == 0)
					{
						block = this.world.getBlockState(blockpos.offset(this.leftSideFacing)).getBlock();

						if (block != Blocks.glowstone)
						{
							break loop;
						}
					}
					else if (x == this.width - 1)
					{
						block = this.world.getBlockState(blockpos.offset(this.rightSideFacing)).getBlock();

						if (block != Blocks.glowstone)
						{
							break loop;
						}
					}
				}
			}

			for (x = 0; x < this.width; ++x)
			{
				if (this.world.getBlockState(this.portalPos.offset(this.rightSideFacing, x).up(this.height)).getBlock() != Blocks.glowstone)
				{
					this.height = 0;
					break;
				}
			}

			if (this.height <= 21 && this.height >= 3)
			{
				return this.height;
			}
			else
			{
				this.portalPos = null;
				this.width = 0;
				this.height = 0;
				return 0;
			}
		}

		protected boolean isBlockSuitable(Block block)
		{
			return block.getMaterial() == Material.air || block == Blocks.water || block == Aether.getBlocks().aether_portal;
		}

		public boolean isWithinSizeBounds()
		{
			return this.portalPos != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
		}

		public void createPortal()
		{
			for (int i = 0; i < this.width; ++i)
			{
				BlockPos blockpos = this.portalPos.offset(this.rightSideFacing, i);

				for (int j = 0; j < this.height; ++j)
				{
					this.world.setBlockState(blockpos.up(j), Aether.getBlocks().aether_portal.getDefaultState().withProperty(BlockAetherPortal.PORTAL_AXIS, this.axis), 2);
				}
			}
		}

		public int get_field_150864_e()
		{
			return this.field_150864_e;
		}
	}
}
