
public class Exceptions {
	public static class IncorrectFileExtensionException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public IncorrectFileExtensionException(String errorMessage) {
			super(errorMessage);
		}
	}

	public static class NoSuchPairException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoSuchPairException(String errorMessage) {
			super(errorMessage);
		}
	}

	public static class MissMatchException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MissMatchException(String errorMessage) {
			super(errorMessage);
		}
	}
}
