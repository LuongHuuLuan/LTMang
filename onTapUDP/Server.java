package onTapUDP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Server {
	public static int PORT = 12345;
	private static DatagramSocket socket;

	public static void startServer() throws IOException {
		socket = new DatagramSocket(PORT);
		byte[] buff = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buff, buff.length);
		System.out.println("Server is starting...");
		while (true) {
			packet.setData(buff);
			packet.setLength(buff.length);
			socket.receive(packet);
			String command = new String(buff, 0, packet.getLength());
			Map<String, String> checkCommand = getDataFromCommand(command);
			if (checkCommand.get("notification").equals("Success")) {

				byte[] error = checkCommand.get("notification").getBytes();
				packet.setData(error);
				packet.setLength(error.length);
				socket.send(packet);

				File sFile = new File(checkCommand.get("sFile"));
				int sendNum = ((int) sFile.length() / 1024);
				if (((int) sFile.length() % 1024) != 0) {
					sendNum++;
				}
				String headerSend = checkCommand.get("dFile") + " " + sendNum;
				byte[] start = headerSend.getBytes();
				packet.setData(start);
				packet.setLength(start.length);
				socket.send(packet);

				BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(sFile));
				int dataRead = 0;
				while ((dataRead = BIS.read(buff, 0, buff.length)) != -1) {
					packet.setData(buff);
					packet.setLength(dataRead);
					socket.send(packet);
				}
				BIS.close();

			} else {
				byte[] error = checkCommand.get("notification").getBytes();
				packet.setData(error);
				packet.setLength(error.length);
				socket.send(packet);
			}
		}
	}

	public static Map<String, String> getDataFromCommand(String command) {
		Map<String, String> result = new HashMap<>();
		StringTokenizer stk = new StringTokenizer(command);
		int countTokens = stk.countTokens();
		if (countTokens == 3) {
			String keyWord = stk.nextToken();
			if (keyWord.equals("copy")) {
				result.put("notification", "Success");
				String sFile = stk.nextToken();
				String dFile = stk.nextToken();
				result.put("sFile", sFile);
				result.put("dFile", dFile);
			} else {
				result.put("notification", "Systax error");
			}
		} else {
			result.put("notification", "Systax error");
		}
		return result;
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
