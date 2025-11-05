package ourproject;

// @author Catherine Habib

import java.sql.SQLException;


public class Leader extends User {

    public Leader() {

    }

    public Leader(String user, String pass, int id, String fname, String midname, String lname, int age) {
        super(user, pass, id, fname, midname, lname, age);
    }

    public void createTask(int code, String title, String desc, int assignedEmp, String priority, String creator, String startDate, String endDate, String hours) {
        Task x = new Task(code, title, desc, assignedEmp, priority, creator, startDate, endDate, hours);
        if (!x.addTask(x)) {
            System.out.println("\nFailed to insert ... !");
        } else {
            System.out.println(x.toString() + "\n\nAdded Successfully ... !");
        }
    }

    public void displayTasks() throws SQLException { //Calendar
        Task x = new Task();
        if (!x.displayTasks()) 
            System.out.println("\nFailed to display :( ... !");
    }
    
    public boolean searchTask(int code) throws SQLException, ClassNotFoundException {
        Task x = new Task();
        if(x.searchTask(code))
            return true;
        else
            return false;
    }

    public void updateTask(int oldCode, String val, String choice) throws SQLException, ClassNotFoundException {
        Task x = new Task();
        boolean c;
        c = x.searchTask(oldCode);
        if (c == true && x.updateTask(oldCode, val, choice)) {
            System.out.println("\n\nContinue Updating ...\n");
        } else {
            System.out.println("\nFailed to Update :( ...\nInvalid ID!\n");
            System.out.println("\nTry Again ...!\n");
        }
    }
    
    public void updateTask(int oldCode, int val, String choice) throws SQLException, ClassNotFoundException {
        Task x = new Task();
        boolean c;
        c = x.searchTask(oldCode);
        if (c == true && x.updateTask(oldCode, val, choice)) {
            System.out.println("\n\nContinue Updating ...\n");
        } else {
            System.out.println("\nTry Again ...!\n");
        }
    }
    
    public void showMissionRequests() throws SQLException {
        Employee x = new Employee();
        if (!x.displayMissionRequests()) 
            System.out.println("\nFailed to display :( ... !");
    }
    
    public boolean respondToMissionRequests(int id) throws SQLException, ClassNotFoundException {
        String st = "SELECT * FROM EMPLOYEES WHERE id = " + id + ";";
        String s = "SELECT * FROM mission_request WHERE Emp_id = " + id + ";";
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
    
    public void respondToMissionRequests(int id, int code, int i) throws SQLException, ClassNotFoundException {
        Employee x = new Employee();
            String app;
            if (i == 1)
                app = "approved";
            else
                app = "disapproved";
            x.respondToMissionRequests(id, code, app);
    }
    
    public void deleteTask(int code) throws SQLException, ClassNotFoundException {
        Task x = new Task();
        boolean c;
        c = x.searchTask(code);
        if (c == true && x.deleteTask(code)) {
           System.out.println("\nTask: "+ code + " Deleted Successfully ... !");
        } else {
            System.out.println("\nFailed to be Deleted ... !");
        }
    }
    
    @Override
    //Not Complete!!!
    public String toString() {
        Leader ob = new Leader();
        return "\nName: " + fname + " " + midname + " " + lname + "\n" + 
                "Age: " + age + "\n" + 
                "ID: " + id + "\n" + 
                "User TyPe: " + usertype + "\n" +
                "Hiring Date: " + ob.getHiringDate();
    }

}
