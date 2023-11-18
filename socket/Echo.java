package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Echo {
	/*
	 * client - server 
	 * sau khi kết nối thành công S -> C: hello 
	 * C -> S: String 
	 * S -> C: echo String 
	 * C -> S:quit -> S close
	 */
	public static void main(String[] args) throws IOException {
		// Step1: connection
		ServerSocket serverSocket = new ServerSocket(7);
		Socket socket = serverSocket.accept();
		// Step2: request/response
		//2a:prepare IO
		BufferedReader netin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netout = new PrintWriter(socket.getOutputStream(), true);
		//2b:Protocol
		netout.println("Hello");
		String line;
		while(true) {
			line = netin.readLine();
			if(line.equalsIgnoreCase("quit")) {
				break;
			}
			netout.println("Echo: "+line);
		}
		//step3
		socket.close();
	}
}
