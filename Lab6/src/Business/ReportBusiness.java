package Business;

import java.util.ArrayList;

public abstract class ReportBusiness {
	int id;
	TimeGiver time;
	ArrayList<TaskBusiness> resolvedTasks;
	ArrayList<TaskBusiness> changedTasks;
	EmployeeBusiness employee;

	ReportBusiness(EmployeeBusiness employee, TimeGiver time) {
		this.employee = employee;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	abstract void createReport();

	abstract void reCreateReport();

	abstract void addResolvedTask(TaskBusiness task);

	abstract void addChangedTask(TaskBusiness task);
}
