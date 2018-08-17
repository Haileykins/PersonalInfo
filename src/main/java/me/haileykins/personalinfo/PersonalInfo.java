package me.haileykins.personalinfo;

import me.haileykins.personalinfo.commands.CommandManager;
import me.haileykins.personalinfo.listeners.UpdateListener;
import me.haileykins.personalinfo.utils.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class
 */
public class PersonalInfo extends JavaPlugin {

    /**
     * Called on server startup
     */
    @Override
    public void onEnable() {

        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(this);

        ConfigUtils cfgUtils = new ConfigUtils(this);
        MessageUtils msgUtils = new MessageUtils(this);
        CommandUtils cmdUtils = new CommandUtils(msgUtils, cfgUtils);
        DataUtils dataUtils = new DataUtils(cfgUtils, msgUtils);
        DatabaseUtils dbUtils = new DatabaseUtils(cfgUtils, dataUtils);

        if (!getDataFolder().exists()) {
            if (getDataFolder().mkdirs()) {
                getLogger().severe("DISABLING! Failed To Create Directories");
                getServer().getPluginManager().disablePlugin(this);
            }
        }

        cfgUtils.loadConfig();
        msgUtils.loadLang();

        if (cfgUtils.useMySQL()) {
            dbUtils.connectToSQL();
        } else {
            new SQLiteUtils(this);
            dbUtils.connectToSQLite();
        }

        getServer().getPluginManager().registerEvents(new UpdateListener(cfgUtils, this, msgUtils), this);

        getCommand("personalinfo").setExecutor(new CommandManager(this, dbUtils, msgUtils, cmdUtils, cfgUtils));

    }
}