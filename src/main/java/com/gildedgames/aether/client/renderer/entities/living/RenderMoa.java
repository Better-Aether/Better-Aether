package com.gildedgames.aether.client.renderer.entities.living;

import com.gildedgames.aether.client.models.entities.living.ModelMoa;
import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.entities.living.mounts.EntityMoa;
import com.gildedgames.aether.common.entities.util.AnimalGender;
import com.gildedgames.aether.common.genes.moa.MoaGenePool;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderMoa extends RenderLiving<EntityMoa>
{

	public static ResourceLocation FEATHERS = new ResourceLocation(AetherCore.MOD_ID, "textures/entities/moa/feathers.png");

	public static ResourceLocation BODY = new ResourceLocation(AetherCore.MOD_ID, "textures/entities/moa/body.png");

	public static ResourceLocation LEGS = new ResourceLocation(AetherCore.MOD_ID, "textures/entities/moa/legs.png");

	public static ResourceLocation BEAK = new ResourceLocation(AetherCore.MOD_ID, "textures/entities/moa/beak.png");

	public static ResourceLocation EYES = new ResourceLocation(AetherCore.MOD_ID, "textures/entities/moa/eyes.png");

	public static ResourceLocation TEETH = new ResourceLocation(AetherCore.MOD_ID, "textures/entities/moa/teeth.png");

	public static ResourceLocation TONGUE = new ResourceLocation(AetherCore.MOD_ID, "textures/entities/moa/tongue.png");
	
	public RenderMoa(RenderManager manager)
	{
		super(manager, new ModelMoa(), 1.0F);
	}

	protected float getWingRotation(EntityMoa moa, float f)
	{
		float f1 = moa.prevWingRotation + (moa.wingRotation - moa.prevWingRotation) * f;
		float f2 = moa.prevDestPos + (moa.destPos - moa.prevDestPos) * f;

		return (MathHelper.sin(f1) + 1.0F) * f2;
	}

	@Override
	protected float handleRotationFloat(EntityMoa entityliving, float f)
	{
		return this.getWingRotation(entityliving, f);
	}

	protected void scaleMoa(EntityMoa entityMoa)
	{
		float moaScale = entityMoa.isChild() ? 0.5f : 1.0f;
		
		moaScale += entityMoa.isGroupLeader() ? 0.15F : 0.0F;
		
		GL11.glScalef(moaScale, moaScale, moaScale);
	}

	@Override
	protected void preRenderCallback(EntityMoa entityliving, float f)
	{
		this.scaleMoa(entityliving);
	}

	public void renderMoa(int color, ResourceLocation texture, EntityLivingBase entity, float par7, ModelRenderer... models)
	{
		float red = ((color >> 16) & 0xff) / 255F;
		float green = ((color >> 8) & 0xff) / 255F;
		float blue = (color & 0xff) / 255F;
		
		GL11.glColor3f(red, green, blue);

		this.renderManager.renderEngine.bindTexture(texture);

		for (ModelRenderer model : models)
		{
			model.render(par7);
		}
	}

	public void renderMoa(int color, ResourceLocation texture, EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		ModelMoa model = ((ModelMoa) this.mainModel);
		
		float red = ((color >> 16) & 0xff) / 255F;
		float green = ((color >> 8) & 0xff) / 255F;
		float blue = (color & 0xff) / 255F;
		GL11.glColor3f(red, green, blue);

		this.renderManager.renderEngine.bindTexture(texture);
		
		model.LeftLeg2.isHidden = true;
		model.LeftLeg2.isHidden = true;
		model.RightLeg1.isHidden = true;
		model.RightLeg2.isHidden = true;
		this.mainModel.render(entity, par2, par3, par4, par5, par6, par7);
		model.LeftLeg2.isHidden = false;
		model.LeftLeg2.isHidden = false;
		model.RightLeg1.isHidden = false;
		model.RightLeg2.isHidden = false;
	}

	public Color darker(Color c, float factor)
	{
		return new Color(Math.max((int)(c.getRed()  * factor), 0), Math.max((int)(c.getGreen()* factor), 0), Math.max((int)(c.getBlue() * factor), 0), c.getAlpha());
	}

	private Color blend(Color c1, Color c2, float ratio)
	{
		if ( ratio > 1f ) ratio = 1f;
		else if ( ratio < 0f ) ratio = 0f;
		float iRatio = 1.0f - ratio;

		int i1 = c1.getRGB();
		int i2 = c2.getRGB();

		int a1 = (i1 >> 24 & 0xff);
		int r1 = ((i1 & 0xff0000) >> 16);
		int g1 = ((i1 & 0xff00) >> 8);
		int b1 = (i1 & 0xff);

		int a2 = (i2 >> 24 & 0xff);
		int r2 = ((i2 & 0xff0000) >> 16);
		int g2 = ((i2 & 0xff00) >> 8);
		int b2 = (i2 & 0xff);

		int a = (int)((a1 * iRatio) + (a2 * ratio));
		int r = (int)((r1 * iRatio) + (r2 * ratio));
		int g = (int)((g1 * iRatio) + (g2 * ratio));
		int b = (int)((b1 * iRatio) + (b2 * ratio));

		return new Color( a << 24 | r << 16 | g << 8 | b );
	}

	protected void renderMoa(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		EntityMoa moa = ((EntityMoa) entity);
		MoaGenePool genePool = moa.getGenePool();
		ModelMoa model = ((ModelMoa) this.mainModel);

		if (genePool == null || genePool.getFeathers() == null)
		{
			return;
		}
		
		GL11.glPushMatrix();

		GL11.glDepthMask(true);

		this.renderMoa(genePool.getFeathers().gene().data().getRGB(), BODY, entity, par2, par3, par4, par5, par6, par7);

		this.renderMoa(genePool.getKeratin().gene().data().getRGB(), LEGS, entity, par7, model.LeftLeg1, model.RightLeg1, model.LeftLeg2, model.RightLeg2, model.FootLeft, model.FootRight, model.Toe1Left, model.Toe1Right, model.Toe2Left, model.Toe2Right);

		this.renderMoa(genePool.getKeratin().gene().data().getRGB(), BEAK, entity, par7, model.Jaw, model.Head);
		this.renderMoa(genePool.getEyes().gene().data().getRGB(), EYES, entity, par7, model.Head);

		GL11.glScalef(1.001f, 1.001f, 1.001f);
		GL11.glTranslatef(0.0f, -0.001f, 0.001f);

		Color patternColor = genePool.getFeathers().gene().data().darker();

		this.renderMoa(patternColor.getRGB(), genePool.getMarks().gene().getHead(), entity, par7, model.Head);

		GL11.glTranslatef(0.0f, 0.0f, -0.001f);

		this.renderMoa(patternColor.getRGB(), genePool.getMarks().gene().getBack(), entity, par7, model.Body, model.Chest);
		this.renderMoa(patternColor.getRGB(), genePool.getMarks().gene().getWing(), entity, par7, model.LeftWing, model.RightWing);
		
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		this.renderManager.renderEngine.bindTexture(TONGUE);
		model.Jaw.render(par7);
		
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		this.renderManager.renderEngine.bindTexture(TEETH);
		model.Teeth.render(par7);
		
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		this.renderManager.renderEngine.bindTexture(LEGS);
		model.Claw1Left.render(par7);
		model.Claw2Left.render(par7);
		model.Claw3Left.render(par7);
		model.Claw1Right.render(par7);
		model.Claw2Right.render(par7);
		model.Claw3Right.render(par7);
		
		/*if (moa.getSaddled())
		{
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			this.renderManager.renderEngine.bindTexture(genePool.saddle);
			model.Body.render(par7);
			model.Chest.render(par7);
		}*/
		
		if (moa.getGender() == AnimalGender.MALE)
		{
			this.renderMoa(genePool.getFeathers().gene().data().getRGB(), FEATHERS, entity, par7, model.feather1, model.feather2, model.feather3, model.feather4);
		}

		GL11.glColor3f(1.0f, 1.0f, 1.0f);

		//GL11.glTranslatef(0.0f, -0.1f, 0.0f);

		//this.renderMoa(patternColor.getRGB(), genePool.getMarks().gene().getTail(), entity, par7, model.TailLeft, model.TailRight);

		GL11.glPopMatrix();
	}

	@Override
	protected void renderModel(EntityMoa entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.renderMoa(entity, par2, par3, par4, par5, par6, par7);
	}

	@Override
	public void doRender(EntityMoa entity, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMoa entity)
	{
		return null;
	}
}
