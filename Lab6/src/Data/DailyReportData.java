package Data;

import java.util.ArrayList;

public class DailyReportData {
	public static ArrayList<DailyReportData> dailyReports = new ArrayList<DailyReportData>();
	public int id;
	public ArrayList<TaskData> resolvedTasks;
	public ArrayList<TaskData> changedTasks;
	public EmployeeData employee;

	public DailyReportData(int id, EmployeeData employee) {
		this.id = id;
		this.employee = employee;
		resolvedTasks = new ArrayList<TaskData>();
		changedTasks = new ArrayList<TaskData>();
		dailyReports.add(this);
	}

	public void addResolvedTask(TaskData task) {
		resolvedTasks.add(task);
	}

	public void addChangedTask(TaskData task) {
		changedTasks.add(task);
	}
	

}
