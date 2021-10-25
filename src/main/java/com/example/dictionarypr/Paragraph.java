package com.example.dictionarypr;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import animatefx.animation.*;
import animatefx.util.ParallelAnimationFX;
import animatefx.util.SequentialAnimationFX;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Setting implements Initializable {
    private final TextToText textToTextEngine = new TextToText();

    @FXML
    private JFXTextArea engText;
    @FXML
    private JFXTextArea viText;
    @FXML
    private JFXButton tranButton;

    @FXML
    protected void renderText(ActionEvent keyEvent) {
        String eng = engText.getText();
        String vi = textToTextEngine.translate(eng);
        new RotateIn(tranButton).play();  /** ???? Why it not work ??? **/
        viText.setText(vi);
    }
    @FXML
    protected void renderTextByEnter(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            String eng = engText.getText();
            String vi = textToTextEngine.translate(eng);
            viText.setText(vi);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viText.setEditable(false);
    }
}
