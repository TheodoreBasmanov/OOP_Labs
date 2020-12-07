
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
			this("A transaction with such ID doesn't exist.");
		}
	}

	public static class IncorrectIDClient extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IncorrectIDClient(String errorMessage) {
			super(errorMessage);
		}

		public IncorrectIDClient() {
			this("A client with such ID doesn't exist in this bank.");
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

	public static class AlreadyReliable extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AlreadyReliable(String errorMessage) {
			super(errorMessage);
		}

		public AlreadyReliable() {
			this("The client is already reliable.");
		}
	}

	public static class WrongBank extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public WrongBank(String errorMessage) {
			super(errorMessage);
		}

		public WrongBank() {
			this("You seem to be trying to add the client to a wrong bank.");
		}
	}

	public static class WrongDepositPercents extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public WrongDepositPercents(String errorMessage) {
			super(errorMessage);
		}

		public WrongDepositPercents() {
			this("Your percents for deposit accounts seem to be set so that the initial summ of money doesn't feet into any of the interval.");
		}
	}

	public static class NegativeMoneySumm extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativeMoneySumm(String errorMessage) {
			super(errorMessage);
		}

		public NegativeMoneySumm() {
			this("The summ of money you are operating with can't be negative.");
		}
	}

	public static class NegativePercent extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativePercent(String errorMessage) {
			super(errorMessage);
		}

		public NegativePercent() {
			this("The percent for the account can't be negative.");
		}
	}

	public static class NegativeTime extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativeTime(String errorMessage) {
			super(errorMessage);
		}

		public NegativeTime() {
			this("The time period you are operating with can't be negative.");
		}
	}

	public static class NegativeComission extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativeComission(String errorMessage) {
			super(errorMessage);
		}

		public NegativeComission() {
			this("The comission for a credit account can't be negative.");
		}
	}

	public static class NegativeLimit extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativeLimit(String errorMessage) {
			super(errorMessage);
		}

		public NegativeLimit() {
			this("The limit for a credit account can't be negative.");
		}
	}

	public static class AlreadyCanceled extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AlreadyCanceled(String errorMessage) {
			super(errorMessage);
		}

		public AlreadyCanceled() {
			this("The transactions you are trying to cancel was already cancelled.");
		}
	}

}
