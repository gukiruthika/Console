package controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
	private Scanner scan = new Scanner(System.in);

	public String getInput(String field, String regex) {
		String input;
		System.out.print("\nEnter " + field + " : ");
		input = scan.nextLine();
		if(input.equals("exit"))
			throw new InputMismatchException();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches())
			return input;
		else {
			System.out.println("Invalid input!!");
			return getInput(field, regex);
		}
	}
}
