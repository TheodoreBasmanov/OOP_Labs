package Business;

import java.time.LocalDate;
import java.util.ArrayList;

import Data.DailyReportData;
import Data.EmployeeData;
import Data.TaskData;

public class DailyReportBusiness extends ReportBusiness {
	static int ID = 1;
	static ArrayList<DailyReportBusiness> reportsForSprint;
	LocalDate date;

	DailyReportBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(employee, time);
		id = ID;
		ID++;
		date = time.getDate().toLocalDate();
		reportsForSprint.add(this);
		DailyReportDataAdapter.adapt(this);
	}

	@Override
	void createReport() {
		resolvedTasks = new ArrayList<TaskBusiness>();
		changedTasks = new ArrayList<TaskBusiness>();
		resolvedTasks.addAll(TaskManagerBusiness.getWhatsDoneByEmployeeInADay(employee, date));
		changedTasks.addAll(TaskManagerBusiness.getWhatsChangedByEmployeeInADay(employee, date));
		updateDataReport();
	}

	@Override
	void reCreateReport() {
		resolvedTasks.clear();
		changedTasks.clear();
		resolvedTasks.addAll(TaskManagerBusiness.getWhatsDoneByEmployeeInADay(employee, date));
		changedTasks.addAll(TaskManagerBusiness.getWhatsChangedByEmployeeInADay(employee, date));
		updateDataReport();
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

	public void updateDataReport() {
		int num = 0;
		for (int i = 0; i < DailyReportData.dailyReports.size(); i++) {
			if (DailyReportData.dailyReports.get(i).id == id) {
				num = 0;
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

}
