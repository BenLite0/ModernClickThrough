package com.ModernClickThrough;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ScriptID;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Modern Click Through"
)
public class ModernClickThroughPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ModernClickThroughConfig config;

	private boolean toggled = true;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		updateOrShowWidgets(false);
		log.debug("stopped!");
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		if (toggled && (event.getGroupId() == InterfaceID.TOPLEVEL_PRE_EOC))
		{
			updateOrShowWidgets(true);
		}
	}

	@Subscribe
	public void onScriptPostFired(ScriptPostFired s)
	{
		if (!toggled && s.getScriptId() == 839)
		{
			updateOrShowWidgets(false);
		}

		if (toggled && (s.getScriptId() == ScriptID.TOPLEVEL_REDRAW || s.getScriptId() == 903))
		{
			updateOrShowWidgets(true);
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		updateOrShowWidgets(toggled);
	}


	@Provides
	ModernClickThroughConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ModernClickThroughConfig.class);
	}


	private void updateOrShowWidgets(boolean hide)
	{
		toggleWidget(InterfaceID.ToplevelPreEoc.SIDE_MOVABLE_LAYER, hide && config.hideTopStones());
		toggleWidget(InterfaceID.ToplevelPreEoc.SIDE_STATIC_LAYER, hide && config.hideBottomStones());

		toggleClickThrough(InterfaceID.ToplevelPreEoc.SIDE_CONTAINER, hide && config.clickThrough());

	}

	private void toggleWidget(int widgetId, boolean hide)
	{
		Widget widget = client.getWidget(widgetId);
		if (widget != null)
		{
			widget.setHidden(hide);
		}
	}

	private void toggleClickThrough(int widgetId, boolean enable)
	{
		Widget widget = client.getWidget(widgetId);
		if (widget != null)
		{
			widget.setNoClickThrough(!enable);
		}
	}
}