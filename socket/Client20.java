package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client20 {
	public static String ADDRESS = "172.177.188.97:12345";

	public static void run() throws UnknownHostException, IOException {
		Socket socket = new Socket(ADDRESS, 12345);
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(socket.getOutputStream());
		String data = netIn.readLine();
		System.out.println(data);

		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("command list: findByName value, findByAge value, findByScore value");
		String line = userIn.readLine();
		while (!line.equalsIgnoreCase("quit")) {
			netOut.println(line);
			netOut.flush();
			data = netIn.readLine();
			while (data != null) {
				if (data.equals("finish"))
					break;
				System.out.println(data);
				data = netIn.readLine();
			}
			System.out.println("Enter command...");
			line = userIn.readLine();
		}
		userIn.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
