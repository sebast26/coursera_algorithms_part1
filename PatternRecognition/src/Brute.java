
/**
 *
 * @author sebast26
 */
public class Brute {

    public static void main(String[] args) {
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        Point[] points = new Point[N];

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            p.draw();
        }
        
        StdDraw.show(0);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    Point p = points[i];
                    Point q = points[j];
                    Point r = points[k];

                    if (p.compareTo(q) < 0 && q.compareTo(r) < 0 && (p.slopeTo(q) == p.slopeTo(r))) {
                        for (int l = 0; l < N; l++) {
                            Point s = points[l];
                            
                            if (r.compareTo(s) < 0 && p.slopeTo(q) == p.slopeTo(s)) {
                                System.out.println(p + " -> " + q + " -> " + r + " -> " + s);
                                p.drawTo(s);
                                StdDraw.show(0);
                            }
                        }
                    }
                }
            }
        }
    }
}
