import java.util.ArrayList;

public class Race<T extends Moving> {
	double distance;
	ArrayList<T> vehicles;

	Race(double distance) {
		this.distance = distance;
		vehicles = new ArrayList<T>();
	}


	void addVehicle(T vehicle) {
		vehicles.add(vehicle);
	}

	T getWinner() {
		T winner = null;
		double time = Double.MAX_VALUE;
		double currentTime = 0;
		for (int i = 0; i < vehicles.size(); i++) {
			currentTime = ((Moving)vehicles.get(i)).timeToGoTheDistance(distance);
			if (time > currentTime) {
				time = currentTime;
				winner = vehicles.get(i);
			}
		}
		return winner;
	}
}
