package com.example.dictionarypr;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

/**
 * @Tuan.
 * Tham khao tai day https://www.youtube.com/watch?v=Ln0BFlx6R4w
 * Thay doi text WOMEN <-> MEN (Do ko can thiep dc bang ScenceBuiler
 * */

public class Setting {
    @FXML
    private JFXSlider speedSlider;

    @FXML
    protected void action(MouseEvent mouseEvent) {
        int speed = (int) speedSlider.getValue();
        System.out.println(speed);
    }
}
