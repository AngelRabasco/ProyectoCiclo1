package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.io.IOException;
import java.util.logging.Logger;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.logging.Level;

public class LoginController {
	
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Button signupButton;
	
	@FXML
	public void initialize() {
	}
	
	@FXML
	protected void logIn() throws IOException {
		if(new UserDAO().logIn(this.userField.getText(),this.passwordField.getText())) {
			loadMainMenu(new UserDAO(UserDAO.searchByName(this.userField.getText()).get(0)));
		}else{
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error de identificación");
			alert.setContentText("Parámetros introducidos erróneos");
			alert.showAndWait();
		}
	}
	@FXML
	protected void signUp() throws IOException {
		if(new UserDAO().signUp(this.userField.getText(), this.passwordField.getText())==false) {
			logIn();
		}else{
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error de registro");
			alert.setContentText("Este nobre ya está en uso");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void loadMainMenu(UserDAO user) throws IOException {
		try {	
			FXMLLoader loader=new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			Parent parent=loader.load();
			MainMenuController mainMenuController=loader.getController();
			mainMenuController.loadUserInfo(user);
			mainMenuController.loadSubjects();
			Stage stage = new Stage();
			stage.setScene(new Scene(parent));
			stage.setTitle("Main window");
			stage.setResizable(false);
			Stage currentStage=(Stage) loginButton.getScene().getWindow();
			currentStage.close();
			stage.show();
		}catch (IOException ex){
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	@FXML
	private void switchToMainMenu() throws IOException {
		App.setRoot("MainMenu");
	}
}
