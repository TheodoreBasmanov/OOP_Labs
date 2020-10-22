
public class AirRace extends Race {

	AirRace(double distance) {
		super(distance);
	}

	@Override
	void addVehicle(Moving vehicle) throws Exceptions.CantAddLandVehicle {
		if (vehicle.getClass().getSuperclass().getName().equals("AirTransport")) {
			vehicles.add(vehicle);
		} else {
			throw new Exceptions.CantAddLandVehicle();
		}
	}
}
