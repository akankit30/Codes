import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class B {
	static int mod=1000_000_007;

	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
	    int n=fs.nextInt();
	    long arr[]=new long[n];
	    for(int i=0;i<n;i++) arr[i]=fs.nextLong();
	    long left[]=new long[n];
	    long right[]=new long[n];
	    left[0]=arr[0];
	    for(int i=1;i<n;i++) {
	    	if(arr[i]+left[i-1]<arr[i]) {
	    		left[i]=arr[i];
	    	}
	    	else left[i]=left[i-1]+arr[i];
	    }
	    right[n-1]=arr[n-1];
	    for(int i=n-2;i>=0;i--) {
	    	if(right[i+1]+arr[i]<arr[i]) {
	    		right[i]=arr[i];
	    	}
	    	else right[i]=arr[i]+right[i+1];
	    }
	    long max=0;
	    for(int i=0;i<n;i++) {
	    	long cur=arr[i]*arr[i];
	    	long l=0;
	    	if(i>0) l=Math.max(l, left[i-1]);
	    	long r=0;
	    	if(i<n-1) r=Math.max(r, right[i+1]);
	    	max=Math.max(max, cur+l+r);
	    }
	    out.println(max);
	    out.close();
	}
	static boolean solve(int n,int g[],int k) {
		int mn=g[0], mx=g[0];
		for(int i=1;i<n;i++) {
			mn=Math.max(mn-k+1, g[i]);
			mx=Math.min(mx+k-1, g[i]+k-1);
			if(mn>mx) return false;
		}
		if(g[n-1]<=mx&&g[n-1]>=mn) return true;
		return false;
	}
	static long gcd(long  a,long  b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
	static long nck(int n,int k) {
		if(k>n) return 0;
		long res=1;
		res*=fact(n);
		res%=mod;
		res*=modInv(fact(k));
		res%=mod;
		res*=modInv(fact(n-k)); 
		res%=mod;
		return res;
	}
	static long fact(long n) {
		long res=1;
		for(int i=2;i<=n;i++) {
			res*=i;
			res%=mod;
		}
		return res;
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
	static long modInv(long n) {
		return pow(n,mod-2);
	}
	
	static void sort(int[] a) {
		//suffle
		int n=a.length;
		Random r=new Random();
		for (int i=0; i<a.length; i++) {
			int oi=r.nextInt(n);
			int temp=a[i];
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