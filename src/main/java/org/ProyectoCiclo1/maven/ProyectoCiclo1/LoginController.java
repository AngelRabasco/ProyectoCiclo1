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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
				FXMLLoader loader=new FXMLLoader(App.class.getResource("MainMenu.fxml"));
				Parent modal=loader.load();
				Stage modalStage=new Stage();
				modalStage.initModality(Modality.APPLICATION_MODAL);
				modalStage.initOwner(App.rootstage);
				modalStage.setScene(new Scene(modal));
				Stage currentStage=(Stage) loginButton.getScene().getWindow();
				currentStage.close();
				modalStage.showAndWait();
			}catch (IOException ex){
				Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	@FXML
	private void switchToMainMenu() throws IOException {
		App.setRoot("MainMenu");
	}
}
