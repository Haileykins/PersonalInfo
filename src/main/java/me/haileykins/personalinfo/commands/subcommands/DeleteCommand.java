package me.haileykins.personalinfo.commands.subcommands;

import me.haileykins.personalinfo.commands.CommandBase;
import me.haileykins.personalinfo.utils.CommandUtils;
import me.haileykins.personalinfo.utils.DatabaseUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Class that handles the sub-command delete
 */
public class DeleteCommand extends CommandBase {

    private DatabaseUtils dbUtils;
    private MessageUtils msgUtils;
    private CommandUtils cmdUtils;

    public DeleteCommand(DatabaseUtils databaseUtils, MessageUtils messageUtils, CommandUtils commandUtils) {
        dbUtils = databaseUtils;
        msgUtils = messageUtils;
        cmdUtils = commandUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.size() == 2) {

                if (!cmdUtils.validateType(args.get(1))) {
                    player.sendMessage(msgUtils.getPrefixMessage("invalid-type"));
                    return;
                }

                dbUtils.deleteInfoSelf(player, args.get(1));

                player.sendMessage(msgUtils.getPrefixMessage("removed-data-self")
                        .replace("{option}", msgUtils.formatOption(args.get(1))));

                return;

            }
            player.sendMessage(msgUtils.getPrefixMessage("invalid-number-of-arguments"));
            return;
        }
        sender.sendMessage(msgUtils.getPrefixMessage("must-be-a-player"));
    }

    @Override
    public String getCommand() {
        return "delete";
    }
}
