package controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.BestFirstSearch;
import models.Board;
import models.GameLevel;
import models.GameLevels;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;


/**
 * Created by hamideh on 02/02/2018.
 */
public class GameController implements Initializable{


    private Board gameBoard;
    private boolean status;
    private GameLevels levels;
    private int currentLevel;

    @FXML
    private Label message;
    @FXML
    private Text tile00;
    @FXML
    private Text tile01;
    @FXML
    private Text tile02;
    @FXML
    private Text tile03;
    @FXML
    private Text tile04;
    @FXML
    private Text tile10;
    @FXML
    private Text tile11;
    @FXML
    private Text tile12;
    @FXML
    private Text tile13;
    @FXML
    private Text tile14;
    @FXML
    private Text tile20;
    @FXML
    private Text tile21;
    @FXML
    private Text tile22;
    @FXML
    private Text tile23;
    @FXML
    private Text tile24;


    @FXML
    private void onKeyPressed(KeyEvent event){
        if (!status) {
            String key = event.getCode().toString();
            boolean validMove = true;
            boolean validKey = true;

            if (key == "LEFT") {
                validMove = gameBoard.move('L');
            } else if (key == "RIGHT") {
                validMove = gameBoard.move('R');
            } else if (key == "UP") {
                validMove = gameBoard.move('U');
            } else if (key == "DOWN") {
                validMove = gameBoard.move('D');
            } else {
                validKey = false;
            }
            if (!validMove)
                message.setText("This is not a valid move");
            else if (!validKey)
                message.setText("Please use arrow keys to move");
            else
                message.setText("");

            if (gameBoard.checkGoal()) {
                message.setText("You Won!!!");
                status = true;
                this.levels.getListOfLevels().get(currentLevel).setStatus(true);
            }
            this.displayBoard();
        }
    }
    @FXML
    private void automaticMode(ActionEvent event){
        System.out.println("Automatic mode");
        /*Board[]sucessors=gameBoard.getSucessors();
        for(int i=0;i<sucessors.length;i++)
        {
            System.out.println("Board : "+sucessors[i].getEmptyTokenRef().getPos());
            //sucessors[i].heuricticFunction();
        }*/
        //TO-DO: do search
        BestFirstSearch bfs = new BestFirstSearch();
        bfs.search(this.gameBoard);
        String solution = bfs.getSolution();
        for(int i = 0; i<solution.length(); ++i){
            gameBoard.move(solution.charAt(i));
            System.out.println( gameBoard.toString());
        }
        displayBoard();

    }
    @FXML
    private void OnButtonClicked(ActionEvent event){
        if (!status) {
            String btnText = ((Button) event.getSource()).getText();
            boolean validMove = true;

            if (btnText.equals("LEFT")) {
                validMove = gameBoard.move('L');
            } else if (btnText.equals("RIGHT")) {
                validMove = gameBoard.move('R');
            } else if (btnText.equals("UP")) {
                validMove = gameBoard.move('U');
            } else if (btnText.equals("DOWN")) {
                validMove = gameBoard.move('D');
            }

            if (!validMove)
                message.setText("This is not a valid move.");
            else
                message.setText("");

            if (gameBoard.checkGoal()) {
                message.setText("You Won!!!");
                status = true;
                this.levels.getListOfLevels().get(currentLevel).setStatus(true);
            }
            this.displayBoard();
        }
    }

    @FXML
    private void nextLevelClicked(ActionEvent event){
        if (status==false){
            message.setText("Complete this level First!!");
            return;
        }
        currentLevel++;
        if(currentLevel<levels.getListOfLevels().size())
        {
            gameBoard.setBoard(levels.getListOfLevels().get(currentLevel).getCharacters());
            status = false;
            displayBoard();
        }else{
            message.setText("This was last Level!!");
        }
    }

    public void displayBoard(){
        /*for(int i = 0 ; i< Configuration.ROWS; ++i){
            for (int j = 0; j <Configuration.COLUMNS; ++j){

            }
        }*/
        tile00.setText(String.valueOf(gameBoard.getGridToken()[0][0].getSign()));
        tile01.setText(String.valueOf(gameBoard.getGridToken()[0][1].getSign()));
        tile02.setText(String.valueOf(gameBoard.getGridToken()[0][2].getSign()));
        tile03.setText(String.valueOf(gameBoard.getGridToken()[0][3].getSign()));
        tile04.setText(String.valueOf(gameBoard.getGridToken()[0][4].getSign()));
        tile10.setText(String.valueOf(gameBoard.getGridToken()[1][0].getSign()));
        tile11.setText(String.valueOf(gameBoard.getGridToken()[1][1].getSign()));
        tile12.setText(String.valueOf(gameBoard.getGridToken()[1][2].getSign()));
        tile13.setText(String.valueOf(gameBoard.getGridToken()[1][3].getSign()));
        tile14.setText(String.valueOf(gameBoard.getGridToken()[1][4].getSign()));
        tile20.setText(String.valueOf(gameBoard.getGridToken()[2][0].getSign()));
        tile21.setText(String.valueOf(gameBoard.getGridToken()[2][1].getSign()));
        tile22.setText(String.valueOf(gameBoard.getGridToken()[2][2].getSign()));
        tile23.setText(String.valueOf(gameBoard.getGridToken()[2][3].getSign()));
        tile24.setText(String.valueOf(gameBoard.getGridToken()[2][4].getSign()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        levels = new GameLevels();
        currentLevel = 0;
        gameBoard = new Board();
        gameBoard.setBoard(levels.getListOfLevels().get(currentLevel).getCharacters());
        status = false;
        displayBoard();
    }

}
