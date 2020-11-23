import java.time.LocalDateTime;

public class pointsToRemoveTimeRestriction implements PointsToRemove {
	LocalDateTime RestrictionTime;

	pointsToRemoveTimeRestriction(LocalDateTime restrictionTime) {
		RestrictionTime = restrictionTime;
	}

	@Override
	public int pointsToRemove(BackUp backUp) {
		int pointsToBeRemoved = 0;
		while ((pointsToBeRemoved < backUp.restorePoints.size())
				&& (backUp.restorePoints.get(pointsToBeRemoved).CreationTime.isBefore(RestrictionTime))) {
			pointsToBeRemoved++;
		}
		int numberOfDeltaPoints = 0;
		int i = pointsToBeRemoved;
		while ((i < backUp.restorePoints.size()) && (backUp.restorePoints.get(i).isDelta)) {
			numberOfDeltaPoints++;
		}
		if (numberOfDeltaPoints + 1 > pointsToBeRemoved) {
			RemovingPointsRestrictions.giveARemoveWarning();
			return 0;
		} else {
			return pointsToBeRemoved;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		pointsToRemoveTimeRestriction object = (pointsToRemoveTimeRestriction) o;
		return RestrictionTime.equals(object.RestrictionTime);
	}

}
