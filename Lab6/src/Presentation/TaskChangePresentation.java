package Presentation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskChangePresentation {
	LocalDateTime changeTime;
	EmployeePresentation employee;
	String type;
	public ArrayList<String> additionalInfo;

	public TaskChangePresentation(EmployeePresentation employee, LocalDateTime time, String type) {
		changeTime = time;
		this.employee = employee;
		this.type = type;
		additionalInfo = new ArrayList<String>();
	}

	String show() {
		StringBuilder sb = new StringBuilder();
		sb.append("Change time " + changeTime);
		sb.append(System.getProperty("line.separator"));
		sb.append("Employee, who made the change - " + employee.name);
		sb.append(System.getProperty("line.separator"));
		sb.append("Type of the change - " + type);
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < additionalInfo.size(); i++) {
			sb.append(additionalInfo.get(i));
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

}
