package Graph;
import java.util.*;
public class SSSP {
	static class Node{
		int node,cost;
		Node(){};
		Node(int node,int cost){
			this.node=node;
			this.cost=cost;
		}
	}
	static class G{
		List<List<Node>> adj;
		G(int v){
			adj=new ArrayList<>(v);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=5;
		G g=new G(n);
		for(int i=0;i<n;i++) {
			g.adj.add(new ArrayList<>());
		}
		g.adj.get(0).add(new Node(1, 4));
		g.adj.get(0).add(new Node(3, 2));
		g.adj.get(1).add(new Node(2, 2));
		g.adj.get(3).add(new Node(4, 3));
  
		g.adj.get(2).add(new Node(4, 2));
		g.adj.get(2).add(new Node(3, 1));
		int arr[]=dijkstra(0,g.adj);
		for(int ele:arr) System.out.print(ele+" ");
		System.out.println();
		arr=bellmanFord(0,g.adj);
		for(int ele:arr) System.out.print(ele+" ");
	}
	
	//Dijktra's algo -> single source shortest path algo for the graphs which does not contains -ve edge weigh
	// time complexity -> O( (E+V)(log(E)) )
	static int[] dijkstra(int src,List<List<Node>> g) {
		int dist[]=new int[g.size()];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src]=0;
		PriorityQueue<Node> q=new PriorityQueue<>((a,b)->a.cost-b.cost);
		q.add(new Node(src,0));
		boolean vst[]=new boolean[g.size()];
		while(!q.isEmpty()) {
			Node cur=q.poll();
			vst[cur.node]=true;
			for(Node next:g.get(cur.node)) {
				if(vst[next.node]) continue;
				int newdist=dist[cur.node]+next.cost;
				int olddist=dist[next.node];
				if(newdist<olddist) {
					dist[next.node]=newdist;
					q.add(new Node(next.node,dist[next.node]));
				}
			}
		}
		return dist;
	}
	
//	bellmanFord (SSSP) for graph containing -ve edges
	static int[] bellmanFord(int src,List<List<Node>> g) {
		int dist[]=new int[g.size()];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src]=0;
		for(int i=0;i<g.size()-1;i++) {
			for(int u=0;u<g.size();u++) {
				for(Node next:g.get(i)) {
					if(dist[u]!=Integer.MAX_VALUE&&dist[u]+next.cost<dist[next.node]) {
						dist[next.node]=dist[u]+next.cost;
					}
				}
			}
		}
		// if we have to find which nodes are affected by negative cycle we have to run it again
		// but if we only have to find tha whether it contains -ve cycle or not just run for 1 loop again
		for(int i=0;i<g.size()-1;i++) {
			for(int u=0;u<g.size();u++) {
				for(Node next:g.get(i)) {
					if(dist[u]!=Integer.MAX_VALUE&&dist[u]+next.cost<dist[next.node]) {
						dist[next.node]=Integer.MIN_VALUE;
					}
				}
			}
		}
		return dist;
	}
	
}
