import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BroomTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		Broom broom = new Broom();
		double expected = broom.timeToGoTheDistance(1000);
		double actual = 49.5;
		Assert.assertEquals(expected == actual, true);
	}
}
