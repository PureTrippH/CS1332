import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Tripp Hanley
 * @version 1.0
 * @userid jhanley32
 * @GTID 903793303
 *
 * Collaborators: None *
 * Resources: None
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        this.size = 0;
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        // Exceptions to throw
        if (data == null) {
            throw new IllegalArgumentException("The Data Must Not Be Null or Empty!");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("ArrayLists must have consecutive entries!");
        }
        if (index == size && backingArray.length < size) {
            backingArray[index] = data;
            ++size;
            return;
        }
        /*
        Steps:
        1) Check to see if Array needs to be doubled
        2) Double if neccesary
        3) Begin to copy
        4) Check if it is index
        5) If it is, insert
        6) if above index, start to add i-1
        7) Increment size
         */
        T[] temp = (T[]) new Object[((size) >= backingArray.length)
                ? backingArray.length * 2 : backingArray.length];
        for (int i = 0; i < size + 1; i++) {
            if (i == index) {
                temp[i] = data;
            } else if (i > index) {
                temp[i] = backingArray[i - 1];
            } else {
                temp[i] = backingArray[i];
            }
        }
        this.backingArray = temp;
        ++size;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The Data Must Not Be Null or Empty!");
        }
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The Data Must Not Be Null or Empty!");
        }
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        T removedData;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index you want to remove is outside the size or less than 0!");
        }
        /* An array size can not change, so no need to make a new array
        Let's just modify the exiting one. */
        removedData = backingArray[index];
        //FIXED: Out of bounds
        for (int i = index; i < size - 1; i++) {
            System.out.println("Current Val: " + backingArray[i] + "\n + 1 ind: " + backingArray[i + 1]);
            backingArray[i] = backingArray[i + 1];
        }
        //Delete the duplicate at back of Array
        backingArray[size-1] = null;
        --size;
        return removedData;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Array List is Empty!");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cant remove if ArrayList is Empty!");
        }
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index you want to remove is outside the size or less than 0!");
        }
        return backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        /*
        They must be consecutive. If 0 is null, the rest of the
        list must be empty too!
         */
        //List is Null
        return (backingArray[0] == null);
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
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
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
