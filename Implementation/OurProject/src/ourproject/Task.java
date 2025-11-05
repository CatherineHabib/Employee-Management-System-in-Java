package ourproject;

// @author Catherine Habib

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task implements Serializable {

    protected int Code;
    protected String title;
    protected String TaskDesc;
    protected int AssignedEmp;
    protected String TaskPhase;
    protected String priority;
    protected String CreatorName;
    protected String startDate;
    protected String endDate;
    protected String EstimationHours;
    protected String evaluation;
    
    boolean b;
    Connection connect = new Connection(); 
    private String st,s;

    public Task() {
        
    }

    public Task(int code, String title, String desc, int assignedEmp, String priority, String creator, String startDate, String endDate, String hours) {
        this.Code = code;
        this.title = title;
        this.TaskDesc = desc;
        this.AssignedEmp = assignedEmp;
        this.TaskPhase = "Pending";
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.CreatorName = creator;
        this.EstimationHours = hours;
        this.evaluation = "Not Evaluated";
    }
    
    public boolean addTask(Task x) {
        st = "INSERT INTO TASKS VALUES (" + 
                x.Code + ", '" + 
                x.title + "', '" + 
                x.TaskDesc + "', " +
                x.AssignedEmp + ", '" + 
                x.TaskPhase + "', " + 
                x.priority + ", '" + 
                x.CreatorName + "', '" + 
                x.startDate + "', '" + 
                x.endDate + "', '" + 
                x.EstimationHours + "', '" +
                x.evaluation + "');";
        try {
            connect.Connection(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean searchTask(int code) throws SQLException, ClassNotFoundException {
        st = "SELECT * FROM TASKS WHERE code = " + code + ";";
        boolean b = connect.checkID(st, code);
        if (b == true) {
            try {
                System.out.println("Searching Tasks ... !");
                connect.Connection(st);
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            System.out.println("\nNot Found !...\n\nInvalid Code :( !\n");
            System.out.println("\nTry Again !...\n");
            return false;
        }
    }

    public boolean updateTask(int oldCode, String x, String upd) throws SQLException {
        boolean a,b;
        st = "UPDATE TASKS SET " + upd + " = '" + x + "' WHERE code = " + oldCode + ";";
            try {
                connect.Connection(st);
                a = true;
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                a = false;
            }
        st = "SELECT * FROM TASKS WHERE " + upd + " = '" + x + "';";
            try {
                System.out.println("\nUpdated Successfully...!\n");
                connect.Connection(st);
                b = true;
            } catch (SQLException ex) {                
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                b = false;
            }
            if(a == true && b == true)
                return true;
            else
                return false;
    }
    
    public boolean updateTask(int oldCode, int x, String upd) throws SQLException {
        boolean a,b;
        st = "UPDATE TASKS SET " + upd + " = " + x + " WHERE code = " + oldCode + ";";        
            try {
                connect.Connection(st);
                a = true;
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                a = false;
            }
            st = "SELECT * FROM TASKS WHERE " + upd + " = " + x + ";";
            try {
                System.out.println("\nUpdated Successfully...!\n");
                connect.Connection(st);
                b = true;
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
                b = false;
            }
            if(a == true && b == true)
                return true;
            else
                return false;
    }

    public boolean displayTasks() throws SQLException { //calendar
        st = "SELECT * FROM TASKS;";
        try {
            System.out.println("\nDisplaying TASKS ... !\n");
            boolean b = connect.Connection(st);
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteTask(int code) {
        st = "DELETE FROM TASKS WHERE code = " + code;
        try {
            connect.Connection(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public String toString() {
        
        return "\nCode: " + Code + "\n" + 
                "Title: " + title + "\n" + 
                "Creator Name: " + CreatorName + "\n" +
                "Assigned Employee: " + AssignedEmp + "\n" + 
                "Description: " + TaskDesc + "\n" +  
                "Task Phase: " + TaskPhase + "\n" +
                "Priority: " + priority + "\n" +
                "Estimation Hours: " + EstimationHours + "\n" +
                "Evaluation: " + evaluation + "\n" +
                "Start Date: " + startDate + "\n" +
                "End Date: " + endDate + "\n";
    }  
}
