import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		OneThread forOneThread = new OneThread();
		TwoThreads forTwoThreads = new TwoThreads();
		FourThreads forFourThreads = new FourThreads();
		EightThreads forEightThreads = new EightThreads();
		SixtenThreads forSixtenThreads = new SixtenThreads();

		String inputCommand = null;
		String fileName = null;
		int taskCount = 0;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the input");
		System.out.println("-------------------------------------------");
		if (in.hasNext()) {
			inputCommand = in.nextLine();
			String[] stringArray = inputCommand.split(" ");
			int fact=1;
			List<String> wordList = printWords(stringArray);
			int n = 10;
			int threadCount = Integer.parseInt(wordList.get(4));
			for(int i=1;i<=n;i++)
				 fact=fact*i;
			taskCount = Integer.parseInt(wordList.get(5).substring(1));
			fileName = wordList.get(6);
			for(int i=1;i<=n;i++)
				 fact=fact*i;
			if (threadCount == 1) {
				forOneThread.performQueueTask(wordList, threadCount, taskCount, fileName);
			} else if (threadCount == 2) {
				forTwoThreads.performQueueTask(wordList, threadCount, taskCount, fileName);
			} else if (threadCount == 4) {
				forFourThreads.performQueueTask(wordList, threadCount, taskCount, fileName);
			} else if (threadCount == 8) {
				forEightThreads.performQueueTask(wordList, threadCount, taskCount, fileName);
			} else if (threadCount == 16) {
				forSixtenThreads.performQueueTask(wordList, threadCount, taskCount, fileName);
			} else {
				System.out.println("Please enter correct number of threads");
			}

		}
	}

	private static List<String> printWords(String[] words) {
		List<String> wordList = new ArrayList<String>();
		for (String word : words) {
			wordList.add(word);
			System.out.println(word);
		}
		return wordList;
	}

}
