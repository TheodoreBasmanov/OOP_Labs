package Business;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class ReportBusiness {
	protected int id;
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

	public boolean checkTaskDay(LocalDate date, TaskBusiness task)
			throws ExceptionsBusiness.NotYourTask, ExceptionsBusiness.NotToday {
		if (!task.employee.equals(employee)) {
			throw new ExceptionsBusiness.NotYourTask();
		}
		if (!(task.lastChangeTime.isBefore(date.atStartOfDay())
				&& task.lastChangeTime.isAfter(date.plusDays(1).atStartOfDay()))) {
			throw new ExceptionsBusiness.NotToday();
		}
		return true;
	}
	public boolean checkTaskSprint(LocalDate startDate, LocalDate endDate, TaskBusiness task)
			throws ExceptionsBusiness.NotYourTask, ExceptionsBusiness.NotToday {
		if (!task.employee.equals(employee)) {
			throw new ExceptionsBusiness.NotYourTask();
		}
		if (!(task.lastChangeTime.isBefore(startDate.atStartOfDay())
				&& task.lastChangeTime.isAfter(endDate.plusDays(1).atStartOfDay()))) {
			throw new ExceptionsBusiness.NotToday();
		}
		return true;
	}
	

	abstract void createReport();

	abstract void reCreateReport();

	abstract void addResolvedTask(TaskBusiness task);

	abstract void addChangedTask(TaskBusiness task);
}
