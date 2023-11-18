package onTapTcpIp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerProcess extends Thread {
	private Socket socket;
	private DataInputStream netIn;
	private DataOutputStream netOut;

	public ServerProcess(Socket socket) {
		this.socket = socket;
		try {
			this.setNetIn(new DataInputStream(new BufferedInputStream(this.socket.getInputStream())));
			this.setNetOut(new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Server.clientNum++;
		Server.printClient();
		try {
			while (true) {
				String dataIn = netIn.readUTF();
				if (dataIn.equals("quit")) {
					netIn.close();
					netOut.close();
					socket.close();
					Server.clientNum--;
					Server.printClient();
					break;
				} else {
					BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(dataIn));
					byte[] data = new byte[1000];
					int byteOffset = 0;
					while ((byteOffset = BIS.read(data, 0, data.length)) != -1) {
						netOut.write(data, 0, byteOffset);
						netOut.flush();
					}
					BIS.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DataInputStream getNetIn() {
		return netIn;
	}

	public void setNetIn(DataInputStream netIn) {
		this.netIn = netIn;
	}

	public DataOutputStream getNetOut() {
		return netOut;
	}

	public void setNetOut(DataOutputStream netOut) {
		this.netOut = netOut;
	}

}
