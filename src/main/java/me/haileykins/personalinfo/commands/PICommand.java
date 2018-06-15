package me.haileykins.personalinfo.commands;

import me.haileykins.personalinfo.utils.CommandUtils;
import me.haileykins.personalinfo.utils.ConfigUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import me.haileykins.personalinfo.utils.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static me.haileykins.personalinfo.utils.MessageUtils.transAltColors;

public class PICommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // If No Args, Return Help Menu
        if (args.length == 0) {
            if (sender.hasPermission("personalinfo.admin") || !(sender instanceof Player)) {
                CommandUtils.staffHelp(sender);
                return true;
            }
            CommandUtils.playerHelp(sender);
            return true;
        }

        // Player Commands
        if (args[0].equalsIgnoreCase("clear")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                PlayerDataHandler.clearInfoSelf(player);
                return true;
            }
            sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.mustBeAPlayer));
            return true;
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
                    List<String> fullMsg = Arrays.asList(args).subList(2, args.length);
                    if (args[1].equalsIgnoreCase("name")) {
                        if (ConfigUtils.isAllowLastName()) {
                            String name = String.join(" ", fullMsg);
                            if (name.length() > ConfigUtils.nameCharLength) {
                                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.nameTooLong
                                        .replace("{length}", Integer.toString(ConfigUtils.nameCharLength))));
                                return true;
                            }
                            PlayerDataHandler.setInfo(player, args[1], name);
                            return true;
                        }
                        player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.firstNameOnly));
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("steam")) {
                        String steam = String.join(" ", fullMsg);
                        PlayerDataHandler.setInfo(player, args[1], steam);
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("bio")) {
                        String bio = String.join(" ", fullMsg);
                        if (bio.length() > ConfigUtils.bioCharLength) {
                            player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.bioTooLong
                                    .replace("{length}", Integer.toString(ConfigUtils.bioCharLength))));
                            return true;
                        }
                        PlayerDataHandler.setInfo(player, args[1], bio);
                        return true;
                    }
                }
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.specifyOptionToSet)
                        .replace("{option}", MessageUtils.formatOption(args[1])));
                return true;
            }
            sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.mustBeAPlayer));
            return true;
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
            }
            sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.mustBeAPlayer));
            return true;
        }

        if (args[0].equalsIgnoreCase("delete")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length < 2) {
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                    return true;
                }
                if (args.length == 2) {
                    PlayerDataHandler.deleteInfoSelf(player, args[1]);
                    return true;
                }
            }
        }

        // Staff Commands
        if (args[0].equalsIgnoreCase("clrothers")) {
            if (sender.hasPermission("personalinfo.admin")) {
                if (args.length != 2) {
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                    return true;
                } else {
                    Player player = Bukkit.getPlayer(args[1]);
                    //noinspection deprecation
                    if (player != null) {
                        PlayerDataHandler.clearInfoOthers(sender, player);
                        return true;
                    }
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.playerNotFound
                            .replace("{player}", args[1])));
                    return true;
                }
            }
            sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.noPermission));
            return true;
        }


        if (args[0].equalsIgnoreCase("delothers")) {
            if (sender.hasPermission("personalinfo.admin")) {
                if (args.length != 3) {
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidNumberOfArguments));
                    return true;
                } else {
                    Player player = Bukkit.getPlayer(args[1]);
                    //noinspection deprecation
                    if (player != null) {
                        PlayerDataHandler.deleteInfoOthers(sender, player, args[2]);
                        return true;
                    }
                    sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.playerNotFound
                            .replace("{player}", args[1])));
                    return true;

                }
            }
            sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.noPermission));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("personalinfo.admin")) {
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
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidSubCmd));
                return true;
            }
            sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.noPermission));
            return true;
        }

        sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.invalidSubCmd));
        return true;
    }
}
