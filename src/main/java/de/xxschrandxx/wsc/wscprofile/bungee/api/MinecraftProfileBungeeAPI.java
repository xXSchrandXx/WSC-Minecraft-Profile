package de.xxschrandxx.wsc.wscprofile.bungee.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.logging.Logger;

import de.xxschrandxx.wsc.wscbridge.bungee.api.MinecraftBridgeBungeeAPI;
import de.xxschrandxx.wsc.wscbridge.core.api.MinecraftBridgeCoreAPI;
import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscprofile.core.api.IMinecraftProfileCoreAPI;
import de.xxschrandxx.wsc.wscprofile.core.api.MinecraftProfileCoreAPI;

public class MinecraftProfileBungeeAPI extends MinecraftBridgeBungeeAPI implements IMinecraftProfileCoreAPI {

    protected final URL url;

    public MinecraftProfileBungeeAPI(URL url, Logger logger, MinecraftBridgeCoreAPI api) {
        super(api.getID(), api.getAuth(), logger, api.isDebugModeEnabled());
        this.url = url;
    }

    public Response<String, Object> sendSkinData(ISender<?> sender, Boolean online) throws SocketTimeoutException, MalformedURLException, IOException {
        return MinecraftProfileCoreAPI.sendSkinData(this, url, sender, online);
    }
}
