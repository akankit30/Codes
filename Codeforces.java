import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
 
 
public class Codeforces {
	
	static int mod =1000000007; 
	static int cnt[][]=new int[1002][100005];
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
	    int n=fs.nextInt();
	    int arr[]=fs.readArray(n);
	    for(int i=n-1;i>=0;i--) {
	    	int val=arr[i];
	    	for(int j=0;j<100005;j++) {
	    		cnt[i][j]=cnt[i+1][j];
	    	}
	    	cnt[i][val]++;
	    }
	    int next[]=new int[100005];
	    int last[]=new int[100005];
	    Arrays.fill(last,n);
	    for(int i=n-1;i>=0;i--) {
	    	int val=arr[i];
	    	next[i]=last[val+1];
	    	last[val]=i;
	    }
//	    for(int i=0;i<n;i++) System.out.print(next[i]+" ");
//	    System.out.println();
	    long ans=0;
	    boolean used[]=new boolean[100005];
	    for(int i=0;i<n;i++) {
	    	int cur=arr[i];
	    	int f=next[cur];
	    	if(!used[cur]) {
	    		long res=cnt[i][cur];
	    		for(int j=0;j<100005;j++) {
	    			if(j==cur) continue;
	    			res*=(cnt[f][j]+1);	
	    			res%=mod;
	    		}
//	    		System.out.println(res);
	    		used[cur]=true;
	    		ans+=res;
		    	ans%=mod;
	    	}
	    }
	    System.out.println(ans);
	    out.close();
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