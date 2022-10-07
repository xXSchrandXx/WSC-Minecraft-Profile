package de.xxschrandxx.wsc.wscprofile.bukkit.listener;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;
import de.xxschrandxx.wsc.wscprofile.bukkit.MinecraftProfileBukkit;

public class PlayerListenerBukkit implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        MinecraftProfileBukkit instance = MinecraftProfileBukkit.getInstance();
        try {
            instance.getAPI().sendSkinData(new SenderBukkit(event.getPlayer(), instance), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        MinecraftProfileBukkit instance = MinecraftProfileBukkit.getInstance();
        try {
            instance.getAPI().sendSkinData(new SenderBukkit(event.getPlayer(), instance), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
