package utilityFunctions;

import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		int arr[]= {1,2,5,5,5,8,9,15,64,70};
		          //0,1,2,3,4,5,6,7
//		int l=(lower_bound(arr,4));
		int r =(upper_bound(arr,8));
//		System.out.println(l);
		System.out.println(r);
	}
	public static int binarySearch(int[]arr,int n,int target) {
		int l=0,r=n;
		while(l<=r) {
			System.out.println("e");
			int mid=l+(r-l)/2;
			if(arr[mid]<target)
				l=mid+1;
			else if(arr[mid]>target)
				r=mid-1;
			else return mid;
		}
		return -1;
	}
	public static int lower_bound(int[] arr,int key) {
		int l=0,r=arr.length;
		while(l<r) {
			int mid=l+(r-l)/2;
			if(arr[mid]>=key)
				r=mid;
			else l=mid+1;
		}
		return l;
	}
	public static int upper_bound(int[] arr,int key) {
		int l=0,r=arr.length;
		while(l<r) {
			int mid=l+(r-l)/2;
			if(arr[mid]<=key)
				l=mid+1;
			else r=mid;
		}
		return l;
	}

}
