package Presentation;

import java.util.ArrayList;

public class ChangedTaskPresentation {
	public TaskPresentation task;
	public ArrayList<TaskChangePresentation> changes;

	public ChangedTaskPresentation(TaskPresentation task) {
		this.task = task;
		changes = new ArrayList<TaskChangePresentation>();
	}

	String show() {
		StringBuilder sb = new StringBuilder();
		sb.append(task.show());
		sb.append(System.getProperty("line.separator"));
		sb.append("Changes made: ");
		
		for (int i = 0; i < changes.size(); i++) {
			sb.append(changes.get(i).show());
		}
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
}
