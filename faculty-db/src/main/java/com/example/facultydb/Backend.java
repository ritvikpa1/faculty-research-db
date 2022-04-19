package com.example.facultydb;

import java.sql.*;
import java.util.ArrayList;

public class Backend {
    private Connection conn;
    private Statement stmt;
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
        int rows = 0;
        try {
            query = "INSERT INTO abstract(abstract) VALUES(?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, abs);
            rows = pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    // get all the abstract values for the faculty to insert into the select box
    public void getAllAbstract() {
        try {
            query = "SELECT * FROM abstract";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
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

    // get all the information of the faculty
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
}


