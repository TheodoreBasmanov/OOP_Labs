import java.io.IOException;

public class CreatingDeltaPoints implements CreatingPoints {
	@Override
	public void CreateAPoint(BackUp backUp) throws IOException {
		if (backUp.restorePoints.isEmpty()) {
			throw new Exceptions.CantAddDeltaPoint();
		}
		backUp.checkForDeltaFiles();
		RestorePoint tmpRestorePoint = new RestorePoint(backUp.files, false);
		long deltaSize = tmpRestorePoint.BackupSize - backUp.restorePoints.get(backUp.lastRestorePoint).BackupSize;
		backUp.lastBackUpSize = tmpRestorePoint.BackupSize;
		backUp.backUpsSize += deltaSize;
		RestorePoint deltaRestorePoint = new RestorePoint(backUp.deltaFiles, true);
		backUp.restorePoints.add(deltaRestorePoint);
		backUp.lastDeltaPoint = backUp.restorePoints.size() - 1;
		backUp.refreshFiles();
	}

}