import static org.junit.Assert.*;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BackUpTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void numberRestrictionTest() throws IOException {
		BackUp backUp = new BackUp();
		backUp.setNumberRestriction(2);
		backUp.addFile("test1.txt");
		backUp.createRestorePoint();
		backUp.addFile("test2.txt");
		backUp.createRestorePoint();
		backUp.addFile("test3.txt");
		backUp.createRestorePoint();
		ArrayList<String> expected = backUp.restorePoints.get(0).files;
		ArrayList<String> actual = new ArrayList<String>();
		actual.add("test1.txt");
		actual.add("test2.txt");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void sizeRestrictionTest() throws IOException {
		BackUp backUp = new BackUp();
		backUp.setSizeRestriction(20);
		backUp.addFile("test1.txt");
		backUp.createRestorePoint();
		backUp.addFile("test2.txt");
		backUp.createRestorePoint();
		backUp.addFile("test3.txt");
		backUp.createRestorePoint();
		ArrayList<String> expected = backUp.restorePoints.get(0).files;
		ArrayList<String> actual = new ArrayList<String>();
		actual.add("test1.txt");
		actual.add("test2.txt");
		actual.add("test3.txt");
		Assert.assertEquals(expected, actual);
	}

	/*@Test
	public void timeRestrictionTest() throws IOException {
		Clock clock = Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
		System.out.println(LocalDateTime.now(clock));
		BackUp backUp = new BackUp();
		LocalDateTime time = LocalDateTime.of(2018, Month.JANUARY, 1, 1, 1);
		backUp.setTimeRestriction(time);
		backUp.addFile("test1.txt");
		backUp.createRestorePoint();
		Instant.now(Clock.fixed(Instant.parse("2020-08-22T10:00:00Z"), ZoneOffset.UTC));
		backUp.addFile("test2.txt");
		backUp.createRestorePoint();
		backUp.addFile("test3.txt");
		backUp.createRestorePoint();
		ArrayList<String> expected = backUp.restorePoints.get(0).files;
		ArrayList<String> actual = new ArrayList<String>();
		actual.add("test1.txt");
		actual.add("test2.txt");
		Assert.assertEquals(expected, actual);
	}*/
	
	@Test
	public void testDeltasRestriction() throws IOException {
		BackUp backUp = new BackUp();
		backUp.addFile("test1.txt");
		backUp.createRestorePoint();
		backUp.addFile("test2.txt");
		backUp.createDeltaPoint();
		backUp.addFile("test3.txt");
		backUp.createDeltaPoint();
		backUp.createRestorePoint();
		backUp.setNumberRestriction(2);
		int expected = backUp.restorePoints.size();
		int actual = 4;
		Assert.assertEquals(expected == actual, true);
	}
	
	@Test
	public void testMixedRestriction() throws IOException {
		BackUp backUp = new BackUp();
		backUp.addFile("test1.txt");
		backUp.addFile("test2.txt");
		backUp.addFile("test3.txt");
		backUp.setRestrictionsMax();
		backUp.setNumberRestriction(2);
		backUp.setSizeRestriction(20);
		backUp.createRestorePoint();
		int expected = backUp.restorePoints.size();
		int actual = 1;
		Assert.assertEquals(expected == actual, true);
	}

	@Test
	public void test1() throws IOException {
		BackUp backUp = new BackUp();
		backUp.addFile("test1.txt");
		backUp.addFile("test2.txt");
		backUp.createRestorePoint();
		ArrayList<String> expected = backUp.restorePoints.get(0).files;
		ArrayList<String> actual = new ArrayList<String>();
		actual.add("test1.txt");
		actual.add("test2.txt");
		Assert.assertEquals(expected, actual);
		backUp.createRestorePoint();
		backUp.setNumberRestriction(1);
		int expected1 = backUp.restorePoints.size();
		int actual1 = 1;
		Assert.assertEquals(expected1 == actual1, true);
	}

	@Test
	public void test2() throws IOException {
		BackUp backUp = new BackUp();
		backUp.addFile("test4.txt");
		backUp.createRestorePoint();
		backUp.createRestorePoint();
		long expected = backUp.backUpsSize;
		long actual = 200;
		Assert.assertEquals(expected == actual, true);
		backUp.setSizeRestriction(150);
		int expected1 = backUp.restorePoints.size();
		int actual1 = 1;
		Assert.assertEquals(expected1 == actual1, true);
	}

}
