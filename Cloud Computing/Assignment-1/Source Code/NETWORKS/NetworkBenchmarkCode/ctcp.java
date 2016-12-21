import java.io.*;
import java.net.*;
import java.util.concurrent.CountDownLatch;

public class ctcp {

//port  number initialization
static int port  = 7250;
	
	public static void main(String args[]) {
	try {
	System.out.println("For 1 thread");
	clientService(1);
	System.out.println("For 2 threads");
	clientService(2);
	} catch (InterruptedException e) {
	System.out.println("Unexpected thread Interruption");
	}
	}

	private static void clientService(int count) throws InterruptedException {
	final int BYTE = 1;
	final CountDownLatch startBarierr = new CountDownLatch(count + 1);
	final CountDownLatch finishBarierr = new CountDownLatch(count);

	for (int i = 0; i < count; i++) {
	new Thread(new Runnable() {

	public void run() {

	String s;
	startBarierr.countDown();
	try {

	startBarierr.await();

//Socket creation
	Socket socket = new Socket("52.36.11.89", port++);

	for (int i = 0; i < BYTE; i++) {
	
	DataOutputStream message = new DataOutputStream(

//connection setup
	socket.getOutputStream());
	s = "c";
	message.writeBytes(s + '\n');

// Receive Message	
	BufferedReader reply = new BufferedReader(new InputStreamReader(
	socket.getInputStream()));
	String replyMsg = reply.readLine();
	}
	socket.close();
	finishBarierr.countDown();
	System.out.println("MESSAGE SENT : OK");
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	}
	}).start();
	}
startBarierr.countDown();
	startBarierr.await();
	double starttime = System.currentTimeMillis();
	finishBarierr.await();
	double endtime = System.currentTimeMillis();
	
	double totaltime = (endtime - starttime)/1000;
	double rtt = totaltime/(count * BYTE);
	System.out.println("RTT for " +count +" threads in sec: "+ rtt);

//calculation of latency and throughput
	double latency = rtt*1000;
	
	double throughputbps=1/rtt;
	
	double throughput = ((8*throughputbps)/(1024*1024));
	System.out.println("latency for " +count +" threads in ms: "+ latency);
	System.out.println("throughput for " +count +" threads in Mb/s: "+ throughput);

	}
}
