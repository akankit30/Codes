package Geometry;

import java.util.*;



public class geometry {
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
	static pt perp(pt p) {
		return new pt(-p.y, p.x);
	}
	static boolean isUhalf(pt a) {
		// return true if angle is b/w (0 , pi];
		return a.y>0 || a.y==0 && a.x<0 ;
	}
	static void polarSort(List<pt> list) {
		
		Collections.sort(list,new Comparator<pt>() {
			// this will assume angle as (-pi, 0] (0, pi]  and will sort in increasing order of
			// angle
			public int compare(pt a, pt b) {
				boolean ainu=isUhalf(a), binu= isUhalf(b);
				if(ainu!=binu) {
					if(ainu) return 1;
					else return -1;
				}
				// +1 will put b before a
				if(cross(a,b)==0.0) return 0;
				
				return cross(a,b)>0 ? -1 : +1;
			}
		});
	}
	static double cross(pt a,pt b) {
		return a.x*b.y - a.y*b.x ;
	}
	static double dot(pt a, pt b) {
		return a.x*b.x + a.y*b.y;
	}
	
	static double angle(pt a, pt b) {
		double costheta= dot(a,b)/a.mag()/b.mag();
		return Math.acos(Math.max(-1.0, Math.min(1.0, costheta)));
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
	
	static class line{
		pt v;
		double c;
		// From direction vector v and offset c
		line(pt v, double c) {
		   this.v=v;
		   this.c=c;
		}
		// From equation ax+by=c
		line(double a, double b, double c) {
			v=new pt(b,-a);
			this.c=c;
		}
		// From points P and Q
		line(pt p, pt q) {
			v=q.minus(p);
			c=cross(v,p);
		}
		double side(pt p) {
//			give information about which side of line is the point p is
//			return -ve value if the point is on its left side
//			return +ve value if the point is on its right side
			
			return cross(v,p)-c;
		}
		double dist(pt p) {
//			return perpendicular distance of p from the line
			return Math.abs(side(p))/p.mag();
		}
		line perpThrough(pt p) {
			return new line(p, p.add(perp(v)));
		}
		
		/*
		 * if we have to sort points on a line according to there occurrence on line (in direction on line)
		 * we can use the fact that if point a comes before point b on line vector v then
		 *    v.a < v.b 
		 *    this fact will also work for projection of point on line
		 *    like we can also compare projection of points 
		 */
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
