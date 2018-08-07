package me.haileykins.personalinfo.utils;

import me.haileykins.personalinfo.PersonalInfo;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataHandler {

    private PersonalInfo plugin;
    private MessageUtils msgUtils;
    private ConfigUtils cfgUtils;

    public PlayerDataHandler(PersonalInfo pl, MessageUtils messageUtils, ConfigUtils configUtils) {
        plugin = pl;
        msgUtils = messageUtils;
        cfgUtils = configUtils;
    }

    private Map<UUID, PlayerData> playerInfo = new HashMap<>();

    private PlayerData info(UUID id) {
        if (!playerInfo.containsKey(id)) {
            playerInfo.put(id, new PlayerData(id));
            saveInfo();
        }
        return playerInfo.get(id);
    }

    public void showInfoOthers(CommandSender sender, OfflinePlayer player) {
        if (!playerInfo.containsKey(player.getUniqueId())) {
            sender.sendMessage(msgUtils.transAltColors(
                    msgUtils.prefix + " " + msgUtils.playerHasNotRegisted.replace("{player}", player.getName())));
            return;
        }
        PlayerData pd = info(player.getUniqueId());
        sender.sendMessage("");
        sender.sendMessage(msgUtils.transAltColors(
                msgUtils.othersPersonalInfo.replace("{player}", player.getName())));
        sender.sendMessage(ChatColor.GREEN + "-----------------------------------");
        if (cfgUtils.isAllowName()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.nameMsg) + " " + pd.name);
        }

        if (cfgUtils.isAllowAge()) {
            if (pd.age == 0) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.ageMsg + " " + msgUtils.dataNotSet));
            } else {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.ageMsg) + " " + pd.age);
            }
        }

        if (cfgUtils.isAllowBirthday()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.birthdayMsg) + " " + pd.birthday);
        }

        if (cfgUtils.isAllowLocation()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.locationMsg) + " " + pd.location);
        }

        if (cfgUtils.isAllowGender()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.genderMsg) + " " + pd.gender);
        }

        if (cfgUtils.isAllowPronouns()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.pronounsMsg) + " " + pd.pronouns);
        }

        if (cfgUtils.isAllowDiscord()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.discordMsg) + " " + pd.discord);
        }
        if (cfgUtils.isAllowYoutube()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.youtubeMsg) + " " + pd.youtube);
        }
        if (cfgUtils.isAllowTwitch()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.twitchMsg) + " " + pd.twitch);
        }
        if (cfgUtils.isAllowSteam()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.steamMsg) + " " + pd.steam);
        }

        if (cfgUtils.isAllowBio()) {
            sender.sendMessage(msgUtils.transAltColors(msgUtils.bioMsg + " " + pd.bio));
        }
    }

    public void showInfoSelf(Player player) {
        if (!playerInfo.containsKey(player.getUniqueId())) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.youHaveNotRegistered));
            return;
        }
        PlayerData pd = info(player.getUniqueId());
        player.sendMessage("");
        player.sendMessage(msgUtils.transAltColors(msgUtils.yourPersonalInfo));
        player.sendMessage(ChatColor.GREEN + "-----------------------------------");
        if (cfgUtils.isAllowName()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.nameMsg) + " " + pd.name);
        }

        if (cfgUtils.isAllowAge()) {
            if (pd.age == 0) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.ageMsg + " " + msgUtils.dataNotSet));
            } else {
                player.sendMessage(msgUtils.transAltColors(msgUtils.ageMsg) + " " + pd.age);
            }
        }

        if (cfgUtils.isAllowBirthday()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.birthdayMsg) + " " + pd.birthday);
        }

        if (cfgUtils.isAllowLocation()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.locationMsg) + " " + pd.location);
        }

        if (cfgUtils.isAllowGender()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.genderMsg) + " " + pd.gender);
        }

        if (cfgUtils.isAllowPronouns()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.pronounsMsg) + " " + pd.pronouns);
        }

        if (cfgUtils.isAllowDiscord()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.discordMsg) + " " + pd.discord);
        }
        if (cfgUtils.isAllowYoutube()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.youtubeMsg) + " " + pd.youtube);
        }
        if (cfgUtils.isAllowTwitch()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.twitchMsg) + " " + pd.twitch);
        }
        if (cfgUtils.isAllowSteam()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.steamMsg) + " " + pd.steam);
        }

        if (cfgUtils.isAllowBio()) {
            player.sendMessage(msgUtils.transAltColors(msgUtils.bioMsg + " " + pd.bio));
        }
    }

    public void setInfo(Player player, String type, String data) {
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!cfgUtils.isAllowName()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.name = data;
        } else if (type.equalsIgnoreCase("age")) {
            if (!cfgUtils.isAllowAge()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            try {
                pd.age = Integer.parseInt(data);
            } catch (NumberFormatException e) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.ageNumberOnlyMsg));
                return;
            }
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!cfgUtils.isAllowBirthday()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.birthday = data;
        } else if (type.equalsIgnoreCase("location")) {
            if (!cfgUtils.isAllowLocation()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.location = data;
        } else if (type.equalsIgnoreCase("gender")) {
            if (!cfgUtils.isAllowGender()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.gender = data;
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!cfgUtils.isAllowPronouns()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.pronouns = data;
        } else if (type.equalsIgnoreCase("discord")) {
            if (!cfgUtils.isAllowDiscord()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.discord = data;
        } else if (type.equalsIgnoreCase("youtube")) {
            if (!cfgUtils.isAllowYoutube()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.youtube = data;
        } else if (type.equalsIgnoreCase("twitch")) {
            if (!cfgUtils.isAllowTwitch()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.twitch = data;
        } else if (type.equalsIgnoreCase("steam")) {
            if (!cfgUtils.isAllowSteam()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.steam = data;
        } else if (type.equalsIgnoreCase("bio")) {
            if (!cfgUtils.isAllowBio()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.bio = data;
        } else {
            player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.unknownOptionType));
            return;
        }
        player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.setInformationMsg
                .replace("{option}", msgUtils.formatOption(type)).replace("{info}", data)));
        saveInfo();
    }

    public void deleteInfoSelf(Player player, String type) {
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!cfgUtils.isAllowName()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.name = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("age")) {
            if (!cfgUtils.isAllowAge()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.age = 0;
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!cfgUtils.isAllowBirthday()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.birthday = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("location")) {
            if (!cfgUtils.isAllowLocation()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.location = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("gender")) {
            if (!cfgUtils.isAllowGender()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.gender = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!cfgUtils.isAllowPronouns()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.pronouns = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("discord")) {
            if (!cfgUtils.isAllowDiscord()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.discord = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("youtube")) {
            if (!cfgUtils.isAllowYoutube()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.youtube = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("twitch")) {
            if (!cfgUtils.isAllowTwitch()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.twitch = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("steam")) {
            if (!cfgUtils.isAllowSteam()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.steam = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("bio")) {
            if (!cfgUtils.isAllowBio()) {
                player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.bio = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else {
            player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.unknownOptionType));
        }
        player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils
                .removedDataSelf).replace("{option}", msgUtils.formatOption(type)));
        saveInfo();
    }

    public void clearInfoSelf(Player player) {

        File file = new File(plugin.getDataFolder(), "playerInfo.yml");
        try {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            PlayerData pd = info(player.getUniqueId());
            config.set(pd.playerId.toString(), null);
            config.save(file);
            loadInfo();
            player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.clearedDataSelf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // STAFF ONLY
    public void clearInfoOthers(CommandSender sender, Player player) {
        File file = new File(plugin.getDataFolder(), "playerInfo.yml");
        try {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            PlayerData pd = info(player.getUniqueId());
            config.set(pd.playerId.toString(), null);
            config.save(file);
            loadInfo();
            sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.clearedDataOthers)
                    .replace("{player}", player.getDisplayName()));
            player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.dataClearedByStaff));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteInfoOthers(CommandSender sender, Player player, String type) {
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!cfgUtils.isAllowName()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.name = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("age")) {
            if (!cfgUtils.isAllowAge()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.age = 0;
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!cfgUtils.isAllowBirthday()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.birthday = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("location")) {
            if (!cfgUtils.isAllowLocation()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.location = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("gender")) {
            if (!cfgUtils.isAllowGender()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.gender = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!cfgUtils.isAllowPronouns()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.pronouns = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("discord")) {
            if (!cfgUtils.isAllowDiscord()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.discord = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("youtube")) {
            if (!cfgUtils.isAllowYoutube()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled
                        .replace("{option}", msgUtils.formatOption(type))));
                return;
            }
            pd.youtube = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("twitch")) {
            if (!cfgUtils.isAllowTwitch()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.twitch = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("steam")) {
            if (!cfgUtils.isAllowSteam()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.steam = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("bio")) {
            if (!cfgUtils.isAllowBio()) {
                sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.optionDisabled)
                        .replace("{option}", msgUtils.formatOption(type)));
            }
            pd.bio = msgUtils.transAltColors(msgUtils.dataNotSet);
        } else {
            player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.unknownOptionType));
        }
        // Inform sender data was removed
        sender.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.removedDataOthers
                .replace("{option}", msgUtils.formatOption(type)).replace("{player}", player.getDisplayName())));

        // Inform target player data was removed
        player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.dataRemovedByStaff)
                .replace("{option}", msgUtils.formatOption(type)));
        saveInfo();
    }


    // DATA HANDLING
    private void saveInfo() {
        File file = new File(plugin.getDataFolder(), "playerInfo.yml");
        try {
            FileConfiguration config = new YamlConfiguration();
            for (UUID pid : playerInfo.keySet()) {
                PlayerData pd = playerInfo.get(pid);
                ConfigurationSection pc = config.createSection(pid.toString());
                pc.set("name", pd.name);
                pc.set("age", pd.age);
                pc.set("birthday", pd.birthday);
                pc.set("location", pd.location);
                pc.set("gender", pd.gender);
                pc.set("pronouns", pd.pronouns);
                pc.set("discord", pd.discord);
                pc.set("youtube", pd.youtube);
                pc.set("twitch", pd.twitch);
                pc.set("steam", pd.steam);
                pc.set("bio", pd.bio);
            }
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadInfo() {
        // Create if missing
        File file = new File(plugin.getDataFolder(), "playerInfo.yml");
        try {
            if (file.createNewFile()) {
                plugin.getLogger().info("playerInfo.yml created");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Otherwise, load the players perms from the file
        try {
            playerInfo.clear();
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            for (String playerID : config.getKeys(false)) {
                ConfigurationSection pc = config.getConfigurationSection(playerID);
                UUID pid = UUID.fromString(playerID);
                PlayerData pd = info(pid);
                pd.name = pc.getString("name");
                pd.age = pc.getInt("age");
                pd.birthday = pc.getString("birthday");
                pd.location = pc.getString("location");
                pd.gender = pc.getString("gender");
                pd.pronouns = pc.getString("pronouns");
                pd.discord = pc.getString("discord");
                pd.youtube = pc.getString("youtube");
                pd.twitch = pc.getString("twitch");
                pd.steam = pc.getString("steam");
                pd.bio = pc.getString("bio");
                playerInfo.put(pid, pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
