# LinkedArrayList
A quick test of a Java implementation of a linked-array data structure

# Explanation
This data structure consists of, at its core, a bunch of arrays connected by an arraylist. 
I thought this might perform fast because adding an element would be constant, unless you are at the max size, in which you add a new array to grow the list by a factor of two. Assuming initializing an array is constant time, this would be log(n) time on worst-case insert, but usually constant time. Which is better than an ArrayList since it has to copy all of its components. However, for some mostly unknown reason, it is still almost always slower than ArrayList.

Getting an element would be slower than an arraylist but for an arbitrary index, theoretically faster than a Linked List. The linked array data stucture has to find which internal array the index belongs to, the time of this should scale with the number of internal arrays, which is log(n).

Removing would be quite slow and is the reason I have abandoned this. It would need to shift all elements after n, so it would always be O(N) time to remove. ArrayList also has to do this, but the linked array list is incredibly slow. I think the reason for this is arraylist can make use of System.arraycopy, as well as the other low level benefits of memory caching and other things like that. the linked array list can't do this and in the current implementation, is hundreds of times slower. This is in part due to laziness, right now the remove code calls functions to calculate what internal array and local index it needs every time, which can be incremented, but this leads me to my final point.

This data structure in theory might have some interesting statistics like log(n) insertions and access to arbitrary indicies, but it's a little too complicated to worry about and has already proved that it is going to be slower than other structures available.