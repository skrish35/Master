import java.util.*;
import java.util.concurrent.CountDownLatch;

public class CPUGflops {
	
	
	static List<Double> gflopList = new ArrayList<>();
    static List<Double> totaltimelist=new ArrayList<>();
    
	public static void main(String[] args) throws InterruptedException {
	
//  Displaying Options
	System.out.println("1.Using Single thread");
	System.out.println("2.Using two threads");
	System.out.println("3.Using four threads");
	
	System.out.println("Enter your choice:");
	Scanner in=new Scanner(System.in);
	int ch=in.nextInt();
	
	switch(ch)
	{
	case 1:System.out.println("---------------------For 1 Thread----------------------");
	          runthreadsByCount(1);
	          break;
	
	   case 2:System.out.println("---------------------For 2 Threads----------------------");
               runthreadsByCount(2);
	          break;
	          
	   case 3:System.out.println("---------------------For 4 Threads----------------------");
	      runthreadsByCount(4);
	          break;
	          
	   default: System.out.println("Enter valid input");
	             break;
	
	}	

	}

	private static void runthreadsByCount(int count) {
	
//start and finish barrier initialization of time interval
final CountDownLatch startBarierr = new CountDownLatch(count + 1);
	final CountDownLatch finishBarierr = new CountDownLatch(count);
	for (int i = 0; i < count; i++) {

// Thread Starting
	new Thread(new Runnable() {
	@Override
	public void run() {
	startBarierr.countDown();
	try {
	float a = 1.234f, b = 5.789f;
	float sum;
	startBarierr.await();
	double startTime = System.currentTimeMillis();

// performing floating point operation
	
	for (long j = 0; j <= Integer.MAX_VALUE; j++) {
	sum = a + b;
	}
	double timetaken = System.currentTimeMillis() - startTime;

	double totaltime = (timetaken) / 1e3;
	
	totaltimelist.add(totaltime);

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
        
	double timesum=0.00;
	for(Double tt : totaltimelist){
	timesum=timesum+tt;
	}
	
	totaltimelist.clear();
	double sum = 0.00;
	for (Double gf : gflopList) {
	sum = sum + gf;
	}
	gflopList.clear();
	
	System.out.println("Total Time :" +(timesum/count));
	System.out.println("GFLOPS :" + (sum / count));
	//System.out.println(Thread.activeCount());

	}


}


