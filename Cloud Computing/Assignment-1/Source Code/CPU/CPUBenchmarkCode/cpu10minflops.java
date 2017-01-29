import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class cpu10minflops {

	static Date start;
	static Date end;
	static boolean isRunThread = true;
	static double avgGflops;
	static List<Double> avgGFlopList = new ArrayList<Double>();
	static List<Double> gflopList = new ArrayList<>();

	public static void main(String[] args) {

//setting the limit for the program to run for 10 minutes
int limit = 10 * 60;
	
	System.out.println("Start");
	
	runThreadPerodicLaency(limit);
	
	while (isRunThread) {
	runthreadsByCount(4);
	/*runthreadsByCount(1);
	runthreadsByCount(1);
	runthreadsByCount(1);*/
	}
	
	
	
	System.out.println(avgGFlopList.size());
	for (Double latency : avgGFlopList) {
	System.out.println(latency);
	}
	System.out.println("Done");
	}

	private static void runThreadPerodicLaency(final int limit) {

// Thread Start
	new Thread(new Runnable() {
	@Override
	public void run() {

//Adding Delay
	addDelay(10);
	for (int i = 0; i < limit; i++) {
	addDelay(1);
	avgGFlopList.add(avgGflops);
	}
	isRunThread = false;
	}

	private void addDelay(int seconds) {
	start = new Date();
	end = new Date();
	while (end.getTime() - start.getTime() < 1000 * seconds) {
	end = new Date();
	}

	}
	}).start();
	}

	private static void runthreadsByCount(int count) {

//start barrier and finish barrier initializations of time interval
	final CountDownLatch startBarierr = new CountDownLatch(count + 1);
	final CountDownLatch finishBarierr = new CountDownLatch(count);
	for (int i = 0; i < count; i++) {
	new Thread(new Runnable() {
	@Override
	public void run() {
	startBarierr.countDown();
	try {
	float a = 1.234f, b = 5.789f;
	float sum;
	startBarierr.await();
	double startTime = System.currentTimeMillis();
	
	for (long j = 0; j <= Integer.MAX_VALUE; j++) {
	sum = a + b;
	}
	

	double timetaken = System.currentTimeMillis() - startTime;

	double totaltime = (timetaken) / 1e3;
	
	//totaltimelist.add(totaltime);

	double GFLOPS = (((Integer.MAX_VALUE) / (totaltime)) /1e9);

	gflopList.add(GFLOPS*6);
	finishBarierr.countDown();
	} catch (InterruptedException e) {
	System.out.print("Unexpected thread interruption");
	}
	}
	}).start();
	}
	startBarierr.countDown();
	try {
	startBarierr.await();
	} catch (InterruptedException e) {
	System.out.print("Unexpected thread interruption");
	}

	try {
	finishBarierr.await();
	} catch (InterruptedException e) {
	System.out.print("Unexpected thread interruption");
	}
        
	//	double timesum=0.00;
	//for(Double tt : totaltimelist){
	//timesum=timesum+tt;
	//}
	//totaltimelist.clear();
	double sum = 0.00;
	for (Double gf : gflopList) {
	sum = sum + gf;
	}
	gflopList.clear();
	
	avgGflops=(sum/count);
	
	//	System.out.println("Total Time :" +(timesum/count));
	//System.out.println("GFLOPS :" + (sum / count));
	//System.out.println(Thread.activeCount());

	}


}
