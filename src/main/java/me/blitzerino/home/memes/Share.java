package me.blitzerino.home.memes;

import me.blitzerino.home.Main;
import me.blitzerino.home.Settings;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;


/**
 * Created by Blitz on 6/15/2016.
 */
public class Share extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if(args[0].equalsIgnoreCase("share")) {
                if(args.length == 2){
                    e.getChannel().sendFile(Memecatch.imageCache.get(args[1]), null);
                }else{
                    MessageBuilder builder = new MessageBuilder();
                    builder.appendString("Not sure " + e.getAuthor().getAsMention() + "? Here is a list of my images!").appendString("\n").appendString("```xl").appendString("\n").appendString("{*}---=CurrentImages=---{*}").appendString("\n");
                    for(String s : Main.images){
                        if(s!=""){
                            builder.appendString(Settings.getMsgStart() + s).appendString("\n");
                        }
                    }
                    builder.appendString("```");
                    e.getChannel().sendMessage(builder.build());
                }
            }
        }
    }
}
