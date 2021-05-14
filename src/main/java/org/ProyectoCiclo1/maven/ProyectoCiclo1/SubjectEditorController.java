package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.SubjectDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SubjectEditorController {
	
	@FXML
	private TextField nameField;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;
	
	Boolean addOrEdit;
	UserDAO user;
	Integer subjectID;
	
	@FXML
	public void initialize() {
	}
	
	@FXML
	public void setFields(Subject subject, UserDAO owner, Boolean mode) {
		addOrEdit=mode;
		user=owner;
		subjectID=subject.getID();
		nameField.setText(subject.getName());
	}
	
	@FXML
	private void save() {
		if(addOrEdit==false) {
			if(nameField.getText().trim()!="") {
				SubjectDAO subject=new SubjectDAO(null,
						nameField.getText(),
						user);
				if(SubjectDAO.checkExists(subject.getName().toString(), user)==false) {
					subject.save();
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Asignatura registrada");
					alert.setContentText("La asignatura ha sido registrada con exito");
					alert.showAndWait();
					closeWindow();
				}else{
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error de registro");
					alert.setContentText("Esta asignatura ya existe, cambie el nombre");
					alert.showAndWait();
				}
			}else{
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error de registro");
				alert.setContentText("Introduzca un nombre");
				alert.showAndWait();
			}
		}else{
			if(nameField.getText().trim()!="") {
				SubjectDAO subject=new SubjectDAO(subjectID,
						nameField.getText(),
						user);
				if(SubjectDAO.checkExists(subject.getName().toString(), user)==false) {
					subject.save();
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Entrada actualizada");
					alert.setContentText("La entrada ha sido actualizada con exito");
					alert.showAndWait();
					closeWindow();
				}else{
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error de actualización");
					alert.setContentText("Esta asignatura ya existe, cambie el nombre");
					alert.showAndWait();
				}
			}else{
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error de actualización");
				alert.setContentText("Introduzca un nombre");
				alert.showAndWait();
			}
		}
	}
	
	@FXML
	private void closeWindow() {
		Stage currentStage=(Stage) saveButton.getScene().getWindow();
		currentStage.close();
	}
}
