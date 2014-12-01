package ua.artcode.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by admin on 28.11.2014.
 */
public class FileUtils {

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

}
