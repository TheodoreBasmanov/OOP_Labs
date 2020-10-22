import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BactrianTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		Bactrian bactrian = new Bactrian();
		double expected = bactrian.timeToGoTheDistance(1000);
		double actual = 121;
		Assert.assertEquals(expected == actual, true);
	}

}
