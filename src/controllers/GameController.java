package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Board;
import utilities.FileOps;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by hamideh on 02/02/2018.
 */
public class GameController extends Application{

    @FXML
    private Label label1;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../views/Game.fxml"));
        Scene scene=new Scene(root,600,400);
        primaryStage.setTitle("Candy Crisis");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(Arrays.toString(FileOps.readFile()));
        Board b1=new Board();
        b1.print();
        //label1.setText("23");
        System.out.println("validate move: U "+b1.validateMove('U'));
        System.out.println("validate move: D "+b1.validateMove('D'));
        System.out.println("validate move: L "+b1.validateMove('L'));
        System.out.println("validate move: R "+b1.validateMove('R'));
    }


}
