package com.gildedgames.aether.common.entities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFloatingBlock extends Entity
{
	private static final int BLOCK_NAME_ID = 20, BLOCK_STATE_ID = 21;

	private int inAirTicks;

	public EntityFloatingBlock(World world)
	{
		super(world);

		this.setSize(0.98F, 0.98F);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
	}

	public EntityFloatingBlock(World world, double x, double y, double z, IBlockState state)
	{
		this(world);

		this.setBlockState(state);

		this.setPosition(x, y, z);

		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(BLOCK_NAME_ID, new String(""));
		this.dataWatcher.addObject(BLOCK_STATE_ID, new Integer(0));
	}

	@Override
	public void onUpdate()
	{
		// Destroys the source block, since deleting a neighboring block in the actual block class
		// causes a infinite loop of updates.

		if (this.inAirTicks++ == 0)
		{
			BlockPos pos = new BlockPos(this);

			if (this.worldObj.getBlockState(pos).getBlock() == this.getBlockState().getBlock())
			{
				this.worldObj.setBlockToAir(pos);
			}
			else
			{
				this.setDead();
			}
		}

		if (this.inAirTicks > 200)
		{
			this.setDead();

			if (!this.worldObj.isRemote)
			{
				if (this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops"))
				{
					IBlockState state = this.getBlockState();

					Block block = state.getBlock();
					int meta = block.getMetaFromState(state);

					ItemStack stack = new ItemStack(block, 1, meta);

					EntityItem entityItem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, stack);
					this.worldObj.spawnEntityInWorld(entityItem);
				}
			}
		}
		else
		{
			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;

			this.motionY += 0.04D;

			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			this.motionX *= 0.98D;
			this.motionY *= 0.98D;
			this.motionZ *= 0.98D;

			if (!this.worldObj.isRemote)
			{
				BlockPos pos = new BlockPos(this);
				BlockPos abovePos = pos.up();

				if (!this.worldObj.isAirBlock(abovePos))
				{
					this.worldObj.setBlockState(pos, this.getBlockState());

					this.setDead();
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		Block block = Block.getBlockFromName(compound.getString("Block"));

		this.setBlockState(block.getStateFromMeta(compound.getByte("BlockState")));
		this.ticksExisted = compound.getInteger("TicksExisted");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		IBlockState state = this.getBlockState();
		Block block = state.getBlock();
		ResourceLocation resourceLocation = (ResourceLocation) Block.blockRegistry.getNameForObject(block);

		compound.setString("Block", resourceLocation.toString());
		compound.setByte("BlockState", (byte) block.getMetaFromState(state));
		compound.setInteger("TicksExisted", this.ticksExisted);
	}

	public IBlockState getBlockState()
	{
		Block block = Block.getBlockFromName(this.dataWatcher.getWatchableObjectString(BLOCK_NAME_ID));
		int meta = this.dataWatcher.getWatchableObjectInt(BLOCK_STATE_ID);

		return block.getStateFromMeta(meta);
	}

	public void setBlockState(IBlockState state)
	{
		Block block = state.getBlock();

		this.dataWatcher.updateObject(BLOCK_NAME_ID, Block.blockRegistry.getNameForObject(block).toString());
		this.dataWatcher.updateObject(BLOCK_STATE_ID, block.getMetaFromState(state));
	}
}
