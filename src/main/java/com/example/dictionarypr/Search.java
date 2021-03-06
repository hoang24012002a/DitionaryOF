package com.example.dictionarypr;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import animatefx.animation.*;
import animatefx.util.ParallelAnimationFX;
import animatefx.util.SequentialAnimationFX;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Search implements Initializable {
    private final TextToSpeech engineSpeech = new TextToSpeech();
    private final DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    private ArrayList<Word> engWordList;
    private double speed;
    @FXML
    private ListView<String> listEnglish;
    @FXML
    private Label engWord;
    @FXML
    private JFXTextArea viWord;
    @FXML
    private TextField searchText;
    @FXML
    private JFXToggleButton US_UKBtn;
    @FXML
    private JFXButton markJFXButton;
    @FXML
    private JFXSlider speedSlider;
    @FXML
    private JFXButton speakJFXButton;

    @FXML
    protected void renderListWord(KeyEvent actionEvent) {
        String text = searchText.getText();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        if (!text.equals("")) {
            engWordList = dictionaryCommandline.MySQLCon(text);
        }
        if (engWordList != null) {
            for (Word value : engWordList) {
                observableList.add(value.getWord_target());
            }
            listEnglish.setItems(observableList);
            listEnglish.setCellFactory(list -> new LabelCell());
        } else {
            listEnglish.getItems().clear();
            listEnglish.setPlaceholder(new Label("null"));
        }
    }

    static class LabelCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if(empty || item == null) {
                setGraphic(null);
                setText(null);
            }
            else {
                HBox hBox = new HBox();
                hBox.setPrefWidth(184);
                hBox.setPrefHeight(29.0);
                Label label = new Label();
//                Font font = new Font("Noto Sans Mono", 12);
//                label.setFont(font);
                label.setStyle("-fx-font-weight: 900; -fx-font-size: 14;");
                label.setText(item);
                label.setPrefHeight(23.0);
                label.setPrefWidth(154.0);
                label.setMinWidth(154.0);
                ImageView imageView = new ImageView();
                imageView.setFitHeight(24.0);
                imageView.setFitWidth(24.0);
                imageView.setPickOnBounds(true);
                imageView.setPreserveRatio(true);
                imageView.setScaleY(0.9);
                imageView.setScaleX(0.9);
                File path = new File("src/main/resources/com/example/dictionarypr/images/checked_icon.png");
                imageView.setImage(new Image(path.toURI().toString()));
                hBox.getChildren().addAll(label, imageView);
                HBox.setMargin(label, new Insets(2.5, 0, 0, 2.0));
                HBox.setMargin(imageView, new Insets(2.5, 0, 0, 6.0));
                hBox.setStyle("-fx-background-radius: 20 4 20 4; -fx-border-radius: 20 4 20 4; -fx-border-color: #66ff66");
                setGraphic(hBox);
            }
        }
    }
    @FXML
    protected void getEnglishWord(MouseEvent clicked) {
        if (engWordList != null) {
            int index = listEnglish.getSelectionModel().getSelectedIndex();
            Word word = engWordList.get(index);
            String engText = word.getWord_target();
            String viText = word.getWord_explain();
            String pronun = word.pronunciationOfWord(word);
            speakJFXButton.setText(pronun);
            new BounceIn(engWord).play();
            engWord.setText(engText);
            viWord.setText(viText);
        }
    }

    @FXML
    protected void speakEnglishUs(ActionEvent clicked) {
        new Wobble(speakJFXButton).setResetOnFinished(true).play();
        String text = engWord.getText();
        String languageSpeech = US_UKBtn.getText();
        if (languageSpeech.equals("US")) {
            engineSpeech.speak(text, "en-us", speed);
        } else {
            engineSpeech.speak(text, "en-uk", speed);
        }
    }

    @FXML
    protected void action(MouseEvent clicked) {
        int value = (int) speedSlider.getValue();
        speed = (double) value / 100;
        System.out.println(speed);
    }

    @FXML
    protected void addNewWord(ActionEvent clicked) {
        new Pulse(markJFXButton).play();
        String engWordAdd = engWord.getText();
        String viWordAdd = viWord.getText();
        viWordAdd = viWordAdd.replaceAll("\n", "<br>");
        if (!engWordAdd.equals("") && !viWordAdd.equals("")) {
            dictionaryCommandline.add(engWordAdd, viWordAdd);
        }
        System.out.print(engWordAdd + "\n" + viWordAdd);
    }

    public Search() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        US_UKBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (US_UKBtn.isSelected()) {
                    US_UKBtn.setText("UK");
                } else {
                    US_UKBtn.setText("US");
                }
            }
        });
        viWord.setEditable(false);
    }
}
