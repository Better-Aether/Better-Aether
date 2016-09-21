package com.gildedgames.aether.client.models.entities.attachments;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBattleBomb extends ModelBase
{
	//fields
	public ModelRenderer bomb_main;
	public ModelRenderer bomb_sub;

	public ModelBattleBomb()
	{
		textureWidth = 128;
		textureHeight = 64;

		bomb_main = new ModelRenderer(this, 0, 0);
		bomb_main.addBox(-3.5F, -2.5F, -2.5F, 7, 5, 5);
		bomb_main.setRotationPoint(0F, 0F, 0F);
		bomb_main.setTextureSize(128, 64);
		bomb_main.mirror = true;
		setRotation(bomb_main, 0F, 0F, 0F);
		bomb_sub = new ModelRenderer(this, 0, 0);
		bomb_sub.addBox(-5.5F, -2F, -2F, 11, 4, 4);
		bomb_sub.setRotationPoint(0F, 0F, 0F);
		bomb_sub.setTextureSize(128, 64);
		bomb_sub.mirror = true;
		setRotation(bomb_sub, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bomb_main.render(f5);
		bomb_sub.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}

