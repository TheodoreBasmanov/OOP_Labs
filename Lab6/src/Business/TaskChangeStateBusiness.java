package Business;

import java.util.ArrayList;

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

	@Override
	public ArrayList<String> giveInfo() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("Previous task state - " + oldState.toString());
		info.add("Current task state - " + newState.toString());
		return info;
	}

}
