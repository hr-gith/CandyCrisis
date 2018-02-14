package models;



import javafx.geometry.Point2D;
import utilities.Configuration;
import utilities.FileOps;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

/**
 * Created by hamideh on 02/02/2018.
 */
public class Board {
    private int length;
    private int width;
    private Token emptyTokenRef;
    private Token[][] gridToken;

    public Board(){
        gridToken=new Token[3][5];
        String[] characters= FileOps.readFile();
        System.out.println("char array "+ Arrays.toString(characters));
        int indexCharac=0;
        for(int i=0;i<Configuration.ROWS;i++){
            for(int j=0;j<Configuration.COLUMNS;j++){
                        //characters.
                System.out.println(characters[indexCharac].charAt(0));
                gridToken[i][j]=new Token(characters[indexCharac].charAt(0));
                Position tempPos=new Position(i,j);
                gridToken[i][j].setPos(tempPos);
                if (characters[indexCharac].charAt(0)=='e')
                    emptyTokenRef=  gridToken[i][j];

                indexCharac++;

            }
        }
    }
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Token getEmptyTokenRef() {
        return emptyTokenRef;
    }

    public void setEmptyTokenRef(Token emptyTokenRef) {
        this.emptyTokenRef = emptyTokenRef;
    }

    public Token[][] getGridToken() {
        return gridToken;
    }

    public void setGridToken(Token[][] gridToken) {
        this.gridToken = gridToken;
    }


    public boolean checkGoal(){
        boolean result = true;
        for(int i = 0; i < Configuration.COLUMNS; ++i){
            if (this.gridToken[0][i].getSign() != this.gridToken[Configuration.ROWS-1][i].getSign()){
                result = false;
            }
        }
        return result;
    }

    public boolean validateMove(char direction){
        boolean result = true;
        int currentX = emptyTokenRef.getPos().getX();
        int currentY = emptyTokenRef.getPos().getY();
        switch (direction)
        {
            case 'U':
                if(currentX==0)
                    return false;
                break;
            case 'D':
                if(currentX== Configuration.ROWS-1)
                    return false;

                break;
            case 'L':
                if(currentY==0)
                    return false;

                break;
            case 'R':
                if(currentY==Configuration.COLUMNS-1)
                    return false;

                break;

        }
        return result;
    }


    public void print(){
        for(int i=0;i<Configuration.ROWS;i++){
            for(int j=0;j<Configuration.COLUMNS;j++) {
                System.out.print("   "+ gridToken[i][j].getSign());//+" Position :"+gridToken[i][j].getPos().getX() +" , "+gridToken[i][j].getPos().getY());

            }
            System.out.println();
        }
    }
    public Position getNewPosition(char direction){
        Position pos=new Position(emptyTokenRef.getPos().getX(),emptyTokenRef.getPos().getY());
        switch (direction){
        case 'U':
            pos.setX(pos.getX()-1);
            break;
        case 'D':
            pos.setX(pos.getX()+1);
            break;
        case 'L':
            pos.setY(pos.getY()-1);
            break;
        case 'R':
            pos.setY(pos.getY()+1);
            break;

        }
        return pos;
    }

    // TO-DO: can return int for each error message
    public boolean move(char direction){
        boolean result = false;
        if (validateMove(direction)){
            Position oldEmptyPos=new Position(emptyTokenRef.getPos().getX(),emptyTokenRef.getPos().getY());
            System.out.println("Old empty position :"+ oldEmptyPos);
            Position newEmptyPos=getNewPosition(direction);
            System.out.println("new empty position :"+ newEmptyPos);
            //emptyTokenRef.setPos(newPos);
            //gridToken[][]
            gridToken[newEmptyPos.getX()][newEmptyPos.getY()].setPos(oldEmptyPos);
            gridToken[oldEmptyPos.getX()][oldEmptyPos.getY()].setPos(newEmptyPos);

            //Changing References in array[][]
            Token tempTOken=gridToken[newEmptyPos.getX()][newEmptyPos.getY()];


            gridToken[newEmptyPos.getX()][newEmptyPos.getY()]=gridToken[oldEmptyPos.getX()][oldEmptyPos.getY()];
            gridToken[oldEmptyPos.getX()][oldEmptyPos.getY()]=tempTOken;
            emptyTokenRef=gridToken[newEmptyPos.getX()][newEmptyPos.getY()];
            //System.out.println(newPos);
            result = true;
        }
        return result;
    }

}
