import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DromedaryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		Dromedary dromedary = new Dromedary();
		double expected = dromedary.timeToGoTheDistance(1000);
		double actual = 36.5;
		Assert.assertEquals(expected == actual, true);
	}

}
