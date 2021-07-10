import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public class B {
	static class Node{
		int freq[]=new int[10];
		int dur[]=new int[10];
		String [] recent=new String[10];
		Node() {}
	}
	static int mod =1000000007; 
	public static void main(String[] args) throws Exception {
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
	    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    String a= "2020-02-03 04:22:25";
	    String b="2020-02-03 04:20:25";
	    System.out.println(diff(a,b));
	    int m=Integer.parseInt(br.readLine());
	    Map<String,Node> map=new HashMap<>();
	    Map<String,Integer> pind=new HashMap<>();
	    pind.put("offers",0);
	    pind.put("rewards", 1);
	    pind.put("dispute", 1);
	    pind.put("flights", 1);
	    pind.put("referral", 1);
	    pind.put("payment", 1);
	    pind.put("hotels", 1);
	    pind.put("statement", 1);
	    pind.put("change_pin", 1);
	    pind.put("transaction", 1);
	    
	    for(int i=0;i<m;i++) {
	    	String[] in =br.readLine().split(",");
	    	String cid= in[0], page=in[1], st=in[2],end=in[3];
	    	if(!map.containsKey(cid)) map.put(cid, new Node());
	    	
	    }
	    out.close();
	}
	static int diff(String a,String b) {
		LocalDateTime date1 = LocalDateTime.of(Integer.parseInt(a.substring(0,4)),
				Integer.parseInt(a.substring(5,7)),
				Integer.parseInt(a.substring(8,10)),
				Integer.parseInt(a.substring(11,13)),
				Integer.parseInt(a.substring(14,16)),
				Integer.parseInt(a.substring(17)));
		
		LocalDateTime date2 = LocalDateTime.of(Integer.parseInt(b.substring(0,4)),
				Integer.parseInt(b.substring(5,7)),
				Integer.parseInt(b.substring(8,10)),
				Integer.parseInt(b.substring(11,13)),
				Integer.parseInt(b.substring(14,16)),
				Integer.parseInt(b.substring(17)));
		int diff = date1.compareTo(date2);
		int ad  = date1.getNano();
		int bd= date2.getNano();
        return ad-bd;
	}
	// 0123456789101112131415161718
	// yyyy-mm-dd  H H - M M : s s
	static int compare(String a,String b) {
		
		LocalDateTime date1 = LocalDateTime.of(Integer.parseInt(a.substring(0,5)),
				Integer.parseInt(a.substring(5,7)),
				Integer.parseInt(a.substring(8,10)),
				Integer.parseInt(a.substring(11,13)),
				Integer.parseInt(a.substring(14,16)),
				Integer.parseInt(a.substring(17)));
		
		LocalDateTime date2 = LocalDateTime.of(Integer.parseInt(b.substring(0,5)),
				Integer.parseInt(b.substring(5,7)),
				Integer.parseInt(b.substring(8,10)),
				Integer.parseInt(b.substring(11,13)),
				Integer.parseInt(b.substring(14,16)),
				Integer.parseInt(b.substring(17)));

        // isAfter() method
        if(date1.isAfter(date2)) {
            return +1;
        }

        // isBefore() method
        if(date1.isBefore(date2)) {
            return -1;
        }

        // isEqual() method
        return 0;
        // compareTo() method
        
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
		long res=1;
		for(int i=2;i<=n;i++) {
			res*=i;
			res%=mod;
		}
		return res;
	}
	static long pow(long a,long b) {
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