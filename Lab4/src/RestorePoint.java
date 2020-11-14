import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RestorePoint {
	int Id;
	LocalDateTime CreationTime;
	long BackupSize;
	ArrayList<String> files;
	boolean isDelta;

	RestorePoint(int Id, ArrayList<String> files, boolean isDelta) throws IOException {
		this.Id = Id;
		this.CreationTime = LocalDateTime.now();
		this.files = new ArrayList<String>();
		this.files.addAll(files);
		BackupSize = this.getSize();
		this.isDelta = isDelta;
	}

	private long getSize() throws IOException, Exceptions.TheFileDoesntExist {
		long Size = 0;
		File file;
		long fileSize = 0;
		for (int i = 0; i < files.size(); i++) {
			file = new File(files.get(i));
			if (!file.exists()) {
				throw new Exceptions.TheFileDoesntExist();
			} else {
				if (file.isDirectory()) {
					fileSize = getFolderSize(file);
				} else {
					fileSize = file.length();
				}
			}
			Size += fileSize;
		}
		return Size;
	}

	public static long getFolderSize(File dir) {
		long size = 0;
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				System.out.println(file.getName() + " " + file.length());
				size += file.length();
			} else
				size += getFolderSize(file);
		}
		return size;
	}
}
