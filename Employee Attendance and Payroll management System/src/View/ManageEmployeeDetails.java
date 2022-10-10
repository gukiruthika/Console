package View;

import controller.EmployeeDetailsController;
import controller.InputController;
import model.Employee;

public class ManageEmployeeDetails {
	private EmployeeDetailsController employeeDetailsController;
	private InputController inputController;

	public ManageEmployeeDetails() {
		inputController = new InputController();
		employeeDetailsController = new EmployeeDetailsController();
	}

	public void addEmployee() {
		String employeeId = inputController.getInput("Employee id (Eg. Emp0001)", "(Emp\\d{4})");
		if (employeeDetailsController.isExistEmployeeId(employeeId)) {
			System.out.println("Employee id already exists!!");
			addEmployee();
		} else {
			String employeeName = inputController.getInput("name", "[A-Za-z ]+");
			String dateOfBirth = inputController.getInput("date of birth (DD/MM/YYYY)",
					"(0[1-9]|[12][0-9]|3[01])[/](0?[1-9]|1[012])[/](19[6-9][0-9]|20[01][0-5])");
			String designation = inputController.getInput("designation", "[A-Za-z ]+");
			Long aadharNumber = Long.valueOf(inputController.getInput("aadharNumber", "[2-9][0-9]{11}"));
			Long phoneNumber = Long.valueOf(inputController.getInput("phoneNumber", "^[5-9][0-9]{9}$"));
			String emailId = inputController.getInput("email address",
					"(^[a-zA-Z0-9.]+@[a-zA-Z0-9]+([.]+[a-zA-Z0-9-]+)+)");
			int salary = Integer.valueOf(inputController.getInput("gross salary", "[1-9][0-9]+"));
			employeeDetailsController.toAddEmployee(employeeId, employeeName, dateOfBirth, designation, aadharNumber,
					phoneNumber, emailId, salary);
			System.out.print("Employee added Successfully!!");
		}
	}

	public void viewEmployee() {
		System.out.printf("%-15s %-20s %-15s %-20s %-15s %-15s %-20s", "Employee id", "Name", "Date of Birth",
				"Designation", "Aadhar Number", "Phone Number", "Email id");
		employeeDetailsController.toViewEmployee();
	}

	public void removeEmployee() {
		String employeeId = inputController.getInput("Employee id (Eg. Emp0001)", "(Emp\\d{4})");
		System.out.println(employeeDetailsController.toRemoveEmployee(employeeId));
	}

}
