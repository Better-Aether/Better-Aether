package com.gildedgames.aether.client.models.entities.living;

import com.gildedgames.aether.common.entities.living.enemies.EntityCockatrice;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCockatrice extends ModelBase
{

	ModelRenderer LegTopRight;
	ModelRenderer LegTopLeft;
	ModelRenderer LegMidRight;
	ModelRenderer LegMidLeft;
	ModelRenderer LegBottomRight;
	ModelRenderer LegBottomLeft;
	ModelRenderer FootRight;
	ModelRenderer FootLeft;
	ModelRenderer LegFeatheredRight;
	ModelRenderer LegFeatheredLeft;
	ModelRenderer BodyFront;
	ModelRenderer BodyBack;
	ModelRenderer ShoulderRight;
	ModelRenderer ShoulderLeft;
	ModelRenderer WingBaseRight;
	ModelRenderer WingBaseLeft;
	ModelRenderer WingRight;
	ModelRenderer WingLeft;
	ModelRenderer TailMiddle;
	ModelRenderer TailLeft;
	ModelRenderer TailRight;
	ModelRenderer NeckBase;
	ModelRenderer NeckTop;
	ModelRenderer Head;
	ModelRenderer BeakJaw;
	ModelRenderer Teeth;
	ModelRenderer FeatherRight;
	ModelRenderer FeatherLeft;
	ModelRenderer FeatherTop;
	ModelRenderer ToeLeftRight;
	ModelRenderer ToeMidRight;
	ModelRenderer ToeRightRight;
	ModelRenderer ToeLeftLeft;
	ModelRenderer ToeMidLeft;
	ModelRenderer ToeRightLeft;
	ModelRenderer Dart1;
	ModelRenderer Dart2;
	ModelRenderer Dart3;
	ModelRenderer Dart4;
	ModelRenderer Dart5;
	ModelRenderer Dart6;

	public ModelCockatrice()
	{
		textureWidth = 128;
		textureHeight = 64;

		LegTopRight = new ModelRenderer(this, 50, 0);
		LegTopRight.addBox(-1F, 2F, 0.7F, 2, 4, 2);
		LegTopRight.setRotationPoint(-3F, 7F, -2F);
		LegTopRight.setTextureSize(128, 64);
		LegTopRight.mirror = true;
		setRotation(LegTopRight, -0.2094395F, 0F, 0F);
		LegTopRight.mirror = false;
		LegTopLeft = new ModelRenderer(this, 50, 0);
		LegTopLeft.addBox(-1F, 2F, 0.7F, 2, 4, 2);
		LegTopLeft.setRotationPoint(3F, 7F, -2F);
		LegTopLeft.setTextureSize(128, 64);
		LegTopLeft.mirror = true;
		setRotation(LegTopLeft, -0.2094395F, 0F, 0F);
		LegMidRight = new ModelRenderer(this, 58, 0);
		LegMidRight.addBox(-1F, 3.5F, -5F, 2, 8, 2);
		LegMidRight.setRotationPoint(-3F, 7F, -2F);
		LegMidRight.setTextureSize(128, 64);
		LegMidRight.mirror = true;
		setRotation(LegMidRight, 0.8726646F, 0F, 0F);
		LegMidRight.mirror = false;
		LegMidLeft = new ModelRenderer(this, 58, 0);
		LegMidLeft.addBox(-1F, 3.5F, -5F, 2, 8, 2);
		LegMidLeft.setRotationPoint(3F, 7F, -2F);
		LegMidLeft.setTextureSize(128, 64);
		LegMidLeft.mirror = true;
		setRotation(LegMidLeft, 0.8726646F, 0F, 0F);
		LegBottomRight = new ModelRenderer(this, 58, 10);
		LegBottomRight.addBox(-1F, 3.1F, 9.5F, 2, 9, 2);
		LegBottomRight.setRotationPoint(-3F, 7F, -2F);
		LegBottomRight.setTextureSize(128, 64);
		LegBottomRight.mirror = true;
		setRotation(LegBottomRight, -0.6981317F, 0F, 0F);
		LegBottomRight.mirror = false;
		LegBottomLeft = new ModelRenderer(this, 58, 10);
		LegBottomLeft.addBox(-1F, 3.1F, 9.5F, 2, 9, 2);
		LegBottomLeft.setRotationPoint(3F, 7F, -2F);
		LegBottomLeft.setTextureSize(128, 64);
		LegBottomLeft.mirror = true;
		setRotation(LegBottomLeft, -0.6981317F, 0F, 0F);
		FootRight = new ModelRenderer(this, 38, 12);
		FootRight.addBox(-1F, 15F, -2F, 2, 2, 8);
		FootRight.setRotationPoint(-3F, 7F, -2F);
		FootRight.setTextureSize(128, 64);
		FootRight.mirror = true;
		setRotation(FootRight, 0F, 0F, 0F);
		FootRight.mirror = false;
		FootLeft = new ModelRenderer(this, 38, 12);
		FootLeft.addBox(-1F, 15F, -2F, 2, 2, 8);
		FootLeft.setRotationPoint(3F, 7F, -2F);
		FootLeft.setTextureSize(128, 64);
		FootLeft.mirror = true;
		setRotation(FootLeft, 0F, 0F, 0F);
		LegFeatheredRight = new ModelRenderer(this, 58, 21);
		LegFeatheredRight.addBox(-2F, 0F, -2F, 4, 4, 6);
		LegFeatheredRight.setRotationPoint(-3F, 7F, -2F);
		LegFeatheredRight.setTextureSize(128, 64);
		LegFeatheredRight.mirror = true;
		setRotation(LegFeatheredRight, -0.2094395F, 0F, 0.1745329F);
		LegFeatheredRight.mirror = false;
		LegFeatheredLeft = new ModelRenderer(this, 58, 21);
		LegFeatheredLeft.addBox(-2F, 0F, -2F, 4, 4, 6);
		LegFeatheredLeft.setRotationPoint(3F, 7F, -2F);
		LegFeatheredLeft.setTextureSize(128, 64);
		LegFeatheredLeft.mirror = true;
		setRotation(LegFeatheredLeft, -0.2094395F, 0F, -0.1745329F);
		BodyFront = new ModelRenderer(this, 74, 0);
		BodyFront.addBox(-3F, 0F, -8F, 6, 5, 8);
		BodyFront.setRotationPoint(0F, 3F, 0F);
		BodyFront.setTextureSize(128, 64);
		BodyFront.mirror = true;
		setRotation(BodyFront, 0.0872665F, 0F, 0F);
		BodyBack = new ModelRenderer(this, 94, 5);
		BodyBack.addBox(-4F, 0F, 0F, 8, 6, 8);
		BodyBack.setRotationPoint(0F, 3F, 0F);
		BodyBack.setTextureSize(128, 64);
		BodyBack.mirror = true;
		setRotation(BodyBack, -0.1396263F, 0F, 0F);
		ShoulderRight = new ModelRenderer(this, 29, 0);
		ShoulderRight.addBox(-5F, -0.8F, -2F, 6, 3, 3);
		ShoulderRight.setRotationPoint(-2F, 4.5F, -3F);
		ShoulderRight.setTextureSize(128, 64);
		ShoulderRight.mirror = true;
		setRotation(ShoulderRight, 0F, 0.1745329F, -0.1745329F);
		ShoulderRight.mirror = false;
		ShoulderLeft = new ModelRenderer(this, 29, 0);
		ShoulderLeft.addBox(-1F, -0.8F, -2F, 6, 3, 3);
		ShoulderLeft.setRotationPoint(2F, 4.5F, -3F);
		ShoulderLeft.setTextureSize(128, 64);
		ShoulderLeft.mirror = true;
		setRotation(ShoulderLeft, 0F, -0.1745329F, 0.1745329F);
		WingBaseRight = new ModelRenderer(this, 66, 0);
		WingBaseRight.addBox(-5F, 0.2F, -1F, 2, 6, 2);
		WingBaseRight.setRotationPoint(-2F, 4.5F, -3F);
		WingBaseRight.setTextureSize(128, 64);
		WingBaseRight.mirror = true;
		setRotation(WingBaseRight, 0F, 0F, 0F);
		WingBaseRight.mirror = false;
		WingBaseLeft = new ModelRenderer(this, 66, 0);
		WingBaseLeft.addBox(3F, 0.2F, -1F, 2, 6, 2);
		WingBaseLeft.setRotationPoint(2F, 4.5F, -3F);
		WingBaseLeft.setTextureSize(128, 64);
		WingBaseLeft.mirror = true;
		setRotation(WingBaseLeft, 0F, 0F, 0F);
		WingRight = new ModelRenderer(this, 0, 20);
		WingRight.addBox(-5F, 2F, 0F, 1, 8, 15);
		WingRight.setRotationPoint(-2F, 4.5F, -3F);
		WingRight.setTextureSize(128, 64);
		WingRight.mirror = true;
		setRotation(WingRight, -0.1745329F, 0F, 0F);
		WingLeft = new ModelRenderer(this, 0, 20);
		WingLeft.addBox(4F, 2F, 0F, 1, 8, 15);
		WingLeft.setRotationPoint(2F, 4.5F, -3F);
		WingLeft.setTextureSize(128, 64);
		WingLeft.mirror = true;
		setRotation(WingLeft, -0.1745329F, 0F, 0F);
		TailMiddle = new ModelRenderer(this, 17, 20);
		TailMiddle.addBox(-1.5F, 0F, 0F, 3, 1, 14);
		TailMiddle.setRotationPoint(0F, 5F, 7F);
		TailMiddle.setTextureSize(128, 64);
		TailMiddle.mirror = true;
		setRotation(TailMiddle, 0.3490659F, 0F, 0F);
		TailLeft = new ModelRenderer(this, 20, 35);
		TailLeft.addBox(0F, 0F, 0F, 3, 1, 12);
		TailLeft.setRotationPoint(0F, 5F, 7F);
		TailLeft.setTextureSize(128, 64);
		TailLeft.mirror = true;
		setRotation(TailLeft, 0F, 0.3490659F, 0.1745329F);
		TailRight = new ModelRenderer(this, 20, 35);
		TailRight.addBox(-3F, 0F, 0F, 3, 1, 12);
		TailRight.setRotationPoint(0F, 5F, 7F);
		TailRight.setTextureSize(128, 64);
		TailRight.mirror = true;
		setRotation(TailRight, 0F, -0.3490659F, -0.1745329F);
		NeckBase = new ModelRenderer(this, 66, 13);
		NeckBase.addBox(-3F, -4F, -4F, 6, 4, 4);
		NeckBase.setRotationPoint(0F, 8.7F, -7.5F);
		NeckBase.setTextureSize(128, 64);
		NeckBase.mirror = true;
		setRotation(NeckBase, -0.669215F, 0F, 0F);
		NeckTop = new ModelRenderer(this, 44, 6);
		NeckTop.addBox(-2F, -3F, -3F, 4, 3, 3);
		NeckTop.setRotationPoint(0F, 6F, -10F);
		NeckTop.setTextureSize(128, 64);
		NeckTop.mirror = true;
		setRotation(NeckTop, -0.9599311F, 0F, 0F);
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-4F, -6F, -8F, 8, 7, 13);
		Head.setRotationPoint(0F, 3.5F, -10F);
		Head.setTextureSize(128, 64);
		Head.mirror = true;
		setRotation(Head, 0.0872665F, 0F, 0F);
		BeakJaw = new ModelRenderer(this, 80, 15);
		BeakJaw.addBox(-2F, 1.2F, -5.2F, 4, 1, 6);
		BeakJaw.setRotationPoint(0F, 3.5F, -10F);
		BeakJaw.setTextureSize(128, 64);
		BeakJaw.mirror = true;
		setRotation(BeakJaw, 0.1745329F, 0F, 0F);
		Teeth = new ModelRenderer(this, 44, 25);
		Teeth.addBox(-2F, 0.2F, -5.2F, 4, 1, 6);
		Teeth.setRotationPoint(0F, 3.5F, -10F);
		Teeth.setTextureSize(128, 64);
		Teeth.mirror = true;
		setRotation(Teeth, 0.1745329F, 0F, 0F);
		FeatherRight = new ModelRenderer(this, 78, 22);
		FeatherRight.addBox(-3.5F, -4.6F, 1F, 1, 5, 5);
		FeatherRight.setRotationPoint(0F, 3.5F, -10F);
		FeatherRight.setTextureSize(128, 64);
		FeatherRight.mirror = true;
		setRotation(FeatherRight, 0F, -0.4363323F, 0.0523599F);
		FeatherLeft = new ModelRenderer(this, 78, 22);
		FeatherLeft.addBox(2.533333F, -4.6F, 1F, 1, 5, 5);
		FeatherLeft.setRotationPoint(0F, 3.5F, -10F);
		FeatherLeft.setTextureSize(128, 64);
		FeatherLeft.mirror = true;
		setRotation(FeatherLeft, 0F, 0.4363323F, -0.0523599F);
		FeatherLeft.mirror = false;
		FeatherTop = new ModelRenderer(this, 43, 32);
		FeatherTop.addBox(-3F, -6.5F, -2F, 6, 1, 8);
		FeatherTop.setRotationPoint(0F, 3.5F, -10F);
		FeatherTop.setTextureSize(128, 64);
		FeatherTop.mirror = true;
		setRotation(FeatherTop, 0.3490659F, 0F, 0F);
		ToeLeftRight = new ModelRenderer(this, 7, 0);
		ToeLeftRight.addBox(0F, 15F, -3F, 1, 2, 2);
		ToeLeftRight.setRotationPoint(-3F, 7F, -2F);
		ToeLeftRight.setTextureSize(128, 64);
		ToeLeftRight.mirror = true;
		setRotation(ToeLeftRight, 0F, -0.2617994F, 0F);
		ToeMidRight = new ModelRenderer(this, 7, 0);
		ToeMidRight.addBox(-0.5F, 15F, -4F, 1, 2, 2);
		ToeMidRight.setRotationPoint(-3F, 7F, -2F);
		ToeMidRight.setTextureSize(128, 64);
		ToeMidRight.mirror = true;
		setRotation(ToeMidRight, 0F, 0F, 0F);
		ToeRightRight = new ModelRenderer(this, 7, 0);
		ToeRightRight.addBox(-1F, 15F, -3F, 1, 2, 2);
		ToeRightRight.setRotationPoint(-3F, 7F, -2F);
		ToeRightRight.setTextureSize(128, 64);
		ToeRightRight.mirror = true;
		setRotation(ToeRightRight, 0F, 0.2617994F, 0F);
		ToeLeftLeft = new ModelRenderer(this, 7, 0);
		ToeLeftLeft.addBox(0F, 15F, -3F, 1, 2, 2);
		ToeLeftLeft.setRotationPoint(3F, 7F, -2F);
		ToeLeftLeft.setTextureSize(128, 64);
		ToeLeftLeft.mirror = true;
		setRotation(ToeLeftLeft, 0F, -0.2617994F, 0F);
		ToeMidLeft = new ModelRenderer(this, 7, 0);
		ToeMidLeft.addBox(-0.5F, 15F, -4F, 1, 2, 2);
		ToeMidLeft.setRotationPoint(3F, 7F, -2F);
		ToeMidLeft.setTextureSize(128, 64);
		ToeMidLeft.mirror = true;
		setRotation(ToeMidLeft, 0F, 0F, 0F);
		ToeRightLeft = new ModelRenderer(this, 7, 0);
		ToeRightLeft.addBox(-1F, 15F, -3F, 1, 2, 2);
		ToeRightLeft.setRotationPoint(3F, 7F, -2F);
		ToeRightLeft.setTextureSize(128, 64);
		ToeRightLeft.mirror = true;
		setRotation(ToeRightLeft, 0F, 0.2617994F, 0F);
		Dart1 = new ModelRenderer(this, 0, 0);
		Dart1.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
		Dart1.setRotationPoint(-2F, 4F, 1F);
		Dart1.setTextureSize(128, 64);
		Dart1.mirror = true;
		setRotation(Dart1, 0.5235988F, 0F, 0F);
		Dart2 = new ModelRenderer(this, 0, 0);
		Dart2.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
		Dart2.setRotationPoint(2F, 4F, 1F);
		Dart2.setTextureSize(128, 64);
		Dart2.mirror = true;
		setRotation(Dart2, 0.5235988F, 0F, 0F);
		Dart2.mirror = false;
		Dart3 = new ModelRenderer(this, 0, 0);
		Dart3.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
		Dart3.setRotationPoint(2F, 5F, 4F);
		Dart3.setTextureSize(128, 64);
		Dart3.mirror = true;
		setRotation(Dart3, 0.5235988F, -0.1745329F, 0F);
		Dart3.mirror = false;
		Dart4 = new ModelRenderer(this, 0, 0);
		Dart4.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
		Dart4.setRotationPoint(-2F, 5F, 4F);
		Dart4.setTextureSize(128, 64);
		Dart4.mirror = true;
		setRotation(Dart4, 0.5235988F, 0.1745329F, 0F);
		Dart5 = new ModelRenderer(this, 0, 0);
		Dart5.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
		Dart5.setRotationPoint(-2F, 6F, 7F);
		Dart5.setTextureSize(128, 64);
		Dart5.mirror = true;
		setRotation(Dart5, 0.5235988F, 0.3490659F, 0F);
		Dart6 = new ModelRenderer(this, 0, 0);
		Dart6.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
		Dart6.setRotationPoint(2F, 6F, 7F);
		Dart6.setTextureSize(128, 64);
		Dart6.mirror = true;
		setRotation(Dart6, 0.5235988F, -0.1745329F, 0F);
		Dart6.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		LegTopRight.render(f5);
		LegTopLeft.render(f5);
		LegMidRight.render(f5);
		LegMidLeft.render(f5);
		LegBottomRight.render(f5);
		LegBottomLeft.render(f5);
		FootRight.render(f5);
		FootLeft.render(f5);
		LegFeatheredRight.render(f5);
		LegFeatheredLeft.render(f5);
		BodyFront.render(f5);
		BodyBack.render(f5);
		ShoulderRight.render(f5);
		ShoulderLeft.render(f5);
		WingBaseRight.render(f5);
		WingBaseLeft.render(f5);
		WingRight.render(f5);
		WingLeft.render(f5);
		TailMiddle.render(f5);
		TailLeft.render(f5);
		TailRight.render(f5);
		NeckBase.render(f5);
		NeckTop.render(f5);
		Head.render(f5);
		BeakJaw.render(f5);
		Teeth.render(f5);
		FeatherRight.render(f5);
		FeatherLeft.render(f5);
		FeatherTop.render(f5);
		ToeLeftRight.render(f5);
		ToeMidRight.render(f5);
		ToeRightRight.render(f5);
		ToeLeftLeft.render(f5);
		ToeMidLeft.render(f5);
		ToeRightLeft.render(f5);
		Dart1.render(f5);
		Dart2.render(f5);
		Dart3.render(f5);
		Dart4.render(f5);
		Dart5.render(f5);
		Dart6.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		//super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		EntityCockatrice cockatrice = (EntityCockatrice) entity;
		
		float f6 = 3.141593F;
		
		this.Head.rotateAngleX = f4 / 57.29578F;
		this.Head.rotateAngleY = f3 / 57.29578F;
		
		float rightLegRotation;
		float leftLegRotation;
		
		float legSwingSpeed = 0.7F;
		float legSwingLength = 0.8F;
		
		if (cockatrice.onGround)
		{
			rightLegRotation = MathHelper.cos(f * legSwingSpeed) * legSwingLength * f1;
			leftLegRotation = MathHelper.cos(f * legSwingSpeed + 3.141593F) * legSwingLength * f1;
		} else {
			rightLegRotation = 0.0F;
			leftLegRotation = 0.0F;
		}

		this.LegFeatheredRight.rotateAngleX = -0.2094395F + rightLegRotation;
		this.LegTopRight.rotateAngleX = -0.2094395F + rightLegRotation;
		this.LegMidRight.rotateAngleX = 0.8726646F + rightLegRotation;
		this.LegBottomRight.rotateAngleX = -0.6981317F + rightLegRotation;
		this.ToeLeftRight.rotateAngleX = rightLegRotation;
		this.ToeMidRight.rotateAngleX = rightLegRotation;
		this.ToeRightRight.rotateAngleX = rightLegRotation;
		this.FootRight.rotateAngleX = rightLegRotation;
		
		this.LegFeatheredLeft.rotateAngleX = -0.2094395F + leftLegRotation;
		this.LegTopLeft.rotateAngleX = -0.2094395F + leftLegRotation;
		this.LegMidLeft.rotateAngleX = 0.8726646F + leftLegRotation;
		this.LegBottomLeft.rotateAngleX = -0.6981317F + leftLegRotation;
		this.ToeLeftLeft.rotateAngleX = leftLegRotation;
		this.ToeMidLeft.rotateAngleX = leftLegRotation;
		this.ToeRightLeft.rotateAngleX = leftLegRotation;
		this.FootLeft.rotateAngleX = leftLegRotation;
		
		if (f2 > 0F)
		{
			this.LegFeatheredRight.rotateAngleX += 0.8F;
			this.LegTopRight.rotateAngleX += 0.8F;
			this.LegMidRight.rotateAngleX += 0.8F;
			this.LegBottomRight.rotateAngleX += 0.8F;
			this.ToeLeftRight.rotateAngleX += 0.8F;
			this.ToeMidRight.rotateAngleX += 0.8F;
			this.ToeRightRight.rotateAngleX += 0.8F;
			this.FootRight.rotateAngleX += 0.8F;
			
			this.LegFeatheredLeft.rotateAngleX += 0.8F;
			this.LegTopLeft.rotateAngleX += 0.8F;
			this.LegMidLeft.rotateAngleX += 0.8F;
			this.LegBottomLeft.rotateAngleX += 0.8F;
			this.ToeLeftLeft.rotateAngleX += 0.8F;
			this.ToeMidLeft.rotateAngleX += 0.8F;
			this.ToeRightLeft.rotateAngleX += 0.8F;
			this.FootLeft.rotateAngleX += 0.8F;
		}
		else
		{
			
		}

		/*this.Neck1.rotateAngleX = 0.0F;
		this.Neck2.rotateAngleX = 0.0F;
		this.Neck1.rotateAngleY = this.Head.rotateAngleY;
		this.Neck2.rotateAngleY = this.Head.rotateAngleY;*/
		
		this.FeatherTop.rotateAngleY = this.Head.rotateAngleY;
		this.FeatherTop.rotateAngleX = 0.3490659F + this.Head.rotateAngleX;
		this.FeatherRight.rotateAngleY = -0.4363323F + this.Head.rotateAngleY;
		this.FeatherRight.rotateAngleX = this.Head.rotateAngleX;
		this.FeatherLeft.rotateAngleY = 0.4363323F + this.Head.rotateAngleY;
		this.FeatherLeft.rotateAngleX = this.Head.rotateAngleX;
		
		this.BeakJaw.rotateAngleY = this.Head.rotateAngleY;
		this.BeakJaw.rotateAngleX = 0.1745329F + this.Head.rotateAngleX;
		this.Teeth.rotateAngleY = this.Head.rotateAngleY;
		this.Teeth.rotateAngleX = 0.1745329F + this.Head.rotateAngleX;
		
		/*this.feather1.rotateAngleX = 0.1745329F + this.Head.rotateAngleX;
		this.feather1.rotateAngleY = 0.2268928F + this.Head.rotateAngleY;
		
		this.feather2.rotateAngleX = -0.1745329F + this.Head.rotateAngleX;
		this.feather2.rotateAngleY = -0.2268928F + this.Head.rotateAngleY;

		this.feather3.rotateAngleX = 0.1745329F + this.Head.rotateAngleX;
		this.feather3.rotateAngleY = -0.2268928F + this.Head.rotateAngleY;
		
		this.feather4.rotateAngleX = -0.1745329F + this.Head.rotateAngleX;
		this.feather4.rotateAngleY = 0.2268928F + this.Head.rotateAngleY;*/
	}

}
