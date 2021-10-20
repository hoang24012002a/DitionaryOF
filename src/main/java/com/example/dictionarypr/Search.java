package com.example.dictionarypr;
;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Search implements Initializable {
    private final TextToSpeech engineSpeech = new TextToSpeech();
    private final DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    private ArrayList<Word> engWordList;
    private double speed;
    private Word word;
    @FXML
    private JFXListView<String> listEnglish;
    @FXML
    private Label engWord;
    @FXML
    private Label viWord;
    @FXML
    private TextField searchText;
    @FXML
    private JFXToggleButton US_UKBtn;

    @FXML
    private JFXSlider speedSlider;

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
            listEnglish.setCellFactory(new Callback<ListView<String>,
                                               ListCell<String>>() {
                                           @Override
                                           public ListCell<String> call(ListView<String> list) {
                                               return new LabelCell();
                                           }
                                       }
            );
        } else {
            listEnglish.getItems().clear();
            listEnglish.setPlaceholder(new Label("null"));
        }
    }

    static class LabelCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Label label = new Label();
            if(empty || item == null) {
                setGraphic(null);
                setText(null);
            }
            else {
                label.setText(item);
                label.setFont(new Font("Cambria", 25));
                label.setPrefWidth(172);
                label.setPrefHeight(28);
                label.setCursor(Cursor.HAND);
                setGraphic(label);
            }
        }
    }
    @FXML
    protected void getEnglishWord(MouseEvent clicked) {
        if (engWordList != null) {
            int index = listEnglish.getSelectionModel().getSelectedIndex();
            word = engWordList.get(index);
            String engText = word.getWord_target();
            String viText = word.getWord_explain();
            engWord.setText(engText);
            viWord.setText(viText);
        }
    }

    @FXML
    protected void speakEnglishUs(ActionEvent clicked) {
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
    }
}
