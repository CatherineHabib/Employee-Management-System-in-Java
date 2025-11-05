package ourproject;

// @author Catherine Habib

import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class User implements Serializable {

    protected int id;
    protected String username;
    protected String passw;
    protected String fname;
    protected String midname;
    protected String lname;
    protected String usertype;
    protected int age;
    protected Date hiringDate = new Date();
    Connection connect = new Connection(); 
    private String st;

    static MyException MyEX = new MyException("Exception");

    public User() {
    }

    public User(String user, String pass, int id, String fname, String midname, String lname, int age /*UserType/ String type*/) {

        if (id < 0) {
            this.id = MyEX.checkPositive("ID");
        } else {
            this.id = id;
        }
        this.fname = fname;
        this.midname = midname;
        this.lname = lname;

        if (age < 0) {
            this.age = MyEX.checkPositive("Age");
        } else {
            this.age = age;
        }

        if (!user.contains("@") || !user.contains(".")) {
            this.username = MyEX.checkEmail("User name");
        } else {
            this.username = user;
        }

        if (pass.length() < 8) {
            this.passw = MyEX.checkPassLength("Password");
        } else {
            this.passw = pass;
        }
    }

    @Override
    public abstract String toString();

    public void setType(/*UserType*/String type) {
        this.usertype = type;
    }

    public Date getHiringDate() {
        return this.hiringDate;
    }

    public /*UserType*/ String getUserType() {
        return this.usertype;
    }

    public boolean login(String userName, String Pass, String type) throws ClassNotFoundException {
        st = "SELECT * FROM  EMPLOYEES WHERE USERNAME = ? AND PASSW = ? AND USERTYPE = ?;";
        try {
            boolean b = connect.returnLoginData(st, userName, Pass, type);
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

