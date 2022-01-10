package dataStructureImplementations;

import java.util.List;

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
	static int find(List<String> list) {
		int n=list.size();
		Subset set[]=new Subset[n];
		init(set,n);
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(list.get(i).charAt(j)=='1') {
					union(set,i,j);
				}
			}
		}
		int ans=0;
		for(int i=0;i<n;i++) {
			if(set[i].parent==i) ans++;
		}
		return ans;
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
