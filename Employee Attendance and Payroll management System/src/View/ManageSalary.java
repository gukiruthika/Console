package View;


import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import controller.InputController;
import controller.SalaryController;

public class ManageSalary {
	private SalaryController salaryController;
//	private InputController inputController;

	public ManageSalary() {
		salaryController = new SalaryController();
//		inputController = new InputController();

	}

	public void updateAllSalary() {
		int month = Integer.valueOf(getInput("month (MM)", "(0?[1-9]|1[012])"));
		int year = Integer.valueOf(getInput("year (YYYY)", "\\d{4}"));
		salaryController.toUpdateAllSalary(month, year);
		System.out.print("Salary updated Successfully!!");
	}

	public void viewSalary() {
		System.out.printf("%-10s %-10s %-15s %-20s %-15s %-15s ", "Month", "Year", "Employee Id", "Employee Name",
				"Net Salary", "Number of Days Present");
		salaryController.toViewSalary();
	}

	public void viewPaySlip(String employeeId) {
		int month = Integer.valueOf(getInput("month (MM)", "(0[1-9]|1[012])"));
		int year = Integer.valueOf(getInput("year (YYYY)", "\\d{4}"));
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
			System.out.println(" - "+month+","+year);
			int line=0;
			while(!paySlip.isEmpty()) {
				if(line==4||line==5||line==11||line==12||line==15) {
					System.out.println("----------------------------------------------------");
				}
				if(line==4) {
					System.out.println("Earnings");
					line++;
					continue;
				}
				if(line==11) {
					System.out.println("Deductions");
					line++;
					continue;
				}
				System.out.println(paySlip.poll());
				line++;
			}

		}

		
//		System.out.println("Name of the Employee  : \t"
//				+ EmployeeDetailsDatabase.getInstance().getEmployee(employeeId).getEmployeeName());
//		System.out.println("Employee Id           : \t" + employeeId);
//		System.out.println("Designation           : \t"
//				+ EmployeeDetailsDatabase.getInstance().getEmployee(employeeId).getDesignation());
//		System.out.println("Number of Days Present: \t" + numberOfDaysPresent);

//		System.out.println("Basic Wages           : \t" + basicPay);
//		System.out.println("HRA                   : \t" + houseRentAllowances);
//		System.out.println("Conveynce Allowances  : \t" + conveyanceAllowances);
//		System.out.println("Medical Allowances    : \t" + medicalAllowances);
//		System.out.println("Other Allowances      : \t" + otherAllowances);
//		System.out.println("Gross Salary          : \t" + totalEarnings);

//		System.out.println("EPF                   : \t" + PFContribution);
//		System.out.println("ESI                   : \t" + ESIContribution);
//		System.out.println("Total Deductions      : \t" + totalDeductions);
//		System.out.println("----------------------------------------------------");
//		System.out.println("Net Salary            : \t" + netSalary);
//		System.out.println("----------------------------------------------------");

	}
	private Scanner scan = new Scanner(System.in);

	public String getInput(String field, String regex) {
		String input;
		System.out.print("\nEnter " + field + " : ");
		input = scan.nextLine();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches())
			return input;
		else {
			System.out.println("Invalid input!!");
			return getInput(field, regex);
		}
	}
}
