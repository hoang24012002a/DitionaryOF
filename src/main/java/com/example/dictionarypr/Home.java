package com.example.dictionarypr;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class Home implements Initializable {

    @FXML
    private JFXTextArea thankText;

    @FXML
    protected void setLinkGit(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/hoang24012002a/DitionaryOF"));
    }

    public Home() throws IOException{}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        thankText.setEditable(false);
    }
}
