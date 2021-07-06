import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Solution {
	static int mod=1000_000_007;
	static int max=(int) 1e5 + 5;
	static List<Integer> div[]=new ArrayList[max];
	static int ans[]=new int[max];
	static int fake[]=new int[max];
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
	    for(int i=0;i<max;i++) {
	    	div[i]=new ArrayList<>();
	    }
	   
	    for(int p = 2; p < max; ++p) {
			for(int x = 2 * p; x < max; x += p) {
				div[x].add(p);
			}
		}
	    
	    for(int i=2;i<max;i++) {
	    	fake[i]=1;
	    	for(int x:div[i]) {
	    		fake[i]=Math.max(fake[i], 1+fake[(i/x) -1]);
	    	}
	    }
	    for(int i=3;i<max;i++) {
	    	ans[i]=1;
	    	for(int x:div[i]) {
	    		if(x>=3)
	    		ans[i]=Math.max(ans[i], 1+fake[i/x-1]);
	    	}
	    }
	    int t=fs.nextInt();
	    for(int cs=1;cs<=t;cs++) {
	    	int n=fs.nextInt();
	    	
	    	System.out.println("Case #"+cs+": "+ans[n]);
	    }
	  // out.close();
    }
	
	//  [30,20,10,40]  51,33,100,51]
	static int gcd(int a,int b) {
		if(b==0) return a;
		return gcd(b,a%b);
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