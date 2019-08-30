package com.pdai.javafx.app.fx;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * <b>ClassName</b>: SpringFXMLLoader <br/>
 *
 * <b>Description</b>: Will load the FXML hierarchy as specified in the load
 * method and register Spring as the FXML Controller Factory. Allows Spring and
 * Java FX to coexist once the Spring Application context has been
 * bootstrapped.<br/>
 *
 * <b>Date</b>: Apr 22, 2019 1:11:58 PM <br/>
 * 
 * @author pdai
 * @version Apr 22, 2019
 *
 */
@Component
public class SpringFXMLLoader {
	private final ResourceBundle resourceBundle;
	private final ApplicationContext context;

	@Autowired
	public SpringFXMLLoader(ApplicationContext context, ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
		this.context = context;
	}

	public Parent load(String fxmlPath) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean); // Spring now FXML Controller Factory
		loader.setResources(resourceBundle);
		loader.setLocation(getClass().getResource(fxmlPath));
		return loader.load();
	}
}
