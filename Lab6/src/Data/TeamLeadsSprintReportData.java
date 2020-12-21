package Data;

import java.util.ArrayList;

public class TeamLeadsSprintReportData {
	public static ArrayList<TeamLeadsSprintReportData> teamLeadsSprintReports = new ArrayList<TeamLeadsSprintReportData>();
	private int id;
	public ArrayList<TaskData> resolvedTasks;
	public ArrayList<TaskData> changedTasks;
	public EmployeeData employee;

	public TeamLeadsSprintReportData(int id, EmployeeData employee) {
		this.id = id;
		this.employee = employee;
		resolvedTasks = new ArrayList<TaskData>();
		changedTasks = new ArrayList<TaskData>();
		teamLeadsSprintReports.add(this);
	}

	public void addResolvedTask(TaskData task) {
		resolvedTasks.add(task);
	}

	public void addChangedTask(TaskData task) {
		changedTasks.add(task);
	}

	public int getId() {
		return id;
	}

}
