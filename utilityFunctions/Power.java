package utilityFunctions;

public class Power {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(pow(3,8,1000000007));
	}
	static long pow(long a,long b,int mod) {
		long r=1;
		while(b>0) {
			if(b%2!=0)
				r=(r*a)%mod;
			a=(a*a)%mod;
			b/=2;
		}
		return r;
	}

}
