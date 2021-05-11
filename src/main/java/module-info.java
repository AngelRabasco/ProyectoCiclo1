module org.ProyectoCiclo1.maven.ProyectoCiclo1 {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires java.sql;
	requires spring.security.core;
	requires java.desktop;
	requires javafx.graphics;

	opens org.ProyectoCiclo1.maven.ProyectoCiclo1 to javafx.fxml;
	opens org.ProyectoCiclo1.maven.ProyectoCiclo1.Model.Entries to javafx.base;
	exports org.ProyectoCiclo1.maven.ProyectoCiclo1;
}
