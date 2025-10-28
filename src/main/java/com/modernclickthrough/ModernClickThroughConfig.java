package com.modernclickthrough;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("modernclickthrough")
public interface ModernClickThroughConfig extends Config
{
	@ConfigItem(
		keyName = "hideTopStones",
		name = "Hide Top Menu Stones",
		description = "Toggles whether the top menu stones are displayed or not.",
		position = 0
	)
	default boolean hideTopStones()
	{
		return true;
	}

	@ConfigItem(
			keyName = "hideBottomStones",
			name = "Hide Bottom Menu Stones",
			description = "Toggles whether the bottom menu stones are displayed or not.",
			position = 1
	)
	default boolean  hideBottomStones()
	{
		return true;
	}

	@ConfigItem(
			keyName= "clickThrough",
			name = "Enable Click through inventory",
			description = "Toggles whether or not the inventory is able to be clicked through.",
			position = 3
	)
	default boolean clickThrough()
	{
		return false;
	}
}
