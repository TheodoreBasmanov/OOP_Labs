
public class AirTransport implements Moving {
	double Speed;
	double DistanceReducer;

	AirTransport(double speed, double distanceReducer) {
		this.Speed = speed;
		this.DistanceReducer = distanceReducer;
	}

	@Override
	public double timeToGoTheDistance(double distance) {
		double reducedDistance = distance - distance*DistanceReducer/100;
		double time = reducedDistance/Speed;
		return time;
	}

}
