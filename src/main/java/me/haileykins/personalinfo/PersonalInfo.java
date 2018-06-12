package me.haileykins.personalinfo;

import me.haileykins.personalinfo.commands.PICommand;
import me.haileykins.personalinfo.utils.ConfigUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import me.haileykins.personalinfo.utils.PlayerDataHandler;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class PersonalInfo extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getLogger().info("No Data Folder Found! Creating...");
            if (!getDataFolder().mkdirs()) {
                getLogger().severe("DISABLING! Failed To Create Directories.");
                getServer().getPluginManager().disablePlugin(this);
            }
            getLogger().info("Data Folder Created!");
        }

        PlayerDataHandler.plugin = this;
        getLogger().info("Loading Info!");
        PlayerDataHandler.loadInfo();
        getLogger().info("Info Loaded!");
        ConfigUtils.plugin = this;
        getLogger().info("Loading Config!");
        ConfigUtils.setConfig();
        getLogger().info("Config Loaded!");
        MessageUtils.plugin = this;
        getLogger().info("Loading Language File");
        MessageUtils.loadLang();
        getLogger().info("Language File Loaded!");

        getCommand("pi").setExecutor(new PICommand());
        getLogger().info("Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }
}
