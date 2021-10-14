package com.example.dictionarypr;
;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class Search {
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
    private Text viWord;
    @FXML
    private TextField searchText;

    @FXML
    private JFXSlider speedSlider;

    @FXML
    protected void renderListWord(KeyEvent actionEvent) {
        String text = searchText.getText();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        engWordList = dictionaryCommandline.dictionarySearcher(text);
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
                label.setFont(new Font("Cambria", 16));
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
        engineSpeech.speak(text, "en-us", speed);
    }
//    @FXML
//    protected void speakEnglishUk(ActionEvent clicked) {
//        String text = engWord.getText();
//        engineSpeech.speak(text, "en-uk", speed);
//    }

//    @FXML
//    protected void action(MouseEvent mouseEvent) {
//        int value = (int) speedSlider.getValue();
//        speed = (double) value / 100;
//        System.out.println(speed);
//    }

    @FXML
    protected void addNewWord(ActionEvent clicked) {
        String engWordAdd = engWord.getText();
        String viWordAdd = viWord.getText();
        if (!engWordAdd.equals("") && !viWordAdd.equals("")) {
            dictionaryCommandline.add(engWordAdd, viWordAdd);
            dictionaryCommandline.ExportToFile();
        }
        System.out.print(engWordAdd + "\n" + viWordAdd);
    }
    public Search() throws IOException {
    }
}
