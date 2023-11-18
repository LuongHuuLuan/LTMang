package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Server19Process extends Thread {

	private Socket socket;

	public Server19Process(Socket socket) throws IOException {
		this.socket = socket;
	}
//	public static void main(String[] args) {
//		try {
//			InetAddress IP = InetAddress.getLocalHost();
//			System.out.println("my ip: "+IP.getHostAddress());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
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
//		 data
		Server19.count++;
		Server19.notifyClientConnect();
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(socket.getOutputStream());
		// protocol
		String data = netIn.readLine();
		while (data != null) {
			if (data.equalsIgnoreCase("quit")) {
				break;
			}
			if (checkData(data)) {
				String result = sovleData(data);
				netOut.println(result);
				netOut.flush();
			} else {
				netOut.println("systax eror");
				netOut.flush();
			}
			data = netIn.readLine();
		}
		// close
		socket.close();
		Server19.count--;
		Server19.notifyClientConnect();
	}

	public static boolean checkData(String data) {
		StringTokenizer st = new StringTokenizer(data);
		try {
			Double.parseDouble(st.nextToken());
			 
			String oparator = st.nextToken();
			if (!oparator.equals("+") && !oparator.equals("-") && !oparator.equals("*") && !oparator.equals("/")) {
				return false;
			}
			Double.parseDouble(st.nextToken());
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public String sovleData(String data) {
		StringTokenizer st = new StringTokenizer(data);
		double x = Double.parseDouble(st.nextToken());
		String oparator = st.nextToken();
		double y = Double.parseDouble(st.nextToken());
		if (oparator.equals("+")) {
			return x + " + " + y + " = " + (x + y);
		} else if (oparator.equals("-")) {
			return x + " - " + y + " = " + (x - y);
		} else if (oparator.equals("*")) {
			return x + " * " + y + " = " + (x * y);
		} else if (oparator.equals("/")) {
			if (y == 0) {
				return "division by 0 error";
			} else {
				return x + " / " + y + " = " + (x / y);
			}
		}
		return null;
	}
}
