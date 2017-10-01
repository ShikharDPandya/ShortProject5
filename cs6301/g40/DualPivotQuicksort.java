package cs6301.g40;

/*
 * Group members:
Mukesh Kumar(mxk170430)
Shikhar Pandya (sdp170030)
Arijeet Roy (axr165030)*/
import java.util.Scanner;

import cs6301.g00.Shuffle;
import cs6301.g00.Timer;

public class DualPivotQuicksort {

	public DualPivotQuicksort() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the maximum number of elements:(eg. 1000 or 5000000). "
				+ "Values will be shuffled and start from 0 to maxNum - 1");
		int maxNum = scanner.nextInt();
		int[] inums = new int[maxNum];
		for (int i = 0; i < maxNum; i++) {
			inums[i] = i;
		}
		Shuffle.shuffle(inums);
		// start timer
		Timer timer = new Timer();
		System.out.println("---Dual pivot sort result---");
		dualPivotSort(inums, 0, inums.length - 1);
		// for (int i = 0; i < inums.length; i++) {
		// System.out.print(inums[i] + " ");
		// }
		System.out.println(timer.end());
		System.out.println();

		Shuffle.shuffle(inums);
		// start timer
		timer = new Timer();
		System.out.println("---Quick sort result---");
		qSort(inums, 0, inums.length - 1);
		// for (int i = 0; i < inums.length; i++) {
		// System.out.print(inums[i] + " ");
		// }
		System.out.println(timer.end());
		System.out.println();
		scanner.close();
	}

	private static void qSort(int[] inums, int start, int end) {
		// TODO Auto-generated method stub
		int low = start;
		int high = end;
		// select middle element as pivot
		int pivot = inums[low + (high - low) / 2];
		while (low <= high) {
			while (inums[low] < pivot) {
				low++;
			}

			while (inums[high] > pivot) {
				high--;
			}

			// swap the elements to correct position
			if (low <= high) {
				swap(inums, low, high);
				low++;
				high--;
			}
		}

		// recursion
		if (start < high) {
			qSort(inums, start, high);
		}

		if (low < end) {
			qSort(inums, low, end);
		}

	}

	// dual pivot sort algorithm
	private static void dualPivotSort(int[] inums, int start, int end) {
		// TODO Auto-generated method stub
		if (start < end) {
			// swap in case the left pivot is greater than the right
			if (inums[start] > inums[end]) {
				swap(inums, start, end);
			}
			// pick pivots
			int p = inums[start];
			int q = inums[end];
			// pick partitions of pivots
			int l = start + 1;
			int k = l;
			int g = end - 1;

			// put elements in the correct place in accordance with the pivots.
			while (k <= g) {
				if (inums[k] < p) {
					swap(inums, k, l);
					l++;
				} else if (inums[k] >= q) {
					while (inums[g] > q && k < g) {
						g--;
					}
					swap(inums, k, g);
					g--;
					if (inums[k] < p) {
						swap(inums, k, l);
						l++;
					}
				}
				k++;
			}
			g++;
			l--;

			// put pivots to their correct place and recursively sort the left
			// and right halves
			swap(inums, start, l);
			swap(inums, end, g);
			dualPivotSort(inums, start, l - 1);
			dualPivotSort(inums, l + 1, g - 1);
			dualPivotSort(inums, g + 1, end);

		}
	}

	// swap two indices of array
	private static void swap(int[] inums, int i, int j) {
		int temp = inums[i];
		inums[i] = inums[j];
		inums[j] = temp;
	}
}
