package me.haileykins.personalinfo.commands.subcommands;

import me.haileykins.personalinfo.commands.CommandBase;
import me.haileykins.personalinfo.utils.DatabaseUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Class that handles the sub-command show
 */
public class ShowCommand extends CommandBase {

    private DatabaseUtils dbUtils;
    private MessageUtils msgUtils;

    public ShowCommand(DatabaseUtils databaseUtils, MessageUtils messageUtils) {
        dbUtils = databaseUtils;
        msgUtils = messageUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (args.size() != 2) {

            sender.sendMessage(msgUtils.getPrefixMessage("invalid-number-of-arguments"));
            return;

        }

        for (OfflinePlayer target : Bukkit.getOfflinePlayers()) {

            if (target.getName().equalsIgnoreCase(args.get(1))) {
                dbUtils.showInfoOthers(sender, (Player) target);
                return;
            }
        }

        sender.sendMessage(msgUtils.getPrefixMessage("player-not-found"));

    }

    @Override
    public String getCommand() {
        return "show";
    }
}
