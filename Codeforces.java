import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.text.DecimalFormat;
import java.util.*;


public class Codeforces {
	static int mod= 1000000007;
	static int rank[], par[];
	static List<Integer> adj[];
	static class Node{
		int u,v,val;
		Node(int u,int v,int val){
			this.v=v;
			this.u=u;
			this.val=val;
		}
	}
	static int temp=0;
	static Map<Integer,HashMap<Integer,Integer>> map;
	static int vst[];
	static int timer=1;
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
		FastScanner fs=new FastScanner();
		int t=fs.nextInt();
		
		outer:while(t-->0) {
			int n=fs.nextInt(), m=fs.nextInt();
			adj=new ArrayList[n];
			vst=new int[n];
			
			for(int i=0;i<n;++i) adj[i]=new ArrayList<>();
			map=new HashMap<>();
//			List<Integer> list=new ArrayList<>();
			while(m-->0) {
				int x=fs.nextInt()-1, y=fs.nextInt()-1,w=fs.nextInt();
				adj[x].add(y);
				adj[y].add(x);
				if(!map.containsKey(x)) map.put(x, new HashMap<>());
				if(!map.containsKey(y)) map.put(y, new HashMap<>());
				map.get(x).put(y, w);
				map.get(y).put(x, w);
			}
			int l=1,r=1000000000;
			while(l<r) {
				int mid=(l+r)/2;
				if(check(n,mid)) {
					r=mid;
				}
				else l=mid+1;
			}
			if(check(n,l-1))
				l=l--;
			out.println(l);
			
			
			
//			System.out.println(check(n,8));
		}
		out.close();
		
	}
	static boolean check(int n,int mid) {
		timer++;
		dfs(0,-1,mid);
		temp=0;
		for(int i=0;i<n;i++) if(vst[i]!=timer) return false;
		return true;
	}
	static void dfs(int cur,int p,int ans) {
		int w=0;
		if(p!=-1) {
			w=map.get(cur).get(p);
		}
		if( (temp|w) > ans ) return;
		
		if(vst[cur]==timer) return;
		temp|=w;
		vst[cur]=timer;
		for(int c:adj[cur]) {
			if(c!=p) dfs(c,cur,ans);
		}
		return ;
	}
	static void union(int x,int y) {
		int px=find(x), py=find(y);
		if(rank[px]<rank[py]) {
			par[px]=py;
		}
		else if(rank[px]>rank[py]) {
			par[py]=px;
		}
		else {
			par[px]=py;
			rank[py]++;
		}
	}
	static int find(int x) {
		if(par[x]==-1) return x;
		return par[x]=find(par[x]);
	}
	
	static long pow(long a,long b) {
		if(b<0) return 1;
		long res=1;
		while(b!=0) {
			if((b&1)!=0) {
				res*=a;
				res%=mod;
			}
			a*=a;
			a%=mod;
			b=b>>1;
		}
		return res;
	}
	static long gcd(long  a,long  b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
	static long nck(int n,int k) {
		if(k>n) return 0;
		long res=1;
		res*=fact(n);
		res%=mod;
		res*=modInv(fact(k));
		res%=mod;
		res*=modInv(fact(n-k)); 
		res%=mod;
		return res;
	}
	static long fact(long n) {
//		return fact[(int)n];
		long res=1;
		for(int i=2;i<=n;i++) {
			res*=i;
			res%=mod;
		}
		return res;
	}
	
	static long modInv(long n) {
		return pow(n,mod-2);
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
 
}