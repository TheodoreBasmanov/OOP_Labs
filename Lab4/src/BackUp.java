import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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
	boolean NumberRestriction = false;
	boolean SizeRestriction = false;
	boolean TimeRestriction = false;
	int RestrictionN = 0;
	long RestrictionSize = 0;
	LocalDateTime RestrictionTime;
	boolean MinPoints = true;
	boolean MaxPoints = false;

	BackUp() {
		lastBackUpSize = 0;
<<<<<<< HEAD
		files = new ArrayList<FileForBackup>();
		deltaFiles = new ArrayList<FileForBackup>();
=======
		files = new ArrayList<String>();
		deltaFiles = new ArrayList<String>();
>>>>>>> main
		restorePoints = new ArrayList<RestorePoint>();
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
<<<<<<< HEAD
		lastRestorePoint = restorePoints.size() - 1;
=======
		lastRestorePoint = restorePoints.size()-1;
>>>>>>> main
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
<<<<<<< HEAD
		lastDeltaPoint = restorePoints.size() - 1;
=======
		lastDeltaPoint = restorePoints.size()-1;
>>>>>>> main
		ID++;
		refreshFiles();
	}

	void setNumberRestriction(int N) throws Exceptions.NegativeNumberRestriction {
		if (N < 0) {
			throw new Exceptions.NegativeNumberRestriction();
		}
		NumberRestriction = true;
		RestrictionN = N;
		removePointsRestriction();
	}

	void setSizeRestriction(long Size) throws Exceptions.NegativeSizeRestriction {
		if (Size < 0) {
			throw new Exceptions.NegativeSizeRestriction();
		}
		SizeRestriction = true;
		RestrictionSize = Size;
		removePointsRestriction();
	}

	void setTimeRestriction(LocalDateTime Time) {
		TimeRestriction = true;
		RestrictionTime = Time;
		removePointsRestriction();
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

	int pointsToRemoveNumberRestriction() {
<<<<<<< HEAD

		if (restorePoints.size() > RestrictionN) {
			if (restorePoints.size() >= 2) {

=======
	
		if (restorePoints.size() > RestrictionN) {
			if (restorePoints.size() >= 2) {
				
>>>>>>> main
				int numberOfDeltaPoints = 0;
				int i = 1;
				while ((i < restorePoints.size()) && (restorePoints.get(i).isDelta)) {
					numberOfDeltaPoints++;
					i++;
				}
				if (restorePoints.size() - numberOfDeltaPoints - 1 < RestrictionN) {
					giveARemoveWarning();
					return 0;

				} else {
					return restorePoints.size() - RestrictionN;
				}
			} else {
				giveARestrictionWarning();
				return 1;
			}
		}
		return 0;
	}

	int pointsToRemoveSizeRestriction() {
		if (backUpsSize > RestrictionSize) {
			long sizeToBeRemoved = 0;
			int pointsToBeRemoved = 0;
<<<<<<< HEAD
			while ((pointsToBeRemoved < restorePoints.size()) && (backUpsSize - RestrictionSize > sizeToBeRemoved)) {
=======
			while (backUpsSize - RestrictionSize > sizeToBeRemoved) {
>>>>>>> main
				sizeToBeRemoved += restorePoints.get(pointsToBeRemoved).BackupSize;
				pointsToBeRemoved++;
			}
			int numberOfDeltaPoints = 0;
			int i = pointsToBeRemoved;
			while ((i < restorePoints.size()) && (restorePoints.get(i).isDelta)) {
				numberOfDeltaPoints++;
			}
			if (numberOfDeltaPoints + 1 > pointsToBeRemoved) {
				giveARemoveWarning();
				return 0;
			} else {
				return pointsToBeRemoved;
			}
		}
		return 0;
	}

	int pointsToRemoveTimeRestriction() {
		int pointsToBeRemoved = 0;
<<<<<<< HEAD
		while ((pointsToBeRemoved < restorePoints.size())
				&& (restorePoints.get(pointsToBeRemoved).CreationTime.isBefore(RestrictionTime))) {
=======
		while (restorePoints.get(pointsToBeRemoved).CreationTime.isBefore(RestrictionTime)) {
>>>>>>> main
			pointsToBeRemoved++;
		}
		int numberOfDeltaPoints = 0;
		int i = pointsToBeRemoved;
		while ((i < restorePoints.size()) && (restorePoints.get(i).isDelta)) {
			numberOfDeltaPoints++;
		}
		if (numberOfDeltaPoints + 1 > pointsToBeRemoved) {
			giveARemoveWarning();
			return 0;
		} else {
			return pointsToBeRemoved;
		}
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
		if (NumberRestriction || SizeRestriction || TimeRestriction) {
			int pointsToBeRemoved = 0;
			int pointsToBeRemovedNumber = 0;
			int pointsToBeRemovedSize = 0;
			int pointsToBeRemovedTime = 0;
			if (NumberRestriction) {
				pointsToBeRemovedNumber = pointsToRemoveNumberRestriction();
			} else {
				if (MinPoints) {
					pointsToBeRemovedNumber = Integer.MAX_VALUE;
				} else {
					pointsToBeRemovedNumber = Integer.MIN_VALUE;
				}
			}
			if (SizeRestriction) {
				pointsToBeRemovedSize = pointsToRemoveSizeRestriction();
			} else {
				if (MinPoints) {
					pointsToBeRemovedSize = Integer.MAX_VALUE;
				} else {
					pointsToBeRemovedSize = Integer.MIN_VALUE;
				}
			}
			if (TimeRestriction) {
				pointsToBeRemovedTime = pointsToRemoveTimeRestriction();
			} else {
				if (MinPoints) {
					pointsToBeRemovedTime = Integer.MAX_VALUE;
				} else {
					pointsToBeRemovedTime = Integer.MIN_VALUE;
				}
			}
			if (MinPoints) {
				pointsToBeRemoved = Math.min(pointsToBeRemovedNumber,
						Math.min(pointsToBeRemovedSize, pointsToBeRemovedTime));
			}
			if (MaxPoints) {
				pointsToBeRemoved = Math.max(pointsToBeRemovedNumber,
						Math.max(pointsToBeRemovedSize, pointsToBeRemovedTime));
			}
			lastRestorePoint -= pointsToBeRemoved;
			lastDeltaPoint -= pointsToBeRemoved;
			int i = 0;
			while (i < pointsToBeRemoved) {
				restorePoints.remove(0);
				i++;
			}
			/*
			 * for (int i = 0; i < pointsToBeRemoved; i++) {
			 * restorePoints.remove(i); }
			 */
		}
	}

}
