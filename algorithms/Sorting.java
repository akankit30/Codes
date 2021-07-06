package algorithms;
import java.util.*;
public class Sorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {12 ,5 ,787 ,1, 23};
		for(int ele:arr) System.out.print(ele+" ");
		System.out.println();
		quickSort(arr,0,arr.length-1);
		for(int ele:arr) System.out.print(ele+" ");
		System.out.println();

	}
	public static void merge(ArrayList<Integer> arr, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr.get(l+i);
        for (int j = 0; j < n2; ++j)
            R[j] = arr.get(m + 1 + j);
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr.set(k,L[i]);
                i++;
            }
            else {
                arr.set(k,R[j]);
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr.set(k,L[i]);
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr.set(k,R[j]);
            j++;
            k++;
        }
    }
 
    public static void merge_sort(ArrayList<Integer> arr, int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;
            merge_sort(arr, l, m);
            merge_sort(arr, m + 1, r);
 
            merge(arr, l, m, r);
        }
    }
    
    // Quick Sort
    private static void quickSort(int[] arr,int low,int high) {
		if(low<high) {
			int pi=partition(arr,low,high);
			quickSort(arr,low,pi-1);
			quickSort(arr,pi+1,high);
		}
	}
    
	static int partition(int[] arr, int low, int high)
	{
	     
	    // pivot
	    int pivot = arr[high];
	     
	    // Index of smaller element and
	    // indicates the right position
	    // of pivot found so far
	    int i = (low - 1);
	 
	    for(int j = low; j <= high - 1; j++)
	    {
	         
	        // If current element is smaller
	        // than the pivot
	        if (arr[j] < pivot)
	        {
	             
	            // Increment index of
	            // smaller element
	            i++;
	            swap(arr, i, j);
	        }
	    }
	    swap(arr, i + 1, high);
	    return (i + 1);
	}
	private static void swap(int[] arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}

}
