package de.xxschrandxx.wsc.wscprofile.bungee.listener;

import java.io.IOException;

import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import de.xxschrandxx.wsc.wscprofile.bungee.MinecraftProfileBungee;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListenerBungee implements Listener {
    @EventHandler
    public void onJoin(ServerConnectedEvent event) {
        MinecraftProfileBungee instance = MinecraftProfileBungee.getInstance();
        try {
            instance.getAPI().sendSkinData(new SenderBungee(event.getPlayer(), instance), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @EventHandler
    public void onLeave(PlayerDisconnectEvent event) {
        MinecraftProfileBungee instance = MinecraftProfileBungee.getInstance();
        try {
            instance.getAPI().sendSkinData(new SenderBungee(event.getPlayer(), instance), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
