import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackUp {
	private static int ID = 0;
	long lastBackUpSize;
	long backUpsSize;
	ArrayList<FileForBackup> files;
	ArrayList<FileForBackup> deltaFiles;
	ArrayList<RestorePoint> restorePoints;
	int lastRestorePoint = 0;
	int lastDeltaPoint = 0;
	ArrayList<PointsRemoving> restrictions;
	boolean MinPoints = true;
	boolean MaxPoints = false;

	BackUp() {
		lastBackUpSize = 0;
		backUpsSize = 0;
		files = new ArrayList<FileForBackup>();
		deltaFiles = new ArrayList<FileForBackup>();
		restorePoints = new ArrayList<RestorePoint>();
		restrictions = new ArrayList<PointsRemoving>();
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

	void createRestorePoint() throws IOException {
		deltaFiles.clear();
		refreshFiles();
		RestorePoint restorePoint = new RestorePoint(ID, files, false);
		ID++;
		lastBackUpSize = restorePoint.BackupSize;
		backUpsSize += restorePoint.BackupSize;
		restorePoints.add(restorePoint);
		lastRestorePoint = restorePoints.size() - 1;
		removePointsRestriction();
	}

	void createDeltaPoint() throws IOException, Exceptions.CantAddDeltaPoint {
		if (restorePoints.isEmpty()) {
			throw new Exceptions.CantAddDeltaPoint();
		}
		checkForDeltaFiles();
		RestorePoint tmpRestorePoint = new RestorePoint(ID, files, false);
		long deltaSize = tmpRestorePoint.BackupSize - restorePoints.get(lastRestorePoint).BackupSize;
		lastBackUpSize = tmpRestorePoint.BackupSize;
		backUpsSize += deltaSize;
		RestorePoint deltaRestorePoint = new RestorePoint(ID, deltaFiles, true);
		restorePoints.add(deltaRestorePoint);
		lastDeltaPoint = restorePoints.size() - 1;
		ID++;
		refreshFiles();
	}

	void addRestriction(PointsRemoving restriction) {
		restrictions.add(restriction);
		removePointsRestriction();
	}

	void removeRestriction(PointsRemoving restriction) {
		restrictions.remove(restriction);
	}

	void setRestrictionsMax() {
		MaxPoints = true;
		MinPoints = false;
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

	void giveARemoveWarning() {
		Logger logger = Logger.getLogger(BackUp.class.getName());
		logger.setLevel(Level.WARNING);
		logger.warning(
				"Can't remove restore points above the number restriction, because then delta points will be left without the actual restore point.");
	}

	void giveARestrictionWarning() {
		Logger logger = Logger.getLogger(BackUp.class.getName());
		logger.setLevel(Level.WARNING);
		logger.warning("You must have set the restriction so, that no points can be saved.");
	}

	void removePointsRestriction() {
		if (!restrictions.isEmpty()) {
			int pointsToBeRemoved = 0;
			if (MinPoints) {
				pointsToBeRemoved = Integer.MAX_VALUE;
				for (int i = 0; i < restrictions.size(); i++) {
					pointsToBeRemoved = Math.min(pointsToBeRemoved, restrictions.get(i).PointsToRemove(this));
				}
			} else {
				pointsToBeRemoved = Integer.MIN_VALUE;
				for (int i = 0; i < restrictions.size(); i++) {
					pointsToBeRemoved = Math.max(pointsToBeRemoved, restrictions.get(i).PointsToRemove(this));
				}
			}
			lastRestorePoint -= pointsToBeRemoved;
			lastDeltaPoint -= pointsToBeRemoved;
			int i = 0;
			while (i < pointsToBeRemoved) {
				restorePoints.remove(0);
				i++;
			}
		}
	}

}
