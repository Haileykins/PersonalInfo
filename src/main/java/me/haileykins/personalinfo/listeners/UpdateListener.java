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

public class UpdateListener  implements Listener {

    private PersonalInfo plugin;
    private ConfigUtils cfgUtils;
    private MessageUtils msgUtils;

    private final String resourceURL = "https://api.spigotmc.org/legacy/update.php?resource=58322";

    public UpdateListener(ConfigUtils configUtils, PersonalInfo pl, MessageUtils messageUtils) {
        cfgUtils = configUtils;
        plugin = pl;
        msgUtils = messageUtils;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!cfgUtils.updaterEnabled) {
            return;
        }

        Player player = event.getPlayer();

        if (player.hasPermission("personalinfo.admin")) {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    HttpsURLConnection connection = (HttpsURLConnection) new URL(resourceURL).openConnection();
                    String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

                    if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                        player.sendMessage(msgUtils.transAltColors(msgUtils.prefix + " " + msgUtils.pluginOutOfDate));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
