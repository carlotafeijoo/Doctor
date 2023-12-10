package exceptions;

import java.io.*;

public class InputException {
	
	

	private static BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
	/**
	Reads a string input from the console.
	@param question the question to be displayed to the user
	@return the string input from the user
	*/
	public static String getString(String question) {

		while (true) {
			try {
				System.out.println(question);
				String leido = consola.readLine();
				return leido;

			} catch (IOException ex) {
				System.out.println("\n Error. Please input again a correct value");
			}
		}
	}
	/**
	Reads an integer input from the console.
	@param question the question to be displayed to the user
	@return the integer input from the user
	*/
	public static Integer getInt(String question) {
		int x = 0;
		while (true) {
			try {
				System.out.println(question);

				x = Integer.parseInt(consola.readLine());
				return x;

			} catch (NumberFormatException ex) {
				System.out.println("\n Error. Please input again a correct number. ");
			} catch (IOException ex) {

			}

		}
	}

}
