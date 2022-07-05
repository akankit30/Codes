import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;



public class Codeforces {
	static int max;
	static int timer;
	static int log;
	static int up[][];
	static StringBuilder str[][];
	static List<Integer> adj[];
	static int[] tin,tout;
	static int d[] ;
	static char[] arr;
	static long mod= 10000_0000_0;
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
		FastScanner fs=new FastScanner();
//		DecimalFormat formatter= new DecimalFormat("#0.000000");
//		int t=fs.nextInt();
		int t=1;
	    outer:for(int time=1;time<=t;time++) {
	    	int n=fs.nextInt();
	    	adj=new ArrayList[n+1];
	    	for(int i=0;i<=n;i++) adj[i]=new ArrayList<>();
	    	for(int i=0;i<n-1;i++) {
	    		int u=fs.nextInt(), v=fs.nextInt();
	    		adj[v].add(u);
	    		adj[u].add(v);
	    	}
	    	arr=fs.next().toCharArray();
	    	max=n+1;
	    	log=(int)(Math.ceil(Math.log(max)/Math.log(2)));
	    	up=new int[max][log+1];
	    	str=new StringBuilder[max][log+1];
	    	for(int i=0;i<max;i++) for(int j=0;j<=log;j++) str[i][j]=new StringBuilder();
	    	timer=0;
	    	tin=new int[max];
	    	tout=new int[max];
	    	d=new int[max];
	    	dfs(1,1);
	    	
	    	int m=fs.nextInt();
	    	while(m-->0) {
	    		int u=fs.nextInt(), v=fs.nextInt();
	    		int lca = lca(u,v);
	    		StringBuilder a = from_node_to_kth_anc(u,d[u]-d[lca]);
	    		StringBuilder b= from_node_to_kth_anc(v,d[v]-d[lca]);
	    		b.reverse();
	    		a.append(arr[lca-1]);
	    		a.append(b);
	    		
	    		System.out.println(a);
	    	}
	    }
		out.close();	
		
	}
	static void dfs(int cur,int p) {
		d[cur]=d[p]+1;
		tin[cur]= ++timer;
		up[cur][0]=p;
		str[cur][0]=new StringBuilder(String.valueOf(arr[cur-1]));
		for(int j=1;j<=log;j++) {
			up[cur][j]=up[up[cur][j-1]][j-1];
			StringBuilder lower = new StringBuilder(str[cur][j-1]);
			StringBuilder upper = new StringBuilder(str[up[cur][j-1]][j-1]);
			str[cur][j]= lower.append(upper);
		}
		for(int c:adj[cur]) {
			if(p!=c) {
				dfs(c, cur);
			}
		}
		tout[cur]=++timer;
	}
	static boolean is_ancestor(int a,int b) { // tells whether a is ancestor of b or not;
		return tin[a]<=tin[b]&&tout[a]>=tout[b];
	}
	static int lca(int u,int v) {
		if(is_ancestor(u,v)) return u;
		if(is_ancestor(v,u)) return v;
		for(int k=log;k>=0;k--) {
			if(!is_ancestor(up[u][k],v)) {
				u=up[u][k];
			}
		}
		return up[u][0];
	}
	static StringBuilder from_node_to_kth_anc(int node,int k) {
		int nn= node;
		StringBuilder ans = new StringBuilder();
		for(int j=0;j<log;j++) {
			if((k&(1<<j))!=0) {
				ans.append(str[node][j]);
				node= up[node][j];
			}
		}
		return ans;
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
//		return fact[(int)n];
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
		String nextLine() {
			String str="";
			try {
				str= (br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
		double nextDouble() {
			return Double.parseDouble(next());
		}
		long[] readArrayL(int n) {
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