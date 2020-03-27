package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItenSellerAction() {
		System.out.println("onMenuItenSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentListView.fxml", (DepartmentListViewController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});

	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/AboutView.fxml", x -> {});
	}

	private synchronized <T> void loadView(String absolutename, Consumer<T> InitializedAction ) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutename));
			VBox newvbox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(newvbox.getChildren());
			
			T controller = loader.getController();
			InitializedAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlerts("Io Exception", null, e.getMessage(), AlertType.ERROR);
		}
	}



	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}
