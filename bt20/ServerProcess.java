package bt20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerProcess extends Thread {
	private Socket socket;

	public ServerProcess(Socket socket) {
		this.socket = socket;
	}

	public void startServer() throws IOException {
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(socket.getOutputStream());
		netOut.println("Connect to server success!");
		netOut.flush();
		String line = netIn.readLine();
		while (line != null) {
			if (checkCommand(line)) {
				ArrayList<Sinhvien> students = getValues(line);
				for (Sinhvien student : students) {
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

	public ArrayList<Sinhvien> getValues(String command) {
		StringTokenizer st = new StringTokenizer(command);
		String key = st.nextToken();
		String value = st.nextToken();
		if (key.equalsIgnoreCase("findByName")) {
			String name = command.substring(command.indexOf(" ") + 1);
			return GetDataBase.findByName(name);
		} else if (key.equalsIgnoreCase("findByAge")) {
			return GetDataBase.findByAge(Integer.parseInt(value));
		} else {
			return GetDataBase.findByScore(Double.parseDouble(value));
		}
	}

	@Override
	public void run() {
		super.run();
		try {
			startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
