package kanik;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Priority Queue class
 */
public class PriorityQueue {
    /**
     * array which queue's heap
     * comparator to compare something
     * size is array's size
     * parent and child are indexes
     */
    private K2DArray[] array;
    Comparator<K2DArray> comparator = null;
    private int capacity=100, size=0;
    private int index=0;
    private int parent=0, child=0;

    /*** constructor, initializes array */
    public PriorityQueue () {
        array = new K2DArray[capacity];
    }
    /**
     * sets comparator to given comparison type
     * @param c to know which method of comparison
     */
    void setComparator(Comparator<K2DArray> c) {
        comparator = c;
    }

    /**
     * adds an item to the priority queue
     * @param item given item which has color pixels
     * @return always true
     */
    public boolean offer(K2DArray item) {
        if(size==capacity)
            reallocate();
        array[index++] = item;
        size++;
        child = size-1;
        parent = (child-1)/2;
        /*** if parent's value is smaller than child's value, swap these two thing */
        while(parent >= 0 && comparator.compare(array[parent], array[child]) < 0)
            swap(parent, child);
        return true;
    }

    /**
     * removes the first which biggest element from the priority queue
     * @return null if priority queue is empty, else the biggest element
     */
    public K2DArray poll() {
        if(isEmpty())
            return null;
        return remove();
    }

    /**
     * removes the first which biggest element from the piority queue
     * @return biggest element if the queue is not empty, else throws NoSuchElementException
     */
    public K2DArray remove() {
        if(isEmpty())
            throw new NoSuchElementException("There is no element in heap!");
        /*** the first element assigned to return it
         * parent, left and right child is created depending first element*/
        K2DArray first = getFirst();
        int parent = 0;
        int leftChild = 2*parent+1;
        int rightChild = 2*parent+2;
        /**
         * while left and right child are smaller than size and heap array in these indexes is not null
         * compare parent's left and right child and if change their position, appropriate
         */
        while(leftChild<size && rightChild<size && array[leftChild] != null && array[rightChild] != null) {
            if (comparator.compare(array[leftChild], array[rightChild]) > 0) {
                array[parent] = array[leftChild];
                parent = leftChild;
            }
            else if (comparator.compare(array[leftChild], array[rightChild]) < 0) {
                array[parent] = array[rightChild];
                parent = rightChild;
            }
            else {
                array[parent] = array[rightChild];
                parent = rightChild;
            }
            /*** depending on changing parent and child's values, update this values */
            leftChild = 2 * parent + 1;
            rightChild = 2 * parent + 2;
        }
        /*** then decrease size of array and return first */
        size--;
        return first;
    }

    /**
     * returns the first element of priority queue if exist
     * @return first element of the queue if queue is not empty, else return null
     */
    public K2DArray peek() {
        if(isEmpty())
            return null;
        return getFirst();
    }

    /**
     * returns the first element of the priority queue if exist
     * @return first element of the queue if queue is not empty, else throws NoSuchElementException
     */
    public K2DArray element() {
        if(isEmpty())
            throw new NoSuchElementException("There is no element in heap!");
        return getFirst();
    }

    /**
     * returns if priority queue is empty
     * @return true if priority queue is empty, else return false
     */
    private boolean isEmpty() {
        if(size == 0)
            return true;
        return false;
    }
    /**
     * changes given two values each other
     * @param index1 first value to change to index2
     * @param index2 second value to change to index1
     */
    private void swap(int index1, int index2) {
        K2DArray temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        /*** then calls swap */
        swap();
    }
    /*** swaps indexes which child and parent */
    private void swap() {
        child = parent;
        parent = (child-1)/2;
    }
    /**
     * to expand the array size
     */
    private void reallocate() {
        capacity *= 2;
        K2DArray[] newDirectory = new K2DArray[capacity];
        System.arraycopy(array, 0, newDirectory, 0,
                array.length);
        array = newDirectory;
    }
    /**
     * returns the first element
     * @return the first element
     */
    private K2DArray getFirst() {
        return array[0];
    }
}
