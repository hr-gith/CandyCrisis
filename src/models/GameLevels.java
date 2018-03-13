package models;

import utilities.FileOps;

import java.util.ArrayList;

public class GameLevels {
    private ArrayList<GameLevel>  listOfLevels;

    public ArrayList<GameLevel> getListOfLevels() {
        return listOfLevels;
    }

    private void setListOfLevels() {
        listOfLevels = new ArrayList<>();
        String[] characters;
        while((characters= FileOps.readFile()) != null){
            //TO-DO: get difficulty from file
            int dificulty = 1;
            listOfLevels.add(new GameLevel(characters,dificulty ));
        }
        this.listOfLevels = listOfLevels;
    }

    public GameLevels() {
        this.setListOfLevels();
    }
}
