package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.io.IOException;
import java.util.logging.Logger;
import java.net.URL;
import java.util.ResourceBundle;
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
	public void initialize(URL url, ResourceBundle rb) {
	}
	@FXML
	protected void logIn() throws IOException {
		UserDAO dummy=new UserDAO();
		if(dummy.logIn(this.userField.getText(),this.passwordField.getText())) {
			try {
				Parent modal=FXMLLoader.load(App.class.getResource("MainMenu.fxml"));
				Stage modalStage=new Stage();
				modalStage.setTitle("Main Window");
				modalStage.setResizable(false);
				modalStage.initOwner(App.rootstage);
				modalStage.setScene(new Scene(modal));
				Stage currentStage=(Stage) loginButton.getScene().getWindow();
				currentStage.close();
				modalStage.show();
			}catch (IOException ex){
				Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}else{
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error de identificación");
			alert.setContentText("Parámetros introducidos erróneos");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void switchToMainMenu() throws IOException {
		App.setRoot("MainMenu");
	}
}
