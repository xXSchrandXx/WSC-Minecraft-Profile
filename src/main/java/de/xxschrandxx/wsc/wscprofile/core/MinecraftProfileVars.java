package de.xxschrandxx.wsc.wscprofile.core;

import java.util.logging.Logger;

import de.xxschrandxx.wsc.wscbridge.core.api.configuration.AbstractConfiguration;
import de.xxschrandxx.wsc.wscbridge.core.api.configuration.IConfiguration;

public class MinecraftProfileVars extends AbstractConfiguration {
    public static boolean startConfig(IConfiguration<?> configuration, Logger logger) {
        return startConfig(configuration, Configuration.class, defaults.class, logger);
    }

    public static class Configuration {
        // universal
        // url
        public static final String url ="url";

        // language
        // language.command.noperm
        public static final String LangCmdNoPerm = "language.command.noperm";
        // language.command.playeronly
        public static final String LangCmdPlayerOnly = "language.command.playeronly";
        // language.command.reload.config.start
        public static final String LangCmdReloadConfigStart = "language.command.reload.config.start";
        // language.command.reload.config.error
        public static final String LangCmdReloadConfigError = "language.command.reload.config.error";
        // language.command.reload.config.success
        public static final String LangCmdReloadConfigSuccess = "language.command.reload.config.success";
        // language.command.reload.api.start
        public static final String LangCmdReloadAPIStart = "language.command.reload.api.start";
        // language.command.reload.api.success
        public static final String LangCmdReloadAPISuccess = "language.command.reload.api.success";
    }
    // Default values
    public static final class defaults {
        // universal
        // url
        public static final String url = "https://example.domain/index.php?minecraft-profile/";

        // language
        // language.command.noperm
        public static final String LangCmdNoPerm = "&8[&6WSC-Profile&8]&c You don't have permission to do this.";
        // language.command.playeronly
        public static final String LangCmdPlayerOnly = "&8[&6WSC-Profile&8]&c You need to be a player.";
        // language.command.reload.config.start
        public static final String LangCmdReloadConfigStart = "&8[&6WSC-Profile&8]&7 Reloading configuration.";
        // language.command.reload.config.error
        public static final String LangCmdReloadConfigError = "&8[&6WSC-Profile&8]&e Reloading configuration failed.";
        // language.command.reload.config.success
        public static final String LangCmdReloadConfigSuccess = "&8[&6WSC-Profile&8]&7 Configuration reloaded successfully.";
        // language.command.reload.api.start
        public static final String LangCmdReloadAPIStart = "&8[&6WSC-Profile&8]&7 Reloading API.";
        // language.command.reload.api.success
        public static final String LangCmdReloadAPISuccess = "&8[&6WSC-Profile&8]&7 API reloaded successfully.";
    }
}
