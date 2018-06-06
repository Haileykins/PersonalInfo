package Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {

    public static void playerHelp(CommandSender sender) {
        sender.sendMessage("/pi - Brings Up This Help Page");
        sender.sendMessage("/pi del (type) - Deletes Specified Info Type");
        sender.sendMessage("/pi me - Shows Your Personal Info");
        sender.sendMessage("/pi set (type) (info) - Sets Specified Info Type");
        sender.sendMessage("/pi show (ign) - Shows Another Players Info");
    }

    public static void staffHelp(CommandSender sender) {
        sender.sendMessage("/pi - Brings Up This Help Page");
        sender.sendMessage("/pi del (type) - Deletes Specified Info Type");
        sender.sendMessage("/pi delothers (type) (ign) - Deletes Info For A Player");
        sender.sendMessage("/pi me - Shows Your Personal Info");
        sender.sendMessage("/pi set (type) (info) - Sets Specified Info Type");
        sender.sendMessage("/pi show (playername) - Shows Another Players Info");
    }

    public static void setHelp(Player player) {
        player.sendMessage("To Set Different Personal Info");
        if(ConfigUtils.isAllowName()) {
            player.sendMessage("/pi set name (your name)");
        }

        if(ConfigUtils.isAllowAge()) {
            player.sendMessage("/pi set age (your age)");
        }

        if(ConfigUtils.isAllowBirthday()) {
            player.sendMessage("/pi set birthday (your birthday)");
        }

        if(ConfigUtils.isAllowLocation()) {
            player.sendMessage("/pi set location (your location)");
        }

        if(ConfigUtils.isAllowGender()) {
            player.sendMessage("/pi set gender (your gender)");
        }

        if(ConfigUtils.isAllowPronouns()) {
            player.sendMessage("/pi set pronouns (your pronouns)");
        }

        if (ConfigUtils.isAllowDiscord()) {
            player.sendMessage("/pi set discord (your discord)");
        }
    }
}
