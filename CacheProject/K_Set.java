package CacheProject;
import java.util.*;
class Block3
{
	String Tag;
	String set;
	String off;
	int[] offsets;
	public Block3(int N)
	{
		this.offsets = new int[N]; 
	}
}

public class K_Set 
{
	static Scanner scan = new Scanner(System.in);
	static int S = scan.nextInt();
	static int CL = scan.nextInt();
	static int B = scan.nextInt();
	static int n = scan.nextInt();
	static int Set = (int)Math.pow(2, n);
	static int CLS = CL/Set;
	static int offsetBit = Integer.toBinaryString(B-1).length();
	static int Tag = 16 - Integer.toBinaryString(Set-1).length() - offsetBit;
	
	static ArrayList<ArrayList<Block3>> Cache = new ArrayList<ArrayList<Block3>>();
	
	
	public static void Write(String Add,int Data,String offset)
	{
		int SetNo = Integer.parseInt(Add.substring(0,16-offsetBit),2) % Set;
		int Word = Integer.parseInt(offset, 2);
		boolean Hit = false;
		int idx = 0;
		for(int i=0;i<Cache.get(SetNo).size();i++)
		{
			if(Cache.get(SetNo).get(i).Tag.equals(Add.substring(0,Tag)))
			{Hit = true; idx = i; break;}
		}
		
		if(!Hit)
		{
			if(Cache.get(SetNo).size()<CLS)
			{
				Block3 b = new Block3(B);
				b.Tag = Add.substring(0,Tag);
				b.off = offset;
				b.offsets[Word] = Data;
				b.set = Add.substring(Tag,16-offsetBit);
				Cache.get(SetNo).add(b);
			}
			else
			{
				Block3 b = new Block3(B);
				Cache.get(SetNo).remove(0);
				b.Tag = Add.substring(0,Tag);
				b.off = offset;
				b.offsets[Word] = Data;
				b.set = Add.substring(Tag,16-offsetBit);
				Cache.get(SetNo).add(b);
			}
		}
		else
		{
			Cache.get(SetNo).get(idx).offsets[Word] = Data;
		}
	}
	
	public static void Read(String Add,String offset)
	{
		int SetNo = Integer.parseInt(Add.substring(0,16-offsetBit),2) % Set;
		int Word = Integer.parseInt(offset, 2);
		boolean Hit = false;
		int idx = 0;
		for(int i=0;i<Cache.get(SetNo).size();i++)
		{
			if(Cache.get(SetNo).get(i).Tag.equals(Add.substring(0,Tag)))
			{Hit = true; idx = i; break;}
		}
		
		if(!Hit)
		{
			if(Cache.get(SetNo).size()<CLS)
			{
				Block3 b = new Block3(B);
				b.Tag = Add.substring(0,Tag);
				b.off = offset;
				b.set = Add.substring(Tag,16-offsetBit);
				Cache.get(SetNo).add(b);
			}
			else
			{
				Block3 b = new Block3(B);
				Cache.get(SetNo).remove(0);
				b.Tag = Add.substring(0,Tag);
				b.off = offset;
				b.set = Add.substring(Tag,16-offsetBit);
				Cache.get(SetNo).add(b);
			}
			System.out.println("Address not found");
		}
		else
		{
			System.out.println("Address : "+Add+" data : "+Cache.get(SetNo).get(idx).offsets[Word]);
		}
	}
	public static void main(String[] args)
	{

		for(int i=0;i<Set;i++)
		{
			ArrayList<Block3> k = new ArrayList<Block3>();
			Cache.add(k);
		}
		
		int Test = scan.nextInt();
		for(int i=0;i<Test;i++)
		{
			String Type = scan.next().toLowerCase();
			if(Type.equals("write"))
			{
				String Add = scan.next();
				int Data = scan.nextInt();
				String offset = Add.substring(16-offsetBit,16);
				Write(Add,Data,offset);
			}
			else
			{
				String Add = scan.next();
				String offset = Add.substring(16-offsetBit,16);
				Read(Add,offset);
			}
			
		}
		
	}
	
}
