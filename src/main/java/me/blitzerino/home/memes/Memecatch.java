package me.blitzerino.home.memes;

import me.blitzerino.home.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Blitz on 6/14/2016.
 */
public class Memecatch {
    public static ArrayList<String> images = new ArrayList<>();
    public static HashMap<String, File> imageCache = new HashMap<>();
    public static HashMap<String, String> gifCache = new HashMap<>();
    public synchronized static void cacheImage(String url, String extension, String name){
        try {
            if(extension.equalsIgnoreCase("gif")){
                gifCache.put(name, url);
                return;
            }
            File imgf = new File(name + "." + extension);
            BufferedImage img = ImageIO.read(new URL(url));
            ImageIO.write(img, extension, imgf);
            imageCache.put(name, imgf);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void cacheImages(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("memes.txt")));
            String line = "";
            while((line=br.readLine()) != null) {
                String[] another = line.split("!");
                cacheImage(another[0], another[1], another[2]);
                Main.images.add(another[2] + "." + another[1]);
            }
            br.close();
        } catch (Exception e) {}
    }

}