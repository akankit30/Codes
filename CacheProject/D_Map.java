package CacheProject;
import java.util.*;

class Block
{
	int Value;
	int Start = 1;
	String Tag;
	String Middle;
	int[] offsets;
	public Block(int N)
	{
		this.offsets = new int[N];
	}
}

public class D_Map 
{
	static Scanner scan = new Scanner(System.in);
	static int S = scan.nextInt();
	static int CL = scan.nextInt();
	static int B = scan.nextInt();
	static int offsetBit = Integer.toBinaryString(B-1).length();
	static int Tag = 16 - Integer.toBinaryString(CL-1).length() - offsetBit;
	
	static Block[] Cache = new Block[CL];
	static int Val = 0;
	
	public static void Write(String Add,int Data,String offset)
	{
		int MapIndex = Integer.parseInt(Add.substring(0,16-offset.length()),2) % CL;
		int Word = Integer.parseInt(offset,2);
		if(Cache[MapIndex].Start==1)
		{
			Cache[MapIndex].offsets[Word] = Data;
			Cache[MapIndex].Middle = Add.substring(Tag,16-offsetBit);
			Cache[MapIndex].Tag = Add.substring(0,Tag);	 
			Cache[MapIndex].Start = 0;
		}
		else
		{
			if(Cache[MapIndex].Tag.equals(Add.substring(0,Tag))) Cache[MapIndex].offsets[Word] = Data;
			else
			{
				Block b = new Block(B);
				b.offsets[Word] = Data;
				b.Middle = Add.substring(Tag,16-offsetBit);
				b.Tag = Add.substring(0,Tag);
				b.Start = 0;
				Cache[MapIndex] = b;
			}
		}
		
		
	}
	
	public static boolean Read(String Add,String offset)
	{
		int MapIndex = Integer.parseInt(Add.substring(0,16-offsetBit),2) % CL;
		boolean found = false;
		int Word = Integer.parseInt(offset,2);
		if(Cache[MapIndex].Start==1)found = false;
		else
		{
			if(Cache[MapIndex].Tag.equals(Add.substring(0,Tag))) 
			{ 
				found=true; Val = Cache[MapIndex].offsets[Word];
			}
			else
			{
				found = false;
				Block b = new Block(B);
				b.Tag = Add.substring(0,Tag);
				b.Middle = Add.substring(Tag,16-offsetBit);
				b.Start = 0;
				Cache[MapIndex] = b;
			}
		}
		return found;
	}
	
	public static void main(String[] args)
	{
		
	
		int N = scan.nextInt();
		
		for(int i=0;i<CL;i++) Cache[i] = new Block(B);
		//System.out.println("Tag Bit : "+Tag);
		//System.out.println("Offset bit : "+offsetBit);
		for(int i=0;i<N;i++)
		{
			String Type = scan.next();
			Type = Type.toLowerCase();
			if(Type.equals("write"))
			{
				String Address = scan.next();
				String offset = Address.substring(16-offsetBit,16);
				//System.out.println("Offset address : "+offset);
				int Data = scan.nextInt();
				Write(Address,Data,offset);
			}
			else
			{
				String Address = scan.next();
				String offset = Address.substring(16-offsetBit,16);
				if(Read(Address,offset))System.out.println("Address : "+Address+" Data : "+Val);
				else System.out.println("Address not found");
			}
		}
		
//		System.out.println("Address : "+Address);
//		System.out.println("First 14 bits of PA : "+Address.substring(0,14));
//		System.out.println("MAPING CACHE LINE : "+(Integer.parseInt(Address.substring(0,14),2)%CL));
//		System.out.println("Value of Age ki 14 bit : "+Integer.parseInt(Address.substring(0,14),2));
//		System.out.println("Value of peche ki 2 bit : "+Integer.parseInt(Address.substring(14,16),2));
//		int Blocks = (int)Math.pow(2, 16-Integer.toBinaryString(B-1).length());
//		System.out.println("NO of Blocks in MaIn MeMoRy : "+Blocks);
		scan.close();
	}
}