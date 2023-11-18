package IOStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IOStudent {

	public static void main(String[] args) {
		String sFile = "E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTmang\\io\\fileIOStudent.txt";

		Student st1 = new Student("Luong Van A", "27/07/2001", "19130131", 9.0);
		Student st2 = new Student("Nguyen Van B", "02/07/2001", "19130132", 8.0);
		Student st3 = new Student("Tran Van C", "10/05/2001", "19130133", 7.0);
		Student st4 = new Student("Le Thi D", "10/03/2001", "19130131", 9.0);
		Student st5 = new Student("Nguyen Thi E", "02/08/2001", "19130132", 8.0);
		Student st6 = new Student("Tran Thi F", "09/11/2001", "19130133", 7.0);

		List<Student> studentList = new ArrayList<>();
		studentList.add(st1);
		studentList.add(st2);
		studentList.add(st3);
		studentList.add(st4);
		studentList.add(st5);
		studentList.add(st6);

		try {
			writeStudent(studentList, sFile);
			editStudent(sFile, "19130131", "name", "Luong Van C");
			printStudentList(readStudent(sFile));
//			export("E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTmang\\io\\student.txt", studentList, "utf-8", "\t");
//			ArrayList<Student> students = imports("E:\\TaiLieuHocTap\\Nam3\\Ki1\\LTmang\\io\\student.xlsx", "\t",
//					"utf-8");
//			printStudentList(students);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeStudent(List<Student> listData, String pathFile) throws Exception {
		RandomAccessFile raf = new RandomAccessFile(pathFile, "rw");
		long location[] = new long[listData.size()];
		raf.writeInt(listData.size());
		for (int i = 0; i < listData.size(); i++) {
			Student student = listData.get(i);
			raf.writeUTF(student.getMssv());
			location[i] = raf.getFilePointer();
			raf.writeLong(0);
		}
		for (int i = 0; i < listData.size(); i++) {
			Student student = listData.get(i);
			long currentPoiter = raf.getFilePointer();
			raf.seek(location[i]);
			raf.writeLong(currentPoiter);
			raf.seek(currentPoiter);
			raf.writeUTF(student.getName());
			raf.writeUTF(student.getBirthDay());
			raf.writeUTF(student.getMssv());
			raf.writeDouble(student.getAverageScore());
		}
		raf.close();
	}

	public static List<Student> readStudent(String pathFile) throws Exception {
		List<Student> result = new ArrayList<Student>();
		RandomAccessFile raf = new RandomAccessFile(pathFile, "r");
		int size = raf.readInt();
		for(int i = 0; i < size; i++) {
			raf.readUTF();
			long fileLocation = raf.readLong();
	 		long currentPoiter = raf.getFilePointer();
	 		raf.seek(fileLocation);
			String name = raf.readUTF();
			String birthDay = raf.readUTF();
			String mssv = raf.readUTF();
			double score = raf.readDouble();
			Student student = new Student(name, birthDay, mssv, score);
			result.add(student);
			raf.seek(currentPoiter);
		}
		raf.close();
		return result;
	}
	public static void editStudent(String pathFile,String mssv , String attribute, String values) throws Exception {
		RandomAccessFile raf = new RandomAccessFile(pathFile, "rw");
		int size = raf.readInt();
		for(int i = 0; i < size; i++) {
			String mssvInFile = raf.readUTF();
			System.out.println(mssvInFile);
			if(mssvInFile.equals(mssv)) {
				long fileLocation = raf.readLong();
				if(attribute.equals("name")) {
					raf.seek(fileLocation);
					raf.writeUTF(values);
					break;
				}
			} else {
				raf.readLong();
			}
		}
		raf.close();
	}	

	public static void printStudentList(List<Student> dataList) {
		for (Student student : dataList) {
			System.out.println(student);
		}
	}

	public static void export(String filePath, List<Student> listData, String charet, String demilited)
			throws Exception {
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), charet));
		for (Student student : listData) {
			String out = student.getName() + demilited + student.getBirthDay() + demilited + student.getMssv()
					+ demilited + student.getAverageScore();
			pw.println(out);
		}
		pw.flush();
		pw.close();
	}

	public static ArrayList<Student> imports(String filePath, String demilited, String charset) throws IOException {
		ArrayList<Student> result = new ArrayList<>();
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charset));
		String in = buf.readLine();
		while (in != null) {
			StringTokenizer token = new StringTokenizer(in, demilited);
			String name = token.nextToken();
			String birthDay = token.nextToken();
			String mssv = token.nextToken();
			double score = Double.parseDouble(token.nextToken());
			Student s = new Student(name, birthDay, mssv, score);
			result.add(s);
			in = buf.readLine();
		}
		buf.close();
		return result;

	}
}
