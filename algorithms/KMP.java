package algorithms;

import java.util.*;

public class KMP {
	static int lps[];
	static List<Integer> list=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	static void KMPSearch(String pat, String text) {
		int n= text.length(), m = pat.length();
		lps=new int[m];
		computeLPS(pat,m);
		int i=0, j=0;
		while(i<n) {
			if(pat.charAt(j)==text.charAt(i)) {
				i++; j++;
			}
			if(j==m) {
				list.add(i-j);
				j=lps[j-1];
			}
			else if(i<n&&pat.charAt(j)!=text.charAt(i)) {
				if(j!=0) {
					j=lps[j-1];
				}
				else i++;
			}
		}
	}
	static void computeLPS(String pat, int m) {
		int len=0;
		lps[0]=0;
		int i=1;
		while(i<m) {
			if(pat.charAt(i)==pat.charAt(len)) {
				len++;
				lps[i]=len;
				i++;
			}
			else {
				if(len!=0) len= lps[len-1];
				else {  /// len == 0;
					lps[i]=len;
					i++;
				}
			}
			
		}
		
	}
}
