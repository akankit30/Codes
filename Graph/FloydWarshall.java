package Graph;

import java.util.*;

public class FloydWarshall {
	static double[][] g,dp;
	static int[][]next;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=9;
		init(n);
		
	}
	static void solve(int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				dp[i][j]=g[i][j];
				next[i][j]=j;
			}
		}
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++) {
					if(dp[i][j]>dp[i][k]+dp[k][j]) {
						dp[i][j]=dp[i][k]+dp[k][j];
						next[i][j]=next[i][k];
					}
				}
		}
		// identifying negative cycle by giving -INF to every edge that is trapped
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++) {
					if(dp[i][j]>dp[i][k]+dp[k][j]) {
						dp[i][j]=Double.NEGATIVE_INFINITY;
						next[i][j]=-1;
					}
				}
		}
	}
	static List<Integer> reconstructShortestPath(int st,int end){
		List<Integer> path=new ArrayList<>();
		if(dp[st][end]==Double.POSITIVE_INFINITY) return path;
		int at=st;
		for(;at!=end;at=next[at][end]) {
			if(at==-1) return null;
			path.add(at);
		}
		if(next[at][end]==-1) return null;
		return path;
	}
	static void init(int n) {
		g=new double[n][n];
		dp=new double[n][n];
		next=new int[n][n];
	}

}
