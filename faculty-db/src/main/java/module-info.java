module com.example.facultydb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.facultydb to javafx.fxml;
    exports com.example.facultydb;
}