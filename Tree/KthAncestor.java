package Tree;

public class KthAncestor {

	static int up[][];
	static int log;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=50000;
		int p[]= {};// parent array;
		int root=0; /// by doning this we will get root if the value of k is larger than depth
		p[root]=root;
		while(1<<(log)<n) {
			log++;
		}
		up=new int[n][log];
		
	}
	static void build(int p[]) {
		int n=p.length;
		for(int i=0;i<p.length;i++) {
			up[i][0]=p[i];
		}
		for(int j=1;j<log;j++) {
			for(int i=0;i<n;i++) {
				up[i][j]=up[up[i][j-1]][j-1];
			}
		}
	}
	static int kth_ancestor(int node,int k) {
		for(int j=0;j<log;j++) {
			if((k&(1<<j))!=0) {
				node=up[node][j];
			}
		}
		return node;
	}

}
