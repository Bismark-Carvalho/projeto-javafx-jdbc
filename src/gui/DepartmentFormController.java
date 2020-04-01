package gui;


import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.db.DbException;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	private Department entitie;
	
	private DepartmentService service;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label txtMsgErrorName;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnCancel;
	
	
	public void setDepartment(Department entitie) {
		this.entitie = entitie;
	}
	
	
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}



	@FXML
	public void onBtnSaveAction(ActionEvent event) {
		if (entitie == null) {
			throw new IllegalStateException("Entitie was null");
		} if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		try {
			entitie = getFormData();
			service.saveOrUpdate(entitie);
			Utils.currentStage(event).close();
			
		} catch (DbException e) {
			Alerts.showAlerts("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Department getFormData() {
		Department dep = new Department();
		dep.setId(Utils.tryParseToInt(txtId.getText()));
		dep.setName(txtName.getText());
		return dep;
	}



	@FXML
	public void onBtnCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNodes();
			
	}
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void updateFormData() {
		if ( entitie == null) {
			throw new IllegalStateException("Entitie was null");
		}
		txtId.setText(String.valueOf(entitie.getId()));
		txtName.setText(entitie.getName());
	}

}
