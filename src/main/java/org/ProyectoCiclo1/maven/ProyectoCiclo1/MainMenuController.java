package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.time.LocalDateTime;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Entry;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.EntryDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Reminder;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.ReminderDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuController {
	//UserDAO user=new UserDAO(recieveUserData());
	ObservableList<Subject> list=FXCollections.observableArrayList();
	ObservableList<Entry> entryTableList=FXCollections.observableArrayList();
	ObservableList<Reminder> reminderTableList=FXCollections.observableArrayList();
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ListView<Subject> subjectList;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab entryTab;
	@FXML
	private TableView<Entry> entryTable;
	@FXML
	private TableColumn<Entry, String> entryColumn1;
	@FXML
	private TableColumn<Entry, String> entryColumn2;
	@FXML
	private TableColumn<Entry, LocalDateTime> entryColumn3;
	@FXML
	private Tab reminderTab;
	@FXML
	private TableView<Reminder> reminderTable;
	@FXML
	private TableColumn<Reminder, String> reminderColumn1;
	@FXML
	private TableColumn<Reminder, String> reminderColumn2;
	@FXML
	private TableColumn<Reminder, LocalDateTime> reminderColumn3;
	@FXML
	private TableColumn<Reminder, LocalDateTime> reminderColumn4;
	@FXML
	private TableColumn<Reminder, Boolean> reminderColumn5;
	
	@FXML
	public void initialize() {
		//loadUserInfo();
		loadSubjects();
	}
//	@FXML
//	private void loadUserInfo() {
//		UserDAO user=new UserDAO(recieveUserData());
//		labelID.setText(user.getID().toString());
//		labelName.setText(user.getName().toString());
//	}
	@FXML
	private void loadSubjects() {
		list.removeAll(list);
		list.addAll(new Subject(1,"P.E"),new Subject(2,"Dibujo"));
		subjectList.getItems().addAll(list);
	}
	@FXML
	private void displayEntries() {
		entryTableList.removeAll(entryTableList);
		entryColumn1.setCellValueFactory(new PropertyValueFactory<Entry, String>("name"));
		entryColumn2.setCellValueFactory(new PropertyValueFactory<Entry, String>("description"));
		entryColumn3.setCellValueFactory(new PropertyValueFactory<Entry, LocalDateTime>("creationDate"));
		entryTableList.addAll(EntryDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
		entryTable.setItems(entryTableList);
		reminderTableList.removeAll(reminderTableList);
		reminderColumn1.setCellValueFactory(new PropertyValueFactory<Reminder, String>("name"));
		reminderColumn2.setCellValueFactory(new PropertyValueFactory<Reminder, String>("description"));
		reminderColumn3.setCellValueFactory(new PropertyValueFactory<Reminder, LocalDateTime>("creationDate"));
		reminderColumn4.setCellValueFactory(new PropertyValueFactory<Reminder, LocalDateTime>("remindDate"));
		reminderColumn5.setCellValueFactory(new PropertyValueFactory<Reminder, Boolean>("status"));
		reminderTableList.addAll(ReminderDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
		reminderTable.setItems(reminderTableList);
	}
	@FXML
	private UserDAO recieveUserData() {
		Stage stage=(Stage) anchorPane.getScene().getWindow();
		return (UserDAO)stage.getUserData();
	}
}
