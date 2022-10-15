package View;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import controller.AttendanceController;
import controller.EmployeeDetailsController;
import controller.InputValidation;
import controller.SalaryController;
import model.Employee;
import model.SalaryDetails;

public class AdminMenu {
	private Scanner scan = new Scanner(System.in);
	private EmployeeDetailsController employeeDetailsController;
	private InputValidation inputValidation;
	private SalaryController salaryController;
	private AttendanceController attendanceController;

	AdminMenu() {
		inputValidation = new InputValidation();
		employeeDetailsController = new EmployeeDetailsController();
		salaryController = new SalaryController();
		attendanceController = new AttendanceController();
	}

	void init() {
		displayOptions();
	}

	private void displayOptions() {
		String option;
		do {
			System.out.println("\n");
			System.out.println("""
					1) Add Employee
					2) Remove Employee
					3) View Employees
					4) Update Salary
					5) View Salary
					6) View Attendance
					7) Log out
					Enter option..
					""");
			option = scan.nextLine();
			try {
				switch (option) {
				case "1":
					addEmployee();
					break;
				case "2":
					removeEmployee();
					break;
				case "3":
					viewEmployee();
					break;
				case "4":
					updateAllSalary();
					break;
				case "5":
					viewSalary();
					break;
				case "6":
					viewAttendance();
					break;
				case "7":
					System.out.println("\nThank you!\n");
					break;
				default:
					System.out.println("\nInvalid option.. Please choose again!\n");
				}
			} catch (InputMismatchException e) {
				System.out.println();
			}
		} while (!option.equals("7"));
	}

	private void addEmployee() {
		String employeeId = inputValidation.getInput("Employee id (Eg. Emp0001)", "(Emp\\d{4})");
		if (employeeDetailsController.isExistEmployeeId(employeeId)) {
			System.out.println("Employee id already exists!!");
			addEmployee();
		} else {
			String employeeName = inputValidation.getInput("name", "[A-Za-z ]+");
			String dateOfBirth = inputValidation.getInput("date of birth (DD/MM/YYYY)",
					"(0[1-9]|[12][0-9]|3[01])[/](0?[1-9]|1[012])[/](19[6-9][0-9]|20[01][0-5])");
			String designation = inputValidation.getInput("designation", "[A-Za-z ]+");
			Long aadharNumber = Long.valueOf(inputValidation.getInput("aadharNumber", "[2-9][0-9]{11}"));
			Long phoneNumber = Long.valueOf(inputValidation.getInput("phoneNumber", "^[5-9][0-9]{9}$"));
			String emailId = inputValidation.getInput("email address",
					"(^[a-zA-Z0-9.]+@[a-zA-Z0-9]+([.]+[a-zA-Z0-9-]+)+)");
			int grossSalary = Integer.valueOf(inputValidation.getInput("gross salary", "[1-9][0-9]+"));
			String password = inputValidation.getInput("password", ".+");
			employeeDetailsController.toAddEmployee(employeeId, employeeName, dateOfBirth, designation, aadharNumber,
					phoneNumber, emailId, grossSalary, password);
			System.out.print("Employee added Successfully!!");
		}
	}

	private void viewEmployee() {
		System.out.printf("%-15s %-20s %-15s %-20s %-15s %-20s", "Employee id", "Name", "Date of Birth", "Designation",
				"Phone Number", "Email id");
		List<Employee> employeeList = employeeDetailsController.toViewEmployee();
		for (Employee employee : employeeList) {
			System.out.printf("\n%-15s %-20s %-15s %-20s %-15s %-20s", employee.getEmployeeId(),
					employee.getEmployeeName(), employee.getDateOfBirth(), employee.getDesignation(),
					employee.getPhoneNumber(), employee.getEmailId());
		}
	}

	private void removeEmployee() {
		String employeeId = inputValidation.getInput("Employee id (Eg. Emp0001)", "(Emp\\d{4})");
		if (employeeDetailsController.isExistEmployeeId(employeeId)) {
			System.out.println("Are you sure about removing?");
			if (inputValidation.getInput("Yes/No", "Yes|No|yes|no").equalsIgnoreCase("yes"))
				System.out.println(employeeDetailsController.toRemoveEmployee(employeeId));
		} else {
			System.out.println("No such Employee id");
			removeEmployee();
		}

	}

	private void updateAllSalary() {
		String month = inputValidation.getInput("month (MM)", "(0?[1-9]|1[012])");
		String year = inputValidation.getInput("year (YYYY)", "2022");
		System.out.println(salaryController.toUpdateAllSalary(month, year));
	}

	private void viewSalary() {
		System.out.printf("%-10s %-10s %-15s %-20s %-15s %-15s ", "Month", "Year", "Employee Id", "Employee Name",
				"Net Salary", "Number of Days Present");
		Iterator<SalaryDetails> salaryDetailsIterator = salaryController.toViewSalary().iterator();
		while(salaryDetailsIterator.hasNext()) {
			SalaryDetails salaryDetails = salaryDetailsIterator.next();
			System.out.printf("\n%-10s %-10s %-15s %-20s %-15s %-15s", salaryDetails.getMonth(),
					salaryDetails.getYear(), salaryDetails.getEmployeeId(), salaryDetails.getEmployeeName(),
					salaryDetails.getNetSalary(), salaryDetails.getNumberOfDaysPresent());
		}
	}

	private void viewAttendance() {
		String date = inputValidation.getInput("date (YYYY/MM/DD)",
				"(2022)[/](0?[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])");
		System.out.printf("%-15s %-20s %-15s\n","Employee ID","Employee Name","Attendance");
		Queue<String> employeeList = attendanceController.getAttendance(date);
		while(!employeeList.isEmpty()) {
			System.out.printf("%-15s %-20s %-15s\n",employeeList.poll(), employeeList.poll(), employeeList.poll());
		}
		
	}
}
