package me.blitzerino.home.misc;

import me.blitzerino.home.Settings;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.Role;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.utils.MiscUtil;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Blitz on 6/16/2016.
 */
public class Test  extends ListenerAdapter{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("test")) {
                if(args.length == 2){
                    List<User> myList = e.getJDA().getUsers().stream().filter(user -> user.getUsername().equalsIgnoreCase(args[1])).collect(Collectors.toList());
                    if(!myList.isEmpty()){
                        if(e.getGuild().getUsers().contains(myList.get(0))){
                            if(myList.size() < 2) {
                                User mentioned = myList.get(0);
                                MessageBuilder builder= new MessageBuilder();
                                String joindate = e.getGuild().getJoinDateForUser(mentioned).getMonthValue() + "-" + e.getGuild().getJoinDateForUser(mentioned).getDayOfMonth() + "-" + e.getGuild().getJoinDateForUser(mentioned).getYear() + " at " + e.getGuild().getJoinDateForUser(mentioned).getHour() + ":" + e.getGuild().getJoinDateForUser(mentioned).getMinute();
                                String dcjoindate =String.valueOf(MiscUtil.getCreationTime(mentioned.getId()).getMonthValue()) + "-" + String.valueOf(MiscUtil.getCreationTime(mentioned.getId()).getDayOfMonth()) + "-" + String.valueOf(MiscUtil.getCreationTime(mentioned.getId()).getYear());
                                builder.appendString("Here is all the information I could find on " + mentioned.getUsername() + ", " + e.getAuthor().getAsMention() + "!").appendString("\n");
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
                                MessageBuilder builder = new MessageBuilder();
                                builder.appendString("There was multiple users found " + e.getAuthor().getAsMention() + " be specific!").appendString("\n");
                                builder.appendString("```").appendString("\n");
                                for(int i = 0; i < myList.size(); i++) {
                                    builder.appendString(String.valueOf(myList.get(i).getAsMention() + "#" + myList.get(i).getDiscriminator())).appendString("\n");
                                }
                                builder.appendString("```");
                                e.getChannel().sendMessage(builder.build());
                            }
                        }
                    }else{
                        e.getChannel().sendMessage("User doesnt exist");
                    }
                }
            }
        }
    }
}
