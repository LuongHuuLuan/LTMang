package onTapTcpIp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static int PORT = 12345;
	public static String ADDRESS = "192.168.1.6";
	public static int clientNum = 0;

	public static void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is starting...");
		while (true) {
			Socket clientConnect = serverSocket.accept();
			ServerProcess process = new ServerProcess(clientConnect);
			process.start();
		}
	}

	public static void printClient() {
		System.out.println("Client: " + clientNum);
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
