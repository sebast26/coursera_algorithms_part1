
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
        return null;
    }
    
    public static void main(String[] args) {
        Deque emptyDeque = new Deque();
        emptyDeque.addLast("seba");
        emptyDeque.addLast("seba2");
        emptyDeque.addLast("seba3");
        emptyDeque.removeLast();
        emptyDeque.removeLast();
        emptyDeque.removeLast();
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