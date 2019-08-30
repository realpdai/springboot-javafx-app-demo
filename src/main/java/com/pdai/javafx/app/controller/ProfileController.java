package com.pdai.javafx.app.controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

@Component
public class ProfileController extends BaseController implements Initializable {

    @FXML private Label note;
    @FXML private Rating rating;
    @FXML private Label fullName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        note.textProperty().bind(rating.ratingProperty().asString(Locale.ENGLISH, "%.2f"));
		//TODO fullName.textProperty().bind(/* App.getUserDetail().textProperty() */);
    }

}
