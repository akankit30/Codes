package algorithms;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {2,6,5,9,13,5,334,85,23};
		for(int ele:arr)
			System.out.print(ele+" ");
		System.out.println();
		quickSort(arr,0,arr.length-1);
		for(int ele:arr)
			System.out.print(ele+" ");
		System.out.println();
	}
	private static void quickSort(int[] arr,int low,int high) {
		if(low<high) {
			int pi=partition(arr,low,high);
			quickSort(arr,low,pi-1);
			quickSort(arr,pi+1,high);
		}
	}
	private static int partition(int[]arr,int low,int high) {
		int pivot=arr[low];
		int i=low,j=high;
		while(i<j) {
			while(i<j&&arr[i]<=pivot) i++;
			while(i<j&&arr[j]>pivot) j--;
			if(i<j) swap(arr,i,j);
		}
		swap(arr,low,j);
		return j;
	}
	private static void swap(int[] arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}

}
