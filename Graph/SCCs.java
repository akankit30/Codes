package Graph;

import java.util.*;

public class SCCs {

	static int v;
	static int time=0;
	static List<Integer> adj[];
	static void initial(int n) {
		v=n;
		adj=new ArrayList[n];
		for(int i=0;i<n;i++) {
			adj[i]=new ArrayList<>();
		}
	}
	static void addEdge(int u,int v) {
		adj[u].add(v);
		//adj[v].add(u);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Tarjan's algo for Strongly connected graph 
		// Kojasaraju's algo is also for the same but in that 2 DFS travesals are involved
		int n=11;
		initial(n);
		addEdge(0, 1);
	    addEdge(0, 3);
	    
	    List<List<Integer>> list=new ArrayList<>();
	    TarjanSSCs(list);
	    for(List<Integer> sub:list) {
	    	for(int ele:sub) {
	    		System.out.print(ele+" ");
	    	}
	    	System.out.println();
	    }
		System.out.println("done");
	}
	static void TarjanSSCs(List<List<Integer>> list) {
		int disc[]=new int[v];
		Arrays.fill(disc, -1);
		int low[]=new int[v];
		boolean []onst=new boolean[v];
		Stack<Integer> st=new Stack<>();
		for(int i=0;i<v;i++) {
			if(disc[i]==-1) {
				Tutill(i,disc,low,onst,st,list);
			}
		}
	}
	static void Tutill(int u,int disc[],int low[],boolean[] onst,Stack<Integer> st,List<List<Integer>>list) {
		disc[u]=low[u]=++time;
		onst[u]=true;
		st.push(u);
		for(int v:adj[u]) {
			if(disc[v]==-1) {
				Tutill(v,disc,low,onst,st,list);
				low[u]=Math.min(low[u],low[v]);
			}
			else if(onst[v]) {
				low[u]=Math.min(low[u], disc[v]);
			}
		}
		int w=-1;
		if(low[u]==disc[u]) {
			list.add(new ArrayList<>());
			int ind=list.size()-1;
			while(w!=u) {
				w=st.pop();
				onst[w]=false;
				list.get(ind).add(w);
			}
		}
	}

}
