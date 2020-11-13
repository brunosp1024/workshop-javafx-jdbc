package gui;

import java.net.URL;
import java.nio.channels.IllegalSelectorException;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{
	
	private DepartmentService service; 
	
	@FXML
	private TableView<Department> tableViewDepartment;
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	@FXML
	private TableColumn<Department, Integer> tableColumnName;
	@FXML
	private Button btnNew;
	
	private ObservableList<Department> obsList;
	
	public void onBtnNewAction() {
		System.out.println("onBtnNewAction");
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
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
	
	public void updateTableView() {
		if(service == null) { //Caso o programador esqueça de passar o serviço, extoura uma exceção;
			throw new IllegalSelectorException();
		}
		List<Department> list = service.findAll(); //Carrega o dados Mockados na classe DepartmentService.
		obsList = FXCollections.observableArrayList(list); // Joga a lista para o objeto ObservableList.
		tableViewDepartment.setItems(obsList); //Atualiza a tableView com os dados.
	}

}
