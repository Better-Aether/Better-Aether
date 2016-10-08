package com.gildedgames.aether.common.containers.slots;

import com.gildedgames.aether.common.AetherCore;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SlotFlintAndSteel extends Slot
{

    private static TextureAtlasSprite sprite;

    public SlotFlintAndSteel(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean isItemValid(@Nullable ItemStack stack)
    {
        return this.inventory.isItemValidForSlot(this.getSlotIndex(), stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getBackgroundSprite()
    {
        return sprite;
    }

    @SideOnly(Side.CLIENT)
    public static void registerIcons(TextureStitchEvent.Pre event)
    {
        sprite = event.getMap().registerSprite(AetherCore.getResource("gui/slots/slot_flint_and_steel"));
    }

}
