package lab1_fileAndClass;

import java.io.File;

public class BT01 {
	public static void main(String[] args) {
		dirTree("E://TaiLieuHocTap/Nam3/Ki1/LTmang/BT1");
	}

	public static String dirStatMain(String path, int NumTab) {
		String result = "";
		String tab = "";
		for (int i = 0; i < NumTab; i++) {
			tab += "|\t";
		}
		File file = new File(path);
		File[] listFile = file.listFiles();
		for (File f : listFile) {
			if (f.isFile()) {
				result += tab + "|--(" + f.length() + " byte)" + "\n";
			} else {
				result += tab + "|--(" + (getFolderSize(f.getAbsolutePath(), 0)) + " byte)" + "+\n";
				NumTab++;
				result += dirStatMain(f.getAbsolutePath(), NumTab);
				NumTab--;
			}
		}
		return result;
	}

	public static long getFolderSize(String path, long size) {
		File file = new File(path);
		File[] listFile = file.listFiles();
		if (file.exists()) {
			for (File f : listFile) {
				if (f.isFile()) {
					size += f.length();
				} else {
					size += getFolderSize(f.getAbsolutePath(), size);
				}
			}
		}
		return size;
	}

	public static void dirTree(String path) {
		String result = "";
		File file = new File(path);
		if (file.exists()) {
			result = "|--(" + getFolderSize(path, 0) + " byte)" + "+\n" + dirStatMain(path, 1);
		} else {
			result = "File khong ton tai";
		}
		System.out.println(result);
	}
}
