import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RestorePointTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sizeTest() throws IOException {
		ArrayList<String> files = new ArrayList<String>();
		files.add("test1.txt");
		files.add("test2.txt");
		files.add("test3.txt");
		RestorePoint restorePoint = new RestorePoint(1, files, false);
		long expected = restorePoint.BackupSize;
		long actual = 15;
		Assert.assertEquals(expected == actual, true);
	}

}
