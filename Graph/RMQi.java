package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class RMQi {

	static int max=100005;
	static int log=17;
	static int m[][]=new int[max][log];
	static int arr[]=new int[max];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FastScanner fs=new FastScanner();
		int n=fs.nextInt();
		for(int i=0;i<n;i++) {
			arr[i]=fs.nextInt();
			m[i][0]=arr[i];
		}
		for(int j=1;j<log;j++) {
			for(int i=0;i+(1<<j)-1<n;i++) {
				m[i][j]=Math.min(m[i][j-1], m[i+(1<<(j-1))][j-1]);
			}
		}
		int q=fs.nextInt();
		while(q-->0) {
			int l=fs.nextInt(),r=fs.nextInt();
			System.out.println(query(l,r));
		}
	}
    static int query(int l,int r){
    	int len=r-l+1;
    	int j=(int)(Math.log(len)/Math.log(2));
    	return Math.min(m[l][j], m[r-(1<<j)+1][j]);
    }
	static void sort(int[] a) {
		int n=a.length;
		Random r=new Random();
		for (int i=0; i<a.length; i++) {
			int oi=r.nextInt(n), temp=a[i];
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
		double nextDouble() {
			return Double.parseDouble(next());
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
