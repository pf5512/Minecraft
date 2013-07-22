package net.minecraft.src;


public class RenderGhast extends RenderLiving
{
	public RenderGhast()
	{
		super(new ModelGhast(), 0.5F);
	}
	
	@Override protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
	{
		preRenderGhast((EntityGhast) par1EntityLiving, par2);
	}
	
	protected void preRenderGhast(EntityGhast par1EntityGhast, float par2)
	{
		float var4 = (par1EntityGhast.prevAttackCounter + (par1EntityGhast.attackCounter - par1EntityGhast.prevAttackCounter) * par2) / 20.0F;
		if(var4 < 0.0F)
		{
			var4 = 0.0F;
		}
		var4 = 1.0F / (var4 * var4 * var4 * var4 * var4 * 2.0F + 1.0F);
		float var5 = (8.0F + var4) / 2.0F;
		float var6 = (8.0F + 1.0F / var4) / 2.0F;
		GL11.glScalef(var6, var5, var6);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}