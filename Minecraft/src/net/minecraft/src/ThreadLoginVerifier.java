package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;

class ThreadLoginVerifier extends Thread
{
	final NetLoginHandler loginHandler;
	
	ThreadLoginVerifier(NetLoginHandler par1NetLoginHandler)
	{
		loginHandler = par1NetLoginHandler;
	}
	
	@Override public void run()
	{
		try
		{
			String var1 = new BigInteger(CryptManager.getServerIdHash(NetLoginHandler.getServerId(loginHandler), NetLoginHandler.getLoginMinecraftServer(loginHandler).getKeyPair().getPublic(), NetLoginHandler.getSharedKey(loginHandler))).toString(16);
			URL var2 = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(NetLoginHandler.getClientUsername(loginHandler), "UTF-8") + "&serverId=" + URLEncoder.encode(var1, "UTF-8"));
			BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.openConnection(NetLoginHandler.getLoginMinecraftServer(loginHandler).func_110454_ao()).getInputStream()));
			String var4 = var3.readLine();
			var3.close();
			if(!"YES".equals(var4))
			{
				loginHandler.raiseErrorAndDisconnect("Failed to verify username!");
				return;
			}
			NetLoginHandler.func_72531_a(loginHandler, true);
		} catch(Exception var5)
		{
			loginHandler.raiseErrorAndDisconnect("Failed to verify username! [internal error " + var5 + "]");
			var5.printStackTrace();
		}
	}
}
