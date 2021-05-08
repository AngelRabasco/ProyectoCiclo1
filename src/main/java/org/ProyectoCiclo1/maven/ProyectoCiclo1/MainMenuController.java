package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.User;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuController {
	
	@FXML
	private Label labelID;
	@FXML
	private Label labelName;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView<User> tv1;
	@FXML
	private TableColumn<User, Integer> tc1;
	@FXML
	private TableColumn<User, String> tc2;
	@FXML
	private Button buttonLoad;
	
	@FXML
	public void initialize() {
		System.out.println("caca");
//		tc1.setCellValueFactory(new PropertyValueFactory<User, Integer>("ID"));
//		tc2.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
//		new UserDAO();
//		ObservableList<User> items = FXCollections.observableArrayList(UserDAO.searchByID(11));
//		tv1.setItems(items);
	}
	@FXML
	private void loadUserInfo() {
		UserDAO user=new UserDAO(recieveUserData());
		labelID.setText(user.getID().toString());
		labelName.setText(user.getName().toString());
	}
	@FXML
	private UserDAO recieveUserData() {
		Stage stage=(Stage) labelID.getScene().getWindow();
		return (UserDAO)stage.getUserData();
	}
}
