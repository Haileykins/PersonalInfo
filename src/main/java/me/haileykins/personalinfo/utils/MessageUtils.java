package me.haileykins.personalinfo.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MessageUtils {

    public static Plugin plugin;

    public static String transAltColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String formatOption(String word) {
        String fl = word.substring(0,1).toUpperCase();
        String rst = word.substring(1).toLowerCase();
        return fl + rst;
    }

    public static String prefix = "&6[PersonalInfo]&f";
    static String nameMsg = "&2Name:&f";
    static String ageMsg = "&2Age:&f";
    static String birthdayMsg = "&2Birthday:&f";
    static String locationMsg = "&2Location:&f";
    static String genderMsg = "&2Gender:&f";
    static String pronounsMsg = "&2Pronouns:&f";
    static String discordMsg = "&2Discord:&f";
    static String youtubeMsg = "&2Youtube:&f";
    static String twitchMsg = "&2Twitch:&f";
    static String steamMsg = "&2Steam:&f";
    static String bioMsg = "&2Bio:&f";
    static String yourPersonalInfo = "&2Your Personal Information";
    static String othersPersonalInfo = "&2Personal Information for {player}";
    static String setInformationMsg = "&2{option} Set To: {info}";
    static String removedDataSelf = "&2Removed Your {option} From Your Personal Information";
    static String clearedDataSelf = "&2You Have Successfully Cleared Your Profile Data";
    static String removedDataOthers = "&2Removed {option} From Player {player}";
    static String clearedDataOthers = "&2Removed all data From Player {player}";
    static String dataRemovedByStaff = "&2Your {option} Was Removed By Staff!";
    static String dataClearedByStaff = "&2Your Profile Was Removed By Staff!";
    static String dataNotSet = "Not Set";
    public static String firstNameOnly = "&2Last Name's Are Disabled!";
    public static String nameTooLong = "&2Name Too Long! Max {length} Characters";
    public static String bioTooLong = "&2Your bio is too long! Max {length} Characters!";
    static String playerHasNotRegisted = "&2{player} has not registered any data!";
    static String youHaveNotRegistered = "&2You have not registered any data!";
    static String optionDisabled = "&2{option}s Are Disabled On This Server";
    static String unknownOptionType = "&2Unknown Info Type!";
    public static String specifyOptionToSet = "&2Please specify your {option}";
    static String ageNumberOnlyMsg = "&2Your age must be numerical!";
    public static String mustBeAPlayer = "&2You must be a player to use this command!";
    public static String invalidNumberOfArguments = "&2Invalid Number of Arguments";
    public static String invalidSubCmd = "&2Invalid Sub Command!";
    public static String playerNotFound = "&2Player {player} not found!";
    public static String noPermission = "&2You don't have permission to do this!";
    static String helpMenuTitle = "&2-----&6PersonalInfo Help&2-----";
    static String helpMenuPiCommand = "&2Brings Up This Help Page";
    static String helpMenuClearCommand = "&2Clears Your Data From Record";
    static String helpMenuClearOthersCommand = "&2Clears Specified Player's Data From Record";
    static String helpMenuDeleteCommand = "&2Deletes Specified Info Type";
    static String helpMenuDeleteOthersCommand = "&2Deletes Info For A Player";
    static String helpMenuMeCommand = "&2Shows Your Personal Info";
    static String helpMenuSetCommand = "&2Sets Specified Info Type";
    static String helpMenuShowCommand = "&2Shows Another Player's Info";
    public static String configReloaded = "&2Config File Reloaded!";
    public static String langFileReloaded = "&2Language File Reloaded";
    public static String reloadHelpMsg = "&2/pi reload lang or /pi reload config to reload files";

    private static void saveLang() {
        File file = new File(plugin.getDataFolder(), "language.yml");
        FileConfiguration config = new YamlConfiguration();
        config.set("prefix", prefix);
        config.set("nameMsg", nameMsg);
        config.set("ageMsg", ageMsg);
        config.set("birthdayMsg", birthdayMsg);
        config.set("locationMsg", locationMsg);
        config.set("genderMsg", genderMsg);
        config.set("pronounsMsg", pronounsMsg);
        config.set("discordMsg", discordMsg);
        config.set("youtubeMsg", youtubeMsg);
        config.set("twitchMsg", twitchMsg);
        config.set("steamMsg", steamMsg);
        config.set("bioMsg", bioMsg);
        config.set("yourPersonalInfo", yourPersonalInfo);
        config.set("othersPersonalInfo", othersPersonalInfo);
        config.set("setInformationMsg", setInformationMsg);
        config.set("removedDataSelf", removedDataSelf);
        config.set("clearedDataSelf", clearedDataSelf);
        config.set("removedDataOthers", removedDataOthers);
        config.set("clearedDataOthers", clearedDataOthers);
        config.set("dataRemovedByStaff", dataRemovedByStaff);
        config.set("dataClearedByStaff", dataClearedByStaff);
        config.set("dataNotSet", dataNotSet);
        config.set("firstNameOnly", firstNameOnly);
        config.set("nameTooLong", nameTooLong);
        config.set("bioTooLong", bioTooLong);
        config.set("playerHasNotRegisted", playerHasNotRegisted);
        config.set("youHaveNotRegistered", youHaveNotRegistered);
        config.set("optionDisabled", optionDisabled);
        config.set("unknownOptionType", unknownOptionType);
        config.set("specifyOptionToSet", specifyOptionToSet);
        config.set("mustBeAPlayer", mustBeAPlayer);
        config.set("invalidNumberOfArguments", invalidNumberOfArguments);
        config.set("invalidSubCmd", invalidSubCmd);
        config.set("playerNotFound", playerNotFound);
        config.set("noPermission", noPermission);
        config.set("helpMenuTitle", helpMenuTitle);
        config.set("helpMenuPiCommand", helpMenuPiCommand);
        config.set("helpMenuDeleteCommand", helpMenuDeleteCommand);
        config.set("helpMenuDeleteOthersCommand", helpMenuDeleteOthersCommand);
        config.set("helpMenuMeCommand", helpMenuMeCommand);
        config.set("helpMenuSetCommand", helpMenuSetCommand);
        config.set("helpMenuShowCommand", helpMenuShowCommand);
        config.set("configReloaded", configReloaded);
        config.set("langFileReloaded", langFileReloaded);
        config.set("reloadHelpMsg", reloadHelpMsg);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadLang() {
        // Create if missing
        File file = new File(plugin.getDataFolder(), "language.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            if (file.createNewFile()) {
                saveLang();
                plugin.getLogger().info("language.yml created.");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Otherwise, load the players perms from the file
        prefix = config.getString("prefix");
        nameMsg = config.getString("nameMsg");
        ageMsg = config.getString("ageMsg");
        birthdayMsg = config.getString("birthdayMsg");
        locationMsg = config.getString("locationMsg");
        genderMsg = config.getString("genderMsg");
        pronounsMsg = config.getString("pronounsMsg");
        discordMsg = config.getString("discordMsg");
        youtubeMsg = config.getString("youtubeMsg");
        twitchMsg = config.getString("twitchMsg");
        steamMsg = config.getString("steamMsg");
        bioMsg = config.getString("bioMsg");
        yourPersonalInfo = config.getString("yourPersonalInfo");
        othersPersonalInfo = config.getString("othersPersonalInfo");
        setInformationMsg = config.getString("setInformationMsg");
        removedDataSelf = config.getString("removedDataSelf");
        clearedDataSelf = config.getString("clearedDataSelf");
        removedDataOthers = config.getString("removedDataOthers");
        clearedDataOthers = config.getString("clearedDataOthers");
        dataRemovedByStaff = config.getString("dataRemovedByStaff");
        dataClearedByStaff = config.getString("dataClearedByStaff");
        dataNotSet = config.getString("dataNotSet");
        firstNameOnly = config.getString("firstNameOnly");
        nameTooLong = config.getString("nameTooLong");
        bioTooLong = config.getString("bioTooLong");
        youHaveNotRegistered = config.getString("youHaveNotRegistered");
        optionDisabled = config.getString("optionDisabled");
        unknownOptionType = config.getString("unknownOptionType");
        specifyOptionToSet = config.getString("specifyOptionToSet");
        mustBeAPlayer = config.getString("mustBeAPlayer");
        invalidNumberOfArguments = config.getString("invalidNumberOfArguments");
        invalidSubCmd = config.getString("invalidSubCmd");
        playerNotFound = config.getString("playerNotFound");
        noPermission = config.getString("noPermission");
        helpMenuTitle = config.getString("helpMenuTitle");
        helpMenuPiCommand = config.getString("helpMenuPiCommand");
        helpMenuDeleteCommand = config.getString("helpMenuDeleteCommand");
        helpMenuDeleteOthersCommand = config.getString("helpMenuDeleteOthersCommand");
        helpMenuMeCommand = config.getString("helpMenuMeCommand");
        helpMenuSetCommand = config.getString("helpMenuSetCommand");
        helpMenuShowCommand = config.getString("helpMenuShowCommand");
        configReloaded = config.getString("configReloaded");
        langFileReloaded = config.getString("langFileReloaded");
    }

    public static void reloadLang() {
        File file = new File(plugin.getDataFolder(), "language.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        // Get new data
        prefix = config.getString("prefix");
        nameMsg = config.getString("nameMsg");
        ageMsg = config.getString("ageMsg");
        birthdayMsg = config.getString("birthdayMsg");
        locationMsg = config.getString("locationMsg");
        genderMsg = config.getString("genderMsg");
        pronounsMsg = config.getString("pronounsMsg");
        discordMsg = config.getString("discordMsg");
        youtubeMsg = config.getString("youtubeMsg");
        twitchMsg = config.getString("twitchMsg");
        steamMsg = config.getString("steamMsg");
        bioMsg = config.getString("bioMsg");
        yourPersonalInfo = config.getString("yourPersonalInfo");
        othersPersonalInfo = config.getString("othersPersonalInfo");
        setInformationMsg = config.getString("setInformationMsg");
        removedDataSelf = config.getString("removedDataSelf");
        clearedDataSelf = config.getString("clearedDataSelf");
        removedDataOthers = config.getString("removedDataOthers");
        clearedDataOthers = config.getString("clearedDataOthers");
        dataRemovedByStaff = config.getString("dataRemovedByStaff");
        dataClearedByStaff = config.getString("dataClearedByStaff");
        dataNotSet = config.getString("dataNotSet");
        firstNameOnly = config.getString("firstNameOnly");
        nameTooLong = config.getString("nameTooLong");
        bioTooLong = config.getString("bioTooLong");
        youHaveNotRegistered = config.getString("youHaveNotRegistered");
        optionDisabled = config.getString("optionDisabled");
        unknownOptionType = config.getString("unknownOptionType");
        specifyOptionToSet = config.getString("specifyOptionToSet");
        mustBeAPlayer = config.getString("mustBeAPlayer");
        invalidNumberOfArguments = config.getString("invalidNumberOfArguments");
        invalidSubCmd = config.getString("invalidSubCmd");
        playerNotFound = config.getString("playerNotFound");
        noPermission = config.getString("noPermission");
        helpMenuTitle = config.getString("helpMenuTitle");
        helpMenuPiCommand = config.getString("helpMenuPiCommand");
        helpMenuDeleteCommand = config.getString("helpMenuDeleteCommand");
        helpMenuDeleteOthersCommand = config.getString("helpMenuDeleteOthersCommand");
        helpMenuMeCommand = config.getString("helpMenuMeCommand");
        helpMenuSetCommand = config.getString("helpMenuSetCommand");
        helpMenuShowCommand = config.getString("helpMenuShowCommand");
        configReloaded = config.getString("configReloaded");
        langFileReloaded = config.getString("langFileReloaded");

        // Set new data into language.yml
        config.set("prefix", prefix);
        config.set("nameMsg", nameMsg);
        config.set("ageMsg", ageMsg);
        config.set("birthdayMsg", birthdayMsg);
        config.set("locationMsg", locationMsg);
        config.set("genderMsg", genderMsg);
        config.set("pronounsMsg", pronounsMsg);
        config.set("discordMsg", discordMsg);
        config.set("youtubeMsg", youtubeMsg);
        config.set("twitchMsg", twitchMsg);
        config.set("steamMsg", steamMsg);
        config.set("bioMsg", bioMsg);
        config.set("yourPersonalInfo", yourPersonalInfo);
        config.set("othersPersonalInfo", othersPersonalInfo);
        config.set("setInformationMsg", setInformationMsg);
        config.set("removedDataSelf", removedDataSelf);
        config.set("clearedDataSelf", clearedDataSelf);
        config.set("removedDataOthers", removedDataOthers);
        config.set("clearedDataOthers", clearedDataOthers);
        config.set("dataRemovedByStaff", dataRemovedByStaff);
        config.set("dataClearedByStaff", dataClearedByStaff);
        config.set("dataNotSet", dataNotSet);
        config.set("firstNameOnly", firstNameOnly);
        config.set("nameTooLong", nameTooLong);
        config.set("bioTooLong", bioTooLong);
        config.set("playerHasNotRegisted", playerHasNotRegisted);
        config.set("youHaveNotRegistered", youHaveNotRegistered);
        config.set("optionDisabled", optionDisabled);
        config.set("unknownOptionType", unknownOptionType);
        config.set("specifyOptionToSet", specifyOptionToSet);
        config.set("mustBeAPlayer", mustBeAPlayer);
        config.set("invalidNumberOfArguments", invalidNumberOfArguments);
        config.set("invalidSubCmd", invalidSubCmd);
        config.set("playerNotFound", playerNotFound);
        config.set("noPermission", noPermission);
        config.set("helpMenuTitle", helpMenuTitle);
        config.set("helpMenuPiCommand", helpMenuPiCommand);
        config.set("helpMenuDeleteCommand", helpMenuDeleteCommand);
        config.set("helpMenuDeleteOthersCommand", helpMenuDeleteOthersCommand);
        config.set("helpMenuMeCommand", helpMenuMeCommand);
        config.set("helpMenuSetCommand", helpMenuSetCommand);
        config.set("helpMenuShowCommand", helpMenuShowCommand);
        config.set("configReloaded", configReloaded);
        config.set("langFileReloaded", langFileReloaded);
        config.set("reloadHelpMsg", reloadHelpMsg);
        saveLang();
    }
}
