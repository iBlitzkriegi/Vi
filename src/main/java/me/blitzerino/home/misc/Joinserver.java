package me.blitzerino.home.misc;

import me.blitzerino.home.Settings;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by Blitz on 6/16/2016.
 */
public class Joinserver extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("joinguild")) {
                e.getChannel().sendMessage("To let me into your guild " + u.getAsMention() + " you must have a moderator click this link: https://discordapp.com/oauth2/authorize?client_id=175072248430854144&scope=bot&permissions=66186303");
            }
        }
    }
}
