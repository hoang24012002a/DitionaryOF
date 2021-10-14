package com.example.dictionarypr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private TextField addWordEn;
    @FXML
    private TextField fixWord;
    @FXML
    private TextField deleteWord;
    @FXML
    private TextField meanViDanh;
    @FXML
    private TextField meanViDong;
    @FXML
    private TextField meanViTinh;
    @FXML
    private TextField meanViTrang;
    @FXML
    private VBox contentVbox;

    public About() throws IOException {
    }

    @FXML
    protected void addNewWord(ActionEvent clicked) {
        String engWord = addWordEn.getText();
        String viWord = meanViDanh.getText();
        if (!engWord.equals("") && !viWord.equals("")) {
            dictionaryCommandline.add(engWord, viWord);
            dictionaryCommandline.ExportToFile();
        }
        addWordEn.setText("");
        meanViDanh.setText("");
        System.out.print(engWord + "\n" + viWord);
        System.gc();
    }

    @FXML
    protected void fixWordEvent(ActionEvent clicked) {
        String engWord = fixWord.getText();
        String viWord = meanViDong.getText();
        if (!engWord.equals("") && !viWord.equals("")) {
            if (dictionaryCommandline.fix(engWord, viWord)) {
                dictionaryCommandline.ExportToFile();
            }
        }
        fixWord.setText("");
        meanViDong.setText("");
    }

    @FXML
    protected void deleteWordEvent(ActionEvent clicked) {
        String engWord = deleteWord.getText();
        if (!engWord.equals("")) {
            if (dictionaryCommandline.delete(engWord)) {
                dictionaryCommandline.ExportToFile();
            }
        }
        deleteWord.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Word> listWord = dictionaryCommandline.dictionarySearcher("");
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
        hBox.setLayoutX(10.0);
        hBox.setLayoutY(42.0);
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
        // create button speak;
        Button speakBtn = new Button();
        ImageView speakImage = new ImageView();
        speakImage.setFitWidth(24.0);
        speakImage.setFitHeight(24.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        path = new File("src/main/resources/com/example/dictionarypr/images/speaker_icon.png");
        speakImage.setImage(new Image(path.toURI().toString()));
        speakBtn.setGraphic(speakImage);
        speakBtn.setOnAction((new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                engineSpeech.speak(targetWord, "en-us", 0.75);
            }
        }));
        hBox.getChildren().add(speakBtn);
        HBox.setMargin(text, new Insets(2.0, 0, 0, 28.0));
        HBox.setMargin(speakBtn, new Insets(0, 0, 0,260.0));
        return hBox;
    }
}
