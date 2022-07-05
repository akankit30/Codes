package DP;

public class DivideAndConques {
    /*
     Divide And conquer optimization could be applied when 
     
     dp[i][j]= max( k| ( dp[i-1][k-1] + cost[k][j] );
     where 0<=k<=j;
     
     and cost[k][0]<=cost[k][1]<=[cost[k][2] ..... 
     that is cost follows monotonicity at a layer
     
     Time Complexity : O( N*logN * k * TT) ----> TT = cost of finding cost[i][j];
     */
	
	static int n,k;
	static int cost[][];
	static int dp_cur[];
	static int dp_before[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		n=1; k=1;
    	
    	dp_before=new int[n];
    	for(int i=0;i<n;i++) {
    		dp_before[i]= cost[0][i];
    	}
    	dp_cur=new int[n];
		for(int j=1;j<k;j++) {
			compute(0,n-1, 0,n-1);
			for(int i=0;i<n;i++) dp_before[i]=dp_cur[i];
		}
    	
    	System.out.println(dp_before[n-1]);

	}
	static void compute(int l,int r,int optl, int optr) {
		if(l>r) return ;
		int mid= (l+r)/2;
		int best=Integer.MAX_VALUE, opt=-1;
		for(int j=optl; j<=Math.min(mid, optr);j++) {
			int cur= ((j==0) ? 0 : dp_before[j-1]) +cost[j][mid];
			if(cur<best) {
				best=cur;
				opt= j;
			}
		}
		
		dp_cur[mid]=best;
		compute(l,mid-1, optl,opt);
		compute(mid+1, r, opt, optr);
	}

}
