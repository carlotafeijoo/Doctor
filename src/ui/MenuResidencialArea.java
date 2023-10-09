package ui;

import java.io.*;

import java.security.MessageDigest;

import java.text.SimpleDateFormat;

import java.util.List;

import exceptions.InputException;
import POJOS.*;

public class MenuResidencialArea {

	private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	

	
	public static void main(String[] args) {

		System.out.println("WELCOME TO THE RESIDENCIAL AREA DATA BASE");
		//JDBCManager jdbcManager = new JDBCManager();

		//initialize database JDBC
		//elderlyManager = new JDBCElderlyManager(jdbcManager);
		//familyContactManager = new JDBCFamilyContactManager(jdbcManager);
		//staffManager = new JDBCStaffManager(jdbcManager);
		//tasksManager = new JDBCTasksManager(jdbcManager);
		//scheduleManager = new JDBCScheduleManager (jdbcManager);
		//initialize database JPA
	//	userManager = new JPAUserManager();
		
		mainMenu();
		
	}
	
	public static void mainMenu() {
		try {
			
			int option;
			do {
				System.out.println("MAIN MENU ");

				System.out.println("3. I am a staff member  ");
				System.out.println("4. Exit ");
				option = InputException.getInt("Introduce the number choice:  ");

				switch (option) {


					
				case 3:
					loginStaff();
					break;
					
				case 4:
					System.out.println("YOU HAVE EXIT THE RESIDENCIAL AREA DATA BASE");
					System.exit(4);
					break;

				default:
					break;
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
		
	

	
	
	
	
	
	public static void logIn() throws Exception {
		
		System.out.println("Username :");
		String username = read.readLine();
		String password = InputException.getString("Password: ");
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
				
		User u = null;
		//User u = userManager.checkPassword(username, digest);
	
		if (u == null) {
			System.out.println("User not found");
			mainMenu(); 
		}
		
		//depending on the type of user we open a different menu
		if(u!= null && u.getRole().getName().equals("Staff")) {
			Integer id=u.getId();
			
			//int staff_id = staffManager.searchStaffIdfromUId(id);
			//Staff staff = staffManager.searchStaffbyId(staff_id);
			Staff staff = null;
			System.out.println(staff);
			System.out.println("Login successful!");
			staffMenu(u.getId());
			
		}
		

	
		}
	
	
	private static void staffMenu(int User_id) {
		try {

			int choice;
			do {
				System.out.println("1.Update information. ");
				System.out.println("2.Register new task. ");
				System.out.println("3.List all the tasks. ");
				System.out.println("4.Load new staff members. ");
				System.out.println("5.Export staff members.  ");
				System.out.println("6.Back.  ");

				choice = InputException.getInt("Introduce your choice: ");

				switch (choice) {
				
				case 1:
					//int staff_id = staffManager.searchStaffIdfromUId(User_id);
					Staff staffToUpdate = null;
					//Staff staffToUpdate =staffManager.searchStaffbyId(staff_id);
					if(staffToUpdate !=null) {
						int newPhone = InputException.getInt("Enter your new phone number: ");
						staffToUpdate.setPhone(newPhone);
						String newAddress = InputException.getString("Enter your new address: ");
						staffToUpdate.setAddress(newAddress);
						//staffManager.updateStaffMemberInfo(staffToUpdate);
						 System.out.println("Information updated successfully! ");
					} else {
					    System.out.println("Staff update fail.");
					}
				    break;
					
				case 2:
					//int staffToAssignNewTask_id = staffManager.searchStaffIdfromUId(User_id);
					//addTask(staffToAssignNewTask_id);
					System.out.println("Task added sucessfully!");
					break;
					
				case 3:
					//int staffAllTask_id = staffManager.searchStaffIdfromUId(User_id);
					//List <Task> tasksList = tasksManager.getListOfTasks(staffAllTask_id);
					//System.out.println("List of tasks: " +tasksList);
					break;
					
				case 4:
					mainMenu();
					break;
					
				default:
					break;
					
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addTask(int staffToAssignNewTask_id) throws Exception {

		System.out.println("Input the information of the new task: ");
 
		String description = InputException.getString("Description: ");
		
		Task task = new Task(description,staffToAssignNewTask_id );
 
		//tasksManager.addTask(task);
	}
    
	
	private static void loginStaff() throws Exception {

		System.out.println("1. Register");
		System.out.println("2. Log in ");
		System.out.println("3. Exit");
		int choice = InputException.getInt(" Introduce the number of your choice: ");
	
		switch (choice) {
			case 1:
				// Call method REGISTER
				registerStaff();
				loginStaff();
				break;
				
			case 2:
				// LOG IN as staff member
				logIn();
				break;
	
			case 3:
				// EXIT
				mainMenu();
				break;
				
			default:
				break;
		}
	
	}
	
	public static void registerStaff() throws Exception {
		
		System.out.println("Input information: ");
	
		String name = InputException.getString("Name: ");
		
		String field = null;
		int choice;
		do {

			System.out.println("1.Carer");
			System.out.println("2.Cleaner");
			System.out.println("3.Chef");
			System.out.println("4.Animator");

			choice = InputException.getInt("Chose field: ");
			switch (choice) {

			case 1:
				field = "Carer";
				break;
				
			case 2:
				field = "Cleaner";
				break;
				
			case 3:
				field = "Chef";
				break;
				
			case 4:
				field = "Animator";
				break;
				
			default:
				break;
			}
		} while (choice < 1 || choice > 4);
		
		String address = InputException.getString("Address: ");
		String email = InputException.getString("Email: ");
		System.out.println("Your email adress will be used as your username");
		Integer phone = InputException.getInt("Phone: ");
		System.out.println("Enter the year of birth:");
        int year = Integer.parseInt(read.readLine());

        System.out.println("Enter the month of birth:");
        int month = Integer.parseInt(read.readLine());

        System.out.println("Enter the day of birth:");
        int day = Integer.parseInt(read.readLine());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dobStr = String.format("%04d/%02d/%02d", year, month, day);
        java.util.Date utilDate = dateFormat.parse(dobStr);
        java.sql.Date dob = new java.sql.Date(utilDate.getTime());

		String username = email;
		String password = InputException.getString("Password: ");
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
	
		// CREATE STAFF AND ADD TO JPA
		User u = new User(username, digest);
		Role role = null;
		//Role role = userManager.getRole("Staff");
		u.setRole(role);
		role.addUser(u);
		//userManager.newUser(u);
	
		// CREATE STAFF AND ADD TO JDBD
		Staff staff = new Staff(name, phone,dob, address, field, email);
		staff.setField(field); 
		
		//staffManager.addStaffMember(staff);
		System.out.println("Register sucessfull!");
		
	}


}
