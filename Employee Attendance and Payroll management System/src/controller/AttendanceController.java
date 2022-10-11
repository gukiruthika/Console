package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Attendance;
import model.Employee;
import model.EmployeeDetailsDatabase;

public class AttendanceController {
	private Attendance attendance = new Attendance();
	private List<Attendance> attendanceList = EmployeeDetailsDatabase.getInstance().getAttendanceList();
	private List<Employee> employeeList = EmployeeDetailsDatabase.getInstance().getEmployeeList();

	public String toCheckIn(String employeeId) {
		String checkInDate = new SimpleDateFormat("YYYY/MM/dd").format(Calendar.getInstance().getTime());
		for (Attendance attendance : attendanceList) {
			if (attendance.getEmployeeId().equals(employeeId)) {
				if (attendance.getDate().equals(checkInDate)) {
					return "You have already checked in!!";
				}
			}
		}
		attendance.setDate(checkInDate);
		attendance.setEmployeeId(employeeId);
		EmployeeDetailsDatabase.getInstance().insertAttendance(attendance);
		return "Checked in Sucessfully!!";
	}

	public List<Attendance> toViewAttendance(String month, String year) {
		List<Attendance> temporaryAttendanceList = new ArrayList<Attendance>();
		for (Attendance attendance : attendanceList) {
			Pattern pattern = Pattern.compile(year + "/" + month + "/(0[1-9]|[12][0-9]|3[01])");
			Matcher matcher = pattern.matcher(attendance.getDate());
			if (matcher.matches())
				temporaryAttendanceList.add(attendance);
		}
		return temporaryAttendanceList;
	}

	public Queue<String> getAttendance(String date) {
		List<Attendance> temporaryAttendanceList = new ArrayList<Attendance>();
		Queue<String> temporaryEmployeeList = new LinkedList<String>();
		for (Attendance attendance : attendanceList) {
			if (attendance.getDate().equals(date)) {
				temporaryAttendanceList.add(attendance);
			}
		}
		boolean check = true;
		for (Employee employee : employeeList) {
			for (Attendance attendance : temporaryAttendanceList) {

				if (attendance.getEmployeeId().equals(employee.getEmployeeId())) {
					temporaryEmployeeList.add(employee.getEmployeeId());
					temporaryEmployeeList.add(employee.getEmployeeName());
					temporaryEmployeeList.add("Present");
					check = false;
				}
			}
			if (check) {
				temporaryEmployeeList.add(employee.getEmployeeId());
				temporaryEmployeeList.add(employee.getEmployeeName());
				temporaryEmployeeList.add("Absent");
			}
		}
		return temporaryEmployeeList;
	}

	public List<Attendance> toViewAttendance(String employeeId, String month, String year) {
		List<Attendance> temporaryAttendanceList = new ArrayList<Attendance>();
		for (Attendance attendance : attendanceList) {
			if (attendance.getEmployeeId().equals(employeeId)) {
				Pattern pattern = Pattern.compile(year + "/" + month + "/(0[1-9]|[12][0-9]|3[01])");
				Matcher matcher = pattern.matcher(attendance.getDate());
				if (matcher.matches())
					temporaryAttendanceList.add(attendance);
			}
		}
		return temporaryAttendanceList;
	}

	public int getNumberOfDaysPresent(String employeeId, String month, String year) {
		return toViewAttendance(employeeId, month, year).size();
	}

	public int getNumberOfDaysAbsent(int numberOfDaysPresent, String month, String year) {
		return getNumberOfWorkingDays(month, year) - numberOfDaysPresent;
	}

	public int getNumberOfWorkingDays(String month, String year) {
		if (month.equals("08") && year.equals("2022"))
			return 24;
		if (month.equals("09") && year.equals("2022"))
			return 24;
		if (month.equals("10") && year.equals("2022"))
			return 8;
		return 0;
	}

}
