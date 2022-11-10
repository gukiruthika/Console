package com.flight.view;

import java.util.Scanner;

import com.flight.controller.LoginController;

public class LoginView {

	private Scanner scanner = new Scanner(System.in);
	private LoginController loginController;
	
	LoginView() {
		loginController = new LoginController(this);
	}

	public static void main(String[] args) {
		MenuView menuView = new MenuView();
		menuView.init();
	}

	private void initLogin() {
		checkForLogin();
	}

	private void checkForLogin() {
		System.out.println("Enter User Name");
		String userName = scanner.nextLine();
		System.out.println("Enter password");
		String password = scanner.nextLine();
		if (loginController.checkLoginCredentials(userName, password)) {
			MenuView menuView = new MenuView();
			menuView.init();
		} else {
			System.out.println("\nInvalid Credentials. Please try again!\n");
			checkForLogin();
		}
	}
}
