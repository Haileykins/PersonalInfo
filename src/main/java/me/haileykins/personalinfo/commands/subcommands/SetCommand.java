package me.haileykins.personalinfo.commands.subcommands;

import me.haileykins.personalinfo.commands.CommandBase;
import me.haileykins.personalinfo.utils.CommandUtils;
import me.haileykins.personalinfo.utils.DatabaseUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Class that handles the sub-command set
 */
public class SetCommand extends CommandBase {

    private CommandUtils cmdUtils;
    private MessageUtils msgUtils;
    private DatabaseUtils dbUtils;

    public SetCommand(CommandUtils commandUtils, MessageUtils messageUtils, DatabaseUtils databaseUtils) {
        cmdUtils = commandUtils;
        msgUtils = messageUtils;
        dbUtils = databaseUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(msgUtils.getPrefixMessage("must-be-a-player"));
            return;
        }

        Player player = (Player) sender;

        if (args.size() == 1) {
            cmdUtils.setHelp(player);
            return;
        }

        if (args.size() == 2) {
            player.sendMessage(msgUtils.getPrefixMessage("specify-type"));
            return;
        }

        if (!cmdUtils.validateType(args.get(1))) {
            player.sendMessage(msgUtils.getPrefixMessage("invalid-type"));
            return;
        }

        if (cmdUtils.optionDisabled(args.get(1))) {
            player.sendMessage(msgUtils.getPrefixMessage("option-disabled"));
            return;
        }

        List<String> incoming = args.subList(2, args.size());
        String outgoing = String.join(" ", incoming);

        if (!cmdUtils.validateRegex(args.get(1), outgoing)) {
            player.sendMessage(msgUtils.getPrefixMessage("invalid-type-format")
                    .replace("{option}", msgUtils.formatOption(args.get(1)))
                    .replace("{chars}", cmdUtils.getAllowedChars())
                    .replace("{count}", String.valueOf(cmdUtils.getMaxChar()))
                    .replace("{newline}", "\n" + msgUtils.getMessage("prefix") + " ")
                    .replace("{exampleformat}", cmdUtils.getExample()));

            return;
        }

        dbUtils.setInfo(player, args.get(1), outgoing);

        player.sendMessage(msgUtils.getPrefixMessage("set-information-msg")
                .replace("{option}", msgUtils.formatOption(args.get(1)))
                .replace("{info}", outgoing));

    }

    @Override
    public String getCommand() {
        return "set";
    }
}
