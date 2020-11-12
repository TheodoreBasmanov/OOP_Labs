import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BackUp {
	private static int ID = 0;
	long lastBackUpSize;
	long backUpsSize;
	ArrayList<String> files;
	ArrayList<String> deltaFiles;
	ArrayList<RestorePoint> restorePoints;
	int lastRestorePoint = 0;
	int lastDeltaPoint = 0;
	boolean NumberRestriction = false;
	boolean SizeRestriction = false;
	boolean TimeRestriction = false;
	int RestrictionN = 0;
	long RestrictionSize = 0;
	LocalDateTime RestrictionTime;

	BackUp() {
		lastBackUpSize = 0;
		files = new ArrayList<String>();
		restorePoints = new ArrayList<RestorePoint>();
	}

	void addFile(String filePath) throws Exceptions.TheFileDoesntExist {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exceptions.TheFileDoesntExist();
		}
		files.add(filePath);
		deltaFiles.add(filePath);
	}

	void removeFile(String filePath) throws Exceptions.TheFileHasntBeenAdded {
		if (files.indexOf(filePath) == -1) {
			throw new Exceptions.TheFileHasntBeenAdded();
		} else {
			files.remove(filePath);
		}
	}

	void createRestorePoint() throws IOException {
		RestorePoint restorePoint = new RestorePoint(ID, files, false);
		ID++;
		lastBackUpSize = restorePoint.BackupSize;
		backUpsSize += restorePoint.BackupSize;
		restorePoints.add(restorePoint);
		lastRestorePoint = restorePoints.size();
	}

	void createDeltaPoint() throws IOException, Exceptions.CantAddDeltaPoint {
		if (restorePoints.isEmpty()) {
			throw new Exceptions.CantAddDeltaPoint();
		}
		RestorePoint tmpRestorePoint = new RestorePoint(ID, files, false);
		long deltaSize = tmpRestorePoint.BackupSize - restorePoints.get(lastRestorePoint).BackupSize;
		lastBackUpSize = tmpRestorePoint.BackupSize;
		backUpsSize += deltaSize;
		RestorePoint deltaRestorePoint = new RestorePoint(ID, deltaFiles, true);
		restorePoints.add(deltaRestorePoint);
		lastDeltaPoint = restorePoints.size();
		ID++;
	}

	void setNumberRestriction(int N) {
		NumberRestriction = true;
		RestrictionN = N;
	}

	void setSizeRestriction(long Size) {
		SizeRestriction = true;
		RestrictionSize = Size;
	}

	void setTimeRestriction(LocalDateTime Time) {
		TimeRestriction = true;
		RestrictionTime = Time;
	}

	RestorePoint getLastRestorePoint() throws Exceptions.ThereAreNoRestorePoints {
		if (restorePoints.isEmpty()) {
			throw new Exceptions.ThereAreNoRestorePoints();
		}
		return restorePoints.get(lastRestorePoint);
	}

	RestorePoint getLastDeltaPoint() throws Exceptions.ThereAreNoDeltaPoints {
		if (lastDeltaPoint == 0) {
			throw new Exceptions.ThereAreNoDeltaPoints();
		}
		return restorePoints.get(lastDeltaPoint);
	}

}
