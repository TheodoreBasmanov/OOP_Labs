package Business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Data.EmployeeData;
import Data.TaskData;
import Presentation.EmployeePresentation;
import Presentation.TaskPresentation;

public class TaskBusiness {
	public static ArrayList<TaskBusiness> tasks = new ArrayList<TaskBusiness>();
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
		TaskDataAdapter.adapt(this);
		TaskPresentationAdapter.adapt(this);
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

	boolean isResolved() {
		return state.equals(TaskBusiness.State.Resolved);
	}

	TaskChangeBusiness getLastStateChange() {
		for (int i = journal.size() - 1; i >= 0; i++) {
			if (journal.get(i).type.equals(TaskChangeBusiness.Type.StateChange)) {
				return journal.get(i);
			}
		}
		return null;
	}

	ArrayList<TaskChangeBusiness> getWhatsChangedInADay(LocalDate day) {
		ArrayList<TaskChangeBusiness> result = new ArrayList<TaskChangeBusiness>();
		for (int i = 0; i < journal.size(); i++) {
			if (journal.get(i).changeTime.isAfter(day.atStartOfDay())
					&& journal.get(i).changeTime.isBefore(day.plusDays(1).atStartOfDay())) {
				result.add(journal.get(i));
			}
		}
		return result;
	}

	ArrayList<TaskChangeBusiness> getWhatsChangedInASprit(LocalDate startDay, LocalDate endDay) {
		ArrayList<TaskChangeBusiness> result = new ArrayList<TaskChangeBusiness>();
		for (int i = 0; i < journal.size(); i++) {
			if (journal.get(i).changeTime.isAfter(startDay.atStartOfDay())
					&& journal.get(i).changeTime.isBefore(endDay.plusDays(1).atStartOfDay())) {
				result.add(journal.get(i));
			}
		}
		return result;
	}

	static boolean taskIsOnTheList(ArrayList<TaskBusiness> list, TaskBusiness task) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).id == task.id) {
				return true;
			}
		}
		return false;
	}

	public static void updatePresentationTask(int id) {
		TaskBusiness businessTask = null;
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				businessTask = tasks.get(i);
				break;
			}
		}
		for (int i = 0; i < TaskPresentation.tasks.size(); i++) {
			if (TaskPresentation.tasks.get(i).id == id) {
				if (businessTask.employee.id != TaskPresentation.tasks.get(i).employee.id) {
					TaskPresentation.tasks.get(i).employee = EmployeePresentation.get(businessTask.employee.id);
				}
				TaskPresentation.tasks.get(i).lastChangeTime = businessTask.lastChangeTime;
				if (businessTask.commentaries.size() != TaskPresentation.tasks.get(i).commentaries.size()) {
					for (int j = TaskPresentation.tasks.get(i).commentaries.size(); j < businessTask.commentaries
							.size(); j++) {
						TaskPresentation.tasks.get(i).commentaries.add(businessTask.commentaries.get(j));
					}
				}
			}
		}
	}

	public static void updatePresentationTaskJournal(int id) {
		TaskBusiness businessTask = null;
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				businessTask = tasks.get(i);
				break;
			}
		}
		for (int i = 0; i < TaskPresentation.tasks.size(); i++) {
			if (TaskPresentation.tasks.get(i).id == id) {
				if (TaskPresentation.tasks.get(i).journal.size() != businessTask.journal.size()) {
					for (int j = TaskPresentation.tasks.get(i).journal.size(); j < businessTask.journal.size(); j++) {
						TaskPresentation.tasks.get(i).journal.add(
								TaskChangeBusiness.TaskChangePresentationAdapter.adapt(businessTask.journal.get(j)));
					}
				}
			}
		}
	}

	public static class TaskDataAdapter {
		public static TaskData adapt(TaskBusiness businessTask) {
			EmployeeData employee = EmployeeData.get(businessTask.employee.id);
			TaskData dataTask = new TaskData(businessTask.title, businessTask.description, businessTask.id, employee);
			return dataTask;
		}
	}

	public static class TaskPresentationAdapter {
		public static TaskPresentation adapt(TaskBusiness businessTask) {
			EmployeePresentation employee = EmployeePresentation.get(businessTask.employee.id);
			TaskPresentation presentationTask = new TaskPresentation(businessTask.id, businessTask.title,
					businessTask.description, employee, businessTask.creationTime);
			return presentationTask;
		}
	}
}
