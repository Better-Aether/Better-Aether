package com.gildedgames.aether.client.renderer.entities.living.layers;

import com.gildedgames.aether.client.models.entities.living.ModelSheepuff;
import com.gildedgames.aether.client.renderer.entities.living.RenderSheepuff;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.entities.living.passive.EntitySheepuff;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class LayerSheepuffFur implements LayerRenderer
{
	private static final ResourceLocation texture = AetherCore.getResource("textures/entities/sheepuff/fur.png");

	private final ModelSheep1 sheepModel = new ModelSheep1();
	
	private final ModelSheepuff sheepuffModel = new ModelSheepuff();

	private final RenderSheepuff render;

	public LayerSheepuffFur(RenderSheepuff render)
	{
		this.render = render;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
	{
		EntitySheepuff sheepuff = (EntitySheepuff) entity;

		if (!sheepuff.getSheared() && !sheepuff.isInvisible())
		{
			if (entity.hasCustomName() && "jeb_".equals(entity.getCustomNameTag()))
            {
                int i = entity.ticksExisted / 25 + entity.getEntityId();
                int j = EnumDyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f = ((float)(entity.ticksExisted % 25) + p_177141_4_) / 25.0F;
                float[] afloat1 = EntitySheepuff.getDyeRgb(EnumDyeColor.byMetadata(k));
                float[] afloat2 = EntitySheepuff.getDyeRgb(EnumDyeColor.byMetadata(l));
                GlStateManager.color(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
            }
            else
            {
            	float[] colors = EntitySheepuff.getDyeRgb(sheepuff.getFleeceColor());
            	GlStateManager.color(colors[0], colors[1], colors[2]);
            }

			this.render.bindTexture(texture);

			if (sheepuff.getPuffed())
			{
				this.sheepuffModel.setModelAttributes(this.render.getMainModel());
				this.sheepuffModel.setLivingAnimations(sheepuff, p_177141_2_, p_177141_3_, p_177141_4_);
				this.sheepuffModel.render(sheepuff, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
			}
			else
			{
				this.sheepModel.setModelAttributes(this.render.getMainModel());
				this.sheepModel.setLivingAnimations(sheepuff, p_177141_2_, p_177141_3_, p_177141_4_);
				this.sheepModel.render(sheepuff, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
			}
		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return true;
	}
}
