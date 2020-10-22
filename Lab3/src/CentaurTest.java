import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CentaurTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		Centaur centaur = new Centaur();
		double expected = centaur.timeToGoTheDistance(1000);
		double actual = 1000.0 / 15 + 8 * 2;
		Assert.assertEquals(expected == actual, true);
	}

}
