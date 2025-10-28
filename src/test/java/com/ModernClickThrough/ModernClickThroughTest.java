package com.ModernClickThrough;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ModernClickThroughTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ModernClickThroughPlugin.class);
		RuneLite.main(args);
	}
}