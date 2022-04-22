package com.example.facultydb;

import java.sql.*;
import java.util.ArrayList;

public class Backend {
    private Connection conn;
    private ResultSet rs;
    private String query;

    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // empty constructor
    public Backend() {
    }

    // connect to database
    public void connect() {
        conn = null;
        String userName = "root";
        String password = "anuradha1974";
        String url = "jdbc:mysql://localhost/mydb";

        url = url + "?serverTimezone=UTC"; // added 8/27

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("\nCreated Connection!\n");

        } catch (ClassNotFoundException cnfe) {
            System.out.println("ERROR, CAN NOT CONNECT!!");
            System.out.println("Class");
            System.out.println("ERROR MESSAGE-> " + cnfe);
            System.exit(0);
        } catch (SQLException sqle) {
            System.out.println("ERROR SQLExcepiton in connect()");
            System.out.println("ERROR MESSAGE -> " + sqle);
            sqle.printStackTrace();
            System.exit(0);
        } // end of catch

    }

    // insert into faculty table
    public void insertFaculty(String name, String email, String phone, String password, String room) {
        try {
            query = "INSERT INTO faculty(name, email, phone, password, room) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, password);
            pstmt.setString(5, room);
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    public void insertIntoOffice(String officeHours){
        try {
            query = "INSERT INTO office(Hours) VALUES(?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, officeHours);
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    // insert into abstract table
    public void insertAbstract(String abs) {
        try {
            query = "INSERT INTO abstract(abstract) VALUES(?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, abs);
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    public int getFacultyID(String name){
        int id = 0;
        try {
            query = "SELECT id FROM faculty WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return id;
    }

    public int getAbstractID(String abs){
        int id = 0;
        try {
            query = "SELECT id FROM abstract WHERE abstract = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, abs);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return id;
    }

    public int getOfficeID(String hours){
        int id = 0;
        try {
            query = "SELECT id FROM office WHERE Hours = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, hours);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return id;
    }

    // get all the information of the faculty from student
    public ArrayList<String> getAllFacultyInfo(int id) {
        try{
            ArrayList<String> info = new ArrayList<>();
            String query = "SELECT faculty.id AS \"Faculty ID\",name, email, room, phone, abstract.abstract AS \"Abstract\", office.hours AS \"Hours\" FROM faculty INNER JOIN faculty_has_abstract ON faculty.id = faculty_has_abstract.faculty_id INNER JOIN abstract ON faculty_has_abstract.abstract_id = abstract.id INNER JOIN faculty_has_office ON faculty.id = faculty_has_office.faculty_id INNER JOIN office ON faculty_has_office.office_id = office.id WHERE faculty.id= (?) ORDER BY name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
                info.add(rs.getString(5));
                info.add(rs.getString(6));
                info.add(rs.getString(7));
            }
            return info;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

//    student/guest searches faculty by name
    public ArrayList<String> getFacultyByName(String name) {
        try{
            ArrayList<String> info = new ArrayList<>();
            String query = "SELECT faculty.id AS \"Faculty ID\",name, email, room, phone, abstract.abstract AS \"Abstract\", office.hours AS \"Hours\" FROM faculty INNER JOIN faculty_has_abstract ON faculty.id = faculty_has_abstract.faculty_id INNER JOIN abstract ON faculty_has_abstract.abstract_id = abstract.id INNER JOIN faculty_has_office ON faculty.id = faculty_has_office.faculty_id INNER JOIN office ON faculty_has_office.office_id = office.id WHERE name=? ORDER BY name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
                info.add(rs.getString(5));
                info.add(rs.getString(6));
                info.add(rs.getString(7));
            }
            return info;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

//    faculty searches student by interest (regex)
    public ArrayList<String> getStudentByInterest(String interest) {
        try{
            ArrayList<String> info = new ArrayList<>();
            String query = "SELECT student.id AS \"Student ID\", name, email, major, minor, gpa, phone, interest FROM student INNER JOIN student_has_interest ON student.id = student_has_interest.student_id WHERE interest LIKE ? ORDER BY name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, interest);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
                info.add(rs.getString(5));
                info.add(rs.getString(6));
                info.add(rs.getString(7));
                info.add(rs.getString(8));
            }
            return info;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

//    guest search faculty by abstract
    public ArrayList<String> getFacultyByAbstract(String abstractText) {
        try{
            ArrayList<String> info = new ArrayList<>();
            String query = "SELECT faculty.id AS \"Faculty ID\",name, email, room, phone, abstract.abstract AS \"Abstract\", office.hours AS \"Hours\" FROM faculty INNER JOIN faculty_has_abstract ON faculty.id = faculty_has_abstract.faculty_id INNER JOIN abstract ON faculty_has_abstract.abstract_id = abstract.id INNER JOIN faculty_has_office ON faculty.id = faculty_has_office.faculty_id INNER JOIN office ON faculty_has_office.office_id = office.id WHERE abstract.abstract LIKE ? ORDER BY name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, abstractText);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
                info.add(rs.getString(5));
                info.add(rs.getString(6));
                info.add(rs.getString(7));
            }
            return info;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

//    show interested guests to faculty
    public ArrayList<String> getInterestedGuests(String facultyID) {
        try{
            ArrayList<String> info = new ArrayList<>();
            String query = "SELECT guest.id AS \"Guest ID\", name, email, phone, interest FROM guest INNER JOIN guest_has_interest ON guest.id = guest_has_interest.guest_id INNER JOIN faculty_has_interest ON guest_has_interest.interest_id = faculty_has_interest.interest_id WHERE faculty_has_interest.faculty_id = ? ORDER BY name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, facultyID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
                info.add(rs.getString(5));
            }
            return info;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

//    create guest
    public boolean createGuest(String name, String email, String phone) {
        try{
            String query = "INSERT INTO guest (name, email, phone) VALUES (?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

//    Insert faculty interest of guest
    public boolean insertFacultyInterest(String guestID, String interestID) {
        try{
            String query = "INSERT INTO guest_has_interest (guest_id, interest_id) VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, guestID);
            preparedStatement.setString(2, interestID);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

//    update faculty
    public boolean updateFaculty(String id, String name, String email, String phone) {
        try{
            String query = "UPDATE faculty SET name = ?, email = ?, phone = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, id);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

//    delete faculty
    public boolean deleteFaculty(String id) {
        try{
            String query = "DELETE FROM faculty WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }
}