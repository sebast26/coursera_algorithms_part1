import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebast26
 */
public class PercolationStatsTest {
    
    public PercolationStatsTest() {
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPercolationStatsConstructorThrowException1() {
        new PercolationStats(0, 10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPercolationStatsConstructorThrowException2() {
        new PercolationStats(-1, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPercolationStatsConstructorThrowException3() {
        new PercolationStats(10, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPercolationStatsConstructorThrowException4() {
        new PercolationStats(10, -1);
    }
    
    
}