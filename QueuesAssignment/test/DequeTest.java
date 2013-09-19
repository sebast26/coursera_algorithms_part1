import java.util.Iterator;
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isEmpty method, of class Deque.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Deque instance = new Deque();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class Deque.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Deque instance = new Deque();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFirst method, of class Deque.
     */
    @Test
    public void testAddFirst() {
        System.out.println("addFirst");
        Object item = null;
        Deque instance = new Deque();
        instance.addFirst(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLast method, of class Deque.
     */
    @Test
    public void testAddLast() {
        System.out.println("addLast");
        Object item = null;
        Deque instance = new Deque();
        instance.addLast(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFirst method, of class Deque.
     */
    @Test
    public void testRemoveFirst() {
        System.out.println("removeFirst");
        Deque instance = new Deque();
        Object expResult = null;
        Object result = instance.removeFirst();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeLast method, of class Deque.
     */
    @Test
    public void testRemoveLast() {
        System.out.println("removeLast");
        Deque instance = new Deque();
        Object expResult = null;
        Object result = instance.removeLast();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class Deque.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Deque instance = new Deque();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}