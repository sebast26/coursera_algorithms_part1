
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
     * add the item.
     * @param item      item to add.
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Item cannot be null!");
        
        if (n == array.length) {
            // double size of array if necessary
            resize(2 * array.length);
        }
        // add item
        array[n++] = item;
    }

    /**
     * delete and return a random item.
     * @return      random item from Randomized queue.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot delete item from empty queue!");
        }
        
        int indexToRemove = StdRandom.uniform(n);
        
        Item item = array[indexToRemove];
        // make algorithm fast we raplace randomly removed item
        // with last item in structure
        array[indexToRemove] = array[n - 1];
        // to avoid loitering
        array[n - 1] = null;
        n--;

        // shrink size of array if necessary
        if (n > 0 && n == array.length / 4) resize(array.length / 2);

        return item;
    }

    /**
     * return (but do not delete) a random item
     * @return 
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot delete item from empty queue!");
        }
        
        int randomIndex = StdRandom.uniform(n);
        
        return array[randomIndex];
    }

    /**
     * return an independent iterator over items in random order
     * @return 
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private boolean[] visitedItem = new boolean[n];
        private int visitedItemCount = 0;
        
        public RandomizedQueueIterator() {
        }

        @Override
        public boolean hasNext() {
            return visitedItemCount < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no more elements!");
            }
            
            int randomIndex = StdRandom.uniform(n);
            while (visitedItem[randomIndex]) {
                randomIndex = StdRandom.uniform(n);
            }
            
            visitedItem[randomIndex] = true;
            visitedItemCount++;
            
            return array[randomIndex];
        }

        @Override
        public void remove() {
                throw new UnsupportedOperationException("Not supported method.");
        }
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        
        System.out.println("---------------");
        
        queue = new RandomizedQueue<Integer>();
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        
//        Iterator<Integer> iter = queue.iterator();
//        boolean hasNext = iter.hasNext();
//        Integer i = iter.next();
        
        for (int i : queue) {
            System.out.println(i);
        }
        System.out.println("---------------");
        for (int i : queue) {
            System.out.println(i);
        }
        System.out.println("---------------");
        for (int i : queue) {
            System.out.println(i);
        }
    }
}