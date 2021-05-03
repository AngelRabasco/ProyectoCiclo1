module org.ProyectoCiclo1.maven.ProyectoCiclo1 {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens org.ProyectoCiclo1.maven.ProyectoCiclo1 to javafx.fxml;
	exports org.ProyectoCiclo1.maven.ProyectoCiclo1;
}