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
			if(!vst[v]) {
				findBridge(v,u,disc,low,vst,bridges);
				low[u]=Math.min(low[u], low[v]);
				if(low[v]>disc[u]) {
					bridges.add(u);
					bridges.add(v);
				}
			}
			else if(v!=parent) {
				low[u]=Math.min(low[u],disc[v]);
			}
		}
	}
	static void findAtcPoint(int u,int parent,int disc[],int low[],boolean vst[],boolean isA[]) {
		vst[u]=true;
		int childrens=0;
		disc[u]=low[u]=++time;
		for(int v:adj[u]) {
			if(!vst[v]) {
				childrens++;
				findAtcPoint(v,u,disc,low,vst,isA);
				low[u]=Math.min(low[u], low[v]);
				if(parent==-1&&childrens>1) {
					isA[u]=true;
				}
				if(parent!=-1&&low[v]>=disc[u]) {
					isA[u]=true;
				}
			}
			else if(v!=parent) {
				low[u]=Math.min(low[u],disc[v]);
			}
		}
	}

}
