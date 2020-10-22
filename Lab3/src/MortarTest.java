import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MortarTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		Mortar mortar = new Mortar();
		double expected = mortar.timeToGoTheDistance(1000);
		double actual = 117.5;
		Assert.assertEquals(expected == actual, true);
	}

}
