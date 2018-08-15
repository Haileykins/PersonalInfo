package me.haileykins.personalinfo.utils;

import me.haileykins.personalinfo.PersonalInfo;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * The class designed to handle every interaction between the config file and the plugin
 */
public class ConfigUtils {

    private PersonalInfo plugin;

    public ConfigUtils(PersonalInfo pl) {
        plugin = pl;
    }

    private boolean updaterEnabled;
    private boolean allowName;
    private boolean allowAge;
    private boolean allowBirthday;
    private boolean allowLocation;
    private boolean allowGender;
    private boolean allowPronouns;
    private boolean allowDiscord;
    private boolean allowYoutube;
    private boolean allowTwitch;
    private boolean allowSteam;
    private boolean allowBio;
    private boolean useMySQL;
    private String address;
    private int port;
    private String database;
    private String username;
    private String password;

    /**
     * Called when the server starts or reloads to obtain and store the data from the config file
     */
    public void loadConfig() {
        plugin.saveDefaultConfig();

        FileConfiguration config = plugin.getConfig();
        ConfigurationSection mysql = config.getConfigurationSection("MySQL");

        updaterEnabled = config.getBoolean("Enable-Update-Notifications");
        allowName = config.getBoolean("Allow-Name");
        allowAge = config.getBoolean("Allow-Age");
        allowBirthday = config.getBoolean("Allow-Birthday");
        allowLocation = config.getBoolean("Allow-Location");
        allowGender = config.getBoolean("Allow-Gender");
        allowPronouns = config.getBoolean("Allow-Pronouns");
        allowDiscord = config.getBoolean("Allow-Discord");
        allowYoutube = config.getBoolean("Allow-Youtube");
        allowTwitch = config.getBoolean("Allow-Twitch");
        allowSteam = config.getBoolean("Allow-Steam");
        allowBio = config.getBoolean("Allow-Bio");

        useMySQL = mysql.getBoolean("Use-MySQL");
        address = mysql.getString("Host-Name");
        port = mysql.getInt("Port");
        database = mysql.getString("Database-Name");
        username = mysql.getString("Username");
        password = mysql.getString("Password");

        plugin.saveConfig();
    }

    /**
     * Called the /pi reload config command is run to re-obtain any new data from the config file
     */
    public void reloadConfig() {
        plugin.reloadConfig();
        loadConfig();
        plugin.getConfig();
    }

    /**
     * Called when a player joins the server with a certain permission, used to decide if the plugin should
     * send a message regarding the status of the plugins version if it is out of date
     *
     * @return the True or False statement of the updater being enabled
     */
    public boolean isUpdaterEnabled() {
        return updaterEnabled;
    }

    boolean isAllowName() {
        return allowName;
    }

    boolean isAllowAge() {
        return allowAge;
    }

    boolean isAllowBirthday() {
        return allowBirthday;
    }

    boolean isAllowLocation() {
        return allowLocation;
    }

    boolean isAllowGender() {
        return allowGender;
    }

    boolean isAllowPronouns() {
        return allowPronouns;
    }

    boolean isAllowDiscord() {
        return allowDiscord;
    }

    boolean isAllowYoutube() {
        return allowYoutube;
    }

    boolean isAllowTwitch() {
        return allowTwitch;
    }

    boolean isAllowSteam() {
        return allowSteam;
    }

    boolean isAllowBio() {
        return allowBio;
    }

    public boolean useMySQL() {
        return useMySQL;
    }

    String getAddress() {
        return address;
    }

    int getPort() {
        return port;
    }

    String getDatabase() {
        return database;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}
