package Business;

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

}
