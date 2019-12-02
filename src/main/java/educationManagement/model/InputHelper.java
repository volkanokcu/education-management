package educationManagement.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author Bora SAYINER
 * @since 3:25:32 PM Jan 15, 2017
 * @version 1.0.0
 */
public final class InputHelper {
	
	
	public static void main(String[] args) throws IOException {
		String firstName = InputHelper.getStringInput("First Name: ");
		System.out.println("Welcome, " + firstName);
	}

	private InputHelper() {
	}

	public static String getStringInput(String prompt) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
		System.out.print(prompt);
		return bufferedReader.readLine();
	}
	
	public static int getIntInput(String prompt) throws IOException {
		return Integer.parseInt(getStringInput(prompt));
	}
	
	public static float getFloatInput(String prompt) throws IOException {
		return Float.parseFloat(getStringInput(prompt));
	}

}
