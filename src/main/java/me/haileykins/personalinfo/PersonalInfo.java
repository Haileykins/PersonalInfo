package me.haileykins.personalinfo;

import me.haileykins.personalinfo.commands.PICommand;
import me.haileykins.personalinfo.utils.CommandUtils;
import me.haileykins.personalinfo.utils.ConfigUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import me.haileykins.personalinfo.utils.PlayerDataHandler;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class PersonalInfo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Initiate Instances
        MessageUtils msgUtils = new MessageUtils(this);
        ConfigUtils cfgUtils = new ConfigUtils(this);
        CommandUtils cmdUtils = new CommandUtils(msgUtils, cfgUtils);
        PlayerDataHandler pdh = new PlayerDataHandler(this, msgUtils, cmdUtils, cfgUtils);

        // Check For/Create Data Folder
        if (!getDataFolder().exists()) {
            getLogger().info("No Data Folder Found! Creating...");
            if (!getDataFolder().mkdirs()) {
                getLogger().severe("DISABLING! Failed To Create Directories.");
                getServer().getPluginManager().disablePlugin(this);
            }
            getLogger().info("Data Folder Created!");
        }

        // Load Players Data File
        getLogger().info("Loading Info!");
        pdh.loadInfo();
        getLogger().info("Info Loaded!");

        // Load Config File
        getLogger().info("Loading Config!");
        cfgUtils.setConfig();
        getLogger().info("Config Loaded!");

        // Load Message File
        getLogger().info("Loading Language File");
        msgUtils.loadLang();
        getLogger().info("Language File Loaded!");

        // Register Commands
        getCommand("pi").setExecutor(new PICommand(msgUtils, cmdUtils, cfgUtils, pdh));

        // Display Successful Load
        getLogger().info("Enabled: Startup Successful");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }
}
