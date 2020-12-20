package Business;

import java.util.ArrayList;

public class TaskChangeCommentBusiness extends TaskChangeBusiness {
	String comment;

	public TaskChangeCommentBusiness(EmployeeBusiness employee, TimeGiver time) {
		super(employee, time);
		this.type = Type.Comment;
	}

	public TaskChangeCommentBusiness(EmployeeBusiness employee, TimeGiver time, String comment) {
		super(employee, time);
		this.comment = comment;
		this.type = Type.Comment;
	}

	@Override
	public ArrayList<String> giveInfo() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("Added commentary:" + comment);
		return info;
	}

}
