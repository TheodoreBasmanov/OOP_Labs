package Presentation;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Business.DailyReportBusiness;
import Business.EmployeeBusiness;
import Business.TaskBusiness;
import Business.TestTime;

public class DailyReportPresentationTest {

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
		DailyReportPresentation reportPres = DailyReportPresentation.get(report.id);
		System.out.println(reportPres.show());
	}

}
