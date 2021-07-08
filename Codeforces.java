import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
 
 

public class Codeforces {
	
	static int mod=1000_000_007 ;
	
	static long pow;
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
	    int n=fs.nextInt();
	    int arr[]=fs.readArray(n);
	    int ans[]=new int[n];
	    int dp[]=new int[n];
	    Arrays.fill(dp, Integer.MAX_VALUE);
	    for(int i=0;i<n;i++) {
	    	int cur=arr[i];
	    	int ind=find(dp,n,cur);
	    	ans[i]=ind+1;
	    	dp[ind]=cur;
	    }
	    for(int ele: ans) out.print(ele+" ");
	    out.println();
	    out.close();
	    
    }
	static int find(int arr[],int n, int tar) {
		int l=0,r=n-1;
		while(l<r) {
			int mid=(l+r)/2;
			if(arr[mid]<=tar) l=mid+1;
			else r=mid;
		}
		return r;
	}
	static long pow(long a,long b) {
		long res=1;
		while(b!=0) {
			if((b&1)!=0) {
				res*=a;
				res%=mod;
			}
			a*=a;
			a%=mod;
			b=b>>1;
		}
		return res;
	}
	static long lcm(long a,long b) {
		long ans=a*b;
		while(b!=0) {
			long temp= b;
			b=a%b;
			a=temp;
		}
		return ans/a;
	}
	
	static long fact(long n) {
		long res=1;
		for(int i=2;i<=n;i++) {
			res*=i;
			res%=mod;
		}
		return res;
	}
	static int gcd(int  a,int  b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
    static void sort(long[] a) {
		//suffle
		int n=a.length;
		Random r=new Random();
		for (int i=0; i<a.length; i++) {
			int oi=r.nextInt(n);
			long temp=a[i];
			a[i]=a[oi];
			a[oi]=temp;
		}
		
		//then sort
		Arrays.sort(a);
	}
	// Use this to input code since it is faster than a Scanner
	static class FastScanner {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer("");
		String next() {
			while (!st.hasMoreTokens())
				try {
					st=new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		int[] readArray(int n) {
			int[] a=new int[n];
			for (int i=0; i<n; i++) a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
	}
 
}