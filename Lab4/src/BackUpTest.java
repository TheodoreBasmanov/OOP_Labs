import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
		CreatingFullPoints algorythm = new CreatingFullPoints();
		BackUp backUp = new BackUp();
		backUp.setRemovingAlgorythm(new RemovingPointsRestrictions());
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).addRestriction(backUp,
				new pointsToRemoveNumberRestriction(2));
		backUp.addFile("test1.txt");
		backUp.createAPoint(algorythm);
		backUp.addFile("test2.txt");
		backUp.createAPoint(algorythm);
		backUp.addFile("test3.txt");
		backUp.createAPoint(algorythm);
		ArrayList<String> expected = new ArrayList<String>();
		for (int i = 0; i < backUp.restorePoints.get(0).files.size(); i++) {
			expected.add(backUp.restorePoints.get(0).files.get(i).name);
		}
		ArrayList<String> actual = new ArrayList<String>();
		actual.add("test1.txt");
		actual.add("test2.txt");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void sizeRestrictionTest() throws IOException {
		CreatingFullPoints algorythm = new CreatingFullPoints();
		BackUp backUp = new BackUp();
		backUp.setRemovingAlgorythm(new RemovingPointsRestrictions());
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).addRestriction(backUp,
				new pointsToRemoveSizeRestriction(20));
		backUp.addFile("test1.txt");
		backUp.createAPoint(algorythm);
		backUp.addFile("test2.txt");
		backUp.createAPoint(algorythm);
		backUp.addFile("test3.txt");
		backUp.createAPoint(algorythm);
		ArrayList<String> expected = new ArrayList<String>();
		for (int i = 0; i < backUp.restorePoints.get(0).files.size(); i++) {
			expected.add(backUp.restorePoints.get(0).files.get(i).name);
		}
		ArrayList<String> actual = new ArrayList<String>();
		actual.add("test1.txt");
		actual.add("test2.txt");
		actual.add("test3.txt");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void timeRestrictionTest() throws IOException, InterruptedException {
		CreatingFullPoints algorythm = new CreatingFullPoints();
		BackUp backUp = new BackUp();
		backUp.addFile("test1.txt");
		backUp.createAPoint(algorythm);
		backUp.addFile("test2.txt");
		backUp.createAPoint(algorythm);
		backUp.addFile("test3.txt");
		backUp.createAPoint(algorythm);
		TimeUnit.SECONDS.sleep(2);
		backUp.setRemovingAlgorythm(new RemovingPointsRestrictions());
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).addRestriction(backUp,
				new pointsToRemoveTimeRestriction(LocalDateTime.now()));
		int expected = backUp.restorePoints.size();
		int actual = 0;
		Assert.assertEquals(expected == actual, true);
	}

	@Test
	public void testDeltasRestriction() throws IOException {
		CreatingFullPoints algorythm = new CreatingFullPoints();
		CreatingDeltaPoints algorythmDelta = new CreatingDeltaPoints();
		BackUp backUp = new BackUp();
		backUp.addFile("test1.txt");
		backUp.createAPoint(algorythm);
		backUp.addFile("test2.txt");
		backUp.createAPoint(algorythmDelta);
		backUp.addFile("test3.txt");
		backUp.createAPoint(algorythmDelta);
		backUp.createAPoint(algorythm);
		backUp.setRemovingAlgorythm(new RemovingPointsRestrictions());
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).addRestriction(backUp,
				new pointsToRemoveNumberRestriction(2));
		int expected = backUp.restorePoints.size();
		int actual = 4;
		Assert.assertEquals(expected == actual, true);
	}

	@Test
	public void testMixedRestriction() throws IOException {
		CreatingFullPoints algorythm = new CreatingFullPoints();
		BackUp backUp = new BackUp();
		backUp.addFile("test1.txt");
		backUp.addFile("test2.txt");
		backUp.addFile("test3.txt");
		backUp.setRemovingAlgorythm(new RemovingPointsRestrictions());
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).setRestrictionsMax();
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).addRestriction(backUp,
				new pointsToRemoveNumberRestriction(2));
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).addRestriction(backUp,
				new pointsToRemoveSizeRestriction(20));
		backUp.createAPoint(algorythm);
		int expected = backUp.restorePoints.size();
		int actual = 1;
		Assert.assertEquals(expected == actual, true);
	}

	@Test
	public void test1() throws IOException {
		CreatingFullPoints algorythm = new CreatingFullPoints();
		BackUp backUp = new BackUp();
		backUp.addFile("test1.txt");
		backUp.addFile("test2.txt");
		backUp.createAPoint(algorythm);
		ArrayList<String> expected = new ArrayList<String>();
		for (int i = 0; i < backUp.restorePoints.get(0).files.size(); i++) {
			expected.add(backUp.restorePoints.get(0).files.get(i).name);
		}
		ArrayList<String> actual = new ArrayList<String>();
		actual.add("test1.txt");
		actual.add("test2.txt");
		Assert.assertEquals(expected, actual);
		backUp.createAPoint(algorythm);
		backUp.setRemovingAlgorythm(new RemovingPointsRestrictions());
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).addRestriction(backUp,
				new pointsToRemoveNumberRestriction(1));
		int expected1 = backUp.restorePoints.size();
		int actual1 = 1;
		Assert.assertEquals(expected1 == actual1, true);
	}

	@Test
	public void test2() throws IOException {
		CreatingFullPoints algorythm = new CreatingFullPoints();
		BackUp backUp = new BackUp();
		backUp.addFile("test4.txt");
		backUp.createAPoint(algorythm);
		backUp.createAPoint(algorythm);
		long expected = backUp.backUpsSize;
		long actual = 200;
		Assert.assertEquals(expected == actual, true);
		backUp.setRemovingAlgorythm(new RemovingPointsRestrictions());
		((RemovingPointsRestrictions) backUp.removingPointsAlgorythm).addRestriction(backUp,
				new pointsToRemoveSizeRestriction(150));
		int expected1 = backUp.restorePoints.size();
		int actual1 = 1;
		Assert.assertEquals(expected1 == actual1, true);
	}

}
