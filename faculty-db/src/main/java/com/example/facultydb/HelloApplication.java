package com.example.facultydb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication extends Application implements EventHandler<ActionEvent> {

    public static Backend backend = new Backend();


    //Scene Home
    private final Button btnStudent = new Button("Student");
    private final Button btnGuest = new Button("Guest");
    private final Button btnFaculty = new Button("Faculty");
    private final HBox hBoxHome = new HBox();
    private final Button btnback00 = new Button("<- Return to home");
    private final VBox vBoxHome = new VBox();

    //Scene Guest
    private final Label tfGuestName = new Label("Enter your Guest name: ");
    private final Label tfGuestEmail = new Label("Enter your email: ");
    private final TextField textField1 = new TextField();
    private final TextField textField2 = new TextField();
    private final Label label3 = new Label("Keyword to search a faculty member by name or abstract: ");
    private final TextField textField3 = new TextField();
    private final GridPane gridPane01 = new GridPane();
    private final Button btnGuestSearch = new Button("Search");
    private final Button btnback01 = new Button("<- Return to home");
    private final HBox hbox01 = new HBox();
    private final VBox vbox01 = new VBox();

    //Scene Student
    private final Label labelStudent = new Label("Enter Student name: ");
    private final TextField tfStudentName = new TextField();
    private final Label labelStudent2 = new Label("Enter your interest: ");
    private final TextField tfStudentInterest = new TextField();
    private final GridPane gpStudent = new GridPane();
    private final Label label4 = new Label("Keyword to search a faculty member by name or abstract:");
    private final TextField textField4 = new TextField();
    private final Button btnStudentSave = new Button("Save");
    private final Button btnBack02 = new Button("<- Return to home");
    private final Button btnSubmit02 = new Button("Search");
    private final HBox hbox02 = new HBox();
    private final VBox vbox02 = new VBox();

    // Scene 0
    private final Button btnLogIn = new Button("Log In");
    private final Button btnSignUp = new Button("Sign Up");
    private final Label lblUsername = new Label("Email ID: ");
    private final TextField tfUsername = new TextField();
    private final Label lblPassword = new Label("Password: ");
    private final PasswordField tfPassword = new PasswordField();

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

    //TextFields Scene1
    private final TextField tfID1 = new TextField();
    private final TextField tfName1 = new TextField();
    private final TextField tfEmail1 = new TextField();
    private final TextField tfPhone1 = new TextField();
    private final TextField tfRoom1 = new TextField();
    private final TextField tfHours1 = new TextField();
    private final TextField tfPassword1 = new TextField();
    private final TextArea taAbstract1 = new TextArea();

    //TextFields Scene2
    private final TextField tfID = new TextField();
    private final TextField tfName = new TextField();
    private final TextField tfEmail = new TextField();
    private final TextField tfPhone = new TextField();
    private final TextField tfRoom = new TextField();
    private final TextField tfHours = new TextField();
    private final TextArea taAbstract = new TextArea();

    //Buttons
    private final Button btnSelect = new Button("Select");
    private final Button btnSearch = new Button("Search");
    private final Button btnSave = new Button("Save");
    private final Button btnBack1 = new Button("<- Return to home");
    private final Button btnBack2 = new Button("Log Out");

    //Boxes

    //Scene 0
    private final GridPane gpFaculty = new GridPane();
    private final HBox hbx02 = new HBox(10);
    private final VBox vbx01 = new VBox(10);

    private final HBox hBoxS2 = new HBox();
    private final GridPane gridPane1 = new GridPane();
    private final GridPane gridPane2 = new GridPane();
    private final VBox vBox1 = new VBox();
    private final VBox vBox = new VBox();
    private final VBox rootS3 = new VBox();
    private final VBox rootS2 = new VBox();

    //Faculty after login
    private final HBox hBoxS3 = new HBox();
    private final Button btnupdate = new Button("Update profile");
    private final Button btndelete = new Button("Delete profile");
    private final Button btnMyRequest = new Button("My Requests");
    private final Button  btnStudentSearch = new Button("Search Student");

    //New Abstract
    private final Button btnnewabstract = new Button("New Abstract");
    private final TextArea taAbstracts = new TextArea("Abstracts");
    private final Button btnabstractssave = new Button("Save New Abstract");
    private final VBox vBox21 = new VBox();
    private final Button btnreturnfromabstract = new Button("<- Return");
    private final HBox hBoxAbstract= new HBox();

    //Search Student
    private final Label lblStudentKeyword = new Label("Keyword to search a student by name or interest:");
    private final TextField tfStudentKeyword = new TextField();
    private final Button  btnStudentSearch2 = new Button("Search Student");
    private final TextArea taStudentDetails = new TextArea();
    private final Button btnReturnStudents = new Button("<- Return");
    private final VBox vBoxStudents = new VBox();

    //My Request
    private final TextArea taRequests = new TextArea();
    private final Button btnReturnRequests = new Button("<- Return");
    private final VBox vBoxRequests = new VBox();

    //Faculty after login 2
    private final Label lblPassword2 = new Label("Password: ");
    private final TextField tfPassword2 = new TextField();

    //Create Request
    private final TextArea taGuestKeyword = new TextArea();
    private final Label lblRequestFaculty= new Label("Enter faculty ID to request: ");
    private final TextField tfRequestFaculty= new TextField();
    private final TextArea taCreateRequest = new TextArea();
    private final Button  btnSendRequest = new Button("Send Request");
    private final Button btnReturnGuest= new Button("<- Return");
    private final VBox vBoxGuestRequest = new VBox();
    private final HBox hBoxGuestRequest	= new HBox();


    public static void main(String[] args) {
        backend.connect();
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Faculty DB");

        //Scene Home
        Text title = new Text("Welcome to the Faculty Database!");
        title.setStyle("-fx-font: 24px Arial;-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, green 0%, red 80%);");
        Text title2 = new Text("Please select an option below");
        hBoxHome.getChildren().addAll(btnFaculty, btnStudent, btnGuest);
        vBoxHome.getChildren().addAll(title,title2,hBoxHome);
        hBoxHome.setAlignment(Pos.CENTER);
        vBoxHome.setAlignment(Pos.CENTER);
        vBoxHome.setSpacing(10);
        vBoxHome.setPadding(new Insets(10,10,10,10));

        //Create Request
        hBoxGuestRequest.getChildren().addAll(btnReturnGuest,btnSendRequest);
        vBoxGuestRequest.getChildren().addAll(taGuestKeyword, lblRequestFaculty,tfRequestFaculty,taCreateRequest, hBoxGuestRequest);
        vBoxGuestRequest.setSpacing(10);
        vBoxGuestRequest.setPadding(new Insets(10,10,10,10));

        //Scene 0
        gpFaculty.add(lblUsername,0,1,1,1);
        gpFaculty.add(tfUsername,1,1,1,1);
        gpFaculty.add(lblPassword,0,2,1,1);
        gpFaculty.add(tfPassword,1,2,1,1);
        hbx02.getChildren().addAll(btnback00, btnSignUp, btnLogIn);
        vbx01.getChildren().addAll(gpFaculty, hbx02);
        gpFaculty.setVgap(10);
        gpFaculty.setHgap(5);

        //Student Search



        //Guest
        addtopaneStudent(gridPane01, tfGuestName, textField1, tfGuestEmail, textField2, hbox01, btnback01, btnGuestSearch, vbox01, label3, textField3);

        //Student
        addtopaneStudent(gpStudent, labelStudent, tfStudentName, labelStudent2, tfStudentInterest, hbox02, btnBack02, btnSubmit02, vbox02, label4, textField4);
        gpStudent.add(btnStudentSave,3,1,1,1);
        //Scene Abstract
        hBoxAbstract.getChildren().addAll(btnreturnfromabstract, btnabstractssave);
        vBox21.getChildren().addAll(taAbstracts, hBoxAbstract);
        hBoxAbstract.setSpacing(10);

        //Scene My Requests
        taRequests.setEditable(false);
        vBoxRequests.getChildren().addAll(taRequests, btnReturnRequests);
        vBoxRequests.setSpacing(10);
        vBoxRequests.setPadding(new Insets(10,10,10,10));

        //Scene My Students
        taStudentDetails.setEditable(false);
        vBoxStudents.getChildren().addAll(lblStudentKeyword,tfStudentKeyword,btnStudentSearch2 ,taStudentDetails, btnReturnStudents);
        vBoxStudents.setSpacing(10);
        vBoxStudents.setPadding(new Insets(10,10,10,10));

        //Scene 2
        tfID1.setText("Auto assigned");
        tfID1.setEditable(false);
        addToPane2(gridPane1, lblID1, lblName1, lblEmail1, lblPhone1, lblRoom1, lblHours1);
        gridPane1.add(lblPassword1, 0, 6, 1, 1);
        addToPane1(gridPane1, tfID1, tfName1, tfEmail1, tfPhone1, tfRoom1, tfHours1);
        gridPane1.add(tfPassword1, 1, 6, 1, 1);
        hBoxS2.getChildren().addAll(btnBack1, btnSave);

        //Scene 3
        // Labels in GridPane
        tfID.setEditable(false);
        tfEmail.setEditable(false);
        taAbstract.setEditable(false);
        addToPane2(gridPane2, lblID, lblName, lblEmail, lblPhone, lblRoom, lblHours);
        //TextFields in GridPane
        addToPane1(gridPane2, tfID, tfName, tfEmail, tfPhone, tfRoom, tfHours);
        gridPane2.add(lblPassword2, 0, 6, 1, 1);
        gridPane2.add(tfPassword2, 1, 6, 1, 1);
        vBox1.getChildren().addAll(taAbstract1, hBoxS2);
        hBoxS3.getChildren().addAll(btnBack2, btnupdate, btndelete, btnnewabstract, btnMyRequest, btnStudentSearch);
        hBoxS3.setSpacing(10);
        vBox.getChildren().addAll(taAbstract, hBoxS3);

        //Design
        tfID1.setPrefWidth(300);
        tfID.setPrefWidth(300);
        gridPane01.setVgap(10);
        gridPane01.setHgap(5);
        gpStudent.setVgap(10);
        gpStudent.setHgap(5);
        gridPane1.setVgap(10);
        gridPane1.setHgap(5);
        gridPane2.setVgap(10);
        gridPane2.setHgap(5);
        hBoxHome.setPadding(new Insets(10, 10, 10, 10));
        hBoxHome.setSpacing(20);
        hBoxS2.setSpacing(400);
        vBox21.setPadding(new Insets(10, 10, 10, 10));
        vBox21.setSpacing(10);
        vbox01.setPadding(new Insets(10, 10, 10, 10));
        vbox01.setSpacing(10);
        vbox02.setPadding(new Insets(10, 10, 10, 10));
        vbox02.setSpacing(10);
        vbx01.setPadding(new Insets(10, 10, 10, 10));
        vBox1.setPadding(new Insets(10, 0, 0, 0));
        vBox.setPadding(new Insets(10, 0, 0, 0));
        rootS2.setPadding(new Insets(10, 10, 10, 10));
        rootS3.setPadding(new Insets(10, 10, 10, 10));

        //gridPane and vBox in ROOT VBox
        rootS2.getChildren().addAll(gridPane1, vBox1);
        rootS3.getChildren().addAll(gridPane2, vBox);

        //Scene
        Scene sceneGuestRequest = new Scene(vBoxGuestRequest);
        Scene sceneStudentSearch = new Scene(vBoxStudents);
        Scene sceneRequests = new Scene(vBoxRequests);
        Scene sceneAbstract = new Scene(vBox21);
        Scene sceneHome = new Scene(vBoxHome);
        Scene sceneStudent = new Scene(vbox02);
        Scene sceneGuest = new Scene(vbox01);
        Scene sceneLogin = new Scene(vbx01);
        Scene scene2 = new Scene(rootS2);
        Scene scene3 = new Scene(rootS3);
        stage.setScene(sceneHome);
        stage.show();

        //Button Actions
        btnFaculty.setOnAction(e -> stage.setScene(sceneLogin));
        btnSearch.setOnAction(e -> stage.setScene(scene3));
        btnSignUp.setOnAction(e -> stage.setScene(scene2));
        btnBack1.setOnAction(e -> stage.setScene(sceneHome));
        btnBack2.setOnAction(e -> stage.setScene(sceneLogin));
        btnback00.setOnAction(e -> stage.setScene(sceneHome));
        btnGuest.setOnAction(e -> stage.setScene(sceneGuest));
        btnback01.setOnAction(e -> stage.setScene(sceneHome));
        btnStudent.setOnAction(e -> stage.setScene(sceneStudent));
        btnreturnfromabstract.setOnAction(e -> stage.setScene(scene3));
        btnnewabstract.setOnAction(e -> stage.setScene(sceneAbstract));
        btnReturnRequests.setOnAction(e -> stage.setScene(scene3));
        btnBack02.setOnAction(e -> stage.setScene(sceneHome));
        btnReturnStudents.setOnAction(e -> stage.setScene(scene3));
        btnStudentSearch.setOnAction(e -> stage.setScene(sceneStudentSearch));
//        btnGuestSearch.setOnAction(e -> stage.setScene(sceneGuestRequest));


        //Button Actions
        btnMyRequest.setOnAction(this);
        btnabstractssave.setOnAction(this);
        btnSave.setOnAction(this);
        btnupdate.setOnAction(this);
        btndelete.setOnAction(this);
        btnGuestSearch.setOnAction(this);
        btnStudentSave.setOnAction(this);
        btnStudentSearch2.setOnAction(this);



        btnBack2.setOnAction(e -> stage.setScene(sceneLogin));


        btnLogIn.setOnAction(e -> {
            if (doLogin()) {
                stage.setScene(scene3);
            }
        });

        btndelete.setOnAction(e -> {
            if (doDelete()) {
                stage.setScene(sceneHome);
            }
        });

        btnMyRequest.setOnAction(e -> {
            if (doShowRequests()) {
                stage.setScene(sceneRequests);
            }
        });

        btnGuestSearch.setOnAction(e -> {
            if (doGuestSearch()) {
                stage.setScene(sceneGuestRequest);
            }
        });


        tfPassword.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                btnLogIn.fire();
            }
        });

    }


    private void addtopaneStudent(GridPane gpStudent, Label labelStudent, TextField textFieldStudent, Label labelStudent2, TextField textFieldStudent2, HBox hbox02, Button btnBack02, Button btnSubmit02, VBox vbox02, Label label4, TextField textField4) {
        gpStudent.add(labelStudent, 0, 0, 1, 1);
        gpStudent.add(textFieldStudent, 1, 0, 1, 1);
        gpStudent.add(labelStudent2, 0, 1, 1, 1);
        gpStudent.add(textFieldStudent2, 1, 1, 1, 1);
        hbox02.getChildren().addAll(btnBack02, btnSubmit02);
        vbox02.getChildren().addAll(gpStudent, label4, textField4, hbox02);
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
        if (b == btnupdate) {
            doUpdate();
        }
        if (b == btndelete) {
            doDelete();
        }
        if (b == btnabstractssave) {
            doAbstractSave();
        }
        if (b == btnGuestSearch) {
            doSaveGuestAndSearch();
        }
        if (b == btnStudentSave) {
            doStudentSave();
        }
        if (b == btnStudentSearch2) {
            doStudentSearch();
        }
        if (b == btnGuestSearch) {
            doGuestSearch();
        }
    }

    private boolean doGuestSearch() {
        String name = tfGuestName.getText();
        String email = tfGuestEmail.getText();
        String id = tfRequestFaculty.getText();
        String request = taCreateRequest.getText();
        return true;
    }

    private boolean doStudentSearch() {
        String id = tfStudentKeyword.getText();


        return true;
    }

    private void doStudentSave() {
        String Name = tfStudentName.getText();
        String Interest = tfStudentInterest.getText();

        if (Name.isEmpty() || Interest.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
        else {
            try {
                backend.saveStudent(Name, Interest);
            }
            catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();
            }
        }

    }

    private boolean doShowRequests() {
        int id = Integer.parseInt(tfID.getText());
        System.out.println(id);
        ArrayList<String> info = backend.getInterestedGuests(id);
        taRequests.setText("Guest ID: "+info.get(0)+"\nName: "+info.get(1)+"\nEmail: "+info.get(2)+"\nRequest: "+info.get(3));
        return true;

    }

    private void doSaveGuestAndSearch() {
        String name = textField1.getText();
        String email = textField2.getText();
        String keyword = textField3.getText();

    }

    private void doAbstractSave() {
        String abs = taAbstracts.getText();
        String email = tfEmail.getText();
        backend.addAbstract(abs, email);
    }


    private void doUpdate() {
        int id = Integer.parseInt(tfID.getText());
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String room = tfRoom.getText();
        String hours = tfHours.getText();
        String Password = tfPassword2.getText();
        String abstract1 = taAbstract.getText();
        backend.updateFaculty(id, email, name, phone, room, hours, Password, abstract1);
    }

    private boolean doDelete() {
        String id = tfEmail.getText();
        backend.deleteFaculty(id);
        System.out.println("Deletion Successful");
        return true;
    }

    private boolean doLogin() {
        String id = tfUsername.getText();
        String password = tfPassword.getText();
        if (id.equals("admin") && password.equals("admin")) {
            return true;
        } else if (backend.loginVerification(id, password)) {
            System.out.println("Login Successful");
            doSelect();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Error");
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
            return false;
        }
    }

    private void doSelect() {
        String id = tfUsername.getText();
        ArrayList<String> info = backend.getAllFacultyInfo(id);
        tfID.setText(info.get(0));
        tfName.setText(info.get(1));
        tfEmail.setText(info.get(2));
        tfPhone.setText(info.get(4));
        tfRoom.setText(info.get(3));
        tfPassword2.setText(info.get(5));
        tfHours.setText(info.get(7));
        taAbstract.setText(info.get(6));
    }

    private void doSave() {
        String name = tfName1.getText();   //get the name from the textfield
        String email = tfEmail1.getText();   //get the email from the textfield
        String phone = tfPhone1.getText();   //get the phone from the textfield
        String room = tfRoom1.getText();   //get the room from the textfield
        String password = tfPassword1.getText();   //get the password from the textfield
        String hours = tfHours1.getText();   //get the hours from the textfield
        String abstract1 = taAbstract1.getText();   //get the abstract from the textarea
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || room.isEmpty() || password.isEmpty() || hours.isEmpty() || abstract1.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        } else {
            try {
                backend.insertFaculty(name, email, phone, password, room);
                backend.addoffice(hours,email);
                backend.addAbstract(abstract1,email);

                int fID = backend.getFacultyID(email);
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