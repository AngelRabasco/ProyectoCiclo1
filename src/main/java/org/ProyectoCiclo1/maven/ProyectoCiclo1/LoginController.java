package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	
	@FXML
	public void initialize(URL url, ResourceBundle rb) {
	}
	@FXML
	protected void logIn() throws IOException {
		UserDAO dummy=new UserDAO();
		if(dummy.logIn(this.userField.getText(),this.passwordField.getText())) {
			switchToMainMenu();
		}
	}
	
	@FXML
	private void switchToMainMenu() throws IOException {
		App.setRoot("MainMenu");
	}
}
