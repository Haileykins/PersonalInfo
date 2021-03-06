package me.haileykins.personalinfo.listeners;

import me.haileykins.personalinfo.PersonalInfo;
import me.haileykins.personalinfo.utils.ConfigUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Class for handling everything related to the update notifications for server admin
 */
public class UpdateListener implements Listener {

    private PersonalInfo plugin;
    private ConfigUtils cfgUtils;
    private MessageUtils msgUtils;

    private final String resourceURL = "https://api.spigotmc.org/legacy/update.php?resource=58322";

    public UpdateListener(ConfigUtils configUtils, PersonalInfo pl, MessageUtils messageUtils) {
        cfgUtils = configUtils;
        plugin = pl;
        msgUtils = messageUtils;
    }

    /**
     * Called when a player joins, designed to tell players with permissions that the plugin needs updating
     * @param event The PlayerJoinEvent
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!cfgUtils.isUpdaterEnabled()) {
            return;
        }

        Player player = event.getPlayer();

        if (!player.hasPermission("personalinfo.admin")) {
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                HttpsURLConnection connection = (HttpsURLConnection) new URL(resourceURL).openConnection();
                String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

                if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                    player.sendMessage(msgUtils.getPrefixMessage("plugin-out-of-date"));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
