package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class MainViewController implements Initializable{
	
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
		loadView2("/gui/DepartmentList.fxml");
	}
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	//M�todo usado para carrregar uma cena dentro da cena principal
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); //carrega os elementos do arquivo descrito como argumento (About).
			VBox newVbox = loader.load(); //Carrega o fxml na vari�vel VBox
			Scene mainScene = Main.getMainScene(); //Pega a cena principal do Main View.
			VBox mainVbox = (VBox)((ScrollPane) mainScene.getRoot()).getContent(); //Pega o VBox da cena principal
			Node mainMenu = mainVbox.getChildren().get(0); //Pega o primeiro elemento do VBox da cena principal, o MenuBar.
			mainVbox.getChildren().clear(); //Limpa todos os elementos da VBox principal
			mainVbox.getChildren().add(mainMenu); //Adiciona o menu da cena principal
			mainVbox.getChildren().addAll(newVbox.getChildren()); //Adiciona os elementos do VBox da cena About
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR); //Exibe um alerta de erro na tela.
		}
	}
	
	private synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); //carrega os elementos do arquivo descrito como argumento (About).
			VBox newVbox = loader.load(); //Carrega o fxml na vari�vel VBox
			Scene mainScene = Main.getMainScene(); //Pega a cena principal do Main View.
			VBox mainVbox = (VBox)((ScrollPane) mainScene.getRoot()).getContent(); //Pega o VBox da cena principal
			Node mainMenu = mainVbox.getChildren().get(0); //Pega o primeiro elemento do VBox da cena principal, o MenuBar.
			mainVbox.getChildren().clear(); //Limpa todos os elementos da VBox principal
			mainVbox.getChildren().add(mainMenu); //Adiciona o menu da cena principal
			mainVbox.getChildren().addAll(newVbox.getChildren()); //Adiciona os elementos do VBox da cena About
			
			DepartmentListController controller = loader.getController(); //Carrega o controller
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView(); //Exibe os dados na tableView.
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR); //Exibe um alerta de erro na tela.
		}
	}
	
	

}
