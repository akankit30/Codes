import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Solution {
	static long tree[];
	static int n;
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
//	    int max=100000;
	    int t = fs.nextInt(); 
	    for(int time=1;time<=t;time++) {
	    	Map<Long,Integer> map=new TreeMap<>();
	    	int n=fs.nextInt();
	    	long cuts = fs.nextLong();
	    	for(int i=0;i<n;i++) {
	    		long l=fs.nextLong(), r=fs.nextLong();
	    		map.put(l+1,map.getOrDefault(l+1,0)+1);
	    		map.put(r, map.getOrDefault(r, 0)-1);
	    	}
	    	long arr[]=new long[n+1];
	    	int j=0;
	    	long prev=0;
	    	for(Map.Entry<Long, Integer> e: map.entrySet()) {
	    		long cur = e.getKey();
//	    		System.out.println(cur);
	    		arr[j]+=(cur - prev);
	    		prev=cur;
	    		j+=e.getValue();
	    	}
	    	long ans=n;
	    	for(int i=n;i>0;i--) {
	    		ans += Math.min(cuts, arr[i])*(long)i;
	    		cuts-=Math.min(cuts, arr[i]);
	    	}
	    	
	    	out.println("Case #" + time + ": "+ans );
	    	
	    }
	    out.close();
	    
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

	static void update_q(int i,int v) {
		tree[n+i]=v;
		for(int j=(n+i)/2;j>=1;j/=2) {
			tree[j]=tree[j*2]+tree[j*2+1];
		}
	}
	static int gcd(int a,int b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
    static void sort(long[] a) {
		//suffle
		int n=a.length;
		Random r=new Random();
		for (int i=0; i<a.length; i++) {
			int oi=r.nextInt(n);
			long temp=a[i];
			a[i]=a[oi];
			a[oi]=temp;
		}
		
		//then sort
		Arrays.sort(a);
	}
	// Use this to input code since it is faster than a Scanner
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
		int[] readArray(int n) {
			int[] a=new int[n];
			for (int i=0; i<n; i++) a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
	}
 
}