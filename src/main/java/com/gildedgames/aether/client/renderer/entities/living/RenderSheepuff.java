package com.gildedgames.aether.client.renderer.entities.living;

import com.gildedgames.aether.client.renderer.entities.living.layers.LayerSheepuffFur;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.entities.living.passive.EntitySheepuff;

import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSheepuff extends RenderLiving<EntitySheepuff>
{
	private static final ResourceLocation texture = AetherCore.getResource("textures/entities/sheepuff/sheepuff.png");

	public RenderSheepuff(RenderManager renderManager)
	{
		super(renderManager, new ModelSheep2(), 0.7f);

		this.addLayer(new LayerSheepuffFur(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySheepuff entity)
	{
		return texture;
	}
}
