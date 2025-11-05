
package ourproject;

// @author Catherine Habib

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Scanner input = new Scanner(System.in);
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        Employee employ = new Employee();
        Leader lead = new Leader();
        Admin admin = new Admin();
        int ch = 1, c = 1, h = 1, o = 1, i = 1, e = 1, f = 1, j = 1, k = 1, l = 1, m = 1;
        String userName, Pass, Fname, Lname, Pname, hours, midname, employeeType, leaveType, title, phase, var = "y", creator, desc, start, end;
        String []choices = {"fname", "midname", "lname", "id", "usertype", "age", "username", "passw"};
        String []Taskchoices = {"code", "title", "description", "id", "task_phase", "Pid", "creator", "start_date", "end_date", "estimation_hours"};
        int id, oldID, age, code, pID;
        System.out.println("\n\nWelcome ... !\n\n");
        while(ch!=0) {
            System.out.println("\n\t ________________________________\n\t"
                        + "| Enter 1: For Admins.     |\n\t| Enter 2: For Employees.  |  \n\t| Enter 0: To Exit.\t    |"
                        + "\n\t -------------------------------\n\t");
            System.out.print("Choose a number: ");
            ch = input.nextInt();
            switch(ch) {
                case 0:
                    break;
                case 1:
                    System.out.print("\n\nWelcome Admin ... !\n\nUser Name : ");
                    userName = input.next();
                    System.out.print("Password  : ");
                    Pass = input.next();
                    employeeType = "Admin";
                    if (admin.login(userName, Pass, employeeType)) {

                        while (c != 0) {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("\n\t ________________________________\n\t"
                                    + "| Enter 1: Add New Employee.     |\n\t| Enter 2: Display All Employees.   |\n\t| Enter 3: Search an Employee.          |\n\t| Enter 4: Update an Employee.    |\n\t| Enter 5: Show & Respond to leave requests.      |"
                                    + "\n\t| Enter 6: Delete an Employee.|\n\t| Enter 7: Add New Project.  |\n\t| Enter 8: Display All Projects.     |\n\t| Enter 9: Search a Project.|\n\t| Enter 10: Update a Project.     |"
                                    + "\n\t| Enter 11: Update Task Phase.|\n\t| Enter 12: Delete a Project.     |"
                                    + "\n\t| Enter 0: To Exit The Admin's Menu (Log out).\t|"
                                    + "\n\t -------------------------------\n\t");
                            System.out.print("Choose a number: ");
                            c = input.nextInt();

                            switch (c) {
                                case 0:
                                    break;
                                case 1:
                                    System.out.println("Enter Employee Info ... ");
                                    System.out.print("Emloyee First Name : ");
                                    Fname = input.next();
                                    System.out.print("Emloyee Middle Name : ");
                                    midname = input.next();
                                    System.out.print("Emloyee Last Name : ");
                                    Lname = input.next();
                                    System.out.print("Emloyee ID : ");
                                    id = input.nextInt();
                                    System.out.print("Emloyee Age : ");
                                    age = input.nextInt();
                                    System.out.print("Emloyee Type : ");
                                    employeeType = input.next();
                                    System.out.print("Username : ");
                                    userName = input.next();
                                    System.out.print("Password : ");
                                    Pass = input.next();
                                    admin.addNewEmployee(userName, Pass, id, Fname, midname, Lname, age, employeeType);
                                    break;
                                case 2:
                                    try {
                                        admin.displayEmployees();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 3:
                                    System.out.print("\nSearch for Employee ...!\nEnter Employee ID : ");
                                    id = input.nextInt();
                                    admin.searchForEmployee(id);
                                    break;
                                case 4:
                                    System.out.print("\nUpdate Employee's info ...!\n\nEnter Employee's ID : ");
                                    oldID = input.nextInt();
                                    if (admin.searchForEmployee(oldID)) {
                                        while (o != 0) {
                                            System.out.println("\n\t ________________________________\n\t"
                                                    + "| Enter 1: First Name.     |\n\t| Enter 2: Middle Name.   |\n\t| Enter 3: Last Name.          |\n\t| Enter 4: ID.    |\n\t| Enter 5: Age.      |"
                                                    + "\n\t| Enter 6: Type.       |\n\t| Enter 7: Username.  |\n\t| Enter 8: Password.  |\n\t| Enter 0: To Finish The Update.      |"
                                                    + "\n\t -------------------------------\n\t");
                                            System.out.print("\nPlease enter the no. of what needs to be updated : ");
                                            o = input.nextInt();
                                            switch (o) {
                                                case 0:
                                                    break;
                                                case 1:
                                                    System.out.println("\nEnter Employee's Updated Info ... ");
                                                    System.out.print("\nFirst Name : ");
                                                    Fname = input.next();
                                                    admin.updateEmployee(oldID, Fname, choices[0]);
                                                    break;
                                                case 2:
                                                    System.out.println("\nEnter Employee's Updated Info ... ");
                                                    System.out.print("\nMiddle Name : ");
                                                    midname = input.next();
                                                    admin.updateEmployee(oldID, midname, choices[1]);
                                                    break;
                                                case 3:
                                                    System.out.println("\nEnter Employee's Updated Info ... ");
                                                    System.out.print("\nLast Name : ");
                                                    Lname = input.next();
                                                    admin.updateEmployee(oldID, Lname, choices[2]);
                                                    break;
                                                case 4:
                                                    System.out.println("\nEnter Employee's Updated Info ... ");
                                                    System.out.print("\nID : ");
                                                    id = input.nextInt();
                                                    admin.updateEmployee(oldID, id, choices[3]);
                                                    break;
                                                case 5:
                                                    System.out.println("\nEnter Employee's Updated Info ... ");
                                                    System.out.print("\nAge : ");
                                                    age = input.nextInt();
                                                    admin.updateEmployee(oldID, age, choices[4]);
                                                    break;
                                                case 6:
                                                    System.out.println("\nEnter Employee's Updated Info ... ");
                                                    System.out.print("\nEmloyee Type : ");
                                                    employeeType = input.next();
                                                    admin.updateEmployee(oldID, employeeType, choices[5]);
                                                    break;
                                                case 7:
                                                    System.out.println("\nEnter Employee's Updated Info ... ");
                                                    System.out.print("\nUsername : ");
                                                    userName = input.next();
                                                    admin.updateEmployee(oldID, userName, choices[6]);
                                                    break;
                                                case 8:
                                                    System.out.println("\nEnter Employee's Updated Info ... ");
                                                    System.out.print("\nPassword : ");
                                                    Pass = input.next();
                                                    admin.updateEmployee(oldID, Pass, choices[7]);
                                                    break;
                                                default:
                                                    System.out.println("\nEnter a vaild Option :( !");

                                            }
                                        }
                                    }
                                    break;
                                case 5:
                                    admin.showLeaveRequests();
                                    System.out.print("\nChoose an ID to respond to its request : ");
                                    id = input.nextInt();
                                    if (admin.respondToLeaveRequests(id)) {
                                        while (i != 0) {
                                            System.out.println("\n\t ________________________________\n\t"
                                                    + "| Enter 1: Approve Request.     |\n\t| Enter 2: Disapprove Request.  |  \n\t| Enter 0: To Exit This Menu.\t    |"
                                                    + "\n\t -------------------------------\n\t");
                                            System.out.print("Choose a number: ");
                                            i = input.nextInt();
                                            switch (i) {
                                                case 0:
                                                    break;
                                                case 1:
                                                    admin.respondToLeaveRequests(id, 1);
                                                    break;
                                                case 2:
                                                    admin.respondToLeaveRequests(id, 2);
                                                    break;
                                                default:
                                                    System.out.println("\nEnter a vaild Option :( !");
                                            }
                                        }
                                    }
                                    break;
                                case 6:
                                    System.out.print("\nDelete Employee info ...!\nEnter Employee ID : ");
                                    id = input.nextInt();
                                    admin.deleteEmployee(id);
                                    break;
                                case 7:
                                    System.out.println("Add New Project ... ");
                                    System.out.print("Project Code : ");
                                    id = input.nextInt();
                                    System.out.print("Project Title : ");
                                    title = input.next();
                                    admin.addNewProject(id, title);
                                    break;
                                case 8:
                                    try {
                                        admin.displayProjects();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 9:
                                    System.out.print("\nSearch for Project ...!\nEnter Project ID : ");
                                    id = input.nextInt();
                                    admin.searchProject(id);
                                    break;
                                case 10:
                                    System.out.print("\nUpdate Project's info ...!\n\nEnter Project's ID : ");
                                    oldID = input.nextInt();
                                    if (admin.searchProject(oldID)) {
                                        while (h != 0) {
                                            System.out.println("\n\t ________________________________\n\t"
                                                    + "| Enter 1: Project Name.     |\n\t| Enter 2: Project ID.  |  \n\t| Enter 0: To Exit This Menu.\t    |"
                                                    + "\n\t -------------------------------\n\t");
                                            System.out.print("Choose a number: ");
                                            h = input.nextInt();
                                            switch (h) {
                                                case 0:
                                                    break;
                                                case 1:
                                                    System.out.println("\nEnter Project's Updated Info ... ");
                                                    System.out.print("\nProject Name : ");
                                                    Pname = input.next();
                                                    admin.updateProject(oldID, Pname);
                                                    break;
                                                case 2:
                                                    System.out.println("\nEnter Project's Updated Info ... ");
                                                    System.out.print("\nProject ID : ");
                                                    id = input.nextInt();
                                                    admin.updateProject(oldID, id);
                                                    break;
                                                default:
                                                    System.out.println("\nEnter a vaild Option :( !");
                                            }
                                        }
                                    }
                                    break;
                                case 11:
                                    System.out.print("\nUpdate A Task's phase using Its Code.... ");
                                    while(var.equals("y")) {
                                        System.out.print("\nTask Code : ");
                                        id = input.nextInt();
                                        System.out.print("\nUpdated Task phase : ");
                                        phase = input.next();
                                        admin.updatePhases(id, phase);
                                        System.out.print("\nUpdate Another Task's Phase ? (y/n) : ");
                                        var = input.next();
                                    }
                                    break;
                                case 12:
                                    System.out.print("\nDelete Project ...!\nEnter Project ID : ");
                                    id = input.nextInt();
                                    admin.deleteProject(id);
                                    break;
                                default:
                                    System.out.println("Enter a vaild Option :( !");

                            }//switch
                        }//while
                    } else 
                        System.out.println("\nWrong Username or Password :( ! \nTry Again...");
                    break;
                case 2:
                    while (f != 0) {
                        System.out.println("\n\t ________________________________\n\t"
                                + "| Enter 1: Project Leader.     |\n\t| Enter 2: Employee.   |\n\t| Enter 0: To Exit This Menu.      |"
                                + "\n\t -------------------------------\n\t");
                        System.out.print("\nPlease enter the no. of your state : ");
                        f = input.nextInt();
                        switch (f) {                            
                            case 0:
                                break;
                            case 1:
                                System.out.print("\n\nWelcome Leader ... !\n\nUser Name : ");
                                userName = input.next();
                                System.out.print("Password  : ");
                                Pass = input.next();
                                employeeType = "Leader";
                                if (lead.login(userName, Pass, employeeType)) {
                                    while (k != 0) {
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println("\n\t ________________________________\n\t"
                                                + "| Enter 1: Create New Task.     |\n\t| Enter 2: Display All Tasks.   |\n\t| Enter 3: Search a Task.          |\n\t| Enter 4: Update a Task.    |\n\t| Enter 5: Show & Respond to mission requests.      |"
                                                + "\n\t| Enter 6: Delete a Task.     |"
                                                + "\n\t| Enter 0: To Exit The Leader's Menu (Log out).\t|"
                                                + "\n\t -------------------------------\n\t");
                                        System.out.print("Choose a number: ");
                                        k = input.nextInt();

                                        switch (k) {
                                            case 0:
                                                break;
                                            case 1:
                                                System.out.println("Enter Task Info ... ");
                                                System.out.print("Project ID : ");
                                                pID = input.nextInt();                                                 
                                                System.out.print("Task Title : ");
                                                title = scan.next();
                                                System.out.print("Task Code : ");
                                                code = input.nextInt();                                               
                                                System.out.print("Description : ");
                                                desc = scan.next();
                                                System.out.print("Creator Name : ");
                                                creator = scan.next();
                                                System.out.print("Assigned Employee ID : ");
                                                id = input.nextInt();
                                                System.out.print("Start Date : ");
                                                start = scan.next();
                                                System.out.print("End Date : ");
                                                end = scan.next();
                                                System.out.print("Estimation Hours : ");
                                                hours = scan.next();
                                                lead.createTask(code, title, desc, id, start, creator, start, end, hours);
                                                break;
                                            case 2:
                                                try {
                                                    lead.displayTasks();
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                break;
                                            case 3:
                                                System.out.print("\nSearch for a Task ...!\nEnter Task Code : ");
                                                code = input.nextInt();
                                                lead.searchTask(code);
                                                break;
                                            case 4:
                                                System.out.print("\nUpdate a Task's info ...!\n\nEnter Task's Code : ");
                                                oldID = input.nextInt();
                                                if (lead.searchTask(oldID)) {
                                                    while (l != 0) {
                                                        System.out.println("\n\t ________________________________\n\t"
                                                                + "| Enter 1: Project ID.     |\n\t| Enter 2: Task Title.   |\n\t| Enter 3: Task Code.          |\n\t| Enter 4: Description.    |\n\t| Enter 5: Creator Name.      |"
                                                                + "\n\t| Enter 6: Assigned Employee.       |\n\t| Enter 7: Start Date.  |\n\t| Enter 8: End Date.  |\n\t| Enter 9: Estimation Hours.  |\n\t| Enter 0: To Finish The Update.      |"
                                                                + "\n\t -------------------------------\n\t");
                                                        System.out.print("\nPlease enter the no. of what needs to be updated : ");
                                                        l = input.nextInt();
                                                        switch (l) {
                                                            case 0:
                                                                break;
                                                            case 1:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nProject ID : ");
                                                                pID = input.nextInt();
                                                                lead.updateTask(oldID, pID, Taskchoices[5]);
                                                                break;
                                                            case 2:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nTask Title : ");
                                                                title = scan.next();
                                                                lead.updateTask(oldID, title, Taskchoices[1]);
                                                                break;
                                                            case 3:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nTask Code : ");
                                                                code = input.nextInt();
                                                                lead.updateTask(oldID, code, Taskchoices[0]);
                                                                break;
                                                            case 4:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nDescription : ");
                                                                desc = scan.next();
                                                                lead.updateTask(oldID, desc, Taskchoices[2]);
                                                                break;
                                                            case 5:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nCreator Name : ");
                                                                creator = scan.next();
                                                                lead.updateTask(oldID, creator, Taskchoices[6]);
                                                                break;
                                                            case 6:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nAssigned Employee ID : ");
                                                                id = input.nextInt();
                                                                lead.updateTask(oldID, id, Taskchoices[3]);
                                                                break;
                                                            case 7:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nStart Date : ");
                                                                start = scan.next();
                                                                lead.updateTask(oldID, start, Taskchoices[7]);
                                                                break;
                                                            case 8:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nEnd Date : ");
                                                                end = scan.next();
                                                                lead.updateTask(oldID, end, Taskchoices[8]);
                                                                break;
                                                            case 9:
                                                                System.out.println("\nEnter Task's Updated Info ... ");
                                                                System.out.print("\nEstimation Hours : ");
                                                                hours = scan.next();
                                                                lead.updateTask(oldID, hours, Taskchoices[9]);
                                                                break;
                                                            default:
                                                                System.out.println("\nEnter a vaild Option :( !");

                                                        }
                                                    }
                                                }
                                                break;
                                            case 5:
                                                lead.showMissionRequests();
                                                System.out.print("\nChoose an ID to respond to its request : ");
                                                id = input.nextInt();
                                                System.out.print("\nEnter the code of the requested Task : ");
                                                code = input.nextInt();
                                                if (lead.respondToMissionRequests(id)) {
                                                    while (m != 0) {
                                                        System.out.println("\n\t ________________________________\n\t"
                                                                + "| Enter 1: Approve Request.     |\n\t| Enter 2: Disapprove Request.  |  \n\t| Enter 0: To Exit This Menu.\t    |"
                                                                + "\n\t -------------------------------\n\t");
                                                        System.out.print("Choose a number: ");
                                                        m = input.nextInt();
                                                        switch (m) {
                                                            case 0:
                                                                break;
                                                            case 1:
                                                                lead.respondToMissionRequests(id, code, 1);
                                                                break;
                                                            case 2:
                                                                lead.respondToMissionRequests(id, code, 1);
                                                                break;
                                                            default:
                                                                System.out.println("\nEnter a vaild Option :( !");
                                                        }
                                                    }
                                                }
                                                break;
                                            case 6:
                                                System.out.print("\nDelete Task info ...!\nEnter Task Code : ");
                                                code = input.nextInt();
                                                lead.deleteTask(code);
                                                break;
                                            default:
                                                System.out.println("Enter a vaild Option :( !");
                                        }//switch
                                    }//while
                                } else
                                    System.out.println("\nWrong Username or Password :( !\n\nTry Again...");
                                break;
                            case 2:
                                System.out.print("\n\nWelcome Employee ... !\n\nUser Name : ");
                                userName = input.next();
                                System.out.print("Password  : ");
                                Pass = input.next();
                                employeeType = "Employee";
                                if (employ.login(userName, Pass, employeeType)) {

                                    while (j != 0) {
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println("\n\t ________________________________\n\t"
                                                + "| Enter 1: Record Your Attendance.     |\n\t| Enter 2: Display Your Tasks.   |\n\t| Enter 3: Request permission to a mission.          |\n\t| Enter 4: Show mission request approvals.    |\n\t| Enter 5: Start working on a Task.      |"
                                                + "\n\t| Enter 6: Log out from Task.|\n\t| Enter 7: Request to leave.  |\n\t| Enter 8: Show leave request approvals.     |\n\t| Enter 9: Record Your Departure.     |"
                                                + "\n\t| Enter 0: To Exit The Employee's Menu (Log out).\t|"
                                                + "\n\t -------------------------------\n\t");
                                        System.out.print("Choose a number: ");
                                        j = input.nextInt();

                                        switch (j) {
                                            case 0:
                                                break;
                                            case 1:
                                                System.out.print("\nEnter your ID : ");
                                                id = input.nextInt();
                                                employ.setAttendance(id, userName);
                                                break;
                                            case 2:
                                                System.out.print("\nDisplay Your Tasks ...!\nEnter Your ID : ");
                                                id = input.nextInt();
                                                if(employ.showMyTasks(id, userName))
                                                    System.out.println("\nFailed to display :( ... !");
                                                break;
                                            case 3:
                                                System.out.print("\nEnter your ID : ");
                                                id = input.nextInt();
                                                System.out.print("\nMission Code : ");
                                                code = input.nextInt();
                                                employ.missionRequest(id, code, userName);
                                                break;
                                            case 4:
                                                System.out.print("\nEnter your ID : ");
                                                id = input.nextInt();
                                                if(!employ.employeeMissionApprovals(id, userName))
                                                    System.out.println("\nFailed to display :( ... !");
                                                break;
                                            case 5:
                                                System.out.print("\nEnter your ID : ");
                                                id = input.nextInt();
                                                System.out.print("\nTask Code : ");
                                                code = input.nextInt();
                                                employ.taskLogin(id, code, userName);
                                                break;
                                            case 6:
                                                System.out.print("\nEnter your ID : ");
                                                id = input.nextInt();
                                                System.out.print("\nTask Code : ");
                                                code = input.nextInt();
                                                employ.taskLogout(id, code, userName);
                                                break;
                                            case 7:
                                                System.out.print("\nEnter your ID : ");
                                                id = input.nextInt();
                                                System.out.print("\nReason to leaving (Type of vacation): ");
                                                leaveType = scan.next();
                                                employ.leaveRequest(id, leaveType, userName);
                                                break;
                                            case 8:
                                                System.out.print("\nEnter your ID : ");
                                                id = input.nextInt();
                                                if(!employ.employeeLeaveApprovals(id, userName))
                                                    System.out.println("\nFailed to display :( ... !");
                                                break;
                                            case 9:
                                                System.out.print("\nEnter your ID : ");
                                                id = input.nextInt();
                                                employ.setDeparture(id, userName);
                                                break;
                                            default:
                                                System.out.println("Enter a vaild Option :( !");
                                        }//switch
                                    }//while
                                } else
                                    System.out.println("\nWrong Username or Password :( !\n\nTry Again...");
                                break;
                            default:
                                System.out.println("Enter a vaild Option :( !");
                        }//switch
                    }//while
                    break;
                default:
                    System.out.println("Enter a vaild Option :( !");
            }//switch
        }//while
    }//main method
}
