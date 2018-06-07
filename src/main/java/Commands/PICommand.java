package Commands;

import Utils.CommandUtils;
import Utils.MessageUtils;
import Utils.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PICommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check for no args, then check perms/console status, display correlating help menu
        if (args.length == 0) {
            if (sender.hasPermission("personalinfo.admin") || !(sender instanceof Player)) {
                CommandUtils.staffHelp(sender);
                return true;
            }
            CommandUtils.playerHelp(sender);
            return true;
        }

        // Player commands
        if (args[0].equalsIgnoreCase("set")) {
            if ((sender instanceof Player)) {
                Player player = (Player) sender;
                if (args.length < 2) {
                    CommandUtils.setHelp(player);
                    return true;
                } else if (args.length == 3) {
                    PlayerDataHandler.setInfoSelf(player, args[1], args[2]);
                    return true;
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            MessageUtils.prefix + " " + MessageUtils.specifyOptionToSet).
                            replace("{option}", args[1]));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.prefix + " " + MessageUtils.mustBeAPlayer));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("show")) {
            if (args.length != 2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                return true;
            } else {
                for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()) {
                    if (offlinePlayer.getName().equalsIgnoreCase(args[1])) {
                        PlayerDataHandler.showInfoOthers(sender, offlinePlayer);
                        return true;
                    }
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.prefix + " " + MessageUtils.playerNotFound.
                                replace("{player}", args[1])));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("me")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                PlayerDataHandler.showInfoSelf(player);
                return true;
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.prefix + " " + MessageUtils.mustBeAPlayer));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("delete")) {
            if ((sender instanceof Player)) {
                Player player = (Player) sender;
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                    return true;
                } else {
                    if (args.length == 2) {
                        PlayerDataHandler.deleteInfoSelf(player, args[1]);
                        return true;
                    }
                }
            }
        }

        // Staff commands
        if (sender.hasPermission("personalinfo.admin")) {
            if (args[0].equalsIgnoreCase("delothers")) {
                if (args.length != 3) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                    return true;
                } else {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.getName().equalsIgnoreCase(args[1])) {
                            PlayerDataHandler.deleteInfoOthers(sender, player, args[2]);
                            return true;
                        }
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            MessageUtils.prefix + " " + MessageUtils.playerNotFound.replace("{player}", args[1])));
                    return true;
                }
            }
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                MessageUtils.prefix + " " + MessageUtils.invalidSubCmd));
        return true;
    }
}
