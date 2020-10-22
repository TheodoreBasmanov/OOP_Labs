
public class Mortar extends AirTransport{
	private Mortar(double speed, double distanceReducer) {
		super(speed, distanceReducer);
	}

	Mortar() {
		this(8, 6);
	}
}
