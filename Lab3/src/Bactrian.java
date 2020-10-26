

public class Bactrian extends LandTransport {

	private Bactrian(double speed, double restInterval, double restDuration) {
		super(speed, restInterval, restDuration);
	}

	Bactrian() {
		this(10, 30, 5);
	}

	@Override
	public double timeToGoTheDistance(double distance) {
		int restTimes = (int) (distance / Speed / RestInterval);
		double time = distance / Speed;
		if (restTimes != 0) {
			time += RestDuration + (restTimes - 1) * 8;
		}
		return time;
	}

}
