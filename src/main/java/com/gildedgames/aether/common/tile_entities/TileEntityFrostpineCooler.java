package com.gildedgames.aether.common.tile_entities;

import com.gildedgames.aether.api.capabilites.AetherCapabilities;
import com.gildedgames.aether.api.capabilites.items.properties.CoolingProperties;
import com.gildedgames.aether.common.containers.ContainerFrostpineCooler;
import com.gildedgames.aether.common.items.ItemsAether;
import com.gildedgames.aether.common.util.TickTimer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;

import javax.annotation.Nullable;

public class TileEntityFrostpineCooler extends TileEntityLockable implements ITickable, IInventory
{

    private static final int INVENTORY_SIZE = 7;

    private static final int AMBROSIUM_INDEX = 4;

    private static final int ITEM_TO_COOL_INDEX = 5;

    private static final int IRRADIATED_DUST_INDEX = 6;

    private ItemStack[] inventory;

    private int reqCoolingThreshold, currentCoolingProgress;

    private TickTimer progress = new TickTimer();

    public TileEntityFrostpineCooler()
    {
        this.inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public void update()
    {
        if (this.worldObj.isRemote)
        {
            return;
        }

        ItemStack itemToCool = this.getItemToCool();
        ItemStack ambrosium = this.getStackInSlot(AMBROSIUM_INDEX);
        ItemStack irradiatedDust = this.getStackInSlot(IRRADIATED_DUST_INDEX);

        if (itemToCool != null )
        {
            if (irradiatedDust == null)
            {
                CoolingProperties props = itemToCool.getCapability(AetherCapabilities.ITEM_PROPERTIES, null).getCoolingProperties();

                if (props != null && props.getCoolingThreshold(itemToCool) > 0 && ambrosium != null && ambrosium.stackSize >= 4)
                {
                    this.progress.tick();

                    if (this.progress.isMultipleOfSeconds())
                    {
                        this.currentCoolingProgress += this.getTotalCoolingValue();
                        this.sync();
                    }

                    if (this.currentCoolingProgress >= this.reqCoolingThreshold)
                    {
                        this.setInventorySlotContents(ITEM_TO_COOL_INDEX, props.getResultWhenCooled(itemToCool, this.worldObj.rand));

                        this.setInventorySlotContents(IRRADIATED_DUST_INDEX, new ItemStack(ItemsAether.irradiated_dust, 3 + this.worldObj.rand.nextInt(3)));

                        this.decrStackSize(AMBROSIUM_INDEX, 4);

                        this.currentCoolingProgress = 0;
                        this.reqCoolingThreshold = 0;
                        this.progress.reset();
                        this.sync();
                    }
                }
            }
        }
        else
        {
            this.currentCoolingProgress = 0;
            this.reqCoolingThreshold = 0;
            this.progress.reset();
            this.sync();
        }
    }

    public ItemStack getItemToCool()
    {
        return this.getStackInSlot(ITEM_TO_COOL_INDEX);
    }

    public boolean isCooling()
    {
        return this.getItemToCool() != null && this.reqCoolingThreshold > 0 && this.getStackInSlot(IRRADIATED_DUST_INDEX) == null && this.getStackInSlot(AMBROSIUM_INDEX) != null && this.getStackInSlot(AMBROSIUM_INDEX).stackSize >= 4;
    }

    public int getCurrentCoolingProgress()
    {
        return this.currentCoolingProgress;
    }

    public int getRequiredCoolingThreshold()
    {
        return this.reqCoolingThreshold;
    }

    public int getTotalCoolingValue()
    {
        int total = 0;

        for (int count = 0; count < AMBROSIUM_INDEX; count++)
        {
            ItemStack stack = this.getStackInSlot(count);

            if (stack == null)
            {
                continue;
            }

            CoolingProperties props = stack.getCapability(AetherCapabilities.ITEM_PROPERTIES, null).getCoolingProperties();

            if (props != null)
            {
                total += props.getCoolingStrength(stack) * stack.stackSize;
            }
        }

        return total;
    }

    @Override
    public int getSizeInventory()
    {
        return INVENTORY_SIZE;
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index)
    {
        if (index >= this.getSizeInventory())
        {
            return null;
        }

        return this.inventory[index];
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack stack = this.getStackInSlot(index);

        if (stack == null)
        {
            return null;
        }

        ItemStack copiedStack;

        if (stack.stackSize <= count)
        {
            copiedStack = stack;

            this.setInventorySlotContents(index, null);

            return copiedStack;
        }
        else
        {
            copiedStack = stack.splitStack(count);

            if (stack.stackSize == 0)
            {
                this.setInventorySlotContents(index, null);
            }

            return copiedStack;
        }
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack stack = this.getStackInSlot(index);

        if (stack != null)
        {
            this.setInventorySlotContents(index, null);

            return stack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack)
    {
        if (index >= this.getSizeInventory())
        {
            return;
        }

        this.inventory[index] = stack;

        if (index == ITEM_TO_COOL_INDEX)
        {
            if (stack == null)
            {
                this.reqCoolingThreshold = 0;
            }
            else
            {
                CoolingProperties props = stack.getCapability(AetherCapabilities.ITEM_PROPERTIES, null).getCoolingProperties();

                if (props != null)
                {
                    this.reqCoolingThreshold = props.getCoolingThreshold(stack);
                }
            }
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (stack == null)
        {
            return false;
        }

        CoolingProperties props = stack.getCapability(AetherCapabilities.ITEM_PROPERTIES, null).getCoolingProperties();

        if (index < AMBROSIUM_INDEX)
        {
            if (props != null && props.getCoolingStrength(stack) > 0)
            {
                return true;
            }
        }

        if (index == AMBROSIUM_INDEX)
        {
            return stack.getItem() == ItemsAether.ambrosium_shard;
        }

        if (index == ITEM_TO_COOL_INDEX)
        {
            return props != null && props.getCoolingThreshold(stack) > 0;
        }

        return false;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        this.inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public String getName()
    {
        return "container.frostpine_cooler";
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    public void sync()
    {
        IBlockState state = this.worldObj.getBlockState(this.pos);

        this.worldObj.notifyBlockUpdate(this.pos, state, state, 3);

        this.markDirty();
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound tag = super.getUpdateTag();

        this.writeToNBT(tag);

        return tag;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound compound = this.getUpdateTag();

        return new SPacketUpdateTileEntity(this.pos, 1, compound);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, SPacketUpdateTileEntity packet)
    {
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerFrostpineCooler(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return "aether:frostpine_cooler";
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        NBTTagList stackList = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound stackNBT = new NBTTagCompound();

                stackNBT.setByte("slot", (byte) i);

                this.inventory[i].writeToNBT(stackNBT);

                stackList.appendTag(stackNBT);
            }
        }

        compound.setTag("inventory", stackList);

        compound.setInteger("reqCoolingThreshold", this.reqCoolingThreshold);
        compound.setInteger("currentCoolingProgress", this.currentCoolingProgress);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        this.inventory = new ItemStack[INVENTORY_SIZE];

        NBTTagList stackList = compound.getTagList("inventory", 10);

        for (int i = 0; i < stackList.tagCount(); ++i)
        {
            NBTTagCompound stack = stackList.getCompoundTagAt(i);

            byte slotPos = stack.getByte("slot");

            if (slotPos >= 0 && slotPos < this.inventory.length)
            {
                this.inventory[slotPos] = ItemStack.loadItemStackFromNBT(stack);
            }
        }

        this.reqCoolingThreshold = compound.getInteger("reqCoolingThreshold");
        this.currentCoolingProgress = compound.getInteger("currentCoolingProgress");
    }

}
