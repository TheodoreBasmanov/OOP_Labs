package Business;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Presentation.EmployeePresentation;
import Presentation.TaskChangePresentation;

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

	public ArrayList<String> giveInfo() {
		ArrayList<String> info = new ArrayList<String>();
		return info;
	}

	public static class TaskChangePresentationAdapter {
		public static TaskChangePresentation adapt(TaskChangeBusiness businessTaskChange) {
			EmployeePresentation employee = EmployeePresentation.get(businessTaskChange.employee.id);
			TaskChangePresentation presentationTaskChange = new TaskChangePresentation(employee,
					businessTaskChange.changeTime, businessTaskChange.type.toString());
			presentationTaskChange.additionalInfo.addAll(businessTaskChange.giveInfo());
			return presentationTaskChange;
		}
	}

}
