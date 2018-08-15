package me.haileykins.personalinfo.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Abstract class that acts as the base of the commands, requiring an onCommand and getCommand method
 */
public abstract class CommandBase {

    /**
     * The Method called when the sub-oommand is ran
     * @param sender The CommandSender that ran the command
     * @param args The arguments passed to the command
     */
    public abstract void onCommand(CommandSender sender, List<String> args);

    /**
     * The method to return the String value of the sub-command
     * @return The string value of the sub-command
     */
    public abstract String getCommand();

}
