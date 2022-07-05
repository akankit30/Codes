package introduction;
import java.io.*;
import java.math.*;
import java.util.*;


class GFG {
	static long dp[]=new long[1025];
	static long arr[];
	static int n;
	public static void main(String args[]) {
		System.out.println(Integer.toBinaryString((1<<20)-1));
	}
	static long solve(String a , int arr[], int []c ,int d) {
		char str[]=a.toCharArray();
		int n=arr.length;
		for(int i=0;i<n;i++) {
			if(c[i]==1) {
				arr[i]+=d;
			}
			else arr[i]-=d;
		}
		long ans=0;
		long cur=0;
		for(int i=1;i<n;i++) {
			cur+= ((Math.abs((long)arr[i]-arr[i-1]))*i);
			ans+=cur;
			
		}
		cur=0;
		for(int i=n-2;i>=0;i--) {
			cur+= ((Math.abs((long)arr[i]-arr[i+1]))*i);
			ans+=cur;
			
		}
		return ans;
	}
	static long recur(int mask ) {
		if(mask==0) return 0;
		if(dp[mask]!=-1) return dp[mask];
		long cur= Integer.MIN_VALUE;
		for(int i=0;i<10;i++) {
			if(((1<<i)&mask)!=0){
				long sub = calculate(i,mask)+ recur(mask^(1<<i));
				if(sub>cur) {
					cur=sub;
				}
			}
		}
		return dp[mask]=cur;
	}
	static long calculate(int index,int mask) {
		long left= -1;
		for(int i=index+1;i<10;i++) {
			if(((1<<i)&mask)!=0){
				left=arr[i];
				break;
			}
		}
		long right=-1;
		for(int i=index-1;i>=0;i--) {
			if(((1<<i)&mask)!=0){
				right=arr[i];
				break;
			}
		}
		if(left==-1&&right==-1) return arr[index];
		if(left!=-1&&right!=-1) return left*right;
		if(left!=-1) return left;
		return right;
	}
	
	
}

//1
//1
//4
//15
//76
//455
//3186
//25487
//229384
//2293839