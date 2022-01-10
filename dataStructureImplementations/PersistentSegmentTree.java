package dataStructureImplementations;


public class PersistentSegmentTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=10; // size of array
		int arr[]=new int[n];
		ST st[]=new ST[n+1];
		
		st[0]=new ST(0,n-1);
		for(int i=1;i<=n;i++) {
			st[i]=st[i-1].pointUpdate(arr[i], 1); // it mean we are adding elements one by one 
			                                      // in a seperate segment tree.
		}
	}
	static class ST {
		int leftmost, rightmost;
		ST lChild, rChild;
		int sum;
		public ST(ST cur) {
			this.leftmost=cur.leftmost;
			this.rightmost=cur.rightmost;
			this.lChild=cur.lChild;
			this.rChild=cur.rChild;
			
		}
		public ST(int leftmost, int rightmost) { 
			this.leftmost=leftmost;
			this.rightmost=rightmost;
			if (leftmost!=rightmost) {
				int mid=(leftmost+rightmost)/2;
				lChild=new ST(leftmost, mid);
				rChild=new ST(mid+1, rightmost);
				recalc();
			}
		}
		
		void recalc() {
			if (leftmost==rightmost) return;
			sum=lChild.sum+rChild.sum;
		}
		
		ST pointUpdate(int index,int val) {
			if(leftmost==rightmost) {
				ST cur=new ST(index,index);
				cur.sum+=1;
				return cur;
			}
			ST cur=new ST(this);
			
			int mid=(leftmost+rightmost)/2;
			if(index<=mid) {
				cur.lChild=lChild.pointUpdate(index,val);
			}
			else cur.rChild=rChild.pointUpdate(index,val);
			cur.recalc();
			return cur;
		}
		int rangeSum(int l,int r) {
			if(l>rightmost||r<leftmost) return 0;
			if(l<=leftmost&&r>=rightmost) return sum;
			return lChild.rangeSum(l, r)+rChild.rangeSum(l, r);
		}
	}

}
