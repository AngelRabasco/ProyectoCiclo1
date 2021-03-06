package org.ProyectoCiclo1.maven.ProyectoCiclo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	public static Stage rootstage;

	@Override
	public void start(Stage stage) throws IOException {
		//Carga el login
		scene = new Scene(loadFXML("Login"));
		stage.setScene(scene);
		stage.setTitle("Please Login");
		stage.setResizable(false);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		//Genera el parent
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}
}
