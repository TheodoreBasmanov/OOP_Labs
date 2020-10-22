import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MagicCarpetTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		MagicCarpet magicCarpet = new MagicCarpet();
		double expected = magicCarpet.timeToGoTheDistance(1000);
		double actual = 97;
		Assert.assertEquals(expected == actual, true);
	}

}
