package Graph;

import java.util.*;

public class BridgeAndArticulation {
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
		adj[v].add(u);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//https://www.geeksforgeeks.org/bridge-in-a-graph/
		//https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
		int n=9;
		initial(n);
		System.out.println("done");
	}
	static void findBridge(int u,int parent,int disc[],int low[],boolean vst[],List<Integer> bridges) {
		vst[u]=true;
		disc[u]=low[u]=++time;
		for(int v:adj[u]) {
			if(v==parent) continue;
			if(!vst[v]) {
				findBridge(v,u,disc,low,vst,bridges);
				low[u]=Math.min(low[u], low[v]);
				if(low[v]>disc[u]) {
					bridges.add(u);
					bridges.add(v);
				}
			}
			else{
				low[u]=Math.min(low[u],disc[v]);
			}
		}
	}
	static void findAtcPoint(int u,int p,int disc[],int low[],boolean vst[],boolean isA[]) {
		vst[u]=true;
		int childrens=0;
		disc[u]=low[u]=++time;
		for(int v:adj[u]) {
			if(v==p) continue;
			if(vst[v]) {
				low[u]=Math.min(low[u], disc[v]);
			}
			else {
				findAtcPoint(v,u,disc,low,vst,isA);
				low[v]=Math.min(low[v], low[u]);
				if(low[v]>=low[u]&&p!=-1) {
					isA[u]=true;
				}
				childrens++;
			}
		}
		if(p==-1&&childrens>1) isA[u]=true;
	}

}
