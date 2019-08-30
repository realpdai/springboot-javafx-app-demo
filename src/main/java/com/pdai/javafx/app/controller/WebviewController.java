package com.pdai.javafx.app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

@Component
public class WebviewController implements Initializable {

	@FXML
	private WebView webView;

	@FXML
	private ProgressIndicator progressIndicator;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// setUrl("https://www.baidu.com");
		WebEngine webEngine = webView.getEngine();
		webEngine.setOnResized((WebEvent<Rectangle2D> event) -> {
			// To change body of generated methods, choose Tools | Templates.
			System.out.println("Window resized");
		});

		// 加载指示器
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				if ("SUCCEEDED".equals(newValue.toString())) {
//	                	webView.setVisible(true);
					progressIndicator.setVisible(false);
				} else {
					progressIndicator.setVisible(true);
				}
			}
		});
	}

	@FXML
	public void clickPageABtn(Event event) throws IOException {
		setUrl("https://www.bing.com");
	}

	@FXML
	public void clickPageBBtn(Event event) throws IOException {
		setUrl("https://www.baidu.com");
	}

	public void setUrl(String url) {
		// show process indicator
//		webView.setVisible(false);
		progressIndicator.setVisible(true);

		// load html page
		WebEngine webEngine = webView.getEngine();
		webEngine.load(url);

	}

}
