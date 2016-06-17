package me.blitzerino.home.misc;

import me.blitzerino.home.Settings;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.entities.VoiceChannel;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.utils.MiscUtil;

import java.util.List;

/**
 * Created by Blitz on 6/16/2016.
 */
public class Userinfo extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getMessage().getContent().startsWith(Settings.getCmdStart())){
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("getinfo")) {
                if(args.length >= 2) {
                    if (e.getMessage().getMentionedUsers().size() == 1) {
                        User mentioned = e.getMessage().getMentionedUsers().get(0);
                        String joindate = e.getGuild().getJoinDateForUser(mentioned).getMonthValue() + "-" + e.getGuild().getJoinDateForUser(mentioned).getDayOfMonth() + "-" + e.getGuild().getJoinDateForUser(mentioned).getYear() + " at " + e.getGuild().getJoinDateForUser(mentioned).getHour() + ":" + e.getGuild().getJoinDateForUser(mentioned).getMinute();
                        String dcjoindate =String.valueOf(MiscUtil.getCreationTime(mentioned.getId()).getMonthValue()) + "-" + String.valueOf(MiscUtil.getCreationTime(mentioned.getId()).getDayOfMonth()) + "-" + String.valueOf(MiscUtil.getCreationTime(mentioned.getId()).getYear());
                        MessageBuilder builder = new MessageBuilder();
                        builder.appendString("Here is all the information I could find on " + mentioned.getAsMention() + ", " + e.getAuthor().getAsMention() + "!").appendString("\n");
                        builder.appendString("```xl").appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "Id: " + mentioned.getId()).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "Name: " + mentioned.getUsername()).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "JoinedDiscord: " + dcjoindate).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "Nickname: " + e.getGuild().getNicknameForUser(mentioned)).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "Status: " + mentioned.getOnlineStatus()).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "FriendTag: " + mentioned.getUsername() + "#" + mentioned.getDiscriminator()).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "Playing: " + mentioned.getCurrentGame()).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "AvatarURL: " + mentioned.getAvatarUrl()).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "IsBot: " + mentioned.isBot()).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "Hashcode: " + mentioned.hashCode()).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "JoinedGuild: " + joindate).appendString("\n");
                        builder.appendString(Settings.getMsgStart() + "Roles: ");
                        List<Role> roles = e.getGuild().getRolesForUser(mentioned);
                        for(int i = 0; i < roles.size(); i++){
                            builder.appendString(roles.get(i).getName().toLowerCase());
                            if(i != roles.size() - 1){
                                builder.appendString(", ");
                            }else{
                                builder.appendString(".");
                            }
                        }
                        builder.appendString("\n");
                        builder.appendString("```");
                        e.getChannel().sendMessage(builder.build());
                    }else{
                        e.getChannel().sendMessage("tr2");
                    }
                }else{
                    e.getChannel().sendMessage("tr1");
                }
            }
        }
    }
}
