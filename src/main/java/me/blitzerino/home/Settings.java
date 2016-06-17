package me.blitzerino.home;

/**
 * Created by Blitz on 6/14/2016.
 */
public class Settings {
    private static String name = "Vi";
    private static String cmdStart = "$";
    private static String msgStart = "Ӝ ";
    private static String game = "With JDA..";
    private static String adminMsg = "... You are not one of my admins, sorry!";
    public static String getMsgStart(){ return msgStart; }
    public static String getName(){
        return name;
    }
    public static String getCmdStart() {
        return cmdStart;
    }
    public static String getAdminMsg() {
        return adminMsg;
    }
    public static String getGame() {
        return game;
    }
}
