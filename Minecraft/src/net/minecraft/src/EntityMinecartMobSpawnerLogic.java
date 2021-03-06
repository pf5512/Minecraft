package net.minecraft.src;

class EntityMinecartMobSpawnerLogic extends MobSpawnerBaseLogic
{
	final EntityMinecartMobSpawner spawnerMinecart;
	
	EntityMinecartMobSpawnerLogic(EntityMinecartMobSpawner par1EntityMinecartMobSpawner)
	{
		spawnerMinecart = par1EntityMinecartMobSpawner;
	}
	
	@Override public void func_98267_a(int par1)
	{
		spawnerMinecart.worldObj.setEntityState(spawnerMinecart, (byte) par1);
	}
	
	@Override public World getSpawnerWorld()
	{
		return spawnerMinecart.worldObj;
	}
	
	@Override public int getSpawnerX()
	{
		return MathHelper.floor_double(spawnerMinecart.posX);
	}
	
	@Override public int getSpawnerY()
	{
		return MathHelper.floor_double(spawnerMinecart.posY);
	}
	
	@Override public int getSpawnerZ()
	{
		return MathHelper.floor_double(spawnerMinecart.posZ);
	}
}
