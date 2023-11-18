package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client23 {
	private InetAddress inet;

	public void run() throws IOException {
		System.out.println("Enter your command...");
		inet = InetAddress.getByName("192.168.43.23");
		DatagramSocket socket = new DatagramSocket();
		byte[] buff = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buff, buff.length, inet, 12345);

		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String line = userIn.readLine();
			byte[] data = line.getBytes();
			packet.setData(data);
			packet.setLength(data.length);
			socket.send(packet);
			if (line.equalsIgnoreCase("quit")) {
				break;
			}

			packet.setData(buff);
			packet.setLength(buff.length);
			socket.receive(packet);
			String recevie = new String(packet.getData(), 0, packet.getLength());
			System.out.println(recevie);
		}

		socket.close();
	}

	public static void main(String[] args) {
		Client23 client = new Client23();
		try {
			client.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
