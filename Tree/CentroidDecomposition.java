package Tree;

import java.util.*;

public class CentroidDecomposition {
	static int max;
	static int[] par, sub;
	static Set<Integer> adj[];
	public static void main(String[] args) {
		Scanner fs=new Scanner(System.in);
		int n=fs.nextInt();
		adj=new HashSet[n+1];
		for(int i=0;i<=n;i++) adj[i]=new HashSet<>();
		for(int i=0;i<n-1;i++) {
			int u=fs.nextInt(), v=fs.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		init(n);
		
		for(int i=0;i<=n;i++) {
			System.out.println(par[i]);
		}
		
	}
	static void init(int n) {
		// assume that we are done with the graph input
		max=n;
		par=new int[max+1];
		Arrays.fill(par,-1);
		sub=new int[max+1];
		decompose(1,-1);
		
	}
	static void decompose(int cur,int p) {
		int subsize= dfs1(cur,-1);
		int centroid= dfs(cur,p,subsize);
		
		par[centroid]=p;
		for(int c:adj[centroid]) {
			adj[c].remove(centroid);
			decompose(c,centroid);
		}
	}
	static int dfs1(int cur,int p) {
		sub[cur]=1;
		for(int c:adj[cur]) {
			if(c!=p) {
				sub[cur]+=dfs1(c,cur);
			}
		}
		return sub[cur];
	}
	static int dfs(int cur,int p,int n) {
		for(int c:adj[cur]) {
			if(c!=p&& sub[c]>n/2) {
				return dfs(c,cur,sub[c]);
			}
		}
		return cur;
	}

}
