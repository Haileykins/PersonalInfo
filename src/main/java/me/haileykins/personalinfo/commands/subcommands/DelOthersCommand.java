package me.haileykins.personalinfo.commands.subcommands;

import me.haileykins.personalinfo.commands.CommandBase;
import me.haileykins.personalinfo.utils.CommandUtils;
import me.haileykins.personalinfo.utils.DatabaseUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Class that handles the sub-command delothers
 */
public class DelOthersCommand extends CommandBase {

    private DatabaseUtils dbUtils;
    private MessageUtils msgUtils;
    private CommandUtils cmdUtils;

    public DelOthersCommand(DatabaseUtils databaseUtils, MessageUtils messageUtils, CommandUtils commandUtils) {
        dbUtils = databaseUtils;
        msgUtils = messageUtils;
        cmdUtils = commandUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (sender.hasPermission("personalinfo.admin")) {

            if (args.size() != 3) {

                sender.sendMessage(msgUtils.getPrefixMessage("invalid-number-of-arguments"));
                return;

            }

            Player target = Bukkit.getPlayer(args.get(2));

            if (target != null) {

                if (!cmdUtils.validateType(args.get(1))) {
                    sender.sendMessage(msgUtils.getPrefixMessage("invalid-type"));
                    return;
                }

                dbUtils.deleteInfoOthers(sender, target, args.get(1));
                return;

            }
            sender.sendMessage(msgUtils.getPrefixMessage("player-not-found"));
            return;
        }
        sender.sendMessage(msgUtils.getPrefixMessage("no-permission"));
    }

    @Override
    public String getCommand() {
        return "delothers";
    }
}
