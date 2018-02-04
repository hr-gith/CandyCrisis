package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class FileOps {
    public static String[] readFile(){
        String characters[]=null;
        try {
            BufferedReader reader=new BufferedReader(new FileReader(Configuration.inputFile));
            String readLine="";
            if ((readLine = reader.readLine()) != null) {
                characters=readLine.split(" ");
                //characters.replaceAll("\\s","");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("fileops"+characters);
    return characters;
    }
}
