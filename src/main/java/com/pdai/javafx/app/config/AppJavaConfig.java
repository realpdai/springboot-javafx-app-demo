package com.pdai.javafx.app.config;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.gn.decorator.GNDecorator;
import com.pdai.javafx.app.fx.SpringFXMLLoader;
import com.pdai.javafx.app.fx.StageManager;
import com.pdai.javafx.app.utils.ExceptionWriter;

import javafx.stage.Stage;

/** 
* <b>ClassName</b>: AppJavaConfig <br/> 
*
* <b>Description</b>: AppJavaConfig <br/> 
*
* <b>Date</b>: Apr 22, 2019 1:13:50 PM <br/> 
* 
* @author pdai
* @version Apr 22, 2019
*
*/
@Configuration
public class AppJavaConfig {
	
    @Autowired 
    SpringFXMLLoader springFXMLLoader;

    /**
     * Useful when dumping stack trace to a string for logging.
     * @return ExceptionWriter contains logging utility methods
     */
    @Bean
    @Scope("prototype")
    public ExceptionWriter exceptionWriter() {
        return new ExceptionWriter(new StringWriter());
    }

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }
    
    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public StageManager stageManager(Stage stage, GNDecorator decorator) throws IOException {
        return new StageManager(springFXMLLoader, stage, decorator);
    }
    
}
