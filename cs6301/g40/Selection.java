package cs6301.g40;

/**
 * Created by mukku on 9/30/2017.
 */
/*
 * Group members:
Mukesh Kumar(mxk170430)
Shikhar Pandya (sdp170030)
Arijeet Roy (axr165030)*/
import java.util.Random;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Scanner;

public class Selection {
	/**
	 *
	 * @param arr- array inputted by user
	 * @param k- number of biggest elements to find in array
	 * @return-  List of k biggest elements from arr[] array
	 *
	 * This function calls select function providing arr[] array and k
	 */
	public List<Integer> selectOrderN(Integer [] arr, int k){
		List<Integer> list = new LinkedList<>();
		if(k<=0) return list;
		if(k>=arr.length) {
			for(int i=0;i<arr.length;i++){
				list.add(arr[i]);
			}
			return list;
		}
		
		select(arr,0,arr.length,k);
		for(int i=0;i<k;i++){
			list.add(arr[i]);
		}
		return list;
		
	}
	
	/**
	 *
	 * @param arr- array inputted by user
	 * @param p- start index of array to work on
	 * @param n- last index of array to work on
	 * @param k- number of biggest elements to find in array
	 * @return kth biggest element from the arr[] array
	 *
	 * This function uses Divide & Conquer technique  and partition to find the kth biggest element
	 */
	
	public Integer select(Integer [] arr, int p, int n,  int k){
		int r = p + n - 1;
		if(n<20){
			InsertionSort.nSquareSort(arr);
			return arr[p+n-k];
		}
		else
		{
			int q = partition(arr,p,r);
			int left = q - p;
			int right = r - q;
			if(right>=k)
				return select(arr,q+1,right,n);
			else if(right+1==k)
				return arr[q];
			else
				return select(arr,p,left,k-1-right);
		}
	}
	
	/**
	 *
	 * @param arr-   array inputted by user
	 * @param start-  start index of array to work on
	 * @param last-   last index of array to work on
	 * @return   the position of randomly selected pivot
	 *
	 * This function randomly selects pivot and arrange the elements of array about pivot such that all elements
	 * less than pivot lies on its left and all elements greater than pivot lies on its right.
	 */
	
	public int partition(Integer [] arr, int start, int last){
		Random rand = new Random();
		int piv = rand.nextInt(last+1) + start;
		Integer pivot = arr[piv];
		int i = start+1;
		int k = start+1;
		int j = start+1;
		while(j<=last){
			if(arr[j] < pivot){
			dpQuickSort.exchange(arr,i,k);
			k++;
			i++;
			}
			else{
				i++;
			}
			j++;
		}
		dpQuickSort.exchange(arr,k-1,start);
		return k-1;
	}
	
	/**
	 *
	 * @param arr- array inputted by user
	 * @param k- number of biggest elements to find in array
	 * @return- List of k biggest elements from arr[] array
	 *
	 * This function find the k biggest elements from arr[] array using Min heap
	 */
	
	public List<Integer> selectMinHeap(Integer [] arr, int k){
		List<Integer> list = new LinkedList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(k);
		for(int i=0;i<k;i++)
			pq.add(arr[i]);
		
		for(int j=k; j<arr.length; j++){
			if(arr[j]>pq.peek()){
				pq.remove();
				pq.add(arr[j]);
			}
		}
	   for(int i=0; i<k; i++)
		list.add(pq.remove());
		
		return list;
	}
	
	/**
	 *
	 * @param arr- array inputted by user
	 * @param k- number of biggest elements to find in array
	 * @return- List of k biggest elements from arr[] array
	 *
	 * This function find the k biggest elements from arr[] array using Max heap
	 */
	public List<Integer> selectMaxHeap(Integer [] arr, int k){
		List<Integer> list = new LinkedList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(arr.length,new Comparator<Integer>(){
			public int compare(Integer x, Integer y){
				if(x<y) return 1;
				else if (x>y) return -1;
				else return 0;
			}
		});
		for(int i=0;i<arr.length;i++){
			pq.add(arr[i]);
		}
		for(int j=0; j<k; j++){
			list.add(pq.remove());
		}
		return list;
	}
	
	
	public static void main(String [] args){
		Selection sel = new Selection();
		System.out.println("Give size of array");
		Scanner sc = new Scanner(System.in);
	//	int n = 1000000;
		int n = sc.nextInt();
		Integer[] inums = new Integer[n];
		for (int i = 0; i < n; i++) {
			inums[i] = new Integer(i);
		}
		Shuffle.shuffle(inums);
		
	/*	for (Object element: inums
				) {
			Integer ele = (Integer) element;
			System.out.print(ele+ " ");
		}
		System.out.println();*/
		
	//	sel.partition(inums,0,inums.length-1);
	//	List list =	sel.selectMaxHeap(inums, 10);
	//	List list = sel.selectMinHeap(inums,10);
		Timer t = new Timer();
		//	sel.partition(inums,0,inums.length-1);
		//	List list =	sel.selectMaxHeap(inums, 50);
			List list = sel.selectMinHeap(inums,50);
		//List list = sel.selectOrderN(inums,50);
		System.out.println(t.end());
		for (Object element: list
			 ) {
		   Integer ele = (Integer) element;
		   System.out.print(ele+" ");
		}
	}
}
