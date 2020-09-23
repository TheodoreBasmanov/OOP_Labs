
public class Exceptions {
	public static class NotUniqueCode extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotUniqueCode(String errorMessage) {
			super(errorMessage);
		}
		public NotUniqueCode(){
			this("The Code is not Unique");
		}
	}
}
