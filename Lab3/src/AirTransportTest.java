import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AirTransportTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		AirTransport vehicle = new AirTransport(10, 5);
		double expected = vehicle.timeToGoTheDistance(1000);
		double actual = 95;
		Assert.assertEquals(expected == actual, true);
	}

}
