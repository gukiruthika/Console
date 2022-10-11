package model;

public class Attendance {
	private String date;
	private String employeeId;

	Attendance(String date, String employeeId) {
		this.date = date;
		this.employeeId = employeeId;
	}

	public Attendance() {

	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
