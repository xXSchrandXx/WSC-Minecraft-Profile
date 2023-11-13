package de.xxschrandxx.wsc.wscprofile.bukkit.listener;

import java.io.IOException;
import java.util.function.Consumer;

import org.bukkit.entity.Player;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;
import de.xxschrandxx.wsc.wscprofile.bukkit.MinecraftProfileBukkit;
import net.skinsrestorer.api.event.SkinApplyEvent;

public class SkinsRestorerListenerBukkit implements Consumer<SkinApplyEvent> {
    public void accept(SkinApplyEvent event) {
        if (event.isCancelled()) {
            return;
        }
                if (event.isCancelled()) {
            return;
        }
        MinecraftProfileBukkit instance = MinecraftProfileBukkit.getInstance();
        try {
            instance.getAPI().sendSkinData(new SenderBukkit(event.getPlayer(Player.class), instance), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
