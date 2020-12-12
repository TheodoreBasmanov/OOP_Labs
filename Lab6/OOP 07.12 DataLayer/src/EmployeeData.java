import java.util.ArrayList;

public class EmployeeData {
	static ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>();
	private static int ID = 1;
	static boolean teamLeadExists = false;
	int id;
	String name;
	EmployeeData boss;
	boolean isTeamLead;
	boolean isLead;
	ArrayList<EmployeeData> subordinates;
	EmployeeData(String name){
		id = ID;
		ID++;
		this.name=name;
		employees.add(this);
	}
	void setBoss(EmployeeData boss){
		this.boss = boss; 
		boss.addSubordinate(this);
	}
	void makeTeamLead() throws ExceptionsData.CantMakeTowTeamLeads{
		if(teamLeadExists){
			throw new ExceptionsData.CantMakeTowTeamLeads();
		}
		this.isTeamLead = true;
		teamLeadExists = true;
	}
	void addSubordinate(EmployeeData sub){
		this.isLead = true;
		if (subordinates == null){
			subordinates = new ArrayList<EmployeeData>();
		}
		subordinates.add(sub);
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
