package Business;

public class TaskChangeStateBusiness extends TaskChangeBusiness {
	TaskBusiness.State oldState;
	TaskBusiness.State newState;

	public TaskChangeStateBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(employee, time);
		this.type = Type.StateChange;
	}

	public TaskChangeStateBusiness(EmployeeBusiness employee, TimeGiver time, TaskBusiness.State oldState,
			TaskBusiness.State newState) {
		super(employee, time);
		this.oldState = oldState;
		this.newState = newState;
		this.type = Type.StateChange;
	}

}
