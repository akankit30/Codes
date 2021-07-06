package dataStructureImplementations;

public class Trie {
	static class TrieNode{
		TrieNode [] children=new TrieNode[26];
		boolean isEndOfWord;
		TrieNode() {
			isEndOfWord=false;
			for(int i=0;i<26;i++)
				children[i]=null;
		}
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	static void insert(TrieNode root,String key) {
		TrieNode temp=root;
		for(int i=0;i<key.length();i++) {
			int ind=key.charAt(i)-'a';
			if(temp.children[ind]==null) {
				temp.children[ind]=new TrieNode();
			}
			temp=temp.children[ind];
		}
		temp.isEndOfWord=true;
	}
	static boolean search(TrieNode root,String key) {
		TrieNode temp=root;
		for(int i=0;i<key.length();i++) {
			int ind=key.charAt(i)-'a';
			if(temp.children[ind]!=null)
				temp=temp.children[ind];
			else return false;
		}
		return temp.isEndOfWord;
	}

}
