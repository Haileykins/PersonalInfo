package me.haileykins.personalinfo.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class for handling all formatting, messaging etc, in relation to the database accesses
 * @see DatabaseUtils
 */
public class DataUtils {

    private ConfigUtils cfgUtils;
    private MessageUtils msgUtils;

    public DataUtils(ConfigUtils configUtils, MessageUtils messageUtils) {
        cfgUtils = configUtils;
        msgUtils = messageUtils;
    }

    void showInfoSelf(DataHelper data, Player player) {
        player.sendMessage("");
        player.sendMessage(msgUtils.getMessage("your-personal-info"));
        player.sendMessage(ChatColor.GREEN + "-----------------------------------");
        if (cfgUtils.isAllowName()) {
            player.sendMessage(msgUtils.getMessage("name-msg") + " " + data.name);
        }

        if (cfgUtils.isAllowNickname()) {
            player.sendMessage(msgUtils.getMessage("nickname-msg") + " " + data.nickname);
        }

        if (cfgUtils.isAllowAge()) {
            if (data.age == 0) {
                player.sendMessage(msgUtils.getMessage("age-msg") + " " + msgUtils.getMessage("data-not-set"));
            } else {
                player.sendMessage(msgUtils.getMessage("age-msg") + " " + data.age);
            }
        }

        if (cfgUtils.isAllowBirthday()) {
            player.sendMessage(msgUtils.getMessage("birthday-msg") + " " + data.birthday);
        }

        if (cfgUtils.isAllowLocation()) {
            player.sendMessage(msgUtils.getMessage("location-msg") + " " + data.location);
        }

        if (cfgUtils.isAllowGender()) {
            player.sendMessage(msgUtils.getMessage("gender-msg") + " " + data.gender);
        }

        if (cfgUtils.isAllowPronouns()) {
            player.sendMessage(msgUtils.getMessage("pronouns-msg") + " " + data.pronouns);
        }

        if (cfgUtils.isAllowDiscord()) {
            player.sendMessage(msgUtils.getMessage("discord-msg") + " " + data.discord);
        }
        if (cfgUtils.isAllowYoutube()) {
            player.sendMessage(msgUtils.getMessage("youtube-msg") + " " + data.youtube);
        }
        if (cfgUtils.isAllowTwitch()) {
            player.sendMessage(msgUtils.getMessage("twitch-msg") + " " + data.twitch);
        }
        if (cfgUtils.isAllowSteam()) {
            player.sendMessage(msgUtils.getMessage("steam-msg") + " " + data.steam);
        }

        if (cfgUtils.isAllowBio()) {
            player.sendMessage(msgUtils.getMessage("bio-msg") + " " + data.bio);
        }
    }

    void showInfoOthers(DataHelper data, CommandSender sender, Player target) {
        sender.sendMessage("");
        sender.sendMessage(msgUtils.getMessage("other-personal-info").replace("{player}", target.getName()));
        sender.sendMessage(ChatColor.GREEN + "-----------------------------------");
        if (cfgUtils.isAllowName()) {
            sender.sendMessage(msgUtils.getMessage("name-msg") + " " + data.name);
        }

        if (cfgUtils.isAllowNickname()) {
            sender.sendMessage(msgUtils.getMessage("nickname-msg") + " " + data.nickname);
        }

        if (cfgUtils.isAllowAge()) {

            if (data.age == 0) {
                sender.sendMessage(msgUtils.getMessage("age-msg") + " " + msgUtils.getMessage("data-not-set"));
            } else {
                sender.sendMessage(msgUtils.getMessage("age-msg") + " " + data.age);
            }

        }

        if (cfgUtils.isAllowBirthday()) {
            sender.sendMessage(msgUtils.getMessage("birthday-msg") + " " + data.birthday);
        }

        if (cfgUtils.isAllowLocation()) {
            sender.sendMessage(msgUtils.getMessage("location-msg") + " " + data.location);
        }

        if (cfgUtils.isAllowGender()) {
            sender.sendMessage(msgUtils.getMessage("gender-msg") + " " + data.gender);
        }

        if (cfgUtils.isAllowPronouns()) {
            sender.sendMessage(msgUtils.getMessage("pronouns-msg") + " " + data.pronouns);
        }

        if (cfgUtils.isAllowDiscord()) {
            sender.sendMessage(msgUtils.getMessage("discord-msg") + " " + data.discord);
        }

        if (cfgUtils.isAllowYoutube()) {
            sender.sendMessage(msgUtils.getMessage("youtube-msg") + " " + data.youtube);
        }

        if (cfgUtils.isAllowTwitch()) {
            sender.sendMessage(msgUtils.getMessage("twitch-msg") + " " + data.twitch);
        }

        if (cfgUtils.isAllowSteam()) {
            sender.sendMessage(msgUtils.getMessage("steam-msg") + " " + data.steam);
        }

        if (cfgUtils.isAllowBio()) {
            sender.sendMessage(msgUtils.getMessage("bio-msg") + " " + data.bio);
        }
    }

    void deleteInfoOthers(CommandSender sender, Player target, String type) {
        sender.sendMessage(msgUtils.getPrefixMessage("removed-data-others")
                .replace("{option}", msgUtils.formatOption(type))
                .replace("{player}", target.getName()));

        target.sendMessage(msgUtils.getPrefixMessage("data-removed-by-staff")
                .replace("{option}", msgUtils.formatOption(type)));
    }

    void clearInfoOthers(CommandSender sender, Player target) {
        sender.sendMessage(msgUtils.getPrefixMessage("cleared-data-others").replace("{player}", target.getName()));
        target.sendMessage(msgUtils.getPrefixMessage("data-cleared-by-staff"));
    }

    void noDataOthers(CommandSender sender, String target) {
        sender.sendMessage(msgUtils.getPrefixMessage("player-has-not-registered").replace("{player}", target));
    }

    void noDataSelf(CommandSender sender) {
        sender.sendMessage(msgUtils.getPrefixMessage("you-have-not-registered"));
    }

    void clearInfoSelf(Player player) {
        player.sendMessage(msgUtils.getPrefixMessage("cleared-data-self"));
    }
}