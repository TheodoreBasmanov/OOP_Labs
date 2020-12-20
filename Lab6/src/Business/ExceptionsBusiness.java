package Business;

public class ExceptionsBusiness {
	public static class NotALead extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotALead(String errorMessage) {
			super(errorMessage);
		}

		public NotALead() {
			this("This employee isn't a lead, you can't manipulate their subordinates, for there are none.");
		}
	}

	public static class CantMakeTowTeamLeads extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CantMakeTowTeamLeads(String errorMessage) {
			super(errorMessage);
		}

		public CantMakeTowTeamLeads() {
			this("You can't make this employee a team lead, because team lead already exists.");
		}
	}

	public static class CantChangeState extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CantChangeState(String errorMessage) {
			super(errorMessage);
		}

		public CantChangeState() {
			this("You can't change state of the task - it's already resolved.");
		}
	}

	public static class NotYourTask extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotYourTask(String errorMessage) {
			super(errorMessage);
		}

		public NotYourTask() {
			this("It's not your task, you can't add it to your report.");
		}
	}

	public static class NotResolvedTask extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotResolvedTask(String errorMessage) {
			super(errorMessage);
		}

		public NotResolvedTask() {
			this("This task isn't resolved, you can't add it to Resolved Tasks of your report.");
		}
	}

	public static class NotToday extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotToday(String errorMessage) {
			super(errorMessage);
		}

		public NotToday() {
			this("The task hasn't been changed/resolved today, you can't add it to your Today report.");
		}
	}
	
	public static class NotInTheSprint extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotInTheSprint(String errorMessage) {
			super(errorMessage);
		}

		public NotInTheSprint() {
			this("The task hasn't been changed/resolved during this sprint, you can't add it to your Today report.");
		}
	}
}
