package dataStructureImplementations;

public class Union {
	static class Subset{
		int rank,parent;
		Subset(){
			rank=0;
			parent=-1;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}
	static void init(Subset[]set ,int n) {
		set=new Subset[n];
		for(int i=0;i<n;i++) {
			set[i]=new Subset();
			set[i].parent=i;
		}
	}
	static void union(Subset[] set,int x,int y) {
		int xroot=find(set,x);
		int yroot=find(set,y);
		if(set[xroot].rank>set[yroot].rank) {
			set[yroot].parent=xroot;
		}
		else if(set[xroot].rank<set[yroot].rank) {
			set[xroot].parent=yroot;
		}
		else {
			set[yroot].parent=xroot;
			set[xroot].rank++;
		}
	}
	static int find(Subset[]set,int i) {
		if(set[i].parent!=i) {
			 set[i].parent=find(set,set[i].parent);
		}
		return set[i].parent;
	}

}
