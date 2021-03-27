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
		
		a.clear(); b.clear(); c.clear();
		System.out.println("Add times");
		System.out.println(" LL: " + testInsertTime(a));
		System.out.println(" AL: " + testInsertTime(b));
		System.out.println("LAL: " + testInsertTime(c));

		
		Random indexOfRandom = new Random(271);
		int tries = 100;
		int correctNum = 0;
		for(int i = 0; i < tries; i++)
		{
			int value = b.get(indexOfRandom.nextInt(b.size()));
			int ai = a.indexOf(value);
			int bi = b.indexOf(value);
			int ci = c.indexOf(value);
			correctNum += (ai == bi && bi == ci) ? 1 : 0;
		}
		System.out.printf("IndexOf: %d/%d%n", correctNum, tries);

		
		a.clear(); b.clear(); c.clear();
		for(int i = 0; i < 10; i++)
		{
			a.add(i);
			b.add(i);
			c.add(i);
		}
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		a.remove(1);
		a.remove(4);
		a.remove(6);
		
		b.remove(1);
		b.remove(4);
		b.remove(6);
		
		c.remove(1);
		c.remove(4);
		c.remove(6);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);

		int randomRemoveTries = 5;
		int randomRemoveInsertAmount = 100;
		int randomRemoveAmount = 10;
		int randomRemoveTestCount = 0;
		for(int i = 0; i < randomRemoveTries; i++)
		{
			ArrayList<Integer> listA = new ArrayList<Integer>();
			LinkedList<Integer> listB = new LinkedList<Integer>();
			LinkedArrayList<Integer> listC = new LinkedArrayList<Integer>();
			Random r = new Random(271 + i);
			for(int j = 0; j < randomRemoveInsertAmount; j++)
			{
				int value = r.nextInt();
				listA.add(value);
				listB.add(value);
				listC.add(value);
			}
			for(int j = 0; j < randomRemoveAmount; j++)
			{
				int index = r.nextInt(listB.size());
				listA.remove(index);
				listB.remove(index);
				listC.remove(index);
				if(testEqualListString(listA, listB) && testEqualListString(listB, listC))
				{
					randomRemoveTestCount++;
				}
			}
		}
		System.out.printf("Random remove: %d/%d%n", randomRemoveTestCount, randomRemoveTries * randomRemoveAmount);

		System.out.println("Random time testing");
		a.clear(); b.clear(); c.clear();
		System.out.println(" LL: " + randomTest(a));
		System.out.println(" AL: " + randomTest(b));
		System.out.println("LAL: " + randomTest(c));

	}

	public static boolean testEqualListString(List l1, List l2)
	{
		return l1.toString().equals(l2.toString());
	}

	public static long testInsertTime(List l)
	{
		long time = 0;
		int n = 10;
		int n2 = 1_000;
		for(int i = 0; i < n; i++)
		{
			Random r = new Random(271+i);
			l.clear();
			long t1 = System.currentTimeMillis();
			for(int j = 0; j < n2; j++)
			{
				l.add(r.nextInt());
			}
			time += System.currentTimeMillis() - t1;
		}
		return time/n;
	}

	public static long randomTest(List l)
	{
		long time = 0;
		int n = 2;
		int ins = 10_000;
		int rm = 9_000;
		for(int i = 0; i < n; i++)
		{
			Random r = new Random(271+i);
			l.clear();
			long t1 = System.currentTimeMillis();
			for(int j = 0; j < ins; j++)
			{
				l.add(r.nextInt());
			}
			for(int j = 0; j < rm; j++)
			{
				l.remove(r.nextInt(l.size()));
			}
			time += System.currentTimeMillis() - t1;
		}
		return time/n;
	}
}