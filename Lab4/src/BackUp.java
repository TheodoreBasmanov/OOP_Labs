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
	boolean MinPoints = true;
	boolean MaxPoints = false;

	BackUp() {
		lastBackUpSize = 0;
		files = new ArrayList<String>();
		deltaFiles = new ArrayList<String>();
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
		lastRestorePoint = restorePoints.size()-1;
		removePointsRestriction();
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
		lastDeltaPoint = restorePoints.size()-1;
		ID++;
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
	
		if (restorePoints.size() > RestrictionN) {
			if (restorePoints.size() >= 2) {
				
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
			while (backUpsSize - RestrictionSize > sizeToBeRemoved) {
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
		while (restorePoints.get(pointsToBeRemoved).CreationTime.isBefore(RestrictionTime)) {
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
