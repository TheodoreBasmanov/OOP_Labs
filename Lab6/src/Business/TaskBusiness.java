package Business;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Data.EmployeeData;
import Data.TaskData;

public class TaskBusiness {
	private static int ID = 1;
	public int id;
	public String title;
	public String description;
	public EmployeeBusiness employee;
	public State state;
	public TimeGiver time;
	LocalDateTime creationTime;
	LocalDateTime lastChangeTime;
	ArrayList<String> commentaries = new ArrayList<String>();

	public enum State {
		Open, Active, Resolved
	}

	TaskBusiness(String title, String description, EmployeeBusiness employee, TimeGiver time) {
		id = ID;
		ID++;
		this.title = title;
		this.description = description;
		this.employee = employee;
		state = State.Open;
		this.time = time;
		creationTime = time.getDate();
		lastChangeTime = time.getDate();
		TaskManagerBusiness.tasks.add(this);
	}

	void addCommentary(String comment) {
		commentaries.add(comment);
		lastChangeTime = time.getDate();
	}

	void assignEmployee(EmployeeBusiness employee) {
		this.employee = employee;
		lastChangeTime = time.getDate();
		TaskData.assignEmployee(this.id, employee.id);
	}
	

	public static class TaskDataAdapter {
		public static TaskData adapt(TaskBusiness businessTask) {
			EmployeeData employee = EmployeeData.get(businessTask.employee.id);
			TaskData dataTask = new TaskData(businessTask.title, businessTask.description, businessTask.id, employee);
			return dataTask;
		}
	}
}
