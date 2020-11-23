
public class Exceptions {
	public static class TheFileDoesntExist extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TheFileDoesntExist(String errorMessage) {
			super(errorMessage);
		}

		public TheFileDoesntExist() {
			this("The file you are trying to add doesn't exist.");
		}
	}

	public static class TheFileHasntBeenAdded extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TheFileHasntBeenAdded(String errorMessage) {
			super(errorMessage);
		}

		public TheFileHasntBeenAdded() {
			this("The file you are trying to remove wan't added.");
		}
	}

	public static class CantAddDeltaPoint extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CantAddDeltaPoint(String errorMessage) {
			super(errorMessage);
		}

		public CantAddDeltaPoint() {
			this("The delta restore point can't be created, because there were no actual restore points created before.");
		}
	}

	public static class ThereAreNoRestorePoints extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ThereAreNoRestorePoints(String errorMessage) {
			super(errorMessage);
		}

		public ThereAreNoRestorePoints() {
			this("No restore points have been created before.");
		}
	}

	public static class ThereAreNoDeltaPoints extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ThereAreNoDeltaPoints(String errorMessage) {
			super(errorMessage);
		}

		public ThereAreNoDeltaPoints() {
			this("No delta points have been created before.");
		}
	}

	public static class NegativeNumberRestriction extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativeNumberRestriction(String errorMessage) {
			super(errorMessage);
		}

		public NegativeNumberRestriction() {
			this("You can't make a number restriction negative.");
		}
	}

	public static class NegativeSizeRestriction extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativeSizeRestriction(String errorMessage) {
			super(errorMessage);
		}

		public NegativeSizeRestriction() {
			this("You can't make a size restriction negative.");
		}
	}

}
