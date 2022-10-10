package model;

public class Attendance {
	private String month;
	private String employeeId;
	private String lastCheckIn;
	private int numberOfDaysPresent;

	Attendance(String month,String employeeId,int numberOfDaysPresent){
		this.month=month;
		this.employeeId=employeeId;
		this.numberOfDaysPresent=numberOfDaysPresent;
	}
	
	public Attendance() {
		
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getNumberOfDaysPresent() {
		return numberOfDaysPresent;
	}

	public void setNumberOfDaysPresent(int numberOfDaysPresent) {
		this.numberOfDaysPresent = numberOfDaysPresent;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getLastCheckIn() {
		return lastCheckIn;
	}

	public void setLastCheckIn(String lastCheckIn) {
		this.lastCheckIn = lastCheckIn;
	}
}
