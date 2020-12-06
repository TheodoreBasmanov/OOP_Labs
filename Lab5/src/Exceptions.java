
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
	public static class IncorrectIDAccount extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IncorrectIDAccount(String errorMessage) {
			super(errorMessage);
		}

		public IncorrectIDAccount() {
			this("An account with such ID doesn't exist.");
		}
	}
	public static class IncorrectIDTransaction extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IncorrectIDTransaction(String errorMessage) {
			super(errorMessage);
		}

		public IncorrectIDTransaction() {
			this("An transactions with such ID doesn't exist.");
		}
	}
	public static class NotAfterThePeriod extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotAfterThePeriod(String errorMessage) {
			super(errorMessage);
		}

		public NotAfterThePeriod() {
			this("You can't withdraw or transfer money from the deposit before its period is over.");
		}
	}
	public static class BelowTheLimit extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BelowTheLimit(String errorMessage) {
			super(errorMessage);
		}

		public BelowTheLimit() {
			this("You can't go below the credit limit.");
		}
	}
	public static class OverUnrealible extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OverUnrealible(String errorMessage) {
			super(errorMessage);
		}

		public OverUnrealible() {
			this("The client is unrealiable, you can't withdraw or transfer more money than your bank allows.");
		}
	}
}
