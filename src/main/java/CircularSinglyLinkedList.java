import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Tripp H.
 * @version 1.0
 * @userid jhanley32
 * @GTID 903793303
 *
 * Collaborators: NONE
 *
 * Resources: NONE
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Your Index is Less than 0 or Greater than the size!");
        }
        if (data == null) {
            throw new IllegalArgumentException("The Data Must not Be Null!");
        }
        //Edge 1: If empty.
        if (head == null) {
            head = new CircularSinglyLinkedListNode<>(data);
            //Makes it Circular
            head.setNext(head);
        } else {
            //Remember: Only needed the one case for CSLL.
            CircularSinglyLinkedListNode curr = head;
            //Step 1) go to index we want to insert to.
            //Keep Track of the current node we are working with.
            //NOTE: Can not be null reference, since it loops onto itself.
            for (int i = 0; i < index && index != size; i++) {
                curr = curr.getNext();
            }
            //Step 2) Now that the node we want to move, create a new one to add the old data to.
            CircularSinglyLinkedListNode movedData = new CircularSinglyLinkedListNode(curr.getData());
            //Step 3) Now, set it's next so we do not break the chain.
            //NOTE: If it is head, then set reference to head to so remove(0) is O(1).
            movedData.setNext(curr.getNext());
            //Step 4) Take old node, and point its next to the new.
            curr.setNext(movedData);
            //Step 5) Overwrite old node's data with the new data to insert.
            curr.setData(data);
            //Step 6) See if head
            if (index == size) {
                head = head.getNext();
            }
        }
        //Step 6) increase size (MOST IMPORTANT PART)
        ++size;
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        //Throws in AddIndex Function
        addAtIndex(0, data);
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Your Index is Less than 0 or Greater than or equal to the size!");
        }
        //Edge Case 1) If Empty
        if (head == null) {
            throw new NoSuchElementException("The List is Empty!");
        }
        //Step 1) Define the thing we will return.
        T data;
        //Edge Case 2) Only head in List
        if (size == 1) {
            data = head.getData();
            head = null;
            --size;
            return data;
        }
        //Step 2) Go to node we want to remove
        CircularSinglyLinkedListNode removeNode = head;
        for (int i = 0; i < index; i++) {
            removeNode = removeNode.getNext();
        }
        System.out.println(removeNode.getData());
        //Step 3) Set current value to the return value;
        data = (T) removeNode.getData();
        //Step 4) copy data in next node to the new one
        removeNode.setData(removeNode.getNext().getData());
        //Step 5) Point the removeNode to the next node after removed one
        removeNode.setNext(removeNode.getNext().getNext());
        if (index == 0 || index == size - 1) {
            head = removeNode;
        }
        //Step 6) Remove one from size
        --size;
        return data;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Your Index is Less than 0 or Greater than or equal to the size!");
        }
        CircularSinglyLinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return (T) curr.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        //Step 1) Create two variables: Current and last instance;
        CircularSinglyLinkedListNode curr = head;
        CircularSinglyLinkedListNode lastInstance = null;
        int index = -1;
        //Step 2) Loop through each part of the circle
        for (int i = 0; i < size; i++) {
            if (curr.getData().equals(data)) {
                lastInstance = curr;
                index = i;
            }
            curr = curr.getNext();
        }
        //Step 3) Now that we know the last instance, check if it is null
        if (lastInstance == null) {
            throw new NoSuchElementException("Requested Data Not Found!");
        }
        //Edge Case 1) Only head in List
        if (size == 1) {
            head = null;
            --size;
            return data;
        }
        //Step 4) copy data in next node to the new one
        lastInstance.setData(lastInstance.getNext().getData());
        //Step 5) Point the removeNode to the next node after removed one
        lastInstance.setNext(lastInstance.getNext().getNext());
        if (index == 0 || index == size - 1) {
            head = lastInstance;
        }
        //Step 6) Remove one from size
        --size;
        return data;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] arrayToReturn = (T[]) new Object[size];
        CircularSinglyLinkedListNode curr = head;
        for (int i = 0; i < size; i++) {
            arrayToReturn[i] = (T) curr.getData();
            curr = curr.getNext();
        }
        return arrayToReturn;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
