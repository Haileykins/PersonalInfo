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

public class PICommand implements CommandExecutor {

    private MessageUtils msgUtils;
    private CommandUtils cmdUtils;
    private ConfigUtils cfgUtils;
    private PlayerDataHandler pdh;

    public PICommand(MessageUtils messageUtils, CommandUtils commandUtils, ConfigUtils configUtils, PlayerDataHandler playerDataHandler) {
        msgUtils = messageUtils;
        cmdUtils = commandUtils;
        cfgUtils = configUtils;
        pdh = playerDataHandler;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // If No Args, Return Help Menu
        if (args.length == 0) {
            if (sender.hasPermission("personalinfo.admin")) {
                cmdUtils.staffHelp(sender);
                return true;
            }
            cmdUtils.playerHelp(sender);
            return true;
        }

        // Player Commands
        if (args[0].equalsIgnoreCase("clear")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                pdh.clearInfoSelf(player);
                return true;
            }
            sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.mustBeAPlayer));
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length < 2) {
                    cmdUtils.setHelp(player);
                    return true;
                } else if (args.length == 3) {
                    pdh.setInfo(player, args[1], args[2]);
                    return true;
                } else if (args.length > 3) {
                    List<String> fullMsg = Arrays.asList(args).subList(2, args.length);
                    if (args[1].equalsIgnoreCase("name")) {
                        if (cfgUtils.isAllowLastName()) {
                            String name = String.join(" ", fullMsg);
                            if (name.length() > cfgUtils.nameCharLength) {
                                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.nameTooLong
                                        .replace("{length}", Integer.toString(cfgUtils.nameCharLength))));
                                return true;
                            }
                            pdh.setInfo(player, args[1], name);
                            return true;
                        }
                        player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.firstNameOnly));
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("steam")) {
                        String steam = String.join(" ", fullMsg);
                        pdh.setInfo(player, args[1], steam);
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("bio")) {
                        String bio = String.join(" ", fullMsg);
                        if (bio.length() > cfgUtils.bioCharLength) {
                            player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.bioTooLong
                                    .replace("{length}", Integer.toString(cfgUtils.bioCharLength))));
                            return true;
                        }
                        pdh.setInfo(player, args[1], bio);
                        return true;
                    }
                }
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.specifyOptionToSet)
                        .replace("{option}", msgUtils.formatOption(args[1])));
                return true;
            }
            sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.mustBeAPlayer));
            return true;
        }

        if (args[0].equalsIgnoreCase("show")) {
            if (args.length != 2) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.invalidNumberOfArguments));
                return true;
            } else {
                for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()) {
                    if (offlinePlayer.getName().equalsIgnoreCase(args[1])) {
                        pdh.showInfoOthers(sender, offlinePlayer);
                        return true;
                    }
                }
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.playerNotFound
                        .replace("{player}", args[1])));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("me")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                pdh.showInfoSelf(player);
                return true;
            }
            sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.mustBeAPlayer));
            return true;
        }

        if (args[0].equalsIgnoreCase("delete")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length < 2) {
                    sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.invalidNumberOfArguments));
                    return true;
                }
                if (args.length == 2) {
                    pdh.deleteInfoSelf(player, args[1]);
                    return true;
                }
            }
        }

        // Staff Commands
        if (args[0].equalsIgnoreCase("clrothers")) {
            if (sender.hasPermission("personalinfo.admin")) {
                if (args.length != 2) {
                    sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.invalidNumberOfArguments));
                    return true;
                } else {
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player != null) {
                        pdh.clearInfoOthers(sender, player);
                        return true;
                    }
                    sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.playerNotFound
                            .replace("{player}", args[1])));
                    return true;
                }
            }
            sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.noPermission));
            return true;
        }


        if (args[0].equalsIgnoreCase("delothers")) {
            if (sender.hasPermission("personalinfo.admin")) {
                if (args.length != 3) {
                    sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.invalidNumberOfArguments));
                    return true;
                } else {
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player != null) {
                        pdh.deleteInfoOthers(sender, player, args[2]);
                        return true;
                    }
                    sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.playerNotFound
                            .replace("{player}", args[1])));
                    return true;

                }
            }
            sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.noPermission));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("personalinfo.admin")) {
                if (args.length != 2) {
                    sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.reloadHelpMsg));
                    return true;
                } else if (args[1].equalsIgnoreCase("lang")) {
                    msgUtils.reloadLang();
                    sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.langFileReloaded));
                    Bukkit.getLogger().info("Language File Reloaded!");
                    return true;
                } else if (args[1].equalsIgnoreCase("config")) {
                    cfgUtils.reloadConfig();
                    sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.configReloaded));
                    Bukkit.getLogger().info("Config File Reloaded!");
                    return true;
                }
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.invalidSubCmd));
                return true;
            }
            sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.noPermission));
            return true;
        }

        sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.invalidSubCmd));
        return true;
    }
}
