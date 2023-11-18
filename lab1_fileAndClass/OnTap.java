package lab1_fileAndClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OnTap {
	public static void main(String[] args) {
//		System.out.println(delete("E:\\GocHocTap\\code\\LTMang\\test\\BT1"));
//		findAll("E:\\GocHocTap\\code\\LTMang\\test\\BT1", ".docx");
//		List<String> paths = findAll("E:\\GocHocTap\\code\\LTMang\\test\\BT1", "*x");
//		for (String path : paths) {
//			System.out.println(path);
//		}
		System.out.println(dirTree("E:\\GocHocTap\\code\\LTMang\\test\\BT1"));
	}

	// bai 1
	public static boolean delete(String path) {
		File file = new File(path);
		if (file.exists()) {
			File[] fileChilds = file.listFiles();
			for (File fileChild : fileChilds) {
				if (fileChild.isDirectory()) {
					if (delete(fileChild.getAbsolutePath()) == false)
						return false;
					fileChild.delete();
				}
				if (fileChild.isFile()) {
					if (fileChild.delete() == false)
						return false;
				}
			}
			file.delete();
			return true;
		}
		return false;
	}

	// bai 2
	public static void findAll(String path, String... exts) {
		File file = new File(path);
		if (file.exists()) {
			for (File fileChild : file.listFiles()) {
				if (fileChild.isDirectory()) {
					for (String ext : exts) {
						if (checkNameMatchExt(fileChild.getName(), ext)) {
							System.out.println("Folder: " + fileChild.getAbsolutePath());
						}
					}
					findAll(fileChild.getAbsolutePath(), exts);
				}
				if (fileChild.isFile()) {
					for (String ext : exts) {
						if (checkNameMatchExt(fileChild.getName(), ext)) {
							System.out.println("File: " + fileChild.getAbsolutePath());
						}
					}
				}
			}
		}
	}

	public static boolean checkNameMatchExt(String name, String ext) {
		int begin = name.lastIndexOf('.');
		int end = name.length();
		if (begin != -1) {
			String extInName = name.substring(begin, end);
			return extInName.equals(ext);
		}
		return false;
	}

	// bai 3 (2)
	public static List<String> findAll(String path, String pattern) {
		File file = new File(path);
		List<String> result = new ArrayList<>();
		if (file.exists()) {
			for (File fileChild : file.listFiles()) {
				if (checkMathWithPattern(fileChild.getName(), pattern)) {
					result.add(fileChild.getAbsolutePath());
				}
				if (fileChild.isDirectory()) {
					List<String> paths = findAll(fileChild.getAbsolutePath(), pattern);
					if (!paths.isEmpty()) {
						result.addAll(paths);
					}
				}
			}
		}
		return result;
	}

	public static boolean checkMathWithPattern(String name, String pattern) {
		char[] charOfName = name.toCharArray();
		char[] charOfPattern = pattern.toCharArray();
		if (charOfName.length == charOfPattern.length) {
			for (int i = 0; i < charOfName.length; i++) {
				if (charOfName[i] != charOfPattern[i] && charOfPattern[i] != '*') {
					return false;
				}
			}
		} else if (charOfPattern.length > charOfName.length) {
			return false;
		} else {
			int NumOfDigit = charOfName.length - charOfPattern.length;
			int indexOfStar = pattern.indexOf('*');
			for (int i = 0; i < charOfName.length; i++) {
				if (i < indexOfStar) {
					if (charOfName[i] != charOfPattern[i]) {
						return false;
					}
				} else if (i > indexOfStar + NumOfDigit) {
					if (charOfName[i] != charOfPattern[i - NumOfDigit]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// bai 3
	public static String dirTree(String path) {
		String result = "";
		File file = new File(path);
		if (file.exists()) {
			result += "|--" + file.getName() + "\n" + dirTreeLoop(path, 1);
		}
		return result;
	}

	public static String dirTreeLoop(String path, int tabNum) {
		String result = "";
		String tab = "";
		for (int i = 0; i < tabNum; i++) {
			tab += "|\t";
		}
		File file = new File(path);
		for (File fileChild : file.listFiles()) {
			if (fileChild.isFile()) {
				result += tab + "|--" + fileChild.getName() + "\n";
			}
			if (fileChild.isDirectory()) {
				result += tab + "|--" + fileChild.getName() + "+\n";
				tabNum++;
				String rest = dirTreeLoop(fileChild.getAbsolutePath(), tabNum);
				tabNum--;
				result += rest;
			}
		}

		return result;
	}
}
