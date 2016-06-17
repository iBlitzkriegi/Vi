package me.blitzerino.home.misc;

import me.blitzerino.home.Settings;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.OnlineStatus;
import net.dv8tion.jda.entities.Channel;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.entities.VoiceChannel;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.util.List;

/**
 * Created by Blitz on 6/16/2016.
 */
public class Serverinfo extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("serverinfo")) {
                int online = 0;
                int away = 0;
                int offline = 0;
                int bots = 0;
                for (User user : e.getGuild().getUsers()) {
                    if (user.getOnlineStatus().equals(OnlineStatus.ONLINE)) {
                        online++;
                    } else if (user.getOnlineStatus().equals(OnlineStatus.AWAY)) {
                        away++;
                    } else if (user.getOnlineStatus().equals(OnlineStatus.OFFLINE)) {
                        offline++;
                    }
                    if(user.isBot()){
                        bots++;
                    }
                }
                boolean rawr;
                if(e.getGuild().getUsers().size() >= 50){
                    rawr = true;
                }else{
                    rawr = false;
                }
                MessageBuilder builder = new MessageBuilder();
                builder.appendString(Settings.getMsgStart() + "Here is all the information I could find about this server " + u.getAsMention() + "!").appendString("\n");
                builder.appendString("```xl").appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Id: " + e.getGuild().getId()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Name: " + e.getGuild().getName()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Owner: " + e.getGuild().getOwner().getUsername() + "#" + e.getGuild().getOwner().getDiscriminator()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Ownerid: " + e.getGuild().getOwnerId()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Verification: " + e.getGuild().checkVerification()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Islarge: " + rawr).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Bots: " + bots).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "MemberCount: " + e.getGuild().getUsers().size()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "MembersOnline: " + online).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "MembersOffline: " + offline).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "MembersAway: " + away).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "AfkTimeout: " + e.getGuild().getAfkTimeout()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Region: " + e.getGuild().getRegion()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "TextChannels: ");
                    List<TextChannel> txtchannels = e.getGuild().getTextChannels();
                    for(int i = 0; i < txtchannels.size(); i++){
                        builder.appendString(txtchannels.get(i).getName());
                        if(i != txtchannels.size() - 1){
                            builder.appendString(", ");
                        }else{
                            builder.appendString(".");
                        }
                    }
                builder.appendString("\n").appendString(Settings.getMsgStart() + "VoiceChannels: ");
                List<VoiceChannel> vcchannels = e.getGuild().getVoiceChannels();
                for(int i = 0; i < vcchannels.size(); i++){
                    builder.appendString(vcchannels.get(i).getName().toLowerCase());
                    if(i != vcchannels.size() - 1){
                        builder.appendString(", ");
                    }else{
                        builder.appendString(".");
                    }
                }
                builder.appendString("\n");
                builder.appendString(Settings.getMsgStart() + "VerificationLevel: " + e.getGuild().getVerificationLevel()).appendString("\n");
                builder.appendString(Settings.getMsgStart() + "Icon: " + e.getGuild().getIconUrl()).appendString("\n");
                builder.appendString("```");
                e.getChannel().sendMessage(builder.build());
            }
        }
    }
}
