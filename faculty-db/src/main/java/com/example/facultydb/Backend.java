package com.example.facultydb;

import java.sql.*;
import java.util.ArrayList;

public class Backend {
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;
    private ResultSet rs;
    private String query;

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

    public void insertIntoOffice(String officeHours) {
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

    public int getFacultyID(String email) {
        int id = 0;
        try {
            query = "SELECT id FROM faculty WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return id;
    }

    public int getAbstractID(String abs) {
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

    public int getOfficeID(String hours) {
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
    public ArrayList<String> getAllFacultyInfo(String id) {
        try {
            ArrayList<String> info = new ArrayList<>();
            String query = "SELECT faculty.id AS \"Faculty ID\",name, email, room, phone, password, group_concat(abstract.abstract separator \" | \") AS \"Abstract\", office.hours AS \"Hours\" FROM faculty INNER JOIN faculty_has_abstract ON faculty.id = faculty_has_abstract.faculty_id INNER JOIN abstract ON faculty_has_abstract.abstract_id = abstract.id INNER JOIN faculty_has_office ON faculty.id = faculty_has_office.faculty_id INNER JOIN office ON faculty_has_office.office_id = office.id WHERE faculty.email= (?) ORDER BY name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(1));
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
                info.add(rs.getString(5));
                info.add(rs.getString(6));
                info.add(rs.getString(7));
                info.add(rs.getString(8));

            }
            return info;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

    //    Add abstract and faculty_has_abstract
    public void addAbstract(String abs, String email) {
        try {
            String query = "INSERT INTO abstract (abstract) VALUES (?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, abs);
            preparedStatement.execute();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO faculty_has_abstract (faculty_id, abstract_id) VALUES (?, ?)");
            pstmt.setInt(1, getFacultyID(email));
            pstmt.setInt(2, getAbstractID(abs));
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }
    public void addoffice(String hours, String email) {
        try {
            String query = "INSERT INTO office (hours) VALUES (?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, hours);
            preparedStatement.execute();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO faculty_has_office (faculty_id, office_id) VALUES (?, ?)");
            pstmt.setInt(1, getFacultyID(email));
            pstmt.setInt(2, getOfficeID(hours));
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }


    //    student/guest searches faculty by name
    public ArrayList<String> getFacultyByName(String name) {
        try {
            ArrayList<String> info = new ArrayList<>();
            String query = "SELECT faculty.id AS \"Faculty ID\",name, email, room, phone, abstract.abstract AS \"Abstract\", office.hours AS \"Hours\" FROM faculty INNER JOIN faculty_has_abstract ON faculty.id = faculty_has_abstract.faculty_id INNER JOIN abstract ON faculty_has_abstract.abstract_id = abstract.id INNER JOIN faculty_has_office ON faculty.id = faculty_has_office.faculty_id INNER JOIN office ON faculty_has_office.office_id = office.id WHERE name=? ORDER BY name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(1));
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
                info.add(rs.getString(5));
                info.add(rs.getString(6));
                info.add(rs.getString(7));
            }
            return info;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

    //    faculty searches student by interest (regex)
    public ArrayList<String> getStudentByInterest(String interest) {
        try {
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
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

    //    guest search faculty by abstract
    public ArrayList<String> getFacultyByAbstract(String abstractText) {
        try {
            ArrayList<String> info = new ArrayList<>();
            String query = "SELECT faculty.id AS \"Faculty ID\",name, email, room, phone, abstract.abstract AS \"Abstract\", office.hours AS \"Hours\" FROM faculty INNER JOIN faculty_has_abstract ON faculty.id = faculty_has_abstract.faculty_id INNER JOIN abstract ON faculty_has_abstract.abstract_id = abstract.id INNER JOIN faculty_has_office ON faculty.id = faculty_has_office.faculty_id INNER JOIN office ON faculty_has_office.office_id = office.id WHERE abstract.abstract LIKE ? ORDER BY name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, abstractText);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(1));
                System.out.println(rs.getString(1));
//                info.add(rs.getString(2));
//                info.add(rs.getString(3));
//                info.add(rs.getString(4));
//                info.add(rs.getString(5));
//                info.add(rs.getString(6));
            }
            return info;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        }
    }

    //    show interested guests to faculty
    public ArrayList<String>  getInterestedGuests(int facultyID) {
        try {
            ArrayList<String> info = new ArrayList<>();
            String query= ("SELECT guest.id AS \"ID\", guest.name AS \"Guest Name\", guest.email AS \"Email\", guest.request AS \"Request\"\n" +
                    "FROM faculty \n" +
                    "INNER JOIN faculty_has_guest ON faculty.id = faculty_has_guest.faculty_id \n" +
                    "INNER JOIN guest ON faculty_has_guest.guest_id = guest.id WHERE faculty.id = ?;");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, facultyID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(1));
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
            }
            return info;

        } catch (SQLException sqle) {
            System.out.println(sqle);
            return null;

        }

    }

    //    create guest
    public boolean createGuest(String name, String email, String keyword) {
        try {
            String query = "INSERT INTO guest (name, email) VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

    //    Insert faculty interest of guest
    public boolean insertFacultyInterest(String guestID, String interestID) {
        try {
            String query = "INSERT INTO guest_has_interest (guest_id, interest_id) VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, guestID);
            preparedStatement.setString(2, interestID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

    //    update faculty
    public boolean updateFaculty(int id, String email, String name, String phone, String room, String hours, String password, String abstract1) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
            String query = "UPDATE faculty SET name = ?, phone = ?, password = ? , room = ? WHERE id = ?;";
            String query3 = "SELECT hours FROM office WHERE id = (SELECT office_id FROM faculty_has_office WHERE faculty_id = ?);";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(5, id);
            preparedStatement.setString(4, room);
            System.out.println("query1");
            preparedStatement.executeUpdate();
            stmt.execute("SET FOREIGN_KEY_CHECKS=1");

            System.out.println("query3");
            PreparedStatement preparedStatement3 = conn.prepareStatement(query3);
            preparedStatement3.setInt(1, id);
            ResultSet rs3 = preparedStatement3.executeQuery();


            if (rs3.next()) {
                String hours2 = rs3.getString(1);
                if (!hours.equals(hours2)) {
                    System.out.println("hours");
                    String insertHours = "INSERT INTO office (hours) VALUES " + "('" + hours + "');";
                    PreparedStatement preparedStatementNewHours = conn.prepareStatement(insertHours);
                    preparedStatementNewHours.executeUpdate();
                    System.out.println("inserted hours");
                    String linkHours = "UPDATE faculty_has_office SET office_id = (SELECT id FROM office WHERE hours = (?)) WHERE faculty_id = ?;";
                    PreparedStatement preparedStatementLinkHours = conn.prepareStatement(linkHours);
                    preparedStatementLinkHours.setString(1, hours);
                    preparedStatementLinkHours.setInt(2, id);
                    preparedStatementLinkHours.executeUpdate();
                }
            }
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

    //    delete faculty
    public boolean deleteFaculty(String id) {
        try {
            Statement statement = conn.createStatement();
            statement.executeQuery("set foreign_key_checks=0;");
            String query = "DELETE FROM faculty WHERE email = ?;";
            String query2 = "DELETE FROM faculty_has_abstract WHERE faculty_id = (SELECT id FROM faculty WHERE email = (?));";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
            preparedStatement.setString(1, id);
            preparedStatement2.setString(1, id);
            preparedStatement2.executeUpdate();
            preparedStatement.executeUpdate();
            statement.executeQuery("set foreign_key_checks=1;");
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

    //    login verification
    public boolean loginVerification(String username, String password) {
        try {
            String query = "SELECT password FROM faculty WHERE email = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String passwordDB = rs.getString(1);
                return passwordDB.equals(password);
            }
            return false;

        } catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

    //save student
    public boolean saveStudent(String name, String interest) {
        try {
            String query = "INSERT INTO student (name, interest) VALUES " + "('" + name + "', '" + interest + "');";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("inserted student");
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return false;
        }
    }

    //search student
    public ArrayList<String> showStudents(int facultyID) {
        try {
            ArrayList<String> info = new ArrayList<>();
            String query= ("SELECT *, (name LIKE '%me1%') AS name_matched,(interest LIKE '%me1%') AS interest_matched FROM student WHERE name LIKE '%me1%' OR interest LIKE '%me1%'");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, facultyID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                info.add(rs.getString(1));
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
            }
            return info;

        } catch (SQLException sqle) {
            System.out.println(sqle);
            return null;

        }

    }

}