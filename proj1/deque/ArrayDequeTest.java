package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void basicTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        assertTrue(test.isEmpty());
        int i = 0;
        while (i < 50) {
            test.addLast(i);
            i += 1;
        }

        while (i < 100) {
            test.addFirst(i);
            i += 1;
        }

        assertEquals(100, test.size());
        assertEquals(0, (int) test.get(50));

        int j = 0;
        while (j < 50) {
            test.removeLast();
            j += 1;
        }

        while (j < 100) {
            test.removeFirst();
            j += 1;
        }

        assertTrue(test.isEmpty());
        assertEquals(0, test.size());
    }

    @Test
    // Tests add/remove last, isEmpty, size, usage ratio, resizing
    public void addRemoveLastTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        assertTrue(test.isEmpty());
        int i = 0;
        while (i < 1000) {
            test.addLast(i);
            i += 1;
        }

        assertEquals(1000, test.size());
        assertEquals(50, (int) test.get(50));

        int j = 0;
        while (j < 983) {
            test.removeLast();
            j += 1;
        }

        assertFalse(test.isEmpty());
        assertEquals(17, test.size());
        assertTrue(test.usage() >= .25);
    }
}
