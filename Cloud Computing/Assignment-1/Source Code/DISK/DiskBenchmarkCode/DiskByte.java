import java.io.BufferedReader;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class DiskByte {

public static void main(String args[]) throws InterruptedException{

	//Taking input file name from the user
	System.out.println("Enter the filename for Sequential Access:");
	Scanner in=new Scanner(System.in);
	String filenamesequence=in.nextLine();
	
	System.out.println("Enter the filename for Random Access:");
	String filenamerandom=in.nextLine();
	
	
	System.out.println("-------OPTIONS------");
	System.out.println("1.Using Single thread");
	System.out.println("2.Using two threads");
	System.out.println("3.Using four threads");
	
	System.out.println("Enter your choice:");
	Scanner on=new Scanner(System.in);
	int ch=on.nextInt();
	
	switch(ch)
	{
	case 1:System.out.println("---------------------For 1 Thread----------------------");
	          runthreadsByCount(1,filenamesequence,filenamerandom);
	          break;
	
	   case 2:System.out.println("---------------------For 2 Threads----------------------");
               runthreadsByCount(2,filenamesequence,filenamerandom);
	          break;
	          
	   case 3:System.out.println("---------------------For 4 Threads----------------------");
	      runthreadsByCount(4,filenamesequence,filenamerandom);
	          break;
	          
	   default: System.out.println("Enter valid input");
	             break;
	
	}	
	
	}

	private static void runthreadsByCount(int count, String name, String nam) throws InterruptedException{
	
	final int BYTE=1;
	final String filenamesequence=name;
	final String filenamerandom=nam;

       // start and finish barrier initialization of time interval
	final CountDownLatch startBarierr1 = new CountDownLatch(count + 1);
	final CountDownLatch finishBarierr1 = new CountDownLatch(count);
	final CountDownLatch startBarierr2 = new CountDownLatch(count + 1);
	final CountDownLatch finishBarierr2 = new CountDownLatch(count);
	final CountDownLatch startBarierr3 = new CountDownLatch(count + 1);
	final CountDownLatch finishBarierr3 = new CountDownLatch(count);
	final CountDownLatch startBarierr4 = new CountDownLatch(count + 1);
	final CountDownLatch finishBarierr4 = new CountDownLatch(count);
	
	for(int i=0;i<count;i++)
	{
	new Thread(new Runnable(){
	
	public void run(){
	
	try {
	
// New file and buffered reader to write inside a file
	FileWriter fw=new FileWriter(filenamesequence);
	BufferedWriter bw=new BufferedWriter(fw);
	
	char ch;
	int r;
	startBarierr1.countDown();

// timer for sequential access write begins
	startBarierr1.await();
	for(int i=0;i<BYTE;i++)
	{

// sequential access write operation
	bw.write("s");
	}

// timer for sequential write ends
	finishBarierr1.countDown();
	bw.close();
	
	FileReader fr=new FileReader(filenamesequence);
	BufferedReader br=new BufferedReader(fr);
	
	startBarierr2.countDown();
	startBarierr2.await();
	
	for(int i=0;i<BYTE;i++)
	{
	while((r=br.read())!=-1)
	{
	ch=(char)r;
	}
	}
	
	finishBarierr2.countDown();
	br.close();
	
	File file = new File(filenamerandom);
	RandomAccessFile raf = new RandomAccessFile(file, "rw");
	startBarierr3.countDown();
	startBarierr3.await();

//Random access write
for(int i=0;i<BYTE;i++)
	{	Random rand = new Random();

// position chosen to perform write operation
	int pos = rand.nextInt(BYTE) + 0;
	FileChannel fc = raf.getChannel();
	fc.position(pos);

// writing at random position
	raf.write(("k").getBytes());
	}
	
	/*for(int i=0;i<BYTE;i++)
	{	Random rand = new Random();
	int ans = rand.nextInt(122-65+1) + 65;
	raf.write((char)ans);
	}*/
	
	finishBarierr3.countDown();
	raf.close();
	
	raf = new RandomAccessFile(file, "rw");
	
	startBarierr4.countDown();
	startBarierr4.await();
	
// Random access read operation
	for(int i=0;i<BYTE;i++)
	{
	Random rand = new Random();
	int pos = rand.nextInt(BYTE) + 0;
	FileChannel fc = raf.getChannel();
	fc.position(pos);
	ch = (char)raf.read();
	}
	
	finishBarierr4.countDown();
	raf.close();
	}catch(FileNotFoundException e){
	e.printStackTrace();
	}catch(IOException e){
	e.printStackTrace();
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	}
	}).start();
	}
	
	startBarierr1.countDown();
	startBarierr1.await();
	double starttime1=System.nanoTime();
	finishBarierr1.await();	
	double endtime1=System.nanoTime();
	
	double time1=(endtime1-starttime1);
	double totaltime1=(time1/1000000000);
	
// calculation of latency and throughput
	double latency1= ((totaltime1/BYTE)*1000);
	
	double throughput1=((BYTE)/(1024*1024*totaltime1));
	System.out.println("---------------------------------------------------------------------");
	System.out.println("Sequential Access Write Time:" +totaltime1);
	System.out.println("Latency for Sequential Access Write Time :" +latency1+" ms");
	System.out.println("Throughput for Sequential Access Write Time :" +throughput1+" MB/sec");
	
	startBarierr2.countDown();
	startBarierr2.await();
	double starttime2=System.nanoTime();
	finishBarierr2.await();
	double endtime2=System.nanoTime();
	
	double time2=(endtime2-starttime2);
	double totaltime2=(time2/1000000000);
	
	double latency2= (((totaltime2)/(BYTE))*1000);
	double throughput2=((BYTE)/(1024*1024*totaltime2));
	
	System.out.println("-------------------------------------------------------------------------");
	System.out.println("Sequential Access Read Time:" +totaltime2);
	System.out.println("Latency for Sequential Access Read Time :" +latency2+" ms");
	System.out.println("Throughput for Sequential Access Read Time :" +throughput2+" MB/sec");
	
	startBarierr3.countDown();
	startBarierr3.await();
	double starttime3=System.nanoTime();
	finishBarierr3.await();
	double endtime3=System.nanoTime();
	
	double time3=(endtime3-starttime3);
	double totaltime3=(time3/1000000000);
	
	double latency3= (((totaltime3)/(BYTE*1000))*1000);
	double throughput3=((BYTE)/(1024*1024*totaltime3));
	
	System.out.println("-----------------------------------------------------------------------------");
	System.out.println("Random Access Write Time:" +totaltime3);
	System.out.println("Latency for Random Access Write Time :" +latency3+" ms");
	System.out.println("Throughput for Random Access Write Time :" +throughput3+" MB/sec");
	
	startBarierr4.countDown();
	startBarierr4.await();
	double starttime4=System.nanoTime();
	finishBarierr4.await();
	double endtime4=System.nanoTime();
	
	double time4=(endtime4-starttime4);
	double totaltime4=(time4/1000000000);
	
	double latency4= (((totaltime4)/(BYTE))*1000);
	double throughput4=((BYTE)/(1024*1024*totaltime4));
	
	System.out.println("------------------------------------------------------------------------------");
	System.out.println("Random Access Read Time:" +totaltime4);
	System.out.println("Latency for Random Access Read Time :" +latency4+" ms");
	System.out.println("Throughput for Random  Access Read Time :" +throughput4+" MB/sec");
	
	System.out.println("-------------------------------------------------------------------------------");
	
	
	
	}
}
