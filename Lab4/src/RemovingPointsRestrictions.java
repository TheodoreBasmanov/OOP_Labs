import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemovingPointsRestrictions implements RemovingPoints {
	ArrayList<PointsToRemove> restrictions;
	boolean MinPoints = true;
	boolean MaxPoints = false;

	RemovingPointsRestrictions() {
		restrictions = new ArrayList<PointsToRemove>();
	}

	void addRestriction(BackUp backUp, PointsToRemove restriction) {
		restrictions.add(restriction);
		removePoints(backUp);
	}

	void removeRestriction(BackUp backUp, PointsToRemove restriction) {
		restrictions.remove(restriction);
	}

	void setRestrictionsMax() {
		MaxPoints = true;
		MinPoints = false;
	}

	@Override
	public void removePoints(BackUp backUp) {
		if (!restrictions.isEmpty()) {
			int pointsToBeRemoved = 0;
			if (MinPoints) {
				pointsToBeRemoved = Integer.MAX_VALUE;
				for (int i = 0; i < restrictions.size(); i++) {
					pointsToBeRemoved = Math.min(pointsToBeRemoved, restrictions.get(i).PointsToRemove(backUp));
				}
			} else {
				pointsToBeRemoved = Integer.MIN_VALUE;
				for (int i = 0; i < restrictions.size(); i++) {
					pointsToBeRemoved = Math.max(pointsToBeRemoved, restrictions.get(i).PointsToRemove(backUp));
				}
			}
			backUp.lastRestorePoint -= pointsToBeRemoved;
			backUp.lastDeltaPoint -= pointsToBeRemoved;
			int i = 0;
			while (i < pointsToBeRemoved) {
				backUp.restorePoints.remove(0);
				i++;
			}
		}

	}
	static void giveARemoveWarning() {
		Logger logger = Logger.getLogger(BackUp.class.getName());
		logger.setLevel(Level.WARNING);
		logger.warning(
				"Can't remove restore points above the number restriction, because then delta points will be left without the actual restore point.");
	}

	static void giveARestrictionWarning() {
		Logger logger = Logger.getLogger(BackUp.class.getName());
		logger.setLevel(Level.WARNING);
		logger.warning("You must have set the restriction so, that no points can be saved.");
	}

}
