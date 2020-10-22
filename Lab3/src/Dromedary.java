
public class Dromedary extends LandTransport {
	private Dromedary(double speed, double restInterval, double restDuration) {
		super(speed, restInterval, restDuration);
	}

	Dromedary() {
		this(40, 10, 5);
	}

	@Override
	public double timeToGoTheDistance(double distance) {
		int restTimes = (int) (distance / Speed / RestInterval);
		double time = distance / Speed;
		if (restTimes == 1) {
			time += RestDuration;
		}
		if (restTimes > 1) {
			time += RestDuration + 6.5 + 8 * (restTimes - 2);
		}
		return time;
	}
}
