package Geometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;



public class ConvexHull {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	static class pt{
		double x,y;
		pt(){
			
		}
		pt(double x,double y){
			this.x=x;
			this.y=y;
		}
		pt minus(pt a) {
			return new pt(x-a.x, y-a.y);
		}
		pt add(pt a) {
			return new pt(x+a.x, y+a.y);
		}
		double mag() {
			return Math.sqrt(x*x+y*y);
		}
		
		
	}
	
	
	static double cross(pt a,pt b) {
		return a.x*b.y - a.y*b.x ;
	}
	static double orient(pt a,pt b,pt c){
		return cross(b.minus(a), c.minus(a));
		
		// return +ve if orientation is like 
		
//			c 
//			
//					b
//			a
//		else return -ve if a b c is in clockwise
//	    return 0 if collinear
	}
	static boolean cw(pt a,pt b,pt c,boolean include_collinear) {
		double o=orient(a,b,c);
		return o<0 || (include_collinear && o==0);
	}
	static boolean ccw(pt a,pt b,pt c ,boolean include_collinear) {
		double o=orient(a,b,c);
		return o>0 || (include_collinear && o==0);
	}
	static void convexHull(pt [] a,boolean include_collinear){
		
		 if(a.length==1) return ;
		 Arrays.sort(a,new Comparator<pt>() {
			 public int compare(pt a,pt b) {
				 if(a.x==b.x) {
					 if(a.y==b.y)return 0; 
					 return a.y-b.y >0 ? 1 : -1;
				 }
				 return a.x -b.x>0? 1:-1;
			 }
		 });
		 pt p1= a[0], p2=a[a.length-1];
		 
		 Stack<pt> up=new Stack<>() , down=new Stack<>();
		 
		 up.push(p1);
		 down.push(p1);
		 
		 for (int i = 1; i < a.length; i++) {
		        if (i == a.length - 1 || cw(p1, a[i], p2, include_collinear)) {
		            while (up.size() >= 2 ) {
		            	pt top=up.pop();
		            	pt second=up.peek();
		            	if(cw(second, top, a[i], include_collinear)) {
		            		up.push(top);
		            		break;
		            	}
		            	
		            	// if not we kept it out
		            }
		            up.push(a[i]);
		        }
		        if (i == a.length - 1 || ccw(p1, a[i], p2, include_collinear)) {
		            while (down.size() >= 2 ) {
		            	pt top=down.pop();
		            	pt second=down.peek();
		            	if(ccw(second, top, a[i], include_collinear)) {
		            		down.push(top);
		            		break;
		            	}
		            	
		            	// if not we kept it out
		            }
		            down.push(a[i]);
		        }
		    }
		 
		 
		 // now up will contain the upper convex hull and down will contain the
		 // lower convex hull
		 
	}

}
