package Presentation;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Business.DailyReportBusiness;
import Business.EmployeeBusiness;
import Business.SprintReportBusiness;
import Business.TaskBusiness;
import Business.TeamLeadsSprintReportBusiness;
import Business.TestTime;

public class TeamLeadsSprintReportPresentationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		EmployeeBusiness.employees.clear();
		EmployeePresentation.employees.clear();
		EmployeeBusiness employee = new EmployeeBusiness("John Smith");
		employee.makeTeamLead();
		EmployeeBusiness employee1 = new EmployeeBusiness("Mary Jane");
		TestTime time = new TestTime();
		time.setDate(LocalDateTime.of(2020, 12, 21, 9, 00));
		TaskBusiness task = new TaskBusiness("Task1", "Some description", employee, time, employee);
		task.addCommentary("Some commentary", employee);
		task.changeState(employee);
		task.addCommentary("Some other commentary", employee);
		task.changeState(employee);
		TaskBusiness task1 = new TaskBusiness("Task2", "Some other description", employee, time, employee);
		task1.addCommentary("Some commentary to other task", employee);
		task1.changeState(employee);
		time.setDate(LocalDateTime.of(2020, 12, 21, 00, 00));
		DailyReportBusiness report = new DailyReportBusiness(employee, time);
		report.createReport();
		time.setDate(LocalDateTime.of(2020, 12, 22, 9, 00));
		TaskBusiness task2 = new TaskBusiness("Task2", "Some description next day", employee1, time, employee);
		task2.addCommentary("Some commentary next day", employee1);
		task2.changeState(employee);
		task2.addCommentary("Some other commentary next day", employee1);
		task2.changeState(employee);
		TaskBusiness task3 = new TaskBusiness("Task3", "Some other description next day", employee1, time, employee);
		task3.addCommentary("Some commentary to other task next day", employee1);
		task3.changeState(employee1);
		time.setDate(LocalDateTime.of(2020, 12, 22, 00, 00));
		DailyReportBusiness report1 = new DailyReportBusiness(employee1, time);
		report1.createReport();
		SprintReportBusiness sprReport = new SprintReportBusiness(employee, time,
				LocalDateTime.of(2020, 12, 20, 00, 00).toLocalDate(),
				LocalDateTime.of(2020, 12, 25, 00, 00).toLocalDate());
		sprReport.createReport();
		SprintReportBusiness sprReport1 = new SprintReportBusiness(employee1, time,
				LocalDateTime.of(2020, 12, 20, 00, 00).toLocalDate(),
				LocalDateTime.of(2020, 12, 25, 00, 00).toLocalDate());
		sprReport1.createReport();
		TeamLeadsSprintReportBusiness tmlSprReport = new TeamLeadsSprintReportBusiness(employee, time,
				LocalDateTime.of(2020, 12, 20, 00, 00).toLocalDate(),
				LocalDateTime.of(2020, 12, 25, 00, 00).toLocalDate());
		tmlSprReport.createReport();
		TeamLeadsSprintReportPresentation tmlSprReportPresent = TeamLeadsSprintReportPresentation
				.get(tmlSprReport.getId());
		System.out.println(tmlSprReportPresent.show());
	}

}
