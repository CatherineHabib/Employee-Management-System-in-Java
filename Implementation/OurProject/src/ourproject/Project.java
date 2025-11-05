package ourproject;

// @author Catherine Habib

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project implements Serializable {

    protected int Code;
    protected String title;
//tasks of the project
    boolean b;
    Connection connect = new Connection(); 
    private String st,s;
    
    public Project() {

    }
    
    public Project(int code, String title) {
        this.Code = code;
        this.title = title;
    }

    public boolean addProject(Project x) {
        st = "INSERT INTO PROJECTS VALUES (" + 
                x.Code + ", '" + 
                x.title + "');";
        try {
            connect.Connection(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean searchProject(int id) throws SQLException, ClassNotFoundException {
        st = "SELECT * FROM PROJECTS WHERE Pid = " + id + ";";
        boolean b = connect.checkID(st, id);
        if (b == true) {
            try {
                System.out.println("Searching Projects ... !");
                connect.Connection(st);
            } catch (SQLException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            System.out.println("\nNot Found !...\n\nInvalid ID :( !\n");
            System.out.println("\nTry Again !...\n");
            return false;
        }
    }
    
    public boolean updateProject(int oldID, String x) throws SQLException {
        boolean a,b;
        st = "UPDATE PROJECTS SET Pname = '" + x + "' WHERE Pid = " + oldID + ";";
            try {
                connect.Connection(st);
                a = true;
            } catch (SQLException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                a = false;
            }
        st = "SELECT * FROM PROJECTS WHERE Pname = '" + x + "';";
            try {
                System.out.println("\nUpdated Successfully...!\n");
                connect.Connection(st);
                b = true;
            } catch (SQLException ex) {                
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                b = false;
            }
            if(a == true && b == true)
                return true;
            else
                return false;
    }
    
    public boolean updateProject(int oldID, int x) throws SQLException {
        boolean a,b;
        st = "UPDATE PROJECTS SET Pid = " + x + " WHERE Pid = " + oldID + ";";        
            try {
                connect.Connection(st);
                a = true;
            } catch (SQLException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                a = false;
            }
            st = "SELECT * FROM PROJECTS WHERE Pid = " + x + ";";
            try {
                System.out.println("\nUpdated Successfully...!\n");
                connect.Connection(st);
                b = true;
            } catch (SQLException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                b = false;
            }
            if(a == true && b == true)
                return true;
            else
                return false;
    }
    
    public boolean displayProjects() throws SQLException {
        st = "SELECT * FROM PROJECTS;";
        try {
            System.out.println("\nDisplaying Projects ... !\n");
            boolean b = connect.Connection(st);
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteProject(int id) {
        st = "DELETE FROM PROJECTS WHERE id = " + id;
        try {
            connect.Connection(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    //Not Complete!!!
    public String toString() {
        return "\nCode: " + Code + "\n" + 
                "Title: " + title + "\n";
    }
}
