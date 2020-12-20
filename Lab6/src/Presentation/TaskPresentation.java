package Presentation;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Business.TaskBusiness;
import Business.TaskBusiness.State;

public class TaskPresentation {
	public static ArrayList<TaskPresentation> tasks = new ArrayList<TaskPresentation>();
	public int id;
	public String title;
	public String description;
	public EmployeePresentation employee;
	public String state;
	public LocalDateTime creationTime;
	public LocalDateTime lastChangeTime;
	public ArrayList<String> commentaries = new ArrayList<String>();
	public ArrayList<TaskChangePresentation> journal = new ArrayList<TaskChangePresentation>();

	public TaskPresentation(int id, String title, String description, EmployeePresentation employee,
			LocalDateTime creationTime) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.employee = employee;
		state = State.Open.toString();
		this.creationTime = creationTime;
		lastChangeTime = creationTime;
		tasks.add(this);
	}

	String show() {
		TaskBusiness.updatePresentationTask(this.id);
		StringBuilder sb = new StringBuilder();
		sb.append("Task title: " + title);
		sb.append(System.getProperty("line.separator"));
		sb.append("Task description: " + description);
		sb.append(System.getProperty("line.separator"));
		sb.append("Assigned employee - " + employee.name);
		sb.append(System.getProperty("line.separator"));
		sb.append("Task state - " + state);
		sb.append(System.getProperty("line.separator"));
		sb.append("Task creation time: " + creationTime);
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < commentaries.size(); i++) {
			sb.append(commentaries.get(i));
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	String showJournal() {
		TaskBusiness.updatePresentationTaskJournal(this.id);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < journal.size(); i++) {
			sb.append(journal.get(i).show());
		}
		return sb.toString();
	}

	public static TaskPresentation get(int id) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				return tasks.get(i);
			}
		}
		return null;
	}

}
