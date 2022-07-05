package algorithms;

public class ZFunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int ele:zFunction("azbazbzaz")) {
			System.out.print(ele+" ");
		}
	
	}
	static int[] zFunction(String s) {
		int n=s.length();
		int z[]=new int[n];
		int l=0, r=0;
		for(int i=1;i<n;i++) {
			if(i<=r) {
				z[i]=Math.min(r-i+1, z[i-l]);
			}
			while(i+z[i]<n&&s.charAt(z[i])==s.charAt(i+z[i]))
				++z[i];
			if(i+z[i]-1>r) {
				r=i+z[i]-1;
				l=i;
			}
		}
		return z;
	}

}
