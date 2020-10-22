
public class LandRace extends Race {

	LandRace(double distance) {
		super(distance);
	}

	@Override
	void addVehicle(Moving vehicle) throws Exceptions.CantAddAirVehicle {
		if (vehicle.getClass().getSuperclass().getName().equals("LandTransport")) {
			vehicles.add(vehicle);
		} else {
			throw new Exceptions.CantAddAirVehicle();
		}
	}

}
