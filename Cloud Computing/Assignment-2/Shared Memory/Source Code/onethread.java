import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class onethread {
	
	static final int FILE_ROWS = 1000;

	public static void main(String[] args) {

		List<List<String>> list = new ArrayList<List<String>>();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Input file name");
		String inputFile = in.nextLine();
		System.out.println("Enter Output file name");
		String outputFile = in.nextLine();
		double startTime = System.currentTimeMillis();
		list = readfileIntoList(list, inputFile);
		double startTime2 = System.currentTimeMillis();
		MyThreadLarge runner = new MyThreadLarge(list);
		runner.start();
		try {
			runner.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		list = runner.getSortedInternal();
		list = finalmerge(list);
		double stopTime2 = System.currentTimeMillis();
		double elapsedTime2 = (stopTime2 - startTime2) * 1000;
		double throughput = 1024 / elapsedTime2;
		System.out.println("sort time : " + elapsedTime2 + "s");
		System.out.println("throughput : " + throughput);
		writefileByList(list, outputFile);
		list.clear();

		double stopTime = System.currentTimeMillis();
		double elapsedTime = stopTime - startTime;
		System.out.println("1-thread TeraMergeSort takes: "
				+ (float) elapsedTime / 1000 + " seconds");

	}

	private static List<List<String>> finalmerge(List<List<String>> list) {
		List<List<String>> finalList = new ArrayList<List<String>>();
		List<String> list1 = list.get(0);
		List<String> list2 = list.get(1);
		List<String> list3 = list.get(2);
		List<String> list4 = list.get(3);
		List<String> list5 = list.get(4);
		List<String> list6 = list.get(5);
		List<String> list7 = list.get(6);
		List<String> list8 = list.get(7);
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		int h = 0;
		int k = 0;
		int j = 0;
		int size = 0;
		for (List<String> l : list) {
			size = size + l.size();
		}
		while (j < size) {
			k = 0;
			List<String> listnew = new ArrayList<>();
			while (k < (FILE_ROWS / 8)) {
				int i = getmin(getVal(list1, a), getVal(list2, b),
						getVal(list3, c), getVal(list4, d), getVal(list5, e),
						getVal(list6, f), getVal(list7, g), getVal(list8, h));
				
				if(i == 1){
					listnew.add(list1.get(a));
					a++;
					k++;
					j++;
				}else if(i == 2){
					listnew.add(list2.get(b));
					b++;
					k++;
					j++;
				}else if(i == 3){
					listnew.add(list3.get(c));
					c++;
					k++;
					j++;
				}else if(i == 4){
					listnew.add(list4.get(d));
					d++;
					k++;
					j++;
				}else if(i == 5){
					listnew.add(list5.get(e));
					e++;
					k++;
					j++;
				}else if(i == 6){
					listnew.add(list6.get(f));
					f++;
					k++;
					j++;
				}else if(i == 7){
					listnew.add(list7.get(g));
					g++;
					k++;
					j++;
				}else if(i == 8){
					listnew.add(list8.get(h));
					h++;
					k++;
					j++;
				}

			}
			finalList.add(listnew);
		}
		return finalList;
	}

	private static int getmin(String val1, String val2, String val3,
			String val4, String val5, String val6, String val7, String val8) {
		  String[] strarr = {val1,val2,val3,val4,val5,val6,val7,val8};
		  int[] arr = {1,2,3,4,5,6,7,8};
		  int n = strarr.length;
			int k;
			for (int m = n; m >= 0; m--) {
				for (int i = 0; i < n - 1; i++) {
					k = i + 1;
					if (!isFirstElementLesserOrEqual(strarr[i] , strarr[k])) {
						swapNumbers(i, k, strarr, arr);
					}
				}
			}
		return arr[0];
	}

	private static void swapNumbers(int i, int j, String[] strarr, int[] arr) {
		String temp;
		temp = strarr[i];
		strarr[i] = strarr[j];
		strarr[j] = temp;
		int temp1;
		temp1 = arr[i];
		arr[i] = arr[j];
		arr[j] = temp1;
	}

	private static String getVal(List<String> list1, int a) {
		if (a < list1.size()) {
			return list1.get(a);
		} else {
			return null;
		}
	}
	
	private static boolean isFirstElementLesserOrEqual(String first,
			String second) {
		if (first == null) {
			return false;
		}
		if (second == null) {
			return true;
		}
		String[] strs1 = first.split("");
		String[] strs2 = second.split("");
		String a = getKey(strs1);
		String b = getKey(strs2);

		int p = a.compareTo(b);
		return ((p <= 0) ? true : false);
	}

	private static String getKey(String[] strs1) {
		String str = "";
		for (int i = 0; i < 10 ; i++) {
			str = str + strs1[i];
		}
		return str;
	}

	private static void writefileByList(List<List<String>> ts, String fileName) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			for (List<String> slist : ts) {
				for (String string : slist) {
					bw.write(string + "\r\n");
				}
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private static List<List<String>> readfileIntoList(List<List<String>> list,
			String fileName) {
		FileReader fr;
		try {
			String line;
			fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			boolean k = false;
			List<String> list1 = null;
			while ((line = br.readLine()) != null) {
				if (!k) {
					list1 = new ArrayList<String>();
					k = true;
					;
				}
				list1.add(line);
				if (list1.size() >= (FILE_ROWS / 8)) {
					k = false;
					list.add(list1);
				}
			}
			if (list1.size() > 0 && k) {
				list.add(list1);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

}
