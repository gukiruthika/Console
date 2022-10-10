package View;

import java.util.List;
import controller.AttendanceController;
import model.Attendance;

public class ManageAttendance {

	private AttendanceController attendanceController;

	public ManageAttendance() {
		attendanceController = new AttendanceController();
	}

	public void checkIn(String employeeId) {
		System.out.println(attendanceController.toCheckIn(employeeId));
	}

	public void viewAttendance(String employeeId) {
		System.out.println("Month\t\tNumber of Days Present");
		List<Attendance> attendanceList = attendanceController.toViewAttendance(employeeId);
		for (Attendance attendance : attendanceList) {
			System.out.println(attendance.getMonth() + "\t\t" + attendance.getNumberOfDaysPresent());
		}
	}

	public void viewAttendance() {
		System.out.printf("%-10s %-10s %-15s %-15s ", "Month", "Year", "Employee Id", "Number of Days Present");
		attendanceController.toViewAttendance();
	}

}
