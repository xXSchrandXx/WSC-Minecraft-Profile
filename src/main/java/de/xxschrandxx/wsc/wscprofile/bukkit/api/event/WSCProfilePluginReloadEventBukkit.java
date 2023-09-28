package de.xxschrandxx.wsc.wscprofile.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.AbstractWSCPluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public final class WSCProfilePluginReloadEventBukkit extends AbstractWSCPluginReloadEventBukkit {
    public WSCProfilePluginReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }    
}
