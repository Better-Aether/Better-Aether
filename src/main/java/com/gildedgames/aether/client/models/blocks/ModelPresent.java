package com.gildedgames.aether.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPresent extends ModelBase
{

	private final ModelRenderer box;

	private final ModelRenderer bowRight, bowLeft, bowMiddle;

	public ModelPresent()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.box = new ModelRenderer(this, 0, 12);
		this.box.addBox(-5F, -10F, -5F, 10, 10, 10);
		this.box.setRotationPoint(0F, 0F, 0F);
		this.box.setTextureSize(64, 32);
		this.box.mirror = true;
		this.setRotation(this.box, 0F, 0.7853982F, 0F);

		this.bowRight = new ModelRenderer(this, 4, 5);
		this.bowRight.addBox(-8F, -10.5F, -1.5F, 5, 1, 3);
		this.bowRight.setRotationPoint(0F, 0F, 0F);
		this.bowRight.setTextureSize(64, 32);
		this.bowRight.mirror = true;
		this.setRotation(this.bowRight, 0F, 0F, 0.2617994F);

		this.bowLeft = new ModelRenderer(this, 20, 5);
		this.bowLeft.addBox(3F, -10.5F, -1.5F, 5, 1, 3);
		this.bowLeft.setRotationPoint(0F, 0F, 0F);
		this.bowLeft.setTextureSize(64, 32);
		this.bowLeft.mirror = true;
		this.setRotation(this.bowLeft, 0F, 0F, -0.2617994F);

		this.bowMiddle = new ModelRenderer(this, 16, 9);
		this.bowMiddle.addBox(-1F, -11F, -1F, 2, 1, 2);
		this.bowMiddle.setRotationPoint(0F, 0F, 0F);
		this.bowMiddle.setTextureSize(64, 32);
		this.bowMiddle.mirror = true;
		this.setRotation(this.bowMiddle, 0F, 0F, 0F);
	}

	public void renderBow(float scale)
	{
		this.bowRight.render(scale);
		this.bowLeft.render(scale);
		this.bowMiddle.render(scale);
	}

	public void renderBox(float scale)
	{
		this.box.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
