package me.haileykins.personalinfo.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class for handling larger cumbersome tasks that commands sometimes need to run
 */
public class CommandUtils {

    private int maxChar;
    private String allowedChars;
    private String example;

    private MessageUtils msgUtils;
    private ConfigUtils cfgUtils;

    public CommandUtils(MessageUtils messageUtils, ConfigUtils configUtils) {
        msgUtils = messageUtils;
        cfgUtils = configUtils;
    }

    /**
     * Called when /pi run and the sender doesn't have admin perms
     * @param sender The CommandSender that issued the command
     *
     * @see me.haileykins.personalinfo.commands.CommandManager
     */
    public void playerHelp(CommandSender sender) {
        sender.sendMessage(msgUtils.getMessage("help-menu-title"));
        sender.sendMessage(ChatColor.GOLD + "/pi - " + msgUtils.getMessage("help-menu-pi-command"));
        sender.sendMessage(ChatColor.GOLD + "/pi clear - " + msgUtils.getMessage("help-menu-clear-command"));
        sender.sendMessage(ChatColor.GOLD + "/pi delete (type) - " + msgUtils.getMessage("help-menu-delete-command"));
        sender.sendMessage(ChatColor.GOLD + "/pi me - " + msgUtils.getMessage("help-menu-me-command"));
        sender.sendMessage(ChatColor.GOLD + "/pi set (type) (info) - " + msgUtils.getMessage("help-menu-set-command"));
        sender.sendMessage(ChatColor.GOLD + "/pi show (ign) - " + msgUtils.getMessage("help-menu-show-command"));
    }

    /**
     * Called when /pi run and the sender has admin perms
     * @param sender The CommandSender that issued the command
     *
     * @see me.haileykins.personalinfo.commands.CommandManager
     */
    public void staffHelp(CommandSender sender) {
        playerHelp(sender);
        sender.sendMessage(ChatColor.GOLD + "/pi clrothers (ign) - " + msgUtils.getMessage("help-menu-clear-others-command"));
        sender.sendMessage(ChatColor.GOLD + "/pi delothers (type) (ign) - " + msgUtils.getMessage("help-menu-delete-others-command"));
        sender.sendMessage(ChatColor.GOLD + "/pi reload (lang/config) - " + msgUtils.getMessage("help-menu-show-command"));
    }

    /**
     * Called when the sender runs /pi set without any other arguments
     * @param player The player who issued the command
     *
     * @see me.haileykins.personalinfo.commands.subcommands.SetCommand
     */
    public void setHelp(Player player) {
        player.sendMessage(msgUtils.getMessage("help-menu-title"));
        if (cfgUtils.isAllowName()) {
            player.sendMessage(ChatColor.GOLD + "/pi set name " + ChatColor.DARK_GRAY + "(your name)");
        }

        if (cfgUtils.isAllowNickname()) {
            player.sendMessage(ChatColor.GOLD + "/pi set nickname " + ChatColor.DARK_GRAY + "(your nickname)");
        }

        if (cfgUtils.isAllowAge()) {
            player.sendMessage(ChatColor.GOLD + "/pi set age " + ChatColor.DARK_GRAY + "(your age)");
        }

        if (cfgUtils.isAllowBirthday()) {
            player.sendMessage(ChatColor.GOLD + "/pi set birthday " + ChatColor.DARK_GRAY + "(your birthday)");
        }

        if (cfgUtils.isAllowLocation()) {
            player.sendMessage(ChatColor.GOLD + "/pi set location " + ChatColor.DARK_GRAY + "(your location)");
        }

        if (cfgUtils.isAllowGender()) {
            player.sendMessage(ChatColor.GOLD + "/pi set gender " + ChatColor.DARK_GRAY + "(your gender)");
        }

        if (cfgUtils.isAllowPronouns()) {
            player.sendMessage(ChatColor.GOLD + "/pi set pronouns " + ChatColor.DARK_GRAY + "(your pronouns)");
        }

        if (cfgUtils.isAllowDiscord()) {
            player.sendMessage(ChatColor.GOLD + "/pi set discord " + ChatColor.DARK_GRAY + "(your discord)");
        }

        if (cfgUtils.isAllowYoutube()) {
            player.sendMessage(ChatColor.GOLD + "/pi set youtube " + ChatColor.DARK_GRAY + "(your youtube)");
        }

        if (cfgUtils.isAllowTwitch()) {
            player.sendMessage(ChatColor.GOLD + "/pi set twitch " + ChatColor.DARK_GRAY + "(your twitch)");
        }

        if (cfgUtils.isAllowSteam()) {
            player.sendMessage(ChatColor.GOLD + "/pi set steam " + ChatColor.DARK_GRAY + "(your steam)");
        }

        if (cfgUtils.isAllowBio()) {
            player.sendMessage(ChatColor.GOLD + "/pi set bio " + ChatColor.DARK_GRAY + "(your bio)");
        }
    }

    /**
     * Called from the Set Command to verify the type a player is trying to set is allowed on the server
     * @param option The type of data they are trying to set
     * @return The True or False value of whether the option is disabled or not
     *
     * @see me.haileykins.personalinfo.commands.subcommands.SetCommand
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean optionDisabled(String option) {

        if (option.equalsIgnoreCase("name")) {
            return !cfgUtils.isAllowName();
        }

        if (option.equalsIgnoreCase("nickname")) {
            return !cfgUtils.isAllowNickname();
        }

        if (option.equalsIgnoreCase("age")) {
            return !cfgUtils.isAllowAge();
        }

        if (option.equalsIgnoreCase("birthday")) {
            return !cfgUtils.isAllowBirthday();
        }

        if (option.equalsIgnoreCase("location")) {
            return !cfgUtils.isAllowLocation();
        }

        if (option.equalsIgnoreCase("gender")) {
            return !cfgUtils.isAllowGender();
        }

        if (option.equalsIgnoreCase("pronouns")) {
            return !cfgUtils.isAllowPronouns();
        }

        if (option.equalsIgnoreCase("discord")) {
            return !cfgUtils.isAllowDiscord();
        }

        if (option.equalsIgnoreCase("youtube")) {
            return !cfgUtils.isAllowYoutube();
        }

        if (option.equalsIgnoreCase("twitch")) {
            return !cfgUtils.isAllowTwitch();
        }

        if (option.equalsIgnoreCase("steam")) {
            return !cfgUtils.isAllowSteam();
        }

        if (option.equalsIgnoreCase("bio")) {
            return !cfgUtils.isAllowBio();
        }

        return true;

    }

    /**
     * Called from commands to verify the type of data a player is trying to set exists
     * @param type The type of data they are trying to set
     * @return The True or False value of whether the option is valid or not
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean validateType(String type) {

        if (type.equalsIgnoreCase("name")) {
            return true;
        }

        if (type.equalsIgnoreCase("nickname")) {
            return true;
        }

        if (type.equalsIgnoreCase("age")) {
            return true;
        }

        if (type.equalsIgnoreCase("birthday")) {
            return true;
        }

        if (type.equalsIgnoreCase("location")) {
            return true;
        }

        if (type.equalsIgnoreCase("gender")) {
            return true;
        }

        if (type.equalsIgnoreCase("pronouns")) {
            return true;
        }

        if (type.equalsIgnoreCase("discord")) {
            return true;
        }

        if (type.equalsIgnoreCase("youtube")) {
            return true;
        }

        if (type.equalsIgnoreCase("twitch")) {
            return true;
        }

        if (type.equalsIgnoreCase("steam")) {
            return true;
        }

        //noinspection RedundantIfStatement
        if (type.equalsIgnoreCase("bio")) {
            return true;
        }

        return false;

    }

    /**
     * Called from the Set Command to validate that the data they entered is within the data types boundaries
     * @param option The data type they are attempting to set
     * @param data The data they are attemping to set to the option
     * @return The True or False value of whether the option passes the REGEX check or not
     */
    public boolean validateRegex(String option, String data) {

        if (option.equalsIgnoreCase("name")) {


            String regex = "[A-Za-z]{1,20}";

            allowedChars = "A-Z, a-z";
            example = "Jasmine";
            maxChar = 20;

            return data.matches(regex);

        }

        if (option.equalsIgnoreCase("nickname")) {

            String regex = "[A-Za-z,\\s]{1,25}";

            allowedChars = "A-Z, a-z, comma";
            example = "Jass, Jassy, Jasper; Jass";
            maxChar = 25;

            return data.matches(regex);
        }

        if (option.equalsIgnoreCase("age")) {

            String regex = "\\d(\\d)?";

            allowedChars = "0-9";
            example = "18";
            maxChar = 2;

            return data.matches(regex);

        }

        if (option.equalsIgnoreCase("birthday")) {

            String regex = "\\d(\\d)?/\\d(\\d)?/\\d\\d(\\d\\d)?";

            allowedChars = "0-9, /";
            example = "07/08/94, 07/08/1994, 7/8/94, 7/8/1994";
            maxChar = 10;

            return data.matches(regex);

        }

        if (option.equalsIgnoreCase("location")) {

            String regex = "[[A-Za-z]+,\\s]{1,35}";

            allowedChars = "A-Z, a-z";
            example = "Texas; Texas, USA";
            maxChar = 35;

            return data.matches(regex);

        }

        if (option.equalsIgnoreCase("gender")) {

            String regex = "[A-Za-z]{1,20}";

            allowedChars = "A-Z, a-z";
            example = "Female";
            maxChar = 20;

            return data.matches(regex);

        }

        if (option.equalsIgnoreCase("pronouns")) {

            String regex = "[[A-Za-z/]+]{1,25}";

            allowedChars = "A-Z, a-z, /";
            example = "She/Her, Feminine";
            maxChar = 25;

            return data.matches(regex);

        }

        if (option.equalsIgnoreCase("discord")) {

            String regex = "[^.+]{1,34}\\S#[\\d]{4}";

            allowedChars = "A-Z, a-z, 0-9, Symbols";
            example = "JohnWayne#5578, John Wayne#5576";
            maxChar = 40;

            return data.matches(regex);

        }

        if (option.equalsIgnoreCase("youtube")) {

            String regex = "youtube\\.com/[\\w/]{5,40}";

            allowedChars = "A-Z, a-z, 0-9, Symbols";
            example = "youtube.com/user/n6suion489, youtube.com/channel/obu4h3hj3kh3";
            maxChar = 40;

            return data.matches(regex);

        }

        if (option.equalsIgnoreCase("twitch")) {

            String regex = "twitch\\.tv/[\\w]{1,40}";

            allowedChars = "A-Z, a-z, 0-9, Symbols";
            example = "twitch.tv/beastgamingpro";
            maxChar = 40;

            return data.matches(regex);

        }

        return true;
    }

    /**
     * Called when the REGEX validation fails, or /pi set (option) is issued without any data to show the
     * user the max amount of characters allowed for that option
     * @return The integer value that is the max character limit
     */
    public int getMaxChar() {
        return maxChar;
    }

    /**
     * Called when the REGEX validation fails, or /pi set (option) is issued without any data to show the user
     * the allowed characters for that option
     * @return The string value that contains the characters allowed
     */
    public String getAllowedChars() {
        return allowedChars;
    }

    /**
     * Called when the REGEX validation fails, or /pi set (option) is issued without any data to show the user an
     * example of how to set the data they are trying to set
     * @return The string value tat contains an example of how the data should be set
     */
    public String getExample() {
        return example;
    }

}
