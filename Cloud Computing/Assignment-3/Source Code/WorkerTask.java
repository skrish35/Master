import java.util.concurrent.ConcurrentLinkedQueue;

class WorkerTask implements Runnable {
	private static final String STRING2 = " ";
	private static final String STRING1 = ":";
	ConcurrentLinkedQueue<String> queueForTask;
	ConcurrentLinkedQueue<String> resultntqueue;

	/* constructor */
	WorkerTask(ConcurrentLinkedQueue<String> taskqueue,
			ConcurrentLinkedQueue<String> resultqueue) {
		this.queueForTask = taskqueue;
		this.resultntqueue = resultqueue;
	}

	public static void WorkerStringSplit(String str) {
		String[] tokens = str.split(STRING2);/* splitting the sting with space */
		for (String s : tokens) {
			int len = s.length();/* getting the length of all strings */
		}
		tokens = str.split("\\s+");/* Splitting the string for the second time */
	}

	public void buildCommand(String filename) {
        StringBuffer myInitials = new StringBuffer();
        int length = filename.length();

        for (int i = 0; i < length; i++) {
            if (Character.isUpperCase(filename.charAt(i))) {
                myInitials.append(filename.charAt(i));
            }
        }
	}

	public void run() {
		/* Worker Started */;
		String task = null;
		for (; (task = queueForTask.poll()) != null;) {
			int index = task.indexOf(STRING1);
			String sub = task.substring(6, index);
			int sleep = Integer.parseInt(sub);/* sleep time */
			try {
				Thread.currentThread();
				Thread.sleep(sleep);/* thread sleep */
			} catch (InterruptedException e) {
				index = task.indexOf(STRING1);
				int length = task.length();
				resultntqueue.add(1 + task.substring(index, length));
				System.out.println(e.getMessage());
			}
			resultntqueue.add(0 + task);/* add to result queue* */
			System.out.println(0 + task);
		}
	}

	public String workerMethodReadFile(String inputString) {

		if (inputString.length() > 1) {
			char firstChar = inputString.charAt(0);// In each method we just
													// take the first char and
													// pass the remaining
			// substring to the next method call, during the return calls the
			// characters are appended in reverse order.
			String reverseString = workerMethodReadFile(inputString
					.substring(1));// Recursive call to same method.
			reverseString += firstChar;// during the return calls the characters
										// are appended in reverse order.
			return reverseString;
		} else if (inputString.length() == 1) {
			return inputString;
		}
		return "";
	}
}
