
/**
 * Class representing percolation model.
 * Date: 28-08-2013
 * @author Sebastian Gorecki
 */
public class Percolation {

    private boolean[] openSiteArray = null;
    private WeightedQuickUnionUF quickUnion = null;
    private WeightedQuickUnionUF quickUnionFilled = null;
    private int gridNSize = 0;

    /**
     * create N-by-N grid, with all sites blocked
     *
     * @param N
     */
    public Percolation(int N) {
        gridNSize = N;

        // we add two virtual sites (top and bottom)
        quickUnion = new WeightedQuickUnionUF(N * N + 2);
        // "backwash" problem: we need to maintain another UF structure to
        // use with isFull
        quickUnionFilled = new WeightedQuickUnionUF(N * N + 1);

        // set all sites to close
        openSiteArray = new boolean[N * N + 2];
        openSiteArray[0] = true;
        openSiteArray[N * N + 1] = true;
    }

    /**
     * open site (row i, column j) if it is not already
     *
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        if (i <= 0 || i > gridNSize) {
            throw new IndexOutOfBoundsException("Param i out of bounds.");
        }
        if (j <= 0 || j > gridNSize) {
            throw new IndexOutOfBoundsException("Param j out of bounds.");
        }

        if (isOpen(i, j)) {
            return;
        }

        int siteIndex = getSiteIndex(i, j);

        // mark as open
        openSiteArray[siteIndex] = true;

        // connect it to all of its adjacent open sites
        Object[] adjacentSites = getAdjacentSites(i, j);
        for (int index = 0; index < adjacentSites.length; index++) {
            Integer[] adjacentSite = (Integer[]) adjacentSites[index];
            if (isOpen(adjacentSite[0], adjacentSite[1])) {
                int adjacentSiteIndex = getSiteIndex(adjacentSite[0], adjacentSite[1]);
                quickUnion.union(siteIndex, adjacentSiteIndex);
                quickUnionFilled.union(siteIndex, adjacentSiteIndex);
            }
        }
        
        // if site is on top or bottom connect it with virtual top or bottom
        // get rid of bug: testPercolates1By1Grid
        if (isSiteAtTopRow(i, j)) {
            quickUnion.union(0, siteIndex);
            quickUnionFilled.union(0, siteIndex);
        }
        if (isSiteAtBottomRow(i, j)) {
            quickUnion.union(gridNSize * gridNSize + 1, siteIndex);
        }
    }

    /**
     * is site (row i, column j) open?
     *
     * @param i
     * @param j
     * @return
     */
    public boolean isOpen(int i, int j) {
        if (i <= 0 || i > gridNSize) {
            throw new IndexOutOfBoundsException("Param i out of bounds.");
        }
        if (j <= 0 || j > gridNSize) {
            throw new IndexOutOfBoundsException("Param j out of bounds.");
        }

        int index = getSiteIndex(i, j);
        return openSiteArray[index];
    }

    /**
     * is site (row i, column j) full?
     *
     * @param i
     * @param j
     * @return
     */
    public boolean isFull(int i, int j) {
        if (i <= 0 || i > gridNSize) {
            throw new IndexOutOfBoundsException("Param i out of bounds.");
        }
        if (j <= 0 || j > gridNSize) {
            throw new IndexOutOfBoundsException("Param j out of bounds.");
        }

        int siteIndex = getSiteIndex(i, j);
        return isOpen(i, j) && quickUnionFilled.connected(0, siteIndex);
    }

    /**
     * does the system percolate?
     *
     * @return
     */
    public boolean percolates() {
        return quickUnion.connected(0, gridNSize * gridNSize + 1);
    }

    protected final int[] getTopRowIndexes() {
        int[] retval = new int[gridNSize];
        for (int i = 0; i < gridNSize; i++) {
            retval[i] = getSiteIndex(1, i + 1);
        }
        return retval;
    }
    
    protected boolean isSiteAtTopRow(int i, int j) {
        return i == 1;
    }
    
    protected boolean isSiteAtBottomRow(int i, int j) {
        return i == gridNSize;
    }

    protected final int[] getBottomRowIndexes() {
        int[] retval = new int[gridNSize];
        for (int i = 0; i < gridNSize; i++) {
            retval[i] = getSiteIndex(gridNSize, i + 1);
        }
        return retval;
    }

    /**
     * Returns index of the site given by i and j in internal Percolation
     * representation.
     *
     * @param i
     * @param j
     * @return
     */
    protected int getSiteIndex(int i, int j) {
        if (i <= 0 || i > gridNSize) {
            throw new IndexOutOfBoundsException("Param i out of bounds.");
        }
        if (j <= 0 || j > gridNSize) {
            throw new IndexOutOfBoundsException("Param j out of bounds.");
        }
        // (1, 1) is the upper-left site
        return 1 + (i - 1) * gridNSize + (j - 1);
    }

    /**
     * Returns True iff two sites are connected.
     *
     * @param i
     * @param j
     * @param k
     * @param l
     * @return
     */
    protected boolean areSitesConnected(int i, int j, int k, int l) {
        int site1Idx = getSiteIndex(i, j);
        int site2Idx = getSiteIndex(k, l);
        return quickUnion.connected(site1Idx, site2Idx);
    }

    /**
     * Returns adjacent sites to given site. Includes only sites that are in
     * NbyN grid without i-j site.
     *
     * @param i
     * @param j
     * @return
     */
    protected Object[] getAdjacentSites(int i, int j) {
        // up
        Integer[] up = new Integer[]{Math.max(1, i - 1), j};
        // down
        Integer[] down = new Integer[]{Math.min(gridNSize, i + 1), j};
        // left
        Integer[] left = new Integer[]{i, Math.max(1, j - 1)};
        // right
        Integer[] right = new Integer[]{i, Math.min(gridNSize, j + 1)};

        int adjacentSitesCount = 0;
        if (up[0] != i) adjacentSitesCount++;
        if (down[0] != i) adjacentSitesCount++;
        if (left[1] != j) adjacentSitesCount++;
        if (right[1] != j) adjacentSitesCount++;
        
        Object[] retval = new Object[adjacentSitesCount];
        int index = 0;
        if (up[0] != i) {
            retval[index++] = up;
        }
        if (down[0] != i) {
            retval[index++] = down;
        }
        if (left[1] != j) {
            retval[index++] = left;
        }
        if (right[1] != j) {
            retval[index++] = right;
        }

        return retval;
    }
}
