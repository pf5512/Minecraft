package net.minecraft.src;

import java.util.List;

import net.minecraft.server.MinecraftServer;

public class CommandServerEmote extends CommandBase
{
	@Override public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		return getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
	}
	
	@Override public String getCommandName()
	{
		return "me";
	}
	
	@Override public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "commands.me.usage";
	}
	
	@Override public int getRequiredPermissionLevel()
	{
		return 0;
	}
	
	@Override public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		if(par2ArrayOfStr.length > 0)
		{
			String var3 = func_82361_a(par1ICommandSender, par2ArrayOfStr, 0, par1ICommandSender.canCommandSenderUseCommand(1, "me"));
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(ChatMessageComponent.func_111082_b("chat.type.emote", new Object[] { par1ICommandSender.getCommandSenderName(), var3 }));
		} else throw new WrongUsageException("commands.me.usage", new Object[0]);
	}
}
