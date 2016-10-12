package com.gildedgames.aether.client.models.entities.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBattleSentry extends ModelBase
{

	ModelRenderer Blade;

	ModelRenderer body_main;

	ModelRenderer body_main_top;

	ModelRenderer body_main_bottom;

	ModelRenderer body_main_back_top;

	ModelRenderer body_main_back_bottom;

	ModelRenderer body_eye;

	ModelRenderer body_crest_top;

	ModelRenderer body_crest_left_top;

	ModelRenderer body_crest_right_top;

	ModelRenderer body_crest_left_bottom;

	ModelRenderer body_crest_right_bottom;

	ModelRenderer body_jaw_mid;

	ModelRenderer body_main_jaw_left;

	ModelRenderer body_main_jaw_right;

	ModelRenderer body_left_shoulder_main;

	ModelRenderer body_left_shoulder_ridge_front;

	ModelRenderer body_left_shoulder_right_back;

	ModelRenderer body_left_shoulder_ridge_top;

	ModelRenderer body_right_shoulder_main;

	ModelRenderer body_right_shoulder_ridge_front;

	ModelRenderer body_right_shoulder_ridge_back;

	ModelRenderer body_right_shoulder_ridge_top;

	ModelRenderer leg_left_joint;

	ModelRenderer leg_left_main;

	ModelRenderer leg_left_crest_top;

	ModelRenderer leg_left_crest_mid;

	ModelRenderer leg_left_main_bottom;

	ModelRenderer foot_left_1;

	ModelRenderer foot_left_2;

	ModelRenderer foot_left_3;

	ModelRenderer leg_right_joint;

	ModelRenderer leg_right_main;

	ModelRenderer leg_right_crest_top;

	ModelRenderer leg_right_crest_mid;

	ModelRenderer leg_right_main_bottom;

	ModelRenderer foot_right_1;

	ModelRenderer foot_right_2;

	ModelRenderer foot_right_3;

	public ModelBattleSentry()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.Blade = new ModelRenderer(this, 0, 0);
		this.Blade.addBox(0F, 0F, -11F, 11, 1, 11);
		this.Blade.setRotationPoint(0F, 23F, -1F);
		this.Blade.setTextureSize(64, 32);
		this.Blade.mirror = true;
		this.setRotation(this.Blade, 0F, 0.7853982F, 0F);
		this.body_main = new ModelRenderer(this, 0, 0);
		this.body_main.addBox(-6F, 13F, -6F, 12, 8, 12);
		this.body_main.setRotationPoint(0F, 0F, 0F);
		this.body_main.setTextureSize(64, 32);
		this.body_main.mirror = true;
		this.setRotation(this.body_main, 0F, 0F, 0F);
		this.body_main_top = new ModelRenderer(this, 0, 0);
		this.body_main_top.addBox(-6.5F, 10F, -7F, 13, 3, 16);
		this.body_main_top.setRotationPoint(0F, 0F, 0F);
		this.body_main_top.setTextureSize(64, 32);
		this.body_main_top.mirror = true;
		this.setRotation(this.body_main_top, 0F, 0F, 0F);
		this.body_main_bottom = new ModelRenderer(this, 0, 0);
		this.body_main_bottom.addBox(-6.5F, 21F, -8F, 13, 3, 17);
		this.body_main_bottom.setRotationPoint(0F, 0F, 0F);
		this.body_main_bottom.setTextureSize(64, 32);
		this.body_main_bottom.mirror = true;
		this.setRotation(this.body_main_bottom, 0F, 0F, 0F);
		this.body_main_back_top = new ModelRenderer(this, 0, 0);
		this.body_main_back_top.addBox(-5.5F, 2.5F, 13F, 11, 4, 2);
		this.body_main_back_top.setRotationPoint(0F, 0F, 0F);
		this.body_main_back_top.setTextureSize(64, 32);
		this.body_main_back_top.mirror = true;
		this.setRotation(this.body_main_back_top, -0.7853982F, 0F, 0F);
		this.body_main_back_bottom = new ModelRenderer(this, 0, 0);
		this.body_main_back_bottom.addBox(-5.5F, 17.5F, -11F, 11, 4, 2);
		this.body_main_back_bottom.setRotationPoint(0F, 0F, 0F);
		this.body_main_back_bottom.setTextureSize(64, 32);
		this.body_main_back_bottom.mirror = true;
		this.setRotation(this.body_main_back_bottom, 0.7853982F, 0F, 0F);
		this.body_eye = new ModelRenderer(this, 0, 0);
		this.body_eye.addBox(-5.5F, 12F, -8F, 11, 11, 2);
		this.body_eye.setRotationPoint(0F, 0F, 0F);
		this.body_eye.setTextureSize(64, 32);
		this.body_eye.mirror = true;
		this.setRotation(this.body_eye, 0F, 0F, 0F);
		this.body_crest_top = new ModelRenderer(this, 0, 0);
		this.body_crest_top.addBox(-4F, -2F, -1F, 8, 3, 13);
		this.body_crest_top.setRotationPoint(0F, 12F, -8F);
		this.body_crest_top.setTextureSize(64, 32);
		this.body_crest_top.mirror = true;
		this.setRotation(this.body_crest_top, 0.9599311F, 0F, 0F);
		this.body_crest_left_top = new ModelRenderer(this, 0, 0);
		this.body_crest_left_top.addBox(-1F, -3F, -1F, 8, 5, 2);
		this.body_crest_left_top.setRotationPoint(5F, 14F, -8F);
		this.body_crest_left_top.setTextureSize(64, 32);
		this.body_crest_left_top.mirror = true;
		this.setRotation(this.body_crest_left_top, -0.2094395F, -0.5235988F, -0.6108652F);
		this.body_crest_right_top = new ModelRenderer(this, 0, 0);
		this.body_crest_right_top.addBox(-7F, -3F, -1F, 8, 5, 2);
		this.body_crest_right_top.setRotationPoint(-5F, 14F, -8F);
		this.body_crest_right_top.setTextureSize(64, 32);
		this.body_crest_right_top.mirror = true;
		this.setRotation(this.body_crest_right_top, -0.2094395F, 0.5235988F, 0.6108652F);
		this.body_crest_left_bottom = new ModelRenderer(this, 0, 0);
		this.body_crest_left_bottom.addBox(-0.5F, -2.5F, -0.5F, 5, 5, 2);
		this.body_crest_left_bottom.setRotationPoint(6F, 18F, -8F);
		this.body_crest_left_bottom.setTextureSize(64, 32);
		this.body_crest_left_bottom.mirror = true;
		this.setRotation(this.body_crest_left_bottom, 0F, -0.3490659F, 0F);
		this.body_crest_right_bottom = new ModelRenderer(this, 0, 0);
		this.body_crest_right_bottom.addBox(-0.5F, -2.5F, -1.5F, 5, 4, 2);
		this.body_crest_right_bottom.setRotationPoint(-6F, 18F, -8F);
		this.body_crest_right_bottom.setTextureSize(64, 32);
		this.body_crest_right_bottom.mirror = true;
		this.setRotation(this.body_crest_right_bottom, 0F, -2.792527F, 0F);
		this.body_jaw_mid = new ModelRenderer(this, 0, 0);
		this.body_jaw_mid.addBox(2F, 20F, -10F, 8, 3, 8);
		this.body_jaw_mid.setRotationPoint(0F, 0F, 0F);
		this.body_jaw_mid.setTextureSize(64, 32);
		this.body_jaw_mid.mirror = true;
		this.setRotation(this.body_jaw_mid, 0F, 0.7853982F, 0F);
		this.body_main_jaw_left = new ModelRenderer(this, 0, 0);
		this.body_main_jaw_left.addBox(-2F, -2F, -1F, 3, 6, 3);
		this.body_main_jaw_left.setRotationPoint(6F, 18F, -8F);
		this.body_main_jaw_left.setTextureSize(64, 32);
		this.body_main_jaw_left.mirror = true;
		this.setRotation(this.body_main_jaw_left, 0.0872665F, -0.3490659F, 0.5235988F);
		this.body_main_jaw_right = new ModelRenderer(this, 0, 0);
		this.body_main_jaw_right.addBox(-1F, -2F, -1F, 3, 6, 3);
		this.body_main_jaw_right.setRotationPoint(-6F, 18F, -8F);
		this.body_main_jaw_right.setTextureSize(64, 32);
		this.body_main_jaw_right.mirror = true;
		this.setRotation(this.body_main_jaw_right, 0.0872665F, 0.3490659F, -0.5235988F);
		this.body_left_shoulder_main = new ModelRenderer(this, 0, 0);
		this.body_left_shoulder_main.addBox(5.5F, 8F, 10F, 2, 6, 6);
		this.body_left_shoulder_main.setRotationPoint(0F, 0F, 0F);
		this.body_left_shoulder_main.setTextureSize(64, 32);
		this.body_left_shoulder_main.mirror = true;
		this.setRotation(this.body_left_shoulder_main, -0.7853982F, 0F, 0F);
		this.body_left_shoulder_ridge_front = new ModelRenderer(this, 0, 0);
		this.body_left_shoulder_ridge_front.addBox(5.5F, 8F, 8F, 3, 7, 2);
		this.body_left_shoulder_ridge_front.setRotationPoint(0F, 0F, 0F);
		this.body_left_shoulder_ridge_front.setTextureSize(64, 32);
		this.body_left_shoulder_ridge_front.mirror = true;
		this.setRotation(this.body_left_shoulder_ridge_front, -0.7853982F, 0F, 0F);
		this.body_left_shoulder_right_back = new ModelRenderer(this, 0, 0);
		this.body_left_shoulder_right_back.addBox(1.5F, 10F, -8F, 7, 7, 2);
		this.body_left_shoulder_right_back.setRotationPoint(0F, 0F, 0F);
		this.body_left_shoulder_right_back.setTextureSize(64, 32);
		this.body_left_shoulder_right_back.mirror = true;
		this.setRotation(this.body_left_shoulder_right_back, 0.7853982F, 0F, 0F);
		this.body_left_shoulder_ridge_top = new ModelRenderer(this, 0, 0);
		this.body_left_shoulder_ridge_top.addBox(7.5F, 5F, 0F, 7, 3, 3);
		this.body_left_shoulder_ridge_top.setRotationPoint(0F, 0F, 0F);
		this.body_left_shoulder_ridge_top.setTextureSize(64, 32);
		this.body_left_shoulder_ridge_top.mirror = true;
		this.setRotation(this.body_left_shoulder_ridge_top, 0F, 0F, 0.5235988F);
		this.body_right_shoulder_main = new ModelRenderer(this, 0, 0);
		this.body_right_shoulder_main.addBox(-7.5F, 8F, 10F, 2, 6, 6);
		this.body_right_shoulder_main.setRotationPoint(0F, 0F, 0F);
		this.body_right_shoulder_main.setTextureSize(64, 32);
		this.body_right_shoulder_main.mirror = true;
		this.setRotation(this.body_right_shoulder_main, -0.7853982F, 0F, 0F);
		this.body_right_shoulder_ridge_front = new ModelRenderer(this, 0, 0);
		this.body_right_shoulder_ridge_front.addBox(-8.5F, 8F, 8F, 3, 7, 2);
		this.body_right_shoulder_ridge_front.setRotationPoint(0F, 0F, 0F);
		this.body_right_shoulder_ridge_front.setTextureSize(64, 32);
		this.body_right_shoulder_ridge_front.mirror = true;
		this.setRotation(this.body_right_shoulder_ridge_front, -0.7853982F, 0F, 0F);
		this.body_right_shoulder_ridge_back = new ModelRenderer(this, 0, 0);
		this.body_right_shoulder_ridge_back.addBox(-8.5F, 10F, -8F, 7, 7, 2);
		this.body_right_shoulder_ridge_back.setRotationPoint(0F, 0F, 0F);
		this.body_right_shoulder_ridge_back.setTextureSize(64, 32);
		this.body_right_shoulder_ridge_back.mirror = true;
		this.setRotation(this.body_right_shoulder_ridge_back, 0.7853982F, 0F, 0F);
		this.body_right_shoulder_ridge_top = new ModelRenderer(this, 0, 0);
		this.body_right_shoulder_ridge_top.addBox(-14.5F, 5F, 0F, 7, 3, 3);
		this.body_right_shoulder_ridge_top.setRotationPoint(0F, 0F, 0F);
		this.body_right_shoulder_ridge_top.setTextureSize(64, 32);
		this.body_right_shoulder_ridge_top.mirror = true;
		this.setRotation(this.body_right_shoulder_ridge_top, 0F, 0F, -0.5235988F);
		this.leg_left_joint = new ModelRenderer(this, 0, 0);
		this.leg_left_joint.addBox(0.5F, -1.5F, -1.5F, 1, 3, 3);
		this.leg_left_joint.setRotationPoint(7F, 17F, 1.5F);
		this.leg_left_joint.setTextureSize(64, 32);
		this.leg_left_joint.mirror = true;
		this.setRotation(this.leg_left_joint, 0F, 0F, 0F);
		this.leg_left_main = new ModelRenderer(this, 0, 0);
		this.leg_left_main.addBox(1.5F, -8F, -4F, 4, 10, 8);
		this.leg_left_main.setRotationPoint(7F, 17F, 1.5F);
		this.leg_left_main.setTextureSize(64, 32);
		this.leg_left_main.mirror = true;
		this.setRotation(this.leg_left_main, 0F, 0F, 0F);
		this.leg_left_crest_top = new ModelRenderer(this, 0, 0);
		this.leg_left_crest_top.addBox(2F, -8F, 1F, 3, 5, 10);
		this.leg_left_crest_top.setRotationPoint(7F, 17F, 1.5F);
		this.leg_left_crest_top.setTextureSize(64, 32);
		this.leg_left_crest_top.mirror = true;
		this.setRotation(this.leg_left_crest_top, 0.3490659F, 0F, 0F);
		this.leg_left_crest_mid = new ModelRenderer(this, 0, 0);
		this.leg_left_crest_mid.addBox(2.5F, -10F, -2F, 2, 13, 6);
		this.leg_left_crest_mid.setRotationPoint(7F, 17F, 1.5F);
		this.leg_left_crest_mid.setTextureSize(64, 32);
		this.leg_left_crest_mid.mirror = true;
		this.setRotation(this.leg_left_crest_mid, -0.5235988F, 0F, 0F);
		this.leg_left_main_bottom = new ModelRenderer(this, 0, 0);
		this.leg_left_main_bottom.addBox(2F, 2F, 0F, 3, 2, 4);
		this.leg_left_main_bottom.setRotationPoint(7F, 17F, 1.5F);
		this.leg_left_main_bottom.setTextureSize(64, 32);
		this.leg_left_main_bottom.mirror = true;
		this.setRotation(this.leg_left_main_bottom, 0F, 0F, 0F);
		this.foot_left_1 = new ModelRenderer(this, 0, 0);
		this.foot_left_1.addBox(0.5F, 4F, -2F, 6, 3, 6);
		this.foot_left_1.setRotationPoint(7F, 17F, 1.5F);
		this.foot_left_1.setTextureSize(64, 32);
		this.foot_left_1.mirror = true;
		this.setRotation(this.foot_left_1, 0F, -0.1745329F, 0F);
		this.foot_left_2 = new ModelRenderer(this, 0, 0);
		this.foot_left_2.addBox(2F, 4F, -12F, 3, 2, 10);
		this.foot_left_2.setRotationPoint(7F, 17F, 1.5F);
		this.foot_left_2.setTextureSize(64, 32);
		this.foot_left_2.mirror = true;
		this.setRotation(this.foot_left_2, 0.0872665F, -0.1745329F, 0F);
		this.foot_left_3 = new ModelRenderer(this, 0, 0);
		this.foot_left_3.addBox(1.8F, 3.8F, -16F, 4, 3, 4);
		this.foot_left_3.setRotationPoint(7F, 17F, 1.5F);
		this.foot_left_3.setTextureSize(64, 32);
		this.foot_left_3.mirror = true;
		this.setRotation(this.foot_left_3, 0.0872665F, -0.1745329F, 0F);
		this.leg_right_joint = new ModelRenderer(this, 0, 0);
		this.leg_right_joint.addBox(-0.5F, -1.5F, -1.5F, 1, 3, 3);
		this.leg_right_joint.setRotationPoint(-8F, 17F, 1.5F);
		this.leg_right_joint.setTextureSize(64, 32);
		this.leg_right_joint.mirror = true;
		this.setRotation(this.leg_right_joint, 0F, 0F, 0F);
		this.leg_right_main = new ModelRenderer(this, 0, 0);
		this.leg_right_main.addBox(-4.5F, -8F, -4F, 4, 10, 8);
		this.leg_right_main.setRotationPoint(-8F, 17F, 1.5F);
		this.leg_right_main.setTextureSize(64, 32);
		this.leg_right_main.mirror = true;
		this.setRotation(this.leg_right_main, 0F, 0F, 0F);
		this.leg_right_crest_top = new ModelRenderer(this, 0, 0);
		this.leg_right_crest_top.addBox(-4F, -8F, 1F, 3, 5, 10);
		this.leg_right_crest_top.setRotationPoint(-8F, 17F, 1.5F);
		this.leg_right_crest_top.setTextureSize(64, 32);
		this.leg_right_crest_top.mirror = true;
		this.setRotation(this.leg_right_crest_top, 0.3490659F, 0F, 0F);
		this.leg_right_crest_mid = new ModelRenderer(this, 0, 0);
		this.leg_right_crest_mid.addBox(-3.5F, -10F, -2F, 2, 13, 6);
		this.leg_right_crest_mid.setRotationPoint(-8F, 17F, 1.5F);
		this.leg_right_crest_mid.setTextureSize(64, 32);
		this.leg_right_crest_mid.mirror = true;
		this.setRotation(this.leg_right_crest_mid, -0.5235988F, 0F, 0F);
		this.leg_right_main_bottom = new ModelRenderer(this, 0, 0);
		this.leg_right_main_bottom.addBox(-4F, 2F, 0F, 3, 2, 4);
		this.leg_right_main_bottom.setRotationPoint(-8F, 17F, 1.5F);
		this.leg_right_main_bottom.setTextureSize(64, 32);
		this.leg_right_main_bottom.mirror = true;
		this.setRotation(this.leg_right_main_bottom, 0F, 0F, 0F);
		this.foot_right_1 = new ModelRenderer(this, 0, 0);
		this.foot_right_1.addBox(-5.5F, 4F, -2F, 6, 3, 6);
		this.foot_right_1.setRotationPoint(-8F, 17F, 1.5F);
		this.foot_right_1.setTextureSize(64, 32);
		this.foot_right_1.mirror = true;
		this.setRotation(this.foot_right_1, 0F, 0.1745329F, 0F);
		this.foot_right_2 = new ModelRenderer(this, 0, 0);
		this.foot_right_2.addBox(-4F, 4F, -12F, 3, 2, 10);
		this.foot_right_2.setRotationPoint(-8F, 17F, 1.5F);
		this.foot_right_2.setTextureSize(64, 32);
		this.foot_right_2.mirror = true;
		this.setRotation(this.foot_right_2, 0.0872665F, 0.1745329F, 0F);
		this.foot_right_3 = new ModelRenderer(this, 0, 0);
		this.foot_right_3.addBox(-4.8F, 3.8F, -16F, 4, 3, 4);
		this.foot_right_3.setRotationPoint(-8F, 17F, 1.5F);
		this.foot_right_3.setTextureSize(64, 32);
		this.foot_right_3.mirror = true;
		this.setRotation(this.foot_right_3, 0.0872665F, 0.1745329F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Blade.render(f5);
		this.body_main.render(f5);
		this.body_main_top.render(f5);
		this.body_main_bottom.render(f5);
		this.body_main_back_top.render(f5);
		this.body_main_back_bottom.render(f5);
		this.body_eye.render(f5);
		this.body_crest_top.render(f5);
		this.body_crest_left_top.render(f5);
		this.body_crest_right_top.render(f5);
		this.body_crest_left_bottom.render(f5);
		this.body_crest_right_bottom.render(f5);
		this.body_jaw_mid.render(f5);
		this.body_main_jaw_left.render(f5);
		this.body_main_jaw_right.render(f5);
		this.body_left_shoulder_main.render(f5);
		this.body_left_shoulder_ridge_front.render(f5);
		this.body_left_shoulder_right_back.render(f5);
		this.body_left_shoulder_ridge_top.render(f5);
		this.body_right_shoulder_main.render(f5);
		this.body_right_shoulder_ridge_front.render(f5);
		this.body_right_shoulder_ridge_back.render(f5);
		this.body_right_shoulder_ridge_top.render(f5);
		this.leg_left_joint.render(f5);
		this.leg_left_main.render(f5);
		this.leg_left_crest_top.render(f5);
		this.leg_left_crest_mid.render(f5);
		this.leg_left_main_bottom.render(f5);
		this.foot_left_1.render(f5);
		this.foot_left_2.render(f5);
		this.foot_left_3.render(f5);
		this.leg_right_joint.render(f5);
		this.leg_right_main.render(f5);
		this.leg_right_crest_top.render(f5);
		this.leg_right_crest_mid.render(f5);
		this.leg_right_main_bottom.render(f5);
		this.foot_right_1.render(f5);
		this.foot_right_2.render(f5);
		this.foot_right_3.render(f5);
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
