package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClientPhung {

    static String host;
    static int port;

    public ClientPhung(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void execute() throws IOException {
        //Phần bổ sung
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào host: ");
        host = sc.nextLine();

        System.out.print("Nhập vào port: ");
        port = sc.nextInt();

        Socket client = new Socket(host, port);
        ReadWriteClient readwrite = new ReadWriteClient(client);
        readwrite.start();

    }

    public static void main(String[] args) throws IOException {
    	ClientPhung client = new ClientPhung(host, port);
        client.execute();

    }
}

class ReadWriteClient extends Thread {

    private Socket client;

    public ReadWriteClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            long t0, t1, serverTime, finalTime;
            out.println(t0 = System.currentTimeMillis());
            serverTime = Long.parseLong(in.readLine());
            t1 = System.currentTimeMillis();
            finalTime = serverTime + (t1 - t0) / 2;
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            System.out.println("Client time:" + formatter.format(new Date(t1)));
            System.out.println("Server Time: " + formatter.format(new Date(serverTime)));
            String STM = formatter.format(new Date(finalTime));
            /////// 
            System.out.println();
            System.out.println(STM);
            System.out.println(STM);
            try {
                Runtime.getRuntime().exec("cmd /C time " + STM); // hh:mm:ss
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Client Time after reset: " + formatter.format(new Date(finalTime)));
            //  out.println("EXit");
            in.readLine();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ClientPhung.host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + ClientPhung.host);
            System.exit(1);
        }
    }

}
