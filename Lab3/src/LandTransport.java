
public class LandTransport implements Moving {
	double Speed;
	double RestInterval;
	double RestDuration;

	LandTransport(double speed, double restInterval, double restDuration) {
		this.Speed = speed;
		this.RestInterval = restInterval;
		this.RestDuration = restDuration;
	}

	@Override
	public double timeToGoTheDistance(double distance) {
		int restTimes = (int) (distance/Speed/RestInterval);
		double time = restTimes*RestDuration+distance/Speed;
		return time;
	}

}
