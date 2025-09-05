import java.util.Arrays;

/**
 * The DisjointSets data structure, which manages a set of sets, supporting efficient union and
 * find operations.  Implemented as a forest of "up-trees".
 */
public class DisjointSets {

    /** Array for storage of the trees. */
    private int[] parent;

    /**
     * Creates n single element sets (n unconnected root nodes)
     * @param n number of sets
     */
    public DisjointSets(int n) {

        // TODO: Implement this

    }

    /**
     * Returns the representative for the set containing x.  It also should implement path compression.  Each element
     * discovered along the path to the root will be made a child of the root.
     *
     * @param x the element to search for
     * @return the representative of the set containing x, or if the element is not in any set, an exception is thrown
     * @throws java.util.NoSuchElementException if x is not a valid element
     */
    public int find(int x) {

        // TODO: Implement this method.

        return 0;
    }

    /**
     * Combines the sets containing a and b.  This will always append the root of the smaller tree as a child of
     * the root of the larger tree.  If the two are equal, the root of the tree containing b is appended to the
     * root of the tree containing a.  If the two are contained within the same set, this method makes no changes.
     *
     * @param a an element
     * @param b an element
     */
    public void union(int a, int b) {

        // TODO: Implement this method

    }

    @Override
    public String toString() {
        return Arrays.toString(parent);
    }
}
