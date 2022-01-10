package utilityFunctions;

public class TernarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
//	
//	   /\
//	  /  \
//	 /    \
//	/      \
//	 for first increasing and then decreasing function 
//	 although we can apply on first dcreasing and then increasing functions also
//   by return -ve of res in find function.
	static long ternary_search(int arr[]) {
		long l=0, r=Integer.MAX_VALUE;
		while(r-l>3) {
			long m1=l+(r-l)/3;
			long m2=r-(r-l)/3;
			long f1= find(m1);
			long f2= find(m2);
			if(f1<f2) {
				l=m1;
			}
			else r=m2;
		}
		long ans=Integer.MIN_VALUE;
		for(long i=l;i<=r;i++) {
			ans=Math.max(ans, find(i));
		}
		return ans;
	}
	static long find(long m) {
		long res=0;
		return res;
	}

}
