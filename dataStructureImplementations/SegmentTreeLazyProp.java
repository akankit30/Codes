package dataStructureImplementations;

public class SegmentTreeLazyProp {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=10;
		int l=0,r=n-1;
		ST st=new ST(l,r);
	}
	static class ST {
		int leftmost, rightmost;
		ST lChild, rChild;
		int max, toProp;
		long sum;
		
		public ST(int leftmost, int rightmost) { // we can also pass a array here and if leftmost==rightmost
			                                     // we will do sum=arr[leftmost] else same in if case;
			this.leftmost=leftmost;
			this.rightmost=rightmost;
			if (leftmost!=rightmost) {
				int mid=(leftmost+rightmost)/2;
				lChild=new ST(leftmost, mid);
				rChild=new ST(mid+1, rightmost);
				recalc();
			}
		}
		
		int max() {
			return max+toProp;
		}
		
		void recalc() {
			if (leftmost==rightmost) return;
			max=Math.max(lChild.max(), rChild.max());
		}
		void prop() {
			if (leftmost!=rightmost) {
				lChild.toProp+=toProp;
				rChild.toProp+=toProp;
				toProp=0;
			}
			recalc();
		}
		
		void rangeAdd(int l, int r, int d) {
			if (l>rightmost || r<leftmost) return;
			if (l<=leftmost && r>=rightmost) {
				toProp+=d;
				return;
			}
			prop();
			lChild.rangeAdd(l, r, d);
			rChild.rangeAdd(l, r, d);
			recalc();
		}
		int max(int l, int r) {
			if (l>rightmost || r<leftmost) return Integer.MIN_VALUE;
			if (l<=leftmost && r>=rightmost) {
				return max();
			}
			prop();
			return Math.max(lChild.max(l, r), rChild.max(l, r));
		}
		void pointUpdate(int index,int val) {
			if(leftmost==rightmost) {
				sum=val;
				return;
			}
			if(index<=leftmost) lChild.pointUpdate(index,val);
			else rChild.pointUpdate(index,val);
			recalc();
		}
		long rangeSum(int l,int r) {
			if(l>rightmost||r<leftmost) return 0;
			if(l<=leftmost&&r>=rightmost) return sum;
			return lChild.rangeSum(l, r)+rChild.rangeSum(l, r);
		}
	}

}
