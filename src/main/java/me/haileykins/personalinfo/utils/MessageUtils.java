package me.haileykins.personalinfo.utils;

import me.haileykins.personalinfo.PersonalInfo;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageUtils {

    private PersonalInfo plugin;

    public MessageUtils(PersonalInfo pl) {
        plugin = pl;
    }

    public String transAltColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String formatOption(String word) {
        String fl = word.substring(0, 1).toUpperCase();
        String rst = word.substring(1).toLowerCase();
        return fl + rst;
    }

    public String prefix = "&6[PersonalInfo]&f";
    public String firstNameOnly = "&2Last Name's Are Disabled!";
    public String nameTooLong = "&2Name Too Long! Max {length} Characters";
    public String bioTooLong = "&2Your bio is too long! Max {length} Characters!";
    public String specifyOptionToSet = "&2Please specify your {option}";
    public String mustBeAPlayer = "&2You must be a player to use this command!";
    public String invalidNumberOfArguments = "&2Invalid Number of Arguments";
    public String invalidSubCmd = "&2Invalid Sub Command!";
    public String playerNotFound = "&2Player {player} not found!";
    public String noPermission = "&2You don't have permission to do this!";
    public String configReloaded = "&2Config File Reloaded!";
    public String langFileReloaded = "&2Language File Reloaded";
    public String reloadHelpMsg = "&2/pi reload lang or /pi reload config to reload files";
    public String pluginOutOfDate = "&cYour Version Is Outdated!";
    String nameMsg = "&2Name:&f";
    String ageMsg = "&2Age:&f";
    String birthdayMsg = "&2Birthday:&f";
    String locationMsg = "&2Location:&f";
    String genderMsg = "&2Gender:&f";
    String pronounsMsg = "&2Pronouns:&f";
    String discordMsg = "&2Discord:&f";
    String youtubeMsg = "&2Youtube:&f";
    String twitchMsg = "&2Twitch:&f";
    String steamMsg = "&2Steam:&f";
    String bioMsg = "&2Bio:&f";
    String yourPersonalInfo = "&2Your Personal Information";
    String othersPersonalInfo = "&2Personal Information for {player}";
    String setInformationMsg = "&2{option} Set To: {info}";
    String removedDataSelf = "&2Removed Your {option} From Your Personal Information";
    String clearedDataSelf = "&2You Have Successfully Cleared Your Profile Data";
    String removedDataOthers = "&2Removed {option} From Player {player}";
    String clearedDataOthers = "&2Removed all data From Player {player}";
    String dataRemovedByStaff = "&2Your {option} Was Removed By Staff!";
    String dataClearedByStaff = "&2Your Profile Was Removed By Staff!";
    String dataNotSet = "Not Set";
    String playerHasNotRegisted = "&2{player} has not registered any data!";
    String youHaveNotRegistered = "&2You have not registered any data!";
    String optionDisabled = "&2{option}s Are Disabled On This Server";
    String unknownOptionType = "&2Unknown Info Type!";
    String ageNumberOnlyMsg = "&2Your age must be numerical!";
    String helpMenuTitle = "&2-----&6PersonalInfo Help&2-----";
    String helpMenuPiCommand = "&2Brings Up This Help Page";
    String helpMenuClearCommand = "&2Clears Your Data From Record";
    String helpMenuClearOthersCommand = "&2Clears Specified Player's Data From Record";
    String helpMenuDeleteCommand = "&2Deletes Specified Info Type";
    String helpMenuDeleteOthersCommand = "&2Deletes Info For A Player";
    String helpMenuMeCommand = "&2Shows Your Personal Info";
    String helpMenuSetCommand = "&2Sets Specified Info Type";
    String helpMenuShowCommand = "&2Shows Another Player's Info";

    private void saveLang() {
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

    public void loadLang() {
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

    public void reloadLang() {
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
