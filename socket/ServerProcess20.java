package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerProcess20 extends Thread {
	private Socket socket;

	public ServerProcess20(Socket socket) throws IOException {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startServer() throws IOException {
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(socket.getOutputStream());
		netOut.println("Connect to server success!");
		netOut.flush();
		String line = netIn.readLine();
		while (line != null) {
			if (checkCommand(line)) {
				ArrayList<Student> students = getValues(Server20.students, line);
				for (Student student : students) {
					netOut.println(student);
				}
			} else {
				netOut.println("Systax error");
			}
			netOut.println("finish");
			netOut.flush();
			line = netIn.readLine();
		}
		socket.close();
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
}
