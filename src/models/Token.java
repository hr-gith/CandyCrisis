package models;

/**
 * Created by hamideh on 02/02/2018.
 */
public class Token {
    private char sign;
    private Position pos;
	private char name;


    @Override
    public String toString() {
        return "Token{" +
                "sign=" + sign +
                ", pos=" + pos +
                '}';
    }

    public Token(char sign, char name){
        this.sign=sign;
		this.name = name;
        this.pos = new Position(0,0);
    }
    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
	
	public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public void moveUp (){
        this.pos.moveUp();
    }

    public void moveDown (){
        this.pos.moveDown();
    }
    public void moveRight (){
        this.pos.moveRight();
    }
    public void moveLeft (){
        this.pos.moveLeft();
    }
}
