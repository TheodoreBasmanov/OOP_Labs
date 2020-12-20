package Data;

import java.util.ArrayList;

public class TaskData {
	public static ArrayList<TaskData> tasks = new ArrayList<TaskData>();
	public int id;
	public String title;
	public String description;
	public EmployeeData employee;

	public TaskData(String title, String description, int id, EmployeeData employee) {
		this.title = title;
		this.description = description;
		this.id = id;
		this.employee = employee;
		tasks.add(this);
	}

	public static void assignEmployee(int taskId, int employeeId) {
		EmployeeData employee = EmployeeData.get(employeeId);
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == taskId) {
				tasks.get(i).employee = employee;
			}
		}
	}

	public static TaskData get(int id) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				return tasks.get(i);
			}
		}
		return null;
	}
}
