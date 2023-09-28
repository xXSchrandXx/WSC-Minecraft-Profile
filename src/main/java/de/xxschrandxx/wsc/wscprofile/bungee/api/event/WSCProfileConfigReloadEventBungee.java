package de.xxschrandxx.wsc.wscprofile.bungee.api.event;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.AbstractWSCConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public final class WSCProfileConfigReloadEventBungee extends AbstractWSCConfigReloadEventBungee {
    public WSCProfileConfigReloadEventBungee(ISender<?> sender) {
        super(sender);
    }   
}
