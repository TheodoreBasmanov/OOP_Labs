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
}

