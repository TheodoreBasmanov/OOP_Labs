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
	ArrayList<TaskChangeBusiness> journal = new ArrayList<TaskChangeBusiness>();

	public enum State {
		Open, Active, Resolved;
		private static State[] vals = values();

		public State next() throws ExceptionsBusiness.CantChangeState {
			if (this.equals(State.Resolved)) {
				throw new ExceptionsBusiness.CantChangeState();
			}
			return vals[(this.ordinal() + 1) % vals.length];
		}
	}

	TaskBusiness(String title, String description, EmployeeBusiness employee, TimeGiver time, EmployeeBusiness doer) {
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
		journal.add(new TaskChangeBusiness(doer, time));
	}

	void addCommentary(String comment, EmployeeBusiness doer) {
		commentaries.add(comment);
		lastChangeTime = time.getDate();
		journal.add(new TaskChangeCommentBusiness(doer, time, comment));
	}

	void assignEmployee(EmployeeBusiness employee, EmployeeBusiness doer) {
		journal.add(new TaskChangeAssignBusiness(doer, time, this.employee, employee));
		this.employee = employee;
		lastChangeTime = time.getDate();
		TaskData.assignEmployee(this.id, employee.id);

	}

	void changeState(EmployeeBusiness doer) {
		journal.add(new TaskChangeStateBusiness(doer, time, this.state, this.state.next()));
		this.state = this.state.next();
	}

	boolean didEmployeeChange(EmployeeBusiness doer) {
		for (int i = 0; i < journal.size(); i++) {
			if (journal.get(i).employee.equals(doer)) {
				return true;
			}
		}
		return false;
	}

	public static class TaskDataAdapter {
		public static TaskData adapt(TaskBusiness businessTask) {
			EmployeeData employee = EmployeeData.get(businessTask.employee.id);
			TaskData dataTask = new TaskData(businessTask.title, businessTask.description, businessTask.id, employee);
			return dataTask;
		}
	}
}
