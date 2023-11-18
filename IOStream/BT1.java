package IOStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class BT1 {
	public static void main(String[] args) {
		String sFile = "E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTmang\\io\\file1.txt";
		String dFile = "E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTmang\\io\\file1_copy.txt";
//		copyFile(sFile, dFile, true);
//		splitFile(sFile, 4);
		try {
//			joinerFile("E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTmang\\io\\file1Split");
//			pack("E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTmang\\io\\pack");
			unpack("E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTmang\\io\\pack.ltm", "3.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void copyFile(String sFile, String dFile, boolean moved) {
		File sourceFile = new File(sFile);
		if (sourceFile.exists()) {
			File destFile = new File(dFile);
			long countByte = sourceFile.length();
			try {
				FileInputStream fis = new FileInputStream(sourceFile);
				FileOutputStream fos = new FileOutputStream(destFile);
//				long currentTime = System.currentTimeMillis();
				byte[] b = new byte[(int) countByte];
//				long endTime = 0;
				while (fis.read(b) != -1) {
					fos.write(b);
				}
//				endTime = System.currentTimeMillis();
//				System.out.println((endTime - currentTime));
				fis.close();
				fos.close();
				if (moved) {
					sourceFile.delete();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void splitFile(String sFile, long dataSize) {
		File sourceFile = new File(sFile);
		if (sourceFile.exists()) {
			String dname = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf('.'));
			File dFolder = new File(sourceFile.getParent() + "\\" + dname + "Split");
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sFile));
				if (!dFolder.exists()) {
					dFolder.mkdir();
				}
				int numOfFile = (int) (sourceFile.length() / dataSize);
				int rest = (int) (sourceFile.length() % dataSize);
				byte[] b = new byte[(int) dataSize];
				for (int i = 0; i < numOfFile; i++) {
					String split = dFolder.getAbsolutePath() + "\\" + dname + "Split" + (i + 1) + ".txt";
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(split));
					bis.read(b);
					bos.write(b);
					bos.close();
				}
				if (rest != 0) {
					String split = dFolder.getAbsolutePath() + "\\" + dname + "Split" + (numOfFile + 1) + ".txt";
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(split));
					b = new byte[rest];
					bis.read(b);
					bos.write(b);
					bos.close();
				}
				bis.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void joinerFile(String sFolder) throws Exception {
		File sourceFolder = new File(sFolder);
		if (sourceFolder.exists() && sourceFolder.isDirectory()) {
			File[] listFile = sourceFolder.listFiles();
			String nameJoinFile = sourceFolder.getName().substring(0, sourceFolder.getName().lastIndexOf("Split"));
			String nameDestFile = sourceFolder.getParent() + "\\" + nameJoinFile + "-join" + ".txt";
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(nameDestFile));
			int size = listFile.length;
			int fileBeginSize = (int) listFile[0].length();
			int fileEndSize = (int) listFile[size - 1].length();
			for (int i = 0; i < size; i++) {
				byte[] b = new byte[fileBeginSize];
				if (i == size - 1 && fileBeginSize != fileEndSize) {
					b = new byte[fileEndSize];
				}
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(listFile[i].getAbsoluteFile()));
				bis.read(b);
				bos.write(b);
				bis.close();
			}
			bos.flush();
			bos.close();
		}
	}

	public static void pack(String sFolder) throws Exception {
		File file = new File(sFolder);
		File[] lisFiles = file.listFiles();
		if (file.exists() && file.isDirectory()) {
			String dFile = file.getParent() + "\\" + file.getName() + ".ltm";
			RandomAccessFile rdf = new RandomAccessFile(dFile, "rw");
			rdf.writeInt(lisFiles.length);
			long[] seekName = new long[lisFiles.length];
			for (int i = 0; i < lisFiles.length; i++) {
				rdf.writeUTF(lisFiles[i].getName());
				rdf.writeLong(lisFiles[i].length());
				seekName[i] = rdf.getFilePointer();
				rdf.writeLong(0);
			}
			for (int i = 0; i < lisFiles.length; i++) {
				RandomAccessFile rdfChild = new RandomAccessFile(lisFiles[i], "r");
				long size = lisFiles[i].length();
				byte[] b = new byte[(int) size];
				long filePoiter = rdf.getFilePointer();
				rdfChild.read(b);
				rdf.write(b);
				long currentPoiter = rdf.getFilePointer();
				rdfChild.close();
				rdf.seek(seekName[i]);
				rdf.writeLong(filePoiter);
				rdf.seek(currentPoiter);
			}
		}
	}

	public static void unpack(String sFile, String... fileName) throws Exception {
		File sourceFile = new File(sFile);
		if (sourceFile.exists()) {
			RandomAccessFile rdf = new RandomAccessFile(sFile, "r");
			int len = rdf.readInt();
			fileIfo[] fileIfo = new fileIfo[len];
			for (int i = 0; i < len; i++) {
				String name = rdf.readUTF();
				long size = rdf.readLong();
				long poiter = rdf.readLong();
				fileIfo[i] = new fileIfo(name, size, poiter);
			}
			if (fileName.length == 0) {
				for (int i = 0; i < len; i++) {
					String fileResult = sourceFile.getParent() + "\\" + fileIfo[i].getName();
					RandomAccessFile rdf1 = new RandomAccessFile(fileResult, "rw");
					byte[] b = new byte[(int) fileIfo[i].getSize()];
					rdf.seek(fileIfo[i].getPoiter());
					rdf.read(b);
					rdf1.write(b);
					rdf1.close();
				}
			} else {
				int count = fileName.length;
				for (int i = 0; i < len; i++) {
					for (int j = 0; j < fileName.length; j++) {
						if (fileIfo[i].getName().equals(fileName[j])) {
							String fileResult = sourceFile.getParent() + "\\" + fileIfo[i].getName();
							RandomAccessFile rdf1 = new RandomAccessFile(fileResult, "rw");
							byte[] b = new byte[(int) fileIfo[i].getSize()];
							rdf.seek(fileIfo[i].getPoiter());
							rdf.read(b);
							rdf1.write(b);
							rdf1.close();
							count--;
							if(count == 0) {
								break;
							}
						}
					}
					if(count == 0) {
						break;
					}
				}
			}
			rdf.close();
		}
	}
}
