import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class Tester 
{
	public static void main(String[] args)
	{
		System.out.println("Test");
		ArrayList<Integer> a = new ArrayList<Integer>();
		LinkedList<Integer> b = new LinkedList<Integer>();
		LinkedArrayList<Integer> c = new LinkedArrayList<Integer>();
		for(int i = 0; i < 10; i++)
		{
			a.add(i);
			b.add(i);
			c.add(i);
		}

		for(int i = 0; i < 10; i++)
		{
			System.out.print(a.get(i));
		}
		System.out.println();
		System.out.println(a.size());
		for(int i = 0; i < 10; i++)
		{
			System.out.print(b.get(i));
		}
		System.out.println();
		System.out.println(b.size());
		for(int i = 0; i < 10; i++)
		{
			System.out.print(c.get(i));
		}
		System.out.println();
		System.out.println(c.size());

		System.out.println(a.toString());
		System.out.println(b.toString());
		System.out.println(c.toString());

		if(testEqualListString(a, b) && testEqualListString(b, c))
		{
			System.out.println("Equal");
		}
		else
		{
			System.out.println("Not Equal");
		}

		int randomAddTestNum = 50;
		int randomAddTestAddCount = 10_000;
		int randomAddValidNum = 0;
		for(int i = 0; i < randomAddTestNum; i++)
		{
			Random r = new Random(271 + i);
			ArrayList<Integer> listA = new ArrayList<Integer>();
			LinkedList<Integer> listB = new LinkedList<Integer>();
			LinkedArrayList<Integer> listC = new LinkedArrayList<Integer>();
			for(int j = 0; j < randomAddTestAddCount; j++)
			{
				int num = r.nextInt();
				listA.add(num);
				listB.add(num);
				listC.add(num);
			}
			if(testEqualListString(listA, listB) && testEqualListString(listB, listC))
			{
				randomAddValidNum++;
			}
		}
		System.out.printf("Random add %d/%d%n", randomAddValidNum, randomAddTestNum);

		a.clear();
		b.clear();
		c.clear();
		for(int i = 0; i < 10; i++)
		{
			a.add(0, i);
			b.add(0, i);
			c.add(0, i);
		}
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);

		int randomInsertTestNum = 100;
		int randomInsertTestInsertCount = 1_000;
		int randomInsertValidNum = 0;
		for(int i = 0; i < randomInsertTestNum; i++)
		{
			Random r = new Random(271 + i);
			ArrayList<Integer> listA = new ArrayList<Integer>();
			LinkedList<Integer> listB = new LinkedList<Integer>();
			LinkedArrayList<Integer> listC = new LinkedArrayList<Integer>();
			for(int j = 0; j < randomInsertTestInsertCount; j++)
			{
				int num = r.nextInt();
				int size = listB.size();
				int index = size == 0 ? 0 : r.nextInt(listB.size());
				listA.add(index, num);
				listB.add(index, num);
				listC.add(index, num);
			}
			if(testEqualListString(listA, listB) && testEqualListString(listB, listC))
			{
				randomInsertValidNum++;
			}
		}
		System.out.printf("Random insert test %d/%d%n", randomInsertValidNum, randomInsertTestNum);
		
		System.out.println("Add times");
		System.out.println(testInsertTime(new LinkedList<Integer>()));
		System.out.println(testInsertTime(new ArrayList<Integer>()));
		System.out.println(testInsertTime(new LinkedArrayList<Integer>()));
	}

	public static boolean testEqualListString(List l1, List l2)
	{
		return l1.toString().equals(l2.toString());
	}

	public static long testInsertTime(List l)
	{
		long time = 0;
		int n = 20;
		int n2 = 1_000_000;
		for(int i = 0; i < n; i++)
		{
			Random r = new Random(271+i);
			long t1 = System.currentTimeMillis();
			l.clear();
			for(int j = 0; j < n2; j++)
			{
				l.add(r.nextInt());
			}
			time += System.currentTimeMillis() - t1;
		}
		return time/n;
	}
}