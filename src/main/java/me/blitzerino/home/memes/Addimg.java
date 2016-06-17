package me.blitzerino.home.memes;

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
 * Created by Blitz on 6/14/2016.
 */
public class Addimg extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getMessage().getContent().startsWith(Settings.getCmdStart())) {
            User u = e.getAuthor();
            String content = e.getMessage().getContent().substring(Settings.getCmdStart().length()).trim();
            String[] args = content.split("\\s+");
            if (args[0].equalsIgnoreCase("addimg")) {
                if (admins.contains(e.getAuthor().getId())) {
                    if (args.length > 1) {
                        if (args[1].contains("http")) {
                            if (args[2].contains("jpg") || args[2].contains("png")) {
                                e.getChannel().sendMessage(Settings.getMsgStart() + "Attempting to add new image..");
                                File f = new File("memes.txt");
                                BufferedWriter br = null;
                                try {
                                    br = new BufferedWriter(new FileWriter(f, true));
                                } catch (Exception r) {
                                    r.printStackTrace();
                                }
                                if (br != null) {
                                    try {
                                        br.write("\n" + args[1] + "!" + args[2] + "!" + args[3]);
                                    } catch (IOException g) {
                                        g.printStackTrace();
                                    }
                                    try {
                                        br.close();
                                        Memecatch.cacheImage(args[1], args[2], args[3]);
                                        Memecatch.images.add(args[3] + "." + args[2]);
                                        e.getChannel().sendMessage(Settings.getMsgStart() + "Image successfully added " + e.getAuthor().getAsMention() + "!");
                                    } catch (IOException p) {
                                        p.printStackTrace();
                                    }
                                }
                            } else {
                                e.getChannel().sendMessage("Incorrect usage " + e.getAuthor().getAsMention() + "! Syntax is: `" + Settings.getCmdStart() + "addimg %directLink% %ImageExtension% %ImgName%`");
                            }
                        } else {
                            e.getChannel().sendMessage("Incorrect usage " + e.getAuthor().getAsMention() + "! Syntax is: `" + Settings.getCmdStart() + "addimg %directLink% %ImageExtension% %ImgName%`");
                        }
                    } else {
                        e.getChannel().sendMessage("Incorrect usage " + e.getAuthor().getAsMention() + "! Syntax is: `" + Settings.getCmdStart() + "addimg %directLink% %ImageExtension% %ImgName%`");
                    }
                }else{
                    e.getChannel().sendMessage(Settings.getMsgStart() + e.getAuthor().getAsMention() + Settings.getAdminMsg());
                }
            }
        }
    }
}
