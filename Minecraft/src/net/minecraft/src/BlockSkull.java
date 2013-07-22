package net.minecraft.src;

import java.util.Random;

public class BlockSkull extends BlockContainer
{
	protected BlockSkull(int par1)
	{
		super(par1, Material.circuits);
		setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
	}
	
	@Override public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		if(!par1World.isRemote)
		{
			if((par6 & 8) == 0)
			{
				ItemStack var7 = new ItemStack(Item.skull.itemID, 1, getDamageValue(par1World, par2, par3, par4));
				TileEntitySkull var8 = (TileEntitySkull) par1World.getBlockTileEntity(par2, par3, par4);
				if(var8.getSkullType() == 3 && var8.getExtraType() != null && var8.getExtraType().length() > 0)
				{
					var7.setTagCompound(new NBTTagCompound());
					var7.getTagCompound().setString("SkullOwner", var8.getExtraType());
				}
				dropBlockAsItem_do(par1World, par2, par3, par4, var7);
			}
			super.breakBlock(par1World, par2, par3, par4, par5, par6);
		}
	}
	
	@Override public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntitySkull();
	}
	
	@Override public int damageDropped(int par1)
	{
		return par1;
	}
	
	@Override public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
	}
	
	private boolean func_82528_d(World par1World, int par2, int par3, int par4, int par5)
	{
		if(par1World.getBlockId(par2, par3, par4) != blockID) return false;
		else
		{
			TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
			return var6 != null && var6 instanceof TileEntitySkull ? ((TileEntitySkull) var6).getSkullType() == par5 : false;
		}
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	
	@Override public int getDamageValue(World par1World, int par2, int par3, int par4)
	{
		TileEntity var5 = par1World.getBlockTileEntity(par2, par3, par4);
		return var5 != null && var5 instanceof TileEntitySkull ? ((TileEntitySkull) var5).getSkullType() : super.getDamageValue(par1World, par2, par3, par4);
	}
	
	@Override public Icon getIcon(int par1, int par2)
	{
		return Block.slowSand.getBlockTextureFromSide(par1);
	}
	
	@Override public String getItemIconName()
	{
		return func_111023_E() + "_" + ItemSkull.field_94587_a[0];
	}
	
	@Override public int getRenderType()
	{
		return -1;
	}
	
	@Override public int idDropped(int par1, Random par2Random, int par3)
	{
		return Item.skull.itemID;
	}
	
	@Override public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return Item.skull.itemID;
	}
	
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
	
	public void makeWither(World par1World, int par2, int par3, int par4, TileEntitySkull par5TileEntitySkull)
	{
		if(par5TileEntitySkull.getSkullType() == 1 && par3 >= 2 && par1World.difficultySetting > 0 && !par1World.isRemote)
		{
			int var6 = Block.slowSand.blockID;
			int var7;
			EntityWither var8;
			int var9;
			for(var7 = -2; var7 <= 0; ++var7)
			{
				if(par1World.getBlockId(par2, par3 - 1, par4 + var7) == var6 && par1World.getBlockId(par2, par3 - 1, par4 + var7 + 1) == var6 && par1World.getBlockId(par2, par3 - 2, par4 + var7 + 1) == var6 && par1World.getBlockId(par2, par3 - 1, par4 + var7 + 2) == var6 && func_82528_d(par1World, par2, par3, par4 + var7, 1) && func_82528_d(par1World, par2, par3, par4 + var7 + 1, 1) && func_82528_d(par1World, par2, par3, par4 + var7 + 2, 1))
				{
					par1World.setBlockMetadataWithNotify(par2, par3, par4 + var7, 8, 2);
					par1World.setBlockMetadataWithNotify(par2, par3, par4 + var7 + 1, 8, 2);
					par1World.setBlockMetadataWithNotify(par2, par3, par4 + var7 + 2, 8, 2);
					par1World.setBlock(par2, par3, par4 + var7, 0, 0, 2);
					par1World.setBlock(par2, par3, par4 + var7 + 1, 0, 0, 2);
					par1World.setBlock(par2, par3, par4 + var7 + 2, 0, 0, 2);
					par1World.setBlock(par2, par3 - 1, par4 + var7, 0, 0, 2);
					par1World.setBlock(par2, par3 - 1, par4 + var7 + 1, 0, 0, 2);
					par1World.setBlock(par2, par3 - 1, par4 + var7 + 2, 0, 0, 2);
					par1World.setBlock(par2, par3 - 2, par4 + var7 + 1, 0, 0, 2);
					if(!par1World.isRemote)
					{
						var8 = new EntityWither(par1World);
						var8.setLocationAndAngles(par2 + 0.5D, par3 - 1.45D, par4 + var7 + 1.5D, 90.0F, 0.0F);
						var8.renderYawOffset = 90.0F;
						var8.func_82206_m();
						par1World.spawnEntityInWorld(var8);
					}
					for(var9 = 0; var9 < 120; ++var9)
					{
						par1World.spawnParticle("snowballpoof", par2 + par1World.rand.nextDouble(), par3 - 2 + par1World.rand.nextDouble() * 3.9D, par4 + var7 + 1 + par1World.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
					}
					par1World.notifyBlockChange(par2, par3, par4 + var7, 0);
					par1World.notifyBlockChange(par2, par3, par4 + var7 + 1, 0);
					par1World.notifyBlockChange(par2, par3, par4 + var7 + 2, 0);
					par1World.notifyBlockChange(par2, par3 - 1, par4 + var7, 0);
					par1World.notifyBlockChange(par2, par3 - 1, par4 + var7 + 1, 0);
					par1World.notifyBlockChange(par2, par3 - 1, par4 + var7 + 2, 0);
					par1World.notifyBlockChange(par2, par3 - 2, par4 + var7 + 1, 0);
					return;
				}
			}
			for(var7 = -2; var7 <= 0; ++var7)
			{
				if(par1World.getBlockId(par2 + var7, par3 - 1, par4) == var6 && par1World.getBlockId(par2 + var7 + 1, par3 - 1, par4) == var6 && par1World.getBlockId(par2 + var7 + 1, par3 - 2, par4) == var6 && par1World.getBlockId(par2 + var7 + 2, par3 - 1, par4) == var6 && func_82528_d(par1World, par2 + var7, par3, par4, 1) && func_82528_d(par1World, par2 + var7 + 1, par3, par4, 1) && func_82528_d(par1World, par2 + var7 + 2, par3, par4, 1))
				{
					par1World.setBlockMetadataWithNotify(par2 + var7, par3, par4, 8, 2);
					par1World.setBlockMetadataWithNotify(par2 + var7 + 1, par3, par4, 8, 2);
					par1World.setBlockMetadataWithNotify(par2 + var7 + 2, par3, par4, 8, 2);
					par1World.setBlock(par2 + var7, par3, par4, 0, 0, 2);
					par1World.setBlock(par2 + var7 + 1, par3, par4, 0, 0, 2);
					par1World.setBlock(par2 + var7 + 2, par3, par4, 0, 0, 2);
					par1World.setBlock(par2 + var7, par3 - 1, par4, 0, 0, 2);
					par1World.setBlock(par2 + var7 + 1, par3 - 1, par4, 0, 0, 2);
					par1World.setBlock(par2 + var7 + 2, par3 - 1, par4, 0, 0, 2);
					par1World.setBlock(par2 + var7 + 1, par3 - 2, par4, 0, 0, 2);
					if(!par1World.isRemote)
					{
						var8 = new EntityWither(par1World);
						var8.setLocationAndAngles(par2 + var7 + 1.5D, par3 - 1.45D, par4 + 0.5D, 0.0F, 0.0F);
						var8.func_82206_m();
						par1World.spawnEntityInWorld(var8);
					}
					for(var9 = 0; var9 < 120; ++var9)
					{
						par1World.spawnParticle("snowballpoof", par2 + var7 + 1 + par1World.rand.nextDouble(), par3 - 2 + par1World.rand.nextDouble() * 3.9D, par4 + par1World.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
					}
					par1World.notifyBlockChange(par2 + var7, par3, par4, 0);
					par1World.notifyBlockChange(par2 + var7 + 1, par3, par4, 0);
					par1World.notifyBlockChange(par2 + var7 + 2, par3, par4, 0);
					par1World.notifyBlockChange(par2 + var7, par3 - 1, par4, 0);
					par1World.notifyBlockChange(par2 + var7 + 1, par3 - 1, par4, 0);
					par1World.notifyBlockChange(par2 + var7 + 2, par3 - 1, par4, 0);
					par1World.notifyBlockChange(par2 + var7 + 1, par3 - 2, par4, 0);
					return;
				}
			}
		}
	}
	
	@Override public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
	{
		if(par6EntityPlayer.capabilities.isCreativeMode)
		{
			par5 |= 8;
			par1World.setBlockMetadataWithNotify(par2, par3, par4, par5, 4);
		}
		super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
	}
	
	@Override public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int var7 = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var7, 2);
	}
	
	@Override public void registerIcons(IconRegister par1IconRegister)
	{
	}
	
	@Override public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 7;
		switch(var5)
		{
			case 1:
			default:
				setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
				break;
			case 2:
				setBlockBounds(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
				break;
			case 3:
				setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
				break;
			case 4:
				setBlockBounds(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
				break;
			case 5:
				setBlockBounds(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
		}
	}
}
