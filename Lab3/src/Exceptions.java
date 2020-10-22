
public class Exceptions {
	public static class CantAddAirVehicle extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CantAddAirVehicle(String errorMessage) {
			super(errorMessage);
		}

		public CantAddAirVehicle() {
			this("You can't register an air vehicle to a land race.");
		}
	}

	public static class CantAddLandVehicle extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CantAddLandVehicle(String errorMessage) {
			super(errorMessage);
		}

		public CantAddLandVehicle() {
			this("You can't register a land vehicle to an air race.");
		}
	}
}
