/*To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package ourproject;

// @author Catherine Habib

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employee extends User {
    
    protected String employeeType;
    protected String leaveType;
    boolean b;
//    protected final String EmployeeFileName = "D:\\MyDocuments\\2nd year\\PL2\\MyProjects\\OurProject\\ourproject\\Employees.txt";
//    public static ArrayList<Employee> Employees = new ArrayList<Employee>();
    Connection connect = new Connection(); 
    private String st,s;
    
    Employee() {
        this.setType("Employee");
        employeeType = "Employee";
    }
    
    Employee(String user, String pass, int id, String fname, String midname, String lname, int age, String employeeType) {
        super(user, pass, id, fname, midname, lname, age);
        this.setType("Employee");
        this.employeeType = employeeType;
    }
    
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeType() {
        return this.employeeType;
    }
    
    public boolean addEmployee(Employee e) {
        st = "INSERT INTO EMPLOYEES VALUES (" + 
                e.id + ", '" + 
                e.fname + "', '" + 
                e.midname + "', '" +
                e.lname + "', '" + 
                e.employeeType + "', " + 
                e.age + ", '" + 
                e.hiringDate.toString() + "', '" + 
                e.username + "', '" + 
                e.passw + "');";
        try {
            connect.Connection(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean displayAllEmployees() throws SQLException {
        st = "SELECT * FROM EMPLOYEES;";
        try {
            System.out.println("\nDisplaying Employees ... !\n");
            boolean b = connect.Connection(st);
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean searchEmployee(int id) throws SQLException, ClassNotFoundException {
        st = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        boolean b = connect.checkID(st, id);
        if (b == true) {
            try {
                System.out.println("Searching Employee ... !");
                connect.Connection(st);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            System.out.println("\nNot Found !...\n\nInvalid ID :( !\n");
            System.out.println("\nTry Again !...\n");
            return false;
        }
    }

    public boolean updateEmployee(int oldID, String x, String upd) throws SQLException {
        boolean a,b;
        st = "UPDATE employees SET " + upd + " = '" + x + "' WHERE id = " + oldID + ";";
            try {
                connect.Connection(st);
                a = true;
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                a = false;
            }
        st = "SELECT * FROM EMPLOYEES WHERE " + upd + " = '" + x + "';";
            try {
                System.out.println("\nUpdated Successfully...!\n");
                connect.Connection(st);
                b = true;
            } catch (SQLException ex) {                
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                b = false;
            }
            if(a == true && b == true)
                return true;
            else
                return false;
    }
    
    public boolean updateEmployee(int oldID, int x, String upd) throws SQLException {
        boolean a,b;
        st = "UPDATE employees SET " + upd + " = " + x + " WHERE id = " + oldID + ";";        
            try {
                connect.Connection(st);
                a = true;
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                a = false;
            }
            st = "SELECT * FROM EMPLOYEES WHERE " + upd + " = " + x + ";";
            try {
                System.out.println("\nUpdated Successfully...!\n");
                connect.Connection(st);
                b = true;
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                b = false;
            }
            if(a == true && b == true)
                return true;
            else
                return false;
    }

    public boolean deleteEmployee(int id) {
        st = "DELETE FROM EMPLOYEES WHERE id = " + id;
        try {
            connect.Connection(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void setAttendance(int id, String user) throws SQLException, ClassNotFoundException { 
        Date attendance = new Date(); Employee x = new Employee();
        s = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        if(connect.checkID(s, id) && x.verifyEmployeeID(user, id)) {
            try {
                st = "INSERT INTO TIMECARDS VALUES ("
                    + id + ", '"
                    + attendance.toString() + "', '', '', '', '', '');";
                connect.Connection(st);
                System.out.println("\nAttendance recorded successfully at: " + attendance);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\nFailed to record attendance :( ! \n\nPlease Try Again!");
            }
        }else {
           System.out.println("\nInvalid ID :( !\n");
           System.out.println("\nFailed to record attendance :( ! \n");
           System.out.println("\nTry Again !...\n");
        }
    } 
    
    public void setDeparture(int id, String user) throws SQLException, ClassNotFoundException { 
        Date depart = new Date(); Employee x = new Employee();
        s = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        if (connect.checkID(s, id) && x.verifyEmployeeID(user, id)) {
            s = "SELECT * FROM leave_request WHERE id = " + id + ";";
            if (connect.checkApprovals(s)) {
                st = "UPDATE timecards SET departure = '" + depart + "' WHERE id = " + id + ";";
                try {
                    connect.Connection(st);
                    System.out.println("\nDeparture recorded successfuly at: " + depart);
                } catch (SQLException ex) {
                    Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("\nFailed to record departure :( ! \n\nPlease Try Again!");
                }
            }else
                System.out.println("\nSorry Your request is disapproved !\n");
        } else {
            System.out.println("\nInvalid ID :( !\n");
            System.out.println("\nFailed to record departure :( ! \n");
            System.out.println("\nTry Again !...\n");
        }
    } 
    
    public void leaveRequest(int id, String leaveType, String user) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        s = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        if (connect.checkID(s, id) && x.verifyEmployeeID(user, id)) {
            st = "INSERT INTO leave_request VALUES ("
                    + id + ", '"
                    + leaveType + "', 'Pending');";
            try {
                connect.Connection(st);
                System.out.println("\nYour Request Was sent successfuly !");
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\nFailed to send your request :( ! \n\nPlease Try Again!");
            }
        } else {
            System.out.println("\nInvalid ID :( !\n");
            System.out.println("\nFailed to send your request :( !");
            System.out.println("\nTry Again !...\n");
        }
    }
    
    public boolean displayLeaveRequests() throws SQLException {
        st = "SELECT * FROM leave_request;";
        try {
            System.out.println("\nDisplaying Requests ... \n");
            boolean b = connect.Connection(st);
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean employeeLeaveApprovals(int id, String user) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        s = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        if (connect.checkID(s, id) && x.verifyEmployeeID(user, id)) {
            st = "SELECT * FROM leave_request WHERE id = " + id + ";";
            try {
                System.out.println("\nDisplaying Your Requests ... \n");
                boolean b = connect.Connection(st);
                return b;
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            System.out.println("\nInvalid ID :( !\n");
            System.out.println("\nTry Again !...\n");
            return false;
        }
    }
    
    public boolean employeeMissionApprovals(int id, String user) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        s = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        if (connect.checkID(s, id) && x.verifyEmployeeID(user, id)) {
            st = "SELECT * FROM mission_request WHERE id = " + id + ";";
            try {
                System.out.println("\nDisplaying Your Requests ... \n");
                boolean b = connect.Connection(st);
                return b;
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            System.out.println("\nInvalid ID :( !\n");
            System.out.println("\nTry Again !...\n");
            return false;
        }
    }
    
    public void respondToLeaveRequests(int id, String app) throws SQLException, ClassNotFoundException {
        st = "UPDATE leave_request SET request_approval = '" + app + "' WHERE id = " + id + ";";
        try {
            connect.Connection(st);
            System.out.println("\nYour respond is recorded successfuly !");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nFailed to record your respond :( ! \n\nPlease Try Again!");
        }
    }
    
    public boolean verifyEmployeeID(String username , int id) throws SQLException, ClassNotFoundException {
        st = "SELECT * FROM EMPLOYEES WHERE username = '" + username + "';";
        try {
            boolean b = false;
            int ID = connect.returnEmployeesID(st);
            if (id == ID)
                b = true;
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean verifyAssignedID(int id, int code) throws SQLException, ClassNotFoundException {
        st = "SELECT * FROM TASKS WHERE code = " + code + " AND id = " + id + ";";
        try {
            boolean b = false;
            int ID = connect.returnAssignedID(st);
            if (id == ID)
                b = true;
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean showMyTasks(int id, String user) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        s = "SELECT * FROM employees WHERE id = " + id + ";";
        if (connect.checkID(s, id) && x.verifyEmployeeID(user, id)) {
            st = "SELECT * FROM TASKS WHERE id = " + id + ";";
            try {
                System.out.println("\nDisplaying Your Tasks ... \n");
                boolean b = connect.Connection(st);
                return b;
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            System.out.println("\nInvalid ID :( !\n");
            System.out.println("\nTry Again !...\n");
            return false;
        }
    }
    
    public void missionRequest(int id, int code, String user) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        Date D = new Date();
            s = "SELECT * FROM employees WHERE id = " + id + ";";
        if (connect.checkID(s, id) && x.verifyEmployeeID(user, id) && x.verifyAssignedID(id, code)) {
            st = "INSERT INTO mission_request VALUES ("
                    + id + ", "
                    + code + ", 'Pending', '" 
                    + D.toString() + "');";
            try {
                connect.Connection(st);
                System.out.println("\nYour Request Was sent successfuly !");
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\nFailed to send your request :( ! \n\nPlease Try Again!");
            }
        } else {
            System.out.println("\nInvalid ID or Code :( !\n");
            System.out.println("\nFailed to send your request :( !");
            System.out.println("\nTry Again !...\n");
        }
        return;
    }
    
    public boolean displayMissionRequests() throws SQLException {
        st = "SELECT * FROM mission_request;";
        try {
            System.out.println("\nDisplaying Requests ... \n");
            boolean b = connect.Connection(st);
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void respondToMissionRequests(int id, int code, String app) throws SQLException, ClassNotFoundException {
        st = "UPDATE mission_request SET request_approval = '" + app + "' WHERE id = " + id + " And task_code = " + code +  ";";
        try {
            connect.Connection(st);
            System.out.println("\nYour respond is recorded successfuly !");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nFailed to record your respond :( ! \n\nPlease Try Again!");
        }
    }
    
    public void taskLogin(int id, int code, String user) throws SQLException, ClassNotFoundException { 
        Employee x = new Employee();
        s = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        if (connect.checkID(s, id) && x.verifyEmployeeID(user, id) && x.verifyAssignedID(id, code)) {
            s = "SELECT * FROM mission_request WHERE id = " + id + " And task_code = " + code + ";";
            if (connect.checkApprovals(s)) {
                x.addToTaskLog(id, code);
            }else
                System.out.println("\nSorry Your request is disapproved !\n");
        } else {
            System.out.println("\nInvalid ID :( !\n");
            System.out.println("\nFailed to record your Task Log :( ! \n");
            System.out.println("\nTry Again !...\n");
        }
    } 
    
    public void addToTaskLog(int id, int code) throws SQLException, ClassNotFoundException {
        Date D = new Date();
        st = "INSERT INTO task_log VALUES ("
                    + id + ", '"
                    + code + "', '" + D.toString() + "', 'Pending');";
            try {
                connect.Connection(st);
                System.out.println("\nYour Task Log_in recorded successfuly !");
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\nFailed to record your Task Log :( ! \n\nPlease Try Again!");
            }
    }
    
    public void taskLogout(int id, int code, String user) throws SQLException, ClassNotFoundException {
        Employee x = new Employee(); Date D = new Date(); String upd = "end_date";
        s = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        if (connect.checkID(s, id) && x.verifyEmployeeID(user, id) && x.verifyAssignedID(id, code)) {
            st = "UPDATE task_log SET " + upd + " = '" + D.toString() + "' WHERE id = " + id + " And task_code = " + code + ";";
                try {
                    connect.Connection(st);
                    System.out.println("\nYour Task Log_out recorded successfuly !");
                } catch (SQLException ex) {
                    Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("\nFailed to record your Log_out :( ! \n\nPlease Try Again!");
                }
        } else {
            System.out.println("\nInvalid ID :( !\n");
            System.out.println("\nFailed to record your Log_out :( ! \n");
            System.out.println("\nTry Again !...\n");
        }
    }
    
    @Override
    public String toString() {
        Employee ob = new Employee();
        return "\nName: " + fname + " " + midname + " " + lname + "\n" + 
                "Age: " + age + "\n" + 
                "ID: " + id + "\n" + 
                "User TyPe: " + usertype + "\n" + 
                "Employee Type: " + employeeType + "\n" +
                "Hiring Date: " + ob.getHiringDate();
    }  
}
