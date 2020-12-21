package Presentation;

import java.time.LocalDate;
import java.util.ArrayList;

public class TeamLeadsSprintReportPresentation {
	public static ArrayList<TeamLeadsSprintReportPresentation> teamLeadsSprintReports = new ArrayList<TeamLeadsSprintReportPresentation>();
	public int id;
	public ArrayList<TaskPresentation> resolvedTasks;
	public ArrayList<ChangedTaskPresentation> changedTasks;
	public LocalDate startDate;
	public LocalDate endDate;
	public EmployeePresentation teamLead;

	public TeamLeadsSprintReportPresentation(int id, EmployeePresentation employee, LocalDate startDate,
			LocalDate endDate) {
		this.id = id;
		this.teamLead = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		resolvedTasks = new ArrayList<TaskPresentation>();
		changedTasks = new ArrayList<ChangedTaskPresentation>();
		teamLeadsSprintReports.add(this);
	}

	String show() {
		StringBuilder sb = new StringBuilder();
		sb.append("Start of the sprint: " + startDate + ". End of the sprint: " + endDate);
		sb.append(System.getProperty("line.separator"));
		sb.append("Teadm Lead - " + teamLead.name);
		sb.append(System.getProperty("line.separator"));
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

	public static TeamLeadsSprintReportPresentation get(int id) {
		for (int i = 0; i < teamLeadsSprintReports.size(); i++) {
			if (teamLeadsSprintReports.get(i).id == id) {
				return teamLeadsSprintReports.get(i);
			}
		}
		return null;
	}
}
