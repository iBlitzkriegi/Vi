package me.blitzerino.home;


import me.blitzerino.home.administration.Addadmin;
import me.blitzerino.home.administration.Restart;
import me.blitzerino.home.administration.Shutdown;
import me.blitzerino.home.memes.Addimg;
import me.blitzerino.home.memes.Memecatch;
import me.blitzerino.home.memes.Share;
import me.blitzerino.home.misc.*;
import me.blitzerino.home.registers.Setadmins;
import me.blitzerino.home.registers.Setclasses;
import me.blitzerino.home.registers.Setcmds;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.utils.AvatarUtil;
import net.dv8tion.jda.utils.SimpleLog;

import javax.imageio.ImageIO;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static ArrayList<String> commands = new ArrayList<>();
    public static ArrayList<String> classes = new ArrayList<>();
    public static ArrayList<String> images = new ArrayList<>();
    public static ArrayList<String> admins = new ArrayList<>();
    private static boolean shutdownNatural = false;
    public static boolean isShutdownNatural(){
        return shutdownNatural;
    }
    public static String token;
    public static void setShutdownNatural(boolean shutdownNatural){
        Main.shutdownNatural = shutdownNatural;
    }
    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        if(args.length != 2){
            printLn("You must include a token!");
            delay();
            setShutdownNatural(true);
            delay();
            System.exit(0);
        }
        token = args[0];
        JDA api = new JDABuilder().setBotToken(token)
                .addListener(new Rawr())
                .addListener(new Help())
                .addListener(new Addimg())
                .addListener(new Share())
                .addListener(new Addadmin())
                .addListener(new Shutdown())
                .addListener(new Serverinfo())
                .addListener(new Userinfo())
                .addListener(new Test())
                .addListener(new Restart())
                .addListener(new Joinserver())
                .addListener(new Botinfo())
                .buildBlocking();
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                try{
                    if(!isShutdownNatural()){
                        api.getAccountManager().setGame("Restarting!");
                        api.getAccountManager().setIdle(true);
                        api.shutdown();
                        Runtime.getRuntime().exec("./startvi.sh");

                    }else {
                        api.getAccountManager().setIdle(true);
                        System.out.println("GOING DOWN!");
                        api.shutdown();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Setadmins.setAdmins();
        Setcmds.setCommands();
        Setclasses.setClasses();
        api.getAccountManager().setIdle(true);
        Memecatch memecatch = new Memecatch();
        memecatch.cacheImages();
        parserPrint("Registering classes!");
        for(String b :Main.classes){
            parserPrint(b + ".java has been successfully parsed!");
            delay();
        }
        delay();
        infoPrint("Attempting to set name");
        delay();
        api.getAccountManager().setGame("Parsing classes..");
        if(api.getSelfInfo().getUsername().equals(Settings.getName())){
            infoPrint("Done! Name was already set!");
        }else{
            api.getAccountManager().setUsername(Settings.getName()).update();
            infoPrint("Done! Name is now " + Settings.getName());
        }
        infoPrint("Setting profile picture..");
        if(api.getSelfInfo().getAvatarUrl().equals("https://cdn.discordapp.com/avatars/175072259151626240/7f359a357d7b468210b426245b82fa6e.jpg")){
            infoPrint("Avatar was already set! Moving on..");
            delay();
        }else{
            api.getAccountManager().setAvatar(AvatarUtil.getAvatar(ImageIO.read(new File("pfp.png")))).update();
            infoPrint("Successfully updated Avatar! Moving on..");
            delay();
        }
        Main.delay();
        infoPrint("Setting game to " + Settings.getGame() + "..");
        Main.delay();
        api.getAccountManager().setGame(Settings.getGame());
        infoPrint("Done! Moving on...");
        delay();
        infoPrint("Counting up commands..");
        delay();
        infoPrint("Done! Found " + commands.size() + " commands. Moving on..");
        delay();
        infoPrint("Counting up classes...");
        delay();
        infoPrint("Done! Found " + classes.size() + " classes..");
        delay();
        infoPrint("Bot fully loaded! Type " + Settings.getCmdStart() + "help ingame to get started!");
        api.getAccountManager().setIdle(false);


    }
    public static void delay(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void infoPrint(String s){
        SimpleLog log = SimpleLog.getLog("Info");
        log.info(s);

    }
    public static void parserPrint(String s){
        SimpleLog log = SimpleLog.getLog("Parser");

        log.info(s);
    }
    public static void printLn(String s){
        System.out.println(s);
    }

}
