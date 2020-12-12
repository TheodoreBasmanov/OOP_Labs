
public class ExceptionsPresentation {
	public static class NoTeamLead extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoTeamLead(String errorMessage) {
			super(errorMessage);
		}

		public NoTeamLead() {
			this("You can't do anything hierarchy related without the team lead. And they don't exist.");
		}
	}
}
