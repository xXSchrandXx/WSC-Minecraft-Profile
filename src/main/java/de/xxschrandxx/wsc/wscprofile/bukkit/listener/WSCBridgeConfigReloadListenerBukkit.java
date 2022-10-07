package de.xxschrandxx.wsc.wscprofile.bukkit.listener;

import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgeConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscprofile.bukkit.MinecraftProfileBukkit;
import de.xxschrandxx.wsc.wscprofile.bukkit.api.event.WSCProfilePluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscprofile.core.MinecraftProfileVars;

public class WSCBridgeConfigReloadListenerBukkit implements Listener {
    @EventHandler
    public void onConfigReload(WSCBridgeConfigReloadEventBukkit event) {
        MinecraftProfileBukkit instance = MinecraftProfileBukkit.getInstance();
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
        instance.getServer().getPluginManager().callEvent(new WSCProfilePluginReloadEventBukkit(event.getSender()));
    }
}
