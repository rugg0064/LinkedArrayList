import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedArrayList<E> implements List<E>
{
    int nextIndex;
    int nextSector;
    int nextLocalIndex;
    ArrayList<Object[]> sectors;
    LinkedList<Integer> capacities;
    public LinkedArrayList()
    {
        sectors = new ArrayList<Object[]>();
        sectors.add(new Object[1]);

        capacities = new LinkedList<Integer>();
        capacities.add(1);

        nextIndex = 0;
        nextSector = 0;
        nextLocalIndex = 0;
    }

    public boolean add(E element)
    {
        ensureCapacity();
        sectors.get(nextSector)[nextLocalIndex] = element;
        nextIndex++;
        nextLocalIndex++;
        return true;
    }

    public void add(int index, E element)
    {
        if(index >= size())
        {
            add(element);
            return;
        }
        
        ensureCapacity();
        int tempIndex = nextIndex;
        
        while(tempIndex > index)
        {
            sectors.get(getSectorIndex(tempIndex))[getLocalIndex(tempIndex)] 
                = sectors.get(getSectorIndex(tempIndex-1))[getLocalIndex(tempIndex-1)];
            tempIndex--;
        }
        sectors.get(getSectorIndex(index))[getLocalIndex(index)] = element;
        nextLocalIndex++;
        nextIndex++;
    }

    public boolean addAll(Collection<? extends E> c)
    {
        return false;
    }

    public boolean addAll(int index, Collection<? extends E> c)
    {
        return false;
    }

    public void clear()
    {
        sectors = new ArrayList<Object[]>();
        sectors.add(new Object[1]);
        nextIndex = 0;
        nextSector = 0;
        nextLocalIndex = 0;

        capacities = new LinkedList<Integer>();
        capacities.add(1);
    }

    public boolean contains(Object o)
    {
        return false;
    }

    public boolean containsAll(Collection<?> c)
    {
        return false;
    }

    public boolean equals(Object o)
    {
        return false;
    }

    public E get(int index)
    {
        int sector = getSectorIndex(index);
        int localIndex = getLocalIndex(index);
        return (E) sectors.get(sector)[localIndex];
    }

    public int hashCode()
    {
        return 0;
    }

    public int indexOf(Object o)
    {
        return 0;
    }

    public boolean isEmpty()
    {
        return false;
    }

    public Iterator<E> iterator()
    {
        return null;
    }

    public int lastIndexOf(Object o)
    {
        return 9;
    }

    public ListIterator<E> listIterator()
    {
        return null;
    }

    public ListIterator<E> listIterator(int index)
    {
        return null;
    }

    public E remove(int index)
    {
        return null;
    }

    public boolean remove(Object o)
    {
        return false;
    }

    public boolean removeAll(Collection<?> c)
    {
        return false;
    }

    public boolean retainAll(Collection<?> c)
    {
        return false;
    }

    public E set(int index, E element)
    {
        return null;
    }

    public int size()
    {
        return nextIndex;
    }

    public List<E> subList(int fromIndex, int toIndex)
    {
        return null;
    }

    public Object[] toArray()
    {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return a;
    }

    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        strb.append('[');
        for(int i = 0; i < nextIndex; i++)
        {
            strb.append(get(i));
            if(i + 1 < nextIndex)
            {
                strb.append(", ");
            }
        }
        strb.append("]");
        return strb.toString();
    }

    private int getSectorIndex(int index)
    {
        int i = 0;
        int capacity = 1;
        while(index >= capacity)
        {
            i++;
            capacity *= 2;
        }
        return i;
    }

    private int getLocalIndex(int index)
    {
        int sectorIndex = getSectorIndex(index);
        if(sectorIndex == 0)
        {
            return 0;
        }
        else
        {
            int capacity = 1;
            for(int i = 0; i < sectorIndex - 1; i++)
            {
                capacity *= 2;
            }
            return index - capacity;
        }
    }

    private void ensureCapacity()
    {
        //if(nextLocalIndex == sectors.get(nextSector).length)
        if(nextIndex == capacities.getLast())
        {
            newSector();
            nextLocalIndex = 0;
        }
    }
    
    private void newSector()
    {
        int capacity = capacities.getLast();
        capacities.add(capacity*2);
        //sectors.add(new Object[capacity]);
        sectors.add(new Object[nextIndex]);
        nextSector++;
    }
}