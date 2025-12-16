package de.xxschrandxx.wsc.wscprofile.bukkit;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import de.xxschrandxx.wsc.wscbridge.bukkit.MinecraftBridgeBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.ConfigurationBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;
import de.xxschrandxx.wsc.wscbridge.core.IMinecraftBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscprofile.bukkit.api.MinecraftProfileBukkitAPI;
import de.xxschrandxx.wsc.wscprofile.bukkit.listener.*;
import de.xxschrandxx.wsc.wscprofile.core.MinecraftProfileVars;
import net.skinsrestorer.api.SkinsRestorer;
import net.skinsrestorer.api.SkinsRestorerProvider;
import net.skinsrestorer.api.event.SkinApplyEvent;

public class MinecraftProfileBukkit extends JavaPlugin implements IMinecraftBridgePlugin<MinecraftProfileBukkitAPI> {

    // start of api part
    public String getInfo() {
        return null;
    }

    private static MinecraftProfileBukkit instance;

    public static MinecraftProfileBukkit getInstance() {
        return instance;
    }

    private MinecraftProfileBukkitAPI api;

    public void loadAPI(ISender<?> sender) {
        String urlString = getConfiguration().getString(MinecraftProfileVars.Configuration.url);
        URL url;
        try {
            url = URI.create(urlString).toURL();
        } catch (MalformedURLException e) {
            getLogger().log(Level.INFO, "Could not load api, disabeling plugin!.", e);
            return;
        }
        MinecraftBridgeBukkit wsc = MinecraftBridgeBukkit.getInstance();
        this.api = new MinecraftProfileBukkitAPI(
            url,
            getLogger(),
            wsc.getAPI()
        );
    }
    public MinecraftProfileBukkitAPI getAPI() {
        return this.api;
    }
    // end of api part

    // start of plugin part
    @Override
    public void onEnable() {
        instance = this;

        // Load configuration
        getLogger().log(Level.INFO, "Loading Configuration.");
        SenderBukkit sender = new SenderBukkit(getServer().getConsoleSender(), getInstance());
        if (!reloadConfiguration(sender)) {
            getLogger().log(Level.WARNING, "Could not load config.yml, disabeling plugin!");
            onDisable();
            return;
        }

        // Load api
        getLogger().log(Level.INFO, "Loading API.");
        loadAPI(sender);

        // Load listener
        getLogger().log(Level.INFO, "Loading Listener.");
        getServer().getPluginManager().registerEvents(new WSCBridgeConfigReloadListenerBukkit(), getInstance());
        getServer().getPluginManager().registerEvents(new WSCBridgePluginReloadListenerBukkit(), getInstance());
        getServer().getPluginManager().registerEvents(new AddModuleListenerBukkit(), getInstance());
        getServer().getPluginManager().registerEvents(new PlayerListenerBukkit(), getInstance());

        // Add SkinsRestorer Listener
        SkinsRestorer skinsRestorerAPI = SkinsRestorerProvider.get();
        skinsRestorerAPI.getEventBus().subscribe(this, SkinApplyEvent.class, new SkinsRestorerListenerBukkit());
    }

    @Override
    public void onDisable() {
    }
    // end of plugin part

    // start config part
    public ConfigurationBukkit getConfiguration() {
        return new ConfigurationBukkit(getInstance().getConfig());
    }

    public boolean reloadConfiguration(ISender<?> sender) {
        reloadConfig();

        if (MinecraftProfileVars.startConfig(getConfiguration(), getLogger())) {
            if (!saveConfiguration()) {
                return false;
            }
            return reloadConfiguration(sender);
        }
        return true;
    }

    public boolean saveConfiguration() {
        saveConfig();
        return true;
    }
    // end config part
}
