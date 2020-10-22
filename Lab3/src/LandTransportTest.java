import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LandTransportTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		LandTransport vehicle = new LandTransport(10, 10, 5);
		double expected = vehicle.timeToGoTheDistance(1000);
		double actual = 150;
		Assert.assertEquals(expected == actual, true);
	}

}
