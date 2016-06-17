package me.blitzerino.home.misc;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.JDAInfo;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by Blitz on 6/14/2016.
 */
public class Help extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("help")) {
                MessageBuilder builder = new MessageBuilder();
                builder.appendString("```md").appendString("\n");
                Main.commands.stream().filter(b -> b != "").forEach(b -> builder.appendString(b).appendString("\n"));
                builder.appendString("```");
                u.getPrivateChannel().sendMessage(builder.build());
                e.getChannel().sendMessage("I have PM'd you my commands list " + u.getAsMention() + "!");
            }
        }
    }
}
