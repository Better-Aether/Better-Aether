package com.gildedgames.aether.common.tile_entities;

import java.util.Collection;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;

import com.gildedgames.aether.api.capabilites.AetherCapabilities;
import com.gildedgames.aether.api.entities.effects.IEffectPool;
import com.gildedgames.aether.api.entities.effects.IEntityEffectsCapability;
import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.entities.effects.EntityEffects;
import com.gildedgames.aether.common.entities.effects.processors.FrozenInSchematicEffect;
import com.gildedgames.aether.common.util.BlockPosUtil;
import com.gildedgames.util.core.nbt.NBT;
import com.gildedgames.util.core.nbt.NBTHelper;
import com.google.common.collect.Lists;

public class TileEntityBoundary extends TileEntityWildcard
{
	
	private boolean masterBoundary;
	
	private BlockPos linkedPos;
	
	private AxisAlignedBB bounds;
	
	private List<FetchedEntity> fetchedEntities = Lists.newArrayList();
	
	private boolean shouldFetch = true;
	
	public static class FetchedEntity implements NBT
	{
		
		private String entityName;
		
		private NBTTagCompound data;
		
		private double x, y, z;
		
		@SuppressWarnings("unused")
		private FetchedEntity()
		{
			
		}
		
		public FetchedEntity(BlockPos boundPos, Entity entity)
		{
			this.entityName = EntityList.getEntityString(entity);
			this.data = new NBTTagCompound();
			
			entity.writeToNBT(this.data);
			
			this.x = entity.posX - boundPos.getX();
			this.y = entity.posY - boundPos.getY();
			this.z = entity.posZ - boundPos.getZ();
		}

		@Override
		public void write(NBTTagCompound output)
		{
			output.setString("entityName", this.entityName);
			output.setTag("data", this.data);
			
			output.setDouble("x", this.x);
			output.setDouble("y", this.y);
			output.setDouble("z", this.z);
		}

		@Override
		public void read(NBTTagCompound input)
		{
			this.entityName = input.getString("entityName");
			this.data = input.getCompoundTag("data");
			
			this.x = input.getDouble("x");
			this.y = input.getDouble("y");
			this.z = input.getDouble("z");
		}
		
	}
	
	public boolean isMasterBoundary()
	{
		return this.masterBoundary;
	}
	
	@Override
	public void unmarkForGeneration()
	{
		super.unmarkForGeneration();
		
		Iterable<MutableBlockPos> bounds = BlockPos.getAllInBoxMutable(this.getPos(), this.linkedPos);
		
		for(MutableBlockPos pos : bounds)
		{
			TileEntity te = this.getWorld().getTileEntity(pos);
			
			if (te instanceof TileEntitySchematicBlock)
			{
				TileEntitySchematicBlock schematicBlock = (TileEntitySchematicBlock)te;
			
				schematicBlock.isMarkedForGeneration = false;
			}
		}
	}
	
	@Override
	public void markForGeneration()
	{
		super.markForGeneration();
		
		Iterable<MutableBlockPos> bounds = BlockPos.getAllInBoxMutable(this.getPos(), this.linkedPos);
		
		for(MutableBlockPos pos : bounds)
		{
			TileEntity te = this.getWorld().getTileEntity(pos);
			
			if (te instanceof TileEntitySchematicBlock)
			{
				TileEntitySchematicBlock schematicBlock = (TileEntitySchematicBlock)te;
			
				schematicBlock.isMarkedForGeneration = true;
				
				schematicBlock.onMarkedForGeneration(this.getPos(), this.linkedPos);
			}
		}
	}
	
	public boolean isLinked()
	{
		return this.linkedPos != null;
	}
	
	public BlockPos getLink()
	{
		return this.linkedPos;
	}
	
	public void linkToPos(BlockPos pos)
	{
		TileEntity ourTE = this.getWorld().getTileEntity(this.getPos());
		TileEntity theirTE = this.getWorld().getTileEntity(pos);

		if (theirTE instanceof TileEntityBoundary)
		{
			TileEntityBoundary sb = (TileEntityBoundary)theirTE;
			
			this.linkedPos = pos;
			sb.linkedPos = this.getPos();
			
			this.masterBoundary = true; // set this boundary as the master
			sb.masterBoundary = false; // make sure other boundary is not the master
			
			NBTTagCompound tagOurTE = ourTE.serializeNBT();
			NBTTagCompound tagTheirTE = theirTE.serializeNBT();
			
			this.getWorld().setBlockState(pos, BlocksAether.linkedSchematicBoundary.getDefaultState());
			this.getWorld().setBlockState(this.getPos(), BlocksAether.linkedSchematicBoundary.getDefaultState());
			
			this.getWorld().getTileEntity(pos).deserializeNBT(tagTheirTE);
			this.getWorld().getTileEntity(this.getPos()).deserializeNBT(tagOurTE);
		}
	}

	@Override
	public void update()
	{
		super.update();
		
		if (this.linkedPos == null || !this.masterBoundary)
		{
			return;
		}

		if (this.bounds == null)
		{
			this.bounds = BlockPosUtil.bounds(this.getPos(), this.linkedPos);
		}
	
		if (this.shouldFetch && !this.getWorld().isRemote)
		{
			List<Entity> entities = this.worldObj.getEntitiesWithinAABB(Entity.class, this.bounds);
			
			for (Entity entity : entities)
			{
				if (!(entity instanceof EntityPlayer) && entity.hasCapability(AetherCapabilities.ENTITY_EFFECTS, null))
				{
					IEntityEffectsCapability effects = EntityEffects.get(entity);
					
					if (effects != null)
					{
						boolean hasProcessor = false;
						
						innerloop:
						for (IEffectPool<?> pool : effects.getEffectPools())
						{
							if (pool.getProcessor() == EntityEffects.FROZEN_IN_SCHEMATIC && pool.getInstances().size() > 0)
							{
								hasProcessor = true;
								break innerloop;
							}
						}
						
						if (!hasProcessor)
						{
							this.fetchedEntities.add(new FetchedEntity(this.getPos(), entity));
		
							effects.addInstance(EntityEffects.FROZEN_IN_SCHEMATIC, new FrozenInSchematicEffect.Instance());
						}
					}
				}
			}
		}
	}
	
	private void recreateFetchedEntities()
	{
		if (this.getWorld().isRemote || !this.masterBoundary)
		{
			return;
		}
		
		this.shouldFetch = false;
		
		for (FetchedEntity entity : this.fetchedEntities)
		{
			Entity ent = EntityList.createEntityByName(entity.entityName, this.getWorld());

			ent.readFromNBT(entity.data);
			
			ent.setLocationAndAngles(this.getPos().getX() + entity.x, this.getPos().getY() + entity.y, this.getPos().getZ() + entity.z, MathHelper.wrapDegrees(this.getWorld().rand.nextFloat() * 360.0F), 0.0F);
		
			this.getWorld().spawnEntityInWorld(ent);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		NBTHelper.writeBlockPos(compound, "linkedPos", this.linkedPos);
		NBTHelper.fullySerializeCollection("fetchedEntities", this.fetchedEntities, compound);
		
		compound.setBoolean("masterBoundary", this.masterBoundary);
		compound.setBoolean("shouldFetch", this.shouldFetch);

		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.linkedPos = NBTHelper.readBlockPos(compound, "linkedPos");
		
		Collection<FetchedEntity> col = NBTHelper.fullyDeserializeCollection("fetchedEntities", compound);
		
		this.fetchedEntities.addAll(col);
		
		this.masterBoundary = compound.getBoolean("masterBoundary");
		this.shouldFetch = compound.getBoolean("shouldFetch");
	}

	@Override
	public void onSchematicGeneration()
	{
		this.recreateFetchedEntities();
		
		super.onSchematicGeneration();
	}

	@Override
	public void onMarkedForGeneration(BlockPos start, BlockPos end)
	{
		super.onMarkedForGeneration(start, end);
	}
	
	public void destroyLinkAndContents()
	{
		if (!this.isLinked() || this.bounds == null)
		{
			return;
		}
		
		if (!this.getWorld().isRemote)
		{
			List<Entity> entities = this.worldObj.getEntitiesWithinAABB(Entity.class, this.bounds);
			
			for (Entity entity : entities)
			{
				if (!(entity instanceof EntityPlayer) && entity.hasCapability(AetherCapabilities.ENTITY_EFFECTS, null))
				{
					IEntityEffectsCapability effects = EntityEffects.get(entity);
					
					if (effects != null)
					{
						boolean hasProcessor = false;
						
						innerloop:
						for (IEffectPool<?> pool : effects.getEffectPools())
						{
							if (pool.getProcessor() == EntityEffects.FROZEN_IN_SCHEMATIC && pool.getInstances().size() > 0)
							{
								hasProcessor = true;
								break innerloop;
							}
						}
						
						if (hasProcessor)
						{
							this.getWorld().removeEntity(entity);
						}
					}
				}
			}
		}
		
		this.fetchedEntities.clear();
		
		this.getWorld().setBlockToAir(this.getLink());
		this.getWorld().setBlockToAir(this.getPos());
	}
	
}
