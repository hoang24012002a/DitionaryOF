package com.example.dictionarypr;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Setting {
    private final TextToText textToTextEngine = new TextToText();

    @FXML
    private JFXTextArea engText;
    @FXML
    private JFXTextArea viText;

    @FXML
    protected void renderText(ActionEvent keyEvent) {
        String eng = engText.getText();
        String vi = textToTextEngine.translate(eng);
        viText.setText(vi);

    }
}
