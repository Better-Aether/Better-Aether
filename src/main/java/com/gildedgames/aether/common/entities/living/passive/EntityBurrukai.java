package com.gildedgames.aether.common.entities.living.passive;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.registry.content.LootTablesAether;
import com.gildedgames.aether.common.registry.content.SoundsAether;
import com.google.common.collect.Sets;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityBurrukai extends EntityAetherAnimal
{

	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT, ItemsAether.blueberries, ItemsAether.orange, ItemsAether.enchanted_blueberry, ItemsAether.enchanted_wyndberry, ItemsAether.wyndberry);

	public EntityBurrukai(World world)
	{
		super(world);

		this.setSize(1.5F, 2.0F);

		this.spawnableBlock = BlocksAether.aether_grass;
	}

	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();

		this.tasks.addTask(3, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
	}

	@Override
	public EntityBurrukai createChild(EntityAgeable ageable)
	{
		return new EntityBurrukai(this.worldObj);
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != null && TEMPTATION_ITEMS.contains(stack.getItem());
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundsAether.burrukai_ambient;
	}

	@Override
	protected SoundEvent getHurtSound()
	{
		return SoundsAether.burrukai_hurt;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundsAether.burrukai_death;
	}

	protected float getSoundVolume()
	{
		return 0.6F;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return LootTablesAether.ENTITY_TAEGORE;
	}

}
