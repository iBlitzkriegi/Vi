package me.blitzerino.home.administration;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.entities.Channel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import static me.blitzerino.home.Main.admins;
import static me.blitzerino.home.Main.setShutdownNatural;

/**
 * Created by Blitz on 6/15/2016.
 */
public class Shutdown extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getMessage().getContent().startsWith(Settings.getCmdStart())){
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if(args[0].equalsIgnoreCase("shutdown")) {
                if(admins.contains(u.getId())){
                    e.getChannel().sendMessage(Settings.getMsgStart() + "If...If you say so " + u.getAsMention() + ".. " + Settings.getName() + " shutting down.");
                    Main.delay();
                    setShutdownNatural(true);
                    Main.delay();
                    System.exit(0);
                }else{
                    e.getChannel().sendMessage(Settings.getMsgStart() + e.getAuthor().getAsMention() + Settings.getAdminMsg());
                }
            }
        }

    }
}
