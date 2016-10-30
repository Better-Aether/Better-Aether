package com.gildedgames.aether.client.renderer.tile_entities;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.blocks.containers.BlockSkyrootChest;
import com.gildedgames.aether.common.tiles.TileEntitySkyrootChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntitySkyrootChestRenderer extends TileEntitySpecialRenderer<TileEntitySkyrootChest>
{
	private static final ResourceLocation textureSingle = AetherCore.getResource("textures/tile_entities/skyroot_chest_large.png");

	private static final ResourceLocation textureDouble = AetherCore.getResource("textures/tile_entities/skyroot_chest.png");

	// This is a stupid hack. See TileEntityItemStackRenderer.
	private static final TileEntitySkyrootChest nullChest = new TileEntitySkyrootChest();

	private ModelChest simpleChest = new ModelChest(), largeChest = new ModelLargeChest();

	@Override
	public void renderTileEntityAt(TileEntitySkyrootChest chest, double x, double y, double z, float partialTicks, int destroyStage)
	{
		int metadata;

		if (chest == null)
		{
			chest = nullChest;
		}

		if (!chest.hasWorldObj())
		{
			metadata = 0;
		}
		else
		{
			Block block = chest.getBlockType();
			metadata = chest.getBlockMetadata();

			if (block instanceof BlockSkyrootChest && metadata == 0)
			{
				((BlockChest) block).checkForSurroundingChests(chest.getWorld(), chest.getPos(), chest.getWorld().getBlockState(chest.getPos()));

				metadata = chest.getBlockMetadata();
			}

			chest.checkForAdjacentChests();
		}

		if (chest.adjacentChestZNeg == null && chest.adjacentChestXNeg == null)
		{
			ModelChest modelchest;

			if (chest.adjacentChestXPos == null && chest.adjacentChestZPos == null)
			{
				modelchest = this.simpleChest;

				if (destroyStage >= 0)
				{
					this.bindTexture(DESTROY_STAGES[destroyStage]);

					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(4.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				}
				else
				{
					this.bindTexture(textureDouble);
				}
			}
			else
			{
				modelchest = this.largeChest;

				if (destroyStage >= 0)
				{
					this.bindTexture(DESTROY_STAGES[destroyStage]);

					GlStateManager.matrixMode(GL11.GL_TEXTURE);

					GlStateManager.pushMatrix();
					GlStateManager.scale(8.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(GL11.GL_MODELVIEW);
				}
				else
				{
					this.bindTexture(textureSingle);
				}
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();

			if (destroyStage < 0)
			{
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			}

			GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.translate(0.5F, 0.5F, 0.5F);

			float angle = 0;

			switch (metadata)
			{
			case 2:
				angle = 180;
				break;
			case 3:
				angle = 0;
				break;
			case 4:
				angle = 90;
				break;
			case 5:
				angle = -90;
				break;
			}

			if (metadata == 2 && chest.adjacentChestXPos != null)
			{
				GlStateManager.translate(1.0F, 0.0F, 0.0F);
			}

			if (metadata == 5 && chest.adjacentChestZPos != null)
			{
				GlStateManager.translate(0.0F, 0.0F, -1.0F);
			}

			GlStateManager.rotate(angle, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);

			float lidAngle = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partialTicks;
			float adjacentLidAngle;

			if (chest.adjacentChestZNeg != null)
			{
				adjacentLidAngle = chest.adjacentChestZNeg.prevLidAngle + (chest.adjacentChestZNeg.lidAngle - chest.adjacentChestZNeg.prevLidAngle) * partialTicks;

				if (adjacentLidAngle > lidAngle)
				{
					lidAngle = adjacentLidAngle;
				}
			}

			if (chest.adjacentChestXNeg != null)
			{
				adjacentLidAngle = chest.adjacentChestXNeg.prevLidAngle + (chest.adjacentChestXNeg.lidAngle - chest.adjacentChestXNeg.prevLidAngle) * partialTicks;

				if (adjacentLidAngle > lidAngle)
				{
					lidAngle = adjacentLidAngle;
				}
			}

			lidAngle = 1.0F - lidAngle;
			lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;

			modelchest.chestLid.rotateAngleX = -(lidAngle * (float) Math.PI / 2.0F);
			modelchest.renderAll();

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			if (destroyStage >= 0)
			{
				GlStateManager.matrixMode(GL11.GL_TEXTURE);
				GlStateManager.popMatrix();
				GlStateManager.matrixMode(GL11.GL_MODELVIEW);
			}
		}
	}
}
