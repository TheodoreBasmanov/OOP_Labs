import java.io.File;

public class FileForBackup {
	File file;
	String name;
	long lastModified;
	long size;
	boolean modified;
	long sizeDifference;

	FileForBackup(String name) {
		this.name = name;
		file = new File(name);
		lastModified = file.lastModified();
		long size = this.getSize();
		this.size = size;
		this.sizeDifference = size;
		modified = false;
		
	}
	private long getFolderSize(File dir) {
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
	long getSize(){
		if (file.isDirectory()) {
			return getFolderSize(file);
		} else {
			return file.length();
		}
	}
}


