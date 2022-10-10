package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Attendance;
import model.EmployeeDetailsDatabase;

public class AttendanceController {
	private Attendance attendance = new Attendance();
	private List<Attendance> attendanceList = EmployeeDetailsDatabase.getInstance().getAttendanceList();

	public String toCheckIn(String employeeId) {
		Date date = new Date();
		SimpleDateFormat monthForm = new SimpleDateFormat("YYYY/MM");
		String month = monthForm.format(date);
		SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/YYYY");
		String lastCheckIn = dateForm.format(date);
		for (Attendance attendance : attendanceList) {
			if ((attendance.getEmployeeId().equals(employeeId)) & (attendance.getMonth().equals(month))) {
				if (attendance.getLastCheckIn().equals(lastCheckIn)) {
					return "You have already checked in!!";
				}
				if (attendance.getNumberOfDaysPresent() > 23) {
					return "Exceeds Total Number of Workings days!!";
				}
				EmployeeDetailsDatabase.getInstance().updateAttendance(lastCheckIn, attendance);
				return "Checked in Sucessfully!!";
			}
		}
		attendance.setMonth(month);
		attendance.setEmployeeId(employeeId);
		attendance.setLastCheckIn(lastCheckIn);
		attendance.setNumberOfDaysPresent(1);
		EmployeeDetailsDatabase.getInstance().insertAttendance(attendance);
		return "Checked in Sucessfully!!";
	}

	public void toViewAttendance() {
		for (Attendance attendance : attendanceList) {
			System.out.printf("\n%-10s %-10s %-15s %-15s", attendance.getMonth(), attendance.getEmployeeId(),
					attendance.getNumberOfDaysPresent());
		}
	}

	public List<Attendance> toViewAttendance(String employeeId) {
		List<Attendance> temporaryAttendanceList = new ArrayList<Attendance>();
		for (Attendance attendance : attendanceList) {
			if (attendance.getEmployeeId().equals(employeeId)) {
				temporaryAttendanceList.add(attendance);
			}
		}
		return temporaryAttendanceList;
	}

}
