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
import exceptions.Plotting;


import exceptions.InputException;
import POJOS.*;

public class DoctorMenuResidencialArea {
	static OutputStream os = null;
	static PrintWriter pw = null;

	static BufferedReader br = null;
	static Socket so = null;
	private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	//consola
	/**
	 * The main method of the program. It initializes socket communication, input and output streams, and calls the mainMenu method.
	 *
	 * @param args the command-line arguments
	 * @throws IOException if an I/O error occurs when creating the socket or the input/output streams
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("\nDOCTOR! WELCOME TO THE RESIDENCIAL AREA DATA BASE");

		//so = new Socket("192.168.1.129", 9009);
		so = new Socket("localhost", 9009);
		// el cliente lee lineas pero tambien manda
		br = new BufferedReader(new InputStreamReader(so.getInputStream())); //sockets cliente
		os = so.getOutputStream();
		pw = new PrintWriter(os, true);

		mainMenu();

	}
	/**
	 * Releases the resources associated with the socket communication.
	 *
	 * @param printWriter the PrintWriter to be closed
	 * @param br the BufferedReader to be closed
	 * @param outputStream the OutputStream to be closed
	 * @param socket the Socket to be closed
	 */
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
	/**
	 * Displays the main menu and handles user input based on the selected options.
	 */
	public static void mainMenu() {
		try {

			int option;
			do {
				System.out.println("\nMAIN MENU ");
				System.out.println("1. Enter  ");
				System.out.println("2. Exit ");
				option = InputException.getInt("\nIntroduce the number choice:  ");

				System.out.println("\n--------------------------------------------------------------------------------");

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
	/**
	 * Presents a menu for doctor login and registration, and handles user input based on the selected options.
	 *
	 * @throws Exception if an error occurs during the execution of the method
	 */
	private static void logindoctor() throws Exception {

		System.out.println("\nMENU");
		System.out.println("1. Register");
		System.out.println("2. Log in ");
		System.out.println("3. Exit");
		int choice = InputException.getInt("\nIntroduce the number of your choice: ");
		System.out.println("\n--------------------------------------------------------------------------------");

		switch (choice) {
		case 1:
			// Call method REGISTER
			System.out.println("\n\tREGISTER");
			registerdoctor();
			logindoctor();
			break;

		case 2:
			// LOG IN as doctor member
			System.out.println("\n\tLOGIN");
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
	/**
	 * Gathers and validates the information to register a doctor, and communicates with the server to add the doctor.
	 *
	 * @throws Exception if an error occurs during the execution of the method
	 */
	public static void registerdoctor() throws Exception {

		System.out.println("\nInput information: ");

		String name = InputException.getString("Name: ");

		String field = null;
		int choice;
		do {

			System.out.println("\n");
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
				registerdoctor();
				System.out.println("You should introduce an option");
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

		if (checkDate(year, month, day)==false) {
			System.out.println("Sorry your date of birth is wrong, try again \n");
			System.out.println("\n--------------------------------------------------------------------------------");

		}else {
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
			System.out.println(	br.readLine());
			System.out.println("\n--------------------------------------------------------------------------------");

		}

	}
	/**
	 * Handles the login process for a user, communicates with the server to validate the user's credentials, and retrieves their role and associated information.
	 *
	 * @throws Exception if an error occurs during the execution of the method
	 */
	public static void logIn() throws Exception {

		String username = InputException.getString("Username: ");
		String password = InputException.getString("Password: ");

		pw.println("checkPassword");
		pw.println(username);
		pw.println(password);

		String role_text=br.readLine();
		String user_text = br.readLine();

		User u = null;

		if(user_text.equalsIgnoreCase("error")) {
			System.out.println("User not found\n");
			System.out.println("\n--------------------------------------------------------------------------------");
			mainMenu();
		}
		else {
			u = new User(user_text);
			u.setRole(new Role(role_text));
			System.out.println("\n--------------------------------------------------------------------------------");
		}

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

			System.out.println("\nLogin successful!");
			System.out.println(doctor);
			System.out.println("\n--------------------------------------------------------------------------------");
			doctorMenu(u.getId());			

		}


	}
	/**
	Displays the doctor's menu and handles various options such as updating information, registering new tasks, listing all tasks,
	viewing patient symptoms, viewing patient reports, and returning to the main menu.
	@param User_id the unique identifier for the doctor
	 */
	private static void doctorMenu(int User_id) {
		try {
			int choice;
			do {
				System.out.println("\nMENU");
				System.out.println("1. Update information. ");
				System.out.println("2. Register new task. ");
				System.out.println("3. List all the tasks. ");
				System.out.println("4. See patient symptoms. ");
				System.out.println("5. See patient report. ");
				System.out.println("6. Back.  ");
				choice = InputException.getInt("\nIntroduce your choice: ");
				System.out.println("\n--------------------------------------------------------------------------------");
				
				switch (choice) {

				case 1:	//UPDATE INFORMATION
					System.out.println("\n\tUPDATING INFORMATION");
					try {
						pw.println("searchDoctorIdfromUId");
						pw.println(User_id);
						String doctor_id_string = br.readLine();
						int doctor_id = Integer.parseInt(doctor_id_string);
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

							if (checkDate(year, month, day)==false) {
								System.out.println("Sorry your date of birth is wrong, try again \n");
								System.out.println("\n--------------------------------------------------------------------------------");
							}else {
								doctorToUpdate.setDob(dob);
								pw.println("updateDoctorMemberInfo");
								pw.println(doctorToUpdate.toString());
								System.out.println("\nInformation updated successfully! ");
								System.out.println("\n--------------------------------------------------------------------------------");
							}
						} else {
							System.out.println("\nDoctor not found");
							System.out.println("\n--------------------------------------------------------------------------------");
						}
					}catch (ParseException pe) {
						System.out.println("Input format error when updating Doctor" + pe);
					}
					break;

				case 2:	//REGISTER NEW TASK
					System.out.println("\n\tREGISTERING NEW TASK");
					pw.println("searchDoctorIdfromUId");
					pw.println(User_id);
					String doctorId_string = br.readLine();
					int doctorToAssignNewTask_id = Integer.parseInt(doctorId_string);
					addTask(doctorToAssignNewTask_id);
					break;

				case 3:	//LIST ALL THE TASKS
					System.out.println("\n\tLISTING ALL THE TASKS");
					//int doctorAllTask_id = 0;// DoctorManager.searchDoctorIdfromUId(User_id);
					pw.println("searchDoctorIdfromUId"); //find id doctor from User id
					pw.println(User_id);
					String doctor_id_string = br.readLine();
					int doctor_id = Integer.parseInt(doctor_id_string);
					List <Elderly> elderlies = getListOfElderlyByDoctorID(doctor_id);
					for (Elderly e : elderlies) {
						System.out.println(e);
					}
					int elderly_id = InputException.getInt("Elderly id to see the tasks: ");
					if(elderlies.isEmpty()==true){
						System.out.println("\nSorry, you dont have any patient associated in this moment");
						System.out.println("\n--------------------------------------------------------------------------------");
						break;
					}else if(checklist(elderly_id,elderlies)== true){
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
						if(tasks.isEmpty()==true) {
							System.out.println("\nSorry, for this moment this patient doesnt have any associated task");
							System.out.println("\n--------------------------------------------------------------------------------");
							break;
						}else{
							System.out.println("\nList of tasks: ");
							for (Task t : tasks) {
								System.out.println(t);
							}
							System.out.println("\n--------------------------------------------------------------------------------");
							break;
						}	
					}else {	
						System.out.println("\nSorry, this id does not correspond to that of any of your associated patients");
						System.out.println("\n--------------------------------------------------------------------------------");
						break;
					}


				case 4:
					System.out.println("\n\tSEE PATIENT SYMPTOMS");

					pw.println("searchDoctorIdfromUId");
					pw.println(User_id);
					String doctorIDtext = br.readLine();
					int doctorID = Integer.parseInt(doctorIDtext);

					List <Elderly> eld_list = getListOfElderlyByDoctorID(doctorID);
					for (Elderly e : eld_list) {
						System.out.println(e);
					}

					System.out.println("Introduce elderly id to see its symptoms:");
					String eld_id_txt = read.readLine();
					int eld_id = Integer.parseInt(eld_id_txt);

					if(eld_list.isEmpty()==true){
						System.out.println("\nSorry, you dont have any patient associated in this moment");
						System.out.println("\n--------------------------------------------------------------------------------");
						break;

					}else if(checklist(eld_id, eld_list)== true){

						pw.println("seeSymptoms");
						pw.println(eld_id_txt);
						String symp = br.readLine();

						if (symp.equalsIgnoreCase("null")) {
							System.out.println("This patient has no symptoms/n");
							System.out.println("\n--------------------------------------------------------------------------------");
						}
						else {
							System.out.println(symp);
							System.out.println("\n--------------------------------------------------------------------------------");
						}
						break;


					}else {	
						System.out.println("\nSorry, this id does not correspond to that of any of your associated patients");
						System.out.println("\n--------------------------------------------------------------------------------");
						break;
					}


				case 5:	//LIST ALL THE REPORTS
					System.out.println("\n\tSEE PATIENT REPORTS");
					//int doctorAllTask_id = 0;// DoctorManager.searchDoctorIdfromUId(User_id);
					pw.println("searchDoctorIdfromUId"); //find id doctor from User id
					pw.println(User_id);
					String doctor_id_string2 = br.readLine();
					int doctor_id2 = Integer.parseInt(doctor_id_string2);


					List <Elderly> elderlies2 = getListOfElderlyByDoctorID(doctor_id2);
					for (Elderly e : elderlies2) {
						System.out.println(e);
					}
					int elderly_id2 = InputException.getInt("Elderly id to see the reports: ");

					if(elderlies2.isEmpty()==true){					
						System.out.println("Sorry, you dont have any patient associated in this moment");
						System.out.println("\n--------------------------------------------------------------------------------");
						break;

					}else if(checklist(elderly_id2,elderlies2)== true){
						//List<Task> tasksList = null;// tasksManager.getListOfTasks(doctorAllTask_id);
						pw.println("getListOfReportsByDoctorFromElder"); //find list task from doctor id
						pw.println(doctor_id2);
						pw.println(elderly_id2);

						ArrayList <Report> reports = new ArrayList<>();
						String cantidad_reports_text=br.readLine();
						int cantidad_reports=Integer.parseInt(cantidad_reports_text);
						for(int i = 0; i < cantidad_reports; i++) {
							String reports_text=br.readLine();
							Report report=new Report(reports_text);
							//System.out.println(report);
							reports.add(report);
						}
						if(reports.isEmpty()==true) {
							System.out.println("Sorry, for this moment this patient doesnt have any associated report");
							System.out.println("\n--------------------------------------------------------------------------------");
							break;

						}else{
							System.out.println("\nList of reports: ");
							for (Report r : reports) {
								System.out.println(r);
							}
							int report_id = InputException.getInt("Report id to see the graphic: ");
							pw.println("printReport"); 
							pw.println(report_id);

							String task_id_text = br.readLine();
							String file_name = br.readLine();
							int task_id = Integer.parseInt(task_id_text);
							String id_elderly_text = br.readLine();
							int elderly_id3 = Integer.parseInt(id_elderly_text);

							Report rep =new Report(file_name, task_id, elderly_id3);
							String diract = System.getProperty("user.dir"); 
							String dirfolder = diract +"//recordstxt";

							File archivo = new File(dirfolder, file_name);

							PrintWriter printwriter = null;

							try {
								printwriter = new PrintWriter(archivo);
								String stringleido;
								stringleido = br.readLine();

								String signal = convertCommaIntoLines(stringleido);
								ECGPlot(signal, file_name);

								printwriter.println(signal);

							} catch (IOException ioe) {
								System.out.println("Error" + ioe);
								System.out.println("\n--------------------------------------------------------------------------------");
							} finally {
								if (printwriter != null) {
									printwriter.close();
								}

							}

							break;

						}	
					}else {	
						System.out.println("Sorry, this id does not correspond to that of any of your associated patients");
						System.out.println("\n--------------------------------------------------------------------------------");
						break;
					}
				case 6:
					mainMenu();
					System.out.println("\n--------------------------------------------------------------------------------");
					break;

				default:
					break;

				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gathers the information for a new task, including description, elderly patient's ID, and task duration, and communicates with the server to add the task.
	 *
	 * @param doctorToAssignNewTask_id the unique identifier for the doctor to whom the new task is assigned
	 * @throws Exception if an error occurs during the execution of the method
	 */
	public static void addTask(int doctorToAssignNewTask_id) throws Exception {

		System.out.println("\nInput the information of the new task: ");
		String description = InputException.getString("Description: ");

		// print all elderlies of this doctor

		List <Elderly> elderlies = getListOfElderlyByDoctorID(doctorToAssignNewTask_id);
		for (Elderly e : elderlies) {
			System.out.println(e);
		}

		int elderly_id = InputException.getInt("Elderly id: ");
		int duration = InputException.getInt("Duration: ");

		if(elderlies.isEmpty()==true){

			System.out.println("\nSorry, you dont have any patient associated in this moment");
			System.out.println("\n--------------------------------------------------------------------------------");

		}else if(checklist(elderly_id,elderlies)== true){

			Task task = new Task(description, doctorToAssignNewTask_id, duration, elderly_id);
			pw.println("addTask");
			pw.println(task);
			br.readLine();
			System.out.println("\nTask added sucessfully!");
			System.out.println("\n--------------------------------------------------------------------------------");


		}else {	
			System.out.println("\nSorry, this id does not correspond to that of any of your associated patients");
			System.out.println("\n--------------------------------------------------------------------------------");


		}


		// tasksManager.addTask(task);

	}
	/**
	 * Retrieves a list of elderly individuals associated with a specific doctor from the server.
	 *
	 * @param doctor_id the unique identifier for the doctor
	 * @return a list of elderly individuals associated with the specified doctor
	 * @throws Exception if an error occurs during the retrieval process
	 */
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

	/**
	 * Checks if a given elderly ID exists in a list of elderly individuals.
	 *
	 * @param eld_id the elderly ID to be checked
	 * @param elder the list of elderly individuals
	 * @return true if the elderly ID exists in the list; false otherwise
	 */
	public static boolean checklist(int eld_id,  List <Elderly> elder) {
		boolean check= false;
		for (int i=0; i < elder.size();i++ ) {
			if (elder.get(i).getElderly_id() == eld_id) {
				check = true;
			}
		}
		return check;
	}

	/**
	 * Validates a given date.
	 *
	 * @param year the year component of the date
	 * @param month the month component of the date
	 * @param day the day component of the date
	 * @return true if the date is valid; false otherwise
	 */
	public static boolean checkDate(int year, int month, int day) {
		if (year < 1900 || year > 2024) {
			return false;
		}
		if (month < 1 || month > 12) {
			return false;
		}
		if (day < 1 || day > 31) {
			return false;
		}
		if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				if (day > 29) {
					return false;
				}
			} else {
				if (day > 28) {
					return false;
				}
			}
		}
		else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day > 30) {
				return false;
			}
		}
		return true;
	}

	/**
	Generates an ECG plot based on the provided signal data and saves it as a PNG file.
	@param args the input string containing the ECG signal data
	@param file_name the name of the output file
	 */
	public static void ECGPlot(String args, String file_name) {
		ArrayList<Double> column2 = new ArrayList<>();
		String line = args;
		String[] filas = line.split("\n");
		String[][] values = new String[filas.length][];
		for (int i = 0; i < filas.length; i++) {
			values[i] = filas[i].split("\\t");
		}
		for (int i = 0; i < values.length; i++) {
			column2.add(Double.parseDouble(values[i][1]));
		}
		double[] signal = column2.stream().mapToDouble(Double::doubleValue).toArray();
		// Redondear hacia arriba utilizando Math.ceil()
		int longitud = (int) (Math.ceil(signal.length / 100.0) * 100);
		Plotting fig = new Plotting(longitud, 300, "ECG Wave", "Time", "Signal");
		fig.initialisePlot();
		fig.addSignal("ECG Wave", signal, false);
		try {
			String diract = System.getProperty("user.dir"); 
			String dirfolder = diract +"\\ECGplots";
			file_name = file_name + ".png";
			File f = new File(dirfolder, file_name);
			fig.saveAsPNG(f.getName());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Replaces commas in the input string with newline characters.
	 *
	 * @param stringleido the input string containing commas
	 * @return the modified string with commas replaced by newline characters
	 */
	private static String convertCommaIntoLines(String stringleido) {
		String signal = stringleido.replace(",", "\n");
		return signal;
	}



}
