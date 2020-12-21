package Presentation;

import java.util.ArrayList;

import Business.EmployeeBusiness;

public class EmployeePresentation {

	public static ArrayList<EmployeePresentation> employees = new ArrayList<EmployeePresentation>();
	private int id;
	public String name;
	public EmployeePresentation boss;
	public boolean isTeamLead;
	public boolean isLead;
	public ArrayList<EmployeePresentation> subordinates;

	public EmployeePresentation(String name, int id) {
		this.id = id;
		this.name = name;
		EmployeePresentation.employees.add(this);
	}

	public int getId() {
		return id;
	}

	public static EmployeePresentation get(int id) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).id == id) {
				return (employees.get(i));
			}
		}
		return null;
	}

	public void setBoss(int id) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).id == id) {
				this.boss = employees.get(i);
			}
		}
	}

	public void addSubordinate(int id) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).id == id) {
				subordinates.add(employees.get(i));
			}
		}
	}

	static public void makeTeamLead(int id) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).id == id) {
				employees.get(i).isTeamLead = true;
			}
		}
	}

	static EmployeePresentation getTeamLead() throws ExceptionsPresentation.NoTeamLead {
		if (!EmployeeBusiness.teamLeadExists) {
			throw new ExceptionsPresentation.NoTeamLead();
		}
		for (int i = 0; i < EmployeePresentation.employees.size(); i++) {
			if (EmployeePresentation.employees.get(i).isTeamLead) {
				return EmployeePresentation.employees.get(i);
			}
		}
		return null;
	}

	private static void showBranch(EmployeePresentation employee, StringBuilder sb) {
		if (employee.isLead) {
			sb.append(employee.name);
			sb.append(System.getProperty("line.separator"));
			for (int i = 0; i < employee.subordinates.size() - 1; i++) {
				sb.append(employee.subordinates.get(i).name + "  ");
			}
			sb.append(employee.subordinates.get(employee.subordinates.size() - 1).name);
			sb.append(System.getProperty("line.separator"));
			for (int i = 0; i < employee.subordinates.size(); i++) {
				showBranch(employee.subordinates.get(i), sb);
			}
		}
	}

	static String getHierarchy() throws ExceptionsPresentation.NoTeamLead {
		EmployeeBusiness.updateHierarchy();
		EmployeePresentation teamLead = getTeamLead();
		StringBuilder sb = new StringBuilder();
		sb.append("Team lead: ");
		showBranch(teamLead, sb);
		return sb.toString();
	}

}
