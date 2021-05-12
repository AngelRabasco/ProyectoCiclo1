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
import javafx.scene.control.cell.TextFieldTableCell;
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
	private TableColumn<Entry, String> entryColumnName;
	@FXML
	private TableColumn<Entry, String> entryColumnDescription;
	@FXML
	private TableColumn<Entry, LocalDateTime> entryColumnCreation;
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
	private TableColumn<Reminder, LocalDateTime> reminderColumnReminder;
	@FXML
	private TableColumn<Reminder, Boolean> reminderColumnStatus;
	
	@FXML
	public void initialize() {
		//loadUserInfo();
		loadSubjects();
		entryColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
		entryColumnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		reminderColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
		reminderColumnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
//		reminderColumnReminder.setCellFactory(null);
//		reminderColumnStatus.setCellFactory(null);
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
		entryColumnName.setCellValueFactory(new PropertyValueFactory<Entry, String>("name"));
		entryColumnDescription.setCellValueFactory(new PropertyValueFactory<Entry, String>("description"));
		entryColumnCreation.setCellValueFactory(new PropertyValueFactory<Entry, LocalDateTime>("creationDate"));
		entryTableList.addAll(EntryDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
		entryTable.setItems(entryTableList);
		reminderTableList.removeAll(reminderTableList);
		reminderColumnName.setCellValueFactory(new PropertyValueFactory<Reminder, String>("name"));
		reminderColumnDescription.setCellValueFactory(new PropertyValueFactory<Reminder, String>("description"));
		reminderColumnCreation.setCellValueFactory(new PropertyValueFactory<Reminder, LocalDateTime>("creationDate"));
		reminderColumnReminder.setCellValueFactory(new PropertyValueFactory<Reminder, LocalDateTime>("remindDate"));
		reminderColumnStatus.setCellValueFactory(new PropertyValueFactory<Reminder, Boolean>("status"));
		reminderTableList.addAll(ReminderDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
		reminderTable.setItems(reminderTableList);
		System.out.println(entryTableList);
	}
	
	@FXML
	private void editEntryName(TableColumn.CellEditEvent<Entry, String> event) {
		EntryDAO entry=new EntryDAO(event.getRowValue());
		entry.setName(event.getNewValue());
		entry.updateName();
	}
	@FXML
	private void editEntryDescription(TableColumn.CellEditEvent<Entry, String> event) {
		EntryDAO entry=new EntryDAO(event.getRowValue());
		entry.setDescription(event.getNewValue());
		entry.updateDescription();
	}
	
	@FXML
	private UserDAO recieveUserData() {
		Stage stage=(Stage) anchorPane.getScene().getWindow();
		return (UserDAO)stage.getUserData();
	}
}
