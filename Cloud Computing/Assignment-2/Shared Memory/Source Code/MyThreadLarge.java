import java.util.ArrayList;
import java.util.List;

class MyThreadLarge extends Thread {
    private List<List<String>> internalList;
    private List<String> sortedList;
	private List<String> tempList;
	private int length;

    public List<List<String>> getSortedInternal() {
        return internalList;
    }

    public List<String> sort(List<String> list) {
		this.sortedList = list;
		this.length = list.size();
		tempList = new ArrayList<String>(length);
		for (int i = 0; i < length; i++) {
			tempList.add(null);
		}
		doMergeSort(0, length - 1);
		tempList.clear();
		return sortedList;
	}

	private void doMergeSort(int lowerIndex, int higherIndex) {

		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			doMergeSort(lowerIndex, middle);
			doMergeSort(middle + 1, higherIndex);
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex) {

		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempList.set(i, sortedList.get(i));
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while (i <= middle && j <= higherIndex) {
			if (isFirstElementLesserOrEqual(tempList.get(i),tempList.get(j))) {
				sortedList.set(k, tempList.get(i));
				i++;
			} else {
				sortedList.set(k, tempList.get(j));
				j++;
			}
			k++;
		}
		while (i <= middle) {
			sortedList.set(k, tempList.get(i));
			k++;
			i++;
		}

	}

	private static boolean isFirstElementLesserOrEqual(String first,
			String second) {
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
    
    MyThreadLarge(List<List<String>> arr) {
        internalList = arr;
    }

    public void run() {
    	for (List<String> list : internalList) {
    		sort(list);
		}
    }
}
