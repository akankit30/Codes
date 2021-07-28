package dataStructureImplementations;

public class SparseTable {
	static int mat[][];
	static int log[];
	static int max;
	static int k;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		max=100000; //size of array;
		callog();
		k=log[max];
		
		

	}
	static void build(int arr[]) {
		mat=new int[max][k+1];
		for(int i=0;i<arr.length;i++) {
			mat[i][0]=arr[i];
		}
		for(int j=1;j<=k;j++) {
			for(int i=0;i+(1<<j)<=max;i++) {
				mat[i][j]=Math.min(mat[i][j-1], mat[i+(1<<(j-1))][j-1]);
			}
		}
	}
	static void callog() {
		log=new int[max+1];
		log[1]=0;
		for(int i=2;i<=max;i++) {
			log[i]=log[i/2]+1;
		}
	}
	static int min_query(int l,int r) {
		int j=log[r-l+1];
		return Math.min(mat[l][j], mat[r-(1<<j)+1][j]);
	}
	static long sum_query(int l,int r) {
		long sum=0;
		for(int j=k;j>=0;j--) {
			if((1<<j)<=r-l+1) {
				sum+=mat[l][j];
				l+=(1<<j);
			}
		}
		return sum;
	}
}
