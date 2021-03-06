package Business;

import java.time.LocalDate;
import java.util.ArrayList;

import Data.EmployeeData;
import Data.SprintReportData;
import Data.TaskData;
import Presentation.ChangedTaskPresentation;
import Presentation.EmployeePresentation;
import Presentation.SprintReportPresentation;
import Presentation.TaskPresentation;

public class SprintReportBusiness extends ReportBusiness {
	static int ID = 1;
	static ArrayList<SprintReportBusiness> employeesReportsForSprint = new ArrayList<SprintReportBusiness>();

	LocalDate startDate;
	LocalDate endDate;

	SprintReportBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(employee, time);
	}

	public SprintReportBusiness(EmployeeBusiness employee, TimeGiver time, LocalDate startDate, LocalDate endDate) {
		super(employee, time);
		id = ID;
		ID++;
		this.startDate = startDate;
		this.endDate = endDate;
		employeesReportsForSprint.add(this);
		SprintReportDataAdapter.adapt(this);
		SprintReportPresentationAdapter.adapt(this);
	}

	@Override
	public
	void createReport() {
		resolvedTasks = new ArrayList<TaskBusiness>();
		changedTasks = new ArrayList<TaskBusiness>();
		for (int i = 0; i < DailyReportBusiness.reportsForSprint.size(); i++) {
			resolvedTasks.addAll(DailyReportBusiness.reportsForSprint.get(i).resolvedTasks);
		}
		for (int i = DailyReportBusiness.reportsForSprint.size() - 1; i >= 0; i--) {
			for (int j = 0; j < DailyReportBusiness.reportsForSprint.get(i).changedTasks.size(); j++) {
				if (!TaskBusiness.taskIsOnTheList(changedTasks,
						DailyReportBusiness.reportsForSprint.get(i).changedTasks.get(j))) {
					changedTasks.add(DailyReportBusiness.reportsForSprint.get(i).changedTasks.get(j));
				}
			}
		}
		updateDataReport();
		updatePresentationReport();
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
		updatePresentationReport();
	}

	@Override
	void addResolvedTask(TaskBusiness task) throws ExceptionsBusiness.NotYourTask, ExceptionsBusiness.NotResolvedTask,
			ExceptionsBusiness.NotInTheSprint {
		checkTaskSprint(startDate, endDate, task);
		if (!task.isResolved()) {
			throw new ExceptionsBusiness.NotResolvedTask();
		}
		resolvedTasks.add(task);
		updateDataReport();
		updatePresentationReport();
	}

	@Override
	void addChangedTask(TaskBusiness task) throws ExceptionsBusiness.NotYourTask, ExceptionsBusiness.NotInTheSprint {
		checkTaskSprint(startDate, endDate, task);
		changedTasks.add(task);
		updateDataReport();
		updatePresentationReport();
	}

	public static class SprintReportDataAdapter {
		public static SprintReportData adapt(SprintReportBusiness businessSprintReport) {
			EmployeeData employee = EmployeeData.get(businessSprintReport.employee.getId());
			SprintReportData dataSprintReport = new SprintReportData(businessSprintReport.id, employee);
			return dataSprintReport;
		}
	}

	public static class SprintReportPresentationAdapter {
		public static SprintReportPresentation adapt(SprintReportBusiness businessSprintReport) {
			EmployeePresentation employee = EmployeePresentation.get(businessSprintReport.employee.getId());
			SprintReportPresentation presentationSprintReport = new SprintReportPresentation(businessSprintReport.id,
					employee, businessSprintReport.startDate, businessSprintReport.endDate);
			return presentationSprintReport;
		}
	}

	public void updateDataReport() {
		int num = 0;
		for (int i = 0; i < SprintReportData.sprintReports.size(); i++) {
			if (SprintReportData.sprintReports.get(i).getId() == id) {
				num = 0;
			}
		}
		for (int i = 0; i < resolvedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < SprintReportData.sprintReports.get(num).resolvedTasks.size(); j++) {
				if (resolvedTasks.get(i).getID() == SprintReportData.sprintReports.get(num).resolvedTasks.get(j).getId()) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				SprintReportData.sprintReports.get(num).resolvedTasks.add(TaskData.get(resolvedTasks.get(i).getID()));
			}
		}
		for (int i = 0; i < changedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < SprintReportData.sprintReports.get(num).changedTasks.size(); j++) {
				if (changedTasks.get(i).getID() == SprintReportData.sprintReports.get(num).changedTasks.get(j).getId()) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				SprintReportData.sprintReports.get(num).changedTasks.add(TaskData.get(changedTasks.get(i).getID()));
			}
		}
	}

	public void updatePresentationReport() {
		int num = 0;
		for (int i = 0; i < SprintReportPresentation.sprintReports.size(); i++) {
			if (SprintReportPresentation.sprintReports.get(i).getId() == id) {
				num = i;
			}
		}
		for (int i = 0; i < resolvedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < SprintReportPresentation.sprintReports.get(num).resolvedTasks.size(); j++) {
				if (resolvedTasks.get(i).getID() == SprintReportPresentation.sprintReports.get(num).resolvedTasks
						.get(j).getId()) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				SprintReportPresentation.sprintReports.get(num).resolvedTasks
						.add(TaskPresentation.get(resolvedTasks.get(i).getID()));
			}
		}
		for (int i = 0; i < changedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < SprintReportPresentation.sprintReports.get(num).changedTasks.size(); j++) {
				if (changedTasks
						.get(i).getID() == SprintReportPresentation.sprintReports.get(num).changedTasks.get(j).task.getId()) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				ChangedTaskPresentation task = new ChangedTaskPresentation(
						TaskPresentation.get(changedTasks.get(i).getID()));
				ArrayList<TaskChangeBusiness> changes = changedTasks.get(i).getWhatsChangedInASprit(startDate, endDate);
				for (int k = 0; k < changes.size(); k++) {
					task.changes.add(TaskChangeBusiness.TaskChangePresentationAdapter.adapt(changes.get(k)));
				}
				SprintReportPresentation.sprintReports.get(num).changedTasks.add(task);
			}
		}
	}

}
