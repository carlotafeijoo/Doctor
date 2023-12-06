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

	public static void main(String[] args) throws IOException {

		System.out.println("\nDOCTOR! WELCOME TO THE RESIDENCIAL AREA DATA BASE");

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
				System.out.println("\nMAIN MENU ");
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

		System.out.println("\nMENU");
		System.out.println("1. Register");
		System.out.println("2. Log in ");
		System.out.println("3. Exit");
		int choice = InputException.getInt(" Introduce the number of your choice: ");

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
	
	public static void registerdoctor() throws Exception {

		System.out.println("Input information: ");

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
			System.out.println("Sorry your date of birth is worng, try again \n");
			
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
	
		}
	}

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
			mainMenu();
		}
		else {
			u = new User(user_text);
			u.setRole(new Role(role_text));
		}
		
		/*User u = new User(user_text);
		u.setRole(new Role(role_text));*/
		
		//TODO checkear esto: es null? es vacio? hay que mover el if?
		/*if (u == null) {
			System.out.println("User not found");
			mainMenu();
		}else {
			Sring user_name user_text);
			u.setRole(new Role(role_text));
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
			
			System.out.println("\nLogin successful!");
			System.out.println(doctor);
			doctorMenu(u.getId());

		}

	}

	private static void doctorMenu(int User_id) {
		try {

			int choice;
			do {
				System.out.println("\nMENU");
				System.out.println("1.Update information. ");
				System.out.println("2.Register new task. ");
				System.out.println("3.List all the tasks. ");
				System.out.println("4.See patient symptoms. ");
				System.out.println("5.See patient report. ");
				System.out.println("6.Back.  ");

				choice = InputException.getInt("Introduce your choice: ");

				switch (choice) {

				case 1:	//UPDATE INFORMATION
					System.out.println("\n\tUPDATING INFORMATION");
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
							
							System.out.println("\nInformation updated successfully! ");
						} else {
							System.out.println("\nDoctor not found");
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
						System.out.println("\nSorry, you dont have any pattient associated in this moment");
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
							break;
						}else{
							System.out.println("\nList of tasks: ");
							for (Task t : tasks) {
								System.out.println(t);
							}
							break;
						}	
					}else {	
						System.out.println("\nSorry, this id does not correspond to that of any of your associated patients");
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
					
					
					pw.println("seeSymptoms");
					pw.println(eld_id_txt);
					String symp = br.readLine();
					
					if (symp.equalsIgnoreCase("null")) {
						System.out.println("This patient has no symptoms/n");
					}
					else {
						System.out.println(symp);
					}
					break;
					
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
						System.out.println("Sorry, you dont have any pattient associated in this moment");
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
							System.out.println(report);
							reports.add(report);
						}
						if(reports.isEmpty()==true) {
							System.out.println("Sorry, for this moment this patient doesnt have any associated report");
							break;
						}else{
							System.out.println("List of reports: ");
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

								printwriter.println(signal);

							} catch (IOException ioe) {
								System.out.println("Error" + ioe);
							} finally {
								if (printwriter != null) {
									printwriter.close();
								}

							}

							break;

						}	
					}else {	
						System.out.println("Sorry, this id does not correspond to that of any of your associated patients");
						break;
					}
				case 6:
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
		for (Elderly e : elderlies) {
			System.out.println(e);
		}
		
		int elderly_id = InputException.getInt("Elderly id: ");
		int duration = InputException.getInt("Duration: ");
		
		if(elderlies.isEmpty()==true){
			
			System.out.println("Sorry, you dont have any pattient associated in this moment");
		}else if(checklist(elderly_id,elderlies)== true){
			
			Task task = new Task(description, doctorToAssignNewTask_id, duration, elderly_id);
			pw.println("addTask");
			pw.println(task);
			br.readLine();
			System.out.println("Task added sucessfully!");
			
		}else {	
			System.out.println("Sorry, this id does not correspond to that of any of your associated patients");
	
		}
		
		
		// tasksManager.addTask(task);
				
		
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
	
	
	public static boolean checklist(int eld_id,  List <Elderly> elder) {
		boolean check= false;
		for (int i=0; i < elder.size();i++ ) {
			if (elder.get(i).getElderly_id() == eld_id) {
				check = true;
			}
		}
		return check;
	}
	
	
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


	    public static void ECGPlot(String args) {
	        ArrayList<Double> column1 = new ArrayList<>();
	        ArrayList<Double> column2 = new ArrayList<>();

	            String line = args;
	            String[] values = line.split("\\t");
	            column2.add(Double.parseDouble(values[1]));
	        int time = column2.size();
	      
	        double[] signal = column2.stream().mapToDouble(Double::doubleValue).toArray();

	        Plotting fig = new Plotting(600, 300, "ECG Wave", "Time", "Signal");
	        fig.initialisePlot();
	        //fig.addSignal("ECG Wave", time, signal, false);
	        //fig.addSignal("ECG Wave", time, signal, false);
	       // fig.add_signal("ECG Wave", time, signal, false);
	       // fig.saveAsPNG(line);
	    }
	
	private static String convertCommaIntoLines(String stringleido) {
		String signal = stringleido.replace(",", "\n");
		return signal;
	}
	
}
