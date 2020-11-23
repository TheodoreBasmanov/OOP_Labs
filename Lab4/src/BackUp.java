import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BackUp {
	long lastBackUpSize;
	long backUpsSize;
	ArrayList<FileForBackup> files;
	ArrayList<FileForBackup> deltaFiles;
	ArrayList<RestorePoint> restorePoints;
	int lastRestorePoint = 0;
	int lastDeltaPoint = 0;
	RemovingPoints removingPointsAlgorythm;

	BackUp() {
		lastBackUpSize = 0;
		backUpsSize = 0;
		files = new ArrayList<FileForBackup>();
		deltaFiles = new ArrayList<FileForBackup>();
		restorePoints = new ArrayList<RestorePoint>();
		// creatingPointsAlgorythm = algorythm;
	}

	void addFile(String filePath) throws Exceptions.TheFileDoesntExist {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exceptions.TheFileDoesntExist();
		}
		files.add(new FileForBackup(filePath));
		deltaFiles.add(new FileForBackup(filePath));
	}

	void removeFile(String filePath) throws Exceptions.TheFileHasntBeenAdded {
		boolean noFileFlag = true;
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).name.equals(filePath)) {
				files.remove(i);
				noFileFlag = false;
			}
		}
		if (noFileFlag) {
			throw new Exceptions.TheFileHasntBeenAdded();
		}
	}

	void checkForDeltaFiles() {
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).lastModified == files.get(i).file.lastModified()) {
				deltaFiles.add(files.get(i));
				deltaFiles.get(deltaFiles.size() - 1).modified = true;
			}
		}
	}

	void refreshFiles() {
		for (int i = 0; i < files.size(); i++) {
			files.get(i).lastModified = files.get(i).file.lastModified();
			files.get(i).size = files.get(i).getSize();
		}
	}

	void createAPoint(CreatingPoints algorythm) throws IOException {
		algorythm.CreateAPoint(this);
	}

	void setRemovingAlgorythm(RemovingPoints algorythm) {
		removingPointsAlgorythm = algorythm;
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

	void removePoints() {
		if (removingPointsAlgorythm != null) {
			removingPointsAlgorythm.removePoints(this);
		}
	}
}
