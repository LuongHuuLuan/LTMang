package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Server23 {
	public static int PORT = 12345;
	public static ArrayList<Student> students = new ArrayList<>();
	static {

		Student st1 = new Student("19130131", "Luong Van A", 20, 9.0);
		Student st2 = new Student("19130132", "Nguyen Van B", 20, 8.0);
		Student st3 = new Student("19130133", "Tran Van C", 20, 7.0);
		Student st4 = new Student("19130131", "Le Thi D", 20, 9.0);
		Student st5 = new Student("19130132", "Nguyen Thi E", 20, 8.0);
		Student st6 = new Student("19130133", "Tran Thi F", 20, 7.0);
		Student st7 = new Student("19130134", "Luong Van A", 20, 9.0);
		Student st8 = new Student("19130135", "Luong Van A", 20, 9.0);

		students.add(st1);
		students.add(st2);
		students.add(st3);
		students.add(st4);
		students.add(st5);
		students.add(st6);
		students.add(st7);
		students.add(st8);
	}

	public static void main(String[] args) {
		Server23 sever = new Server23();
		try {
			sever.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startServer() throws IOException {
		System.out.println("Server is starting...");
		DatagramSocket socket = new DatagramSocket(PORT);
		byte buff[] = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buff, buff.length);
		while (true) {
			packet.setData(buff);
			packet.setLength(buff.length);
			socket.receive(packet);
			String data = new String(packet.getData(), 0, packet.getLength());
			if (data.equalsIgnoreCase("quit")) {
				break;
			}
			String send = "";
			if (checkCommand(data)) {
				ArrayList<Student> students = getValues(Server23.students, data);
				send = ListToString(students);
			} else {
				send = "Systax error";
			}
			
			byte[] dataSend = send.getBytes();
			packet.setData(dataSend);
			packet.setLength(dataSend.length);
			socket.send(packet);
		}

		socket.close();
	}

	public ArrayList<Student> getValues(ArrayList<Student> students, String command) {
		StringTokenizer st = new StringTokenizer(command);
		String key = st.nextToken();
		String value = st.nextToken();
		if (key.equalsIgnoreCase("findByName")) {
			String name = command.substring(command.indexOf(" ") + 1);
			return findByName(students, name);
		} else if (key.equalsIgnoreCase("findByAge")) {
			return findByAge(students, Integer.parseInt(value));
		} else {
			return findByScore(students, Double.parseDouble(value));
		}
	}

	public String ListToString(List<Student> students) {
		String result = "";
		if (students.size() == 0) {
			return "[]";
		}
		for (Student student : students) {
			result += student + "\n";
		}
		return result;

	}

	public ArrayList<Student> findByName(ArrayList<Student> students, String name) {
		ArrayList<Student> result = new ArrayList<Student>();
		for (Student student : students) {
			if (student.getName().equals(name)) {
				result.add(student);
			}
		}
		return result;
	}

	public ArrayList<Student> findByAge(ArrayList<Student> students, int age) {
		ArrayList<Student> result = new ArrayList<Student>();
		for (Student student : students) {
			if (student.getAge() == age) {
				result.add(student);
			}
		}
		return result;
	}

	public ArrayList<Student> findByScore(ArrayList<Student> students, double score) {
		ArrayList<Student> result = new ArrayList<Student>();
		for (Student student : students) {
			if (student.getScore() == score) {
				result.add(student);
			}
		}
		return result;
	}

	public boolean checkCommand(String command) {
		StringTokenizer st = new StringTokenizer(command);
		String key = st.nextToken();
		if (key.equalsIgnoreCase("findByName") || key.equalsIgnoreCase("findByAge")
				|| key.equalsIgnoreCase("findByScore")) {
			return true;
		}
		return false;
	}
}
