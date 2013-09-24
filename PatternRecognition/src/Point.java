
import java.util.Comparator;


/**
 *
 * @author sebast26
 */
public class Point implements Comparable<Point> {

    /**
     * compare points by slope to this point
     */
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(Point t, Point t1) {
            if (slopeTo(t) < slopeTo(t1)) {
                return -1;
            } else if (slopeTo(t) > slopeTo(t1)) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    /**
     * x coordinate
     */
    private final int x;
    /**
     * y coordinate
     */
    private final int y;
    
    
    /**
     * construct the point (x, y)
     * @param x
     * @param y 
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * draw this point
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * draw the line segment from this point to that point
     * @param that 
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * string representation
     * @return 
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * is this point lexicographically smaller than that point?
     * @param that
     * @return 
     */
    public int compareTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            return 0;
        } else if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * the slope between this point and that point
     * @param that
     * @return 
     */
    public double slopeTo(Point that) {
        if (this == that) {
            return Double.NEGATIVE_INFINITY;
        } else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else if (this.y == that.y) {
            return 0.0;
        } else {
            return (double) (that.y - this.y) / (that.x - this.x);
        }
    }
    
    public static void main(String[] args) {
        Point p = new Point(19000, 10000);
        Point q = new Point(18000, 10000);
        
        System.out.println(p.compareTo(q));
        System.out.println(p.slopeTo(q));
        System.out.println(q.slopeTo(q));
    }
}
