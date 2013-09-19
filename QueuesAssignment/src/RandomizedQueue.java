
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @author seba
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    /**
     * array of items.
     */
    private Item[] array;
    /**
     * number of elements on randomized queue.
     */
    private int n;
    
    /**
     * construct an empty randomized queue.
     */
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }

    /**
     * is the queue empty?
     * @return      <code>true</code> is randomized queue is empty
     *              <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * return the number of items on the queue.
     * @return      size of the randomized queue
     */
    public int size() {
        return n;
    }


    /**
     * add the item
     * @param item 
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Item cannot be null!");
    }

    /**
     * delete and return a random item
     * @return 
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Cannot delete item from empty queue!");
        
        return null;
    }

    /**
     * return (but do not delete) a random item
     * @return 
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Cannot delete item from empty queue!");
        
        return null;
    }

    /**
     * return an independent iterator over items in random order
     * @return 
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        public RandomizedQueueIterator() {
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            if (false) throw new NoSuchElementException("There is no more elements!");
            return null;
        }

        @Override
        public void remove() {
                throw new UnsupportedOperationException("Not supported method.");
        }
    }

}