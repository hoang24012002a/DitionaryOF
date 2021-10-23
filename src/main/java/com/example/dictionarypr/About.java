package com.example.dictionarypr;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class About implements Initializable {
    private final DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    private TextToSpeech engineSpeech = new TextToSpeech();

    @FXML
    private VBox contentVbox;

    public About() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Word> listWord = dictionaryCommandline.myList();
        engineSpeech = new TextToSpeech();
        contentVbox.getChildren().removeAll();
        for (int i = 0; i < listWord.size(); i++) {
            HBox newHbox = initHBox(listWord.get(i).getWord_target());
            contentVbox.getChildren().add(newHbox);
        }
    }

    private HBox initHBox(String targetWord) {
        HBox hBox = new HBox();
        //create Hbox
        hBox.setPrefHeight(32.0);
        hBox.setPrefWidth(546.0);
        // create icon
        ImageView imageView = new ImageView();
        File path = new File("src/main/resources/com/example/dictionarypr/images/united-kingdom_icon.png");
        imageView.setImage(new Image(path.toURI().toString()));
        imageView.setFitHeight(32.0);
        imageView.setFitWidth(32.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        hBox.getChildren().add(imageView);
        // create target word
        Text text = new Text(targetWord);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setWrappingWidth(180.53669357299805);
        text.setFont(new Font("Regular", 21.0));
        hBox.getChildren().add(text);
        // create del btn
        JFXButton delBtn = new JFXButton();
        delBtn.setScaleX(0.9);
        delBtn.setScaleY(0.9);
        delBtn.setText("Del");
        delBtn.setStyle("-fx-background-radius: 20; -fx-border-color: red; -fx-border-radius: 20;");
        delBtn.setEllipsisString("");
        ImageView delImage = new ImageView();
        delImage.setFitWidth(24.0);
        delImage.setFitHeight(24.0);
        delImage.setPickOnBounds(true);
        delImage.setPreserveRatio(true);
        delImage.setScaleX(0.9);
        delImage.setScaleY(0.9);
        path = new File("src/main/resources/com/example/dictionarypr/images/bin_icon.png");
        delImage.setImage(new Image(path.toURI().toString()));
        delBtn.setGraphic(delImage);
        delBtn.setCursor(Cursor.HAND);
        delBtn.setOnMouseEntered((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent moved) {
                delBtn.setStyle("-fx-background-radius: 20; -fx-border-color: red; -fx-border-radius: 20; -fx-background-color: #FF0C3A;");
            }
        }));
        delBtn.setOnMouseExited((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent moved) {
                delBtn.setStyle("-fx-background-radius: 20; -fx-border-color: red; -fx-border-radius: 20; -fx-background-color: transparent;");
            }
        }));
        delBtn.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    Thread.sleep(500);
                    System.out.println(contentVbox.getChildren().indexOf(hBox));
                    dictionaryCommandline.delete(targetWord);
                    contentVbox.getChildren().remove(hBox);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
        // create button speak;
        JFXButton speakBtn = new JFXButton();
        speakBtn.setPrefHeight(32.0);
        speakBtn.setPrefWidth(126.0);
        speakBtn.setScaleX(0.9);
        speakBtn.setScaleY(0.9);
        speakBtn.setStyle("-fx-background-radius: 30; -fx-cursor: hand; -fx-border-color: purple; -fx-border-radius: 30;");
        speakBtn.setText("");
        ImageView speakImage = new ImageView();
        speakImage.setFitWidth(24.0);
        speakImage.setFitHeight(24.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        path = new File("src/main/resources/com/example/dictionarypr/images/play_32.png");
        speakImage.setImage(new Image(path.toURI().toString()));
        speakBtn.setGraphic(speakImage);
        speakBtn.setCursor(Cursor.HAND);
        speakBtn.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                engineSpeech.speak(targetWord, "en-us", 0.75);
            }
        }));
        hBox.getChildren().add(delBtn);
        hBox.getChildren().add(speakBtn);
        HBox.setMargin(text, new Insets(2.0, 0, 0, 28.0));
        HBox.setMargin(delBtn, new Insets(1, 0, 0, 94.0));
        HBox.setMargin(speakBtn, new Insets(0, 0, 0,3.0));

        return hBox;
    }
}
