import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class sudp {

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

// socket creation
	DatagramSocket socket = new DatagramSocket(port++);
	byte[] buffer = new byte[65536];
	DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
	System.out.println("Server socket created. Waiting for incoming data...");

	for (int i = 0; i < BYTE; i++) {
	
	socket.receive(datagram);
	byte[] data = datagram.getData();
	String s = new String(data, 0, datagram.getLength());

	//System.out.println(datagram.getAddress().getHostAddress() + " : "
	//	+ datagram.getPort() + " - " + s);

	s = "OK : " + s;

//Create ACK
	DatagramPacket reply = new DatagramPacket(s.getBytes(),
	s.getBytes().length, datagram.getAddress(),
	datagram.getPort());
	socket.send(reply);
	}
	System.out.println("Done");
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
