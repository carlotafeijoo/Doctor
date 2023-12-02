package ui;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import exceptions.InputException;
import POJOS.*;

public class DoctorMenuResidencialArea {
	static OutputStream os = null;
	static PrintWriter pw = null;

	static BufferedReader br = null;
	static Socket so = null;
	private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	//consola

	public static void main(String[] args) throws IOException {

		System.out.println("WELCOME TO THE RESIDENCIAL AREA DATA BASE");

		so = new Socket("localhost", 9009);
		// el cliente lee lineas pero tambien manda
		br = new BufferedReader(new InputStreamReader(so.getInputStream())); //sockets cliente
		os = so.getOutputStream();
		pw = new PrintWriter(os, true);

		mainMenu();

	}

	private static void releaseResources(PrintWriter printWriter, BufferedReader br, OutputStream outputStream,
			Socket socket) {
		printWriter.close();
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void mainMenu() {
		try {

			int option;
			do {
				System.out.println("MAIN MENU ");
				System.out.println("1. Enter  ");
				System.out.println("2. Exit ");
				option = InputException.getInt("Introduce the number choice:  ");

				switch (option) {

				case 1:
					logindoctor();
					break;

				case 2:
					System.out.println("YOU HAVE EXIT THE RESIDENCIAL AREA DATA BASE");
					pw.println("stop");
					releaseResources(pw, br, os, so);
					
					System.exit(2);
					break;

				default:
					break;
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void logindoctor() throws Exception {

		System.out.println("1. Register");
		System.out.println("2. Log in ");
		System.out.println("3. Exit");
		int choice = InputException.getInt(" Introduce the number of your choice: ");

		switch (choice) {
		case 1:
			// Call method REGISTER
			registerdoctor();
			logindoctor();
			break;

		case 2:
			// LOG IN as doctor member
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
	
	public static void registerdoctor() throws Exception {

		System.out.println("Input information: ");

		String name = InputException.getString("Name: ");

		String field = null;
		int choice;
		do {
			
			System.out.println("1.Cardiologist");
			System.out.println("2.Generalist Physician");

			choice = InputException.getInt("Chose field: ");
			switch (choice) {

			case 1:
				field = "Cardiologist";
				break;

			case 2:
				field = "Generalist Physician";
				break;

			default:
				break;
			}
		} while (choice < 1 || choice > 2);

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

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dobStr = String.format("%04d-%02d-%02d", year, month, day);
		java.util.Date utilDate = dateFormat.parse(dobStr);
		java.sql.Date dob = new java.sql.Date(utilDate.getTime());

		String username = email;
		Doctor doctor = new Doctor(name, phone, dob, address, email);

		String password = InputException.getString("Password: ");

		pw.println("addDoctor");
		pw.println(username);
		pw.println(password);
		pw.println(doctor.toString());

		br.readLine();

	}

	public static void logIn() throws Exception {

		
		String username = InputException.getString("Username: ");
		
		String password = InputException.getString("Password: ");
		
		/*User u1 = null;
		System.out.println("User null " +u1.toString());*/
		
		pw.println("checkPassword");
		
		pw.println(username);
		pw.println(password);

		String role_text=br.readLine();
		String user_text = br.readLine();
		
		User u = null;

		if(user_text.equalsIgnoreCase("error")) {
			System.out.println("User not found");
			mainMenu();
		}
		else {
			u = new User(user_text);
			u.setRole(new Role(role_text));
		}
		
		/*ORIGINAL
		User u = new User(user_text);
		u.setRole(new Role(role_text));*/
		
		/*if(u.getUsername()=="error") {
			u = null;
			System.out.println("User not found");
			mainMenu();
		}else if(u.getUsername()!="error"){
			//u = new User(user_text);
			u.setRole(new Role(role_text));
		}*/
		
		//TODO checkear esto: es null? es vacio? hay que mover el if?
		/*if (u == null) {
			System.out.println("User not found");
			mainMenu();
		}*/

		// depending on the type of user we open a different menu
		if (u != null && u.getRole().getName().equals("Doctor")) {
			Integer id = u.getId();
			
			pw.println("searchDoctorIdfromUId");
			pw.println(""+id);
			String doctor_id_text=br.readLine();
			int doctor_id=Integer.parseInt(doctor_id_text);

			pw.println("searchDoctorbyId");
			pw.println(doctor_id);
			String doctor_text = br.readLine();
			Doctor doctor = new Doctor(doctor_text);
			
			System.out.println(doctor);
			System.out.println("Login successful!");
			doctorMenu(u.getId());

		}

	}

	private static void doctorMenu(int User_id) {
		try {

			int choice;
			do {
				System.out.println("1.Update information. ");
				System.out.println("2.Register new task. ");
				System.out.println("3.List all the tasks. ");
				System.out.println("4.Back.  ");

				choice = InputException.getInt("Introduce your choice: ");

				switch (choice) {

				case 1:	//UPDATE INFORMATION
					try {
						pw.println("searchDoctorIdfromUId");
						pw.println(User_id);
						String doctor_id_string = br.readLine();
						int doctor_id = Integer.parseInt(doctor_id_string);
						//en vez de crear un obejto doctor nuevo con todos los datos de ese doctor que queremos actualizar 
						//podríamos simplemente pasa por el socket desde el cliente los datos que queremos actualizar con la id del doctor 
						//y una vez tengamos esos datos en el server actualizar directamente los datos de la db de ese doctor

						pw.println("searchDoctorbyId");
						pw.println(doctor_id);
						String doctor_string = br.readLine();
						Doctor doctorToUpdate = new Doctor(doctor_string);
					
						if (doctorToUpdate != null) {
							
							int newPhone = InputException.getInt("Enter your new phone number: ");
							doctorToUpdate.setPhone(newPhone);
							String newAddress = InputException.getString("Enter your new address: ");
							doctorToUpdate.setAddress(newAddress);
							System.out.println("Enter the year of birth:");
							int year = Integer.parseInt(read.readLine());
							System.out.println("Enter the month of birth:");
							int month = Integer.parseInt(read.readLine());
							System.out.println("Enter the day of birth:");
							int day = Integer.parseInt(read.readLine());
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String dobStr = String.format("%04d-%02d-%02d", year, month, day);
							java.util.Date utilDate = dateFormat.parse(dobStr);
							java.sql.Date dob = new java.sql.Date(utilDate.getTime());
							doctorToUpdate.setDob(dob);
							
							pw.println("updateDoctorMemberInfo");
							pw.println(doctorToUpdate.toString());
							
							System.out.println("Information updated successfully! ");
						} else {
							System.out.println("Doctor not found");
						}
					}catch (ParseException pe) {
						System.out.println("Input format error when updating Doctor" + pe);
					}
					break;
					

				case 2:	//REGISTER NEW TASK
					pw.println("searchDoctorIdfromUId");
					pw.println(User_id);
					String doctorId_string = br.readLine();
					int doctorToAssignNewTask_id = Integer.parseInt(doctorId_string);
					
					addTask(doctorToAssignNewTask_id);
					System.out.println("Task added sucessfully!");
					break;

				case 3:	//LIST ALL THE TASKS
					//int doctorAllTask_id = 0;// DoctorManager.searchDoctorIdfromUId(User_id);
					pw.println("searchDoctorIdfromUId"); //find id doctor from User id
					pw.println(User_id);
					String doctor_id_string = br.readLine();
					int doctor_id = Integer.parseInt(doctor_id_string);
					
					
					List <Elderly> elderlies = getListOfElderlyByDoctorID(doctor_id);
					System.out.println(elderlies);
					//poner bonito
					//TODO Que no pueda meter cualquier elderly id , solo las id asociadas a ese doctor
					int elderly_id = InputException.getInt("Elderly id to see the tasks: ");
					
					//List<Task> tasksList = null;// tasksManager.getListOfTasks(doctorAllTask_id);
					pw.println("getListOfTasksByDoctorFromElder"); //find list task from doctor id
					pw.println(doctor_id);
					pw.println(elderly_id);
					
					ArrayList <Task> tasks = new ArrayList<>();
					String cantidad_tasks_text=br.readLine();
					int cantidad_tasks=Integer.parseInt(cantidad_tasks_text);
					for(int i = 0; i < cantidad_tasks; i++) {
						
						String tasks_text=br.readLine();
						Task task=new Task(tasks_text);
						tasks.add(task);
					}
					System.out.println("List of tasks: " + tasks);
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

	public static void addTask(int doctorToAssignNewTask_id) throws Exception {

		System.out.println("Input the information of the new task: ");
		String description = InputException.getString("Description: ");

		// print all elderlies of this doctor
		
		List <Elderly> elderlies = getListOfElderlyByDoctorID(doctorToAssignNewTask_id);
		System.out.println(elderlies);
		//TODO Que no pueda meter cualquier elderly id , solo las id asociadas a ese doctor
		int elderly_id = InputException.getInt("Elderly id: ");
		int duration = InputException.getInt("Duration: ");
		Task task = new Task(description, doctorToAssignNewTask_id, duration, elderly_id);
		
		// tasksManager.addTask(task);
		pw.println("addTask");
		pw.println(task);
		
		br.readLine();
		
		
	}
	public static List <Elderly> getListOfElderlyByDoctorID(int doctor_id) throws Exception {
		pw.println("getListOfElderlyByDoctorID");
		pw.println(doctor_id);
		ArrayList <Elderly>elderlies = new ArrayList<>();
		String cantidad_elderlies_text=br.readLine();
		int cantidad_elderlies=Integer.parseInt(cantidad_elderlies_text);
		for(int i = 0; i < cantidad_elderlies; i++) {
			
			String elderly_text=br.readLine();
			Elderly elderly=new Elderly(elderly_text);
			elderlies.add(elderly);
			
		}
		return elderlies;
	}
}
