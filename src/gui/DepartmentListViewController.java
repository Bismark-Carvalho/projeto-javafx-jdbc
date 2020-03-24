package gui;

import static application.Main.getMainScene;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

public class DepartmentListViewController implements Initializable {

	private DepartmentService departmentService;
	
	@FXML
	private Button btnNew;
	
	@FXML
	private TableView<Department> tableViewDepartments;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtnNewAction() {
		System.out.println("teste btn new");
	}
	
	
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}


	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		Stage stage = (Stage) getMainScene().getWindow();
		tableViewDepartments.prefHeightProperty().bind(stage.heightProperty());
		
	}
	
	
	public void updateTableView() {
		if (departmentService == null) {
			throw new IllegalStateException("Service was null");
		}
		
		List<Department> list = departmentService.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartments.setItems(obsList);
		
	}

	
	
}
