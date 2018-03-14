package models;



import javafx.geometry.Point2D;
import utilities.Configuration;
import utilities.FileOps;

import javax.xml.transform.sax.SAXSource;
import java.awt.*;
import java.io.File;
import java.sql.Array;
import java.util.*;

/**
 * Created by hamideh on 02/02/2018.
 */
public class Board implements Comparable{
    private int length;
    private int width;
    private Token emptyTokenRef;
    private Token[][] gridToken;
    private Board parent;
    private char directionFromParent;
    private static int nbMoves;

    public Board (){
        this.nbMoves = 0;
        this.parent = null;
        this.directionFromParent = 'X';
        this.gridToken=new Token[3][5];
        for(int i=0;i<Configuration.ROWS;i++) {
            for (int j = 0; j < Configuration.COLUMNS; j++) {
                this.gridToken[i][j] = new Token('X');
            }
        }
        this.emptyTokenRef = new Token(' ');
    }

    public void setBoard(String[] characters){

        //System.out.println("char array "+ Arrays.toString(characters));
        int indexCharac=0;
        for(int i=0;i<Configuration.ROWS;i++){
            for(int j=0;j<Configuration.COLUMNS;j++){
                        //characters.
                //System.out.println(characters[indexCharac].charAt(0));
                char sign = characters[indexCharac].charAt(0);
                if (sign != 'e') {
                    gridToken[i][j] = new Token(sign);
                }else{
                    gridToken[i][j] = new Token(' ');
                }
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

    public Board getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public char getDirectionFromParent() {
        return directionFromParent;
    }

    public void setDirectionFromParent(char directionFromParent) {
        this.directionFromParent = directionFromParent;
    }

    public boolean checkGoal() {
        boolean result = true;
        for (int i = 0; i < Configuration.COLUMNS; ++i) {
            if (this.gridToken[0][i].getSign() != this.gridToken[Configuration.ROWS - 1][i].getSign()) {
                result = false;
            }
        }
        if (result) {
            //write to the output file
            String characters = this.toString();
            String strNbMoves = ("Number of moves:" + nbMoves);
            FileOps.writeFile(characters, strNbMoves);
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
    // TO-DO: can return int for each error message
    public boolean move(char direction){
        boolean result = false;
        if (validateMove(direction)){
            Position oldEmptyPos=new Position(emptyTokenRef.getPos().getX(),emptyTokenRef.getPos().getY());
            //System.out.println("Old empty position :"+ oldEmptyPos);
            Position newEmptyPos=getNewPosition(direction);
            //System.out.println("new empty position :"+ newEmptyPos);
            //emptyTokenRef.setPos(newPos);
            //gridToken[][]
            gridToken[newEmptyPos.getX()][newEmptyPos.getY()].setPos(oldEmptyPos);
            gridToken[oldEmptyPos.getX()][oldEmptyPos.getY()].setPos(newEmptyPos);

            //Changing References in array[][]
            Token tempToken=gridToken[newEmptyPos.getX()][newEmptyPos.getY()];


            gridToken[newEmptyPos.getX()][newEmptyPos.getY()]=gridToken[oldEmptyPos.getX()][oldEmptyPos.getY()];
            gridToken[oldEmptyPos.getX()][oldEmptyPos.getY()]=tempToken;
            emptyTokenRef=gridToken[newEmptyPos.getX()][newEmptyPos.getY()];
            //System.out.println(newPos);
            nbMoves ++;
            result = true;
        }
        return result;
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
    /**
     * Manhattan distance
     *
    **/
    public int getHeuristic2(){
        int heuristicValue=0;
        for(int i=0;i<Configuration.COLUMNS;i++){
            System.out.println("********* Index " +i+"*********");
            int topMatchPosition=10;
            int bottomMatchPosition=10;

            if(this.gridToken[0][i].getSign()==this.gridToken[2][i].getSign())
                continue;
            else{
                for(int j=0;j<Configuration.COLUMNS;j++){
                    System.out.println("-----Checking "+j+"th Column---");
                    if(this.gridToken[0][i].getSign()==this.gridToken[1][j].getSign()){
                        //TO-DO: Make sure we choose position closest to j
                            int currentTopMatchPosition=topMatchPosition;

                            if(currentTopMatchPosition==10)
                                topMatchPosition=j;
                            else{
                                if(Math.abs(currentTopMatchPosition-i)<Math.abs(i-j)){
                                    topMatchPosition=j;
                                }
                            }
                                System.out.println("TOPMatch : "+topMatchPosition+"  J: "+j);
                    }
                    if(this.gridToken[2][i].getSign()==this.gridToken[1][j].getSign()){
                        //TO-DO: Make sure we choose position closest to j
                        int currentBottomMatchPosition=bottomMatchPosition;

                        if(currentBottomMatchPosition==10)
                            bottomMatchPosition=j;
                        else{
                            if(Math.abs(currentBottomMatchPosition-i)<Math.abs(i-j)){
                                bottomMatchPosition=j;
                            }
                        }
                        System.out.println("bottomMatchPosition : "+bottomMatchPosition+"  J: "+j);
                    }
                }
            }
            if(Math.abs(i-bottomMatchPosition)<Math.abs(i-topMatchPosition)){
                //Bottom is near to desired tile
                heuristicValue+=Math.abs(i-bottomMatchPosition);
            }else{
                heuristicValue+=Math.abs(i-topMatchPosition);
            }
        }

        return heuristicValue;
    }
    //*
    // This Function calculates the number of types of each tile in bottom an top row and then calculates the difference between them
    // eg:
    //  r e r r r
    //  r b w b b
    //  w y b r y
    // TOP:
    // count(r)=4
    //  count(e)=1
    //
    // Bottom:
    // count(w)=1
    // count(y)=2
    //  count(b)=1
    // // count(r)=1
    // h(n)= abs(count(r)[top]-count(r)[Bottom])
    // */


    public int getHeurictic(){
        
        HashMap<Character,Integer> topRow=new HashMap<Character, Integer>();
        HashMap<Character,Integer> bottomRow=new HashMap<Character, Integer>();

            for(int j=0;j<Configuration.COLUMNS;j++) {
                if(topRow.containsKey(this.gridToken[0][j].getSign())){
                    int newCount=topRow.get(this.gridToken[0][j].getSign())+1;
                    topRow.put(this.gridToken[0][j].getSign(),newCount);
                }else{
                    topRow.put(this.gridToken[0][j].getSign(),1);
                }
            }
        for(int j=0;j<Configuration.COLUMNS;j++) {
            if(bottomRow.containsKey(this.gridToken[Configuration.ROWS-1][j].getSign())){
                int newCount=bottomRow.get(this.gridToken[Configuration.ROWS-1][j].getSign())+1;
                bottomRow.put(this.gridToken[Configuration.ROWS-1][j].getSign(),newCount);
            }else{
                bottomRow.put(this.gridToken[Configuration.ROWS-1][j].getSign(),1);
            }
        }
        //ArrayList<Character> keys=new ArrayList<Character>();
        Set<Character> keys=new HashSet();
            //--Calculating Heuristic
        for ( Character key : topRow.keySet() ) {
            keys.add(key);
        }
        for ( Character key : bottomRow.keySet() ) {
            keys.add(key);
        }
        int heuristic=0;
        for(Character key:keys){
            int top=0,bottom=0;

            if(topRow.containsKey(key))
                top=topRow.get(key);
            if(bottomRow.containsKey(key))
                bottom=bottomRow.get(key);
            heuristic+=Math.abs(top-bottom);

        }
        System.out.println("----------------------");
        System.out.println("Keys : "+keys);
        System.out.println(topRow);
        System.out.println(bottomRow);
        System.out.println("Heuristic : "+heuristic);
                return 0;
    }
    public Board[] getSucessors(){
        //TO-DO: Make sucessors of given Model by checking if model exists
        //Board[] sucessors=new Board[4];
        ArrayList<Board> sucessors=new ArrayList<Board>();
        //Board currentBoard=this.clone();
/*
        for(int i=0;i<4;i++){
            Board sucessor=new Board();
            sucessor.copyEmptyRef(this.emptyTokenRef);
            sucessor.copyGrid(this.gridToken);
           // sucessors[i].length=this.length;
            //sucessors[i].width=this.width;
            System.out.println(sucessor.emptyTokenRef.getPos());
            sucessors.add(sucessor);
        }
        for(Board sucessor:sucessors){
            System.out.println(sucessor.emptyTokenRef.getPos());
        }
*/
        Board sucessor1=new Board();
        sucessor1.copyEmptyRef(this.emptyTokenRef);
        sucessor1.copyGrid(this.gridToken);
        sucessor1.parent = this;
        //System.out.println(sucessor1.emptyTokenRef.getPos());

        if(sucessor1.validateMove('L')){
            sucessor1.move('L');
            sucessor1.directionFromParent='L';

            //System.out.println("Left: "+sucessor1.emptyTokenRef.getPos());
            //System.out.println(sucessor1);
            sucessors.add(sucessor1);
        }
        else{
            sucessor1=null;
        }
        Board sucessor2=new Board();
        sucessor2.copyEmptyRef(this.emptyTokenRef);
        sucessor2.copyGrid(this.gridToken);
        sucessor2.parent = this;
        //System.out.println(sucessor2.emptyTokenRef.getPos());

        if(sucessor2.validateMove('R')){
            sucessor2.move('R');
            sucessor2.directionFromParent='R';
            //System.out.println("Right: "+sucessor2.emptyTokenRef.getPos());
            //System.out.println(sucessor2);
            sucessors.add(sucessor2);
        }
        else{
            sucessor2=null;
        }
        Board sucessor3=new Board();
        sucessor3.copyEmptyRef(this.emptyTokenRef);
        sucessor3.copyGrid(this.gridToken);
        sucessor3.parent = this;
        //System.out.println(sucessor3.emptyTokenRef.getPos());

        if(sucessor3.validateMove('D')){
            sucessor3.move('D');
            //System.out.println("Down: "+sucessor3.emptyTokenRef.getPos());
            //System.out.println(sucessor3);
            sucessor3.directionFromParent='D';
            sucessors.add(sucessor3);
        }
        else{
            sucessor3=null;
        }
        Board sucessor4=new Board();
        sucessor4.copyEmptyRef(this.emptyTokenRef);
        sucessor4.copyGrid(this.gridToken);
        sucessor4.parent = this;
        //System.out.println(sucessor4.emptyTokenRef.getPos());

        if(sucessor4.validateMove('U')){
            sucessor4.move('U');
            //System.out.println("Up: "+sucessor4.emptyTokenRef.getPos());
            //System.out.println(sucessor4);
            sucessor4.directionFromParent='U';
            sucessors.add(sucessor4);
        }
        else{
            sucessor4=null;
        }
        return sucessors.toArray(new Board[sucessors.size()]);
    }
    public void copyGrid(Token[][] gridToken){
        for(int i=0;i<Configuration.ROWS;i++){
            for(int j=0;j<Configuration.COLUMNS;j++){
                this.gridToken[i][j].setSign(gridToken[i][j].getSign());
                this.gridToken[i][j].getPos().setX(gridToken[i][j].getPos().getX());
                this.gridToken[i][j].getPos().setY(gridToken[i][j].getPos().getY());
            }
        }
    }
    public void copyEmptyRef(Token emptyTokenRef){
        /*Position ps=new Position(emptyTokenRef.getPos().getX(),emptyTokenRef.getPos().getY());
        //ps.setY(emptyTokenRef.getPos().getY());
        //ps.setX(emptyTokenRef.getPos().getX());
        this.emptyTokenRef.setPos(ps);*/
        this.emptyTokenRef.getPos().setX(emptyTokenRef.getPos().getX());
        this.emptyTokenRef.getPos().setY(emptyTokenRef.getPos().getY());
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Board) {
            Board other = (Board) obj;
            if (this.emptyTokenRef.getPos().getX() != other.emptyTokenRef.getPos().getX()) {
                return false;
            } else {
                if (this.emptyTokenRef.getPos().getY() != other.emptyTokenRef.getPos().getY()) {
                    return false;
                } else {
                    for (int i = 0; i < Configuration.ROWS; i++) {
                        for (int j = 0; j < Configuration.COLUMNS; j++) {
                            if (this.gridToken[i][j].getSign() != other.gridToken[i][j].getSign()) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof Board) {
            Board other = (Board) obj;
            return Integer.compare(this.getHeuristic2(), other.getHeuristic2());
        }
        else{
            throw new IllegalArgumentException("Incompatible Object: "+obj.toString());
        }
    }

    public void print(){
        for(int i=0;i<Configuration.ROWS;i++){
            for(int j=0;j<Configuration.COLUMNS;j++) {
                System.out.print("   "+ gridToken[i][j].getSign());//+" Position :"+gridToken[i][j].getPos().getX() +" , "+gridToken[i][j].getPos().getY());

            }
            System.out.println();
        }
    }

    public String toString(){
        String board = "";
        for(int i=0;i<Configuration.ROWS;i++){
            board+="\n";
            for(int j=0;j<Configuration.COLUMNS;j++) {
                if (gridToken[i][j].getSign() != ' ') {
                    board += Character.toString(gridToken[i][j].getSign());
                    board += " ";
                }else{
                    board += "e ";
                }

            }
        }
        return board;
    }

}
