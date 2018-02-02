package models;



import javafx.geometry.Point2D;

import java.awt.*;

/**
 * Created by hamideh on 02/02/2018.
 */
public class Board {
    private int length;
    private int width;
    private Token emptyTokenRef;
    private Token[][] gridToken;


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

    public void displayBoard(){

    }

    public boolean checkGoal(){
        boolean result = false;

        return result;
    }

    public Position findNewCoordinates(char direction){
        Position newPos = new Position(emptyTokenRef.getPos().getX(),emptyTokenRef.getPos().getY());

        return newPos;
    }

    public boolean validateMove(char direction){
        boolean result = false;

        return result;
    }

    public boolean setBoard(){
        boolean result = false;

        return result;
    }


    // TO-DO: can return int for each error message
    public boolean move(char direction){
        boolean result = false;

        return result;
    }

}
