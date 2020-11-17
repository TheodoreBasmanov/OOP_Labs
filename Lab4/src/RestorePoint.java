import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RestorePoint {
	int Id;
	LocalDateTime CreationTime;
	long BackupSize;
	ArrayList<FileForBackup> files;
	boolean isDelta;

	RestorePoint(int Id, ArrayList<FileForBackup> files, boolean isDelta) throws IOException {
		this.Id = Id;
		this.CreationTime = LocalDateTime.now();
<<<<<<< HEAD
		this.files = new ArrayList<FileForBackup>();
		this.files.addAll(files);
		if (isDelta) {
			BackupSize = this.getDeltaSize();
		} else {
			BackupSize = this.getSize();
		}
=======
		this.files = new ArrayList<String>();
		this.files.addAll(files);
		BackupSize = this.getSize();
>>>>>>> main
		this.isDelta = isDelta;
	}

	private long getSize() {
		long Size = 0;
		for (int i = 0; i < files.size(); i++) {
			Size += files.get(i).size;
		}
		return Size;
	}

	private long getDeltaSize() {
		long Size = 0;
		for (int i = 0; i < files.size(); i++) {
			if(!files.get(i).modified){
			Size += files.get(i).sizeDifference;
			}else{
				Size+=files.get(i).getSize()-files.get(i).size;
			}
		}
		return Size;
	}

}
