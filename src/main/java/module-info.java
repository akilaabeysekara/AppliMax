module com.applimax.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.applimax.project.controller to javafx.fxml;
    exports com.applimax.project;
}