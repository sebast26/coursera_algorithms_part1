import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author seba
 */
public class PercolationTest {
    
    public PercolationTest() {
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsOpenThrowsExceptionWhenOutOfBound1() {
        Percolation p = new Percolation(5);
        p.isOpen(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsOpenThrowsExceptionWhenOutOfBound2() {
        Percolation p = new Percolation(5);
        p.isOpen(9, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsOpenThrowsExceptionWhenOutOfBound3() {
        Percolation p = new Percolation(5);
        p.isOpen(2, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsOpenThrowsExceptionWhenOutOfBound4() {
        Percolation p = new Percolation(5);
        p.isOpen(2, 9);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOpenThrowsExceptionWhenOutOfBound1() {
        Percolation p = new Percolation(5);
        p.open(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOpenThrowsExceptionWhenOutOfBound2() {
        Percolation p = new Percolation(5);
        p.open(9, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOpenThrowsExceptionWhenOutOfBound3() {
        Percolation p = new Percolation(5);
        p.open(2, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOpenThrowsExceptionWhenOutOfBound4() {
        Percolation p = new Percolation(5);
        p.open(2, 9);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsFullThrowsExceptionWhenOutOfBound1() {
        Percolation p = new Percolation(5);
        p.isFull(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsFullThrowsExceptionWhenOutOfBound2() {
        Percolation p = new Percolation(5);
        p.isFull(9, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsFullThrowsExceptionWhenOutOfBound3() {
        Percolation p = new Percolation(5);
        p.isFull(2, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsFullThrowsExceptionWhenOutOfBound4() {
        Percolation p = new Percolation(5);
        p.isFull(2, 9);
    }
    
    @Test
    public void testIsFull2By2WithOneOpen() {
        Percolation p = new Percolation(2);
        assertFalse(p.isFull(1, 1));
        p.open(1, 1);
        assertTrue(p.isFull(1, 1));
        assertFalse(p.isFull(2, 1));
        p.open(2, 1);
        assertTrue(p.isFull(2, 1));
    }
    
    @Test
    public void testIsFullBackwashBug() {
        // PercolationVisualizer colors in light blue all sites connected to 
        // open sites on the bottom (in addition to those connected to open sites on the top)
        Percolation p = new Percolation(3);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 1);
        assertFalse(p.isFull(3, 1));
    }
    
    @Test
    public void testOpenSingleElementGrid() {
        Percolation p = new Percolation(1);
        p.open(1, 1);
        assertTrue(p.isOpen(1, 1));
    }
    
    @Test
    public void testOpenSingleElementIn2By2Grid() {
        Percolation p = new Percolation(2);
        p.open(1, 2);
        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isOpen(2, 1));
        assertFalse(p.isOpen(2, 2));
        assertTrue(p.isOpen(1, 2));
    }
    
    @Test
    public void testOpenTwoElementsIn2By2Grid() {
        Percolation p = new Percolation(2);
        p.open(1, 2);
        p.open(1, 1);
        assertTrue(p.areSitesConnected(1, 2, 1, 1));
    }
    
    @Test
    public void testPercolates1By1Grid() {
        Percolation p = new Percolation(1);
        assertFalse(p.percolates());
    }
    
    @Test
    public void testPercolates2By2Grid() {
        Percolation p = new Percolation(2);
        assertFalse(p.percolates());
        p.open(1, 1);
        assertFalse(p.percolates());
        p.open(2, 2);
        assertFalse(p.percolates());
        p.open(1, 2);
        assertTrue(p.percolates());
    }

    
    @Test
    public void testGetTopRowIndexes1() {
        Percolation p = new Percolation(8);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, p.getTopRowIndexes());
    }
    
    @Test
    public void testGetTopRowIndexes2() {
        Percolation p = new Percolation(1);
        assertArrayEquals(new int[] {1}, p.getTopRowIndexes());
    }

    @Test
    public void testGetTopRowIndexes3() {
        Percolation p = new Percolation(2);
        assertArrayEquals(new int[] {1, 2}, p.getTopRowIndexes());
    }
    
    @Test
    public void testIsSiteAtTopRow() {
        Percolation p = new Percolation(10);
        assertTrue(p.isSiteAtTopRow(1, 1));
        assertTrue(p.isSiteAtTopRow(1, 5));
        assertTrue(p.isSiteAtTopRow(1, 10));
        assertFalse(p.isSiteAtTopRow(2, 10));
        assertFalse(p.isSiteAtTopRow(10, 10));
        assertFalse(p.isSiteAtTopRow(5, 7));
    }
    
    @Test
    public void testIsSiteAtBottomRow() {
        Percolation p = new Percolation(10);
        assertFalse(p.isSiteAtBottomRow(1, 1));
        assertFalse(p.isSiteAtBottomRow(1, 5));
        assertFalse(p.isSiteAtBottomRow(1, 10));
        assertFalse(p.isSiteAtBottomRow(2, 10));
        assertTrue(p.isSiteAtBottomRow(10, 10));
        assertTrue(p.isSiteAtBottomRow(10, 1));
        assertTrue(p.isSiteAtBottomRow(10, 5));
        assertFalse(p.isSiteAtBottomRow(5, 7));
    }

    @Test
    public void testGetBottomRowIndexes1() {
        Percolation p = new Percolation(8);
        assertArrayEquals(new int[] {57, 58, 59, 60, 61, 62, 63, 64}, p.getBottomRowIndexes());
    }
    
    @Test
    public void testGetBottomRowIndexes2() {
        Percolation p = new Percolation(1);
        assertArrayEquals(new int[] {1}, p.getBottomRowIndexes());
    }

    @Test
    public void testGetBottomRowIndexes3() {
        Percolation p = new Percolation(2);
        assertArrayEquals(new int[] {3, 4}, p.getBottomRowIndexes());
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetSiteIndexOutOfBounds1() {
        Percolation p = new Percolation(5);
        p.getSiteIndex(0, 2);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetSiteIndexOutOfBounds2() {
        Percolation p = new Percolation(5);
        p.getSiteIndex(9, 2);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetSiteIndexOutOfBounds3() {
        Percolation p = new Percolation(5);
        p.getSiteIndex(2, 0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetSiteIndexOutOfBounds4() {
        Percolation p = new Percolation(5);
        p.getSiteIndex(2, 9);
    }
    
    @Test
    public void testGetSiteIndex1() {
        Percolation p = new Percolation(8);
        assertEquals(1, p.getSiteIndex(1, 1));
    }
    
    @Test
    public void testGetSiteIndex2() {
        Percolation p = new Percolation(8);
        assertEquals(8, p.getSiteIndex(1, 8));
    }

    @Test
    public void testGetSiteIndex3() {
        Percolation p = new Percolation(8);
        assertEquals(17, p.getSiteIndex(3, 1));
    }

    @Test
    public void testGetSiteIndex4() {
        Percolation p = new Percolation(8);
        assertEquals(29, p.getSiteIndex(4, 5));
    }

    @Test
    public void testGetSiteIndex5() {
        Percolation p = new Percolation(1);
        assertEquals(1, p.getSiteIndex(1, 1));
    }

    @Test
    public void testGetSiteIndex2By2_1() {
        Percolation p = new Percolation(2);
        assertEquals(1, p.getSiteIndex(1, 1));
    }
    
    @Test
    public void testGetSiteIndex2By2_2() {
        Percolation p = new Percolation(2);
        assertEquals(2, p.getSiteIndex(1, 2));
    }
    
    @Test
    public void testGetSiteIndex2By2_3() {
        Percolation p = new Percolation(2);
        assertEquals(3, p.getSiteIndex(2, 1));
    }

    @Test
    public void testGetSiteIndex2By2_4() {
        Percolation p = new Percolation(2);
        assertEquals(4, p.getSiteIndex(2, 2));
    }
    
    @Test
    public void testAreSitesConnected() {
        Percolation p = new Percolation(2);
        assertFalse(p.areSitesConnected(1, 1, 2, 1));
    }
    
    @Test
    public void testAreSitesConnected2() {
        Percolation p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 1);
        assertTrue(p.areSitesConnected(1, 1, 2, 1));
    }
    
    @Test
    public void testAreSitesConnectedThreeSites() {
        Percolation p = new Percolation(3);
        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);
        assertTrue(p.areSitesConnected(1, 2, 3, 2));
    }
    
    @Test
    public void testAreSitesConnectedThreeSitesWithoutConnection() {
        Percolation p = new Percolation(3);
        p.open(1, 2);
        p.open(3, 2);
        assertFalse(p.areSitesConnected(1, 2, 3, 2));
    }
    
    @Test
    public void testGetAdjacentSitesTopLeft() {
        Percolation p = new Percolation(2);
        Object[] result = p.getAdjacentSites(1, 1);
        
        assertEquals(2, result.length);
        assertArrayEquals(new Integer[] {2, 1}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {1, 2}, (Integer[]) result[1]);
    }
    
    @Test
    public void testGetAdjacentSitesTop() {
        Percolation p = new Percolation(3);
        Object[] result = p.getAdjacentSites(1, 2);
        
        assertEquals(3, result.length);
        assertArrayEquals(new Integer[] {2, 2}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {1, 1}, (Integer[]) result[1]);
        assertArrayEquals(new Integer[] {1, 3}, (Integer[]) result[2]);
    }
    
    @Test
    public void testGetAdjacentSitesTopRight() {
        Percolation p = new Percolation(3);
        Object[] result = p.getAdjacentSites(1, 3);
        
        assertEquals(2, result.length);
        assertArrayEquals(new Integer[] {2, 3}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {1, 2}, (Integer[]) result[1]);
    }
    
    @Test
    public void testGetAdjacentSitesRight() {
        Percolation p = new Percolation(3);
        Object[] result = p.getAdjacentSites(2, 3);
        
        assertEquals(3, result.length);
        assertArrayEquals(new Integer[] {1, 3}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {3, 3}, (Integer[]) result[1]);
        assertArrayEquals(new Integer[] {2, 2}, (Integer[]) result[2]);
    }
    
    @Test
    public void testGetAdjacentSitesBottomRight() {
        Percolation p = new Percolation(3);
        Object[] result = p.getAdjacentSites(3, 3);
        
        assertEquals(2, result.length);
        assertArrayEquals(new Integer[] {2, 3}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {3, 2}, (Integer[]) result[1]);
    }
    
    @Test
    public void testGetAdjacentSitesBottom() {
        Percolation p = new Percolation(3);
        Object[] result = p.getAdjacentSites(3, 2);
        
        assertEquals(3, result.length);
        assertArrayEquals(new Integer[] {2, 2}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {3, 1}, (Integer[]) result[1]);
        assertArrayEquals(new Integer[] {3, 3}, (Integer[]) result[2]);
    }
    
    @Test
    public void testGetAdjacentSitesBottomLeft() {
        Percolation p = new Percolation(3);
        Object[] result = p.getAdjacentSites(3, 1);
        
        assertEquals(2, result.length);
        assertArrayEquals(new Integer[] {2, 1}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {3, 2}, (Integer[]) result[1]);
    }
    
    @Test
    public void testGetAdjacentSitesLeft() {
        Percolation p = new Percolation(3);
        Object[] result = p.getAdjacentSites(2, 1);
        
        assertEquals(3, result.length);
        assertArrayEquals(new Integer[] {1, 1}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {3, 1}, (Integer[]) result[1]);
        assertArrayEquals(new Integer[] {2, 2}, (Integer[]) result[2]);
    }
    
    @Test
    public void testGetAdjacentSitesCenter() {
        Percolation p = new Percolation(3);
        Object[] result = p.getAdjacentSites(2, 2);
        
        assertEquals(4, result.length);
        assertArrayEquals(new Integer[] {1, 2}, (Integer[]) result[0]);
        assertArrayEquals(new Integer[] {3, 2}, (Integer[]) result[1]);
        assertArrayEquals(new Integer[] {2, 1}, (Integer[]) result[2]);
        assertArrayEquals(new Integer[] {2, 3}, (Integer[]) result[3]);
    }
}