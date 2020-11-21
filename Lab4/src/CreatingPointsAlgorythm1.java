import java.io.IOException;

public class CreatingPointsAlgorythm1 implements CreatingPoints{
	
	@Override
	public void CreateRestorePoint(BackUp backUp) {
		backUp.deltaFiles.clear();
		backUp.refreshFiles();
		RestorePoint restorePoint = null;
		try {
			restorePoint = new RestorePoint(BackUp.getID(), backUp.files, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BackUp.setID(BackUp.getID() + 1);
		backUp.lastBackUpSize = restorePoint.BackupSize;
		backUp.backUpsSize += restorePoint.BackupSize;
		backUp.restorePoints.add(restorePoint);
		backUp.lastRestorePoint = backUp.restorePoints.size() - 1;
		backUp.removePoints();
		
	}

	@Override
	public void CreateDeltaPoint(BackUp backUp) {
		if (backUp.restorePoints.isEmpty()) {
			throw new Exceptions.CantAddDeltaPoint();
		}
		backUp.checkForDeltaFiles();
		RestorePoint tmpRestorePoint=null;
		try {
			tmpRestorePoint = new RestorePoint(BackUp.getID(), backUp.files, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long deltaSize = tmpRestorePoint.BackupSize - backUp.restorePoints.get(backUp.lastRestorePoint).BackupSize;
		backUp.lastBackUpSize = tmpRestorePoint.BackupSize;
		backUp.backUpsSize += deltaSize;
		RestorePoint deltaRestorePoint=null;
		try {
			deltaRestorePoint = new RestorePoint(BackUp.getID(), backUp.deltaFiles, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		backUp.restorePoints.add(deltaRestorePoint);
		backUp.lastDeltaPoint = backUp.restorePoints.size() - 1;
		BackUp.setID(BackUp.getID() + 1);
		backUp.refreshFiles();
		
	}

}
