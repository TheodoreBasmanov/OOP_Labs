import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AirRaceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getWinnerTest() {
		AirRace race = new AirRace(1000);
		race.addVehicle(new MagicCarpet());
		race.addVehicle(new Mortar());
		race.addVehicle(new Broom());
		race.addVehicle(new DeltaFlyer());
		String expected = race.getWinner().getClass().getName();
		String actual = "DeltaFlyer";
		Assert.assertEquals(expected, actual);
	}
	@Test(expected = Exceptions.CantAddLandVehicle.class)
	public void getWinnerErrorTest() {
		AirRace race = new AirRace(1000);
		race.addVehicle(new Centaur());
	}

}
