package com.example.dictionarypr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Home {
    @FXML
    protected void setLinkGit(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/hoang24012002a/DitionaryOF"));
    }
}
