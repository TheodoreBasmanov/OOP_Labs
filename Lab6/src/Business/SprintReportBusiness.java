package Business;

import java.time.LocalDate;
import java.util.ArrayList;

import Data.EmployeeData;
import Data.SprintReportData;
import Data.TaskData;

public class SprintReportBusiness extends ReportBusiness {
	static int ID = 1;
	static ArrayList<SprintReportBusiness> employeesReportsForSprint;

	LocalDate startDate;
	LocalDate endDate;

	SprintReportBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(employee, time);
	}

	SprintReportBusiness(EmployeeBusiness employee, TimeGiver time, LocalDate startDate, LocalDate endDate) {
		super(employee, time);
		id = ID;
		ID++;
		this.startDate = startDate;
		this.endDate = endDate;
		employeesReportsForSprint.add(this);
		SprintReportDataAdapter.adapt(this);
	}

	@Override
	void createReport() {
		resolvedTasks = new ArrayList<TaskBusiness>();
		changedTasks = new ArrayList<TaskBusiness>();
		for (int i = 0; i < DailyReportBusiness.reportsForSprint.size(); i++) {
			resolvedTasks.addAll(DailyReportBusiness.reportsForSprint.get(i).resolvedTasks);
		}
		for (int i = DailyReportBusiness.reportsForSprint.size() - 1; i >= 0; i++) {
			for (int j = 0; j < DailyReportBusiness.reportsForSprint.get(i).changedTasks.size(); j++) {
				if (!TaskBusiness.taskIsOnTheList(changedTasks,
						DailyReportBusiness.reportsForSprint.get(i).changedTasks.get(j))) {
					changedTasks.add(DailyReportBusiness.reportsForSprint.get(i).changedTasks.get(j));
				}
			}
		}
		updateDataReport();
	}

	@Override
	void reCreateReport() {
		resolvedTasks.clear();
		changedTasks.clear();
		for (int i = 0; i < DailyReportBusiness.reportsForSprint.size(); i++) {
			resolvedTasks.addAll(DailyReportBusiness.reportsForSprint.get(i).resolvedTasks);
		}
		for (int i = DailyReportBusiness.reportsForSprint.size() - 1; i >= 0; i++) {
			for (int j = 0; j < DailyReportBusiness.reportsForSprint.get(i).changedTasks.size(); j++) {
				if (!TaskBusiness.taskIsOnTheList(changedTasks,
						DailyReportBusiness.reportsForSprint.get(i).changedTasks.get(j))) {
					changedTasks.add(DailyReportBusiness.reportsForSprint.get(i).changedTasks.get(j));
				}
			}
		}
		updateDataReport();
	}

	@Override
	void addResolvedTask(TaskBusiness task) throws ExceptionsBusiness.NotYourTask, ExceptionsBusiness.NotResolvedTask,
			ExceptionsBusiness.NotInTheSprint {
		if (!task.employee.equals(employee)) {
			throw new ExceptionsBusiness.NotYourTask();
		}
		if (!task.isResolved()) {
			throw new ExceptionsBusiness.NotResolvedTask();
		}
		if (!(task.getLastStateChange().changeTime.isBefore(startDate.atStartOfDay())
				&& task.getLastStateChange().changeTime.isAfter(endDate.plusDays(1).atStartOfDay()))) {
			throw new ExceptionsBusiness.NotToday();
		}
		resolvedTasks.add(task);
		updateDataReport();

	}

	@Override
	void addChangedTask(TaskBusiness task) throws ExceptionsBusiness.NotYourTask, ExceptionsBusiness.NotInTheSprint {
		if (!task.employee.equals(employee)) {
			throw new ExceptionsBusiness.NotYourTask();
		}
		if (!(task.lastChangeTime.isBefore(startDate.atStartOfDay())
				&& task.lastChangeTime.isAfter(endDate.plusDays(1).atStartOfDay()))) {
			throw new ExceptionsBusiness.NotToday();
		}
		changedTasks.add(task);
		updateDataReport();
	}

	public static class SprintReportDataAdapter {
		public static SprintReportData adapt(SprintReportBusiness businessSprintReport) {
			EmployeeData employee = EmployeeData.get(businessSprintReport.employee.id);
			SprintReportData dataSprintReport = new SprintReportData(businessSprintReport.id, employee);
			return dataSprintReport;
		}
	}

	public void updateDataReport() {
		int num = 0;
		for (int i = 0; i < SprintReportData.sprintReports.size(); i++) {
			if (SprintReportData.sprintReports.get(i).id == id) {
				num = 0;
			}
		}
		for (int i = 0; i < resolvedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < SprintReportData.sprintReports.get(num).resolvedTasks.size(); j++) {
				if (resolvedTasks.get(i).id == SprintReportData.sprintReports.get(num).resolvedTasks.get(j).id) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				SprintReportData.sprintReports.get(num).resolvedTasks.add(TaskData.get(resolvedTasks.get(i).id));
			}
		}
		for (int i = 0; i < changedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < SprintReportData.sprintReports.get(num).changedTasks.size(); j++) {
				if (changedTasks.get(i).id == SprintReportData.sprintReports.get(num).changedTasks.get(j).id) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				SprintReportData.sprintReports.get(num).changedTasks.add(TaskData.get(changedTasks.get(i).id));
			}
		}
	}

}
