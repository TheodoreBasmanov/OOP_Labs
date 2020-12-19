package Data;

import java.util.ArrayList;

public class EmployeeData {
	static ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>();
	public int id;
	public String name;
	public boolean isTeamLead;

	public EmployeeData(String name, int id) {
		this.id = id;
		this.name = name;
		EmployeeData.employees.add(this);
	}
	public static EmployeeData get(int id){
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).id == id) {
				return( employees.get(i));
			}
		}
		return null;
	}

	public static void makeTeamLead(int id) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).id == id) {
				employees.get(i).isTeamLead = true;
			}
		}
	}
	

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof EmployeeData)) {
			return false;
		}
		EmployeeData c = (EmployeeData) o;
		return id == c.id;
	}

}