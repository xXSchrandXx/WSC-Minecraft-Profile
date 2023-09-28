package de.xxschrandxx.wsc.wscprofile.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.AbstractWSCConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public final class WSCProfileConfigReloadEventBukkit extends AbstractWSCConfigReloadEventBukkit {
    public WSCProfileConfigReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }   
}
