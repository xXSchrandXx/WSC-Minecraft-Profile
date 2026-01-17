package de.xxschrandxx.wsc.wscprofile.bungee;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;

import de.xxschrandxx.wsc.wscbridge.bungee.MinecraftBridgeBungee;
import de.xxschrandxx.wsc.wscbridge.bungee.api.ConfigurationBungee;
import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import de.xxschrandxx.wsc.wscbridge.core.IBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.MinecraftBridgeLogger;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscprofile.bungee.api.MinecraftProfileBungeeAPI;
import de.xxschrandxx.wsc.wscprofile.bungee.listener.*;
import de.xxschrandxx.wsc.wscprofile.core.MinecraftProfileVars;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.skinsrestorer.api.SkinsRestorer;
import net.skinsrestorer.api.SkinsRestorerProvider;
import net.skinsrestorer.api.event.SkinApplyEvent;

public class MinecraftProfileBungee extends Plugin implements IBridgePlugin<MinecraftProfileBungeeAPI> {

    // start of api part
    public String getInfo() {
        return null;
    }

    private static MinecraftProfileBungee instance;

    public static MinecraftProfileBungee getInstance() {
        return instance;
    }

    private MinecraftProfileBungeeAPI api;

    private MinecraftBridgeLogger bridgeLogger;

    @Override
    public MinecraftBridgeLogger getBridgeLogger() {
        return bridgeLogger;
    }

    public void loadAPI(ISender<?> sender) {
        String urlString = getConfiguration().getString(MinecraftProfileVars.Configuration.url);
        URL url;
        try {
            url = new URI(urlString).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            getLogger().log(Level.INFO, "Could not load api, disabeling plugin!.", e);
            return;
        }
        MinecraftBridgeBungee wsc = MinecraftBridgeBungee.getInstance();
        this.api = new MinecraftProfileBungeeAPI(
            url,
            getBridgeLogger(),
            wsc.getAPI()
        );
    }

    public MinecraftProfileBungeeAPI getAPI() {
        return this.api;
    }
    // end of api part

    // start of plugin part
    @Override
    public void onEnable() {
        instance = this;
        bridgeLogger = new MinecraftBridgeLogger(getLogger());

        // Load configuration
        getLogger().log(Level.INFO, "Loading Configuration.");
        SenderBungee sender = new SenderBungee(getProxy().getConsole(), getInstance());
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
        getProxy().getPluginManager().registerListener(getInstance(), new WSCBridgeConfigReloadListenerBungee());
        getProxy().getPluginManager().registerListener(getInstance(), new WSCBridgePluginReloadListenerBungee());
        getProxy().getPluginManager().registerListener(getInstance(), new AddModuleListenerBungee());
        getProxy().getPluginManager().registerListener(getInstance(), new PlayerListenerBungee());


        // Add SkinsRestorer Listener
        SkinsRestorer skinsRestorerAPI = SkinsRestorerProvider.get();
        skinsRestorerAPI.getEventBus().subscribe(this, SkinApplyEvent.class, new SkinsRestorerListenerBungee());
    }

    @Override
    public void onDisable() {
    }
    // end of plugin part

    // start config part
    private File configFile = new File(getDataFolder(), "config.yml");
    private ConfigurationBungee config;

    public ConfigurationBungee getConfiguration() {
        return getInstance().config;
    }

    public boolean reloadConfiguration(ISender<?> sender) {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        if (configFile.exists()) {
            try {
                config = new ConfigurationBungee(ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile));
            }
            catch (IOException e) {
                getLogger().log(Level.WARNING, "Could not load config.yml.", e);
                return false;
            }
        }
        else {
            try {
                configFile.createNewFile();
            }
            catch (IOException e) {
                getLogger().log(Level.WARNING, "Could not create config.yml.", e);
                return false;
            }
            config = new ConfigurationBungee();
        }

        if (MinecraftProfileVars.startConfig(getConfiguration(), getBridgeLogger())) {
            if (!saveConfiguration()) {
                return false;
            }
            return reloadConfiguration(sender);
        }
        return true;
    }

    public boolean saveConfiguration() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config.getConfiguration(), configFile);
        }
        catch (IOException e) {
            getLogger().log(Level.WARNING, "Could not save config.yml.", e);
            return false;
        }
        return true;
    }
    // end config part
}
