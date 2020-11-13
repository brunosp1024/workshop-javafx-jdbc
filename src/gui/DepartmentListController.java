package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable{
	
	@FXML
	private TableView<Department> tableViewDepartment;
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	@FXML
	private TableColumn<Department, Integer> tableColumnName;
	@FXML
	private Button btnNew;
	
	public void onBtnNewAction() {
		System.out.println("onBtnNewAction");
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();	
	}

	private void initializeNodes() {
		//iniciando o comportamento das celulas na tabela
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow(); //pega a referencia da janela da aplicação.
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); //Faz a tabela acompnhar as dimensões da janela.
	}

}
