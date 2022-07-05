package Graph;

import java.util.ArrayList;
import java.util.List;

public class BuildPathUsingLca {
	static int max;
	static int timer;
	static int log;
	static int up[][];
	static StringBuilder str[][];
	static List<Integer> adj[];
	static int[] tin,tout;
	static int d[] ;
	static char[] arr;
	
	public static void main(String[] args) {
		int n=10;
		// 1 based indexing
    	adj=new ArrayList[n+1];
    	for(int i=0;i<=n;i++) adj[i]=new ArrayList<>();
    	for(int i=0;i<n-1;i++) {
    		int u=2, v=4;
    		adj[v].add(u);
    		adj[u].add(v);
    	}
    	// take input for arr
//    	arr=fs.next().toCharArray();
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
    	
    	int query=100;
    	while(query-->0) {
    		int v=2, u= 5;
    		// v = node_a to u = node_b;
    		int lca = lca(u,v);
    		StringBuilder a = from_node_to_kth_anc(u,d[u]-d[lca]);
    		StringBuilder b= from_node_to_kth_anc(v,d[v]-d[lca]);
    		b.reverse();
    		a.append(arr[lca-1]);
    		a.append(b);
    		
    		System.out.println(a);
    	}
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
}
