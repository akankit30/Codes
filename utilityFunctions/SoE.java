package utilityFunctions;

import java.util.*;

public class SoE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=15;
//		boolean arr[]=new boolean[n+1];
//		sieveOfEratosthenes(arr);
//		for(int i=0;i<=n;i++)
//			System.out.println(i+" "+arr[i]);
//		for(boolean ele:arr)
//			System.out.print(ele+" ");
		int lpf[]=leastPrimeFactor(60);
		List<Integer> pfs=primefactors(lpf,60);
		for(int ele:pfs) {
			System.out.print(ele+" ");
		}
		System.out.println();
	}
	public static void sieveOfEratosthenes(boolean arr[]) {
		Arrays.fill(arr, true);
		int n=arr.length;
		arr[0]=arr[1]=false;
		for(int i=2;i*i<n;i++) {
			if(!arr[i])
				continue;
			for(int j=2*i;j<n;j+=i)
				arr[j]=false;
		}
	}
	private static void divisors(List<Integer> div[],int max) {
		for(int i=2;i<max;i++) {
			for(int j=2*i;j<max;j+=i) {
				div[j].add(i);
			}
		}
	}
	private static void leastPrimeFactor(int lpf[],int max) {
		List<Integer> pfs=new ArrayList<>();
		for(int i=2;i<=max;i++) {
			if(lpf[i]==0) {
				pfs.add(i);
				lpf[i]=i;
			}
			System.out.println(pfs);
			for(int j=0;j<pfs.size()&&pfs.get(j)<=lpf[i]&&i*pfs.get(j)<=max;j++) {
				lpf[i*pfs.get(j)]=pfs.get(j);
			}
		}
	}
	private static int[] leastPrimeFactor(int max) {
		int arr[]=new int[max+1];
		Arrays.fill(arr, -1);
		for(int i=2;i<=max;i++) {
			if(arr[i]==-1) {
				arr[i]=i;
				for(int j=i*i;j<=max;j+=i) {
					if(arr[j]==-1) {
						arr[j]=i;
					}
				}
			}
		}
		return arr;
	}
	static List<Integer> primefactors(int arr[],int n){
		List<Integer> pfs=new ArrayList<>();
		while(n!=1) {
			pfs.add(arr[n]);
			n/=arr[n];
		}
		return pfs;
	}
	
	// ax+by=gcd(a,b);  this function returns one of the values of x and y;
		// rest can be found by (x+k*(b/gcd(a,b)) , y-k*(a/gcd(a,b))) ;
		// where k is any integer;
	private static List<Integer> solveEq(int a,int b){
		if(b==0) {
			return new ArrayList<Integer>(Arrays.asList(1,0,a));
		}
		List<Integer> sub=solveEq(b,a%b);
		int x=sub.get(0);
		int y=sub.get(1);
		int g=sub.get(2);
		return new ArrayList<Integer>(Arrays.asList(y,x-(a/b)*y,g));
	}
}
