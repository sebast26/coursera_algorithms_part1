
/**
 * Program to estimate the value of the percolation threshold via Monte Carlo simulation.
 * Date: 28-08-2013
 * @author Sebastian Gorecki
 */
public class PercolationStats {

    private int experimentsCount = 0;
    private double[] fractions = null;
    
    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be > 0.");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("T should be > 0.");
        }
        
        experimentsCount = T;
        fractions = new double[T];
        
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int randomRow = -1;
            int randomColumn = -1;
            int countSitesOpened = 0;
            while (!p.percolates()) {
                do {
                    randomRow = StdRandom.uniform(1, N+1);
                    randomColumn = StdRandom.uniform(1, N+1);
                } while (p.isOpen(randomRow, randomColumn));
                p.open(randomRow, randomColumn);
                countSitesOpened++;
            }
            fractions[i] = (double) countSitesOpened / (N*N);
        }
    }

    /**
     * sample mean of percolation threshold
     *
     * @return
     */
    public double mean() {
        return StdStats.mean(fractions);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev() {
        if (experimentsCount == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(fractions);
    }

    /**
     * returns lower bound of the 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        return mean() - ((float) (1.96 * stddev()) / Math.sqrt(experimentsCount));
    }

    /**
     * returns upper bound of the 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        return mean() + ((float) (1.96 * stddev()) / Math.sqrt(experimentsCount));
    }

    /**
     * test client, described below
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        
        PercolationStats ps = new PercolationStats(N, T);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
