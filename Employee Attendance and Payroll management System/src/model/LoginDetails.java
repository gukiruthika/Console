package model;

public class LoginDetails {
	private String employeeId;
	private String password;

	public LoginDetails(String employeeId, String password) {
		this.employeeId = employeeId;
		this.password = password;
	}

	public LoginDetails() {
		
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}