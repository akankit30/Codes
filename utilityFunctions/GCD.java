package utilityFunctions;

import java.util.*;

public class GCD {
	public static void main(String []args) {
		System.out.println(gcd(Integer.MAX_VALUE,Integer.MAX_VALUE));
		System.out.println(fastgcd(Integer.MAX_VALUE,Integer.MAX_VALUE));
	}
	
	static int gcd(int a,int b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
	static int fastgcd(int a,int b) {
		if(a==0||b==0) return a|b;
		int shift = Integer.numberOfTrailingZeros(a | b);
	    a >>= Integer.numberOfTrailingZeros(a);
	    do {
	        b >>= Integer.numberOfTrailingZeros(b);
	        if (a > b) {
	           int temp=a;
	           a=b;
	           b=temp;
	        }
	            
	        b -= a;
	    } while (b!=0);
	    return a << shift;
	}
}
