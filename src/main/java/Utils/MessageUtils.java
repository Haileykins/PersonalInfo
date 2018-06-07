package Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MessageUtils {

    public static Plugin plugin;

    public static String prefix = "&6[PersonalInfo]&f";
    static String nameMsg = "&2Name:&f";
    static String ageMsg = "&2Age:&f";
    static String birthdayMsg = "&2Birthday:&f";
    static String locationMsg = "&2Location:&f";
    static String genderMsg = "&2Gender:&f";
    static String pronounsMsg = "&2Pronouns:&f";
    static String discordMsg = "&2Discord:&f";
    static String yourPersonalInfo = "&2Your Personal Information";
    static String othersPersonalInfo = "&2Personal Information for {player}";
    static String setInformationMsg = "&2{option} Set To: {info}";
    static String removedDataSelf = "&2Removed Your {option} From Your Personal Information";
    static String removedDataOthers = "&2Removed {option} From Player {player}";
    static String dataRemovedByStaff = "&2Your {option} Was Removed By Staff!";
    static String dataNotSet = "Not Set";
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
    static String helpMenuTitle = "&2-----&6PersonalInfo Help&2-----";
    static String helpMenuPiCommand = "&2Brings Up This Help Page";
    static String helpMenuDeleteCommand = "&2Deletes Specified Info Type";
    static String helpMenuDeleteOthersCommand = "&2Deletes Info For A Player";
    static String helpMenuMeCommand = "&2Shows Your Personal Info";
    static String helpMenuSetCommand = "&2Sets Specified Info Type";
    static String helpMenuShowCommand = "&2Shows Another Player's Info";

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
        config.set("yourPersonalInfo", yourPersonalInfo);
        config.set("othersPersonalInfo", othersPersonalInfo);
        config.set("setInformationMsg", setInformationMsg);
        config.set("removedDataSelf", removedDataSelf);
        config.set("removedDataOthers", removedDataOthers);
        config.set("dataRemovedByStaff", dataRemovedByStaff);
        config.set("dataNotSet", dataNotSet);
        config.set("playerHasNotRegisted", playerHasNotRegisted);
        config.set("youHaveNotRegistered", youHaveNotRegistered);
        config.set("optionDisabled", optionDisabled);
        config.set("unknownOptionType", unknownOptionType);
        config.set("specifyOptionToSet", specifyOptionToSet);
        config.set("mustBeAPlayer", mustBeAPlayer);
        config.set("invalidNumberOfArguments", invalidNumberOfArguments);
        config.set("invalidSubCmd", invalidSubCmd);
        config.set("helpMenuTitle", helpMenuTitle);
        config.set("helpMenuPiCommand", helpMenuPiCommand);
        config.set("helpMenuDeleteCommand", helpMenuDeleteCommand);
        config.set("helpMenuDeleteOthersCommand", helpMenuDeleteOthersCommand);
        config.set("helpMenuMeCommand", helpMenuMeCommand);
        config.set("helpMenuSetCommand", helpMenuSetCommand);
        config.set("helpMenuShowCommand", helpMenuShowCommand);
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
        config.getString("prefix");
        config.getString("nameMsg");
        config.getString("ageMsg");
        config.getString("birthdayMsg");
        config.getString("locationMsg");
        config.getString("genderMsg");
        config.getString("pronounsMsg");
        config.getString("discordMsg");
        config.getString("yourPersonalInfo");
        config.getString("othersPersonalInfo");
        config.getString("setInformationMsg");
        config.getString("removedDataSelf");
        config.getString("removedDataOthers");
        config.getString("dataRemovedByStaff");
        config.getString("dataNotSet");
        config.getString("playerHasNotRegisted");
        config.getString("youHaveNotRegistered");
        config.getString("optionDisabled");
        config.getString("unknownOptionType");
        config.getString("specifyOptionToSet");
        config.getString("mustBeAPlayer");
        config.getString("invalidNumberOfArguments");
        config.getString("invalidSubCmd");
        config.getString("playerNotFound");
        config.getString("helpMenuTitle");
        config.getString("helpMenuPiCommand");
        config.getString("helpMenuDeleteCommand");
        config.getString("helpMenuDeleteOthersCommand");
        config.getString("helpMenuMeCommand");
        config.getString("helpMenuSetCommand");
        config.getString("helpMenuShowCommand");
    }
}
