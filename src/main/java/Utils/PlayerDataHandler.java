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

    public static void showInfoOthers(CommandSender sender, OfflinePlayer player) {
        if (!playerInfo.containsKey(player.getUniqueId())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    MessageUtils.prefix + " " + MessageUtils.playerHasNotRegisted.replace("{player}", player.getName())));
            return;
        }
        PlayerData pd = info(player.getUniqueId());
        sender.sendMessage("");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                MessageUtils.othersPersonalInfo.replace("{player}", player.getName())));
        sender.sendMessage(ChatColor.GREEN + "-----------------------------------");
        if(ConfigUtils.isAllowName()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.nameMsg)
                    + " " + pd.name);
        }

        if(ConfigUtils.isAllowAge()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.ageMsg)
                    + " " + pd.age);
        }

        if(ConfigUtils.isAllowBirthday()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.birthdayMsg)
                    + " " + pd.birthday);
        }

        if(ConfigUtils.isAllowLocation()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.locationMsg)
                    + " " + pd.location);
        }

        if(ConfigUtils.isAllowGender()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.genderMsg)
                    + " " + pd.gender);
        }

        if(ConfigUtils.isAllowPronouns()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.pronounsMsg)
                    + " " + pd.pronouns);
        }

        if(ConfigUtils.isAllowDiscord()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.discordMsg)
                    + " " + pd.discord);
        }
    }

    public static void showInfoSelf(Player player) {
        if (!playerInfo.containsKey(player.getUniqueId())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix + " " + MessageUtils.youHaveNotRegistered));
            return;
        }
        PlayerData pd = info(player.getUniqueId());
        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.yourPersonalInfo));
        player.sendMessage(ChatColor.GREEN + "-----------------------------------");
        if(ConfigUtils.isAllowName()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.nameMsg)
                    + " " + pd.name);
        }

        if(ConfigUtils.isAllowAge()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.ageMsg)
                    + " " + pd.age);
        }

        if(ConfigUtils.isAllowBirthday()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.birthdayMsg)
                    + " " + pd.birthday);
        }

        if(ConfigUtils.isAllowLocation()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.locationMsg)
                    + " " + pd.location);
        }

        if(ConfigUtils.isAllowGender()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.genderMsg)
                    + " " + pd.gender);
        }

        if(ConfigUtils.isAllowPronouns()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.pronounsMsg)
                    + " " + pd.pronouns);
        }

        if(ConfigUtils.isAllowDiscord()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.discordMsg)
                    + " " + pd.discord);
        }
    }

    public static void setInfoSelf (Player player, String type, String data){
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.name = data;
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            try {
                pd.age = Integer.parseInt(data);
            } catch(NumberFormatException e) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.birthday = data;
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.location = data;
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.gender = data;
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.pronouns = data;
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.discord = data;
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                    + " " + ChatColor.translateAlternateColorCodes('&',
                    MessageUtils.unknownOptionType));
            return;
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                MessageUtils.prefix + " " + MessageUtils.setInformationMsg.replace("{option}", type)
                        .replace("{info}", data)));
        saveInfo();
    }

    public static void deleteInfoSelf (Player player, String type){
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.name = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.age = 0;
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.birthday = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.location = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.gender = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.pronouns = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.discord = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                    + " " + ChatColor.translateAlternateColorCodes('&',
                    MessageUtils.unknownOptionType));
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.removedDataSelf));
        saveInfo();
    }

    public static void deleteInfoOthers (CommandSender sender, Player player, String type){
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.name = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.age = 0;
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.birthday = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.location = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.gender = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.pronouns = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                        + " " + ChatColor.translateAlternateColorCodes('&',
                        MessageUtils.optionDisabled.replace("{option}", type)));
                return;
            }
            pd.discord = ChatColor.translateAlternateColorCodes('&', MessageUtils.dataNotSet);
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',MessageUtils.prefix)
                    + " " + ChatColor.translateAlternateColorCodes('&',
                    MessageUtils.unknownOptionType));
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                MessageUtils.prefix + MessageUtils.removedDataOthers.replace("{option}", type)
                        .replace("{player}", player.getDisplayName())));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageUtils.prefix +
                MessageUtils.dataRemovedByStaff));
        saveInfo();
    }

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
                playerInfo.put(pid, pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
