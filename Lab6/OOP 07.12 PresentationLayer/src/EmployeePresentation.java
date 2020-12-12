
public class EmployeePresentation {
	static EmployeeData getTeamLead() throws ExceptionsPresentation.NoTeamLead {
		if (!EmployeeData.teamLeadExists) {
			throw new ExceptionsPresentation.NoTeamLead();
		}
		for (int i = 0; i < EmployeeData.employees.size(); i++) {
			if (EmployeeData.employees.get(i).isTeamLead) {
				return EmployeeData.employees.get(i);
			}
		}
		return null;
	}

	private static void showBranch(EmployeeData employee) {
		if (employee.isLead) {
			System.out.println(employee.name);
			for (int i = 0; i < employee.subordinates.size(); i++) {
				System.out.print(employee.subordinates.get(i).name + "  ");
			}
			System.out.println();
			for (int i = 0; i < employee.subordinates.size(); i++) {
				showBranch(employee.subordinates.get(i));
			}
		}
	}

	static void getHierarchy() throws ExceptionsPresentation.NoTeamLead {
		EmployeeData teamLead = getTeamLead();
		System.out.print("Team lead: ");
		showBranch(teamLead);
	}
}
