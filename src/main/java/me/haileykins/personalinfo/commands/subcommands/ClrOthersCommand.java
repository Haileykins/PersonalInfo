package me.haileykins.personalinfo.commands.subcommands;

import me.haileykins.personalinfo.commands.CommandBase;
import me.haileykins.personalinfo.utils.DatabaseUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Class that handles the sub-command clrothers
 */
public class ClrOthersCommand extends CommandBase {

    private DatabaseUtils dbUtils;
    private MessageUtils msgUtils;

    public ClrOthersCommand(DatabaseUtils databaseUtils, MessageUtils messageUtils) {
        dbUtils = databaseUtils;
        msgUtils = messageUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (sender.hasPermission("personalinfo.admin")) {

            if (args.size() != 2) {

                sender.sendMessage(msgUtils.getPrefixMessage("invalid-number-of-arguments"));
                return;

            }

            Player target = Bukkit.getPlayer(args.get(1));

            if (target != null) {

                dbUtils.clearInfoOthers(sender, target);
                return;

            }

            sender.sendMessage(msgUtils.getPrefixMessage("player-not-found"));
            return;

        }
        sender.sendMessage(msgUtils.getPrefixMessage("no-permission"));
    }

    @Override
    public String getCommand() {
        return "clrothers";
    }
}
