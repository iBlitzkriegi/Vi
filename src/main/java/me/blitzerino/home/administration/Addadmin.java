package me.blitzerino.home.administration;

import me.blitzerino.home.Settings;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static me.blitzerino.home.Main.admins;

/**
 * Created by Blitz on 6/15/2016.
 */
public class Addadmin extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("addadmin")) {
                if (admins.contains(e.getAuthor().getId())) {
                    if (args.length == 2) {
                        if (e.getMessage().getMentionedUsers().size() == 1) {
                            User u = e.getMessage().getMentionedUsers().get(0);
                            if (!admins.contains(u.getId())) {
                                File file = new File("admins.txt");
                                FileWriter fw = null;
                                try {
                                    fw = new FileWriter(file, true);
                                } catch (IOException y) {
                                    y.printStackTrace();
                                }
                                BufferedWriter bw = new BufferedWriter(fw);
                                try {
                                    bw.write(u.getId());
                                } catch (IOException r) {
                                    r.printStackTrace();
                                }
                                try {
                                    bw.newLine();
                                } catch (IOException g) {
                                    g.printStackTrace();
                                }
                                try {
                                    bw.flush();
                                } catch (IOException b) {
                                    b.printStackTrace();
                                }
                                try {
                                    bw.close();
                                } catch (IOException p) {
                                    p.printStackTrace();
                                }
                                admins.add(u.getId());
                                e.getChannel().sendMessage(Settings.getMsgStart() + "Congratulations " + u.getAsMention() + "! You have been granted Administrator by " + e.getAuthor().getAsMention() + "! ");
                            } else {
                                e.getChannel().sendMessage("Woah there " + e.getAuthor().getAsMention() + "! " + u.getAsMention() + " is already a admin!");
                            }
                        } else {
                            e.getChannel().sendMessage(Settings.getMsgStart() + "Incorrect usage " + e.getAuthor().getAsMention() + ", Syntax: `" + Settings.getCmdStart() + "addadmin [$user$]`!");
                        }
                    }else{
                        e.getChannel().sendMessage(Settings.getMsgStart() + "Incorrect usage " + e.getAuthor().getAsMention() + ", Syntax: `" + Settings.getCmdStart() + "addadmin [$user$]`!");
                    }
                } else {
                    e.getChannel().sendMessage(Settings.getAdminMsg() + e.getAuthor().getAsMention() + Settings.getAdminMsg());
                }
            }
        }
    }
}
