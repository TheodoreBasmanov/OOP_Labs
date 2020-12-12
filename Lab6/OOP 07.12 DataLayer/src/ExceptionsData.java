
public class ExceptionsData {
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
