package me.haileykins.personalinfo.commands.subcommands;

import me.haileykins.personalinfo.commands.CommandBase;
import me.haileykins.personalinfo.utils.DatabaseUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Class that handles the sub-command me
 */
public class MeCommand extends CommandBase {

    private DatabaseUtils dbUtils;
    private MessageUtils msgUtils;

    public MeCommand(DatabaseUtils databaseUtils, MessageUtils messageUtils) {
        dbUtils = databaseUtils;
        msgUtils = messageUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            dbUtils.showInfoSelf(player);
            return;

        }
        sender.sendMessage(msgUtils.getPrefixMessage("must-be-a-player"));
    }

    @Override
    public String getCommand() {
        return "me";
    }
}
