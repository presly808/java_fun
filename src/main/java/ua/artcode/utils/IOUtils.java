package ua.artcode.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by serhii on 26.01.15.
 */
public final class IOUtils {

    private IOUtils() {
    }

    public static String getFile(String path){
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(path));
            while(sc.hasNextLine()){
                sb.append(sc.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getMessage(BufferedReader bf){
        StringBuilder sb = new StringBuilder();
        try {
            while(bf.ready()){
                sb.append(bf.readLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

}
