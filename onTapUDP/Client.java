package onTapUDP;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	public static void run() throws IOException {
		DatagramSocket socket = new DatagramSocket();
		byte[] buff = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buff, buff.length, InetAddress.getLocalHost(), 12345);

		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your command");
		while (true) {
			String line = userIn.readLine();
			if (line.equals("quit")) {
				break;
			}
			byte[] data = line.getBytes();
			packet.setData(data);
			packet.setLength(data.length);
			socket.send(packet);

			packet.setData(buff);
			packet.setLength(buff.length);
			socket.receive(packet);
			String NotifyReceive = new String(buff, 0, packet.getLength());

			if (NotifyReceive.equals("Success")) {
				packet.setData(buff);
				packet.setLength(buff.length);
				socket.receive(packet);
				String dataReceive = new String(buff, 0, packet.getLength());
				int receiveTime = Integer.parseInt(dataReceive.split(" ")[1]);
//				copy E:\GocHocTap\code\LTMang\test\BT1\Diagram-datVe.jpg E:\GocHocTap\code\LTMang\test\BT1(copy)\Diagram-datVe.jpg
				BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(dataReceive.split(" ")[0]));

				while (true) {
					if (receiveTime == 0) {
						break;
					} else {
						packet.setData(buff);
						packet.setLength(buff.length);
						socket.receive(packet);

						BOS.write(buff, 0, packet.getLength());
						BOS.flush();
						receiveTime--;
					}
				}
				BOS.close();

			}
			System.out.println(NotifyReceive);

		}
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
