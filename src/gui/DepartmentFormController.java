package gui;


import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {

	private Department entitie;
	
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
	
	@FXML
	public void onBtnSaveAction() {
		System.out.println("onBtnSaveAction");
	}
	
	@FXML
	public void onBtnCancelAction() {
		System.out.println("onBtnCancelAction");
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
