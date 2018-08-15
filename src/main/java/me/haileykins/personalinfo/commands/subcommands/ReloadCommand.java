package me.haileykins.personalinfo.commands.subcommands;

import me.haileykins.personalinfo.PersonalInfo;
import me.haileykins.personalinfo.commands.CommandBase;
import me.haileykins.personalinfo.utils.ConfigUtils;
import me.haileykins.personalinfo.utils.DatabaseUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import me.haileykins.personalinfo.utils.SQLiteUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Class that handles the sub-command reload
 */
public class ReloadCommand extends CommandBase {

    private PersonalInfo plugin;
    private MessageUtils msgUtils;
    private ConfigUtils cfgUtils;
    private DatabaseUtils dbUtils;

    public ReloadCommand(PersonalInfo pl, MessageUtils messageUtils, ConfigUtils configUtils,
                         DatabaseUtils databaseUtils) {
        plugin = pl;
        msgUtils = messageUtils;
        cfgUtils = configUtils;
        dbUtils = databaseUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (sender.hasPermission("personalinfo.admin")) {

            if (args.size() != 2) {

                sender.sendMessage(msgUtils.getPrefixMessage("reload-help-msg"));
                return;

            }

            if (args.get(1).equalsIgnoreCase("lang")) {

                msgUtils.loadLang();
                sender.sendMessage(msgUtils.getPrefixMessage("lang-reloaded"));
                return;

            }

            if (args.get(1).equalsIgnoreCase("config")) {

                cfgUtils.reloadConfig();
                sender.sendMessage(msgUtils.getPrefixMessage("config-reloaded"));

                if (cfgUtils.useMySQL()) {
                    dbUtils.connectToSQL();
                } else {
                    dbUtils.connectToSQLite();
                    new SQLiteUtils(plugin);
                }
                return;

            }
            sender.sendMessage(msgUtils.getPrefixMessage("unknown-option-type"));
            return;
        }
        sender.sendMessage(msgUtils.getPrefixMessage("no-permission"));
    }

    @Override
    public String getCommand() {
        return "reload";
    }
}