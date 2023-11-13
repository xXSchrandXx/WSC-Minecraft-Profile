package de.xxschrandxx.wsc.wscprofile.core.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import net.skinsrestorer.api.PropertyUtils;
import net.skinsrestorer.api.SkinsRestorer;
import net.skinsrestorer.api.SkinsRestorerProvider;
import net.skinsrestorer.api.exception.DataRequestException;
import net.skinsrestorer.api.property.SkinProperty;
import net.skinsrestorer.api.storage.PlayerStorage;

public class MinecraftProfileCoreAPI {
    public static Response<String, Object> sendSkinData(IMinecraftProfileCoreAPI api, URL url, ISender<?> sender, Boolean online) throws SocketTimeoutException, MalformedURLException, IOException {
        HashMap<String, Object> request = new HashMap<String, Object>();

        // Load SkinsRestorer API
        SkinsRestorer skinsRestorerAPI = SkinsRestorerProvider.get();
        PlayerStorage playerStorage = skinsRestorerAPI.getPlayerStorage();
        try {
            Optional<SkinProperty> property = playerStorage.getSkinForPlayer(sender.getUniqueId(), sender.getName());

            if (property.isPresent()) {
                // Set API
                request.put("url", PropertyUtils.getSkinTextureUrl(property.get()));
                request.put("type", PropertyUtils.getSkinVariant(property.get()).toString());
            }
        } catch (DataRequestException e) {
            e.printStackTrace();
        }

        request.put("uuid", sender.getUniqueId().toString());
        request.put("name", sender.getName());
        if (online) {
            request.put("online", 1);
        } else {
            request.put("online", 0);
        }

        return api.requestObject(url, request);
    }
}
