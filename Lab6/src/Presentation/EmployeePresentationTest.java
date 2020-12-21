package Presentation;
import Business.EmployeeBusiness;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeePresentationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTeamLead() {
		EmployeeBusiness.teamLeadExists = false;
		EmployeeBusiness.employees.clear();
		EmployeePresentation.employees.clear();
		EmployeeBusiness teamLead = new EmployeeBusiness("John Smith");
		teamLead.makeTeamLead();
		EmployeeBusiness lead1 = new EmployeeBusiness("Mary Gray");
		lead1.setBoss(teamLead);
		EmployeeBusiness employee1 = new EmployeeBusiness("Paul Table");
		employee1.setBoss(teamLead);
		EmployeeBusiness employee2 = new EmployeeBusiness("Jane Spoon");
		employee2.setBoss(lead1);
		EmployeeBusiness employee3 = new EmployeeBusiness("Damien Firel");
		employee3.setBoss(lead1);
		EmployeePresentation expected = EmployeePresentation.getTeamLead();
		EmployeePresentation actual = EmployeePresentation.get(teamLead.getId());
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testTeamHierarchy() {
		EmployeeBusiness.teamLeadExists = false;
		EmployeeBusiness.employees.clear();
		EmployeePresentation.employees.clear();
		EmployeeBusiness teamLead = new EmployeeBusiness("John Smith");
		teamLead.makeTeamLead();
		EmployeeBusiness lead1 = new EmployeeBusiness("Mary Gray");
		lead1.setBoss(teamLead);
		EmployeeBusiness employee1 = new EmployeeBusiness("Paul Table");
		employee1.setBoss(teamLead);
		EmployeeBusiness employee2 = new EmployeeBusiness("Jane Spoon");
		employee2.setBoss(lead1);
		EmployeeBusiness employee3 = new EmployeeBusiness("Damien Firel");
		employee3.setBoss(lead1);
		String expected = EmployeePresentation.getHierarchy();
		String actual = "Team lead: John Smith" + System.getProperty("line.separator") + "Mary Gray  Paul Table"
				+ System.getProperty("line.separator") + "Mary Gray" + System.getProperty("line.separator")
				+ "Jane Spoon  Damien Firel" + System.getProperty("line.separator");
		Assert.assertEquals(expected, actual);
	}
	@Test(expected = ExceptionsPresentation.NoTeamLead.class)
	public void testTeamLeadException() {
		EmployeeBusiness.teamLeadExists = false;
		EmployeeBusiness.employees.clear();
		EmployeePresentation.employees.clear();
		EmployeeBusiness teamLead = new EmployeeBusiness("John Smith");
		EmployeeBusiness lead1 = new EmployeeBusiness("Mary Gray");
		lead1.setBoss(teamLead);
		EmployeeBusiness employee1 = new EmployeeBusiness("Paul Table");
		employee1.setBoss(teamLead);
		EmployeeBusiness employee2 = new EmployeeBusiness("Jane Spoon");
		employee2.setBoss(lead1);
		EmployeeBusiness employee3 = new EmployeeBusiness("Damien Firel");
		employee3.setBoss(lead1);
		String expected = EmployeePresentation.getHierarchy();
	}
	

}
