import java.io.IOException;

public class CreatingFullPoints implements CreatingPoints {

	@Override
	public void CreateAPoint(BackUp backUp) throws IOException {
		backUp.deltaFiles.clear();
		backUp.refreshFiles();
		RestorePoint restorePoint = new RestorePoint(backUp.files, false);
		backUp.lastBackUpSize = restorePoint.BackupSize;
		backUp.backUpsSize += restorePoint.BackupSize;
		backUp.restorePoints.add(restorePoint);
		backUp.lastRestorePoint = backUp.restorePoints.size() - 1;
		backUp.removePoints();
	}

}
