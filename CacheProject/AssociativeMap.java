package CacheProject;

import java.util.*;

class Block2
{
	String Tag;
	int Start = 1;
	int[] offsets;
	public Block2(int N)
	{
		this.offsets = new int[N];
	}
	
}
public class AssociativeMap {
	
	static Scanner scan = new Scanner(System.in);
	static int S = scan.nextInt();
	static int CL = scan.nextInt();
	static int B = scan.nextInt();
	static int offsetBit = Integer.toBinaryString(B-1).length();
	static int Tag = 16-offsetBit;
	static ArrayList<Block2> Cache = new ArrayList<Block2>();
	
	
	public static void Write(String Add,int Data,String offset)
	{
		
		int Word =Integer.parseInt(offset,2);
		boolean Hit = false;
		int Index = 0;
		for(int i=0;i<Cache.size();i++)
		{if(Cache.get(i).Tag.equals(Add.substring(0,Tag))){Hit=true;Index=i;break;}}
		
		if(!Hit)
		{
			if(Cache.size()<CL)
			{
				Block2 b = new Block2(B);
				b.Tag = Add.substring(0,Tag);
				b.offsets[Word] = Data;
				Cache.add(b);
			}
			else
			{
				Block2 b = new Block2(B);
				b.Tag = Add.substring(0,Tag);
				b.offsets[Word] = Data;
				Cache.remove(0);
				Cache.add(b);
			}
		}
		else
		{
			Cache.get(Index).offsets[Word] = Data;
		}	
	}
	
	public static void Read(String Add, String offset)
	{
		boolean Hit=false;
		int Word = Integer.parseInt(offset,2);
		int Index = 0;
		for(int i=0;i<Cache.size();i++)
		{if(Cache.get(i).Tag.equals(Add.substring(0,Tag))){Hit=true;Index=i;break;}}
		if(!Hit)
		{
			if(Cache.size()<CL)
			{
				Block2 b = new Block2(B);
				b.Tag = Add.substring(0,Tag);
				Cache.add(b);
			}
			else
			{
				Block2 b = new Block2(B);
				b.Tag = Add.substring(0,Tag);
				Cache.remove(0);
				Cache.add(b);
			}
			System.out.println("Address not found");
		}
		else
		{
			int Val = Cache.get(Index).offsets[Word];
			System.out.println("Address : "+Add+" Data : "+Val);
		}
		
	}
	
	public static void main(String[] args) 
	{
		//System.out.println("Offset Bit : "+offsetBit);
		//System.out.println("Tag Bit : "+Tag);
		int Test = scan.nextInt();
		for(int i=0;i<Test;i++)
		{
			String Type = scan.next().toLowerCase();
			if(Type.equals("write"))
			{
				String Address = scan.next();
				String offset = Address.substring(16-offsetBit,16);
				int Data = scan.nextInt();
				Write(Address,Data,offset);
			}
			else
			{
				String Address = scan.next();
				String offset = Address.substring(16-offsetBit,16);
				Read(Address,offset);
			}
		}
		scan.close();
	}

}
