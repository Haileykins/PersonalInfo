package Commands;

import Utils.CommandUtils;
import Utils.ConfigUtils;
import Utils.MessageUtils;
import Utils.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PICommand implements CommandExecutor {

    private static String transAltColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

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
        if (args[0].equalsIgnoreCase("clear")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                PlayerDataHandler.clearInfoSelf(player);
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("set")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length < 2) {
                    CommandUtils.setHelp(player);
                    return true;
                } else if (args.length == 3) {
                    PlayerDataHandler.setInfo(player, args[1], args[2]);
                    return true;
                } else if (args.length > 3) {
                    if (args[1].equalsIgnoreCase("steam")) {
                        List<String> list = Arrays.asList(args);
                        List<String> fullMsg = list.subList(2, args.length);
                        String steam = String.join(" ", fullMsg);
                        PlayerDataHandler.setInfo(player, args[1], steam);
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("bio")) {
                        // TODO: Configurable Bio Length Checks
                        List<String> list = Arrays.asList(args);
                        List<String> fullMsg = list.subList(2, args.length);
                        String bio = String.join(" ", fullMsg);

                        if (bio.length() > 160) {
                            player.sendMessage("Your Bio Is To Long! 160 Characters Max!");
                            return true;
                        }

                        PlayerDataHandler.setInfo(player, args[1], bio);
                        return true;
                    }
                } else {
                    player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.specifyOptionToSet)
                            .replace("{option}", MessageUtils.formatOption(args[1])));
                    return true;
                }
            } else {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.mustBeAPlayer));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("show")) {
            if (args.length != 2) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                return true;
            } else {
                for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()) {
                    if (offlinePlayer.getName().equalsIgnoreCase(args[1])) {
                        PlayerDataHandler.showInfoOthers(sender, offlinePlayer);
                        return true;
                    }
                }
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.playerNotFound
                        .replace("{player}", args[1])));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("me")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                PlayerDataHandler.showInfoSelf(player);
                return true;
            } else {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.mustBeAPlayer));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("delete")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length < 2) {
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
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
            if (args[0].equalsIgnoreCase("clrothers")) {
                if (args.length != 2) {
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                    return true;
                } else {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.getName().equalsIgnoreCase(args[1])) {
                            PlayerDataHandler.clearInfoOthers(sender, player);
                            return true;
                        }
                    }
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.playerNotFound
                            .replace("{player}", args[1])));
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("delothers")) {
                if (args.length != 3) {
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                    return true;
                } else {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.getName().equalsIgnoreCase(args[1])) {
                            PlayerDataHandler.deleteInfoOthers(sender, player, args[2]);
                            return true;
                        }
                    }
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.playerNotFound
                            .replace("{player}", args[1])));
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (args.length != 2) {
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.reloadHelpMsg));
                    return true;
                } else if (args[1].equalsIgnoreCase("lang")) {
                    MessageUtils.reloadLang();
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.langFileReloaded));
                    Bukkit.getLogger().info("Language File Reloaded!");
                    return true;
                } else if (args[1].equalsIgnoreCase("config")) {
                    ConfigUtils.reloadConfig();
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.configReloaded));
                    Bukkit.getLogger().info("Config File Reloaded!");
                    return true;
                }
            }
        }
        sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidSubCmd));
        return true;
    }
}
