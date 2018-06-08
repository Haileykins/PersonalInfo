package Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {

    private static String transAltColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void playerHelp(CommandSender sender) {
        sender.sendMessage(transAltColors(MessageUtils.helpMenuTitle));
        sender.sendMessage(ChatColor.GOLD + "/pi - " + transAltColors(MessageUtils.helpMenuPiCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi delete (type) - " + transAltColors(MessageUtils.helpMenuDeleteCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi me - " + transAltColors(MessageUtils.helpMenuMeCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi set (type) (info) - " + transAltColors(MessageUtils.helpMenuSetCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi show (ign) - " + transAltColors(MessageUtils.helpMenuShowCommand));
    }

    public static void staffHelp(CommandSender sender) {
        sender.sendMessage(transAltColors(MessageUtils.helpMenuTitle));
        sender.sendMessage(ChatColor.GOLD + "/pi - " + transAltColors(MessageUtils.helpMenuPiCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi clear - " + transAltColors(MessageUtils.helpMenuClearCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi clrothers - " + transAltColors(MessageUtils.helpMenuClearOthersCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi delete (type) - " + transAltColors(MessageUtils.helpMenuDeleteCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi delothers (type) (ign) - " + transAltColors(MessageUtils.helpMenuDeleteOthersCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi me - " + transAltColors(MessageUtils.helpMenuMeCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi set (type) (info) - " + transAltColors(MessageUtils.helpMenuSetCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi show (ign) - " + transAltColors(MessageUtils.helpMenuShowCommand));
    }

    public static void setHelp(Player player) {
        player.sendMessage(transAltColors(MessageUtils.helpMenuTitle));
        if(ConfigUtils.isAllowName()) {
            player.sendMessage(ChatColor.GOLD + "/pi set name " + ChatColor.DARK_GREEN + "(your name)");
        }

        if(ConfigUtils.isAllowAge()) {
            player.sendMessage(ChatColor.GOLD + "/pi set age " + ChatColor.DARK_GREEN + "(your age)");
        }

        if(ConfigUtils.isAllowBirthday()) {
            player.sendMessage(ChatColor.GOLD + "/pi set birthday " + ChatColor.DARK_GREEN + "(your birthday)");
        }

        if(ConfigUtils.isAllowLocation()) {
            player.sendMessage(ChatColor.GOLD + "/pi set location " + ChatColor.DARK_GREEN + "(your location)");
        }

        if(ConfigUtils.isAllowGender()) {
            player.sendMessage(ChatColor.GOLD + "/pi set gender " + ChatColor.DARK_GREEN + "(your gender)");
        }

        if(ConfigUtils.isAllowPronouns()) {
            player.sendMessage(ChatColor.GOLD + "/pi set pronouns " + ChatColor.DARK_GREEN + "(your pronouns)");
        }

        if (ConfigUtils.isAllowDiscord()) {
            player.sendMessage(ChatColor.GOLD + "/pi set discord " + ChatColor.DARK_GREEN + "(your discord)");
        }

        if (ConfigUtils.isAllowYoutube()) {
            player.sendMessage(ChatColor.GOLD + "/pi set youtube " + ChatColor.DARK_GREEN + "(your youtube)");
        }
    }
}
