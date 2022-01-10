package dataStructureImplementations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SegmentTree {

	static long tree[];
	static int n;
	static class FastScanner {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer("");
		String next() {
			while (!st.hasMoreTokens())
				try {
					st=new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		long[] lreadArray(int n) {
			long a[]=new long[n];
			for(int i=0;i<n;i++) a[i]=nextLong();
			return a;
		}
		int[] readArray(int n) {
			int[] a=new int[n];
			for (int i=0; i<n; i++) a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs=new FastScanner();
		n=fs.nextInt();
		int arr[]=new int[n];
//		int arr[]=fs.readArray(n);
		build(arr);
		for(long ele:tree) {
			System.out.print(ele+" ");
		}
		System.out.println();
		System.out.println(sum_q(1,1,n,1,n));
		update_q(0,9);
		System.out.println(sum_q(1,1,n,1,n));
		
	}
	static void build(int arr[]) {
		while(Integer.bitCount(n)!=1) n++;
		
		tree=new long[n*2];
		
		for(int i=0;i<arr.length;i++) {
			tree[n+i]=arr[i];
		}
		for(int i=n-1;i>=1;i--) {
			tree[i]=tree[2*i]+tree[2*i+1];
		}
		
	}

	static long sum_q(int node,int node_low,int node_high,int q_low,int q_high) {
		if(node_low>=q_low&&node_high<=q_high) {
			return tree[node];
		}
		if(node_high<q_low||node_low>q_high) return 0;
		
		int mid= (node_low+node_high)/2;
		
		return sum_q( 2*node,node_low,mid,q_low,q_high)+sum_q( 2*node+1,mid+1,node_high,q_low,q_high);
	}
//	static void update_q(int node,int node_low,int node_high,int q_low,int q_high,int v) {
//		if(node_low>=q_low&&node_high<=q_high) {
//			tree[node]=v;
//			return;
//		}
//		if(node_high<q_low||node_low>q_high) return ;
//		
//		int mid= (node_low+node_high)/2;
//		
//		update_q(node*2,node_low,mid,q_low,q_high,v);
//		update_q(node*2+1,mid+1,node_high,q_low,q_high,v);
//		tree[node]=tree[2*node]+tree[2*node+1];
//	}
	static void update_q(int i,int v) {
		tree[n+i]=v;
		for(int j=(n+i)/2;j>=1;j/=2) {
			tree[j]=tree[j*2]+tree[j*2+1];
		}
	}
}
