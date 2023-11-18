package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client19 {
//	public static String ADDRESS = "127.0.0.1";
	public static String ADDRESS = "127.0.0.1";

	public static void run() throws UnknownHostException, IOException {
		// 1 connect
		Socket socket = new Socket(ADDRESS, Server19.PORT);
		// data
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(socket.getOutputStream());
		// protocol

		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Nhap phep tinh: ");
		String data = userIn.readLine();
		while (!data.equalsIgnoreCase("quit")) {
			netOut.println(data);
			netOut.flush();
			String result = netIn.readLine();
			System.out.println(result);
			data = userIn.readLine();
		}
		userIn.close();
		// close
		socket.close();
	}

	public static void main(String[] args) {
		try {
			run();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
