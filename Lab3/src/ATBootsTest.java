import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ATBootsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		ATBoots atBoots = new ATBoots();
		double expected = atBoots.timeToGoTheDistance(1000);
		double actual = 1000.0 / 6 + 10 + 5;
		Assert.assertEquals(expected == actual, true);
	}

}
