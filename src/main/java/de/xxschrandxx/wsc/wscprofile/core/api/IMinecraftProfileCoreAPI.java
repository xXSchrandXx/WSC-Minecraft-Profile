package de.xxschrandxx.wsc.wscprofile.core.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import de.xxschrandxx.wsc.wscbridge.core.api.IMinecraftBridgeCoreAPI;
import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public interface IMinecraftProfileCoreAPI extends IMinecraftBridgeCoreAPI {
    public Response<String, Object> sendSkinData(ISender<?> sender, Boolean online) throws SocketTimeoutException, MalformedURLException, IOException;
}
