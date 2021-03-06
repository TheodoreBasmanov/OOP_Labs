import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		ArrayList<FileForBackup> files = new ArrayList<FileForBackup>();
		files.add(new FileForBackup("test1.txt"));
		files.add(new FileForBackup("test2.txt"));
		files.add(new FileForBackup("test3.txt"));
		RestorePoint restorePoint = new RestorePoint(files, false);
		long expected = restorePoint.BackupSize;
		long actual = files.get(0).file.length() + files.get(1).file.length() + files.get(2).file.length();
		Assert.assertEquals(expected == actual, true);
	}

	@Test
	public void sizeDeltaTest() throws IOException {
		ArrayList<FileForBackup> files = new ArrayList<FileForBackup>();
		files.add(new FileForBackup("test5.txt"));
		files.add(new FileForBackup("test2.txt"));
		files.add(new FileForBackup("test3.txt"));
		FileWriter pw = new FileWriter("test5.txt", true);
		pw.write("diff");
		pw.close();
		files.get(0).modified = true;
		RestorePoint restorePoint = new RestorePoint(files, true);
		long expected = restorePoint.BackupSize;
		long actual = 14;
		Assert.assertEquals(expected == actual, true);
	}

}
