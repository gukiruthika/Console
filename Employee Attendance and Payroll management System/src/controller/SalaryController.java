package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.EmployeeDetailsDatabase;
import model.SalaryDetails;

public class SalaryController {
	private SalaryDetails salaryDetails;
	private List<SalaryDetails> salaryDetailsList = EmployeeDetailsDatabase.getInstance().getSalaryDetailsList();

	public String toUpdateAllSalary(String month, String year) {
		if ((month + "/" + year).equals(new SimpleDateFormat("MM/YYYY").format(Calendar.getInstance().getTime())))
			return "Month haven't ended yet";

		for (SalaryDetails salaryDetails : salaryDetailsList) {
			if ((salaryDetails.getMonth() == Integer.valueOf(month)
					& (salaryDetails.getYear() == Integer.valueOf(year)))) {
				return "Salary already updated!!";
			}
		}

		for (int i = 0; i < EmployeeDetailsDatabase.getInstance().getEmployeeList().size(); i++) {
			String employeeId = EmployeeDetailsDatabase.getInstance().getEmployeeList().get(i).getEmployeeId();
			AttendanceController attendanceController = new AttendanceController();
			int numberOfDaysPresent = attendanceController.getNumberOfDaysPresent(employeeId, month, year);
			if (numberOfDaysPresent == 0) {
				continue;
			}
			salaryDetails = new SalaryDetails();
			salaryDetails.setMonth(Integer.valueOf(month));
			salaryDetails.setYear(Integer.valueOf(year));
			salaryDetails.setEmployeeId(employeeId);
			salaryDetails
					.setEmployeeName(EmployeeDetailsDatabase.getInstance().getEmployeeList().get(i).getEmployeeName());
			salaryDetails.setNumberOfWorkingDays(attendanceController.getNumberOfWorkingDays(month, year));
			salaryDetails.setNumberOfDaysPresent(numberOfDaysPresent);
			int netSalary = calculateNetSalary(numberOfDaysPresent,
					EmployeeDetailsDatabase.getInstance().getEmployeeList().get(i).getSalary());
			salaryDetails.setNetSalary(netSalary);
			EmployeeDetailsDatabase.getInstance().insertSalaryDetails(salaryDetails);
		}
		return "Salary updated successfully";
	}

	public List<SalaryDetails> toViewSalary() {
		return EmployeeDetailsDatabase.getInstance().getSalaryDetailsList();
	}

	int calculateNetSalary(int numberOfDaysPresent, int grossSalary) {
		int basicPay = ((grossSalary * numberOfDaysPresent * 40) / (25 * 100));
		int PFContribution = (basicPay < 15000) ? (basicPay * 12 / 100) : (15000 * 12 / 100);
		int ESIContribution = (((grossSalary * numberOfDaysPresent) / 25) <= 21000) ? (basicPay * 75 / 10000) : 0;
		int netSalary = ((grossSalary * numberOfDaysPresent) / 25) - PFContribution - ESIContribution;
		return netSalary;
	}

	public int checkSalaryUpdate(String employeeId, int month, int year) {
		List<SalaryDetails> salaryDetailsList = EmployeeDetailsDatabase.getInstance().getSalaryDetailsList();
		for (int i = 0; i < salaryDetailsList.size(); i++) {
			SalaryDetails eachSalaryDetails = salaryDetailsList.get(i);
			if ((eachSalaryDetails.getMonth() == month) & (eachSalaryDetails.getYear() == year)
					& (eachSalaryDetails.getEmployeeId().equals(employeeId))) {
				return i;
			}
		}
		return -1;
	}

	public Queue<String> generatePaySlip(int index, int month, int year) {
		SalaryDetails salaryDetails = EmployeeDetailsDatabase.getInstance().getSalaryDetailsList().get(index);
		Queue<String> paySlip = new LinkedList<String>();
		int numberOfDaysPresent = salaryDetails.getNumberOfDaysPresent();
		int grossSalary = EmployeeDetailsDatabase.getInstance().getEmployee(salaryDetails.getEmployeeId()).getSalary();
		int basicPay = (grossSalary * numberOfDaysPresent * 40) / (25 * 100);
		int houseRentAllowances = basicPay * 40 / 100;
		int conveyanceAllowances = (1000 * numberOfDaysPresent) / 25;
		int medicalAllowances = (1000 * numberOfDaysPresent) / 25;
		int otherAllowances = ((grossSalary * numberOfDaysPresent) / 25)
				- (basicPay + houseRentAllowances + conveyanceAllowances + medicalAllowances);
		int totalEarnings = basicPay + houseRentAllowances + conveyanceAllowances + medicalAllowances + otherAllowances;
		int pfContribution = (basicPay < 15000) ? (basicPay * 12 / 100) : (15000 * 12 / 100);
		int esiContribution = (totalEarnings <= 21000) ? (basicPay * 75 / 10000) : 0;
		int totalDeductions = pfContribution + esiContribution;
		int netSalary = totalEarnings - totalDeductions;
		paySlip.offer("Name\t\t\t:\t" + salaryDetails.getEmployeeName());
		paySlip.offer("Employee Id\t\t:\t" + salaryDetails.getEmployeeId());
		paySlip.offer("Designation\t\t:\t"
				+ EmployeeDetailsDatabase.getInstance().getEmployee(salaryDetails.getEmployeeId()).getDesignation());
		paySlip.offer("Number of Days Present\t:\t" + numberOfDaysPresent);
		paySlip.offer("Basic Salary\t\t:\t" + basicPay);
		paySlip.offer("HRA\t\t\t:\t" + houseRentAllowances);
		paySlip.offer("Conveyance Allowances\t:\t" + conveyanceAllowances);
		paySlip.offer("Medical Allowances\t:\t" + medicalAllowances);
		paySlip.offer("Other Allowances\t:\t" + otherAllowances);
		paySlip.offer("Gross Salary\t\t:\t" + totalEarnings);
		paySlip.offer("EPF\t\t\t:\t" + pfContribution);
		paySlip.offer("ESI\t\t\t:\t" + esiContribution);
		paySlip.offer("Total Deductions\t:\t" + totalDeductions);
		paySlip.offer("Net Salary\t\t:\t" + netSalary);
		return paySlip;

	}
}
