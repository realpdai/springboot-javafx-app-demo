package com.pdai.javafx.app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.pdai.javafx.app.fx.FxmlView;
import com.pdai.javafx.app.fx.StageManager;
import com.pdai.javafx.app.utils.SpringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

@Component
public class MainController extends BaseController implements Initializable {

	@FXML
	public VBox sideBar;
	@FXML
	private VBox views;
	@FXML
	public ScrollPane body;
	@FXML
	public Label title;
	@FXML
	private ScrollPane scroll;

	@FXML
	private Button home;
	@FXML
	private Button about;
	@FXML
	private Button webview;
	@FXML
	private Button hamburger;

	@FXML
	private StackPane root;
	@FXML
	private JFXButton config;
	@FXML
	private VBox drawer;
	@FXML
	private JFXBadge messages;
	@FXML
	private JFXBadge notifications;
	@FXML
	private JFXBadge bg_info;

	public static final PopOver popConfig = new PopOver();
	public static final PopOver popup = new PopOver();

	private ObservableList<Button> items = FXCollections.observableArrayList();

	boolean scrolling = false;

	private Parent popContent;
	public static MainController ctrl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ctrl = this;
		loadContentPopup();

		populateItems();

		// about();

		try {
			addSubPop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void altLayout() {

		int minimum = 70;
		int max = 250;

		if (drawer.getPrefWidth() == max) {

			drawer.setPrefWidth(minimum);

			for (Node node : views.getChildren()) {
				if (node instanceof Button) {
					((Button) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					((Button) node).setAlignment(Pos.BASELINE_CENTER);
				} else if (node instanceof TitledPane) {
					((TitledPane) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					((TitledPane) node).setAlignment(Pos.BASELINE_CENTER);
					((TitledPane) node).setExpanded(false);
					((TitledPane) node).setCollapsible(false);
				} else {
					break;
				}
			}

			addEvents();
		} else {
			drawer.setPrefWidth(max);
			for (Node node : views.getChildren()) {
				if (node instanceof Button) {
					((Button) node).setContentDisplay(ContentDisplay.LEFT);
					((Button) node).setAlignment(Pos.BASELINE_LEFT);
				} else if (node instanceof TitledPane) {
					((TitledPane) node).setContentDisplay(ContentDisplay.RIGHT);
					((TitledPane) node).setAlignment(Pos.BASELINE_RIGHT);
					((TitledPane) node).setCollapsible(true);
				} else {
					break;
				}
			}
		}
	}

	private void addEvents() {
		VBox drawerContent;

		for (Node node : drawer.getChildren()) { // root
			if (node instanceof ScrollPane) {

				drawerContent = (VBox) ((ScrollPane) node).getContent();

				for (Node child : drawerContent.getChildren()) {
					if (child instanceof Button) {
						child.setOnMouseEntered(e -> {
							popup.setAutoHide(true);
							if (popup.isShowing())
								popup.hide();
						});
					}

					else if (child instanceof TitledPane) {
						addEvent(child);
					}
				}
			}

			else {
				// for others layouts
			}
		}
	}

	private void addSubPop() throws Exception {
		popup.setContentNode(FXMLLoader.load(getClass().getResource("/template/main/Popover.fxml")));

//        popup.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());

		popup.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
		popup.setArrowIndent(0);
		popup.setArrowSize(0);
		popup.setCornerRadius(0);
		popup.setAutoFix(true);
	}

	private void addEvent(Node node) {
		popup.setDetached(false);
		popup.setDetachable(false);
		popup.setCloseButtonEnabled(false);
		popup.setArrowSize(0);
		popup.setHeaderAlwaysVisible(false);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStylesheets().add(getClass().getResource("/styles/theme/custom-scroll.css").toExternalForm());

		VBox v = new VBox();
		v.setPrefWidth(200);

		TitledPane pane = (TitledPane) node;
		VBox vbox = (VBox) pane.getContent();

		for (Node btn : vbox.getChildren()) {
			String text = ((Button) btn).getText();
			Button button = new Button(text);
			button.setPrefWidth(v.getPrefWidth());
			button.setOnMouseClicked(e -> {
//                body.setContent(ViewManager.getInstance().get(button.getText().toLowerCase()));
//                title.setText(button.getText());
//                popup.hide();
			});
			button.setMinHeight(40);
			v.getChildren().add(button);
		}

//        Popover.ctrl.options.getChildren().clear();

		node.setOnMouseEntered((Event e) -> {
			if (drawer.getPrefWidth() == 70) {
				PopoverController.ctrl.options.getChildren().setAll(v);
				popup.show(pane, -1);
			}
		});
	}

	private void populateItems() {
		for (Node node : views.getChildren()) {
			if (node instanceof Button) {
				items.add((Button) node);
			}
		}
	}

	private void loadContentPopup() {
		try {
			popContent = FXMLLoader.load(getClass().getResource("/template/main/Config.fxml"));
			popConfig.getRoot().getStylesheets()
					.add(getClass().getResource("/styles/theme/poplight.css").toExternalForm());
			popConfig.setContentNode(popContent);
			popConfig.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
			popConfig.setArrowIndent(0);
			popConfig.setArrowSize(0);
			popConfig.setCornerRadius(0);
			popConfig.setAutoFix(true);
			popConfig.setAnimated(false);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void openConfig() {
		if (popConfig.isShowing()) {
			popConfig.hide();
		} else {
			popConfig.show(config, 0);
			popConfig.getRoot().setFocusTraversable(true);
		}
	}

	@FXML
	private void dashboard() {
		updateBody(FxmlView.MODULE_DASHBOARD);
	}

	private void updateBody(FxmlView view) {
		title.setText(view.title());
		SpringUtils.getBean(StageManager.class).switchContent(view, body);
	}

	@FXML
	private void jfxTextField() {
		// title.setText("JFXTextField");
		SpringUtils.getBean(StageManager.class).showPopWindow(FxmlView.MODULE_DASHBOARD);
		// body.setContent(ViewManager.getInstance().get("jfx-text-field"));
	}

	@FXML
	private void about() {
		updateBody(FxmlView.MODULE_PROFILE);
	}

	@FXML
	private void webview() {
		updateBody(FxmlView.MODULE_WEBVIEW);
		SpringUtils.getBean(ConfigController.class).changeTheme(false);
		SpringUtils.getBean(WebviewController.class).setUrl("https://www.baidu.com");
	}

	@FXML
	private void webview2() {
		updateBody(FxmlView.MODULE_WEBVIEW);
		SpringUtils.getBean(ConfigController.class).changeTheme(true);
		SpringUtils.getBean(WebviewController.class).setUrl("https://www.bing.com");
	}

	private PopOver pop = new PopOver();

	@FXML
	private void openMessages() {
		if (!pop.isShowing()) {

			Separator top = new Separator();
			Separator bottom = new Separator();

			Label message = new Label("Messages");
			Label count = new Label("4 News");
			count.getStyleClass().add("text-success");
			GridPane title = new GridPane();
			title.setMinHeight(40D);

			title.setAlignment(Pos.CENTER);
			title.add(message, 0, 0);
			title.add(count, 1, 0);
			GridPane.setHalignment(count, HPos.RIGHT);

			ListView<Node> listView = new ListView<>();

//            listView.getItems().addAll(list);
			listView.getStyleClass().add("border-0");

			Button btn = new Button("Read all messages");
			btn.getStyleClass().add("btn-flat");

			VBox root = new VBox(title, top, listView, bottom, btn);
			root.setAlignment(Pos.CENTER);
			root.setPrefSize(300, 300);
			title.setPrefWidth(root.getPrefWidth());
			count.setPrefWidth(root.getPrefWidth());
			message.setPrefWidth(root.getPrefWidth());
			count.setAlignment(Pos.CENTER_RIGHT);
			title.setPadding(new Insets(0, 25, 0, 25));
			btn.setPrefWidth(root.getPrefWidth());

//            listView.getStylesheets().add(getClass().getResource("/styles/theme/css/custom-scroll.css").toExternalForm());

			pop.getRoot().getStylesheets().add(getClass().getResource("/styles/theme/poplight.css").toExternalForm());
			pop.setContentNode(root);
			pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
			pop.setArrowIndent(0);
			pop.setArrowSize(0);
			pop.setCloseButtonEnabled(false);
			pop.setHeaderAlwaysVisible(false);
			pop.setCornerRadius(0);
			pop.setAutoFix(true);
			pop.show(messages);

		} else {
			pop.hide();
		}
	}

	@FXML
	private void openNotification() {
		if (!pop.isShowing()) {

			Separator top = new Separator();
			Separator bottom = new Separator();

			Label message = new Label("Messages");
			Label count = new Label("4 News");
			count.getStyleClass().add("text-success");
			GridPane title = new GridPane();
			title.setMinHeight(40D);

			title.setAlignment(Pos.CENTER);
			title.add(message, 0, 0);
			title.add(count, 1, 0);
			GridPane.setHalignment(count, HPos.RIGHT);

			ListView<Node> listView = new ListView<>();

//            listView.getItems().addAll(list);
			listView.getStyleClass().add("border-0");

			Button btn = new Button("Read all messages");
			btn.getStyleClass().add("btn-flat");

			VBox root = new VBox(title, top, listView, bottom, btn);
			root.setAlignment(Pos.CENTER);
			root.setPrefSize(300, 300);
			title.setPrefWidth(root.getPrefWidth());
			count.setPrefWidth(root.getPrefWidth());
			message.setPrefWidth(root.getPrefWidth());
			count.setAlignment(Pos.CENTER_RIGHT);
			title.setPadding(new Insets(0, 25, 0, 25));
			btn.setPrefWidth(root.getPrefWidth());

			listView.getStylesheets().add(getClass().getResource("/styles/theme/custom-scroll.css").toExternalForm());

			pop.getRoot().getStylesheets().add(getClass().getResource("/styles/theme/poplight.css").toExternalForm());
			pop.setContentNode(root);
			pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
			pop.setArrowIndent(0);
			pop.setArrowSize(0);
			pop.setCloseButtonEnabled(false);
			pop.setHeaderAlwaysVisible(false);
			pop.setCornerRadius(0);
			pop.show(notifications);

		} else {
			pop.hide();
		}
	}

}
