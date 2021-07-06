package Graph;
import java.util.*;

public class MaxFlow {

	static long cap[][];
	static List<List<Integer>> adj;
	static int size;
	static int mod=1000_000_007;
	static class pair{
		int first,second;
		pair(int f,int s){
			first=f;
			second=s;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	static void addEdge(int u,int v,int flow) {
		adj.get(u).add(v);
		adj.get(v).add(u);
		cap[u][v]+=flow; // here i am adding each time to cover multiple edges b/w same twp pairs
	}
	static long bfs(int s, int t, int[] parent) {
	    Arrays.fill(parent, -1);
	    parent[s] = -2;
	    Queue<pair> q=new LinkedList<>();
	    q.add(new pair(s,Integer.MAX_VALUE));
 
	    while (!q.isEmpty()) {
	        int cur = q.peek().first;
	        long flow = q.peek().second;
	        q.poll();
 
	        for ( int next : adj.get(cur)) {
	            if (parent[next] == -1 && cap[cur][next]!=0) {
	                parent[next] = cur;
	                long new_flow = Math.min(flow, cap[cur][next]);
	                if (next == t)
	                    return new_flow;
	                q.add(new pair(next,(int)new_flow));
	            }
	        }
	    }
 
	    return 0;
	}
 
	static long maxflow(int s, int t) {
		long flow = 0;
	    int parent[]=new int[size];
	    long new_flow;
 
	    while ((new_flow = bfs(s, t, parent))!=0) {
	        flow += new_flow;
	        int cur = t;
	        while (cur != s) {
	            int prev = parent[cur];
	            cap[prev][cur]-=new_flow;
	            cap[cur][prev]+=new_flow;
	            cur = prev;
	        }
	    }
	    return flow;
	}
	

}
