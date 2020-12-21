package Business;

import Data.EmployeeData;
import Presentation.EmployeePresentation;

import java.util.ArrayList;

public class EmployeeBusiness {
	public static ArrayList<EmployeeBusiness> employees = new ArrayList<EmployeeBusiness>();
	private static int ID = 1;
	private int id;
	public String name;
	public EmployeeBusiness boss;
	public boolean isTeamLead;
	public boolean isLead;
	public ArrayList<EmployeeBusiness> subordinates;
	public static boolean teamLeadExists = false;

	public EmployeeBusiness(String name) {
		this.id = ID;
		ID++;
		this.name = name;
		EmployeeBusiness.employees.add(this);
		EmployeeDataAdapter.adapt(this);
		EmployeePresentationAdapter.adapt(this);
	}

	public int getId() {
		return id;
	}

	public void makeTeamLead()
			throws ExceptionsBusiness.CantMakeTowTeamLeads, ExceptionsBusiness.CantGiveTeamLeadABoss {
		if (teamLeadExists) {
			throw new ExceptionsBusiness.CantMakeTowTeamLeads();
		}
		if (isTeamLead) {
			throw new ExceptionsBusiness.CantGiveTeamLeadABoss();
		}
		this.isTeamLead = true;
		teamLeadExists = true;
		EmployeeData.makeTeamLead(this.id);
		EmployeePresentation.makeTeamLead(this.id);
	}

	public void setBoss(EmployeeBusiness boss) {
		if (this.boss != null) {
			this.boss.removeSubordinate(this);
		}
		this.boss = boss;
		boss.addSubordinate(this);
	}

	void addSubordinate(EmployeeBusiness sub) {
		this.isLead = true;
		if (subordinates == null) {
			subordinates = new ArrayList<EmployeeBusiness>();
		}
		subordinates.add(sub);
	}

	void removeSubordinate(EmployeeBusiness sub) throws ExceptionsBusiness.NotALead {
		if (!isLead) {
			throw new ExceptionsBusiness.NotALead();
		}
		subordinates.remove(sub);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof EmployeeBusiness)) {
			return false;
		}
		EmployeeBusiness c = (EmployeeBusiness) o;
		return id == c.id;
	}

	public static void updateHierarchy() {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).boss != null) {
				if (EmployeePresentation.employees.get(i).boss == null
						|| EmployeePresentation.employees.get(i).boss.getId() != employees.get(i).boss.id) {
					EmployeePresentation.employees.get(i).setBoss(employees.get(i).boss.id);
				}
			}
			if (employees.get(i).isLead) {
				if (!EmployeePresentation.employees.get(i).isLead) {
					EmployeePresentation.employees.get(i).isLead = true;
					EmployeePresentation.employees.get(i).subordinates = new ArrayList<EmployeePresentation>();
					for (int j = 0; j < employees.get(i).subordinates.size(); j++) {
						EmployeePresentation.employees.get(i).addSubordinate(employees.get(i).subordinates.get(j).id);
					}
				}
				for (int j = 0; j < employees.get(i).subordinates.size(); j++) {
					if (!findSub(EmployeePresentation.employees.get(i).subordinates,
							employees.get(i).subordinates.get(j).id)) {
						EmployeePresentation.employees.get(i).addSubordinate(employees.get(i).subordinates.get(j).id);
					}
				}
			}
		}

	}

	private static boolean findSub(ArrayList<Presentation.EmployeePresentation> subs, int id) {
		for (int i = 0; i < subs.size(); i++) {
			if (subs.get(i).getId() == id) {
				return true;
			}
		}
		return false;
	}

	public static EmployeeBusiness getTeamLead() {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).isTeamLead) {
				return employees.get(i);
			}
		}
		return null;
	}

	public static class EmployeeDataAdapter {
		public static EmployeeData adapt(EmployeeBusiness businessEmployee) {
			EmployeeData dataEmployee = new EmployeeData(businessEmployee.name, businessEmployee.id);
			return dataEmployee;
		}
	}

	public static class EmployeePresentationAdapter {
		public static EmployeePresentation adapt(EmployeeBusiness businessEmployee) {
			EmployeePresentation presentationEmployee = new EmployeePresentation(businessEmployee.name,
					businessEmployee.id);
			return presentationEmployee;
		}
	}

}
