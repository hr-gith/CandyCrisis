package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Timer;

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
        //System.out.println("fileops"+characters);
    return characters;
    }

    public static void writeFile(String characters, String nbMoves){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Configuration.outputFile));
            writer.write(characters);
            writer.newLine();
            writer.write(nbMoves);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
