package de.xxschrandxx.wsc.wscprofile.bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgePluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscprofile.bukkit.MinecraftProfileBukkit;
import de.xxschrandxx.wsc.wscprofile.bukkit.api.event.WSCProfilePluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscprofile.core.MinecraftProfileVars;

public class WSCBridgePluginReloadListenerBukkit implements Listener {
    @EventHandler
    public void onPluginReload(WSCBridgePluginReloadEventBukkit event) {
        MinecraftProfileBukkit instance = MinecraftProfileBukkit.getInstance();
        String apiStart = instance.getConfiguration().getString(MinecraftProfileVars.Configuration.LangCmdReloadAPIStart);
        event.getSender().sendMessage(apiStart);
        instance.loadAPI(event.getSender());
        String apiSuccess = instance.getConfiguration().getString(MinecraftProfileVars.Configuration.LangCmdReloadAPISuccess);
        event.getSender().sendMessage(apiSuccess);
        instance.getServer().getPluginManager().callEvent(new WSCProfilePluginReloadEventBukkit(event.getSender()));
    }
}
