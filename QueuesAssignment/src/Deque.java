
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @author sebast26
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * size of the deque.
     */
    private int n;
    /**
     * front of the deque.
     */
    private Node front;
    /**
     * end of the deque.
     */
    private Node end;

    private class DequeListIterator implements Iterator<Item> {

        private Node current = front;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException("No more elements!");
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove in iterator is not supported!");
        }
    }
    
    /**
     * helper linked-list class
     */
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    /**
     * construct an empty deque
     */
    public Deque() {
        front = null;
        end = null;
        n = 0;
        assert check();
    }

    /**
     * is the deque empty.
     * @return      <code>true</code> if deque is empty;
     *              <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return front == null || end == null;
    }


    /**
     * return the number of items on the deque.
     * @return      size of the deque
     */
    public int size() {
        return n;
    }

    /**
     * insert the item at the front
     * @param item 
     */
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("Item cannot be null.");
        
        Node oldFront = front;
        front = new Node();
        front.item = item;
        front.next = oldFront;
        front.prev = null;
        
        if (isEmpty()) {
            end = front;
        } else {
            oldFront.prev = front;
        }       
        n++;
        
        assert check();
    }

    /**
     * insert the item at the end
     * @param item 
     */
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Item cannot be null.");
        
        Node oldEnd = end;
        end = new Node();
        end.item = item;
        end.next = null;
        end.prev = oldEnd;
        
        if (isEmpty()) {
            front = end;
        } else {
            oldEnd.next = end;
        }
        n++;
        
        assert check();
    }

    /**
     * delete and return the item at the front
     * @return 
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty!");
        
        Node oldFront = front;
        Item item = front.item;
        front = front.next;
        if (!isEmpty()) front.prev = null;
        n--;
        if (isEmpty()) end = null;
        oldFront = null;
        
        assert check();
        return item;
    }

    /**
     * delete and return the item at the end
     * @return 
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty!");
        
        Node oldEnd = end;
        Item item = end.item;
        end = end.prev;
        if (!isEmpty()) end.next = null;
        n--;
        if (isEmpty()) front = null;
        oldEnd = null;
        
        assert check();
        return item;
    }

    /**
     * return an iterator over items in order from front to end
     * @return 
     */
    public Iterator<Item> iterator() {
        return new DequeListIterator();
    }
    
    public static void main(String[] args) {
        Deque<String> emptyDeque = new Deque();
        emptyDeque.addLast("seba");
        emptyDeque.addLast("seba2");
        emptyDeque.addLast("seba3");
        for (String i : emptyDeque) {
            System.out.println(i);
        }
        emptyDeque.removeLast();
        for (String i : emptyDeque) {
            System.out.println(i);
        }
        emptyDeque.removeLast();
        for (String i : emptyDeque) {
            System.out.println(i);
        }        
        emptyDeque.removeLast();
        
        System.out.println("------------");
        
        Deque<String> mixedDeque = new Deque<String>();
        mixedDeque.addFirst("seba");
        mixedDeque.addLast("last");
        for (String i : mixedDeque) {
            System.out.println(i);
        }
        
        System.out.println("------------");
        
        mixedDeque.addFirst("first");
        mixedDeque.addFirst("firstest");
        mixedDeque.addLast("lastest");
        for (String i : mixedDeque) {
            System.out.println(i);
        }
        
        System.out.println("-------------");
        
        mixedDeque.removeFirst();        
        for (String i : mixedDeque) {
            System.out.println(i);
        }
        
        System.out.println("-------------");
        
        mixedDeque.removeFirst();
        mixedDeque.removeLast();
        mixedDeque.removeLast();
        for (String i : mixedDeque) {
            System.out.println(i);
        }
        
        System.out.println("-------------");
        
        mixedDeque.removeFirst();
        
        System.out.println("-------------");
        
        for (String i : mixedDeque) {
            System.out.println(i);
        }
        
        Deque<String> testDeque = new Deque<String>();
        testDeque.addFirst("test");
        Iterator<String> iter = testDeque.iterator();
        String t = iter.next();
        try {
            iter.next();
        } catch (NoSuchElementException e) {
            System.out.println("Exception correctly catched!");
        }
        
        testDeque.addLast("test2");
        for (String i : testDeque) {
            System.out.println(i + ":");
            for (String i2 : testDeque) {
                System.out.println("\t" + i2);
            }
        }
    }
    
    /**
     * check internal invariants
     * @return      <code>true</code> if Deque is coherent;
     *              <code>false</code> otherwise.
     */
    private boolean check() {
        if (n == 0) {
            if (front != null) return false;
            if (end != null) return false;
        } else if (n == 1) {
            if (front == null || end == null) return false;
            if (front != end) return false;
            if (front.next != null) return false;
            if (front.prev != null) return false;
            if (end.next != null) return false;
            if (end.prev != null) return false;
        } else if (n == 2) {
            if (front == null || end == null) return false;
            if (front.next != end) return false;
            if (front.prev != null) return false;
            if (end.next != null) return false;
            if (end.prev != front) return false;
        } else {
            if (front == end) return false;
            if (front.next == null) return false;
            if (end.next != null) return false;
            
            // check internal consistency of instance variable N
            int numberOfNodes = 0;
            for (Node x = front; x != null; x = x.next) {
               numberOfNodes++;
            }
            if (numberOfNodes != n) return false;
            
            // check internal consistency of instance variable last
            Node lastNode = front;
            while (lastNode.next != null) {
               lastNode = lastNode.next;
            }
            if (end != lastNode) return false;

            // check internal consistency of instance variable front
            Node frontNode = end;
            while (frontNode.prev != null) {
               frontNode = frontNode.prev;
            }
            if (front != frontNode) return false;

        }
        
        return true;
    }

}