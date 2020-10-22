
public class Broom extends AirTransport {
	private Broom(double speed, double distanceReducer) {
		super(speed, distanceReducer);
	}

	Broom() {
		this(20, 1);
	}

	@Override
	public double timeToGoTheDistance(double distance) {
		int reduceTimes = (int) (distance / 1000);
		for (int i = 0; i < reduceTimes; i++) {
			distance -= distance / 100;
		}
		double time = distance / Speed;
		return time;
	}
}
