package com.example.facultydb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication extends Application implements EventHandler<ActionEvent> {

//    private Button btnLogIn=new Button("Log In");
//    private Button btnSignUp=new Button("Sign Up");
//    private Label lblUsername=new Label("Username: ");
//    private TextField tfUsername=new TextField();

    public static Backend backend = new Backend();

    private final Label lblID1 = new Label("Faculty ID: ");
    private final Label lblName1 = new Label("Name: ");
    private final Label lblEmail1 = new Label("Email: ");
    private final Label lblPhone1 = new Label("Phone: ");
    private final Label lblRoom1 = new Label("Room: ");
    private final Label lblHours1 = new Label("Office Hours: ");
    private final Label lblPassword1 = new Label("Password: ");

    private final Label lblID = new Label("Faculty ID: ");
    private final Label lblName = new Label("Name: ");
    private final Label lblEmail = new Label("Email: ");
    private final Label lblPhone = new Label("Phone: ");
    private final Label lblRoom = new Label("Room: ");
    private final Label lblHours = new Label("Office Hours: ");


    private final TextField tfID1 = new TextField();
    private final TextField tfName1 = new TextField();
    private final TextField tfEmail1 = new TextField();
    private final TextField tfPhone1 = new TextField();
    private final TextField tfRoom1 = new TextField();
    private final TextField tfHours1 = new TextField();
    private final TextField tfPassword1 = new TextField();
    private final TextArea taAbstract1 = new TextArea();

    private final TextField tfID = new TextField();
    private final TextField tfName = new TextField();
    private final TextField tfEmail = new TextField();
    private final TextField tfPhone = new TextField();
    private final TextField tfRoom = new TextField();
    private final TextField tfHours = new TextField();
    private final TextArea taAbstract = new TextArea();

    private final Button btnSelect = new Button("Select");
    private final Button btnInsert = new Button("Insert into Faculty DB");
    private final Button btnSearch = new Button("Search");
    private final Button btnSave = new Button("Save");
    private final Button btnBack1 = new Button("<- Return to home");
    private final Button btnBack2 = new Button("<- Return to home");


    private final HBox hBox = new HBox();
    private final HBox hBoxS2 = new HBox();
    private final GridPane gridPane1 = new GridPane();
    private final GridPane gridPane2 = new GridPane();
    private final VBox vBox1 = new VBox();
    private final VBox vBox = new VBox();
    private final VBox rootS3 = new VBox();
    private final VBox rootS2 = new VBox();

    public static void main(String[] args) {
        backend.connect();
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Faculty DB");

        //Scene 1
        hBox.getChildren().addAll(btnInsert, btnSearch);

        //Scene 2
        tfID1.setText("Auto Increment");
        tfID1.setEditable(false);
        // Labels in GridPane
        addToPane2(gridPane1, lblID1, lblName1, lblEmail1, lblPhone1, lblRoom1, lblHours1);
        gridPane1.add(lblPassword1, 0, 6, 1, 1);
        //TextFields in GridPane
        addToPane1(gridPane1, tfID1, tfName1, tfEmail1, tfPhone1, tfRoom1, tfHours1);
        gridPane1.add(tfPassword1, 1, 6, 1, 1);

        hBoxS2.getChildren().addAll(btnBack1, btnSave);

        //Scene 3
        taAbstract.setEditable(false);
        tfEmail.setEditable(false);
        tfName.setEditable(false);
        tfPhone.setEditable(false);
        tfHours.setEditable(false);
        tfRoom.setEditable(false);
        // Labels in GridPane
        addToPane2(gridPane2, lblID, lblName, lblEmail, lblPhone, lblRoom, lblHours);
        //TextFields in GridPane
        addToPane1(gridPane2, tfID, tfName, tfEmail, tfPhone, tfRoom, tfHours);
        gridPane2.add(btnSelect, 2, 0, 1, 1);

        vBox1.getChildren().addAll(taAbstract1, hBoxS2);
        vBox.getChildren().addAll(taAbstract, btnBack2);

        //Design
        tfID1.setPrefWidth(300);
        tfID.setPrefWidth(300);
        gridPane1.setVgap(10);
        gridPane1.setHgap(5);
        gridPane2.setVgap(10);
        gridPane2.setHgap(5);
        hBox.setPadding(new Insets(25, 25, 25, 25));
        hBox.setSpacing(25);
        hBoxS2.setSpacing(400);
        vBox1.setPadding(new Insets(10, 0, 0, 0));
        vBox.setPadding(new Insets(10, 0, 0, 0));
        rootS2.setPadding(new Insets(10, 10, 10, 10));
        rootS3.setPadding(new Insets(10, 10, 10, 10));

        //gridPane and vBox in ROOT VBox
        rootS2.getChildren().addAll(gridPane1, vBox1);
        rootS3.getChildren().addAll(gridPane2, vBox);

        //Scene
        Scene scene1 = new Scene(hBox);
        Scene scene2 = new Scene(rootS2);
        Scene scene3 = new Scene(rootS3);
        stage.setScene(scene1);
        stage.show();

        //Button Actions
        btnSearch.setOnAction(e -> stage.setScene(scene3));
        btnInsert.setOnAction(e -> stage.setScene(scene2));
        btnBack1.setOnAction(e -> stage.setScene(scene1));
        btnBack2.setOnAction(e -> stage.setScene(scene1));

        btnSelect.setOnAction(this);
        btnSave.setOnAction(this);


    }

    private void addToPane1(GridPane gridPane1, TextField tfID1, TextField tfName1, TextField tfEmail1, TextField tfPhone1, TextField tfRoom1, TextField tfHours1) {
        gridPane1.add(tfID1, 1, 0, 1, 1);
        gridPane1.add(tfName1, 1, 1, 1, 1);
        gridPane1.add(tfEmail1, 1, 2, 1, 1);
        gridPane1.add(tfPhone1, 1, 3, 1, 1);
        gridPane1.add(tfRoom1, 1, 4, 1, 1);
        gridPane1.add(tfHours1, 1, 5, 1, 1);
    }

    private void addToPane2(GridPane gridPane1, Label lblID1, Label lblName1, Label lblEmail1, Label lblPhone1, Label lblRoom1, Label lblHours1) {
        gridPane1.add(lblID1, 0, 0, 1, 1);
        gridPane1.add(lblName1, 0, 1, 1, 1);
        gridPane1.add(lblEmail1, 0, 2, 1, 1);
        gridPane1.add(lblPhone1, 0, 3, 1, 1);
        gridPane1.add(lblRoom1, 0, 4, 1, 1);
        gridPane1.add(lblHours1, 0, 5, 1, 1);
    }

    @Override
    public void handle(ActionEvent e) {
        Button b = (Button) e.getSource();
        if (b == btnSave) {
            doSave();
        }
        if (b == btnSelect) {
            doSelect();
        }
    }

    private void doSelect() {
        int id = Integer.parseInt(tfID.getText());
        ArrayList<String> info = backend.getAllFacultyInfo(id);
        tfName.setText(info.get(0));
        tfEmail.setText(info.get(1));
        tfPhone.setText(info.get(3));
        tfRoom.setText(info.get(2));
        tfHours.setText(info.get(5));
        taAbstract.setText(info.get(4));
    }

    private void doSave() {
        String name = tfName1.getText();   //get the name from the textfield
        String email = tfEmail1.getText();   //get the email from the textfield
        String phone = tfPhone1.getText();   //get the phone from the textfield
        String room = tfRoom1.getText();   //get the room from the textfield
        String password = tfPassword1.getText();   //get the password from the textfield
        String hours = tfHours1.getText();   //get the hours from the textfield
        String abstract1 = taAbstract1.getText();   //get the abstract from the textarea
        if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || room.isEmpty() || password.isEmpty() || hours.isEmpty() || abstract1.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        }
        else {
            try {
                backend.insertFaculty(name, email, phone, password, room);
                backend.insertIntoOffice(hours);
                backend.insertAbstract(abstract1);

                int fID = backend.getFacultyID(name);
                int oID = backend.getOfficeID(hours);
                int aID = backend.getAbstractID(abstract1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Faculty member added successfully with Faculty ID:" + fID + " Office ID:" + oID + " Abstract ID:" + aID);
                alert.showAndWait();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
