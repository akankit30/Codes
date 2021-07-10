package Graph;

import java.io.*;
import java.util.*;


public class LCABinaryLifting {
	
	static int max;
	static int timer;
	static int log;
	static int up[][];
	static List<List<Integer>> adj;
	static int[] tin,tout;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
	    
    	int n=fs.nextInt();
    	adj=new ArrayList<>();
    	for(int i=0;i<n;i++)
    	adj.add(new ArrayList<>());
    	
		int m=fs.nextInt();
		while(m-->0) {
			int u=fs.nextInt(), v=fs.nextInt();
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
    	
    	max=n;
    	log=(int)(Math.ceil(Math.log(max)/Math.log(2)));
    	up=new int[max][log+1];
    	timer=0;
    	tin=new int[max];
    	tout=new int[max];
    	dfs(0,0);
    	int q=fs.nextInt();
    	while(q-->0) {
    		System.out.println(query(fs.nextInt(),fs.nextInt()));
    	}
	    
	    out.close();
	}

	static void dfs(int a,int p) {
		tin[a]=++timer;
		up[a][0]=p;
		for(int j=1;j<log;j++) {
			up[a][j]=up[up[a][j-1]][j-1];
		}
		for(int child:adj.get(a)) {
			if(p!=child) {
				dfs(child,a);
			}
		}
		tout[a]=++timer;
	}
	static boolean is_ancestor(int a,int b) { // tells whether a is ancestor of b or not;
		return tin[a]<=tin[b]&&tout[a]>=tout[b];
	}
	static int query(int u,int v) {
		if(is_ancestor(u,v)) return u;
		if(is_ancestor(v,u)) return v;
		for(int k=log;k>=0;k--) {
			if(!is_ancestor(up[u][k],v)) {
				u=up[u][k];
			}
		}
		return up[u][0];
	}
	static void mysort(long[] a) {
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

//static int max=10000;
//static int log=14;
//static int depth[]=new int[max];
//static int up[][]=new int[max][log];
//static List<List<Integer>> adj;
//
//public static void main(String[] args) throws Exception {
//	// TODO Auto-generated method stub
//    FastScanner fs=new FastScanner();
//    adj=new ArrayList<>();
//    int n=fs.nextInt();
//    for(int i=0;i<n;i++) {
//    	adj.add(new ArrayList<>());
//    	int m=fs.nextInt();
//    	while(m-->0) {
//    		adj.get(i).add(fs.nextInt());
//    	}
//    }
//    dfs(0);
//    int q=fs.nextInt();
//    while(q-->0) {
//    	int u=fs.nextInt(),v=fs.nextInt();
//    	System.out.println(get_lca(u,v));
//    }
//}
//
//static void dfs(int a) {
//	for(int b:adj.get(a)) {
//		depth[b]=depth[a]+1;
//		up[b][0]=a;
//		for(int j=1;j<log;j++) {
//			up[b][j]=up[up[b][j-1]][j-1];
//		}
//		dfs(b);
//	}
//}
//static int get_lca(int a,int b) {
//	if(depth[a]<depth[b]) {
//		int temp=a;
//		a=b;
//		b=temp;
//	}
//	int k=depth[a]-depth[b];
//	for(int j=log-1;j>=0;j--) {
//		if((k&(1<<j))!=0) {
//			a=up[a][j];
//		}
//	}
//	if(a==b) return a;
//	for(int j=log-1;j>=0;j--) {
//		if(up[a][j]!=up[b][j]) {
//			a=up[a][j];
//			b=up[b][j];
//		}
//	}
//	return up[a][0];
//}
