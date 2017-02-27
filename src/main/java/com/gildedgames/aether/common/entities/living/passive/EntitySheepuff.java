package com.gildedgames.aether.common.entities.living.passive;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.entities.ai.sheepuff.EntityAIEatAetherGrass;
import com.gildedgames.aether.common.items.ItemsAether;
import com.google.common.collect.Sets;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySheepuff extends EntitySheep
{
	private static final DataParameter<Byte> DYE_COLOR = EntityDataManager.<Byte>createKey(EntitySheep.class, DataSerializers.BYTE);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT, ItemsAether.blueberries, ItemsAether.orange, ItemsAether.enchanted_blueberry, ItemsAether.enchanted_wyndberry, ItemsAether.wyndberry);
	private EntityAIEatAetherGrass entityAIEatGrass;
	private int sheepTimer;
	
	public EntitySheepuff(World world)
	{
		super(world);

		this.spawnableBlock = BlocksAether.aether_grass;
	}
	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
		entityAIEatGrass = new EntityAIEatAetherGrass(this);
		super.tasks.addTask(3, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
		super.tasks.addTask(5, entityAIEatGrass);
	}
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != null && TEMPTATION_ITEMS.contains(stack.getItem());
	}
	@Override
	protected void updateAITasks()
	{
		this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}
	@Override
	public void onLivingUpdate()
	{
		if (this.worldObj.isRemote)
		{
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}

		super.onLivingUpdate();
	}
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(DYE_COLOR, Byte.valueOf((byte)0));
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
		{
			this.sheepTimer = 40;
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}
	@SideOnly(Side.CLIENT)
	@Override
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.sheepTimer <= 0 ? 0.0F : (this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0F : (this.sheepTimer < 4 ? ((float)this.sheepTimer - p_70894_1_) / 4.0F : -((float)(this.sheepTimer - 40) - p_70894_1_) / 4.0F));
	}
	@SideOnly(Side.CLIENT)
	@Override
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.sheepTimer > 4 && this.sheepTimer <= 36)
		{
			float f = ((float)(this.sheepTimer - 4) - p_70890_1_) / 32.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
		}
		else
		{
			return this.sheepTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Puffed", this.getPuffed());
	}
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setPuffed(compound.getBoolean("Puffed"));
	}
	@Override
	public EnumDyeColor getFleeceColor()
	{
		return EnumDyeColor.byMetadata(((Byte)this.dataManager.get(DYE_COLOR)).byteValue() & 15);
	}
	@Override
	public void setFleeceColor(EnumDyeColor color)
	{
		byte b0 = ((Byte)this.dataManager.get(DYE_COLOR)).byteValue();
		this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 & 240 | color.getMetadata() & 15)));
	}
	@Override
	public boolean getSheared()
	{
		return (((Byte)this.dataManager.get(DYE_COLOR)).byteValue() & 16) != 0;
	}
	@Override
	public void setSheared(boolean sheared)
	{
		byte b0 = ((Byte)this.dataManager.get(DYE_COLOR)).byteValue();

		if (sheared)
		{
			this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 | 16)));
		}
		else
		{
			this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 & -17)));
		}
	}
	public boolean getPuffed()
	{
		return (((Byte)this.dataManager.get(DYE_COLOR)).byteValue() & 32) != 0;
	}
	public void setPuffed(boolean puffed)
	{
		byte b0 = ((Byte)this.dataManager.get(DYE_COLOR)).byteValue();

		if (puffed)
		{
			this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 | 32)));
		}
		else
		{
			this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 & -33)));
		}
	}
	public static EnumDyeColor getRandomSheepColor(Random random)
	{
		int i = random.nextInt(100);
		return i < 2 ? EnumDyeColor.YELLOW : (i < 6 ? EnumDyeColor.LIME : (i < 14 ? EnumDyeColor.PURPLE : (i < 26 ? EnumDyeColor.LIGHT_BLUE : EnumDyeColor.WHITE)));
	}
	@Override
	public EntitySheepuff createChild(EntityAgeable ageable)
	{
		return new EntitySheepuff(this.worldObj);
	}
	@Override
	public void eatGrassBonus()
	{
		if (this.isChild())
		{
			this.addGrowth(60);
		}
		else if (!this.getSheared())
		{
			this.setPuffed(true);
		}
		this.setSheared(false);
	}
	@Override
	protected void jump()
    {
		if(this.getPuffed())
		{
			this.motionY = 1.8D;
			this.motionX += this.rand.nextGaussian() * 0.5D;
			this.motionZ += this.rand.nextGaussian() * 0.5D;
		}
		else
		{
			super.jump();
		}
    }
	@Nullable
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		this.setFleeceColor(getRandomSheepColor(this.worldObj.rand));
		return livingdata;
	}
	@Override
	public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
	{
		boolean wasPuffed = this.getPuffed();
		if (wasPuffed)
		{
			this.setPuffed(false);
		}
		else
		{
			this.setSheared(true);
		}
		int i = 1 + this.rand.nextInt(3);

		java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
		for (int j = 0; j < i; ++j)
			ret.add(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, this.getFleeceColor().getMetadata()));

		this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
		if (!wasPuffed)
		{
			this.setFleeceColor(EnumDyeColor.WHITE);
		}
		return ret;
	}
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(this.getPuffed())
		{
			this.fallDistance = 0;

			if(this.motionY < -0.05D)
			{
				this.motionY = -0.05D;
			}
		}
	}
}
