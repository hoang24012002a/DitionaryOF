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

import java.util.ArrayList;

public class Search {
    private TextToSpeech engineSpeech = new TextToSpeech();
    //private ArrayList<Word> engWordList;
    //private Word word;
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

    @FXML
    protected void renderListWord(ActionEvent actionEvent) {
        String text = searchText.getText();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll("Tuan", "Hoang", "Truong", "Thang", "Tu", "Toan", "Huyen", "Khang");
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
        String engText = "tu tieng anh o vi tri index = " + (index);
        String viText = "tu tieng viet o vi tri index = " + (index);
        engWord.setText(engText);
        viWord.setText(viText);
    }

    @FXML
    protected void speakEnglish(ActionEvent clicked) {
        String text = engWord.getText();
        engineSpeech.speak(text);
    }
}
