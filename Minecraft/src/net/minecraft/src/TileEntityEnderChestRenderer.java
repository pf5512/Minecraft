package net.minecraft.src;


public class TileEntityEnderChestRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_110637_a = new ResourceLocation("textures/entity/chest/ender.png");
	private ModelChest theEnderChestModel = new ModelChest();
	
	public void renderEnderChest(TileEntityEnderChest par1TileEntityEnderChest, double par2, double par4, double par6, float par8)
	{
		int var9 = 0;
		if(par1TileEntityEnderChest.hasWorldObj())
		{
			var9 = par1TileEntityEnderChest.getBlockMetadata();
		}
		func_110628_a(field_110637_a);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par2, (float) par4 + 1.0F, (float) par6 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short var10 = 0;
		if(var9 == 2)
		{
			var10 = 180;
		}
		if(var9 == 3)
		{
			var10 = 0;
		}
		if(var9 == 4)
		{
			var10 = 90;
		}
		if(var9 == 5)
		{
			var10 = -90;
		}
		GL11.glRotatef((float) var10, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float var11 = par1TileEntityEnderChest.prevLidAngle + (par1TileEntityEnderChest.lidAngle - par1TileEntityEnderChest.prevLidAngle) * par8;
		var11 = 1.0F - var11;
		var11 = 1.0F - var11 * var11 * var11;
		theEnderChestModel.chestLid.rotateAngleX = -(var11 * (float) Math.PI / 2.0F);
		theEnderChestModel.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		renderEnderChest((TileEntityEnderChest) par1TileEntity, par2, par4, par6, par8);
	}
}
