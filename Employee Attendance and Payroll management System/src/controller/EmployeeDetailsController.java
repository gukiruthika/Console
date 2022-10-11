package controller;

import java.util.List;

import model.Employee;
import model.EmployeeDetailsDatabase;
import model.LoginDetails;

public class EmployeeDetailsController {
	private List<Employee> employeeList = EmployeeDetailsDatabase.getInstance().getEmployeeList();
	private Employee employee;
	private LoginDetails logindetail;

	public EmployeeDetailsController() {
	}

	public void toAddEmployee(String employeeId, String employeeName, String dateOfBirth, String designation,
			Long aadharNumber, Long phoneNumber, String emailId, int salary, String password) {
		employee = new Employee(employeeId, employeeName, dateOfBirth, designation, aadharNumber, phoneNumber, emailId,
				salary);
		String hashedPassword = (((Integer)password.hashCode()).toString()).trim();
		logindetail = new LoginDetails(employeeId,hashedPassword);
		EmployeeDetailsDatabase.getInstance().insertEmployee(employee);
		EmployeeDetailsDatabase.getInstance().insertLoginDetails(logindetail);
	}

	public boolean isExistEmployeeId(String employeeId) {
		for (Employee eachEmployee : employeeList) {
			if ((eachEmployee.getEmployeeId().equals(employeeId))) {
				return true;
			}
		}
		return false;
	}

	public void toChangePassword(String employeeId, String newPassword) {
		String hashedPassword = ((Integer)newPassword.hashCode()).toString();
		EmployeeDetailsDatabase.getInstance().changePassword(employeeId, hashedPassword);
	}
	
	public List<Employee> toViewEmployee() {
		return EmployeeDetailsDatabase.getInstance().getEmployeeList();
	}

	public String toRemoveEmployee(String employeeId) {
		for (Employee employee : employeeList) {
			if ((employee.getEmployeeId().equals(employeeId))) {
				EmployeeDetailsDatabase.getInstance().removeEmployee(employeeId);
				return "Removed Successfully";
			}
		}
		return "Such Employee ID doesn't exist!!";
	}
}
