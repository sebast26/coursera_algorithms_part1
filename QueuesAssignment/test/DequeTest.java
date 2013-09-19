import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebast26
 */
public class DequeTest {
    
    Deque emptyDeque;
    
    public DequeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        emptyDeque = new Deque();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isEmpty method, of class Deque.
     */
    @Test
    public void testIsEmptyWithEmptyDeque() {
        assertTrue(emptyDeque.isEmpty());
    }
    
    @Test
    public void testIsEmptyWithOneFrontItem() {
        emptyDeque.addFirst("seba");
        assertFalse(emptyDeque.isEmpty());
    }
    
    @Test
    public void testIsEmptyWithOneEndItem() {
        emptyDeque.addLast("seba");
        assertFalse(emptyDeque.isEmpty());
    }
    
    @Test
    public void testIsEmptyWithAddAndRemoveFromFront() {
        emptyDeque.addFirst("seba");
        emptyDeque.removeFirst();
        assertTrue(emptyDeque.isEmpty());
    }
    
    @Test
    public void testIsEmptyWithAddAndRemoveFromEnd() {
        emptyDeque.addLast("seba");
        emptyDeque.removeLast();
        assertTrue(emptyDeque.isEmpty());
    }
    
    @Test
    public void testIsEmptyWithAddAndRemoveFromBoth() {
        emptyDeque.addFirst("seba");
        emptyDeque.addLast("seba");
        emptyDeque.removeFirst();
        emptyDeque.removeLast();
        assertTrue(emptyDeque.isEmpty());
    }

    /**
     * Test of addFirst method, of class Deque.
     */
    @Test(expected = NullPointerException.class)
    public void testAddFirstWithNullItem() {
        emptyDeque.addFirst(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddLastWithNullItem() {
        emptyDeque.addLast(null);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstOnEmptyDeque() {
        emptyDeque.removeFirst();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastOnEmptyDeque() {
        emptyDeque.removeLast();
    }
    
}