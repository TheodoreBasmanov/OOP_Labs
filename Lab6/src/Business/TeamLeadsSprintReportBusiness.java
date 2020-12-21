package Business;

import java.time.LocalDate;
import java.util.ArrayList;

import Data.EmployeeData;
import Data.TaskData;
import Data.TeamLeadsSprintReportData;
import Presentation.ChangedTaskPresentation;
import Presentation.EmployeePresentation;
import Presentation.TaskPresentation;
import Presentation.TeamLeadsSprintReportPresentation;

public class TeamLeadsSprintReportBusiness extends ReportBusiness {
	LocalDate startDate;
	LocalDate endDate;

	TeamLeadsSprintReportBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(EmployeeBusiness.getTeamLead(), time);
	}

	public TeamLeadsSprintReportBusiness(EmployeeBusiness employee, TimeGiver time, LocalDate startDate, LocalDate endDate) {
		super(EmployeeBusiness.getTeamLead(), time);
		this.startDate = startDate;
		this.endDate = endDate;
		TeamLeadsSprintReportDataAdapter.adapt(this);
		TeamLeadsSprintReportPresentationAdapter.adapt(this);
	}

	@Override
	public
	void createReport() {
		resolvedTasks = new ArrayList<TaskBusiness>();
		changedTasks = new ArrayList<TaskBusiness>();
		for (int i = 0; i < SprintReportBusiness.employeesReportsForSprint.size(); i++) {
			resolvedTasks.addAll(SprintReportBusiness.employeesReportsForSprint.get(i).resolvedTasks);
		}
		for (int i = SprintReportBusiness.employeesReportsForSprint.size() - 1; i >= 0; i--) {
			for (int j = 0; j < SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.size(); j++) {
				if (!TaskBusiness.taskIsOnTheList(changedTasks,
						SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.get(j))) {
					changedTasks.add(SprintReportBusiness.employeesReportsForSprint.get(i).changedTasks.get(j));
				}
			}
		}
		updateDataReport();
		updateTeamLeadsPresentationReport();
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
		updateTeamLeadsPresentationReport();
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
		updateTeamLeadsPresentationReport();
	}

	@Override
	void addChangedTask(TaskBusiness task) throws ExceptionsBusiness.NotInTheSprint {
		if (!(task.lastChangeTime.isBefore(startDate.atStartOfDay())
				&& task.lastChangeTime.isAfter(endDate.plusDays(1).atStartOfDay()))) {
			throw new ExceptionsBusiness.NotToday();
		}
		changedTasks.add(task);
		updateDataReport();
		updateTeamLeadsPresentationReport();
	}

	static void clearEmployeesReports() {
		SprintReportBusiness.employeesReportsForSprint.clear();
	}

	public static class TeamLeadsSprintReportDataAdapter {
		public static TeamLeadsSprintReportData adapt(TeamLeadsSprintReportBusiness businessTeamLeadsSprintReport) {
			EmployeeData employee = EmployeeData.get(businessTeamLeadsSprintReport.employee.getId());
			TeamLeadsSprintReportData dataTeamLeadsSprintReport = new TeamLeadsSprintReportData(
					businessTeamLeadsSprintReport.id, employee);
			return dataTeamLeadsSprintReport;
		}
	}

	public static class TeamLeadsSprintReportPresentationAdapter {
		public static TeamLeadsSprintReportPresentation adapt(
				TeamLeadsSprintReportBusiness businessTeamLeadsSprintReport) {
			EmployeePresentation employee = EmployeePresentation.get(businessTeamLeadsSprintReport.employee.getId());
			TeamLeadsSprintReportPresentation presentationTeamLeadsSprintReport = new TeamLeadsSprintReportPresentation(
					businessTeamLeadsSprintReport.id, employee, businessTeamLeadsSprintReport.startDate,
					businessTeamLeadsSprintReport.endDate);
			return presentationTeamLeadsSprintReport;
		}
	}

	public void updateDataReport() {
		int num = 0;
		for (int i = 0; i < TeamLeadsSprintReportData.teamLeadsSprintReports.size(); i++) {
			if (TeamLeadsSprintReportData.teamLeadsSprintReports.get(i).getId() == id) {
				num = 0;
			}
		}
		for (int i = 0; i < resolvedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).resolvedTasks.size(); j++) {
				if (resolvedTasks.get(i).getID() == TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).resolvedTasks
						.get(j).getId()) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).resolvedTasks
						.add(TaskData.get(resolvedTasks.get(i).getID()));
			}
		}
		for (int i = 0; i < changedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).changedTasks.size(); j++) {
				if (changedTasks.get(i).getID()== TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).changedTasks
						.get(j).getId()) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				TeamLeadsSprintReportData.teamLeadsSprintReports.get(num).changedTasks
						.add(TaskData.get(changedTasks.get(i).getID()));
			}
		}
	}

	public void updateTeamLeadsPresentationReport() {
		int num = 0;
		for (int i = 0; i < TeamLeadsSprintReportPresentation.teamLeadsSprintReports.size(); i++) {
			if (TeamLeadsSprintReportPresentation.teamLeadsSprintReports.get(i).getId() == id) {
				num = i;
			}
		}
		for (int i = 0; i < resolvedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < TeamLeadsSprintReportPresentation.teamLeadsSprintReports.get(num).resolvedTasks
					.size(); j++) {
				if (resolvedTasks
						.get(i).getID() == TeamLeadsSprintReportPresentation.teamLeadsSprintReports.get(num).resolvedTasks
								.get(j).getId()) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				TeamLeadsSprintReportPresentation.teamLeadsSprintReports.get(num).resolvedTasks
						.add(TaskPresentation.get(resolvedTasks.get(i).getID()));
			}
		}
		for (int i = 0; i < changedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < TeamLeadsSprintReportPresentation.teamLeadsSprintReports.get(num).changedTasks
					.size(); j++) {
				if (changedTasks
						.get(i).getID()== TeamLeadsSprintReportPresentation.teamLeadsSprintReports.get(num).changedTasks
								.get(j).task.getId()) {
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
				TeamLeadsSprintReportPresentation.teamLeadsSprintReports.get(num).changedTasks.add(task);
			}
		}
	}

}
