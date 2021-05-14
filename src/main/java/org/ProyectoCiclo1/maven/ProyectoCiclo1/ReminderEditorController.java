package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.time.LocalDateTime;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Reminder;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.ReminderDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.SubjectDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReminderEditorController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField descriptionField;
	@FXML
	private ListView<Subject> subjectList;
	@FXML
	private DatePicker datePicker;
	@FXML
	private CheckBox checkBox;
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
	public void setFields(Reminder reminder, UserDAO owner, Boolean mode) {
		addOrEdit=mode;
		user=owner;
		entryID=reminder.getID();
		entrySubject=reminder.getSubject();
		nameField.setText(reminder.getName());
		descriptionField.setText(reminder.getDescription());
		subjectList.setItems(SubjectDAO.searchByOwner(user));
		datePicker.setValue(reminder.getRemindDate().toLocalDate());
		checkBox.setSelected(reminder.getStatus());
	}
	
	@FXML
	private void save() {
		if(addOrEdit==false) {
			if(nameField.getText().trim()!="") {
				if(subjectList.getSelectionModel().getSelectedItem()!=null) {
					new ReminderDAO(null,
							nameField.getText(),
							descriptionField.getText(),
							subjectList.getSelectionModel().getSelectedItem(),
							LocalDateTime.now(),
							LocalDateTime.now(),
							datePicker.getValue().atStartOfDay(),
							checkBox.isSelected()).save();
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Recordatorio registrado");
					alert.setContentText("El recordatorio ha sido registrada con exito");
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
					new ReminderDAO(entryID,
							nameField.getText(),
							descriptionField.getText(),
							entrySubject,
							LocalDateTime.now(),
							LocalDateTime.now(),
							datePicker.getValue().atStartOfDay(),
							checkBox.isSelected()).save();
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Recordatorio actualizado");
					alert.setContentText("El recordatorio ha sido actualizada con exito");
					alert.showAndWait();
					closeWindow();
				}else{
					new ReminderDAO(entryID,
							nameField.getText(),
							descriptionField.getText(),
							subjectList.getSelectionModel().getSelectedItem(),
							LocalDateTime.now(),
							LocalDateTime.now(),
							datePicker.getValue().atStartOfDay(),
							checkBox.isSelected()).save();
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
