import java.io.*;
import java.net.*;
import java.util.concurrent.CountDownLatch;

public class stcp {

static int port  = 7250; // port number initialization
	
	public static void main(String args[]) {
	System.out.println("For 1 thread");
	runServerMethod(1);
	System.out.println("For 2 threads");
	runServerMethod(2);
	}

	private static void runServerMethod(final int count) {
	final int BYTE = 1;
	
	for (int i = 0; i < count; i++) {
	new Thread(new Runnable() {

	public void run() {

	try {

//socket creation
	ServerSocket socket = new ServerSocket(port++);
	Socket connection = socket.accept();
	System.out.println("Server socket created. Waiting for incoming data...");
	for (int j = 0; j < BYTE; j++) {
	BufferedReader message = new BufferedReader(new InputStreamReader(
	connection.getInputStream()));
	String receivedData = message.readLine();
	//System.out.println("Received: " + receivedData);

// New message creation
	DataOutputStream reply = new DataOutputStream(
	connection.getOutputStream());

//send message
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


