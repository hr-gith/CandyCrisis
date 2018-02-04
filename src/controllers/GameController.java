package controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Board;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by hamideh on 02/02/2018.
 */
public class GameController extends Application implements Initializable{
    private Board gameBoard;

    @FXML
    private GridPane gpBoard;
    @FXML
    private Label lblTile00;
    @FXML
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private Button btnLeft;
    @FXML
    private Button btnRight;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../views/Game.fxml"));
        Scene scene=new Scene(root,600,400);
        /*scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {
                    case UP:
                        System.out.println("HI");
                        break;
                    case RIGHT:
                        break;
                    case DOWN:
                        //circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA);
                        break;
                    case LEFT:
                        //circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA);
                        break;
                }
            }
        });*/
        primaryStage.setTitle("Candy Crisis");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTile00.setText("init");
    }

    @FXML
    public void OnButtonclicked(ActionEvent e){
        if (e.getSource().equals(btnUp)){
            System.out.println("up");
            lblTile00.setText("up");
        }else if (e.getSource().equals(btnDown)){
            System.out.println("Down");
            lblTile00.setText("Down");
        }else if (e.getSource().equals(btnLeft)){
            System.out.println("Left");
            lblTile00.setText("Left");
        }else{
            System.out.println("Right");
            lblTile00.setText("Right");
        }
    }

    public static void main(String[] args){
        launch(args);
    }

}
