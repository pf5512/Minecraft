package net.minecraft.src;

public class ContainerHopper extends Container
{
	private final IInventory field_94538_a;
	
	public ContainerHopper(InventoryPlayer p_i10056_1_, IInventory p_i10056_2_)
	{
		field_94538_a = p_i10056_2_;
		p_i10056_2_.openChest();
		byte var3 = 51;
		int var4;
		for(var4 = 0; var4 < p_i10056_2_.getSizeInventory(); ++var4)
		{
			addSlotToContainer(new Slot(p_i10056_2_, var4, 44 + var4 * 18, 20));
		}
		for(var4 = 0; var4 < 3; ++var4)
		{
			for(int var5 = 0; var5 < 9; ++var5)
			{
				addSlotToContainer(new Slot(p_i10056_1_, var5 + var4 * 9 + 9, 8 + var5 * 18, var4 * 18 + var3));
			}
		}
		for(var4 = 0; var4 < 9; ++var4)
		{
			addSlotToContainer(new Slot(p_i10056_1_, var4, 8 + var4 * 18, 58 + var3));
		}
	}
	
	@Override public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return field_94538_a.isUseableByPlayer(p_75145_1_);
	}
	
	@Override public void onContainerClosed(EntityPlayer p_75134_1_)
	{
		super.onContainerClosed(p_75134_1_);
		field_94538_a.closeChest();
	}
	
	@Override public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
	{
		ItemStack var3 = null;
		Slot var4 = (Slot) inventorySlots.get(p_82846_2_);
		if(var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if(p_82846_2_ < field_94538_a.getSizeInventory())
			{
				if(!mergeItemStack(var5, field_94538_a.getSizeInventory(), inventorySlots.size(), true)) return null;
			} else if(!mergeItemStack(var5, 0, field_94538_a.getSizeInventory(), false)) return null;
			if(var5.stackSize == 0)
			{
				var4.putStack((ItemStack) null);
			} else
			{
				var4.onSlotChanged();
			}
		}
		return var3;
	}
}