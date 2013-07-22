package net.minecraft.src;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

abstract class ComponentNetherBridgePiece extends StructureComponent
{
	protected static final WeightedRandomChestContent[] field_111019_a = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.diamond.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 5, 5), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.swordGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.flintAndSteel.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.netherStalkSeeds.itemID, 0, 3, 7, 5), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.field_111216_cf.itemID, 0, 1, 1, 8), new WeightedRandomChestContent(Item.field_111215_ce.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.field_111213_cg.itemID, 0, 1, 1, 3) };
	
	protected ComponentNetherBridgePiece(int par1)
	{
		super(par1);
	}
	
	private ComponentNetherBridgePiece getNextComponent(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, List par3List, Random par4Random, int par5, int par6, int par7, int par8, int par9)
	{
		int var10 = getTotalWeight(par2List);
		boolean var11 = var10 > 0 && par9 <= 30;
		int var12 = 0;
		while(var12 < 5 && var11)
		{
			++var12;
			int var13 = par4Random.nextInt(var10);
			Iterator var14 = par2List.iterator();
			while(var14.hasNext())
			{
				StructureNetherBridgePieceWeight var15 = (StructureNetherBridgePieceWeight) var14.next();
				var13 -= var15.field_78826_b;
				if(var13 < 0)
				{
					if(!var15.func_78822_a(par9) || var15 == par1ComponentNetherBridgeStartPiece.theNetherBridgePieceWeight && !var15.field_78825_e)
					{
						break;
					}
					ComponentNetherBridgePiece var16 = StructureNetherBridgePieces.createNextComponent(var15, par3List, par4Random, par5, par6, par7, par8, par9);
					if(var16 != null)
					{
						++var15.field_78827_c;
						par1ComponentNetherBridgeStartPiece.theNetherBridgePieceWeight = var15;
						if(!var15.func_78823_a())
						{
							par2List.remove(var15);
						}
						return var16;
					}
				}
			}
		}
		return ComponentNetherBridgeEnd.func_74971_a(par3List, par4Random, par5, par6, par7, par8, par9);
	}
	
	private StructureComponent getNextComponent(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, int par6, int par7, int par8, boolean par9)
	{
		if(Math.abs(par4 - par1ComponentNetherBridgeStartPiece.getBoundingBox().minX) <= 112 && Math.abs(par6 - par1ComponentNetherBridgeStartPiece.getBoundingBox().minZ) <= 112)
		{
			List var10 = par1ComponentNetherBridgeStartPiece.primaryWeights;
			if(par9)
			{
				var10 = par1ComponentNetherBridgeStartPiece.secondaryWeights;
			}
			ComponentNetherBridgePiece var11 = this.getNextComponent(par1ComponentNetherBridgeStartPiece, var10, par2List, par3Random, par4, par5, par6, par7, par8 + 1);
			if(var11 != null)
			{
				par2List.add(var11);
				par1ComponentNetherBridgeStartPiece.field_74967_d.add(var11);
			}
			return var11;
		} else return ComponentNetherBridgeEnd.func_74971_a(par2List, par3Random, par4, par5, par6, par7, par8);
	}
	
	protected StructureComponent getNextComponentNormal(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
	{
		switch(coordBaseMode)
		{
			case 0:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX + par4, boundingBox.minY + par5, boundingBox.maxZ + 1, coordBaseMode, getComponentType(), par6);
			case 1:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX - 1, boundingBox.minY + par5, boundingBox.minZ + par4, coordBaseMode, getComponentType(), par6);
			case 2:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX + par4, boundingBox.minY + par5, boundingBox.minZ - 1, coordBaseMode, getComponentType(), par6);
			case 3:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.maxX + 1, boundingBox.minY + par5, boundingBox.minZ + par4, coordBaseMode, getComponentType(), par6);
			default:
				return null;
		}
	}
	
	protected StructureComponent getNextComponentX(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
	{
		switch(coordBaseMode)
		{
			case 0:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX - 1, boundingBox.minY + par4, boundingBox.minZ + par5, 1, getComponentType(), par6);
			case 1:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.minZ - 1, 2, getComponentType(), par6);
			case 2:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX - 1, boundingBox.minY + par4, boundingBox.minZ + par5, 1, getComponentType(), par6);
			case 3:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.minZ - 1, 2, getComponentType(), par6);
			default:
				return null;
		}
	}
	
	protected StructureComponent getNextComponentZ(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
	{
		switch(coordBaseMode)
		{
			case 0:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.maxX + 1, boundingBox.minY + par4, boundingBox.minZ + par5, 3, getComponentType(), par6);
			case 1:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.maxZ + 1, 0, getComponentType(), par6);
			case 2:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.maxX + 1, boundingBox.minY + par4, boundingBox.minZ + par5, 3, getComponentType(), par6);
			case 3:
				return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.maxZ + 1, 0, getComponentType(), par6);
			default:
				return null;
		}
	}
	
	private int getTotalWeight(List par1List)
	{
		boolean var2 = false;
		int var3 = 0;
		StructureNetherBridgePieceWeight var5;
		for(Iterator var4 = par1List.iterator(); var4.hasNext(); var3 += var5.field_78826_b)
		{
			var5 = (StructureNetherBridgePieceWeight) var4.next();
			if(var5.field_78824_d > 0 && var5.field_78827_c < var5.field_78824_d)
			{
				var2 = true;
			}
		}
		return var2 ? var3 : -1;
	}
	
	protected static boolean isAboveGround(StructureBoundingBox par0StructureBoundingBox)
	{
		return par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10;
	}
}
