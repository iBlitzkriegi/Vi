package me.blitzerino.home.misc;

import me.blitzerino.home.Settings;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by Blitz on 6/16/2016.
 */
public class Botinfo extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            args[0] = args[0].replaceFirst(Settings.getCmdStart(), "");
            if(args[0].equalsIgnoreCase("botinfo")){
                MessageBuilder builder = new MessageBuilder();
                builder.appendString(Settings.getMsgStart() + "Here is all the information I can give about myself " + u.getAsMention() + "!").appendString("\n");
                builder.appendString("```xl").appendString("\n");
                builder.appendString(Settings.getMsgStart() + "ID: " + e.getJDA().getSelfInfo().getId()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Name: " + e.getJDA().getSelfInfo().getCurrentGame()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "MentionTag: " + e.getJDA().getSelfInfo().getAsMention()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Playing: " + e.getJDA().getSelfInfo().getCurrentGame()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "FriendTag: " + e.getJDA().getSelfInfo().getUsername() + "#" + e.getJDA().getSelfInfo().getDiscriminator()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Status: " + e.getJDA().getSelfInfo().getOnlineStatus()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Guilds: " + e.getJDA().getGuilds().size()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Avatar: " + e.getJDA().getSelfInfo().getAvatarUrl()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "IsBot: " + e.getJDA().getSelfInfo().isBot()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "IsVerified: " + e.getJDA().getSelfInfo().isVerified()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Server: https://discord.gg/013O8R5zHjlYNrF1S").appendString("\n");
                builder.appendString("```");
                e.getChannel().sendMessage(builder.build());
            }
        }
    }
}
