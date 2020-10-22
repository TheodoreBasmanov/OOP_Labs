import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LandRaceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getWinnerTest() {
		LandRace race = new LandRace(1000);
		race.addVehicle(new Dromedary());
		race.addVehicle(new Centaur());
		race.addVehicle(new Bactrian());
		race.addVehicle(new ATBoots());
		String expected = race.getWinner().getClass().getName();
		String actual = "Dromedary";
		Assert.assertEquals(expected, actual);
	}
	@Test(expected = Exceptions.CantAddAirVehicle.class)
	public void getWinnerErrorTest() {
		LandRace race = new LandRace(1000);
		race.addVehicle(new Broom());
	}

}
