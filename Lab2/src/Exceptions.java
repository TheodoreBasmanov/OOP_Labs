
public class Exceptions {
	public static class NotEnoughProduct extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotEnoughProduct(String errorMessage) {
			super(errorMessage);
		}
		public NotEnoughProduct(){
			this("There's not enough products in the shop.");
		}
	}
	public static class NoSuchProduct extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoSuchProduct(String errorMessage) {
			super(errorMessage);
		}
		public NoSuchProduct(){
			this("There's no such product in the shop.");
		}
	}
	public static class NegativePrice extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativePrice(String errorMessage) {
			super(errorMessage);
		}
		public NegativePrice(){
			this("The price can't be negative.");
		}
	}
	public static class NegativeNumber extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativeNumber(String errorMessage) {
			super(errorMessage);
		}
		public NegativeNumber(){
			this("The number can't be negative.");
		}
	}
}
