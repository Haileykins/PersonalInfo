package Utils;

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

    public static boolean isAllowName() {
        return allowName;
    }

    public static boolean isAllowAge() {
        return allowAge;
    }

    public static boolean isAllowBirthday() {
        return allowBirthday;
    }

    public static boolean isAllowLocation() {
        return allowLocation;
    }

    public static boolean isAllowGender() {
        return allowGender;
    }

    public static boolean isAllowPronouns() {
        return allowPronouns;
    }

    public static boolean isAllowDiscord() {
        return allowDiscord;
    }
}