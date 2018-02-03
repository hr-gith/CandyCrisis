package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hamideh on 02/02/2018.
 */
public class GameController extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../views/Game.fxml"));
        Scene scene=new Scene(root,600,400);
        primaryStage.setTitle("Candy Crisis");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
