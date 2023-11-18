package bt20;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static int PORT = 22222;
	
	public static void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("server is running...");
		while(true) {
			Socket socket = serverSocket.accept();
			ServerProcess process = new ServerProcess(socket);
			process.start();
		}
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
