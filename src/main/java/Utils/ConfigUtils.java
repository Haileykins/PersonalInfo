package Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigUtils {

    public static Plugin plugin;


    private static boolean allowName = true;
    private static boolean allowAge = true;
    private static boolean allowBirthday = true;
    private static boolean allowLocation = true;
    private static boolean allowGender = true;
    private static boolean allowPronouns = true;
    private static boolean allowDiscord = true;

    public static void setConfig() {
        FileConfiguration config = plugin.getConfig();
        allowName = config.getBoolean("Allow-Name", allowName);
        allowAge = config.getBoolean("Allow-Age", allowAge);
        allowBirthday = config.getBoolean("Allow-Birthday", allowBirthday);
        allowLocation = config.getBoolean("Allow-Location", allowLocation);
        allowGender = config.getBoolean("Allow-Gender", allowGender);
        allowPronouns = config.getBoolean("Allow-Pronouns", allowPronouns);
        allowDiscord = config.getBoolean("Allow-Discord", allowDiscord);
        // write in case they're missing
        config.set("Allow-Name", allowName);
        config.set("Allow-Age", allowAge);
        config.set("Allow-Birthday", allowBirthday);
        config.set("Allow-Location", allowLocation);
        config.set("Allow-Gender", allowGender);
        config.set("Allow-Pronouns", allowPronouns);
        config.set("Allow-Discord", allowDiscord);
        plugin.saveConfig();
    }

    public static void reloadConfig() {
        plugin.reloadConfig();
        setConfig();
        plugin.getConfig();
    }

    static boolean isAllowName() {
        return allowName;
    }

    static boolean isAllowAge() {
        return allowAge;
    }

    static boolean isAllowBirthday() {
        return allowBirthday;
    }

    static boolean isAllowLocation() {
        return allowLocation;
    }

    static boolean isAllowGender() {
        return allowGender;
    }

    static boolean isAllowPronouns() {
        return allowPronouns;
    }

    static boolean isAllowDiscord() {
        return allowDiscord;
    }
}