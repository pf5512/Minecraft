package net.minecraft.src;

import java.util.Random;

public class RenderDragon extends RenderLiving
{
	private static final ResourceLocation field_110842_f = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
	private static final ResourceLocation field_110843_g = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
	private static final ResourceLocation field_110845_h = new ResourceLocation("textures/entity/enderdragon/dragon_eyes.png");
	private static final ResourceLocation field_110844_k = new ResourceLocation("textures/entity/enderdragon/dragon.png");
	protected ModelDragon modelDragon;
	
	public RenderDragon()
	{
		super(new ModelDragon(0.0F), 0.5F);
		modelDragon = (ModelDragon) mainModel;
		setRenderPassModel(mainModel);
	}
	
	@Override public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderDragon((EntityDragon) par1Entity, par2, par4, par6, par8, par9);
	}
	
	@Override public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		renderDragon((EntityDragon) par1EntityLiving, par2, par4, par6, par8, par9);
	}
	
	@Override protected ResourceLocation func_110775_a(Entity par1Entity)
	{
		return func_110841_a((EntityDragon) par1Entity);
	}
	
	protected ResourceLocation func_110841_a(EntityDragon par1EntityDragon)
	{
		return field_110844_k;
	}
	
	public void renderDragon(EntityDragon par1EntityDragon, double par2, double par4, double par6, float par8, float par9)
	{
		BossStatus.func_82824_a(par1EntityDragon, false);
		super.doRenderLiving(par1EntityDragon, par2, par4, par6, par8, par9);
		if(par1EntityDragon.healingEnderCrystal != null)
		{
			float var10 = par1EntityDragon.healingEnderCrystal.innerRotation + par9;
			float var11 = MathHelper.sin(var10 * 0.2F) / 2.0F + 0.5F;
			var11 = (var11 * var11 + var11) * 0.2F;
			float var12 = (float) (par1EntityDragon.healingEnderCrystal.posX - par1EntityDragon.posX - (par1EntityDragon.prevPosX - par1EntityDragon.posX) * (1.0F - par9));
			float var13 = (float) (var11 + par1EntityDragon.healingEnderCrystal.posY - 1.0D - par1EntityDragon.posY - (par1EntityDragon.prevPosY - par1EntityDragon.posY) * (1.0F - par9));
			float var14 = (float) (par1EntityDragon.healingEnderCrystal.posZ - par1EntityDragon.posZ - (par1EntityDragon.prevPosZ - par1EntityDragon.posZ) * (1.0F - par9));
			float var15 = MathHelper.sqrt_float(var12 * var12 + var14 * var14);
			float var16 = MathHelper.sqrt_float(var12 * var12 + var13 * var13 + var14 * var14);
			GL11.glPushMatrix();
			GL11.glTranslatef((float) par2, (float) par4 + 2.0F, (float) par6);
			GL11.glRotatef((float) -Math.atan2(var14, var12) * 180.0F / (float) Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef((float) -Math.atan2(var15, var13) * 180.0F / (float) Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
			Tessellator var17 = Tessellator.instance;
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_CULL_FACE);
			func_110776_a(field_110843_g);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			float var18 = 0.0F - (par1EntityDragon.ticksExisted + par9) * 0.01F;
			float var19 = MathHelper.sqrt_float(var12 * var12 + var13 * var13 + var14 * var14) / 32.0F - (par1EntityDragon.ticksExisted + par9) * 0.01F;
			var17.startDrawing(5);
			byte var20 = 8;
			for(int var21 = 0; var21 <= var20; ++var21)
			{
				float var22 = MathHelper.sin(var21 % var20 * (float) Math.PI * 2.0F / var20) * 0.75F;
				float var23 = MathHelper.cos(var21 % var20 * (float) Math.PI * 2.0F / var20) * 0.75F;
				float var24 = var21 % var20 * 1.0F / var20;
				var17.setColorOpaque_I(0);
				var17.addVertexWithUV(var22 * 0.2F, var23 * 0.2F, 0.0D, var24, var19);
				var17.setColorOpaque_I(16777215);
				var17.addVertexWithUV(var22, var23, var16, var24, var18);
			}
			var17.draw();
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glShadeModel(GL11.GL_FLAT);
			RenderHelper.enableStandardItemLighting();
			GL11.glPopMatrix();
		}
	}
	
	protected void renderDragonDying(EntityDragon par1EntityDragon, float par2)
	{
		super.renderEquippedItems(par1EntityDragon, par2);
		Tessellator var3 = Tessellator.instance;
		if(par1EntityDragon.deathTicks > 0)
		{
			RenderHelper.disableStandardItemLighting();
			float var4 = (par1EntityDragon.deathTicks + par2) / 200.0F;
			float var5 = 0.0F;
			if(var4 > 0.8F)
			{
				var5 = (var4 - 0.8F) / 0.2F;
			}
			Random var6 = new Random(432L);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDepthMask(false);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, -1.0F, -2.0F);
			for(int var7 = 0; var7 < (var4 + var4 * var4) / 2.0F * 60.0F; ++var7)
			{
				GL11.glRotatef(var6.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(var6.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(var6.nextFloat() * 360.0F + var4 * 90.0F, 0.0F, 0.0F, 1.0F);
				var3.startDrawing(6);
				float var8 = var6.nextFloat() * 20.0F + 5.0F + var5 * 10.0F;
				float var9 = var6.nextFloat() * 2.0F + 1.0F + var5 * 2.0F;
				var3.setColorRGBA_I(16777215, (int) (255.0F * (1.0F - var5)));
				var3.addVertex(0.0D, 0.0D, 0.0D);
				var3.setColorRGBA_I(16711935, 0);
				var3.addVertex(-0.866D * var9, var8, -0.5F * var9);
				var3.addVertex(0.866D * var9, var8, -0.5F * var9);
				var3.addVertex(0.0D, var8, 1.0F * var9);
				var3.addVertex(-0.866D * var9, var8, -0.5F * var9);
				var3.draw();
			}
			GL11.glPopMatrix();
			GL11.glDepthMask(true);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glShadeModel(GL11.GL_FLAT);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			RenderHelper.enableStandardItemLighting();
		}
	}
	
	protected void renderDragonModel(EntityDragon par1EntityDragon, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		if(par1EntityDragon.deathTicks > 0)
		{
			float var8 = par1EntityDragon.deathTicks / 200.0F;
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glAlphaFunc(GL11.GL_GREATER, var8);
			func_110776_a(field_110842_f);
			mainModel.render(par1EntityDragon, par2, par3, par4, par5, par6, par7);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
			GL11.glDepthFunc(GL11.GL_EQUAL);
		}
		func_110777_b(par1EntityDragon);
		mainModel.render(par1EntityDragon, par2, par3, par4, par5, par6, par7);
		if(par1EntityDragon.hurtTime > 0)
		{
			GL11.glDepthFunc(GL11.GL_EQUAL);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
			mainModel.render(par1EntityDragon, par2, par3, par4, par5, par6, par7);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
		}
	}
	
	@Override protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
	{
		renderDragonDying((EntityDragon) par1EntityLivingBase, par2);
	}
	
	protected int renderGlow(EntityDragon par1EntityDragon, int par2, float par3)
	{
		if(par2 == 1)
		{
			GL11.glDepthFunc(GL11.GL_LEQUAL);
		}
		if(par2 != 0) return -1;
		else
		{
			func_110776_a(field_110845_h);
			float var4 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthFunc(GL11.GL_EQUAL);
			char var5 = 61680;
			int var6 = var5 % 65536;
			int var7 = var5 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0F, var7 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
			return 1;
		}
	}
	
	@Override protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		renderDragonModel((EntityDragon) par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
	}
	
	@Override public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
	{
		renderDragon((EntityDragon) par1EntityLivingBase, par2, par4, par6, par8, par9);
	}
	
	@Override protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	{
		rotateDragonBody((EntityDragon) par1EntityLivingBase, par2, par3, par4);
	}
	
	protected void rotateDragonBody(EntityDragon par1EntityDragon, float par2, float par3, float par4)
	{
		float var5 = (float) par1EntityDragon.getMovementOffsets(7, par4)[0];
		float var6 = (float) (par1EntityDragon.getMovementOffsets(5, par4)[1] - par1EntityDragon.getMovementOffsets(10, par4)[1]);
		GL11.glRotatef(-var5, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(var6 * 10.0F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.0F, 0.0F, 1.0F);
		if(par1EntityDragon.deathTime > 0)
		{
			float var7 = (par1EntityDragon.deathTime + par4 - 1.0F) / 20.0F * 1.6F;
			var7 = MathHelper.sqrt_float(var7);
			if(var7 > 1.0F)
			{
				var7 = 1.0F;
			}
			GL11.glRotatef(var7 * getDeathMaxRotation(par1EntityDragon), 0.0F, 0.0F, 1.0F);
		}
	}
	
	@Override protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return renderGlow((EntityDragon) par1EntityLivingBase, par2, par3);
	}
}
