package Utils;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataHandler {

    public static Plugin plugin;
    private static Map<UUID, PlayerData> playerInfo = new HashMap<>();

    private static PlayerData info(UUID id) {
        if (!playerInfo.containsKey(id)) {
            playerInfo.put(id, new PlayerData(id));
            saveInfo();
        }
        return playerInfo.get(id);
    }

    private static String transAltColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void showInfoOthers(CommandSender sender, OfflinePlayer player) {
        if (!playerInfo.containsKey(player.getUniqueId())) {
            sender.sendMessage(transAltColors(
                    MessageUtils.prefix + " " + MessageUtils.playerHasNotRegisted.replace("{player}", player.getName())));
            return;
        }
        PlayerData pd = info(player.getUniqueId());
        sender.sendMessage("");
        sender.sendMessage(transAltColors(
                MessageUtils.othersPersonalInfo.replace("{player}", player.getName())));
        sender.sendMessage(ChatColor.GREEN + "-----------------------------------");
        if (ConfigUtils.isAllowName()) {
            sender.sendMessage(transAltColors(MessageUtils.nameMsg) + " " + pd.name);
        }

        if (ConfigUtils.isAllowAge()) {
            if (pd.age == 0) {
                sender.sendMessage(transAltColors(MessageUtils.ageMsg + " " + MessageUtils.dataNotSet));
            } else {
                sender.sendMessage(transAltColors(MessageUtils.ageMsg) + " " + pd.age);
            }
        }

        if (ConfigUtils.isAllowBirthday()) {
            sender.sendMessage(transAltColors(MessageUtils.birthdayMsg) + " " + pd.birthday);
        }

        if (ConfigUtils.isAllowLocation()) {
            sender.sendMessage(transAltColors(MessageUtils.locationMsg) + " " + pd.location);
        }

        if (ConfigUtils.isAllowGender()) {
            sender.sendMessage(transAltColors(MessageUtils.genderMsg) + " " + pd.gender);
        }

        if (ConfigUtils.isAllowPronouns()) {
            sender.sendMessage(transAltColors(MessageUtils.pronounsMsg) + " " + pd.pronouns);
        }

        if (ConfigUtils.isAllowDiscord()) {
            sender.sendMessage(transAltColors(MessageUtils.discordMsg) + " " + pd.discord);
        }
        if (ConfigUtils.isAllowYoutube()) {
            sender.sendMessage(transAltColors(MessageUtils.youtubeMsg) + " " + pd.youtube);
        }
        if (ConfigUtils.isAllowTwitch()) {
            sender.sendMessage(transAltColors(MessageUtils.twitchMsg) + " " + pd.twitch);
        }
        if (ConfigUtils.isAllowSteam()) {
            sender.sendMessage(transAltColors(MessageUtils.steamMsg) + " " + pd.steam);
        }

        if (ConfigUtils.isAllowBio()) {
            sender.sendMessage(transAltColors(MessageUtils.bioMsg + " " + pd.bio));
        }
    }

    public static void showInfoSelf(Player player) {
        if (!playerInfo.containsKey(player.getUniqueId())) {
            player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.youHaveNotRegistered));
            return;
        }
        PlayerData pd = info(player.getUniqueId());
        player.sendMessage("");
        player.sendMessage(transAltColors(MessageUtils.yourPersonalInfo));
        player.sendMessage(ChatColor.GREEN + "-----------------------------------");
        if (ConfigUtils.isAllowName()) {
            player.sendMessage(transAltColors(MessageUtils.nameMsg) + " " + pd.name);
        }

        if (ConfigUtils.isAllowAge()) {
            if (pd.age == 0) {
                player.sendMessage(transAltColors(MessageUtils.ageMsg + " " + MessageUtils.dataNotSet));
            } else {
                player.sendMessage(transAltColors(MessageUtils.ageMsg) + " " + pd.age);
            }
        }

        if (ConfigUtils.isAllowBirthday()) {
            player.sendMessage(transAltColors(MessageUtils.birthdayMsg) + " " + pd.birthday);
        }

        if (ConfigUtils.isAllowLocation()) {
            player.sendMessage(transAltColors(MessageUtils.locationMsg) + " " + pd.location);
        }

        if (ConfigUtils.isAllowGender()) {
            player.sendMessage(transAltColors(MessageUtils.genderMsg) + " " + pd.gender);
        }

        if (ConfigUtils.isAllowPronouns()) {
            player.sendMessage(transAltColors(MessageUtils.pronounsMsg) + " " + pd.pronouns);
        }

        if (ConfigUtils.isAllowDiscord()) {
            player.sendMessage(transAltColors(MessageUtils.discordMsg) + " " + pd.discord);
        }
        if (ConfigUtils.isAllowYoutube()) {
            player.sendMessage(transAltColors(MessageUtils.youtubeMsg) + " " + pd.youtube);
        }
        if (ConfigUtils.isAllowTwitch()) {
            player.sendMessage(transAltColors(MessageUtils.twitchMsg) + " " + pd.twitch);
        }
        if (ConfigUtils.isAllowSteam()) {
            player.sendMessage(transAltColors(MessageUtils.steamMsg) + " " + pd.steam);
        }

        if (ConfigUtils.isAllowBio()) {
            player.sendMessage(transAltColors(MessageUtils.bioMsg + " " + pd.bio));
        }
    }

    public static void setInfo(Player player, String type, String data) {
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.name = data;
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            try {
                pd.age = Integer.parseInt(data);
            } catch (NumberFormatException e) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.ageNumberOnlyMsg));
                return;
            }
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.birthday = data;
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.location = data;
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.gender = data;
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.pronouns = data;
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.discord = data;
        } else if (type.equalsIgnoreCase("youtube")) {
            if (!ConfigUtils.isAllowYoutube()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", type));
            }
            pd.youtube = data;
        } else if (type.equalsIgnoreCase("twitch")) {
            if (!ConfigUtils.isAllowTwitch()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", type));
            }
            pd.twitch = data;
        } else if (type.equalsIgnoreCase("steam")) {
            if (!ConfigUtils.isAllowSteam()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", type));
            }
            pd.steam = data;
        } else if (type.equalsIgnoreCase("bio")) {
            if (!ConfigUtils.isAllowBio()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", MessageUtils.formatOption(type)));
            }
            pd.bio = data;
        } else {
            player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.unknownOptionType));
            return;
        }
        player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.setInformationMsg
                .replace("{option}", type).replace("{info}", data)));
        saveInfo();
    }

    public static void deleteInfoSelf(Player player, String type) {
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.name = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.age = 0;
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.birthday = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.location = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.gender = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.pronouns = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.discord = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("youtube")) {
            if (!ConfigUtils.isAllowYoutube()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.youtube = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("twitch")) {
            if (!ConfigUtils.isAllowTwitch()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", type));
            }
            pd.twitch = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("steam")) {
            if (!ConfigUtils.isAllowSteam()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", type));
            }
            pd.steam = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("bio")) {
            if (!ConfigUtils.isAllowBio()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", MessageUtils.formatOption(type)));
            }
            pd.bio = transAltColors(MessageUtils.dataNotSet);
        } else {
            player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.unknownOptionType));
        }
        player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils
                .removedDataSelf).replace("{option}", type));
        saveInfo();
    }

    public static void clearInfoSelf(Player player) {

        File file = new File(plugin.getDataFolder(), "playerInfo.yml");
        try {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            PlayerData pd = info(player.getUniqueId());
            config.set(pd.playerId.toString(), null);
            config.save(file);
            loadInfo();
            player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.clearedDataSelf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // STAFF ONLY
    public static void clearInfoOthers(CommandSender sender, Player player) {
        File file = new File(plugin.getDataFolder(), "playerInfo.yml");
        try {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            PlayerData pd = info(player.getUniqueId());
            config.set(pd.playerId.toString(), null);
            config.save(file);
            loadInfo();
            sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.clearedDataOthers)
                    .replace("{player}", player.getDisplayName()));
            player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.dataClearedByStaff));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteInfoOthers(CommandSender sender, Player player, String type) {
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.name = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.age = 0;
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.birthday = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.location = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.gender = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.pronouns = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.discord = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("youtube")) {
            if (!ConfigUtils.isAllowYoutube()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled
                        .replace("{option}", type)));
                return;
            }
            pd.youtube = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("twitch")) {
            if (!ConfigUtils.isAllowTwitch()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", type));
            }
            pd.twitch = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("steam")) {
            if (!ConfigUtils.isAllowSteam()) {
                player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", type));
            }
            pd.steam = transAltColors(MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("bio")) {
            if (!ConfigUtils.isAllowBio()) {
                sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.optionDisabled)
                        .replace("{option}", MessageUtils.formatOption(type)));
            }
            pd.bio = transAltColors(MessageUtils.dataNotSet);
        } else {
            player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.unknownOptionType));
        }
        // Inform sender data was removed
        sender.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.removedDataOthers
                .replace("{option}", type).replace("{player}", player.getDisplayName())));

        // Inform target player data was removed
        player.sendMessage(transAltColors(MessageUtils.prefix + " " + MessageUtils.dataRemovedByStaff)
                .replace("{option}", type));
        saveInfo();
    }


    // DATA HANDLING
    private static void saveInfo() {
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

    public static void loadInfo() {
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
