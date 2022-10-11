package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailsDatabase {

	private static EmployeeDetailsDatabase employeeDetailsDb;
	private List<Employee> employeeList;
	private List<SalaryDetails> salaryDetailsList;
	private List<Attendance> attendanceList;
	private List<LoginDetails> loginDetailsList;
	private Database database = new Database();

	EmployeeDetailsDatabase() {
		employeeList = new ArrayList<Employee>();
		salaryDetailsList = new ArrayList<SalaryDetails>();
		attendanceList = new ArrayList<Attendance>();
		loginDetailsList = new ArrayList<LoginDetails>();
	}

	public static EmployeeDetailsDatabase getInstance() {
		if (employeeDetailsDb == null) {
			employeeDetailsDb = new EmployeeDetailsDatabase();

		}
		return employeeDetailsDb;
	}

	// Employees related operations
	public void insertEmployee(List<Employee> employeeList) {
		this.employeeList.addAll(employeeList);
		
	}

	public void insertEmployee(Employee employee) {
		this.employeeList.add(employee);
		database.setData("Insert into employee.employeedetails values ('" + employee.getEmployeeId() + "' , '"
				+ employee.getEmployeeName() + "' , '" + employee.getDateOfBirth() + "' , '" + employee.getDesignation()
				+ "' , " + employee.getAadharNumber() + " , " + employee.getPhoneNumber() + " , '"
				+ employee.getEmailId() + "' , " + employee.getSalary() + ")");
	}

	public void removeEmployee(String employeeId) {
		database.setData(
				"Insert into employee.pastemployeedetails Select * from employee.employeedetails where employeeId = '"
						+ employeeId + "'");
		database.setData("Delete from employee.employeedetails where employeeId= '" + employeeId + "'");
		database.setData("Delete from employee.logindetails where employeeId= '" + employeeId + "'");
		this.employeeList.remove(getEmployee(employeeId));
	}

	public Employee getEmployee(String employeeId) {
		Employee employee = new Employee();
		for (Employee eachEmployee : employeeList) {
			if (employeeId.equals(eachEmployee.getEmployeeId()))
				employee = eachEmployee;
		}
		return employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	// Attendance related operations
	public void insertAttendance(List<Attendance> attendanceList) {
		this.attendanceList.addAll(attendanceList);
	}

	public void insertAttendance(Attendance attendance) {
		this.attendanceList.add(attendance);
		database.setData("Insert into employee.attendancelist values ('" + attendance.getDate() + "' , '"
				+ attendance.getEmployeeId() + "' )");
	}

	public Attendance getAttendance(String employeeId) {
		Attendance attendance = new Attendance();
		for (Attendance eachAttendance : attendanceList) {
			if (employeeId.equals(eachAttendance.getEmployeeId()))
				attendance = eachAttendance;
		}
		return attendance;
	}

	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	// Salary related operations
	public void insertSalaryDetails(List<SalaryDetails> salaryDetailsList) {
		this.salaryDetailsList.addAll(salaryDetailsList);
	}

	public void insertSalaryDetails(SalaryDetails salaryDetails) {
		this.salaryDetailsList.add(salaryDetails);
		database.setData("Insert into employee.salarydetails values (" + salaryDetails.getMonth() + ", "
				+ salaryDetails.getYear() + ", '" + salaryDetails.getEmployeeId() + "' , '"
				+ salaryDetails.getEmployeeName() + "' , " + salaryDetails.getNetSalary() + " , "
				+ salaryDetails.getNumberOfWorkingDays() + " , " + salaryDetails.getNumberOfDaysPresent() + " )");
	}

	public SalaryDetails getSalaryDetails(String employeeId) {
		SalaryDetails salaryDetails = new SalaryDetails();
		for (SalaryDetails eachSalaryDetails : salaryDetailsList) {
			if (employeeId.equals(eachSalaryDetails.getEmployeeId()))
				salaryDetails = eachSalaryDetails;
		}
		return salaryDetails;
	}

	public List<SalaryDetails> getSalaryDetailsList() {
		return salaryDetailsList;
	}

	// Login related operations
	public void insertLoginDetails(List<LoginDetails> loginDetailsList) {
		this.loginDetailsList.addAll(loginDetailsList);
	}

	public void insertLoginDetails(LoginDetails loginDetails) {
		this.loginDetailsList.add(loginDetails);
		database.setData("Insert into employee.logindetails values ( '" + loginDetails.getEmployeeId() + "','"
				+ loginDetails.getPassword() + "' )");
	}

	public LoginDetails getLoginDetails(String employeeId) {
		LoginDetails loginDetails = new LoginDetails();
		for (LoginDetails eachLoginDetails : loginDetailsList) {
			if (employeeId.equals(eachLoginDetails.getEmployeeId()))
				loginDetails = eachLoginDetails;
		}
		return loginDetails;
	}

	public List<LoginDetails> getLoginDetailsList() {
		return loginDetailsList;
	}
	
	public void changePassword(String employeeId,String newPassword) {
		LoginDetails loginDetails = getLoginDetails(employeeId);
		loginDetails.setPassword(newPassword);
		database.setData("Update employee.logindetails set password = '" + newPassword + "' where EmployeeID = '" + employeeId + "'");
	}
}
