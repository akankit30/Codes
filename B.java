import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class B {
	
	static int mod =1000000007; 
	
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
//	    long a=fs.nextLong(),b=fs.nextLong();
//	    System.out.println(find(4,10));
	    for(int i=1;i<=100;i++) {
	    	System.out.println(i+" "+find(1,i));
	    }
	    out.close();
	}
	public static int find(long a,long b) {
		int ans=0;
	    for(long i=a+1;i<=b;i++) {
	    	ans+=count(i-1,i);
	    }
	   return ans;
	}
	public static int count(long a,long b) {
		int ans=0;
		a=a^b;
		for(int j=0;j<60;j++) {
			if((a&(1<<j))!=0) ans++;
		}
		return ans;
	}
	static void rev(char arr[]) {
		int i=0,j=arr.length-1;
		while(i<j) {
			char temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			i++; j--;
		}
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