
public class pointsToRemoveNumberRestriction implements PointsToRemove {
	int RestrictionN;

	pointsToRemoveNumberRestriction(int restrictionN) {
		RestrictionN = restrictionN;
	}

	@Override
	public int PointsToRemove(BackUp backUp) {

		if (backUp.restorePoints.size() > RestrictionN) {
			if (backUp.restorePoints.size() >= 2) {

				int numberOfDeltaPoints = 0;
				int i = 1;
				while ((i < backUp.restorePoints.size()) && (backUp.restorePoints.get(i).isDelta)) {
					numberOfDeltaPoints++;
					i++;
				}
				if (backUp.restorePoints.size() - numberOfDeltaPoints - 1 < RestrictionN) {
					RemovingPointsRestrictions.giveARemoveWarning();
					return 0;

				} else {
					return backUp.restorePoints.size() - RestrictionN;
				}
			} else {
				RemovingPointsRestrictions.giveARestrictionWarning();
				return 1;
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
		pointsToRemoveNumberRestriction object = (pointsToRemoveNumberRestriction) o;
		return RestrictionN == object.RestrictionN;
	}

}
