import Commands.PICommand;
import Utils.ConfigUtils;
import Utils.MessageUtils;
import Utils.PlayerDataHandler;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class PersonalInfo extends JavaPlugin {

    private boolean allowName = true;
    private boolean allowAge = true;
    private boolean allowBirthday = true;
    private boolean allowLocation = true;
    private boolean allowGender = true;
    private boolean allowPronouns = true;
    private boolean allowDiscord = true;

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
