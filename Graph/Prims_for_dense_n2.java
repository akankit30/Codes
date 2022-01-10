package Graph;

import java.util.Arrays;

public class Prims_for_dense_n2 {

	static int mod= 1000000007;
	static long wt[][];
	static boolean edge[][], selected[];
	static long min[];
	static int from[];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=9; // no of vertex= n+1;
		wt=new long[n+1][n+1];
		// assign weigth to wt;
		from=new int[n+1];
		edge=new boolean[n+1][n+1];
		
		selected=new boolean[n+1];
		selected[0]=true;
		min=new long[n+1];
		Arrays.fill(min, Long.MAX_VALUE);
		
		min[0]=0;
		long ans=0;
		update_min(0,n);
		for(int i=1;i<=n;i++) {
			int u=select(n);
			selected[u]=true;
			int v=from[u];
			edge[u][v]=edge[v][u]=true;
			ans+=wt[u][v];
			update_min(u,n);
		}

	}
	static void update_min(int u,int n) {
		for(int i=0;i<=n;i++) {
			if(!selected[i]) {
				if(wt[u][i]<min[i]) {
					min[i]=wt[u][i];
					from[i]=u;
				}
				
			}
		}
	}
	static int select(int n) {
		long cur=Long.MAX_VALUE;
		int ind=-1;
		for(int i=0;i<=n;i++) {
			if(!selected[i]&&min[i]<cur) {
				cur=min[i];
				ind=i;
			}
		}
		return ind;
	}

}
