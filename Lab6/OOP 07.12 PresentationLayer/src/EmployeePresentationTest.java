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
		EmployeeData.teamLeadExists = false;
		EmployeeData.employees.clear();
		EmployeeData teamLead = new EmployeeData("John Smith");
		teamLead.makeTeamLead();
		EmployeeData lead1 = new EmployeeData("Mary Gray");
		lead1.setBoss(teamLead);
		EmployeeData employee1 = new EmployeeData("Paul Table");
		employee1.setBoss(teamLead);
		EmployeeData employee2 = new EmployeeData("Jane Spoon");
		employee2.setBoss(lead1);
		EmployeeData employee3 = new EmployeeData("Damien Firel");
		employee3.setBoss(lead1);
		EmployeeData expected = EmployeePresentation.getTeamLead();
		EmployeeData actual = teamLead;
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testHierarchy() {
		EmployeeData.teamLeadExists = false;
		EmployeeData.employees.clear();
		EmployeeData teamLead = new EmployeeData("John Smith");
		teamLead.makeTeamLead();
		EmployeeData lead1 = new EmployeeData("Mary Gray");
		lead1.setBoss(teamLead);
		EmployeeData employee1 = new EmployeeData("Paul Table");
		employee1.setBoss(teamLead);
		EmployeeData employee2 = new EmployeeData("Jane Spoon");
		employee2.setBoss(lead1);
		EmployeeData employee3 = new EmployeeData("Damien Firel");
		employee3.setBoss(lead1);
		EmployeePresentation.getHierarchy();
	}
	@Test(expected = ExceptionsPresentation.NoTeamLead.class)
	public void testTeamLeadException() {
		EmployeeData.teamLeadExists = false;
		EmployeeData.employees.clear();
		EmployeeData teamLead = new EmployeeData("John Smith");
		EmployeeData lead1 = new EmployeeData("Mary Gray");
		lead1.setBoss(teamLead);
		EmployeeData employee1 = new EmployeeData("Paul Table");
		employee1.setBoss(teamLead);
		EmployeeData employee2 = new EmployeeData("Jane Spoon");
		employee2.setBoss(lead1);
		EmployeeData employee3 = new EmployeeData("Damien Firel");
		employee3.setBoss(lead1);
		EmployeePresentation.getHierarchy();
	}


}
