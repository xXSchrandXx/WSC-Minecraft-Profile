package de.xxschrandxx.wsc.wscprofile.bungee.listener;

import java.util.logging.Level;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgeConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscprofile.bungee.MinecraftProfileBungee;
import de.xxschrandxx.wsc.wscprofile.bungee.api.event.WSCProfileConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscprofile.core.MinecraftProfileVars;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WSCBridgeConfigReloadListenerBungee implements Listener {
    @EventHandler
    public void onConfigReload(WSCBridgeConfigReloadEventBungee event) {
        MinecraftProfileBungee instance = MinecraftProfileBungee.getInstance();
        String configStart = instance.getConfiguration().getString(MinecraftProfileVars.Configuration.LangCmdReloadConfigStart);
        event.getSender().sendMessage(configStart);
        if (!instance.reloadConfiguration(event.getSender())) {
            String configError = instance.getConfiguration().getString(MinecraftProfileVars.Configuration.LangCmdReloadConfigError);
            event.getSender().sendMessage(configError);
            instance.getLogger().log(Level.WARNING, "Could not load config.yml!");
            return;
        }
        String configSuccess = instance.getConfiguration().getString(MinecraftProfileVars.Configuration.LangCmdReloadConfigSuccess);
        event.getSender().sendMessage(configSuccess);
        instance.getProxy().getPluginManager().callEvent(new WSCProfileConfigReloadEventBungee(event.getSender()));
    }
}
