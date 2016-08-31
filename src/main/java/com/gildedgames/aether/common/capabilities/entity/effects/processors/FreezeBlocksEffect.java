package com.gildedgames.aether.common.capabilities.entity.effects.processors;

import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectInstance;
import com.gildedgames.aether.api.capabilites.entity.effects.EntityEffectRule;
import com.gildedgames.aether.common.capabilities.entity.effects.AbstractEffectProcessor;
import com.gildedgames.aether.common.capabilities.entity.effects.processors.FreezeBlocksEffect.Instance;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

/**
 * Freezes appropriate blocks arounds the player when wearing the attached Accessory.
 * Unfreezes frozen locations once the player has moved away from them.
 * @author Brandon Pearce
 */
public class FreezeBlocksEffect extends AbstractEffectProcessor<Instance>
{

	public static class Instance extends EntityEffectInstance
	{

		public Instance(int radius, EntityEffectRule... rules)
		{
			super(rules);

			this.getAttributes().setInteger("radius", radius);
		}

		@Override
		public EntityEffectInstance cloneInstance()
		{
			return new Instance(this.getAttributes().getInteger("radius"), this.getRules());
		}

	}

	public FreezeBlocksEffect()
	{
		super("ability.freezeBlocks.localizedName", "ability.freezeBlocks.desc");
	}

	@Override
	public void apply(Entity source, Instance instance, List<Instance> all)
	{
	}

	@Override
	public void tick(Entity source, List<Instance> all)
	{
		World world = source.worldObj;

		int maxRadius = 0;

		for (Instance instance : all)
		{
			int radius = instance.getAttributes().getInteger("radius");

			if (radius > maxRadius)
			{
				maxRadius = radius;
			}
		}

		BlockPos pos = source.getPosition();

		if (source.onGround)
		{
			float radius = (float) Math.min(16, maxRadius);

			BlockPos.MutableBlockPos above = new BlockPos.MutableBlockPos(0, 0, 0);

			for (BlockPos.MutableBlockPos iPos : BlockPos.getAllInBoxMutable(pos.add(-radius, -1.0D, (double) (-radius)), pos.add((double) radius, -1.0D, (double) radius)))
			{
				if (iPos.distanceSqToCenter(source.posX, source.posY, source.posZ) <= (double) (radius * radius))
				{
					above.setPos(iPos.getX(), iPos.getY() + 1, iPos.getZ());

					IBlockState aboveState = source.worldObj.getBlockState(above);

					if (aboveState.getMaterial() == Material.AIR)
					{
						IBlockState iState = source.worldObj.getBlockState(iPos);

						if (iState.getMaterial() == Material.WATER && iState.getValue(BlockLiquid.LEVEL) == 0 &&
								source.worldObj.canBlockBePlaced(Blocks.FROSTED_ICE, iPos, false, EnumFacing.DOWN, null, null))
						{
							source.worldObj.setBlockState(iPos, Blocks.FROSTED_ICE.getDefaultState());
							source.worldObj.scheduleUpdate(iPos.toImmutable(), Blocks.FROSTED_ICE, MathHelper.getRandomIntegerInRange(world.rand, 60, 120));
						}
					}
				}
			}
		}
	}

}
