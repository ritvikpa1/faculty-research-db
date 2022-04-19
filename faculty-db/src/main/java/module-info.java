module com.example.facultydb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.facultydb to javafx.fxml;
    exports com.example.facultydb;
}