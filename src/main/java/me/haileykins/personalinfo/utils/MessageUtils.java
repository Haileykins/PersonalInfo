package me.haileykins.personalinfo.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling every interaction of the messages.yml
 *
 * @author Azarexs
 */
public class MessageUtils {

    private Path path, filePath;
    private static final String SUB_PATH = "messages";
    private Map<String, String> locale;

    /**
     * Constructor for class, calls setup
     * @param plugin The main class, used for data folder
     */
    public MessageUtils(Plugin plugin) {
        locale = new HashMap<>();
        path = plugin.getDataFolder().toPath();
        filePath = Paths.get(path.toString() + "/messages.yml");

        setup();
    }

    /**
     * Returns the desired message with no prefix
     * @param key The key in the messages yml to look for when grabbing the string
     * @return The message gathered from the yml
     */
    public String getMessage(String key) {
        return color(locale.get(key));
    }

    /**
     * Returns the desired message with a prefix
     * @param key The key in the messages yml to look for when grabbing the string
     * @return The message gathered from the yml, including the plugins prefix before it
     */
    public String getPrefixMessage(String key) {
        return color(locale.get("prefix") + " " + locale.get(key));
    }

    private void setup() {
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
                Files.copy(getClass().getResourceAsStream("/messages.yml"), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileConfiguration configuration = YamlConfiguration.loadConfiguration(filePath.toFile());

        for (String s : configuration.getConfigurationSection(SUB_PATH).getKeys(false)) {
            locale.put(s, configuration.getString(SUB_PATH + "." + s));
        }
    }

    private String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /**
     * Called whenever an option requires being returned with a capital first letter only
     * @param word The word to be formatted
     * @return The properly formatted word
     */
    public String formatOption(String word) {
        String fl = word.substring(0, 1).toUpperCase();
        String rst = word.substring(1).toLowerCase();
        return fl + rst;
    }

    /**
     * Called on server startup to load the messages.yml file
     */
    public void loadLang() {
        try {
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(filePath.toFile());
            fileConfiguration.save(filePath.toFile());
        } catch (IOException e) {
            Bukkit.getLogger().severe("FAILED TO LOAD LANGUAGE FILE");
        }
    }

}