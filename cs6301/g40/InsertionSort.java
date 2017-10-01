/**
 * Created by mukku on 8/28/2017.
 */
package cs6301.g40;

public class InsertionSort {
	/**
	 *
	 * @param arr - Unsorted Array which has to be sorted
	 *  This function implements Insertion Sort
	 */
	static <T extends Comparable<? super T>> void nSquareSort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int k = i;
			for (int j = i - 1; j > -1;	 j--) {
				if (arr[k].compareTo(arr[j]) < 0) {
					T temp = arr[k];
					arr[k] = arr[j];
					arr[j] = temp;
					k = j;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		int n = 10000000;
		Integer[] inums = new Integer[n];
		for (int i = 0;
			 i < n;
			 i++) {
			inums[i] = new Integer(i);
		}
		Shuffle.shuffle(inums);
		Timer t = new Timer();
		nSquareSort(inums);
		System.out.println(t.end());
		
	}
}
