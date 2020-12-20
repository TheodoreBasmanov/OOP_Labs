package Business;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskManagerBusiness {
	public static TimeGiver time;
	public static ArrayList<TaskBusiness> tasks = new ArrayList<TaskBusiness>();

	public static void setTime(TimeGiver timeGiver) {
		time = timeGiver;
	}

	public static void createTask(String title, String description, EmployeeBusiness employee, EmployeeBusiness doer) {
		new TaskBusiness(title, description, employee, time, doer);
	}

	public static void changeTaskState(int id, EmployeeBusiness doer) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				tasks.get(i).changeState(doer);
			}
		}
	}

	public static void taskAddCommentary(int id, EmployeeBusiness doer, String comment) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				tasks.get(i).addCommentary(comment, doer);
			}
		}
	}

	public static void taskAssignEmployee(int id, EmployeeBusiness doer, EmployeeBusiness employee) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				tasks.get(i).assignEmployee(employee, doer);
			}
		}
	}

	public static TaskBusiness get(int id) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				return tasks.get(i);
			}
		}
		return null;
	}

	public static ArrayList<TaskBusiness> getCreationTimeBefore(LocalDateTime time) {
		ArrayList<TaskBusiness> result = new ArrayList<TaskBusiness>();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).creationTime.isBefore(time)) {
				result.add(tasks.get(i));
			}
		}
		return result;
	}

	public static ArrayList<TaskBusiness> getCreationTimeAfter(LocalDateTime time) {
		ArrayList<TaskBusiness> result = new ArrayList<TaskBusiness>();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).creationTime.isAfter(time)) {
				result.add(tasks.get(i));
			}
		}
		return result;
	}

	public static ArrayList<TaskBusiness> getChangeTimeBefore(LocalDateTime time) {
		ArrayList<TaskBusiness> result = new ArrayList<TaskBusiness>();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).lastChangeTime.isBefore(time)) {
				result.add(tasks.get(i));
			}
		}
		return result;
	}

	public static ArrayList<TaskBusiness> getChangeTimeAfter(LocalDateTime time) {
		ArrayList<TaskBusiness> result = new ArrayList<TaskBusiness>();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).lastChangeTime.isAfter(time)) {
				result.add(tasks.get(i));
			}
		}
		return result;
	}

	public static ArrayList<TaskBusiness> getAssignedToEmployee(EmployeeBusiness employee) {
		ArrayList<TaskBusiness> result = new ArrayList<TaskBusiness>();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).employee.equals(employee)) {
				result.add(tasks.get(i));
			}
		}
		return result;
	}

	public static ArrayList<TaskBusiness> getAssignedToEmployeesSubordinates(EmployeeBusiness employee) {
		ArrayList<TaskBusiness> result = new ArrayList<TaskBusiness>();
		if (employee.isLead) {
			for (int i = 0; i < employee.subordinates.size(); i++) {
				result.addAll(getAssignedToEmployee(employee.subordinates.get(i)));
				result.addAll(getAssignedToEmployeesSubordinates(employee.subordinates.get(i)));
			}
		}
		return result;
	}

	public static ArrayList<TaskBusiness> getChangedByEmployee(EmployeeBusiness employee) {
		ArrayList<TaskBusiness> result = new ArrayList<TaskBusiness>();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).didEmployeeChange(employee)) {
				result.add(tasks.get(i));
			}
		}
		return result;
	}

}
