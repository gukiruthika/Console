package View;

import java.util.Queue;
import java.util.Scanner;
import controller.AttendanceController;
import controller.EmployeeDetailsController;
import controller.InputValidation;
import controller.SalaryController;

public class EmployeeMenu {
	private Scanner scan = new Scanner(System.in);
	private AttendanceController attendanceController;
	private EmployeeDetailsController employeeDetailsController;
	private InputValidation inputValidation;
	private SalaryController salaryController;

	EmployeeMenu() {
		attendanceController = new AttendanceController();
		employeeDetailsController = new EmployeeDetailsController();
		inputValidation = new InputValidation();
		salaryController = new SalaryController();
		
	}

	void init(String employeeId) {
		displayOptions(employeeId);
	}

	private void displayOptions(String employeeId) {
		String option;
		do {
			System.out.println();
			System.out.println("""
					1) Check in
					2) Pay Slip
					3) Attendance details
					4) Change Password
					5) Log out
					Enter option..
					""");
			option = scan.nextLine();
			switch (option) {
			case "1":
				checkIn(employeeId);
				break;
			case "2":
				viewPaySlip(employeeId);
				break;
			case "3":
				viewAttendance(employeeId);
				break;
			case "4":
				changePassword(employeeId);
				break;
			case "5":
				System.out.println("\nThank you!\n");
				break;
			default:
				System.out.println("\nInvalid option.. Please choose again!\n");
			}
		} while (!option.equals("5"));
	}

	private void changePassword(String employeeId) {
		String newPassword=inputValidation.getInput("new password", ".+");
		employeeDetailsController.toChangePassword(employeeId, newPassword);
		System.out.println("Password changed Successfully");
	}

	private void checkIn(String employeeId) {
		System.out.println(attendanceController.toCheckIn(employeeId));
	}

	private void viewAttendance(String employeeId) {
		String month = inputValidation.getInput("month (MM)", "(0[1-9]|1[012])");
		String year = inputValidation.getInput("year (YYYY)", "\\d{4}");
		int numberOfDaysPresent=attendanceController.getNumberOfDaysPresent(employeeId, month, year);
		System.out.println("Number of days present -> "+ numberOfDaysPresent);
		System.out.println("Number of days absent -> "+ attendanceController.getNumberOfDaysAbsent(numberOfDaysPresent, month, year));
	}

	private void viewPaySlip(String employeeId) {
		int month = Integer.valueOf(inputValidation.getInput("month (MM)", "(0[1-9]|1[012])"));
		int year = Integer.valueOf(inputValidation.getInput("year (YYYY)", "\\d{4}"));
		int index = salaryController.checkSalaryUpdate(employeeId, month, year);
		if (index == -1) {
			System.out.println("Salary has not updated!!");
		} else {
			Queue<String> paySlip = salaryController.generatePaySlip(index, month, year);
			System.out.print("""
							   GUK Enterprise
						No.20,Samba Street, Tenkasi-627811
							     Pay Slip
					""");
			System.out.println(" - " + month + "," + year);
			int line = 0;
			while (!paySlip.isEmpty()) {
				if (line == 4 || line == 5 || line == 11 || line == 12 || line == 15) {
					System.out.println("----------------------------------------------------");
				}
				if (line == 4) {
					System.out.println("Earnings");
					line++;
					continue;
				}
				if (line == 11) {
					System.out.println("Deductions");
					line++;
					continue;
				}
				System.out.println(paySlip.poll());
				line++;
			}

		}

	}
}
