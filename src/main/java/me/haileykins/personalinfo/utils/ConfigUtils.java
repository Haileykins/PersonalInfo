package me.haileykins.personalinfo.utils;

import me.haileykins.personalinfo.PersonalInfo;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {

    private PersonalInfo plugin;

    public ConfigUtils(PersonalInfo pl) {
        plugin = pl;
    }

    private boolean allowName = true;
    private boolean allowLastName = false;
    public int nameCharLength = 25;
    private boolean allowAge = true;
    private boolean allowBirthday = true;
    private boolean allowLocation = true;
    private boolean allowGender = true;
    private boolean allowPronouns = true;
    private boolean allowDiscord = true;
    private boolean allowYoutube = true;
    private boolean allowTwitch = true;
    private boolean allowSteam = true;
    private boolean allowBio = true;
    @SuppressWarnings("FieldCanBeLocal")
    public int bioCharLength = 160;

    public void setConfig() {
        FileConfiguration config = plugin.getConfig();
        allowName = config.getBoolean("Allow-Name", allowName);
        allowLastName = config.getBoolean("Allow-Last-Name", allowLastName);
        nameCharLength = config.getInt("Name-Character-Length", nameCharLength);
        allowAge = config.getBoolean("Allow-Age", allowAge);
        allowBirthday = config.getBoolean("Allow-Birthday", allowBirthday);
        allowLocation = config.getBoolean("Allow-Location", allowLocation);
        allowGender = config.getBoolean("Allow-Gender", allowGender);
        allowPronouns = config.getBoolean("Allow-Pronouns", allowPronouns);
        allowDiscord = config.getBoolean("Allow-Discord", allowDiscord);
        allowYoutube = config.getBoolean("Allow-Youtube", allowYoutube);
        allowTwitch = config.getBoolean("Allow-Twitch", allowTwitch);
        allowSteam = config.getBoolean("Allow-Steam", allowSteam);
        allowBio = config.getBoolean("Allow-Bio", allowBio);
        bioCharLength = config.getInt("Bio-Character-Length", bioCharLength);
        // write in case they're missing
        config.set("Allow-Name", allowName);
        config.set("Allow-Last-Name", allowLastName);
        config.set("Name-Character-Length", nameCharLength);
        config.set("Allow-Age", allowAge);
        config.set("Allow-Birthday", allowBirthday);
        config.set("Allow-Location", allowLocation);
        config.set("Allow-Gender", allowGender);
        config.set("Allow-Pronouns", allowPronouns);
        config.set("Allow-Discord", allowDiscord);
        config.set("Allow-Youtube", allowYoutube);
        config.set("Allow-Twitch", allowTwitch);
        config.set("Allow-Steam", allowSteam);
        config.set("Allow-Bio", allowBio);
        config.set("Bio-Character-Length", bioCharLength);
        plugin.saveConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        setConfig();
        plugin.getConfig();
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

    public boolean isAllowLastName() {
        return allowLastName;
    }
}
