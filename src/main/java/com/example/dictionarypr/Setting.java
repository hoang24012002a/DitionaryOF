package com.example.dictionarypr;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.input.InputEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * @Tuan.
 * Tham khao tai day https://www.youtube.com/watch?v=Ln0BFlx6R4w
 * Thay doi text WOMEN <-> MEN (Do ko can thiep dc bang ScenceBuiler
 * */

public class Setting {
    private TextToText textToTextEngine = new TextToText();

    @FXML
    private JFXTextArea engText;
    @FXML
    private JFXTextArea viText;

    @FXML
    protected void renderText(KeyEvent keyEvent) throws IOException {
        String eng = engText.getText();
        String vi = textToTextEngine.translate(eng);
        viText.setText(vi);
    }
}
