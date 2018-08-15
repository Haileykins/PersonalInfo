package me.haileykins.personalinfo.utils;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.*;

/**
 * Class designed to handle all interactions with SQlite and MySQL
 */
public class DatabaseUtils {

    private ConfigUtils cfgUtils;
    private DataUtils dataUtils;

    public DatabaseUtils(ConfigUtils configUtils, DataUtils dataUtilities) {
        cfgUtils = configUtils;
        dataUtils = dataUtilities;
    }

    private HikariDataSource hikari;

    /**
     * Called if MySQL is enabled, connects to the specified SQL database given in the config file
     * Called on reload and/or startup
     *
     * @see me.haileykins.personalinfo.PersonalInfo
     * @see me.haileykins.personalinfo.commands.subcommands.ReloadCommand
     */
    public void connectToSQL() {
        String address = cfgUtils.getAddress();
        int port = cfgUtils.getPort();
        String dbName = cfgUtils.getDatabase();
        String username = cfgUtils.getUsername();
        String password = cfgUtils.getPassword();

        hikari = new HikariDataSource();

        hikari.setPoolName("PersonalInfo SQL");
        hikari.setMaximumPoolSize(10);

        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        hikari.addDataSourceProperty("ServerName", address);
        hikari.addDataSourceProperty("port", port);
        hikari.addDataSourceProperty("databaseName", dbName);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);

        createTable();
    }

    /**
     * Called if SQlite is enabled, creates or connects to local database in plugins data folder
     * Called on reload and/or startup
     *
     * @see me.haileykins.personalinfo.PersonalInfo
     * @see me.haileykins.personalinfo.commands.subcommands.ReloadCommand
     */
    public void connectToSQLite() {
        hikari = new HikariDataSource();

        hikari.setPoolName("PersonalInfo SQLite");
        hikari.setDriverClassName("org.sqlite.JDBC");
        hikari.setJdbcUrl("jdbc:sqlite:plugins/PersonalInfo/PersonalInfo.db");
        hikari.setMaximumPoolSize(10);

        createTable();
    }

    private void createTable() {
        Connection connection = null;
        PreparedStatement createTableStatement = null;
        ResultSet results = null;

        String createTable = "CREATE TABLE playerdata " +
                "(uuid VARCHAR(36)," +
                "name VARCHAR(20)," +
                "age INTEGER," +
                "birthday VARCHAR(10)," +
                "location VARCHAR(35)," +
                "gender VARCHAR(25)," +
                "pronouns VARCHAR(15)," +
                "discord VARCHAR(40)," +
                "youtube VARCHAR(60)," +
                "twitch VARCHAR(60)," +
                "steam VARCHAR(60)," +
                "bio VARCHAR(255));";

        try {

            connection = hikari.getConnection();

            DatabaseMetaData dbm = connection.getMetaData();
            results = dbm.getTables(null, null, "playerdata", null);

            if (results.next()) {
                return;
            }

            createTableStatement = connection.prepareStatement(createTable);

            createTableStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (createTableStatement != null) {
                try {
                    createTableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when a user attempts to set information on their profile, called only after many checks
     * @param player The person attempting to set data into their profile
     * @param type The type of data the person is attempting to set
     * @param data The information they are attempting to set to the type
     *
     * @see me.haileykins.personalinfo.commands.subcommands.SetCommand
     * @see CommandUtils
     */
    public void setInfo(Player player, String type, String data) {
        Connection connection = null;
        ResultSet records = null;
        String set;

        String check = "SELECT * FROM playerdata WHERE uuid = '" + player.getUniqueId() + "'";
        PreparedStatement checkStatement = null;

        String createRecord = "INSERT INTO playerdata VALUES ('" + player.getUniqueId() + "', 'Not Set', 0, " +
                "'Not Set', 'Not Set', 'Not Set', 'Not Set', 'Not Set', 'Not Set', 'Not Set', 'Not Set', 'Not Set')";

        PreparedStatement createStatement = null;

        if (!type.equalsIgnoreCase("age")) {
            set = "UPDATE playerdata SET " + type.toLowerCase() + "='" + StringEscapeUtils.escapeSql(data) + "' WHERE uuid='" + player.getUniqueId() + "'";
        } else {
            set = "UPDATE playerdata SET " + type.toLowerCase() + "='" + data + "' WHERE uuid='" + player.getUniqueId() + "'";
        }
        PreparedStatement setStatement = null;

        try {
            // Gets An Open Connection
            connection = hikari.getConnection();

            // Creates Prepared Statement
            checkStatement = connection.prepareStatement(check);

            // Runs Query Check
            records = checkStatement.executeQuery();

            // If No Results Creates Record
            if (!records.next()) {

                createStatement = connection.prepareStatement(createRecord);
                createStatement.execute();

            }

                setStatement = connection.prepareStatement(set);
                setStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (checkStatement != null) {
                try {
                    checkStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (createStatement != null) {
                try {
                    createStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (setStatement != null) {
                try {
                    setStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (records != null) {
                try {
                    records.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when a player runs /pi me to display their own information
     * @param player The player who runs the command, as well as the player whos data is to be shown
     *
     * @see me.haileykins.personalinfo.commands.subcommands.MeCommand
     */
    public void showInfoSelf(Player player) {
        Connection connection = null;
        ResultSet results = null;

        String query = "SELECT * FROM playerdata WHERE uuid='" + player.getUniqueId() + "';";
        PreparedStatement queryStatement = null;

        try {
            connection = hikari.getConnection();

            queryStatement = connection.prepareStatement(query);

            results = queryStatement.executeQuery();

            if (results.next()) {

                DataHelper data = new DataHelper();

                data.name = results.getString("name");
                data.age = results.getInt("age");
                data.birthday = results.getString("birthday");
                data.location = results.getString("location");
                data.gender = results.getString("gender");
                data.pronouns = results.getString("pronouns");
                data.discord = results.getString("discord");
                data.youtube = results.getString("youtube");
                data.twitch = results.getString("twitch");
                data.steam = results.getString("steam");
                data.bio = results.getString("bio");

                dataUtils.showInfoSelf(data, player);
                return;

            }

            dataUtils.noDataSelf(player);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (queryStatement != null) {
                try {
                    queryStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when a player runs /pi show (name) to display the information of another user
     * @param sender The CommandSender who issued the command
     * @param target The player whos information to retrieve from the database
     *
     * @see me.haileykins.personalinfo.commands.subcommands.ShowCommand
     */
    public void showInfoOthers(CommandSender sender, Player target) {
        Connection connection = null;
        ResultSet results = null;

        String query = "SELECT * FROM playerdata WHERE uuid='" + target.getUniqueId() + "';";
        PreparedStatement queryStatement = null;

        try {
            connection = hikari.getConnection();

            queryStatement = connection.prepareStatement(query);

            results = queryStatement.executeQuery();

            if (results.next()) {

                DataHelper data = new DataHelper();

                data.name = results.getString("name");
                data.age = results.getInt("age");
                data.birthday = results.getString("birthday");
                data.location = results.getString("location");
                data.gender = results.getString("gender");
                data.pronouns = results.getString("pronouns");
                data.discord = results.getString("discord");
                data.youtube = results.getString("youtube");
                data.twitch = results.getString("twitch");
                data.steam = results.getString("steam");
                data.bio = results.getString("bio");

                dataUtils.showInfoOthers(data, sender, target);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (queryStatement != null) {
                try {
                    queryStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when a CommandSender runs /pi delete (type) to remove a type of data from their profile
     * @param player The player who ran the command, and the player who's data to remove
     * @param type The type of data to be reset
     *
     * @see me.haileykins.personalinfo.commands.subcommands.DeleteCommand
     */
    public void deleteInfoSelf(Player player, String type) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        ResultSet results = null;

        String query = "SELECT * FROM playerdata WHERE uuid='" + player.getUniqueId() + "';";
        PreparedStatement queryStatement = null;

        try {
            connection = hikari.getConnection();

            queryStatement = connection.prepareStatement(query);

            results = queryStatement.executeQuery();

            if (results.next()) {

                if (type.equalsIgnoreCase("age")) {

                    if (!(results.getInt("age") == 0)) {

                        String delete = "UPDATE playerdata SET age = 0 WHERE uuid='" + player.getUniqueId() + "';";

                        deleteStatement = connection.prepareStatement(delete);
                        deleteStatement.executeUpdate();
                        return;

                    }

                }

                String delete = "UPDATE playerdata SET " + type.toLowerCase() + "='Not Set' WHERE uuid='" +
                        player.getUniqueId() + "';";

                deleteStatement = connection.prepareStatement(delete);
                deleteStatement.executeUpdate();
                return;

            }

            dataUtils.noDataSelf(player);

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (queryStatement != null) {
                try {
                    queryStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (deleteStatement != null) {
                try {
                    deleteStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when a CommandSender runs /pi delothers (type) (user) to delete a type of data from another user
     * @param sender The CommandSender who issued the command
     * @param target The player who's data type will be deleted
     * @param type The type of data to remove from the target
     *
     * @see me.haileykins.personalinfo.commands.subcommands.DelOthersCommand
     */
    public void deleteInfoOthers(CommandSender sender, Player target, String type) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        ResultSet results = null;

        String query = "SELECT * FROM playerdata WHERE uuid='" + target.getUniqueId() + "';";
        PreparedStatement queryStatement = null;

        try {
            connection = hikari.getConnection();

            queryStatement = connection.prepareStatement(query);

            results = queryStatement.executeQuery();

            if (results.next()) {

                if (type.equalsIgnoreCase("age")) {
                    System.out.println(2);

                    if (!(results.getInt("age") == 0)) {
                        System.out.println(3);

                        String delete = "UPDATE playerdata SET age = 0 WHERE uuid='" + target.getUniqueId() + "';";

                        deleteStatement = connection.prepareStatement(delete);
                        deleteStatement.executeUpdate();
                        return;

                    }


                }

                String delete = "UPDATE playerdata SET " + type.toLowerCase() + "='Not Set' WHERE uuid='" + target.getUniqueId() + "';";

                deleteStatement = connection.prepareStatement(delete);
                deleteStatement.executeUpdate();

                dataUtils.deleteInfoOthers(sender, target, type);
                return;
            }

            dataUtils.noDataOthers(sender, target.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (queryStatement != null) {
                try {
                    queryStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (deleteStatement != null) {
                try {
                    deleteStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when a player runs /pi clear to entirely remove their data from the database
     * @param player The player who ran the command, and the player who's data to remove
     *
     * @see me.haileykins.personalinfo.commands.subcommands.ClearCommand
     */
    public void clearInfoSelf(Player player) {
        Connection connection = null;
        ResultSet results = null;

        String query = "SELECT * FROM playerdata WHERE uuid='" + player.getUniqueId() + "';";
        PreparedStatement queryStatement = null;

        String clear = "DELETE FROM playerdata WHERE uuid='" + player.getUniqueId() + "';";
        PreparedStatement clearStatement = null;

        try {
            connection = hikari.getConnection();

            queryStatement = connection.prepareStatement(query);

            results = queryStatement.executeQuery();

            if (results.next()) {
                clearStatement = connection.prepareStatement(clear);
                clearStatement.executeUpdate();

                dataUtils.clearInfoSelf(player);
                return;
            }

            dataUtils.noDataSelf(player);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (queryStatement != null) {
                try {
                    queryStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (clearStatement != null) {
                try {
                    clearStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when a player runs /pi clear (user) to entirely remove a players data from the database
     * @param sender The CommandSender who issued the command
     * @param target The player who's data will be cleared from all record
     *
     * @see me.haileykins.personalinfo.commands.subcommands.ClrOthersCommand
     */
    public void clearInfoOthers(CommandSender sender, Player target) {
        Connection connection = null;
        ResultSet results = null;

        String query = "SELECT * FROM playerdata WHERE uuid='" + target.getUniqueId() + "';";
        PreparedStatement queryStatement = null;

        String clear = "DELETE FROM playerdata WHERE uuid='" + target.getUniqueId() + "';";
        PreparedStatement clearStatement = null;

        try {
            connection = hikari.getConnection();

            queryStatement = connection.prepareStatement(query);

            results = queryStatement.executeQuery();

            if (results.next()) {

                clearStatement = connection.prepareStatement(clear);
                clearStatement.executeUpdate();

                dataUtils.clearInfoOthers(sender, target);
                return;

            }

            dataUtils.noDataOthers(sender, target.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (queryStatement != null) {
                try {
                    queryStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (clearStatement != null) {
                try {
                    clearStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
