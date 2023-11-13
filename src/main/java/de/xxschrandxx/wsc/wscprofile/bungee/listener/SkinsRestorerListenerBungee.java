package de.xxschrandxx.wsc.wscprofile.bungee.listener;

import java.io.IOException;
import java.util.function.Consumer;

import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import de.xxschrandxx.wsc.wscprofile.bungee.MinecraftProfileBungee;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.skinsrestorer.api.event.SkinApplyEvent;

public class SkinsRestorerListenerBungee implements Consumer<SkinApplyEvent> {
    public void accept(SkinApplyEvent event) {
        if (event.isCancelled()) {
            return;
        }
                if (event.isCancelled()) {
            return;
        }
        MinecraftProfileBungee instance = MinecraftProfileBungee.getInstance();
        try {
            instance.getAPI().sendSkinData(new SenderBungee(event.getPlayer(ProxiedPlayer.class), instance), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
