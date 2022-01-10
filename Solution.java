import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

 
 
public class Solution {
	
	static int mod=1000000007 ;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
	    
	    int t=fs.nextInt();
	    outer:for(int time=1;time<=t;time++) {
	    	
	    	long ans=0;
	    	
	    	int n=fs.nextInt();
	    	char arr[]=fs.next().toCharArray();
	    	
	    	/*
	    	 *  U = Uncolored
				R = Red       1
				Y = Yellow    2
				B = Blue      3
				O = Orange    1 2 
				P = Purple    1 3 
				G = Green     2 3
				A = Gray      1 2 3
	    	 */
	    	boolean f=false;
	    	for(char c:arr) {
	    		boolean isp=false;
	    		if(c=='R'||c=='O'||c=='P'||c=='A') {
	    			isp=true;
	    			
	    		}
	    		if(!isp) f=false;
	    		
	    		if(!f&&isp) ans++;
	    		if(isp) f=true;
	
	    	}
	    	f=false;
	    	for(char c:arr) {
	    		boolean isp=false;
	    		if(c=='Y'||c=='O'||c=='A'||c=='G') {
	    			isp=true;
	    			
	    		}
	    		if(!isp) f=false;
	    		
	    		if(!f&&isp) ans++;
	    		if(isp) f=true;
	
	    	}
	    	f=false;
	    	for(char c:arr) {
	    		boolean isp=false;
	    		if(c=='B'||c=='A'||c=='P'||c=='G') {
	    			isp=true;
	    			
	    		}
	    		if(!isp) f=false;
	    		
	    		if(!f&&isp) ans++;
	    		if(isp) f=true;
	
	    	}
	    	out.println("Case #" + time + ": "+ans);
	    }
	    
	    
	    out.close();
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