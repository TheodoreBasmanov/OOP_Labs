package Business;

import java.util.ArrayList;

public class TaskManagerBusiness {
	public static ArrayList<TaskBusiness> tasks = new ArrayList<TaskBusiness>();

	public static TaskBusiness get(int id) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).id == id) {
				return tasks.get(i);
			}
		}
		return null;
	}
	
}
