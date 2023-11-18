package lab1_fileAndClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BT1 {
	public static void main(String[] args) {
//		System.out.println(deleteAll("E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/BT1"));
//		System.out.println(deleteFile("E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/BT1"));
//		findAll("E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/BT1", ".docx", ".pptx");
//		findAll("E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/BT1", "*.docx");
		dirTree("E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/BT1");
//		dirStat("E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/BT1");
//		deleteALL("E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/BT1");
//		copyAll("E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/BT1", "E://TaiLieuHocTap/Nam3/Ki1/LTmang_thayTinh/s");

	}

	public static boolean deleteAll(String path) {
		boolean istrue = false;
		File file = new File(path);
		if (file.exists()) {
			if (file.list().length != 0) {
				for (File f : file.listFiles()) {
					if (f.isFile()) {
						f.delete();
					} else if (f.isDirectory()) {
						deleteAll(f.getAbsolutePath());
						f.delete();
					}
				}
			}
			System.out.println("\n" + "Xóa " + file.getAbsolutePath());
			file.delete();
			istrue = true;
		}
		return istrue;
	}

	public static void findAll(String path, String... ext) {
		File file = new File(path);
		if (file.exists()) {
			File[] listFile = file.listFiles();
			for (File f : listFile) {
				if (f.isFile()) {
					for (String s : ext) {
						String fileName = f.getName();
						if (s.equals(fileName.substring(fileName.lastIndexOf('.')))) {
							System.out.println(f.getAbsolutePath());
						}
					}
				} else {
					findAll(f.getAbsolutePath(), ext);
				}
			}
		}
	}

	public static boolean deleteFile(String path) {
		boolean istrue = false;
		File file = new File(path);
		if (file.exists()) {
			if (file.list().length != 0) {
				for (File f : file.listFiles()) {
					if (f.isFile()) {
						f.delete();
						System.out.println("Xóa " + f.getAbsolutePath());
					} else if (f.isDirectory()) {
						System.out.println("\n" + f.getAbsolutePath());
						deleteFile(f.getAbsolutePath());
					}
				}
			}
			istrue = true;
		}
		return istrue;
	}

	public static boolean soSanh(String s1, String s2) {
		int more = Math.abs(s2.length() - s1.length());
		boolean result = false;
		if (s1.indexOf("*") != -1) {
			StringBuffer stringbf1 = new StringBuffer(s1);
			for (int i = 0; i < more; i++) {
				stringbf1.insert(s1.indexOf("*"), "*");
			}
			s1 = stringbf1.toString();
		}
		char[] charS1 = s1.toCharArray();
		char[] charS2 = s2.toCharArray();
		for (int i = 0; i < charS1.length; i++) {
			if (charS1[i] == charS2[i]) {
				result = true;
			} else {
				if (charS1[i] == '*' && more != -1) {
					result = true;
					more--;
				} else {
					result = false;
					break;
				}
			}
		}
		return result;
	}

//	viết riêng 1 hàm so sánh tên file với pattern
//	B1: lấy độ dài tên file - pattern;
//	B2: kiểm tra có tồn tại dấu * trong pattern hay không nếu có thì chèn thêm vài dấu * = B1 nữa vào dấu * đầu tiên
//	B3: Kiểm tra từng kí tự trong tên file với pattern nếu là dấu * mình chèn vô thì bỏ qua còn nếu là kí tự khác thì phải giống nhau
//	viết hàm FindAll
//	chạy từng file con trong path rồi gọi hàm trên để so sánh tên nếu giống thì in đường dẫn rồi kiểm tra nếu f là thư mục thì đệ quy
	public static void findAll(String path, String pattern) {
		File file = new File(path);
		File[] listFile = file.listFiles();
		if (listFile.length != 0) {
			for (File f : listFile) {
				if (soSanh(pattern, f.getName())) {
					System.out.println(f.getAbsolutePath());
				}
				if (f.isDirectory()) {
					findAll(f.getAbsolutePath(), pattern);
				}
			}
		}
	}


	public static String dirTreeMain(String path, int NumTab) {
		String result = "";
		String tab = "";
		for (int i = 0; i < NumTab; i++) {
			tab += "|\t";
		}
		File file = new File(path);
		File[] listFile = file.listFiles();
		for (File f : listFile) {
			if (f.isFile()) {
				result += tab + "|--" + f.getName() + "\n";
			} else {
				result += tab + "|--" + f.getName() + "+\n";
				NumTab++;
				result += dirTreeMain(f.getAbsolutePath(), NumTab);
				NumTab--;
			}
		}
		return result;
	}

	public static void dirTree(String path) {
		String result = "";
		File file = new File(path);
		if (file.exists()) {
			result = "|--" + file.getName() + "+\n" + dirTreeMain(path, 1);
		} else {
			result = "File khong ton tai";
		}
		System.out.println(result);
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
				result += tab + "|--(" + f.length() + " byte) " + f.getName() + "\n";
			} else {
				result += tab + "|--(" + (getFolderSize(f.getAbsolutePath(), 0)) + " byte) " + f.getName() + "+\n";
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

	public static void dirStat(String path) {
		String result = "";
		File file = new File(path);
		if (file.exists()) {
			result = "|--(" + getFolderSize(path, 0) + " byte) " + file.getName() + "+\n" + dirStatMain(path, 1);
		} else {
			result = "File khong ton tai";
		}
		System.out.println(result);
	}

	public static void deleteAll(String path, String... ext) {
		File file = new File(path);
		if (file.exists()) {
			if (ext.length == 0) {
				deleteAll(path);
			} else {
				File[] listFile = file.listFiles();
				for (File f : listFile) {
					if (f.isFile()) {
						for (String s : ext) {
							if (f.getName().endsWith(s)) {
								System.out.println("delete " + f.getName());
								f.delete();
							}
						}
					} else {
						deleteAll(f.getAbsolutePath(), ext);
					}
				}
			}
		}
	}

	public static void copyAll(String sDir, String dDir, String... ext) {
		File sFile = new File(sDir);
		File dFile = new File(dDir);
		if (sFile.isDirectory()) {
			if (!dFile.exists()) {
				dFile.mkdir();
			}
			File[] listFile = sFile.listFiles();
			for (File f : listFile) {
				File dDir1 = new File(dFile, f.getName());
				copyAll(f.getAbsolutePath(), dDir1.getAbsolutePath(), ext);
			}
			if (ext.length != 0) {
				if (dFile.length() == 0) {
					dFile.delete();
				}
			}
		} else {
			if (ext.length == 0) {
				try {
					Files.copy(sFile.toPath(), dFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				for (String s : ext) {
					if (sFile.getName().endsWith(s)) {
						try {
							Files.copy(sFile.toPath(), dFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
