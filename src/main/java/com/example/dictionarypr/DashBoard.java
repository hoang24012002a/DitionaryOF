package com.example.dictionarypr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DashBoard extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
    primaryStage.setTitle("Dictionary App");
    Scene scene = new Scene(root);
    //primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/app_icon.png")));
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
