package Business;

import java.time.LocalDate;
import java.util.ArrayList;

import Data.EmployeeData;
import Data.TaskData;
import Data.TeamLeadsSprintReportData;

public class TeamLeadsSprintReportBusiness extends ReportBusiness {
	LocalDate startDate;
	LocalDate endDate;

	TeamLeadsSprintReportBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(EmployeeBusiness.getTeamLead(), time);
	}

	TeamLeadsSprintReportBusiness(EmployeeBusiness employee, TimeGiver time, LocalDate startDate, LocalDate endDate) {
		super(EmployeeBusiness.getTeamLead(), time);
		this.startDate = startDate;
		this.endDate = endDate;
		TeamLeadsSprintReportDataAdapter.adapt(this);
	}

	@Override
	void createReport() {
		resolvedTasks = new ArrayList<TaskBusiness>();
		changedTasks = new ArrayList<TaskBusiness>();
		for (int i = 0; i < SprintReportBusiness.employeesReportsForSprint.size(); i++) {
			resolvedTasks.addAll(SprintReportBusiness.employeesReportsForSprint.get(i).resolvedTasks);
		}
		for (int i = SprintReportBusiness.employeesReportsForSprint.size() - 1; i >= 0; i++) {
			for (int j = 0; j < SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.size(); j++) {
				if (!TaskBusiness.taskIsOnTheList(changedTasks,
						SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.get(j))) {
					changedTasks.add(SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.get(j));
				}
			}
		}
		updateDataReport();
	}

	@Override
	void reCreateReport() {
		resolvedTasks.clear();
		changedTasks.clear();
		for (int i = 0; i < SprintReportBusiness.employeesReportsForSprint.size(); i++) {
			resolvedTasks.addAll(SprintReportBusiness.employeesReportsForSprint.get(i).resolvedTasks);
		}
		for (int i = SprintReportBusiness.employeesReportsForSprint.size() - 1; i >= 0; i++) {
			for (int j = 0; j < SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.size(); j++) {
				if (!TaskBusiness.taskIsOnTheList(changedTasks,
						SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.get(j))) {
					changedTasks.add(SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.get(j));
				}
			}
		}
		updateDataReport();
	}

	@Override
	void addResolvedTask(TaskBusiness task)
			throws ExceptionsBusiness.NotResolvedTask, ExceptionsBusiness.NotInTheSprint {
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
	void addChangedTask(TaskBusiness task) throws ExceptionsBusiness.NotInTheSprint {
		if (!(task.lastChangeTime.isBefore(startDate.atStartOfDay())
				&& task.lastChangeTime.isAfter(endDate.plusDays(1).atStartOfDay()))) {
			throw new ExceptionsBusiness.NotToday();
		}
		changedTasks.add(task);
		updateDataReport();
	}

	static void clearEmployeesReports() {
		SprintReportBusiness.employeesReportsForSprint.clear();
	}

	public static class TeamLeadsSprintReportDataAdapter {
		public static TeamLeadsSprintReportData adapt(TeamLeadsSprintReportBusiness businessTeamLeadsSprintReport) {
			EmployeeData employee = EmployeeData.get(businessTeamLeadsSprintReport.employee.id);
			TeamLeadsSprintReportData dataTeamLeadsSprintReport = new TeamLeadsSprintReportData(
					businessTeamLeadsSprintReport.id, employee);
			return dataTeamLeadsSprintReport;
		}
	}

	public void updateDataReport() {
		int num = 0;
		for (int i = 0; i < TeamLeadsSprintReportData.teamLeadsSprintReports.size(); i++) {
			if (TeamLeadsSprintReportData.teamLeadsSprintReports.get(i).id == id) {
				num = 0;
			}
		}
		for (int i = 0; i < resolvedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).resolvedTasks.size(); j++) {
				if (resolvedTasks.get(i).id == TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).resolvedTasks
						.get(j).id) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).resolvedTasks
						.add(TaskData.get(resolvedTasks.get(i).id));
			}
		}
		for (int i = 0; i < changedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).changedTasks.size(); j++) {
				if (changedTasks.get(i).id == TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).changedTasks
						.get(j).id) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).changedTasks
						.add(TaskData.get(changedTasks.get(i).id));
			}
		}
	}

}
