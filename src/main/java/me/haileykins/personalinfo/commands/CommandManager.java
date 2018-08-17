package me.haileykins.personalinfo.commands;

import me.haileykins.personalinfo.PersonalInfo;
import me.haileykins.personalinfo.commands.subcommands.*;
import me.haileykins.personalinfo.utils.CommandUtils;
import me.haileykins.personalinfo.utils.ConfigUtils;
import me.haileykins.personalinfo.utils.DatabaseUtils;
import me.haileykins.personalinfo.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class for handling all sub-commands the plugin can read
 */
public class CommandManager implements CommandExecutor {

    private CommandUtils cmdUtils;
    private MessageUtils msgUtils;

    private Set<CommandBase> commands;

    /**
     * Constructor that handles adding the commands to the commands set
     * @param plugin A copy of the main class, PersonalInfo
     * @param dbUtils A copy of DatabaseUtils
     * @param messageUtils A copy of MessageUtils
     * @param commandUtils A copy of CommandUtils
     * @param cfgUtils A copy of ConfigUtils
     *
     * @see PersonalInfo
     * @see DatabaseUtils
     * @see MessageUtils
     * @see CommandUtils
     * @see ConfigUtils
     */
    public CommandManager(PersonalInfo plugin, DatabaseUtils dbUtils, MessageUtils messageUtils,
                          CommandUtils commandUtils, ConfigUtils cfgUtils) {

        cmdUtils = commandUtils;
        msgUtils = messageUtils;

        commands = new HashSet<>();
        commands.add(new ClearCommand(dbUtils, msgUtils));
        commands.add(new ClrOthersCommand(dbUtils, msgUtils));
        commands.add(new DeleteCommand(dbUtils, msgUtils, cmdUtils));
        commands.add(new DelOthersCommand(dbUtils, msgUtils, cmdUtils));
        commands.add(new MeCommand(dbUtils, msgUtils));
        commands.add(new ReloadCommand(plugin, msgUtils, cfgUtils, dbUtils));
        commands.add(new SetCommand(cmdUtils, msgUtils, dbUtils));
        commands.add(new ShowCommand(dbUtils, msgUtils));

    }

    /**
     * Called when the plugin detects that /pi was run, also starts checking the sub-command
     * @param sender The CommandSender who executed the command
     * @param cmd The command ran, /pi
     * @param label Unused in PersonalInfo
     * @param args The arguments given for the command
     * @return The True or False value of the successful execution of the command
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            if (sender.hasPermission("personalinfo.admin")) {
                cmdUtils.staffHelp(sender);
                return true;
            }
            cmdUtils.playerHelp(sender);
            return true;

        }

        CommandBase command = getCommand(args[0]);

        if (command == null) {
            sender.sendMessage(msgUtils.getPrefixMessage("invalid-subcommand"));
            return true;
        }

        List<String> arguments = Arrays.asList(args);

        command.onCommand(sender, arguments);

        return true;
    }


    private CommandBase getCommand(String command) {
        for (CommandBase commandBase : commands) {
            if (commandBase.getCommand().equalsIgnoreCase(command)) {
                return commandBase;
            }
        }
        return null;
    }
}
