import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeltaFlyerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeToGoTheDistance() {
		DeltaFlyer dFlyer= new DeltaFlyer();
		double expected = dFlyer.timeToGoTheDistance(1000);
		double actual = 18.4;
		Assert.assertEquals(expected == actual, true);
	}

}
