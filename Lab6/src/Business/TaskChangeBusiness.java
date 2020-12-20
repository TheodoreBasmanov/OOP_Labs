package Business;

import java.time.LocalDateTime;

public class TaskChangeBusiness {
	TimeGiver time;
	LocalDateTime changeTime;
	EmployeeBusiness employee;
	Type type;

	public enum Type {
		Creation, Assignment, Comment, StateChange
	}

	public void setTime(TimeGiver timeGiver) {
		this.time = timeGiver;
	}

	public TaskChangeBusiness(EmployeeBusiness employee, TimeGiver time) {
		changeTime = time.getDate();
		this.employee = employee;
		this.type = Type.Creation;
		this.time = time;
	}

}
