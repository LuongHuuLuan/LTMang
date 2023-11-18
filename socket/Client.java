package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Client {
	public static String ADDRESS ="127.0.0.1";
	
	public static void run() throws UnknownHostException, IOException {
		//1 connect		
		Socket socket = new Socket(ADDRESS, Server.PORT);
		// 2.data
		//i/o
		DataInputStream netIn = new DataInputStream(socket.getInputStream());
		DataOutputStream netOut = new DataOutputStream(socket.getOutputStream());
		// protocol
		String source, dest;
		// doc 1 dong lenh tu console
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Nhap copy source destination");
		String line = userIn.readLine();
		StringTokenizer st  = new StringTokenizer(line);
		String command = st.nextToken();
		source = st.nextToken();
		dest = st.nextToken();
		// mo file nguon
		FileInputStream fis = new FileInputStream(source);
		// gui ten file dich sang cho server
		netOut.writeUTF(dest);
		netOut.flush();
		// khi nao con du lieu thi doc tu file nguon vao file dich
		int readbyte;
		byte[] data = new byte[10000];
		while((readbyte = fis.read(data)) !=-1) {
			netOut.write(data, 0, readbyte);
		}
		//close file
		fis.close();
		//3 close
		socket.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		run();
	}
}
