package me.haileykins.personalinfo.utils;

import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class handling sqlite file creation
 */
public class SQLiteUtils {

    private Path path;

    /**
     * Called on server startup or reload if plugin is set to use SQlite
     * @param plugin The main class, used for getting the data folder
     */
    public SQLiteUtils(Plugin plugin) {
        path = plugin.getDataFolder().toPath();

        try {
            initialization();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initialization() throws IOException {

        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }

        Path databasePath = Paths.get(path.toString() + "/PersonalInfo.db");

        if (Files.notExists(databasePath)) {
            Files.createFile(databasePath);
        }

    }

}
