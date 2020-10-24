
public class DeltaFlyer extends AirTransport {
	private DeltaFlyer(double speed, double distanceReducer) {
		super(speed, distanceReducer);
	}

	DeltaFlyer() {
		this(50, 8);
	}

	@Override
	public double timeToGoTheDistance(double distance) {
		double time = distance / Speed;
		if (distance < 20000) {
			time -= distance * DistanceReducer / 100 / Speed;
		}
		if ((20000 <= distance) && (distance < 100000)) {
			time -= distance * 9 / 100 / Speed;
		}
		if (100000 <= distance) {
			time -= distance * 10 / 100 / Speed;
		}
		return time;
	}
}
