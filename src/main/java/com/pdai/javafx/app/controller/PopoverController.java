package com.pdai.javafx.app.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PopoverController implements Initializable {

    @FXML
    private JFXButton theme;

    @FXML
    public VBox options;

    public static PopoverController ctrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrl = this;
    }

}