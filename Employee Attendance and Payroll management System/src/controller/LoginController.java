package controller;

import java.util.List;

import model.Database;
import model.EmployeeDetailsDatabase;
import model.LoginDetails;

public class LoginController {

	private Database database = new Database();
	private List<LoginDetails> loginDetailsList = EmployeeDetailsDatabase.getInstance().getLoginDetailsList();

	public LoginController() {
		database.passData("Select * from employee.logindetails", "logindetails");
	}

	public boolean checkAdminCredentials(String userName, String password) {
		return userName.equals("admin") && password.equals("ZSGS");

	}

	public boolean checkEmployeeCredentials(String employeeId, String password) {
		String hashedPassword = ((Integer)password.hashCode()).toString();
		for (LoginDetails loginDetails : loginDetailsList) {
			if (employeeId.equals(loginDetails.getEmployeeId()) && hashedPassword.equals(loginDetails.getPassword())) {
				return true;
			}
		}
		return false;
	}

	public int checkUser(String user) {
		if (user.equalsIgnoreCase("admin"))
			return 1;
		else if (user.equalsIgnoreCase("employee"))
			return 0;
		else
			return -1;
	}
}
