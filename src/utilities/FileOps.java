package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;

public class FileOps {
    public static ArrayList<String> readFile(){
        ArrayList<String> fileConfigs=new ArrayList<String>();
        try {
            BufferedReader reader=new BufferedReader(new FileReader(Configuration.inputFile));
            String readLine="";
            while ((readLine = reader.readLine()) != null) {
                fileConfigs.add(readLine);
                //characters=readLine.split(" ");
                //characters.replaceAll("\\s","");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println("fileops"+characters);
    return fileConfigs;
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
