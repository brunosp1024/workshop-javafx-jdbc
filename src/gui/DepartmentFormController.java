package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable{
	
	private Department entity;
	private DepartmentService service;
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private Label labelError;
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	public void onBtnSaveAction(ActionEvent event) {
		if (entity == null) { //Caso o programador esqueça de injetar o serviço, estoura uma exceção
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			utils.currentStage(event).close();
		}
		catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	//Responsável por pegar os dados na caixa de formulário e instanciar um departamento.
	//Retorna um departamento que esta associado com os campos do formulário.
	private Department getFormData() {
		Department obj = new Department();

		//o Id do form precisa ser convertido pra inteiro, pois o textField so recebe string.
		obj.setId(utils.tryParseToInt(txtId.getText())); //Id do Departamento recebe o Id do formulário.
		obj.setName(txtName.getText()); //Name do Departamento recebe o Name do formulário.

		return obj;
	}

	public void onBtnCancelAction(ActionEvent event) {
		utils.currentStage(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
		
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
