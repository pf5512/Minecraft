package net.minecraft.src;

import java.util.Random;

public class BlockNetherStalk extends BlockFlower
{
	private static final String[] field_94373_a = new String[] { "netherStalk_0", "netherStalk_1", "netherStalk_2" };
	private Icon[] iconArray;
	
	protected BlockNetherStalk(int par1)
	{
		super(par1);
		setTickRandomly(true);
		float var2 = 0.5F;
		setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.25F, 0.5F + var2);
		setCreativeTab((CreativeTabs) null);
	}
	
	@Override public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		return canThisPlantGrowOnThisBlockID(par1World.getBlockId(par2, par3 - 1, par4));
	}
	
	@Override protected boolean canThisPlantGrowOnThisBlockID(int par1)
	{
		return par1 == Block.slowSand.blockID;
	}
	
	@Override public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		if(!par1World.isRemote)
		{
			int var8 = 1;
			if(par5 >= 3)
			{
				var8 = 2 + par1World.rand.nextInt(3);
				if(par7 > 0)
				{
					var8 += par1World.rand.nextInt(par7 + 1);
				}
			}
			for(int var9 = 0; var9 < var8; ++var9)
			{
				dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(Item.netherStalkSeeds));
			}
		}
	}
	
	@Override public Icon getIcon(int par1, int par2)
	{
		return par2 >= 3 ? iconArray[2] : par2 > 0 ? iconArray[1] : iconArray[0];
	}
	
	@Override public int getRenderType()
	{
		return 6;
	}
	
	@Override public int idDropped(int par1, Random par2Random, int par3)
	{
		return 0;
	}
	
	@Override public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return Item.netherStalkSeeds.itemID;
	}
	
	@Override public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	@Override public void registerIcons(IconRegister par1IconRegister)
	{
		iconArray = new Icon[field_94373_a.length];
		for(int var2 = 0; var2 < iconArray.length; ++var2)
		{
			iconArray[var2] = par1IconRegister.registerIcon(field_94373_a[var2]);
		}
	}
	
	@Override public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int var6 = par1World.getBlockMetadata(par2, par3, par4);
		if(var6 < 3 && par5Random.nextInt(10) == 0)
		{
			++var6;
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 2);
		}
		super.updateTick(par1World, par2, par3, par4, par5Random);
	}
}