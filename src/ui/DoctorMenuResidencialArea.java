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

	public static void main(String[] args) throws IOException {

		System.out.println("WELCOME TO THE RESIDENCIAL AREA DATA BASE");

		Socket so = new Socket("localhost", 9009);
		// el cliente lee lineas pero tambien manda
		br = new BufferedReader(new InputStreamReader(so.getInputStream()));
		os = so.getOutputStream();
		pw = new PrintWriter(os, true);

		mainMenu();

		// ejemplo sockets diapo 27
		/*
		 * Socket so=null; System.out.println("CLIENTE");
		 * 
		 * try { so = new Socket ("localhost", 9009); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(so.getInputStream())); String line = br.readLine();
		 * System.out.println(line);
		 * 
		 * OutputStream os = so.getOutputStream(); PrintWriter pw = new PrintWriter(os,
		 * true); pw.println("hola"); pw.println("carlota"); pw.println("stop");
		 * pw.println("adios");
		 * 
		 * releaseResources(pw, os, so);
		 */

		// probando con doctores
		/*
		 * System.out.println("Enter the year of birth:");
		 * 
		 * int year = Integer.parseInt(read.readLine());
		 * 
		 * System.out.println("Enter the month of birth:"); int month =
		 * Integer.parseInt(read.readLine());
		 * 
		 * System.out.println("Enter the day of birth:"); int day =
		 * Integer.parseInt(read.readLine()); SimpleDateFormat dateFormat = new
		 * SimpleDateFormat("yyyy/MM/dd");
		 * 
		 * String dobStr = String.format("%04d/%02d/%02d", year, month, day);
		 * java.util.Date utilDate; try { utilDate = dateFormat.parse(dobStr);
		 * 
		 * java.sql.Date dob = new java.sql.Date(utilDate.getTime()); Doctor doctor =
		 * new Doctor("Paloma", 34656, dob, "avenida", "gmail");
		 * 
		 * os = so.getOutputStream(); pw = new PrintWriter(os, true);
		 * 
		 * pw.println("addDoctor"); pw.println(""+doctor.toString());
		 * 
		 * //recibo linea para saber si se ha hecho BufferedReader br = new
		 * BufferedReader(new InputStreamReader(so.getInputStream())); String line =
		 * br.readLine(); System.out.println(line); pw.println("stop");
		 * 
		 * } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
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
					releaseResources(pw, br, os, so);
					System.exit(3);
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

		System.out.println("Username or dni without letter:");
		String username = read.readLine();
		String password = InputException.getString("Password: ");
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();

		User u = null;// userManager.checkPassword(username, digest);

		if (u == null) {
			System.out.println("User not found");
			mainMenu();
		}

		// depending on the type of user we open a different menu
		if (u != null && u.getRole().getName().equals("Doctor")) {
			Integer id = u.getId();
			
			int doctor_id = 0;// DoctorManager.searchDoctorIdfromUId(id);
			Doctor doctor = null;// DoctorManager.searchDoctorbyId(doctor_id);
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

				case 1:
					int doctor_id = 0;// DoctorManager.searchDoctorIdfromUId(User_id);
					Doctor doctorToUpdate = null;// DoctorManager.searchDoctorbyId(doctor_id);
					if (doctorToUpdate != null) {
						int newPhone = InputException.getInt("Enter your new phone number: ");
						doctorToUpdate.setPhone(newPhone);
						String newAddress = InputException.getString("Enter your new address: ");
						doctorToUpdate.setAddress(newAddress);
						// DoctorManager.updateDoctorMemberInfo(doctorToUpdate);
						System.out.println("Information updated successfully! ");
					} else {
						System.out.println("doctor update fail.");
					}
					break;

				case 2:
					int doctorToAssignNewTask_id = 0;// DoctorManager.searchDoctorIdfromUId(User_id);
					addTask(doctorToAssignNewTask_id);
					System.out.println("Task added sucessfully!");
					break;

				case 3:
					int doctorAllTask_id = 0;// DoctorManager.searchDoctorIdfromUId(User_id);
					List<Task> tasksList = null;// tasksManager.getListOfTasks(doctorAllTask_id);
					System.out.println("List of tasks: " + tasksList);
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
		List<Elderly> elderlies = null;// elderlyManager.getListOfElderlyByDoctorID(doctorToAssignNewTask_id);
		for (int i = 0; i < elderlies.size(); i++) {
			System.out.println(elderlies.get(i).toString() + "\n");
		}
		int elderly_id = InputException.getInt("Elderly id: ");
		int duration = InputException.getInt("Duration: ");
		Task task = new Task(description, doctorToAssignNewTask_id, duration, elderly_id);
		// tasksManager.addTask(task);
	}

}