package ourproject;

// @author Catherine Habib

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {

    public boolean Connection(String s) throws SQLException {
        java.sql.Connection con;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylb", "root", "1234");
            Statement stmt = con.createStatement();
            if (s.contains("SELECT * FROM EMPLOYEES")) {
                rs = stmt.executeQuery(s);
                returnTableData(rs, stmt);
            } else if (s.contains("SELECT * FROM TASKS")) {
                rs = stmt.executeQuery(s);
                returnLTableData(rs, stmt);
            } else if (s.contains("SELECT * FROM PROJECTS")) {
                rs = stmt.executeQuery(s);
                returnVSTableData(rs, stmt);
            } else if (s.contains("SELECT * FROM leave_request") || s.contains("SELECT * FROM timecards")) {
                rs = stmt.executeQuery(s);
                returnSTableData(rs, stmt);
            } else if (s.contains("SELECT * FROM mission_request")) {
                rs = stmt.executeQuery(s);
                returnMTableData(rs, stmt);
            } else {
                stmt.execute(s);
                stmt.close();
            }
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void returnTableData(ResultSet rs, Statement s) throws SQLException {
        try (rs) {
            System.out.print("\033[H\033[2J");
            System.out.println("\tID\t|\t\tFname\t|\t\tMidname\t|\t\tLname\t|\t\tUser Type\t|\t\tAge\t|\t\tHiring Date\t|\t\t\tUsername\t|\t\tPassword\t\t|");
            System.out.println(" ________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________\n");
            while (rs.next()) {
                System.out.print("\033[H\033[2J");
                for (int i = 1; i <= 9; i++)
                    System.out.print("\t" + rs.getString(i) + "\t|\t");
                System.out.print("\n");
            }
        }
        s.close();
        rs.close();
    }
    
    public void returnLTableData(ResultSet rs, Statement s) throws SQLException {
        try (rs) {
            System.out.print("\033[H\033[2J");
            System.out.println("\tCode\t|\t\tTitle\t|\t\tDescription\t|\t\tEmp\t|\t\tPhase\t|\t\tProject\t|\t\tCreator\t|\t\t\tStart Date\t|\t\t\tEnd Date\t|\t\tEstimation Hrs\t\t|");
            System.out.println(" ____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________\n");
            while (rs.next()) {
                System.out.print("\033[H\033[2J");
                for (int i = 1; i <= 10; i++)
                    System.out.print("\t" + rs.getString(i) + "\t|\t");
                System.out.print("\n");
            }
        }
        s.close();
        rs.close();
    }
    
    public void returnSTableData(ResultSet rs, Statement s) throws SQLException {
        try (rs) {
            System.out.print("\033[H\033[2J");
            System.out.println("\tID\t|\t\tLeave Type\t\t|\t\tRequest Approval\t|");
            System.out.println(" __________________________________________________________________________________________\n");
            while (rs.next()) {
                System.out.print("\033[H\033[2J");
                for (int i = 1; i <= 3; i++)
                    System.out.print("\t" + rs.getString(i) + "\t|\t");
                System.out.print("\n");
            }
        }
        s.close();
        rs.close();
    }
    
    public void returnVSTableData(ResultSet rs, Statement s) throws SQLException {
        try (rs) {
            System.out.print("\033[H\033[2J");
            System.out.println("\tProject ID\t|\t\tProject Name\t\t|");
            System.out.println(" ___________________________________________________________________________\n");
            while (rs.next()) {
                System.out.print("\033[H\033[2J");
                for (int i = 1; i <= 2; i++)
                    System.out.print("\t" + rs.getString(i) + "\t|\t");
                System.out.print("\n");
            }
        }
        s.close();
        rs.close();
    }
    
    public void returnMTableData(ResultSet rs, Statement s) throws SQLException {
        try (rs) {
            System.out.print("\033[H\033[2J");
            System.out.println("\tEmp. ID\t|\t\tMission Code\t\t|\t\tRequest Date\t\t|\t\tRequest Approval\t|");
            System.out.println(" _________________________________________________________________________________________________________________________\n");
            while (rs.next()) {
                System.out.print("\033[H\033[2J");
                for (int i = 1; i <= 4; i++)
                    System.out.print("\t" + rs.getString(i) + "\t|\t");
                System.out.print("\n");
            }
        }
        s.close();
        rs.close();
    }

    public boolean checkID(String s, int id) throws SQLException, ClassNotFoundException {
        java.sql.Connection con;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylb", "root", "1234");
            PreparedStatement stmt = con.prepareStatement(s);
            rs = stmt.executeQuery();
            boolean b = false;
            if (rs.next()) {
                int ID = rs.getInt(1);
                if (id == ID)
                    b = true;
            }
            rs.close();
            stmt.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int returnEmployeesID(String s) throws SQLException, ClassNotFoundException {
        java.sql.Connection con;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylb", "root", "1234");
            PreparedStatement stmt = con.prepareStatement(s);
            rs = stmt.executeQuery();
            int ID = -1 ; 
            if (rs.next()) {
                ID = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            return ID;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int returnAssignedID(String s) throws SQLException, ClassNotFoundException {
        java.sql.Connection con;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylb", "root", "1234");
            PreparedStatement stmt = con.prepareStatement(s);
            rs = stmt.executeQuery();
            int ID = -1 ; 
            if (rs.next()) {
                ID = rs.getInt(4);
            }
            rs.close();
            stmt.close();
            return ID;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public boolean checkApprovals(String s) throws SQLException, ClassNotFoundException {
        java.sql.Connection con;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylb", "root", "1234");
            PreparedStatement stmt = con.prepareStatement(s);
            rs = stmt.executeQuery();
            boolean b = true;
            if (rs.next()) {
                String app = rs.getString(3);
                if (app.equals("disapproved"))
                    b = false;
            }
            rs.close();
            stmt.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean returnLoginData(String s, String userName, String Pass, String type) throws SQLException, ClassNotFoundException {
        java.sql.Connection con;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylb", "root", "1234");
            PreparedStatement stmt = con.prepareStatement(s);
            stmt.setString(1, userName);
            stmt.setString(2, Pass);
            stmt.setString(3, type);
            rs = stmt.executeQuery();
            boolean b = false;
            if (rs.next()) {
                String mail = rs.getString(8);
                String pass = rs.getString(9);
                if (userName.equalsIgnoreCase(mail) && Pass.equals(pass))
                    b = true;
            }
            rs.close();
            stmt.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}



