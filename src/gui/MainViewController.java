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
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) ->{
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	// Método usado para carrregar uma cena dentro da cena principal
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			// carrega os elementos do arquivo descrito como argumento (About).
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); 
																						
			VBox newVbox = loader.load(); // Carrega o fxml na variável VBox
			Scene mainScene = Main.getMainScene(); // Pega a cena principal do Main View.
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); // Pega o VBox da cena principal
			Node mainMenu = mainVbox.getChildren().get(0); // Pega o primeiro elemento do VBox da cena principal, o
															// MenuBar.
			mainVbox.getChildren().clear(); // Limpa todos os elementos da VBox principal
			mainVbox.getChildren().add(mainMenu); // Adiciona o menu da cena principal
			mainVbox.getChildren().addAll(newVbox.getChildren()); // Adiciona os elementos do VBox da cena About
			
			//Ativa a função que veio como argumento
			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR); // Exibe um alerta
																										// de erro na
																										// tela.
		}
	}

}
