package introduction;

import java.io.*;
import java.util.*;

public class HashCode {

	static class Node{
	    boolean isRed;
	    int greentime=0;
	    List<Integer> neighbour;
		Node(){
			this.isRed=false;
			neighbour=new ArrayList<>();
		}
	}
	static class Car{
		int st,end,atPresent,remainingtime,next;
		Car(int st,int end,int remainingtime){
			this.st=st;
			this.end=end;
			this.remainingtime=remainingtime;
		}
	}
	static class Path{
		int st,end;
		Queue<Car> q;
		Path(int st,int end){
			this.st=st;
			this.end=end;
			q=new LinkedList<>();
		}
	}
	static class Street{
		int st,end,time;
		String name;
		Street(int st,int end,int time,String name){
			this.st=st;
			this.end=end;
			this.time=time;
			this.name=name;
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FastScanner fs=new FastScanner();
		int d=fs.nextInt();  // duration
		int intersections=fs.nextInt();  // no of intersections
		int s=fs.nextInt(); /// no of street
		int v=fs.nextInt();  /// no of vehicles
		int f=fs.nextInt(); // bonus point;
		Map<String,Street> map=new HashMap<>();
		
		for(int i=0;i<s;i++) {
			Street temp=new Street(0,0,0,"");
			temp.st=fs.nextInt();
			temp.end=fs.nextInt();
			temp.name=fs.next();
			temp.time=fs.nextInt();
			map.put(temp.name, temp);
		}
		Car[] cars=new Car[v];
		for(int i=0;i<v;i++) {
			Car temp=new Car(0,0,0);
			cars[i]=temp;
			int roads=fs.nextInt();
			int time=0;
			for(int j=0;j<roads;j++) {
				Street here=map.get(fs.next());
				if(j==0) {
					cars[i].st=here.st;
				}
				if(j==roads-1) {
					cars[i].end=here.end;
				}
				time+=here.time;
				
			}
			cars[i].remainingtime=time;
		}
		printOut();
	}
//	3
//	1
//	2
//	rue-d-athenes 2
//	rue-d-amsterdam 1
//	0
//	1
//	rue-de-londres 2
//	2
//	1
//	rue-de-moscou 1

	public static void printOut() {
		PrintWriter writer= null;
		try {
			writer=new PrintWriter("H:\\Output","UTF-8");
			writer.println(3);
			writer.println(1);
			writer.println(2);
			writer.println("rue-d-athenes "+1);
			writer.println(0);
			writer.println(1);
			writer.println("rue-de-londres "+2);
			writer.println(2);
			writer.println(1);
			writer.println("rue-de-moscou "+1);
			writer.close();
			
		}catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	}
	static class FastScanner {
		BufferedReader br;    
		FastScanner(){
			try {
				br=new BufferedReader(new FileReader("C:\\Users\\hp\\Downloads\\" + "a.txt"),(int)8*1024);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
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
