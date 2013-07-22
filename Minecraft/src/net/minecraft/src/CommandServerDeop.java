package net.minecraft.src;

import java.util.List;

import net.minecraft.server.MinecraftServer;

public class CommandServerDeop extends CommandBase
{
	@Override public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		return par2ArrayOfStr.length == 1 ? getListOfStringsFromIterableMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getConfigurationManager().getOps()) : null;
	}
	
	@Override public String getCommandName()
	{
		return "deop";
	}
	
	@Override public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "commands.deop.usage";
	}
	
	@Override public int getRequiredPermissionLevel()
	{
		return 3;
	}
	
	@Override public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		if(par2ArrayOfStr.length == 1 && par2ArrayOfStr[0].length() > 0)
		{
			MinecraftServer.getServer().getConfigurationManager().removeOp(par2ArrayOfStr[0]);
			notifyAdmins(par1ICommandSender, "commands.deop.success", new Object[] { par2ArrayOfStr[0] });
		} else throw new WrongUsageException("commands.deop.usage", new Object[0]);
	}
}
