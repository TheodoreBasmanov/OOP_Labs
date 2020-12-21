package Presentation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Business.EmployeeBusiness;
import Business.TaskBusiness;
import Business.TaskChangeBusiness;
import Business.TaskChangeStateBusiness;
import Business.TestTime;

public class TaskChangePresentationTest {

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
		TestTime time = new TestTime();
		TaskChangeBusiness taskChange = new TaskChangeStateBusiness(employee, time, TaskBusiness.State.Open,
				TaskBusiness.State.Active);
		TaskChangePresentation taskChangePres = TaskChangeBusiness.TaskChangePresentationAdapter.adapt(taskChange);
		System.out.println(taskChangePres.show());
	}

}
