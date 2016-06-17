package me.blitzerino.home.administration;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.home.Main.admins;
import static me.blitzerino.home.Main.setShutdownNatural;

/**
 * Created by Blitz on 6/16/2016.
 */
public class Restart extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("restart")) {
                if (admins.contains(u.getId())) {
                    e.getChannel().sendMessage(Settings.getMsgStart() + "If...If you say so " + u.getAsMention() + ".. " + Settings.getName() + " restarting!");
                    Main.delay();
                    setShutdownNatural(false);
                    Main.delay();
                    System.exit(0);
                }
            }
        }
    }
}
