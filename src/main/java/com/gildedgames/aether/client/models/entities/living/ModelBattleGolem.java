package com.gildedgames.aether.client.models.entities.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBattleGolem extends ModelBase
{
	//fields
	ModelRenderer head;

	ModelRenderer b_collar_front;

	ModelRenderer b_collar_front_left;

	ModelRenderer b_collar_front_right;

	ModelRenderer b_collar_left;

	ModelRenderer b_collar_right;

	ModelRenderer b_collar_back_left;

	ModelRenderer b_collar_back_right;

	ModelRenderer b_chest_top;

	ModelRenderer b_shoulder_left_front;

	ModelRenderer b_shoulder_left_mid;

	ModelRenderer b_shoulder_left_back;

	ModelRenderer b_shoulder_right_front;

	ModelRenderer b_shoulder_right_mid;

	ModelRenderer b_shoulder_right_back;

	ModelRenderer b_chest_main;

	ModelRenderer b_core;

	ModelRenderer b_crest_top;

	ModelRenderer b_crest_left_top;

	ModelRenderer b_crest_right_top;

	ModelRenderer b_crest_bottom;

	ModelRenderer b_crest_left;

	ModelRenderer b_crest_right;

	ModelRenderer b_chest_bottom;

	ModelRenderer b_back_top;

	ModelRenderer b_back_bottom;

	ModelRenderer b_waist;

	ModelRenderer t_mid_top;

	ModelRenderer t_mid_bottom;

	ModelRenderer t_left_cover_top;

	ModelRenderer t_left_cover_front_left;

	ModelRenderer t_left_cover_front_right;

	ModelRenderer t_left_cover_back_left;

	ModelRenderer t_left_cover_back_right;

	ModelRenderer t_left_rotor_top;

	ModelRenderer t_left_rotor_front;

	ModelRenderer t_left_rotor_back;

	ModelRenderer t_left_tread_front;

	ModelRenderer t_left_tread_back;

	ModelRenderer t_left_tread_bottom;

	ModelRenderer t_left_center_bottom;

	ModelRenderer t_left_center_mid;

	ModelRenderer t_left_center_top;

	ModelRenderer t_right_cover_top;

	ModelRenderer t_right_cover_front_left;

	ModelRenderer t_right_cover_front_right;

	ModelRenderer t_right_cover_back_left;

	ModelRenderer t_right_cover_back_right;

	ModelRenderer t_right_rotor_top;

	ModelRenderer t_right_rotor_front;

	ModelRenderer t_right_rotot_back;

	ModelRenderer t_right_tread_front;

	ModelRenderer t_right_tread_back;

	ModelRenderer t_right_tread_bottom;

	ModelRenderer t_right_center_bottom;

	ModelRenderer t_right_center_mid;

	ModelRenderer t_right_center_top;

	ModelRenderer l_a_joint;

	ModelRenderer l_a_top;

	ModelRenderer l_a_forearm;

	ModelRenderer l_a_finger_top_left;

	ModelRenderer l_a_finger_top_right;

	ModelRenderer l_a_finger_bottom_left;

	ModelRenderer l_a_finger_bottom_right;

	ModelRenderer r_a_joint;

	ModelRenderer r_a_top;

	ModelRenderer r_a_forearm;

	ModelRenderer r_a_finger_top_left;

	ModelRenderer r_a_finger_top_right;

	ModelRenderer r_a_finger_bottom_left;

	ModelRenderer r_a_finger_bottom_right;

	float throwingAngle = 1.0F;

	float restingAngle = 0.5F;

	float grabbingAngle = 1.5F;

	public ModelBattleGolem()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-6F, -8F, -5F, 12, 8, 10);
		this.head.setRotationPoint(0F, -20F, 1.5F);
		this.head.setTextureSize(128, 64);
		this.head.mirror = true;
		this.setRotation(this.head, 0F, 0F, 0F);
		this.b_collar_front = new ModelRenderer(this, 0, 0);
		this.b_collar_front.addBox(-5F, -22F, -3.5F, 10, 5, 3);
		this.b_collar_front.setRotationPoint(0F, 0F, 0F);
		this.b_collar_front.setTextureSize(128, 64);
		this.b_collar_front.mirror = true;
		this.setRotation(this.b_collar_front, 0.2617994F, 0F, 0F);
		this.b_collar_front_left = new ModelRenderer(this, 0, 0);
		this.b_collar_front_left.addBox(7F, -22F, -2F, 2, 3, 8);
		this.b_collar_front_left.setRotationPoint(0F, 0F, 0F);
		this.b_collar_front_left.setTextureSize(128, 64);
		this.b_collar_front_left.mirror = true;
		this.setRotation(this.b_collar_front_left, 0F, 0.7853982F, 0F);
		this.b_collar_front_right = new ModelRenderer(this, 0, 0);
		this.b_collar_front_right.addBox(-9F, -22F, -2F, 2, 3, 8);
		this.b_collar_front_right.setRotationPoint(0F, 0F, 0F);
		this.b_collar_front_right.setTextureSize(128, 64);
		this.b_collar_front_right.mirror = true;
		this.setRotation(this.b_collar_front_right, 0F, -0.7853982F, 0F);
		this.b_collar_left = new ModelRenderer(this, 0, 0);
		this.b_collar_left.addBox(8.5F, -23F, -2.5F, 3, 6, 8);
		this.b_collar_left.setRotationPoint(0F, 0F, 0F);
		this.b_collar_left.setTextureSize(128, 64);
		this.b_collar_left.mirror = true;
		this.setRotation(this.b_collar_left, 0F, 0F, 0F);
		this.b_collar_right = new ModelRenderer(this, 0, 0);
		this.b_collar_right.addBox(-11.5F, -23F, -2.5F, 3, 6, 8);
		this.b_collar_right.setRotationPoint(0F, 0F, 0F);
		this.b_collar_right.setTextureSize(128, 64);
		this.b_collar_right.mirror = true;
		this.setRotation(this.b_collar_right, 0F, 0F, 0F);
		this.b_collar_back_left = new ModelRenderer(this, 0, 0);
		this.b_collar_back_left.addBox(9F, -22F, -4F, 2, 3, 8);
		this.b_collar_back_left.setRotationPoint(0F, 0F, 0F);
		this.b_collar_back_left.setTextureSize(128, 64);
		this.b_collar_back_left.mirror = true;
		this.setRotation(this.b_collar_back_left, 0F, -0.7853982F, 0F);
		this.b_collar_back_right = new ModelRenderer(this, 0, 0);
		this.b_collar_back_right.addBox(-11F, -22F, -4F, 2, 3, 8);
		this.b_collar_back_right.setRotationPoint(0F, 0F, 0F);
		this.b_collar_back_right.setTextureSize(128, 64);
		this.b_collar_back_right.mirror = true;
		this.setRotation(this.b_collar_back_right, 0F, 0.7853982F, 0F);
		this.b_chest_top = new ModelRenderer(this, 0, 0);
		this.b_chest_top.addBox(-9.5F, -19F, -7.5F, 19, 9, 18);
		this.b_chest_top.setRotationPoint(0F, 0F, 0F);
		this.b_chest_top.setTextureSize(128, 64);
		this.b_chest_top.mirror = true;
		this.setRotation(this.b_chest_top, 0F, 0F, 0F);
		this.b_shoulder_left_front = new ModelRenderer(this, 0, 0);
		this.b_shoulder_left_front.addBox(0F, -18F, -19F, 10, 14, 4);
		this.b_shoulder_left_front.setRotationPoint(0F, 0F, 0F);
		this.b_shoulder_left_front.setTextureSize(128, 64);
		this.b_shoulder_left_front.mirror = true;
		this.setRotation(this.b_shoulder_left_front, -0.7853982F, 0F, 0.5235988F);
		this.b_shoulder_left_mid = new ModelRenderer(this, 0, 0);
		this.b_shoulder_left_mid.addBox(-0.5F, -27F, -1F, 11, 7, 6);
		this.b_shoulder_left_mid.setRotationPoint(0F, 0F, 0F);
		this.b_shoulder_left_mid.setTextureSize(128, 64);
		this.b_shoulder_left_mid.mirror = true;
		this.setRotation(this.b_shoulder_left_mid, 0F, 0F, 0.5235988F);
		this.b_shoulder_left_back = new ModelRenderer(this, 0, 0);
		this.b_shoulder_left_back.addBox(-1F, -15F, 18F, 10, 14, 4);
		this.b_shoulder_left_back.setRotationPoint(1F, 0F, 0F);
		this.b_shoulder_left_back.setTextureSize(128, 64);
		this.b_shoulder_left_back.mirror = true;
		this.setRotation(this.b_shoulder_left_back, 0.7853982F, 0F, 0.5235988F);
		this.b_shoulder_right_front = new ModelRenderer(this, 0, 0);
		this.b_shoulder_right_front.addBox(-10F, -18F, -19F, 10, 14, 4);
		this.b_shoulder_right_front.setRotationPoint(0F, 0F, 0F);
		this.b_shoulder_right_front.setTextureSize(128, 64);
		this.b_shoulder_right_front.mirror = true;
		this.setRotation(this.b_shoulder_right_front, -0.7853982F, 0F, -0.5235988F);
		this.b_shoulder_right_mid = new ModelRenderer(this, 0, 0);
		this.b_shoulder_right_mid.addBox(-10.5F, -27F, -1F, 11, 7, 6);
		this.b_shoulder_right_mid.setRotationPoint(0F, 0F, 0F);
		this.b_shoulder_right_mid.setTextureSize(128, 64);
		this.b_shoulder_right_mid.mirror = true;
		this.setRotation(this.b_shoulder_right_mid, 0F, 0F, -0.5235988F);
		this.b_shoulder_right_back = new ModelRenderer(this, 0, 0);
		this.b_shoulder_right_back.addBox(-10F, -16F, 18F, 10, 14, 4);
		this.b_shoulder_right_back.setRotationPoint(0F, 0F, 0F);
		this.b_shoulder_right_back.setTextureSize(128, 64);
		this.b_shoulder_right_back.mirror = true;
		this.setRotation(this.b_shoulder_right_back, 0.7853982F, 0F, -0.5235988F);
		this.b_chest_main = new ModelRenderer(this, 0, 0);
		this.b_chest_main.addBox(-14F, -14F, -11F, 28, 11, 11);
		this.b_chest_main.setRotationPoint(0F, 0F, 0F);
		this.b_chest_main.setTextureSize(128, 64);
		this.b_chest_main.mirror = true;
		this.setRotation(this.b_chest_main, -0.7853982F, 0F, 0F);
		this.b_core = new ModelRenderer(this, 0, 0);
		this.b_core.addBox(-5F, -13F, -8F, 10, 9, 3);
		this.b_core.setRotationPoint(0F, 0F, 0F);
		this.b_core.setTextureSize(128, 64);
		this.b_core.mirror = true;
		this.setRotation(this.b_core, 0F, 0F, 0F);
		this.b_crest_top = new ModelRenderer(this, 0, 0);
		this.b_crest_top.addBox(-4.5F, -1.5F, -1F, 9, 2, 4);
		this.b_crest_top.setRotationPoint(0F, -13F, -8F);
		this.b_crest_top.setTextureSize(128, 64);
		this.b_crest_top.mirror = true;
		this.setRotation(this.b_crest_top, 0.9599311F, 0F, 0F);
		this.b_crest_left_top = new ModelRenderer(this, 0, 0);
		this.b_crest_left_top.addBox(-1F, -3F, -0.8F, 6, 7, 2);
		this.b_crest_left_top.setRotationPoint(5F, -11F, -8F);
		this.b_crest_left_top.setTextureSize(128, 64);
		this.b_crest_left_top.mirror = true;
		this.setRotation(this.b_crest_left_top, -0.2094395F, -0.5235988F, -0.6108652F);
		this.b_crest_right_top = new ModelRenderer(this, 0, 0);
		this.b_crest_right_top.addBox(-5F, -3F, -0.8F, 6, 7, 2);
		this.b_crest_right_top.setRotationPoint(-5F, -11F, -8F);
		this.b_crest_right_top.setTextureSize(128, 64);
		this.b_crest_right_top.mirror = true;
		this.setRotation(this.b_crest_right_top, -0.2094395F, 0.5235988F, 0.6108652F);
		this.b_crest_bottom = new ModelRenderer(this, 0, 0);
		this.b_crest_bottom.addBox(-4F, 6F, -11.8F, 8, 2, 5);
		this.b_crest_bottom.setRotationPoint(0F, -13F, 0F);
		this.b_crest_bottom.setTextureSize(128, 64);
		this.b_crest_bottom.mirror = true;
		this.setRotation(this.b_crest_bottom, 0.2617994F, 0F, 0F);
		this.b_crest_left = new ModelRenderer(this, 0, 0);
		this.b_crest_left.addBox(-2F, -2F, -1F, 3, 6, 2);
		this.b_crest_left.setRotationPoint(6F, -7F, -8F);
		this.b_crest_left.setTextureSize(128, 64);
		this.b_crest_left.mirror = true;
		this.setRotation(this.b_crest_left, 0.0872665F, -0.3490659F, 0.5235988F);
		this.b_crest_right = new ModelRenderer(this, 0, 0);
		this.b_crest_right.addBox(-1F, -2F, -1F, 3, 6, 2);
		this.b_crest_right.setRotationPoint(-6F, -7F, -8F);
		this.b_crest_right.setTextureSize(128, 64);
		this.b_crest_right.mirror = true;
		this.setRotation(this.b_crest_right, 0.0872665F, 0.3490659F, -0.5235988F);
		this.b_chest_bottom = new ModelRenderer(this, 0, 0);
		this.b_chest_bottom.addBox(-12F, -11F, -7.5F, 24, 7, 7);
		this.b_chest_bottom.setRotationPoint(0F, 0F, 0F);
		this.b_chest_bottom.setTextureSize(128, 64);
		this.b_chest_bottom.mirror = true;
		this.setRotation(this.b_chest_bottom, 0F, 0F, 0F);
		this.b_back_top = new ModelRenderer(this, 0, 0);
		this.b_back_top.addBox(-10F, -25F, 9F, 20, 20, 10);
		this.b_back_top.setRotationPoint(0F, 0F, 0F);
		this.b_back_top.setTextureSize(128, 64);
		this.b_back_top.mirror = true;
		this.setRotation(this.b_back_top, 0F, 0F, 0F);
		this.b_back_bottom = new ModelRenderer(this, 0, 0);
		this.b_back_bottom.addBox(-4F, -13.5F, 5F, 8, 10, 5);
		this.b_back_bottom.setRotationPoint(0F, 0F, 0F);
		this.b_back_bottom.setTextureSize(128, 64);
		this.b_back_bottom.mirror = true;
		this.setRotation(this.b_back_bottom, -0.6108652F, 0F, 0F);
		this.b_waist = new ModelRenderer(this, 0, 0);
		this.b_waist.addBox(-7F, -9F, -5F, 14, 12, 15);
		this.b_waist.setRotationPoint(0F, 0F, 0F);
		this.b_waist.setTextureSize(128, 64);
		this.b_waist.mirror = true;
		this.setRotation(this.b_waist, 0F, 0F, 0F);
		this.t_mid_top = new ModelRenderer(this, 0, 0);
		this.t_mid_top.addBox(-8F, 0F, -10F, 16, 7, 21);
		this.t_mid_top.setRotationPoint(0F, 3F, 2F);
		this.t_mid_top.setTextureSize(128, 64);
		this.t_mid_top.mirror = true;
		this.setRotation(this.t_mid_top, 0F, 0F, 0F);
		this.t_mid_bottom = new ModelRenderer(this, 0, 0);
		this.t_mid_bottom.addBox(-12F, 0.5F, -8.5F, 24, 8, 8);
		this.t_mid_bottom.setRotationPoint(0F, 3F, 2F);
		this.t_mid_bottom.setTextureSize(128, 64);
		this.t_mid_bottom.mirror = true;
		this.setRotation(this.t_mid_bottom, 0.7853982F, 0F, 0F);
		this.t_left_cover_top = new ModelRenderer(this, 0, 0);
		this.t_left_cover_top.addBox(7F, -6F, -4F, 16, 8, 10);
		this.t_left_cover_top.setRotationPoint(0F, 3F, 2F);
		this.t_left_cover_top.setTextureSize(128, 64);
		this.t_left_cover_top.mirror = true;
		this.setRotation(this.t_left_cover_top, 0F, 0F, 0.1745329F);
		this.t_left_cover_front_left = new ModelRenderer(this, 0, 0);
		this.t_left_cover_front_left.addBox(15F, -3.5F, -21F, 6, 5, 20);
		this.t_left_cover_front_left.setRotationPoint(0F, 3F, 2F);
		this.t_left_cover_front_left.setTextureSize(128, 64);
		this.t_left_cover_front_left.mirror = true;
		this.setRotation(this.t_left_cover_front_left, 0.6981317F, 0F, 0F);
		this.t_left_cover_front_right = new ModelRenderer(this, 0, 0);
		this.t_left_cover_front_right.addBox(8.5F, -4F, -21F, 6, 5, 20);
		this.t_left_cover_front_right.setRotationPoint(0F, 3F, 2F);
		this.t_left_cover_front_right.setTextureSize(128, 64);
		this.t_left_cover_front_right.mirror = true;
		this.setRotation(this.t_left_cover_front_right, 0.6981317F, 0.0349066F, 0.0174533F);
		this.t_left_cover_back_left = new ModelRenderer(this, 0, 0);
		this.t_left_cover_back_left.addBox(15F, -4.5F, 3F, 6, 5, 20);
		this.t_left_cover_back_left.setRotationPoint(0F, 3F, 2F);
		this.t_left_cover_back_left.setTextureSize(128, 64);
		this.t_left_cover_back_left.mirror = true;
		this.setRotation(this.t_left_cover_back_left, -0.6981317F, 0F, 0F);
		this.t_left_cover_back_right = new ModelRenderer(this, 0, 0);
		this.t_left_cover_back_right.addBox(8.5F, -4.5F, 3F, 6, 5, 20);
		this.t_left_cover_back_right.setRotationPoint(0F, 3F, 2F);
		this.t_left_cover_back_right.setTextureSize(128, 64);
		this.t_left_cover_back_right.mirror = true;
		this.setRotation(this.t_left_cover_back_right, -0.6981317F, -0.0349066F, 0.0174533F);
		this.t_left_rotor_top = new ModelRenderer(this, 0, 0);
		this.t_left_rotor_top.addBox(7.5F, 1F, -5F, 14, 5, 5);
		this.t_left_rotor_top.setRotationPoint(0F, 3F, 2F);
		this.t_left_rotor_top.setTextureSize(128, 64);
		this.t_left_rotor_top.mirror = true;
		this.setRotation(this.t_left_rotor_top, 0.7853982F, 0F, 0F);
		this.t_left_rotor_front = new ModelRenderer(this, 0, 0);
		this.t_left_rotor_front.addBox(7.5F, -0.4F, -25.6F, 14, 5, 5);
		this.t_left_rotor_front.setRotationPoint(0F, 3F, 2F);
		this.t_left_rotor_front.setTextureSize(128, 64);
		this.t_left_rotor_front.mirror = true;
		this.setRotation(this.t_left_rotor_front, 0.7853982F, 0F, 0F);
		this.t_left_rotor_back = new ModelRenderer(this, 0, 0);
		this.t_left_rotor_back.addBox(7.5F, 22F, -3F, 14, 5, 5);
		this.t_left_rotor_back.setRotationPoint(0F, 3F, 2F);
		this.t_left_rotor_back.setTextureSize(128, 64);
		this.t_left_rotor_back.mirror = true;
		this.setRotation(this.t_left_rotor_back, 0.7853982F, 0F, 0F);
		this.t_left_tread_front = new ModelRenderer(this, 0, 0);
		this.t_left_tread_front.addBox(8F, 1.7F, -23F, 13, 4, 20);
		this.t_left_tread_front.setRotationPoint(0F, 3F, 2F);
		this.t_left_tread_front.setTextureSize(128, 64);
		this.t_left_tread_front.mirror = true;
		this.setRotation(this.t_left_tread_front, 0.6981317F, 0F, 0F);
		this.t_left_tread_back = new ModelRenderer(this, 0, 0);
		this.t_left_tread_back.addBox(8F, 0.7F, 4F, 13, 4, 20);
		this.t_left_tread_back.setRotationPoint(0F, 3F, 2F);
		this.t_left_tread_back.setTextureSize(128, 64);
		this.t_left_tread_back.mirror = true;
		this.setRotation(this.t_left_tread_back, -0.6981317F, 0F, 0F);
		this.t_left_tread_bottom = new ModelRenderer(this, 0, 0);
		this.t_left_tread_bottom.addBox(8F, 17.3F, -15F, 13, 4, 32);
		this.t_left_tread_bottom.setRotationPoint(0F, 3F, 2F);
		this.t_left_tread_bottom.setTextureSize(128, 64);
		this.t_left_tread_bottom.mirror = true;
		this.setRotation(this.t_left_tread_bottom, 0F, 0F, 0F);
		this.t_left_center_bottom = new ModelRenderer(this, 0, 0);
		this.t_left_center_bottom.addBox(11F, 13.3F, -12F, 7, 4, 26);
		this.t_left_center_bottom.setRotationPoint(0F, 3F, 2F);
		this.t_left_center_bottom.setTextureSize(128, 64);
		this.t_left_center_bottom.mirror = true;
		this.setRotation(this.t_left_center_bottom, 0F, 0F, 0F);
		this.t_left_center_mid = new ModelRenderer(this, 0, 0);
		this.t_left_center_mid.addBox(11F, 9.3F, -8F, 7, 4, 18);
		this.t_left_center_mid.setRotationPoint(0F, 3F, 2F);
		this.t_left_center_mid.setTextureSize(128, 64);
		this.t_left_center_mid.mirror = true;
		this.setRotation(this.t_left_center_mid, 0F, 0F, 0F);
		this.t_left_center_top = new ModelRenderer(this, 0, 0);
		this.t_left_center_top.addBox(11F, 5.366667F, -3F, 7, 4, 8);
		this.t_left_center_top.setRotationPoint(0F, 3F, 2F);
		this.t_left_center_top.setTextureSize(128, 64);
		this.t_left_center_top.mirror = true;
		this.setRotation(this.t_left_center_top, 0F, 0F, 0F);
		this.t_right_cover_top = new ModelRenderer(this, 0, 0);
		this.t_right_cover_top.addBox(-23F, -6F, -4F, 16, 8, 10);
		this.t_right_cover_top.setRotationPoint(0F, 3F, 2F);
		this.t_right_cover_top.setTextureSize(128, 64);
		this.t_right_cover_top.mirror = true;
		this.setRotation(this.t_right_cover_top, 0F, 0F, -0.1745329F);
		this.t_right_cover_front_left = new ModelRenderer(this, 0, 0);
		this.t_right_cover_front_left.addBox(-14.5F, -4F, -21F, 6, 5, 20);
		this.t_right_cover_front_left.setRotationPoint(0F, 3F, 2F);
		this.t_right_cover_front_left.setTextureSize(128, 64);
		this.t_right_cover_front_left.mirror = true;
		this.setRotation(this.t_right_cover_front_left, 0.6981317F, -0.0349066F, -0.0174533F);
		this.t_right_cover_front_right = new ModelRenderer(this, 0, 0);
		this.t_right_cover_front_right.addBox(-21F, -3.5F, -21F, 6, 5, 20);
		this.t_right_cover_front_right.setRotationPoint(0F, 3F, 2F);
		this.t_right_cover_front_right.setTextureSize(128, 64);
		this.t_right_cover_front_right.mirror = true;
		this.setRotation(this.t_right_cover_front_right, 0.6981317F, 0F, 0F);
		this.t_right_cover_back_left = new ModelRenderer(this, 0, 0);
		this.t_right_cover_back_left.addBox(-14.5F, -4.5F, 3F, 6, 5, 20);
		this.t_right_cover_back_left.setRotationPoint(0F, 3F, 2F);
		this.t_right_cover_back_left.setTextureSize(128, 64);
		this.t_right_cover_back_left.mirror = true;
		this.setRotation(this.t_right_cover_back_left, -0.6981317F, 0.0349066F, -0.0174533F);
		this.t_right_cover_back_right = new ModelRenderer(this, 0, 0);
		this.t_right_cover_back_right.addBox(-21F, -4.5F, 3F, 6, 5, 20);
		this.t_right_cover_back_right.setRotationPoint(0F, 3F, 2F);
		this.t_right_cover_back_right.setTextureSize(128, 64);
		this.t_right_cover_back_right.mirror = true;
		this.setRotation(this.t_right_cover_back_right, -0.6981317F, 0F, 0F);
		this.t_right_rotor_top = new ModelRenderer(this, 0, 0);
		this.t_right_rotor_top.addBox(-21.5F, 1F, -5F, 14, 5, 5);
		this.t_right_rotor_top.setRotationPoint(0F, 3F, 2F);
		this.t_right_rotor_top.setTextureSize(128, 64);
		this.t_right_rotor_top.mirror = true;
		this.setRotation(this.t_right_rotor_top, 0.7853982F, 0F, 0F);
		this.t_right_rotor_front = new ModelRenderer(this, 0, 0);
		this.t_right_rotor_front.addBox(-21.5F, -0.4F, -25.6F, 14, 5, 5);
		this.t_right_rotor_front.setRotationPoint(0F, 3F, 2F);
		this.t_right_rotor_front.setTextureSize(128, 64);
		this.t_right_rotor_front.mirror = true;
		this.setRotation(this.t_right_rotor_front, 0.7853982F, 0F, 0F);
		this.t_right_rotot_back = new ModelRenderer(this, 0, 0);
		this.t_right_rotot_back.addBox(-21.5F, 22F, -3F, 14, 5, 5);
		this.t_right_rotot_back.setRotationPoint(0F, 3F, 2F);
		this.t_right_rotot_back.setTextureSize(128, 64);
		this.t_right_rotot_back.mirror = true;
		this.setRotation(this.t_right_rotot_back, 0.7853982F, 0F, 0F);
		this.t_right_tread_front = new ModelRenderer(this, 0, 0);
		this.t_right_tread_front.addBox(-21F, 1.7F, -23F, 13, 4, 20);
		this.t_right_tread_front.setRotationPoint(0F, 3F, 2F);
		this.t_right_tread_front.setTextureSize(128, 64);
		this.t_right_tread_front.mirror = true;
		this.setRotation(this.t_right_tread_front, 0.6981317F, 0F, 0F);
		this.t_right_tread_back = new ModelRenderer(this, 0, 0);
		this.t_right_tread_back.addBox(-21F, 0.7F, 4F, 13, 4, 20);
		this.t_right_tread_back.setRotationPoint(0F, 3F, 2F);
		this.t_right_tread_back.setTextureSize(128, 64);
		this.t_right_tread_back.mirror = true;
		this.setRotation(this.t_right_tread_back, -0.6981317F, 0F, 0F);
		this.t_right_tread_bottom = new ModelRenderer(this, 0, 0);
		this.t_right_tread_bottom.addBox(-21F, 17.3F, -15F, 13, 4, 32);
		this.t_right_tread_bottom.setRotationPoint(0F, 3F, 2F);
		this.t_right_tread_bottom.setTextureSize(128, 64);
		this.t_right_tread_bottom.mirror = true;
		this.setRotation(this.t_right_tread_bottom, 0F, 0F, 0F);
		this.t_right_center_bottom = new ModelRenderer(this, 0, 0);
		this.t_right_center_bottom.addBox(-17F, 13.3F, -12F, 5, 4, 26);
		this.t_right_center_bottom.setRotationPoint(0F, 3F, 2F);
		this.t_right_center_bottom.setTextureSize(128, 64);
		this.t_right_center_bottom.mirror = true;
		this.setRotation(this.t_right_center_bottom, 0F, 0F, 0F);
		this.t_right_center_mid = new ModelRenderer(this, 0, 0);
		this.t_right_center_mid.addBox(-17F, 9.3F, -8F, 5, 4, 18);
		this.t_right_center_mid.setRotationPoint(0F, 3F, 2F);
		this.t_right_center_mid.setTextureSize(128, 64);
		this.t_right_center_mid.mirror = true;
		this.setRotation(this.t_right_center_mid, 0F, 0F, 0F);
		this.t_right_center_top = new ModelRenderer(this, 0, 0);
		this.t_right_center_top.addBox(-17F, 5.4F, -3F, 5, 4, 8);
		this.t_right_center_top.setRotationPoint(0F, 3F, 2F);
		this.t_right_center_top.setTextureSize(128, 64);
		this.t_right_center_top.mirror = true;
		this.setRotation(this.t_right_center_top, 0F, 0F, 0F);
		this.l_a_joint = new ModelRenderer(this, 0, 0);
		this.l_a_joint.addBox(-2F, -4F, -3F, 7, 7, 7);
		this.l_a_joint.setRotationPoint(14F, -10F, 2F);
		this.l_a_joint.setTextureSize(128, 64);
		this.l_a_joint.mirror = true;
		this.setRotation(this.l_a_joint, 0.7853982F, 0F, 0F);
		this.l_a_top = new ModelRenderer(this, 0, 0);
		this.l_a_top.addBox(0F, 1F, -3F, 5, 11, 5);
		this.l_a_top.setRotationPoint(14F, -10F, 2F);
		this.l_a_top.setTextureSize(128, 64);
		this.l_a_top.mirror = true;
		this.setRotation(this.l_a_top, 0.1396263F, 0F, -0.7853982F);
		this.l_a_forearm = new ModelRenderer(this, 0, 0);
		this.l_a_forearm.addBox(11F, -8.5F, -17F, 12, 12, 22);
		this.l_a_forearm.setRotationPoint(14F, -10F, 2F);
		this.l_a_forearm.setTextureSize(128, 64);
		this.l_a_forearm.mirror = true;
		this.setRotation(this.l_a_forearm, 0F, -0.1745329F, 0.7853982F);
		this.l_a_finger_top_left = new ModelRenderer(this, 0, 0);
		this.l_a_finger_top_left.addBox(15F, -13.7F, -17F, 4, 2, 4);
		this.l_a_finger_top_left.setRotationPoint(14F, -10F, 2F);
		this.l_a_finger_top_left.setTextureSize(128, 64);
		this.l_a_finger_top_left.mirror = true;
		this.setRotation(this.l_a_finger_top_left, 0.3490659F, -0.1745329F, 0.7853982F);
		this.l_a_finger_top_right = new ModelRenderer(this, 0, 0);
		this.l_a_finger_top_right.addBox(6.6F, -5F, -23F, 2, 4, 4);
		this.l_a_finger_top_right.setRotationPoint(14F, -10F, 2F);
		this.l_a_finger_top_right.setTextureSize(128, 64);
		this.l_a_finger_top_right.mirror = true;
		this.setRotation(this.l_a_finger_top_right, 0.2617994F, -0.5235988F, 0.7679449F);
		this.l_a_finger_bottom_left = new ModelRenderer(this, 0, 0);
		this.l_a_finger_bottom_left.addBox(24.2F, -5.5F, -14F, 2, 4, 4);
		this.l_a_finger_bottom_left.setRotationPoint(14F, -10F, 2F);
		this.l_a_finger_bottom_left.setTextureSize(128, 64);
		this.l_a_finger_bottom_left.mirror = true;
		this.setRotation(this.l_a_finger_bottom_left, -0.1919862F, 0.1745329F, 0.7679449F);
		this.l_a_finger_bottom_right = new ModelRenderer(this, 0, 0);
		this.l_a_finger_bottom_right.addBox(15F, 7F, -18.8F, 4, 2, 4);
		this.l_a_finger_bottom_right.setRotationPoint(14F, -10F, 2F);
		this.l_a_finger_bottom_right.setTextureSize(128, 64);
		this.l_a_finger_bottom_right.mirror = true;
		this.setRotation(this.l_a_finger_bottom_right, -0.3490659F, -0.1745329F, 0.7853982F);
		this.r_a_joint = new ModelRenderer(this, 0, 0);
		this.r_a_joint.addBox(-4F, -4F, -3F, 7, 7, 7);
		this.r_a_joint.setRotationPoint(-14F, -10F, 2F);
		this.r_a_joint.setTextureSize(128, 64);
		this.r_a_joint.mirror = true;
		this.setRotation(this.r_a_joint, 0.7853982F, 0F, 0F);
		this.r_a_top = new ModelRenderer(this, 0, 0);
		this.r_a_top.addBox(-5F, 1F, -3F, 5, 11, 5);
		this.r_a_top.setRotationPoint(-14F, -10F, 2F);
		this.r_a_top.setTextureSize(128, 64);
		this.r_a_top.mirror = true;
		this.setRotation(this.r_a_top, 0.1396263F, 0F, 0.7853982F);
		this.r_a_forearm = new ModelRenderer(this, 0, 0);
		this.r_a_forearm.addBox(-8.5F, 11F, -17F, 12, 12, 22);
		this.r_a_forearm.setRotationPoint(-14F, -10F, 2F);
		this.r_a_forearm.setTextureSize(128, 64);
		this.r_a_forearm.mirror = true;
		this.setRotation(this.r_a_forearm, 0F, 0.1745329F, 0.7853982F);
		this.r_a_finger_top_left = new ModelRenderer(this, 0, 0);
		this.r_a_finger_top_left.addBox(-8.6F, -5F, -23F, 2, 4, 4);
		this.r_a_finger_top_left.setRotationPoint(-14F, -10F, 2F);
		this.r_a_finger_top_left.setTextureSize(128, 64);
		this.r_a_finger_top_left.mirror = true;
		this.setRotation(this.r_a_finger_top_left, 0.2617994F, 0.5235988F, -0.7679449F);
		this.r_a_finger_top_right = new ModelRenderer(this, 0, 0);
		this.r_a_finger_top_right.addBox(-19F, -13.5F, -17F, 4, 2, 4);
		this.r_a_finger_top_right.setRotationPoint(-14F, -10F, 2F);
		this.r_a_finger_top_right.setTextureSize(128, 64);
		this.r_a_finger_top_right.mirror = true;
		this.setRotation(this.r_a_finger_top_right, 0.3490659F, 0.1745329F, -0.7853982F);
		this.r_a_finger_bottom_left = new ModelRenderer(this, 0, 0);
		this.r_a_finger_bottom_left.addBox(-19F, 7.1F, -18.8F, 4, 2, 4);
		this.r_a_finger_bottom_left.setRotationPoint(-14F, -10F, 2F);
		this.r_a_finger_bottom_left.setTextureSize(128, 64);
		this.r_a_finger_bottom_left.mirror = true;
		this.setRotation(this.r_a_finger_bottom_left, -0.3490659F, 0.1745329F, -0.7853982F);
		this.r_a_finger_bottom_right = new ModelRenderer(this, 0, 0);
		this.r_a_finger_bottom_right.addBox(-26.2F, -5.5F, -14F, 2, 4, 4);
		this.r_a_finger_bottom_right.setRotationPoint(-14F, -10F, 2F);
		this.r_a_finger_bottom_right.setTextureSize(128, 64);
		this.r_a_finger_bottom_right.mirror = true;
		this.setRotation(this.r_a_finger_bottom_right, -0.1919862F, -0.1745329F, -0.7679449F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.head.render(f5);
		this.b_collar_front.render(f5);
		this.b_collar_front_left.render(f5);
		this.b_collar_front_right.render(f5);
		this.b_collar_left.render(f5);
		this.b_collar_right.render(f5);
		this.b_collar_back_left.render(f5);
		this.b_collar_back_right.render(f5);
		this.b_chest_top.render(f5);
		this.b_shoulder_left_front.render(f5);
		this.b_shoulder_left_mid.render(f5);
		this.b_shoulder_left_back.render(f5);
		this.b_shoulder_right_front.render(f5);
		this.b_shoulder_right_mid.render(f5);
		this.b_shoulder_right_back.render(f5);
		this.b_chest_main.render(f5);
		this.b_core.render(f5);
		this.b_crest_top.render(f5);
		this.b_crest_left_top.render(f5);
		this.b_crest_right_top.render(f5);
		this.b_crest_bottom.render(f5);
		this.b_crest_left.render(f5);
		this.b_crest_right.render(f5);
		this.b_chest_bottom.render(f5);
		this.b_back_top.render(f5);
		this.b_back_bottom.render(f5);
		this.b_waist.render(f5);
		this.t_mid_top.render(f5);
		this.t_mid_bottom.render(f5);
		this.t_left_cover_top.render(f5);
		this.t_left_cover_front_left.render(f5);
		this.t_left_cover_front_right.render(f5);
		this.t_left_cover_back_left.render(f5);
		this.t_left_cover_back_right.render(f5);
		this.t_left_rotor_top.render(f5);
		this.t_left_rotor_front.render(f5);
		this.t_left_rotor_back.render(f5);
		this.t_left_tread_front.render(f5);
		this.t_left_tread_back.render(f5);
		this.t_left_tread_bottom.render(f5);
		this.t_left_center_bottom.render(f5);
		this.t_left_center_mid.render(f5);
		this.t_left_center_top.render(f5);
		this.t_right_cover_top.render(f5);
		this.t_right_cover_front_left.render(f5);
		this.t_right_cover_front_right.render(f5);
		this.t_right_cover_back_left.render(f5);
		this.t_right_cover_back_right.render(f5);
		this.t_right_rotor_top.render(f5);
		this.t_right_rotor_front.render(f5);
		this.t_right_rotot_back.render(f5);
		this.t_right_tread_front.render(f5);
		this.t_right_tread_back.render(f5);
		this.t_right_tread_bottom.render(f5);
		this.t_right_center_bottom.render(f5);
		this.t_right_center_mid.render(f5);
		this.t_right_center_top.render(f5);
		this.l_a_joint.render(f5);
		this.l_a_top.render(f5);
		this.l_a_forearm.render(f5);
		this.l_a_finger_top_left.render(f5);
		this.l_a_finger_top_right.render(f5);
		this.l_a_finger_bottom_left.render(f5);
		this.l_a_finger_bottom_right.render(f5);
		this.r_a_joint.render(f5);
		this.r_a_top.render(f5);
		this.r_a_forearm.render(f5);
		this.r_a_finger_top_left.render(f5);
		this.r_a_finger_top_right.render(f5);
		this.r_a_finger_bottom_left.render(f5);
		this.r_a_finger_bottom_right.render(f5);
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
