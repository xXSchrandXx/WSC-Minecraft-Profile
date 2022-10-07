package de.xxschrandxx.wsc.wscprofile.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgePluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCProfilePluginReloadEventBukkit extends WSCBridgePluginReloadEventBukkit {
    public WSCProfilePluginReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }    
}
