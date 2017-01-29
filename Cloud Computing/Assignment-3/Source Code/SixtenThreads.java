import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SixtenThreads {

	public boolean clientFlag = true;

	public void performQueueTask(List<String> wordList, int threadCount,
			int taskCount, String fileName) {
		ConcurrentLinkedQueue<String> queueForTask = new ConcurrentLinkedQueue<String>();
		ConcurrentLinkedQueue<String> resultntqueue = new ConcurrentLinkedQueue<String>();

		try {

			if (threadCount == 16) {
				double startTime = System.nanoTime();
				/* Timer Started */;
				ClntTask c = new ClntTask(queueForTask, resultntqueue, taskCount,
						clientFlag, fileName);
				Thread client = new Thread(c);/* client creation */
				client.start();/* client start */
				client.join();/* client end */
				List<Thread> worker = new ArrayList<Thread>();
				int i = 0;
				while (i < threadCount) {
					WorkerTask w = new WorkerTask(queueForTask, resultntqueue);
					Thread t = new Thread(w);
					worker.add(t);/* create workers */
					i++;
				}
				i = 0;
				while (i < threadCount) {
					(worker.get(i)).start();/* start worker */
					i++;
				}
				i = 0;
				while (i < threadCount) {
					(worker.get(i)).join();/* wait for end of all workers */
					i++;
				}
				clientFlag = false;
				/* create thread for removl */
				Thread clientRemove = new Thread(new ClntTask(queueForTask,
						resultntqueue, taskCount, clientFlag, fileName));
				clientRemove.start();/* start remove task */
				clientRemove.join();/* wait for remove task complete */
				double endTime = System.nanoTime();
				caluculateAndDisplayTimeTaken(startTime, endTime);
			} else {
				System.out.println("Please enter correct number of threads");
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	private String fullPath;

	public String extension(String extensionSeparator, String pathSeparator) {
		int dot = fullPath.lastIndexOf(extensionSeparator);/* get last index */
		String a = fullPath.substring(dot + 1);/* get substring */
		int sep = fullPath.lastIndexOf(pathSeparator);/* get last index */
		return fullPath.substring(sep + 1, dot);
	}

	protected static String removeJunk(String string) {
		int len = string.length();
		StringBuilder dest = new StringBuilder(len);
		char charact;
		for (int i = (len - 1); i >= 0; i--) {
			charact = string.charAt(i);
			if (Character.isLetter(charact)) {
				dest.append(charact);
			}
		}
		return dest.toString();
	}

	public Map<Integer, Integer> combineMethod(Map<Integer, Integer> map) {
		Map<Integer, Integer> combineMap = new HashMap<Integer, Integer>();
		int fileSum = map.get(combineMap);/* get file sum */
		int fileCount = map.get(combineMap);/* get file map count */
		float fileAverage = (float) fileSum / (float) fileCount;/* fi nd average */
		return combineMap; /* return average class */
	}

	private void caluculateAndDisplayTimeTaken(double startTime, double endTime) {
		/* caluculate and display time taken */
		double elapsedTime = endTime - startTime;
		double timeSeconds = elapsedTime / 1000000000.0;
		System.out.println("Worker execution time : " + timeSeconds);
	}

}
