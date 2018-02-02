package models;

/**
 * Created by hamideh on 02/02/2018.
 */
public class Token {
    private char sign;
    private int xPos;
    private int yPos;

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void moveUp (){
        this.yPos--;
    }

    public void moveDown (){
        this.yPos++;
    }
    public void moveRight (){
        this.xPos++;
    }
    public void moveLeft (){
        this.xPos--;
    }
}
