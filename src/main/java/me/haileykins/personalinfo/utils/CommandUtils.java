package me.haileykins.personalinfo.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {

    private MessageUtils msgUtils;
    private ConfigUtils cfgUtils;

    public CommandUtils(MessageUtils messageUtils, ConfigUtils configUtils) {
        msgUtils = messageUtils;
        cfgUtils = configUtils;
    }

    public void playerHelp(CommandSender sender) {
        sender.sendMessage(msgUtils.transAltColors(msgUtils.helpMenuTitle));
        sender.sendMessage(ChatColor.GOLD + "/pi - " + msgUtils.transAltColors(msgUtils.helpMenuPiCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi delete (type) - " + msgUtils.transAltColors(msgUtils.helpMenuDeleteCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi me - " + msgUtils.transAltColors(msgUtils.helpMenuMeCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi set (type) (info) - " + msgUtils.transAltColors(msgUtils.helpMenuSetCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi show (ign) - " + msgUtils.transAltColors(msgUtils.helpMenuShowCommand));
    }

    public void staffHelp(CommandSender sender) {
        sender.sendMessage(msgUtils.transAltColors(msgUtils.helpMenuTitle));
        sender.sendMessage(ChatColor.GOLD + "/pi - " + msgUtils.transAltColors(msgUtils.helpMenuPiCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi clear - " + msgUtils.transAltColors(msgUtils.helpMenuClearCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi clrothers (ign) - " + msgUtils.transAltColors(msgUtils.helpMenuClearOthersCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi delete (type) - " + msgUtils.transAltColors(msgUtils.helpMenuDeleteCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi delothers (type) (ign) - " + msgUtils.transAltColors(msgUtils.helpMenuDeleteOthersCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi me - " + msgUtils.transAltColors(msgUtils.helpMenuMeCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi set (type) (info) - " + msgUtils.transAltColors(msgUtils.helpMenuSetCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi show (ign) - " + msgUtils.transAltColors(msgUtils.helpMenuShowCommand));
        sender.sendMessage(ChatColor.GOLD + "/pi reset (lang/config) - " + msgUtils.transAltColors(msgUtils.reloadHelpMsg));
    }

    public void setHelp(Player player) {
        player.sendMessage(msgUtils.transAltColors(msgUtils.helpMenuTitle));
        if (cfgUtils.isAllowName()) {
            player.sendMessage(ChatColor.GOLD + "/pi set name " + ChatColor.DARK_GREEN + "(your name)");
        }

        if (cfgUtils.isAllowAge()) {
            player.sendMessage(ChatColor.GOLD + "/pi set age " + ChatColor.DARK_GREEN + "(your age)");
        }

        if (cfgUtils.isAllowBirthday()) {
            player.sendMessage(ChatColor.GOLD + "/pi set birthday " + ChatColor.DARK_GREEN + "(your birthday)");
        }

        if (cfgUtils.isAllowLocation()) {
            player.sendMessage(ChatColor.GOLD + "/pi set location " + ChatColor.DARK_GREEN + "(your location)");
        }

        if (cfgUtils.isAllowGender()) {
            player.sendMessage(ChatColor.GOLD + "/pi set gender " + ChatColor.DARK_GREEN + "(your gender)");
        }

        if (cfgUtils.isAllowPronouns()) {
            player.sendMessage(ChatColor.GOLD + "/pi set pronouns " + ChatColor.DARK_GREEN + "(your pronouns)");
        }

        if (cfgUtils.isAllowDiscord()) {
            player.sendMessage(ChatColor.GOLD + "/pi set discord " + ChatColor.DARK_GREEN + "(your discord)");
        }

        if (cfgUtils.isAllowYoutube()) {
            player.sendMessage(ChatColor.GOLD + "/pi set youtube " + ChatColor.DARK_GREEN + "(your youtube)");
        }

        if (cfgUtils.isAllowTwitch()) {
            player.sendMessage(ChatColor.GOLD + "/pi set twitch " + ChatColor.DARK_GREEN + "(your twitch)");
        }

        if (cfgUtils.isAllowSteam()) {
            player.sendMessage(ChatColor.GOLD + "/pi set steam " + ChatColor.DARK_GREEN + "(your steam)");
        }

        if (cfgUtils.isAllowBio()) {
            player.sendMessage(ChatColor.GOLD + "/pi set bio " + ChatColor.DARK_GREEN + "(your bio)");
        }
    }
}
