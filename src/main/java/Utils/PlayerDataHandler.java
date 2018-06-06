package Utils;

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
            sender.sendMessage("Player: " + player.getName() + " has not registered any data!");
            return;
        }
        PlayerData pd = info(player.getUniqueId());
        sender.sendMessage("Personal Information for " + player.getName());
        if(ConfigUtils.isAllowName()) {
            sender.sendMessage("Name: " + pd.name);
        }

        if(ConfigUtils.isAllowAge()) {
            sender.sendMessage("Age: " + pd.age);
        }

        if(ConfigUtils.isAllowBirthday()) {
            sender.sendMessage("Birthday: " + pd.birthday);
        }

        if(ConfigUtils.isAllowLocation()) {
            sender.sendMessage("Location: " + pd.location);
        }

        if(ConfigUtils.isAllowGender()) {
            sender.sendMessage("Gender: " + pd.gender);
        }

        if(ConfigUtils.isAllowPronouns()) {
            sender.sendMessage("Pronouns: " + pd.pronouns);
        }

        if(ConfigUtils.isAllowDiscord()) {
            sender.sendMessage("Discord: " + pd.discord);
        }
    }

    public static void showInfoSelf(Player player) {
        if (!playerInfo.containsKey(player.getUniqueId())) {
            player.sendMessage("You have not registered any data!");
            return;
        }
        PlayerData pd = info(player.getUniqueId());
        if(ConfigUtils.isAllowName()) {
            player.sendMessage("Name: " + pd.name);
        }

        if(ConfigUtils.isAllowAge()) {
            player.sendMessage("Age: " + pd.age);
        }

        if(ConfigUtils.isAllowBirthday()) {
            player.sendMessage("Birthday: " + pd.birthday);
        }

        if(ConfigUtils.isAllowLocation()) {
            player.sendMessage("Location: " + pd.location);
        }

        if(ConfigUtils.isAllowGender()) {
            player.sendMessage("Gender: " + pd.gender);
        }

        if(ConfigUtils.isAllowPronouns()) {
            player.sendMessage("Pronouns: " + pd.pronouns);
        }

        if(ConfigUtils.isAllowDiscord()) {
            player.sendMessage("Discord: " + pd.discord);
        }
    }

    public static void setInfoSelf (Player player, String type, String data){
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                player.sendMessage("Names Are Disabled On This Server");
                return;
            }
            pd.name = data;
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                player.sendMessage("Ages Are Disabled On This Server");
                return;
            }
            try {
                pd.age = Integer.parseInt(data);
            } catch(NumberFormatException e) {
                player.sendMessage("Please Only Use Numbers!");
                return;
            }
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                player.sendMessage("Birthdays Are Disabled On This Server");
                return;
            }
            pd.birthday = data;
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                player.sendMessage("Locations Are Disabled On This Server");
                return;
            }
            pd.location = data;
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                player.sendMessage("Genders Are Disabled On This Server");
                return;
            }
            pd.gender = data;
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                player.sendMessage("Pronouns Are Disabled On This Server");
                return;
            }
            pd.pronouns = data;
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                player.sendMessage("Discords Are Disabled On This Server");
                return;
            }
            pd.discord = data;
        } else {
            player.sendMessage("Unknown Data Type!");
        }
        player.sendMessage(type + " Set To: " + data);
        saveInfo();
    }

    public static void deleteInfoSelf (Player player, String type){
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                player.sendMessage("Names Are Disabled On This Server");
                return;
            }
            pd.name = "Not Set";
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                player.sendMessage("Ages Are Disabled On This Server");
                return;
            }
            pd.age = 0;
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                player.sendMessage("Birthdays Are Disabled On This Server");
                return;
            }
            pd.birthday = "Not Set";
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                player.sendMessage("Locations Are Disabled On This Server");
                return;
            }
            pd.location = "Not Set";
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                player.sendMessage("Genders Are Disabled On This Server");
                return;
            }
            pd.gender = "Not Set";
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                player.sendMessage("Pronouns Are Disabled On This Server");
                return;
            }
            pd.pronouns = "Not Set";
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                player.sendMessage("Discords Are Disabled On This Server");
                return;
            }
            pd.discord = "Not Set";
        } else {
            player.sendMessage("Unknown Data Type!");
        }
        player.sendMessage("Removed your " + type + " from your Personal Information");
        saveInfo();
    }

    public static void deleteInfoOthers (CommandSender sender, Player player, String type){
        PlayerData pd = info(player.getUniqueId());
        if (type.equalsIgnoreCase("name")) {
            if (!ConfigUtils.isAllowName()) {
                sender.sendMessage("Names Are Disabled On This Server");
                return;
            }
            pd.name = "Not Set";
        } else if (type.equalsIgnoreCase("age")) {
            if (!ConfigUtils.isAllowAge()) {
                sender.sendMessage("Ages Are Disabled On This Server");
                return;
            }
            pd.age = 0;
        } else if (type.equalsIgnoreCase("birthday")) {
            if (!ConfigUtils.isAllowBirthday()) {
                sender.sendMessage("Birthdays Are Disabled On This Server");
                return;
            }
            pd.birthday = "Not Set";
        } else if (type.equalsIgnoreCase("location")) {
            if (!ConfigUtils.isAllowLocation()) {
                sender.sendMessage("Locations Are Disabled On This Server");
                return;
            }
            pd.location = "Not Set";
        } else if (type.equalsIgnoreCase("gender")) {
            if (!ConfigUtils.isAllowGender()) {
                sender.sendMessage("Genders Are Disabled On This Server");
                return;
            }
            pd.gender = "Not Set";
        } else if (type.equalsIgnoreCase("pronouns")) {
            if (!ConfigUtils.isAllowPronouns()) {
                sender.sendMessage("Pronouns Are Disabled On This Server");
                return;
            }
            pd.pronouns = "Not Set";
        } else if (type.equalsIgnoreCase("discord")) {
            if (!ConfigUtils.isAllowDiscord()) {
                sender.sendMessage("Discords Are Disabled On This Server");
                return;
            }
            pd.discord = "Not Set";
        } else {
            player.sendMessage("Unknown Data Type!");
        }
        sender.sendMessage("Removed " + type + " From Player " + player.getDisplayName());
        player.sendMessage("[PersonalInfo] Your " + type + " Was Removed by Staff");
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
                plugin.getLogger().info("playerInfo.yml created.");
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
