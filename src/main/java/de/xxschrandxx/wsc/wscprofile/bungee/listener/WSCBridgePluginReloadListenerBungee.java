package de.xxschrandxx.wsc.wscprofile.bungee.listener;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgePluginReloadEventBungee;
import de.xxschrandxx.wsc.wscprofile.bungee.MinecraftProfileBungee;
import de.xxschrandxx.wsc.wscprofile.bungee.api.event.WSCProfilePluginReloadEventBungee;
import de.xxschrandxx.wsc.wscprofile.core.MinecraftProfileVars;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WSCBridgePluginReloadListenerBungee implements Listener {
    @EventHandler
    public void onPluginReload(WSCBridgePluginReloadEventBungee event) {
        MinecraftProfileBungee instance = MinecraftProfileBungee.getInstance();
        String apiStart = instance.getConfiguration().getString(MinecraftProfileVars.Configuration.LangCmdReloadAPIStart);
        event.getSender().sendMessage(apiStart);
        instance.loadAPI(event.getSender());
        String apiSuccess = instance.getConfiguration().getString(MinecraftProfileVars.Configuration.LangCmdReloadAPISuccess);
        event.getSender().sendMessage(apiSuccess);
        instance.getProxy().getPluginManager().callEvent(new WSCProfilePluginReloadEventBungee(event.getSender()));
    }
}
