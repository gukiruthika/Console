package com.flight.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation {
	private Scanner scanner = new Scanner(System.in);
	String getInput(String field, String regex) {
		String input;
		System.out.print(field);
		input = scanner.nextLine();
		if (input.equals("exit"))
			throw new InputMismatchException();
		if (input.matches(regex))
			return input;
		else {
			System.out.println("Invalid input!!");
			return getInput(field, regex);
		}
	}
}
