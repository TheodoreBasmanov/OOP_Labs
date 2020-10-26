import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RaceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getWinnerTest() {
		Race<Moving> race = new Race<Moving>(1000);
		race.addVehicle(new Dromedary());
		race.addVehicle(new Centaur());
		race.addVehicle(new Mortar());
		race.addVehicle(new Broom());
		String expected = race.getWinner().getClass().getName();
		String actual = "Dromedary";
		Assert.assertEquals(expected, actual);
	}

}
