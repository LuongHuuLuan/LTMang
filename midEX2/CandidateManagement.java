package midEX2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class CandidateManagement {

	public void saveInfo(ArrayList<Candidate> listCan, String destFile) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(destFile, "rw");
		int size = listCan.size();
		long cvLoc[] = new long[size];
		int i = 0;
		// save header
		raf.writeInt(size);
		for (Candidate can : listCan) {
			raf.writeUTF(can.getId());
			raf.writeInt(can.getAge());
			raf.writeUTF(can.getFullName());
			cvLoc[i++] = raf.getFilePointer();
			raf.writeLong(0);
		}
		i = 0;
		// save data
		for (Candidate can : listCan) {
			// saveLocation
			File file = new File(can.getLinkCV());
			long currentPoiter = raf.getFilePointer();
			raf.seek(cvLoc[i++]);
			raf.writeLong(currentPoiter);
			raf.seek(currentPoiter);
			// save data
			raf.writeUTF(file.getName());
			raf.writeLong(file.length());
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(can.getLinkCV()));
			byte[] data = new byte[1000];
			while (bis.read(data) != -1) {
				raf.write(data);
			}
			bis.close();
		}
		raf.close();
	}

	public void getCVById(String sourceFile, String idCan, String destFolder) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(sourceFile, "rw");
		int size = raf.readInt();
		for (int i = 0; i < size; i++) {
			String id = raf.readUTF();
			if (id.equals(idCan)) {
				raf.readInt();
				raf.readUTF();
				long fileLoc = raf.readLong();
				raf.seek(fileLoc);
				String fileName = raf.readUTF();
				long fileSize = raf.readLong();
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFolder + "\\" + fileName));
				int byteNum = (int) (fileSize / 1000);
				int byteRest = (int) (fileSize % 1000);
				if (byteNum != 0) {
					for (int j = 0; j < byteNum; j++) {
						byte[] data = new byte[1000];
						raf.read(data);
						bos.write(data);
					}
				}
				if (byteRest != 0) {
					byte[] data = new byte[byteRest];
					raf.read(data);
					bos.write(data);
				}
				bos.close();
				raf.close();
				break;
			} else {
				raf.readInt();
				raf.readUTF();
				raf.readLong();
			}
		}
		raf.close();
	}

	public void getCVByAge(String sourceFile, int ageCan, String destFolder) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(sourceFile, "rw");
		int size = raf.readInt();
		for (int i = 0; i < size; i++) {
			raf.readUTF();
			int age = raf.readInt();
			if (age == ageCan) {
				raf.readUTF();
				long fileLoc = raf.readLong();
				long currentPoiter = raf.getFilePointer();
				raf.seek(fileLoc);
				String fileName = raf.readUTF();
				long fileSize = raf.readLong();
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFolder + "\\" + fileName));
				int byteNum = (int) (fileSize / 1000);
				int byteRest = (int) (fileSize % 1000);
				if (byteNum != 0) {
					for (int j = 0; j < byteNum; j++) {
						byte[] data = new byte[1000];
						raf.read(data);
						bos.write(data);
					}
				}
				if (byteRest != 0) {
					byte[] data = new byte[byteRest];
					raf.read(data);
					bos.write(data);
				}
				raf.seek(currentPoiter);
				bos.close();
			} else {
				raf.readUTF();
				raf.readLong();
			}
		}
		raf.close();
	}
}
