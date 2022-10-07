package de.xxschrandxx.wsc.wscprofile.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgeConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCJProfileConfigReloadEventBukkit extends WSCBridgeConfigReloadEventBukkit {
    public WSCJProfileConfigReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }   
}
