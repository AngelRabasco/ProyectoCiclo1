package org.ProyectoCiclo1.maven.ProyectoCiclo1.Controller;

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
		//Carga las variables de la asignatura en los campos
		addOrEdit=mode;
		user=owner;
		subjectID=subject.getID();
		nameField.setText(subject.getName());
	}
	
	@FXML
	private void save() {
	//Guarda la asignatura en la base de datos
		if(addOrEdit==false) {
		//Entra por este IF si estamos cargando el editor con el botón "Añadir"
			if(nameField.getText().trim()!="") {
				SubjectDAO subject=new SubjectDAO(null,
						nameField.getText(),
						user);
				if(SubjectDAO.checkExists(subject.getName().toString(), user)==false) {
					//Si la asignatura no existe la guarda y muestra una notificación
					subject.save();
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Asignatura registrada");
					alert.setContentText("La asignatura ha sido registrada con exito");
					alert.showAndWait();
					closeWindow();
				}else{
					//Si existe muestra una advertencia
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error de registro");
					alert.setContentText("Esta asignatura ya existe, cambie el nombre");
					alert.showAndWait();
				}
			}else{
				//Si el campo del nombre está vacio muestra una advertencia
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error de registro");
				alert.setContentText("Introduzca un nombre");
				alert.showAndWait();
			}
		}else{
			//Entra por este ELSE si estamos cargando el editor con el botón "Editar"
			if(nameField.getText().trim()!="") {
				SubjectDAO subject=new SubjectDAO(subjectID,
						nameField.getText(),
						user);
				if(SubjectDAO.checkExists(subject.getName().toString(), user)==false) {
					//Si existe no la guarda
					subject.save();
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Entrada actualizada");
					alert.setContentText("La entrada ha sido actualizada con exito");
					alert.showAndWait();
					closeWindow();
				}else{
					//Si no existe la guarda
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error de actualización");
					alert.setContentText("Esta asignatura ya existe, cambie el nombre");
					alert.showAndWait();
				}
			}else{
				//Si el campo del nombre está vacio muestra una advertencia
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
		//Cierra la ventana
		Stage currentStage=(Stage) saveButton.getScene().getWindow();
		currentStage.close();
	}
}
