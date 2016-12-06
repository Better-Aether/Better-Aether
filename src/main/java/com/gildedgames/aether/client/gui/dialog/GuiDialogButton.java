package com.gildedgames.aether.client.gui.dialog;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.dialog.IDialogButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiDialogButton extends GuiButton
{

	private static final ResourceLocation DARK_OVERLAY = AetherCore.getResource("textures/gui/inventory/dark_slot_overlay.png");

	private IDialogButton buttonData;

	public GuiDialogButton(int buttonId, int x, int y, IDialogButton buttonData)
	{
		super(buttonId, x, y, 0, 15, "");

		this.buttonData = buttonData;
	}

	public IDialogButton getButtonData()
	{
		return this.buttonData;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (this.visible)
		{
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

			Gui.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, this.hovered ? Integer.MAX_VALUE : Integer.MIN_VALUE);

			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

			FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;

			String label = I18n.format(this.buttonData.getUnlocalizedLabel());

			this.width = fontRenderer.getStringWidth(label) + 5;

			this.drawString(fontRenderer, label, this.xPosition + 3, this.yPosition +3, 0xFFFFFF);
		}
	}

}
