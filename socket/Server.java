package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static int PORT = 12345;
	
	public static void startServer() throws IOException {
		//1 connect
		ServerSocket server = new ServerSocket(PORT);
		Socket socket = server.accept();
		// 2.data
		//i/o
		DataInputStream netIn = new DataInputStream(socket.getInputStream());
		DataOutputStream netOut = new DataOutputStream(socket.getOutputStream());
		// protocol
		// nhan ten file dich tu client
		String dest = netIn.readUTF();
		// tao file dich
		FileOutputStream fos = new FileOutputStream(dest);
		
		int readbyte;
		byte[] data = new byte[10000];
		while((readbyte = netIn.read(data)) !=-1) {
			fos.write(data, 0, readbyte);
		}
		//close file
		fos.close();
		// 3.close
		server.close();
	}
	
	public static void main(String[] args) throws IOException {
		startServer();
	}
}
