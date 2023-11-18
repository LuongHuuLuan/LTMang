package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server20 {
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

	public static void startServer() throws IOException {
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Server is running...");
		while (true) {
			Socket socket = server.accept();
			Thread process = new ServerProcess20(socket);
			process.start();
		}
	}
	public void loadStudent() {
		
	}
	public static void main(String[] args) {
		try {
			startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
