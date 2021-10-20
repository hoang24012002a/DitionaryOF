package com.example.dictionarypr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.bootstrapfx.BootstrapFX;

public class DashBoard extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(root);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("https://cdn-icons.flaticon.com/png/512/3285/premium/3285819.png?token=exp=1634718480~hmac=069ef30b987b478f1d2f5fa317d1ea5d"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
