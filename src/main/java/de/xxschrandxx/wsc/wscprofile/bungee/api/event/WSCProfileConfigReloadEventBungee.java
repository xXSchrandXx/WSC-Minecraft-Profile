package de.xxschrandxx.wsc.wscprofile.bungee.api.event;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgeConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCProfileConfigReloadEventBungee extends WSCBridgeConfigReloadEventBungee {
    public WSCProfileConfigReloadEventBungee(ISender<?> sender) {
        super(sender);
    }   
}
