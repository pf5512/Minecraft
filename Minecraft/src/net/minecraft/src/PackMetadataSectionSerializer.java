package net.minecraft.src;

import java.lang.reflect.Type;

public class PackMetadataSectionSerializer extends BaseMetadataSectionSerializer implements JsonSerializer
{
	public Object deserialize(JsonElement par1JsonElement, Type par2Type, JsonDeserializationContext par3JsonDeserializationContext)
	{
		return func_110489_a(par1JsonElement, par2Type, par3JsonDeserializationContext);
	}
	
	@Override public String func_110483_a()
	{
		return "pack";
	}
	
	public JsonElement func_110488_a(PackMetadataSection par1PackMetadataSection, Type par2Type, JsonSerializationContext par3JsonSerializationContext)
	{
		JsonObject var4 = new JsonObject();
		var4.addProperty("pack_format", Integer.valueOf(par1PackMetadataSection.func_110462_b()));
		var4.addProperty("description", par1PackMetadataSection.func_110461_a());
		return var4;
	}
	
	public PackMetadataSection func_110489_a(JsonElement par1JsonElement, Type par2Type, JsonDeserializationContext par3JsonDeserializationContext)
	{
		JsonObject var4 = par1JsonElement.getAsJsonObject();
		String var5 = func_110486_a(var4.get("description"), "description", (String) null, 1, Integer.MAX_VALUE);
		int var6 = func_110485_a(var4.get("pack_format"), "pack_format", (Integer) null, 1, Integer.MAX_VALUE);
		return new PackMetadataSection(var5, var6);
	}
	
	public JsonElement serialize(Object par1Obj, Type par2Type, JsonSerializationContext par3JsonSerializationContext)
	{
		return func_110488_a((PackMetadataSection) par1Obj, par2Type, par3JsonSerializationContext);
	}
}
