package me.blitzerino.home.registers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static me.blitzerino.home.Main.admins;
import static me.blitzerino.home.Main.infoPrint;

/**
 * Created by Blitz on 6/15/2016.
 */
public class Setadmins {
    public static void setAdmins(){
        String line = null;
        String adminfName = "admins.txt";
        try {
            FileReader fileReader = new FileReader(adminfName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                admins.add(line);
                printAdmins(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + adminfName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + adminfName + "'");
        }
    }
    public static void printAdmins(String s){
        infoPrint("Attempting to add user with id: " + s + " to admin list!");

    }
}
