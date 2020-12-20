package Data;

import java.util.ArrayList;

public class SprintReportData {
	public static ArrayList<SprintReportData> sprintReports = new ArrayList<SprintReportData>();
	public int id;
	public ArrayList<TaskData> resolvedTasks;
	public ArrayList<TaskData> changedTasks;
	public EmployeeData employee;

	public SprintReportData(int id, EmployeeData employee) {
		this.id = id;
		this.employee = employee;
		resolvedTasks = new ArrayList<TaskData>();
		changedTasks = new ArrayList<TaskData>();
		sprintReports.add(this);
	}

	public void addResolvedTask(TaskData task) {
		resolvedTasks.add(task);
	}

	public void addChangedTask(TaskData task) {
		changedTasks.add(task);
	}
}
