package Presentation;

import java.time.LocalDate;
import java.util.ArrayList;

public class DailyReportPresentation {
	public static ArrayList<DailyReportPresentation> dailyReports = new ArrayList<DailyReportPresentation>();
	public int id;
	public ArrayList<TaskPresentation> resolvedTasks;
	public ArrayList<ChangedTaskPresentation> changedTasks;
	public EmployeePresentation employee;
	public LocalDate date;

	public DailyReportPresentation(int id, EmployeePresentation employee, LocalDate date) {
		this.id = id;
		this.employee = employee;
		this.date = date;
		dailyReports.add(this);
	}

	String show() {
		StringBuilder sb = new StringBuilder();
		sb.append("Date of the report: " + date);
		sb.append(System.getProperty("line.separator"));
		sb.append("Employee, who reports - " + employee.name);
		sb.append("Resolved tasks:");
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < resolvedTasks.size(); i++) {
			sb.append(resolvedTasks.get(i).show());
		}
		sb.append("Changed tasks:");
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < changedTasks.size(); i++) {
			sb.append(changedTasks.get(i).show());
		}
		return sb.toString();
	}
}
