package de.hglabor.plugins.kitapi.config;

import de.hglabor.plugins.kitapi.kit.AbstractKit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public final class KitApiConfig {
    private final static KitApiConfig instance = new KitApiConfig();

    private static File kitFile;
    private static YamlConfiguration kitConfiguration;

    private KitApiConfig() {
    }

    public static KitApiConfig getInstance() {
        return instance;
    }

    public void register(File pluginFolder) {
        try {
            kitFile = new File(pluginFolder, "kitConfig.yml");
            if (!kitFile.exists()) {
                kitFile.createNewFile();
            }
            kitConfiguration = YamlConfiguration.loadConfiguration(kitFile);
            kitConfiguration.addDefault("kit.amount", 1);
            kitConfiguration.addDefault("debug", false);
            kitConfiguration.options().copyDefaults(true);
            kitConfiguration.save(kitFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadKit(AbstractKit kit) {
        try {
            kitConfiguration.addDefault("kit" + "." + kit.getName() + "." + "enabled", kit.isEnabled());
            kitConfiguration.addDefault("kit" + "." + kit.getName() + "." + "cooldown", kit.getCooldown());
            kitConfiguration.addDefault("kit" + "." + kit.getName() + "." + "usable", kit.isUsable());
            kitConfiguration.options().copyDefaults(true);
            kitConfiguration.save(kitFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getInteger(String key) {
        return kitConfiguration.getInt(key);
    }

    public String getString(String key) {
        return kitConfiguration.getString(key);
    }

    public boolean getBoolean(String key) {
        return kitConfiguration.getBoolean(key);
    }

    public int getKitAmount() {
        return kitConfiguration.getInt("kit.amount");
    }
}
