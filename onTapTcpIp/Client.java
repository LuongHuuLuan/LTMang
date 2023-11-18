package onTapTcpIp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Client {
	public static void run() throws UnknownHostException, IOException {
		// connect
		Socket socket = new Socket(Server.ADDRESS, Server.PORT);
		// data
		DataInputStream netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		DataOutputStream netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		// protocol
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Enter your command");
			String command = userIn.readLine();
			if (command.equals("quit")) {
				netOut.writeUTF("quit");
				netOut.flush();
				break;
			} else {
				// copy source_file dest_file
				Map<String, String> checkCommand = getDataFromCommand(command);
				if (checkCommand.get("notification").equals("Success")) {
					System.out.println(checkCommand.get("notification"));
					netOut.writeUTF(checkCommand.get("sFile"));
					netOut.flush();
					// tao file can copy
					BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(checkCommand.get("dFile")));
					byte[] data = new byte[1000];
					int byteOffset = 0;
					while ((byteOffset = netIn.read(data, 0, data.length)) != -1) {
						BOS.write(data, 0, byteOffset);
						BOS.flush();
					}
					BOS.close();
					System.out.println("copy success");
				} else {
					System.out.println(checkCommand.get("notification"));
				}
			}
		}

		userIn.close();
		netIn.close();
		netOut.close();
		socket.close();
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
			run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
