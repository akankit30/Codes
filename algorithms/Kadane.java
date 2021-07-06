package algorithms;

public class Kadane {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		 kadanesWithIndex(arr);

	}
	private static void kadanesWithIndex(int arr[]) {
		int st=0,end=0;
		int n=arr.length;
		int maxendinghere=arr[0];
		int maxsofar=maxendinghere;
		int beg=0;
		for(int i=1;i<n;i++) {
			maxendinghere+=arr[i];
			if(maxendinghere<arr[i]) {
				maxendinghere=arr[i];
				beg=i;
				
			}
			if(maxendinghere>maxsofar) {
				maxsofar=maxendinghere;
				st=beg;
				end=i;
			}
		}
		for(int i=st;i<=end;i++) {
			System.out.print(arr[i]+" ");
		}
		
	}
	

}
