package me.blitzerino.home.registers;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;

/**
 * Created by Blitz on 6/14/2016.
 */
public class Setcmds {
    public static void setCommands(){
        addCmd("~ <Misc> ~ ~");
        addCmd("help [%user%](Get the help menu, can take a user)");
        addCmd("rawr [null](Babies first cmd ._.)");
        addCmd("serverinfo [null](Get info about your current guild)");
        addCmd("getinfo [%user%](Get information about a user)");
        addCmd("joinguild [null](Get my invite link)");
        addCmd("botinfo [null](Get information about me)");
        addCmd("~ <Administration> ~ ~");
        addCmd("addadmin [%user%](Add a user as a administrator for " + Settings.getName() + ")");
        addCmd("addimg [%ImgUrl%] [%Extension%] [%RefName%](Add a Meme to the bot)");
        addCmd("share [%ImgRefName%](Share a meme by it's reference name)");
        addCmd("shutdown [null](Shut the bot down..Obviously..)");
        addCmd("restart [null](Restart the bot..Obviously..)");
        addCmd("<If a command has [null] in it, it means there is no arguments for it. Also, all commands start with " + Settings.getCmdStart() + ". So type " + Settings.getCmdStart() + "command to execute a command!>");
    }
    private static void addCmd(String s){
        Main.commands.add(Settings.getMsgStart() + "~ " + s);
    }
}
