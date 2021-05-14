package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.time.LocalDateTime;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Entry;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.EntryDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.SubjectDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EntryEditorController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField descriptionField;
	@FXML
	private ListView<Subject> subjectList;
	@FXML
	private Button saveEntryButton;
	@FXML
	private Button cancelButton;
	
	Boolean addOrEdit;
	UserDAO user;
	Integer entryID;
	Subject entrySubject;
	
	@FXML
	public void initialize() {
	}
	
	@FXML
	public void setFields(Entry entry, UserDAO owner, Boolean mode) {
		addOrEdit=mode;
		user=owner;
		entryID=entry.getID();
		entrySubject=entry.getSubject();
		nameField.setText(entry.getName());
		descriptionField.setText(entry.getDescription());
		subjectList.setItems(SubjectDAO.searchByOwner(user));
	}
	
	@FXML
	private void save() {
		if(addOrEdit==false) {
			if(nameField.getText().trim()!="") {
				if(subjectList.getSelectionModel().getSelectedItem()!=null) {
					new EntryDAO(null,
							nameField.getText(),
							descriptionField.getText(),
							subjectList.getSelectionModel().getSelectedItem(),
							LocalDateTime.now(),
							LocalDateTime.now()).save();
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Entrada registrada");
					alert.setContentText("La entrada ha sido registrada con exito");
					alert.showAndWait();
					closeWindow();
				}else{
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error de registro");
					alert.setContentText("Introduzca una asignatura");
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
				if(subjectList.getSelectionModel().getSelectedItem()==null) {
					new EntryDAO(entryID,
							nameField.getText(),
							descriptionField.getText(),
							entrySubject,
							LocalDateTime.now(),
							LocalDateTime.now()).save();	
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Entrada actualizada");
					alert.setContentText("La entrada ha sido actualizada con exito");
					alert.showAndWait();
					closeWindow();
				}else{
					new EntryDAO(entryID,
							nameField.getText(),
							descriptionField.getText(),
							subjectList.getSelectionModel().getSelectedItem(),
							LocalDateTime.now(),
							LocalDateTime.now()).save();
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
		Stage currentStage=(Stage) saveEntryButton.getScene().getWindow();
		currentStage.close();
	}
}
