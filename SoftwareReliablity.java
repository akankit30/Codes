import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.text.DecimalFormat;
import java.util.*;
 
public class SoftwareReliablity {
	static int mod= 998244353;
	
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
		FastScanner fs=new FastScanner();
		double p[]= { 0.3,0.2, 0.4,0.1};
		dsRandom(p);
		
		out.close();
		
	}
	static void dsRandom(double p[]) {
		// p[i]= problablit of i+1;
		double ex=0, variance=0;
		int n=p.length;
		for(int i=0;i<n;i++) ex+= p[i]*(i+1);
		for(int i=0;i<n;i++) {
			
			variance+= (i+1-ex)*(i+1-ex)*p[i];
		}
		System.out.println(ex);
		System.out.println(variance);
	}
	static void bionomial(int n,int x,int p,int q) {
		double px=0, ex=n*p, vx=n*p*q;
		px= nck(n,x)*Math.pow(p,x)*Math.pow(q, n-x);
	}
	static void poisson(int x,int miu) {
		double ex=miu, vx=miu;
		double px = Math.pow(2.71828, -miu)*Math.pow(miu, x);
		px/=fact(x);
		
	}
	static long pow(long a,long b) {
		if(b<0) return 1;
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
	static int gcd(int  a,int  b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
	static long nck(int n,int k) {
		if(k>n) return 0;
		long res=1;
		for(int i=k+1;i<=n;i++) res*=i;
		for(int i=1;i<=n-k;i++) res/=i;
		return res;
	}
	static long fact(long n) {
		long res=1;
		for(int i=1;i<=n;i++) res*=i;
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
		long[] lreadArray(int n) {
			long a[]=new long[n];
			for(int i=0;i<n;i++) a[i]=nextLong();
			return a;
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