package com.pdai.javafx.app.fx;

import static com.sun.javafx.application.LauncherImpl.launchApplication;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.gn.decorator.GNDecorator;
import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * <b>ClassName</b>: AbstractFxApplication <br/>
 *
 * <b>Description</b>: AbstractFxApplication <br/>
 *
 * <b>Date</b>: Apr 22, 2019 12:20:32 PM <br/>
 * 
 * @author pdai
 * @version Apr 22, 2019
 *
 */
@SuppressWarnings("restriction")
public abstract class AbstractFxApplication extends Application {

	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractFxApplication.class);

	// spring context
	protected static ConfigurableApplicationContext applicationContext;

	// stage manager
	protected StageManager stageManager;

	// pre-load views
	protected static List<FxmlView> preloadViews;
	protected static FxmlView initView;

	// pre-load status
	private float progress = 0;

	/**
	 * 1. load spring application context;
	 * 2. launch FX application;
	 * 
	 * @param appClass
	 * @param args
	 */
	public static void run(final Class<? extends Application> appClass, final List<FxmlView> _preloadViews,
			final FxmlView _initView, final String[] args) {
		preloadViews = _preloadViews;
		initView = _initView;

		CompletableFuture.supplyAsync(() -> applicationContext = SpringApplication.run(appClass, args))
				.whenComplete((ctx, throwable) -> {
					if (throwable != null) {
						LOGGER.error("Failed to load spring application context: ", throwable);
					} else {
						launchApplication(appClass, FxAppPreloader.class, args);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#init()
	 */
	@Override
	public synchronized void init() {
		try {
			for (FxmlView view : preloadViews) {
				// load view
				FXMLLoader.load(getClass().getResource(view.fxml()));

				// update loader status
				notifyLoader();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized void notifyLoader() {
		progress += 100f / preloadViews.size();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primary) {
		GNDecorator decorator = new GNDecorator();
		stageManager = applicationContext.getBean(StageManager.class, primary, decorator);
		stageManager.switchScene(initView);
		stageManager.showDecorator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() throws Exception {
		super.stop();
		if (applicationContext != null) {
			applicationContext.close();
		}
	}

}
