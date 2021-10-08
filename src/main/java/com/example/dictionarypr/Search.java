package com.example.dictionarypr;
;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class Search {
    private final TextToSpeech engineSpeech = new TextToSpeech();
    private final DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    private ArrayList<Word> engWordList;
    private Word word;
    @FXML
    private Button speakBtn;
    @FXML
    private ListView<String> listEnglish;
    @FXML
    private Label engWord;
    @FXML
    private Text viWord;
    @FXML
    private TextField searchText;

    public Search() throws IOException {
    }

    @FXML
    protected void renderListWord(ActionEvent actionEvent) {
        String text = searchText.getText();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        engWordList = dictionaryCommandline.dictionarySearcher(text);
        if (!engWordList.isEmpty()) {
            for (Word value : engWordList) {
                observableList.add(value.getWord_target());
            }
        } else {
            System.out.printf("False");
        }
        listEnglish.setItems(observableList);
        listEnglish.setCellFactory(new Callback<ListView<String>,
                                            ListCell<String>>() {
                                @Override
                                public ListCell<String> call(ListView<String> list) {
                                    return new ColorRectCell();
                                }
                            }
        );
    }
    static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Label label = new Label();
            if (item != null) {
                label.setText(item);
                label.setFont(new Font("Cambria", 16));
                label.setPrefWidth(172);
                label.setPrefHeight(28);
                setGraphic(label);
            }
        }
    }
    @FXML
    protected void getEnglishWord(MouseEvent clicked) {
        int index = listEnglish.getSelectionModel().getSelectedIndex();
        word = engWordList.get(index);
        String engText = word.getWord_target();
        String viText = word.getWord_explain();
        engWord.setText(engText);
        viWord.setText(viText);
    }

    @FXML
    protected void speakEnglish(ActionEvent clicked) {
        String text = engWord.getText();
        engineSpeech.speak(text);
    }
}
