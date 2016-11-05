package com.gildedgames.aether.common.entities.ai.moa;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.natural.plants.BlockWovenSticks;
import com.gildedgames.aether.common.entities.living.mounts.EntityMoa;
import com.gildedgames.aether.common.entities.util.AnimalGender;
import com.gildedgames.aether.common.entities.util.EntityGroupMember;
import com.gildedgames.aether.common.entities.genes.moa.MoaGenePool;
import com.gildedgames.aether.common.tiles.TileEntityMoaEgg;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AIMoaPackBreeding extends EntityAIBase
{

	public World world;

	public EntityMoa moa;

	public EntityLiving packLeader;

	public float moveSpeed;

	public BlockPos eggPos;

	public int timeUntilLay;

	public AIMoaPackBreeding(EntityMoa moa, float moveSpeed)
	{
		this.world = moa.worldObj;
		this.moveSpeed = moveSpeed;

		this.moa = moa;
		
		if (!moa.isGroupLeader())
		{
			this.resetTimer();
		}
	}

	public void resetTimer()
	{
		this.timeUntilLay = 60 + this.moa.getRNG().nextInt(120);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.moa == null || this.world == null)
		{
			return false;
		}

		if (this.moa.isChild())
		{
			return false;
		}

		if (this.moa.getFamilyNest() == null)
		{
			return false;
		}
		else if (!this.moa.getFamilyNest().hasInitialized)
		{
			return false;
		}

		this.eggPos = this.moa.getFamilyNest().pos.add(0, 1, 0);

		if (this.world.getBlockState(this.eggPos).getBlock() == BlocksAether.moa_egg)
		{
			return false;
		}

		if (!(this.world.getBlockState(this.moa.getFamilyNest().pos).getBlock() instanceof BlockWovenSticks))
		{
			return false;
		}

		if (this.timeUntilLay > 0)
		{
			if (this.moa.ticksExisted % 20 == 0)
			{
				this.timeUntilLay--;
			}

			return false;
		}

		return true;
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		Path path = this.moa.getNavigator().getPath();

		boolean isNearEgg = this.moa.getDistanceSq(this.eggPos.getX() - 1, this.eggPos.getY(), this.eggPos.getZ() - 1) <= 4.0D;

		if ((path == null || path.isFinished()) && !isNearEgg)
		{
			if (!this.moa.getNavigator().tryMoveToXYZ(this.eggPos.getX() - 1, this.eggPos.getY(), this.eggPos.getZ() - 1, this.moveSpeed))
			{
				this.resetTimer();
			}
		}
		else if (isNearEgg && this.moa.getNavigator().getPath() != null && this.moa.getNavigator().getPath().isFinished())
		{
			this.world.setBlockState(this.eggPos, BlocksAether.moa_egg.getDefaultState());

			TileEntityMoaEgg egg = (TileEntityMoaEgg) this.world.getTileEntity(this.eggPos);

			if (egg != null)
			{
				MoaGenePool teGenes = egg.getGenePool();
				MoaGenePool entityGenes = this.moa.getGenePool();

				teGenes.transformFromParents(entityGenes.getStorage().getSeed(), entityGenes.getStorage().getFatherSeed(), entityGenes.getStorage().getMotherSeed());

				egg.setFamilyNest(this.moa.getFamilyNest());

				if (!this.moa.getAnimalPack().hasLeader())
				{
					egg.setGender(AnimalGender.MALE);
				}
			}

			this.moa.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.moa.getRNG().nextFloat() - this.moa.getRNG().nextFloat()) * 0.2F + 1.0F);

			this.resetTimer();
		}
	}

	@Override
	public boolean continueExecuting()
	{
		return this.shouldExecute();
	}

}
