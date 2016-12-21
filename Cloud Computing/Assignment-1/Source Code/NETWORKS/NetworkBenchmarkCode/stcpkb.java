import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class stcpkb {

static int port  = 7250;
	
	public static void main(String args[]) {
	System.out.println("For 1 thread");
	runServerMethod(1);
	System.out.println("For 2 threads");
	runServerMethod(2);
	}

	private static void runServerMethod(final int count) {
	final int BYTE = 1024;
	
	for (int i = 0; i < count; i++) {
	new Thread(new Runnable() {

	public void run() {

	try {
	ServerSocket socket = new ServerSocket(port++);
	Socket connection = socket.accept();
	System.out.println("Server socket created. Waiting for incoming data...");
	for (int j = 0; j < BYTE; j++) {
	BufferedReader message = new BufferedReader(new InputStreamReader(
	connection.getInputStream()));
	String receivedData = message.readLine();
	//System.out.println("Received: " + receivedData);
	DataOutputStream reply = new DataOutputStream(
	connection.getOutputStream());
	String replyData = "OK"+ '\n';
	reply.writeBytes(replyData);
	}
	System.out.println("DONE");
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	}).start();
	}

	}
}



