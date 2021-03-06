package net.minecraft.src;


public class GuiChest extends GuiContainer
{
	private static final ResourceLocation field_110421_t = new ResourceLocation("textures/gui/container/generic_54.png");
	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;
	private int inventoryRows;
	
	public GuiChest(IInventory par1IInventory, IInventory par2IInventory)
	{
		super(new ContainerChest(par1IInventory, par2IInventory));
		upperChestInventory = par1IInventory;
		lowerChestInventory = par2IInventory;
		allowUserInput = false;
		short var3 = 222;
		int var4 = var3 - 108;
		inventoryRows = par2IInventory.getSizeInventory() / 9;
		ySize = var4 + inventoryRows * 18;
	}
	
	@Override protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.func_110434_K().func_110577_a(field_110421_t);
		int var4 = (width - xSize) / 2;
		int var5 = (height - ySize) / 2;
		drawTexturedModalRect(var4, var5, 0, 0, xSize, inventoryRows * 18 + 17);
		drawTexturedModalRect(var4, var5 + inventoryRows * 18 + 17, 0, 126, xSize, 96);
	}
	
	@Override protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString(lowerChestInventory.isInvNameLocalized() ? lowerChestInventory.getInvName() : I18n.func_135053_a(lowerChestInventory.getInvName()), 8, 6, 4210752);
		fontRenderer.drawString(upperChestInventory.isInvNameLocalized() ? upperChestInventory.getInvName() : I18n.func_135053_a(upperChestInventory.getInvName()), 8, ySize - 96 + 2, 4210752);
	}
}
