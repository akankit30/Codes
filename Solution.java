import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.text.DecimalFormat;



 
 
public class Solution {
	
	static long mod=1000000007 ;
	static long cnt, num;
//	7 4 3 8 1 6 2 9 5 10
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
	    DecimalFormat formatter= new DecimalFormat("#0.000000");
	    int t=fs.nextInt();
	    
	    outer:for(int time=1;time<=t;time++) {
	    	int n=fs.nextInt(), q=fs.nextInt();
	    	char arr[]=fs.next().toCharArray();
	    	int cnt[][]=new int[26][n+1];
	    	for(int i=1;i<=n;i++) {
	    		for(int j=0;j<26;j++) cnt[j][i]=cnt[j][i-1];
	    		int ind = arr[i-1]-'A';
	    		cnt[ind][i]++;
	    	}
	        int ans=0;
	        while(q-->0) {
	        	int l=fs.nextInt(), r=fs.nextInt();
	        	int e=0, o=0;
	        	for(int i=0;i<26;i++) {
	        		if((cnt[i][r]-cnt[i][l-1])%2==0) e++;
	        		else o++;
	        	}
	        	if(o<=1) ans++;
	        }
	    	out.println("Case #"+time+": "+ans);
	    }
//	    out.println("Case #"+time+": "+ans);
	    out.close();
	}
	static void recur(StringBuilder str, int cur) {
		if(str.length()==0) {
			cnt++;
			cur++;
			num+=cur;
			return ;
		}
		cur+= check(str);
		for(int i=0;i<str.length();i++) {
			StringBuilder temp= new StringBuilder(str);
			str.deleteCharAt(i);
			recur(str,cur);
			str= temp;
		}
		return ;
	}
	static int check(StringBuilder str) {
		int l=0, r=str.length()-1
				;
		while(l<r) {
			if(str.charAt(l)!=str.charAt(r)) return 0;
			l++; r--;
		}
		return 1;
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