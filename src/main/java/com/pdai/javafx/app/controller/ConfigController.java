package com.pdai.javafx.app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.gn.decorator.GNDecorator;
import com.jfoenix.controls.JFXButton;
import com.pdai.javafx.app.fx.StageManager;
import com.pdai.javafx.app.utils.SpringUtils;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

@Component
public class ConfigController implements Initializable {
	StageManager stageManager;

    @FXML
    private JFXButton btn_theme;

    @FXML
    public VBox options;

    public static  ConfigController ctrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrl = this;
    }

    private boolean invert = false;
    @FXML
    private void altTheme() {
        invertTheme(!invert);
    }

    public void changeTheme(boolean dark) {
    	stageManager = SpringUtils.getBean(StageManager.class);
        String theme;
        String path = "/styles/theme/";
        if (dark) {
        	stageManager.getDecorator().initTheme(GNDecorator.Theme.DARKULA);
            theme = "dark.css";
        } else {
        	stageManager.getDecorator().initTheme(GNDecorator.Theme.DEFAULT);
            theme = "light.css";
        }

        ObservableList<String> stylesheets = stageManager.getDecorator().getStage().getScene().getStylesheets();
        stylesheets.clear();
        stylesheets.addAll(
                getClass().getResource(path + "fonts.css").toExternalForm(),
                getClass().getResource(path + "material-color.css").toExternalForm(),
                getClass().getResource(path + "skeleton.css").toExternalForm(),
                getClass().getResource(path + "" + theme).toExternalForm(),
                getClass().getResource(path + "bootstrap.css").toExternalForm(),
                getClass().getResource(path + "simple-green.css").toExternalForm(),
                getClass().getResource(path + "shape.css").toExternalForm(),
                getClass().getResource(path + "typographic.css").toExternalForm(),
                getClass().getResource(path + "helpers.css").toExternalForm(),
                getClass().getResource(path + "master.css").toExternalForm()
        );
    }
    
    public void invertTheme(boolean dark) {
    	
    	stageManager = SpringUtils.getBean(StageManager.class);
        String theme;
        String path = "/styles/theme/";
        if (dark) {
        	stageManager.getDecorator().initTheme(GNDecorator.Theme.DARKULA);
            theme = "dark.css";
            btn_theme.setText("Theme dark : actived");
            invert = true;
        } else {
        	stageManager.getDecorator().initTheme(GNDecorator.Theme.DEFAULT);
            theme = "light.css";
            btn_theme.setText("Theme dark : desactived");
            invert = false;
        }

        ObservableList<String> stylesheets = stageManager.getDecorator().getStage().getScene().getStylesheets();
        stylesheets.clear();
        stylesheets.addAll(
                getClass().getResource(path + "fonts.css").toExternalForm(),
                getClass().getResource(path + "material-color.css").toExternalForm(),
                getClass().getResource(path + "skeleton.css").toExternalForm(),
                getClass().getResource(path + "" + theme).toExternalForm(),
                getClass().getResource(path + "bootstrap.css").toExternalForm(),
                getClass().getResource(path + "simple-green.css").toExternalForm(),
                getClass().getResource(path + "shape.css").toExternalForm(),
                getClass().getResource(path + "typographic.css").toExternalForm(),
                getClass().getResource(path + "helpers.css").toExternalForm(),
                getClass().getResource(path + "master.css").toExternalForm()
        );

//        for (Node node : ViewManager.getInstance().getAll()) {
//            ((StackPane) node).getStylesheets().clear();
//            ((StackPane) node).getStylesheets().setAll(stylesheets);
//        }

        MainController.popConfig.hide();

        Platform.runLater(() -> {
//          force pop's transition

            MainController.popup.getRoot().getStylesheets().remove(MainController.popup.getRoot().getStylesheets().size() - 1);
            MainController.popConfig.getRoot().getStylesheets().remove(MainController.popConfig.getRoot().getStylesheets().size() - 1);

            MainController.popup.getRoot().getStylesheets().add(path + "pop" + theme);
            MainController.popConfig.getRoot().getStylesheets().add(path + "pop" + theme);
        });

    }
}
