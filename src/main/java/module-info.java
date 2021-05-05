module org.ProyectoCiclo1.maven.ProyectoCiclo1 {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires java.sql;
	requires spring.security.core;
	requires java.desktop;

	opens org.ProyectoCiclo1.maven.ProyectoCiclo1 to javafx.fxml;
	exports org.ProyectoCiclo1.maven.ProyectoCiclo1;
}
