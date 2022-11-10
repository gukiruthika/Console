package com.flight.controller;

import com.flight.view.LoginView;

public class LoginController {

	private LoginView loginView;

	public LoginController(LoginView loginView) {
		this.loginView = loginView;
	}

	public boolean checkLoginCredentials(String userName, String password) {
		if (userName.equals("admin") && password.equals("ZSGS"))
			return true;
		return false;
	}
}
