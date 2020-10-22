
public class MagicCarpet extends AirTransport {
	private MagicCarpet(double speed, double distanceReducer) {
		super(speed, distanceReducer);
	}

	MagicCarpet() {
		this(10, 3);
	}

	@Override
	public double timeToGoTheDistance(double distance) {
		double time = distance / Speed;
		if ((1000 <= distance) && (distance < 5000)) {
			time -= distance * DistanceReducer / 100 / Speed;
		}
		if ((5000 <= distance) && (distance < 10000)) {
			time -= distance * 10 / 100 / Speed;
		}
		if (10000 <= distance) {
			time -= distance * 5 / 100 / Speed;
		}
		return time;
	}
}
