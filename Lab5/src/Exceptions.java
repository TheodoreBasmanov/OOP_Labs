
public class Exceptions {
	public static class CantBeNegative extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CantBeNegative(String errorMessage) {
			super(errorMessage);
		}

		public CantBeNegative() {
			this("You can't withdraw more money, than you have.");
		}
	}
	public static class IncorrectID extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IncorrectID(String errorMessage) {
			super(errorMessage);
		}

		public IncorrectID() {
			this("An account with such ID doesn't exist.");
		}
	}
}
