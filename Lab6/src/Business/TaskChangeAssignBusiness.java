package Business;

import java.util.ArrayList;

public class TaskChangeAssignBusiness extends TaskChangeBusiness {
	EmployeeBusiness oldEmployee;
	EmployeeBusiness newEmployee;

	public TaskChangeAssignBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(employee, time);
		this.type = Type.Assignment;
	}

	public TaskChangeAssignBusiness(EmployeeBusiness employee, TimeGiver time, EmployeeBusiness oldEmployee,
			EmployeeBusiness newEmployee) {
		super(employee, time);
		this.oldEmployee = oldEmployee;
		this.newEmployee = newEmployee;
		this.type = Type.Assignment;
	}

	@Override
	public ArrayList<String> giveInfo() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("Previously assigned employee - " + oldEmployee.name);
		info.add("Newly assigned employee - " + newEmployee.name);
		return info;
	}

}
