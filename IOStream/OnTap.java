package IOStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OnTap {

	public static void main(String[] args) {
		try {
			System.out.println(folderCopy("E:\\GocHocTap\\code\\LTMang\\test\\BT1",
					"E:\\GocHocTap\\code\\LTMang\\test\\BT1(copy)", false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// bai 8
	public static boolean fileCopy(String sourceFile, String destFile, boolean moved) throws IOException {
		File sFile = new File(sourceFile);
		if (sFile.exists() && sFile.isFile()) {
			BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(sourceFile));
			BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(destFile));

			byte[] data = new byte[1000];
			int byteRead = 0;
			while ((byteRead = BIS.read(data)) != -1) {
				BOS.write(data, 0, byteRead);
			}
			BIS.close();
			BOS.close();
			if (moved) {
				sFile.delete();
			}
			return true;
		}
		return false;
	}

	// bai 9
	public static boolean folderCopy(String sourceFolder, String destFolder, boolean moved) throws IOException {
		File sFolder = new File(sourceFolder);
		if (sFolder.exists() && sFolder.isDirectory()) {
			File dFolder = new File(destFolder);
			if (!dFolder.exists()) {
				dFolder.mkdir();
			}
			for (File fileChild : sFolder.listFiles()) {
				if (fileChild.isFile()) {
					boolean copyFile = fileCopy(fileChild.getAbsolutePath(),
							dFolder.getAbsolutePath() + "\\" + fileChild.getName(), moved);
					if (copyFile == false) {
						return false;
					}
				}
				if (fileChild.isDirectory()) {
					File childDirectories = new File(dFolder.getAbsoluteFile() + "\\" + fileChild.getName());
					childDirectories.mkdir();
					boolean copyFolder = folderCopy(fileChild.getAbsolutePath(), childDirectories.getAbsolutePath(),
							moved);
					if (copyFolder == false) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
}
