package Presentation;

import java.time.LocalDate;
import java.util.ArrayList;

public class SprintReportPresentation {
	public static ArrayList<SprintReportPresentation> sprintReports = new ArrayList<SprintReportPresentation>();
	public int id;
	public ArrayList<TaskPresentation> resolvedTasks;
	public ArrayList<ChangedTaskPresentation> changedTasks;
	public EmployeePresentation employee;
	public LocalDate startDate;
	public LocalDate endDate;

	public SprintReportPresentation(int id, EmployeePresentation employee, LocalDate startDate, LocalDate endDate) {
		this.id = id;
		this.employee = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		sprintReports.add(this);
	}

	String show() {
		StringBuilder sb = new StringBuilder();
		sb.append("Start of the sprint: " + startDate + ". End of the sprint: " + endDate);
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
