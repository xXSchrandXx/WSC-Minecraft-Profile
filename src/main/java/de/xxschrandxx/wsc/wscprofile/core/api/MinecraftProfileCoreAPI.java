package de.xxschrandxx.wsc.wscprofile.core.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;

import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.property.IProperty;

public class MinecraftProfileCoreAPI {
    public static Response<String, Object> sendSkinData(IMinecraftProfileCoreAPI api, URL url, ISender<?> sender, Boolean online) throws SocketTimeoutException, MalformedURLException, IOException {
        SkinsRestorerAPI skinsRestorerAPI = SkinsRestorerAPI.getApi();

        IProperty skinData;
        if (skinsRestorerAPI.hasSkin(sender.getName())) {
            String skinName = skinsRestorerAPI.getSkinName(sender.getName());
            skinData = skinsRestorerAPI.getSkinData(skinName);
        }
        else {
            skinData = skinsRestorerAPI.getProfile(sender.getUniqueId().toString());
        }

        HashMap<String, Object> request = new HashMap<String, Object>();

        request.put("uuid", sender.getUniqueId().toString());
        request.put("name", sender.getName());
        request.put("url", skinsRestorerAPI.getSkinTextureUrl(skinData));
        if (online) {
            request.put("online", 1);
        } else {
            request.put("online", 0);
        }

        return api.requestObject(url, request);
    }
}
