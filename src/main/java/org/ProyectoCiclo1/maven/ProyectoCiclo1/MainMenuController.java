package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.io.IOException;
import java.time.LocalDateTime;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Entry;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.EntryDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Reminder;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.ReminderDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.SubjectDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {
	UserDAO user=new UserDAO();
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ListView<Subject> subjectList;
	@FXML
	private Button addSubjectButton;
	@FXML
	private Button editSubjectButton;
	@FXML
	private Button deleteSubjectButton;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab entryTab;
	@FXML
	private TableView<Entry> entryTable;
	@FXML
	private TableColumn<Entry, String> entryColumnName;
	@FXML
	private TableColumn<Entry, String> entryColumnDescription;
	@FXML
	private TableColumn<Entry, String> entryColumnCreation;
	@FXML
	private Tab reminderTab;
	@FXML
	private TableView<Reminder> reminderTable;
	@FXML
	private TableColumn<Reminder, String> reminderColumnName;
	@FXML
	private TableColumn<Reminder, String> reminderColumnDescription;
	@FXML
	private TableColumn<Reminder, LocalDateTime> reminderColumnCreation;
	@FXML
	private TableColumn<Reminder, DatePicker> reminderColumnReminder;
	@FXML
	private TableColumn<Reminder, Boolean> reminderColumnStatus;
	@FXML
	private Button addEntryButton;
	@FXML
	private Button editEntryButton;
	@FXML
	private Button deleteEntryButton;
	
	@FXML
	public void initialize() {
		//Da los valores a las columnas de las TableView
		entryColumnName.setCellValueFactory(new PropertyValueFactory<Entry, String>("name"));
		entryColumnDescription.setCellValueFactory(new PropertyValueFactory<Entry, String>("description"));
		entryColumnCreation.setCellValueFactory(new PropertyValueFactory<Entry, String>("creationDate"));
		reminderColumnName.setCellValueFactory(new PropertyValueFactory<Reminder, String>("name"));
		reminderColumnDescription.setCellValueFactory(new PropertyValueFactory<Reminder, String>("description"));
		reminderColumnCreation.setCellValueFactory(new PropertyValueFactory<Reminder, LocalDateTime>("creationDate"));
		reminderColumnReminder.setCellValueFactory(new PropertyValueFactory<Reminder, DatePicker>("remindDate"));
		reminderColumnStatus.setCellValueFactory(new PropertyValueFactory<Reminder, Boolean>("status"));		
	}
	@FXML
	public void loadUserInfo(UserDAO userLogin) {
		//Le otorga a user el usuario utilizado para iniciar sesión
		this.user=userLogin;
	}
	
	@FXML
	public void loadSubjects() {
		//Carga las asignaturas del usuario
		subjectList.setItems(SubjectDAO.searchByOwner(user));
	}
	@FXML
	private void displayEntries() {
		//Carga las entradas y los recordatorios de la asignaturas seleccionadas
		if(subjectList.getSelectionModel().getSelectedItem()!=null) {
			entryTable.setItems(EntryDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
			reminderTable.setItems(ReminderDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
			checkDate(reminderTable.getItems());
		}
	}
	
	@FXML
	private void addSubject() {
		//Carga el editor de asignaturas y le pasa un Booleano con valor false para cambiar la forma en la que actua a la hora de guardar la asignatura
		loadSubjectEditor(new Subject(), false);
	}
	@FXML
	private void addEntry() {
		//Carga el editor de entradas o recordatorios dependiendo de la pestaña en la que se encuentre, pasandole además un Booleano con para afectar la forma en la que se guarda
		switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
		case "Entradas":
			loadEntryEditor(new Entry(), false);
			break;
		case "Recordatorios":
			loadReminderEditor(new Reminder(), false);
			break;
		default:
			break;
		}
	}
	@FXML
	private void editSubject() {
		//Carga el editor de asignaturas con los valores de la asignatura seleccionada, y un Booleano de valor True para afectar a la hora del guardado
		if(subjectList.getSelectionModel().getSelectedItem()!=null) {
			loadSubjectEditor(subjectList.getSelectionModel().getSelectedItem(), true);
		}
	}
	@FXML
	private void editEntry() {
		//Carga el editor de entradas o de recordatorios dependiendo de la pestaña seleccionada, y un Booleano de valor True para afectar el guardado
		switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
		case "Entradas":
			if(entryTable.getSelectionModel().getSelectedItem()!=null) {
				loadEntryEditor(entryTable.getSelectionModel().getSelectedItem(), true);
			}
			break;
		case "Recordatorios":
			if(reminderTable.getSelectionModel().getSelectedItem()!=null) {
				loadReminderEditor(reminderTable.getSelectionModel().getSelectedItem(), true);
			}
			break;
		default:
			break;
		}
	}
	@FXML
	private void deleteSubject() {
		//Elimina la asignatura seleccionada y actualiza la lista
		if(subjectList.getSelectionModel().getSelectedItem()!=null) {
			new SubjectDAO(subjectList.getSelectionModel().getSelectedItem()).remove();
			loadSubjects();
		}
	}
	@FXML
	private void deleteEntry() {
		//Elimina la entrada o recordatorio y la elimina de la lista
		switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
		case "Entradas":
			if(entryTable.getSelectionModel().getSelectedItem()!=null) {
				new EntryDAO(entryTable.getSelectionModel().getSelectedItem()).remove();
				entryTable.getItems().remove(entryTable.getSelectionModel().getSelectedItem());
			}
			break;
		case "Recordatorios":
			if(reminderTable.getSelectionModel().getSelectedItem()!=null) {
				new ReminderDAO(reminderTable.getSelectionModel().getSelectedItem()).remove();
				reminderTable.getItems().remove(reminderTable.getSelectionModel().getSelectedItem());
			}
			break;
		default:
			break;
		}
	}
	
	@FXML
	private void loadSubjectEditor(Subject subject, Boolean mode) {
		//Carga el editor de asignatura con los valores determinados y un Booleano que modifica la forma en la que actua
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("SubjectEditor.fxml"));
			Parent parent=loader.load();
			SubjectEditorController subjectEditorController=loader.getController();
			subjectEditorController.setFields(subject, user, mode);
			Stage stage = new Stage();
			stage.setScene(new Scene(parent));
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			loadSubjects();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void loadEntryEditor(Entry entry, Boolean mode) {
		//Carga el editor de entradas con los valores determinados y un Booleano que modifica la forma en la que actua
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("EntryEditor.fxml"));
			Parent parent=loader.load();
			EntryEditorController entryEditorController=loader.getController();
			entryEditorController.setFields(entry,user,mode);
			Stage stage = new Stage();
			stage.setScene(new Scene(parent));
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			displayEntries();
		} catch (IOException e) {
			
		}	
	}
	@FXML
	private void loadReminderEditor(Reminder reminder, Boolean mode) {
		//Carga el editor de recordatorios con los valores determinados y un Booleano que modifica la forma en la que actua
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ReminderEditor.fxml"));
			Parent parent=loader.load();
			ReminderEditorController reminderEditorController=loader.getController();
			reminderEditorController.setFields(reminder,user,mode);
			Stage stage = new Stage();
			stage.setScene(new Scene(parent));
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			displayEntries();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	private void checkDate(ObservableList<Reminder> observableList) {
		//Comprueba las fechas para monstrar una advertencia si un recordatorio ha pasado la fecha y no está completo
		for(Reminder r:observableList) {
			if(r.getStatus()==false&&r.getRemindDate().isBefore(LocalDateTime.now())) {
				Alert alert=new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Recordatorio "+r.getName());
				alert.setContentText("El recordatorio "+r.getName()+" ha pasado la fecha establecida");
				alert.showAndWait();
			}
		}
	}
}
