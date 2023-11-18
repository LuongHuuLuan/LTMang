package socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server19 {
	public static int PORT = 12345;
	public static int count = 0;
	public static InetSocketAddress inet = new InetSocketAddress("127.0.0.1", PORT);

	public static void startServer() throws IOException {
		// 1 connect
//		ServerSocket server = new ServerSocket(PORT);
		ServerSocket server = new ServerSocket(inet.getPort());
		System.out.println("Waiting for client connect.....");
		System.out.println("Number of Client connected: " + count);
		while (true) {
			Socket socket = server.accept();
			Thread process = new Server19Process(socket);
			process.start();
		}
	}

	public static void notifyClientConnect() {
			System.out.println("Number of Client connected: " + count);
	}

	public static void main(String[] args) throws IOException {
		startServer();
	}
}
