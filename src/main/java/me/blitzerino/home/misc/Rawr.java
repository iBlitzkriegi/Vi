package me.blitzerino.home.misc;
import me.blitzerino.home.Settings;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by Blitz on 6/14/2016.
 */
public class Rawr extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String msg = e.getMessage().getContent();
        if(msg.startsWith(Settings.getCmdStart() + "rawr")){
            e.getChannel().sendMessage("hi");
        }
    }



}
