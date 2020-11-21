
public class pointsToRemoveSizeRestriction implements PointsToRemove {
	long RestrictionSize;

	pointsToRemoveSizeRestriction(long restrictionSize) {
		RestrictionSize = restrictionSize;
	}

	@Override
	public int PointsToRemove(BackUp backUp) {
		if (backUp.backUpsSize > RestrictionSize) {
			long sizeToBeRemoved = 0;
			int pointsToBeRemoved = 0;
			while ((pointsToBeRemoved < backUp.restorePoints.size())
					&& (backUp.backUpsSize - RestrictionSize > sizeToBeRemoved)) {
				sizeToBeRemoved += backUp.restorePoints.get(pointsToBeRemoved).BackupSize;
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
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		pointsToRemoveSizeRestriction object = (pointsToRemoveSizeRestriction) o;
		return RestrictionSize == object.RestrictionSize;
	}

}
