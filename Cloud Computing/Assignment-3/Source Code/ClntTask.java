import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

class ClntTask implements Runnable {
	private static final String STRING2 = ",";
	private static final String STRING_COLON = ":";
	ConcurrentLinkedQueue<String> queueForTask;
	ConcurrentLinkedQueue<String> resultanQqueue;
	int numberOfTask;
	public boolean clientFlag;
	String fileName = null;

	/* constructor */
	ClntTask(ConcurrentLinkedQueue<String> taskqueue,
			ConcurrentLinkedQueue<String> resultqueue, int taskCount,
			Boolean flag, String fileName) {
		this.queueForTask = taskqueue;
		this.resultanQqueue = resultqueue;
		this.clientFlag = flag;
		this.numberOfTask = taskCount;
		this.fileName = fileName;
	}

	/* run the thread */
	public void run() {
		System.out.println("Client started inserting into Task Queue "
				+ clientFlag);
		String inputString = fileName;
		int size = 0;
		if (clientFlag == true) {
			char firstChar = inputString.charAt(0);// In each method we just
													// take the first char and
													// pass the remaining
			// substring to the next method call, during the return calls the
			// characters are appended in reverse order.
			size = insertIntoQueue(fileName, numberOfTask);/*
															 * insertion to
															 * queue
															 */
		} else if (clientFlag == false) {
			String reverseString = (inputString.substring(1));// Recursive call
																// to same
																// method.
			remove(size, resultanQqueue);/* remove from queue */
			System.out.println("Client started picking from result queue");
		}
	}

	/* insertion to queue */
	public int insertIntoQueue(String fileName, int taskCount) {

		try {
			File inputfile = new File(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(inputfile)));/* get reader */
			String string = null;
			int taskId = 0;
			/* read file line by line */
			for (; (string = br.readLine()) != null;) {
				int i = 0;
				while (i < taskCount) {
					taskId++;
					addToTaskQueue(taskId, string);/* add task to queue */
					i++;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		int size = queueForTask.size();/* get size of queue */
		return size;
	}

	/* adding task to queue */
	private void addToTaskQueue(int taskId, String line) {
		queueForTask.add(line + STRING_COLON + taskId);
		System.out.println(line + STRING_COLON + taskId);
	}

	public void StringSplit(String str1) {
		String str2 = STRING2;
		String[] splittedStrs = str1.split(str2);
		for (String string : splittedStrs) { // this is new type of for loop,
												// know this
			System.out.println(string); // know this
		}
		int length = 0;// initiliz the length
		String[] strings2 = str1.split(str2, 3); // know the difference between
													// above and this
		for (String string : strings2) {
			length = length + string.length();// find length of each and sum up
		}
	}

	/* removal of threads */
	public void remove(int tSize, ConcurrentLinkedQueue<String> resultqueue) {
		String result;
		for (;(result = resultqueue.poll()) != null;) {
			System.out.println(result);
		}
		for (String string : resultqueue) {
			int len = string.length();
		}
		int size = resultqueue.size();
	}

	public String clientTask(String inputString) {
		char firstChar = inputString.charAt(0);// In each method we just take
												// the first char and pass the
												// remaining
		// substring to the next method call, during the return calls the
		// characters are appended in reverse order.
		String reverseString = clientTask(inputString.substring(1));// Recursive
																	// call to
																	// same
																	// method.
		reverseString += firstChar;// during the return calls the characters are
									// appended in reverse order.
		return reverseString;
	}
}