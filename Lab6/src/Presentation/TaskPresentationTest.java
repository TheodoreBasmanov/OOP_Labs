package Presentation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Business.EmployeeBusiness;
import Business.TaskBusiness;
import Business.TestTime;

public class TaskPresentationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShow() {
		EmployeeBusiness.employees.clear();
		EmployeePresentation.employees.clear();
		EmployeeBusiness employee = new EmployeeBusiness("John Smith");
		EmployeeBusiness employee2 = new EmployeeBusiness("Mary Jane");
		TestTime time = new TestTime();
		TaskBusiness task = new TaskBusiness("Task1", "Some description", employee, time, employee);
		task.addCommentary("Some commentary", employee);
		task.assignEmployee(employee2, employee);
		TaskPresentation taskPres = TaskPresentation.get(task.id);
		System.out.println(taskPres.show());
	}
	@Test
	public void testShowJournal() {
		EmployeeBusiness.employees.clear();
		EmployeePresentation.employees.clear();
		EmployeeBusiness employee = new EmployeeBusiness("John Smith");
		EmployeeBusiness employee2 = new EmployeeBusiness("Mary Jane");
		TestTime time = new TestTime();
		TaskBusiness task = new TaskBusiness("Task1", "Some description", employee, time, employee);
		task.addCommentary("Some commentary", employee);
		task.assignEmployee(employee2, employee);
		TaskPresentation taskPres = TaskPresentation.get(task.id);
		System.out.println(taskPres.showJournal());
	}

}
