package Graph;
import java.io.*;
import java.util.*;
public class MaxBipartiteMatching {

//	1----> 1 
//	2 \  /  2
//	.  \/   .
//	. / \_  .
	
//	n        k
	static int vst[];
	static int curvst;
	static int mt[];     // mt[i] will tell that i(out of k) is matched with mt[i]
	static List<List<Integer>> adj;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("saved");
	}
	static int maxMatch(int n,int k,List<List<Integer>> g) {
		int ans=0;
		vst=new int[n];
		curvst=0;
		mt=new int[k];
		adj=g;
		for(int i=0;i<n;i++) {
			curvst++;
			if(dfs(i)) {
				ans++;
			}
		}
		
		return ans;
	}
	static boolean dfs(int u) {
		if(vst[u]==curvst)
			return false;
		vst[u]=curvst;
		for(int v:adj.get(u)) {
			if(mt[v]==-1||dfs(mt[v])) {
				mt[v]=u;
				return true;
			}
		}
		return false;
	}

}
