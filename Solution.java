import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Solution {
	static class Customer{
		long c_id;
		float inc, spend;
		Customer(long id, float inc, float spend){
			this.c_id =id;
			this.inc=inc;
			this.spend=spend;
		}
	}
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
//	    FastScanner fs=new FastScanner();
	    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    int n=Integer.parseInt(br.readLine());
	    List<Customer> list = new ArrayList<>();
	    for(int i=0;i<n;i++) {
	    	String arr[]=br.readLine().split("\\s+");
	    	if(arr.length<3) continue;
	    	if(arr[0]==null||arr[1]==null||arr[2]==null) continue;
	    	long id =Long.parseLong(arr[0]);
	    	float inc = Float.parseFloat(arr[1]), spn = Float.parseFloat(arr[2]);
	    	if(inc<0||spn<0) continue;
	    	list.add(new Customer(id,inc,spn));
	    }
	    n=list.size();
	    float dist[]=new float[n];
	    int minind[]=new int[n];
	    for(int i=0;i<n;i++) {
	    	float min = Float.MAX_VALUE;
	    	int cid=0;
	    	for(int j=0;j<n;j++) {
	    		if(j==i) continue;
	    		float cur = dist(list.get(i),list.get(j));
	    		if(cur<min) {
	    			min = cur;
	    			cid = j;
	    		}
	    	}
	    	dist[i]=min;
	    	minind[i]=cid;
	    	
	    }
	    int m=Integer.parseInt(br.readLine());
	    List<Long> ans=new ArrayList<>();
	    for(int i=0;i<m;i++) {
	    	float min = Float.MAX_VALUE;
	    	String arr[]=br.readLine().split("\\s+");
	    	if(arr.length<3) continue;
	    	if(arr[0]==null||arr[1]==null||arr[2]==null) continue;
//	    	out.println("here");
	    	long id =Long.parseLong(arr[0]);
	    	float inc = Float.parseFloat(arr[1]), spn = Float.parseFloat(arr[2]);
	    	if(inc<0||spn<0) continue;
	    	list.add(new Customer(id,inc,spn));
	    	Customer at = new Customer(id,inc,spn);
	    	int cid=0;
	    	for(int j=0;j<n;j++) {
	    		if(j==i) continue;
	    		float cur = dist(at,list.get(j));
	    		if(cur<min) {
	    			min = cur;
	    			cid = j;
	    		}
	    	}
	    	float d = min;
	    	float D = dist[cid];
	    	float ratio = d/D;
	    	
//	    	out.println(d+" "+D);
	    	if(ratio<1.0) {
//	    		out.print("here");
	    		ans.add(at.c_id);
	    	}
	    }
	    Collections.sort(ans);
	    for(long id: ans) out.println(id);
//	    out.print("here");
	    out.close();
	    
    }
	static float dist(Customer a, Customer b) {
		float d= 0;
		float diff=0;
//		diff= (a.c_id-b.c_id);
//		d+= diff*diff;
		diff = a.inc - b.inc;
		d+= diff*diff;
		diff= a.spend-b.spend;
		d+= diff*diff;
		return (float)Math.sqrt(d);
		
	}
	//  [30,20,10,40]  51,33,100,51]
	static int gcd(int a,int b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
    static void sort(int[] a) {
		//suffle
		int n=a.length;
		Random r=new Random();
		for (int i=0; i<a.length; i++) {
			int oi=r.nextInt(n);
			int temp=a[i];
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