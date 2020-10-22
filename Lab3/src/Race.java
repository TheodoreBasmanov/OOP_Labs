import java.util.ArrayList;

public class Race {
	double distance;
	ArrayList<Moving> vehicles;

	Race(double distance) {
		this.distance = distance;
		vehicles = new ArrayList<Moving>();
	}

	void addVehicle(Moving vehicle) {
		vehicles.add(vehicle);
	}

	Moving getWinner() {
		Moving winner = null;
		double time = Double.MAX_VALUE;
		double currentTime = 0;
		for (int i = 0; i < vehicles.size(); i++) {
			currentTime = vehicles.get(i).timeToGoTheDistance(distance);
			if (time > currentTime) {
				time = currentTime;
				winner = vehicles.get(i);
			}
		}
		return winner;
	}
}
