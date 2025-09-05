import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisjointSetsTest {

    @Test
    public void testUnions01() {
        DisjointSets sets = new DisjointSets(5);
        sets.union(0, 3);
        assertEquals("[-2, -1, -1, 0, -1]", sets.toString());
    }

    @Test
    public void testUnions02() {
        DisjointSets sets = new DisjointSets(5);
        sets.union(0, 3);
        sets.union(3, 2);
        assertEquals("[-3, -1, 0, 0, -1]", sets.toString());
    }

    @Test
    public void testUnions03() {
        DisjointSets sets = new DisjointSets(5);
        sets.union(0, 3);
        sets.union(3, 2);
        sets.union(1,4);
        sets.union(4,3);
        assertEquals("[-5, 0, 0, 0, 1]", sets.toString());
    }

    @Test
    public void testFind01() {
        DisjointSets sets = new DisjointSets(5);
        sets.union(0, 3);
        sets.union(3, 2);
        sets.union(1,4);
        sets.union(4,3);
        assertEquals(0, sets.find(4));
        assertEquals("[-5, 0, 0, 0, 0]", sets.toString());
    }

    @Test
    public void testFind02() {
        DisjointSets sets = new DisjointSets(5);
        sets.union(0, 3);
        sets.union(3, 2);
        sets.union(1,4);
        sets.union(4,3);
        assertEquals(0, sets.find(1));
        assertEquals("[-5, 0, 0, 0, 1]", sets.toString());
    }
}
