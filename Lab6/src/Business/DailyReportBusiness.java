package Business;

import java.time.LocalDate;
import java.util.ArrayList;

import Data.DailyReportData;
import Data.EmployeeData;
import Data.TaskData;
import Presentation.ChangedTaskPresentation;
import Presentation.DailyReportPresentation;
import Presentation.EmployeePresentation;
import Presentation.TaskPresentation;

public class DailyReportBusiness extends ReportBusiness {
	static int ID = 1;
	static ArrayList<DailyReportBusiness> reportsForSprint = new ArrayList<DailyReportBusiness>();
	LocalDate date;

	public DailyReportBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(employee, time);
		id = ID;
		ID++;
		date = time.getDate().toLocalDate();
		reportsForSprint.add(this);
		DailyReportDataAdapter.adapt(this);
		DailyReportPresentationAdapter.adapt(this);
	}

	@Override
	public
	void createReport() {
		resolvedTasks = new ArrayList<TaskBusiness>();
		changedTasks = new ArrayList<TaskBusiness>();
		resolvedTasks.addAll(TaskManagerBusiness.getWhatsDoneByEmployeeInADay(employee, date));
		changedTasks.addAll(TaskManagerBusiness.getWhatsChangedByEmployeeInADay(employee, date));
		updateDataReport();
		updatePresentationReport();
	}

	@Override
	void reCreateReport() {
		resolvedTasks.clear();
		changedTasks.clear();
		resolvedTasks.addAll(TaskManagerBusiness.getWhatsDoneByEmployeeInADay(employee, date));
		changedTasks.addAll(TaskManagerBusiness.getWhatsChangedByEmployeeInADay(employee, date));
		updateDataReport();
		updatePresentationReport();
	}

	@Override
	void addResolvedTask(TaskBusiness task)
			throws ExceptionsBusiness.NotYourTask, ExceptionsBusiness.NotResolvedTask, ExceptionsBusiness.NotToday {
		if (!task.employee.equals(employee)) {
			throw new ExceptionsBusiness.NotYourTask();
		}
		if (!task.isResolved()) {
			throw new ExceptionsBusiness.NotResolvedTask();
		}
		if (!(task.getLastStateChange().changeTime.isBefore(date.atStartOfDay())
				&& task.getLastStateChange().changeTime.isAfter(date.plusDays(1).atStartOfDay()))) {
			throw new ExceptionsBusiness.NotToday();
		}
		resolvedTasks.add(task);
		updateDataReport();
		updatePresentationReport();
	}

	@Override
	void addChangedTask(TaskBusiness task) throws ExceptionsBusiness.NotYourTask, ExceptionsBusiness.NotToday {
		if (!task.employee.equals(employee)) {
			throw new ExceptionsBusiness.NotYourTask();
		}
		if (!(task.lastChangeTime.isBefore(date.atStartOfDay())
				&& task.lastChangeTime.isAfter(date.plusDays(1).atStartOfDay()))) {
			throw new ExceptionsBusiness.NotToday();
		}
		changedTasks.add(task);
		updateDataReport();
		updatePresentationReport();
	}

	static void endSprint() {
		reportsForSprint.clear();
	}

	public static class DailyReportDataAdapter {
		public static DailyReportData adapt(DailyReportBusiness businessDailyReport) {
			EmployeeData employee = EmployeeData.get(businessDailyReport.employee.id);
			DailyReportData dataDailyReport = new DailyReportData(businessDailyReport.id, employee);
			return dataDailyReport;
		}
	}

	public static class DailyReportPresentationAdapter {
		public static DailyReportPresentation adapt(DailyReportBusiness businessDailyReport) {
			EmployeePresentation employee = EmployeePresentation.get(businessDailyReport.employee.id);
			DailyReportPresentation presentationDailyReport = new DailyReportPresentation(businessDailyReport.id,
					employee, businessDailyReport.date);
			return presentationDailyReport;
		}
	}

	public void updateDataReport() {
		int num = 0;
		for (int i = 0; i < DailyReportData.dailyReports.size(); i++) {
			if (DailyReportData.dailyReports.get(i).id == id) {
				num = i;
			}
		}
		for (int i = 0; i < resolvedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < DailyReportData.dailyReports.get(num).resolvedTasks.size(); j++) {
				if (resolvedTasks.get(i).id == DailyReportData.dailyReports.get(num).resolvedTasks.get(j).id) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				DailyReportData.dailyReports.get(num).resolvedTasks.add(TaskData.get(resolvedTasks.get(i).id));
			}
		}
		for (int i = 0; i < changedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < DailyReportData.dailyReports.get(num).changedTasks.size(); j++) {
				if (changedTasks.get(i).id == DailyReportData.dailyReports.get(num).changedTasks.get(j).id) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				DailyReportData.dailyReports.get(num).changedTasks.add(TaskData.get(changedTasks.get(i).id));
			}
		}
	}

	public void updatePresentationReport() {
		int num = 0;
		for (int i = 0; i < DailyReportPresentation.dailyReports.size(); i++) {
			if (DailyReportPresentation.dailyReports.get(i).id == id) {
				num = i;
			}
		}
		for (int i = 0; i < resolvedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < DailyReportPresentation.dailyReports.get(num).resolvedTasks.size(); j++) {
				
				if (resolvedTasks.get(i).id == DailyReportPresentation.dailyReports.get(num).resolvedTasks.get(j).id) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				DailyReportPresentation.dailyReports.get(num).resolvedTasks
						.add(TaskPresentation.get(resolvedTasks.get(i).id));
			}
		}
		for (int i = 0; i < changedTasks.size(); i++) {
			boolean taskIsAlreadyIn = false;
			for (int j = 0; j < DailyReportPresentation.dailyReports.get(num).changedTasks.size(); j++) {
				if (changedTasks
						.get(i).id == DailyReportPresentation.dailyReports.get(num).changedTasks.get(j).task.id) {
					taskIsAlreadyIn = true;
				}
			}
			if (!taskIsAlreadyIn) {
				ChangedTaskPresentation task = new ChangedTaskPresentation(
						TaskPresentation.get(changedTasks.get(i).id));
				ArrayList<TaskChangeBusiness> changes = changedTasks.get(i).getWhatsChangedInADay(date);
				for (int k = 0; k < changes.size(); k++) {
					task.changes.add(TaskChangeBusiness.TaskChangePresentationAdapter.adapt(changes.get(k)));
				}
				DailyReportPresentation.dailyReports.get(num).changedTasks.add(task);
			}
		}
	}

}
