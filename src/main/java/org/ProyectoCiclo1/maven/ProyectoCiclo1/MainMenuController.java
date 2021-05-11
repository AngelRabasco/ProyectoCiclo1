package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import java.time.LocalDateTime;

import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Entry;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.EntryDAO;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries.Reminder;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Subjects.Subject;
import org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Users.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuController {
	//UserDAO user=new UserDAO(recieveUserData());
	ObservableList<Subject> list=FXCollections.observableArrayList();
	ObservableList<Entry> table=FXCollections.observableArrayList();
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ListView<Subject> subjectList;
	@FXML
	private TableView<Entry> entryTable;
	@FXML
	private TableColumn<Entry, String> tableColumn1;
	@FXML
	private TableColumn<Entry, String> tableColumn2;
	@FXML
	private TableColumn<Entry, LocalDateTime> tableColumn3;
	@FXML
	private TableColumn<Reminder, LocalDateTime> tableColumn4;
	@FXML
	private TableColumn<Reminder, Boolean> tableColumn5;
	
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
		table.removeAll(table);
		tableColumn1.setCellValueFactory(new PropertyValueFactory<Entry, String>("name"));
		tableColumn2.setCellValueFactory(new PropertyValueFactory<Entry, String>("description"));
		tableColumn3.setCellValueFactory(new PropertyValueFactory<Entry, LocalDateTime>("creationDate"));
		tableColumn4.setCellValueFactory(new PropertyValueFactory<Reminder, LocalDateTime>("remindDate"));
		tableColumn5.setCellValueFactory(new PropertyValueFactory<Reminder, Boolean>("status"));
		table.addAll(EntryDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
		System.out.println(EntryDAO.searchBySubject(subjectList.getSelectionModel().getSelectedItem()));
		entryTable.setItems(table);
		
	}
	@FXML
	private UserDAO recieveUserData() {
		Stage stage=(Stage) anchorPane.getScene().getWindow();
		return (UserDAO)stage.getUserData();
	}
}
