module org.ProyectoCiclo1.maven.ProyectoCiclo1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.ProyectoCiclo1.maven.ProyectoCiclo1 to javafx.fxml;
    exports org.ProyectoCiclo1.maven.ProyectoCiclo1;
}