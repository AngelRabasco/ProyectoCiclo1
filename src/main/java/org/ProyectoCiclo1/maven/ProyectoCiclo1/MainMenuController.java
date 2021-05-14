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
		this.user=userLogin;
	}
	
	@FXML
	public void loadSubjects() {
		subjectList.setItems(SubjectDAO.searchByOwner(user));
	}
	@FXML
	private void displayEntries() {
		if(subjectList.getSelectionModel().getSelectedItem()!=null) {
			entryTable.setItems(EntryDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
			reminderTable.setItems(ReminderDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
			checkDate(reminderTable.getItems());
		}
	}
	
	@FXML
	private void addSubject() {
		loadSubjectEditor(new Subject(), false);
	}
	@FXML
	private void addEntry() {
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
		if(subjectList.getSelectionModel().getSelectedItem()!=null) {
			loadSubjectEditor(subjectList.getSelectionModel().getSelectedItem(), true);
		}
	}
	@FXML
	private void editEntry() {	
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
		if(subjectList.getSelectionModel().getSelectedItem()!=null) {
			new SubjectDAO(subjectList.getSelectionModel().getSelectedItem()).remove();
			loadSubjects();
		}
	}
	@FXML
	private void deleteEntry() {
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
	
	@FXML
	private UserDAO recieveUserData() {
		Stage stage=(Stage) anchorPane.getScene().getWindow();
		return (UserDAO)stage.getUserData();
	}
}
