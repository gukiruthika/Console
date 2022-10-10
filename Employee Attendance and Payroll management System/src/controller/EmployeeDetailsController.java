package controller;

import java.util.List;

import model.Database;
import model.Employee;
import model.EmployeeDetailsDatabase;

public class EmployeeDetailsController {
	private List<Employee> employeeList = EmployeeDetailsDatabase.getInstance().getEmployeeList();
	private Database database = new Database();
	private Employee employee;

	public EmployeeDetailsController() {
	}

	public void toAddEmployee(String employeeId, String employeeName, String dateOfBirth, String designation,
			Long aadharNumber, Long phoneNumber, String emailId, int salary) {
		employee = new Employee(employeeId, employeeName, dateOfBirth, designation, aadharNumber, phoneNumber, emailId,
				salary);
		EmployeeDetailsDatabase.getInstance().insertEmployee(employee);
		database.setData("Insert into employee.employeedetails values ('" + employeeId + "' , '" + employeeName
				+ "' , '" + dateOfBirth + "' , '" + designation + "' , " + aadharNumber + " , " + phoneNumber + " , '"
				+ emailId + "' , " + salary + ")");
	}

	public boolean isExistEmployeeId(String employeeId) {
		for (Employee eachEmployee : employeeList) {
			if ((eachEmployee.getEmployeeId().equals(employeeId))) {
				return true;
			}
		}
		return false;
	}

	public void toViewEmployee() {
		for (Employee employee : employeeList) {
			System.out.printf("\n%-15s %-20s %-15s %-20s %-15s %-15s %-20s", employee.getEmployeeId(),
					employee.getEmployeeName(), employee.getDateOfBirth(), employee.getDesignation(),
					employee.getAadharNumber(), employee.getPhoneNumber(), employee.getEmailId());
		}
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
