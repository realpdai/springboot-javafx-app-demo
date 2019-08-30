package com.pdai.javafx.app.fx;

import java.io.IOException;

import com.jfoenix.controls.JFXProgressBar;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FxAppPreloader extends Preloader {

	private JFXProgressBar progressBar;
	private Parent view;
	private Stage stage;
	private Label label;

	@Override
	public void init() {
		try {
			view = FXMLLoader.load(getClass().getResource("/template/loader/loader.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primary) {
		stage = primary;
		primary.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(view);
		scene.setFill(Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("/styles/theme/fonts.css").toExternalForm());
		progressBar = (JFXProgressBar) scene.lookup("#progressBar");
		label = (Label) scene.lookup("#progressLabel");
		primary.getIcons().add(new Image("/icons/icon.png"));
		primary.setScene(scene);
        primary.setAlwaysOnTop(true);
		primary.show();

	}

	@Override
	public synchronized void handleApplicationNotification(Preloader.PreloaderNotification info) {
		if (info instanceof Preloader.ProgressNotification) {
			double x = ((Preloader.ProgressNotification) info).getProgress();

			double percent = x / 100f;
			progressBar.progressProperty().set(percent > 1 ? 1 : percent);
		}
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification info) {
		try {
			StateChangeNotification.Type type = info.getType();
			switch (type) {
			case BEFORE_LOAD:
				label.textProperty().set("初始化成功...");
				Thread.sleep(2000);
				break;
			case BEFORE_INIT:
				label.textProperty().set("正在加载模块...");
				Thread.sleep(1000);
				break;
			case BEFORE_START:
				label.textProperty().set("加载成功，即将跳转到主页面");
				Thread.sleep(1000);
				stage.close();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
