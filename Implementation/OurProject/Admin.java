package ourproject;

// @author Catherine Habib

import java.sql.SQLException;


public class Admin extends User {

    public Admin() {

    }

    public Admin(String user, String pass, int id, String fname, String midname, String lname, int age) {
        super(user, pass, id, fname, midname, lname, age);
    }

    public void addNewEmployee(String user, String pass, int id, String fname, String midname, String lname, int age, String employeeType) {
        Employee x = new Employee(user, pass, id, fname, midname, lname, age, employeeType);
        if (!x.addEmployee(x)) {
            System.out.println("\nFailed to insert ... !");
        } else {
            System.out.println(x.toString() + "\n\nAdded Successfully ... !");
        }
    }

    public void displayEmployees() throws SQLException {
        Employee x = new Employee();
        if (!x.displayAllEmployees()) 
            System.out.println("\nFailed to display :( ... !");
    }
    
    public void showLeaveRequests() throws SQLException {
        Employee x = new Employee();
        if (!x.displayLeaveRequests()) 
            System.out.println("\nFailed to display :( ... !");
    }

    public boolean respondToLeaveRequests(int id) throws SQLException, ClassNotFoundException {
        String st = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        String s = "SELECT * FROM leave_request WHERE id = " + id + ";";
        boolean b = connect.checkID(st, id);
        boolean c = connect.checkID(s, id);
        if (b == true && c == true) {
            return true;
        } else {
            System.out.println("\nNot Found !...\n\nInvalid ID :( !\n");
            System.out.println("\nTry Again !...\n");
            return false;
        }
    }
    
    public void respondToLeaveRequests(int id, int i) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
            String app;
            if (i == 1)
                app = "approved";
            else
                app = "disapproved";
            x.respondToLeaveRequests(id, app);
    }
    
    public boolean searchForEmployee(int id) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        if(x.searchEmployee(id))
            return true;
        else
            return false;
    }

    public void updateEmployee(int oldID, String val, String choice) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        boolean c;
        c = x.searchEmployee(oldID);
        if (c == true && x.updateEmployee(oldID, val, choice)) {
            System.out.println("\n\nContinue Updating ...\n");
        } else {
            System.out.println("\nFailed to Update :( ...\nInvalid ID!\n");
            System.out.println("\nTry Again ...!\n");
        }
    }
    
    public void updateEmployee(int oldID, int val, String choice) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        boolean c;
        c = x.searchEmployee(oldID);
        if (c == true && x.updateEmployee(oldID, val, choice)) {
            System.out.println("\n\nContinue Updating ...\n");
        } else {
            System.out.println("\nTry Again ...!\n");
        }
    }

    public void deleteEmployee(int Id) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
        boolean c;
        c = x.searchEmployee(Id);
        if (c == true && x.deleteEmployee(Id)) {
           System.out.println("\nEmployee: "+ Id + " Deleted Successfully ... !");
        } else {
            System.out.println("\nFailed to be Deleted ... !");
        }
    }

    public void addNewProject(int code, String title) {
        Project x = new Project(code, title);
        if (!x.addProject(x)) {
            System.out.println("\nFailed to insert ... !");
        } else {
            System.out.println(x.toString() + "\n\nAdded Successfully ... !");
        }
    }
    
    public boolean searchProject(int id) throws SQLException, ClassNotFoundException {
        Project p = new Project();
        if(p.searchProject(id))
            return true;
        else
            return false;
    }
    
    public void updateProject(int oldID, String val) throws SQLException, ClassNotFoundException {
        Project x = new Project();
        boolean c;
        c = x.searchProject(oldID);
        if (c == true && x.updateProject(oldID, val)) {
            System.out.println("\n\nContinue Updating ...\n");
        } else {
            System.out.println("\nFailed to Update :( ...\nInvalid ID!\n");
            System.out.println("\nTry Again ...!\n");
        }
    }
    
    public void updateProject(int oldID, int val) throws SQLException, ClassNotFoundException {
        Project x = new Project();
        boolean c;
        c = x.searchProject(oldID);
        if (c == true && x.updateProject(oldID, val)) {
            System.out.println("\n\nContinue Updating ...\n");
        } else {
            System.out.println("\nTry Again ...!\n");
        }
    }
    
    public void displayProjects() throws SQLException {
        Project x = new Project();
        if (!x.displayProjects()) 
            System.out.println("\nFailed to display :( ... !");
    }
    
    public void deleteProject(int id) throws SQLException, ClassNotFoundException {
        Project x = new Project();
        boolean c;
        c = x.searchProject(id);
        if (c == true && x.deleteProject(id)) {
           System.out.println("\nProject: "+ id + " Deleted Successfully ... !");
        } else {
            System.out.println("\nFailed to be Deleted ... !");
        }
    }
    
    public void updatePhases(int code, String val) throws SQLException, ClassNotFoundException {
        Task x = new Task();
        boolean c;
        c = x.searchTask(code);
        if (c == true && x.updateTask(code, val, "task_phase")) {
            System.out.println("\n\nContinue Updating ...\n");
        } else {
            System.out.println("\nTry Again ...!\n");
        }
    } 
    
    @Override
    //Not Complete!!!
    public String toString() {
        Admin ob = new Admin();
        return "\nName: " + fname + " " + midname + " " + lname + "\n" + 
                "Age: " + age + "\n" + 
                "ID: " + id + "\n" + 
                "User TyPe: " + usertype + "\n" +
                "Hiring Date: " + ob.getHiringDate();
    }

}
