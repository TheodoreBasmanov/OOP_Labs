
public class ATBoots extends LandTransport {
	private ATBoots(double speed, double restInterval, double restDuration) {
		super(speed, restInterval, restDuration);
	}

	ATBoots() {
		this(6, 60, 10);
	}
	@Override
	public double timeToGoTheDistance(double distance) {
		int restTimes = (int) (distance / Speed / RestInterval);
		double time = distance / Speed;
		if (restTimes != 0) {
			time += RestDuration + (restTimes - 1) * 5;
		}
		return time;
	}
}
