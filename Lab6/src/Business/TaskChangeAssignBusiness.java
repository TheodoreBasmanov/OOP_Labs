package Business;

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

}
