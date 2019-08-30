package com.pdai.javafx.app.fx;

import java.util.ResourceBundle;

public enum FxmlView {
    MAIN {
        @Override
		public String title() {
            return getStringFromResourceBundle("app.title");
        }

        @Override
		public String fxml() {
            return "/template/main/main.fxml";
        }

    }, 
    MODULE_DASHBOARD {
        @Override
		public String title() {
            return getStringFromResourceBundle("module.dashboard.title");
        }

        @Override
		public String fxml() {
            return "/template/module/dashboard.fxml";
        }

    },
    MODULE_PROFILE {
        @Override
		public String title() {
            return getStringFromResourceBundle("module.profile.title");
        }

        @Override
		public String fxml() {
            return "/template/module/profile.fxml";
        }

    },
    MODULE_WEBVIEW {
        @Override
		public String title() {
            return getStringFromResourceBundle("module.webview.title");
        }

        @Override
		public String fxml() {
            return "/template/module/webview.fxml";
        }

    };
	
    
    public abstract String title();
    public abstract String fxml();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
