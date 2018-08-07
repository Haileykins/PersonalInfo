package me.haileykins.personalinfo;

import me.haileykins.personalinfo.commands.PICommand;
import me.haileykins.personalinfo.listeners.UpdateListener;
import me.haileykins.personalinfo.utils.CommandUtils;
import me.haileykins.personalinfo.utils.ConfigUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import me.haileykins.personalinfo.utils.PlayerDataHandler;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class PersonalInfo extends JavaPlugin {

    @Override
    public void onEnable() {

        Metrics metrics = new Metrics(this);

        // Initiate Instances
        MessageUtils msgUtils = new MessageUtils(this);
        ConfigUtils cfgUtils = new ConfigUtils(this);
        CommandUtils cmdUtils = new CommandUtils(msgUtils, cfgUtils);
        PlayerDataHandler pdh = new PlayerDataHandler(this, msgUtils, cfgUtils);

        // Check For/Create Data Folder
        if (!getDataFolder().exists()) {
            getLogger().info("No Data Folder Found! Creating...");
            if (!getDataFolder().mkdirs()) {
                getLogger().severe("DISABLING! Failed To Create Directories.");
                getServer().getPluginManager().disablePlugin(this);
            }
            getLogger().info("Data Folder Created!");
        }

        // Load Flat Files
        pdh.loadInfo();
        cfgUtils.setConfig();
        msgUtils.loadLang();

        // Register Listeners
        getServer().getPluginManager().registerEvents(new UpdateListener(cfgUtils, this, msgUtils), this);

        // Register Commands
        getCommand("pi").setExecutor(new PICommand(msgUtils, cmdUtils, cfgUtils, pdh));

        // Display Successful Load
        getLogger().info("Enabled: Startup Successful");
    }
}
