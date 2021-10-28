package deque;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.lang.reflect.Array;

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

        int j = 0;
        while (j < 983) {
            test.removeLast();
            j += 1;
        }

        assertFalse(test.isEmpty());
        assertEquals(17, test.size());
        assertTrue(test.usage() >= .25);
    }

    @Test
    // Tests add/remove first, isEmpty, size, usage ratio, resizing
    public void addRemoveFirst() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        assertTrue(test.isEmpty());
        int i = 0;
        while (i < 1000) {
            test.addFirst(i);
            i += 1;
        }

        assertEquals(1000, test.size());

        int j = 0;
        while (j < 983) {
            test.removeFirst();
            j += 1;
        }

        assertFalse(test.isEmpty());
        assertEquals(17, test.size());
        assertTrue(test.usage() >= .25);
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(0);
        test.addLast(1);
        test.addLast(2);

        test.printDeque();

        ArrayDeque<Integer> test2 = new ArrayDeque<>();

        assertFalse(test.equals(test2));

        test2.addFirst(2);
        test2.addFirst(1);
        test2.addFirst(0);

        assertTrue(test2.equals(test));

        test.removeFirst();
        test.removeFirst();
        test.removeFirst();

        assertFalse(test2.equals(test));

        test2.removeLast();
        test2.removeLast();
        test2.removeLast();

        assertTrue(test.equals(test2));
    }

    @Test
    public void equalsLinkedTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(0);
        test.addLast(1);
        test.addLast(2);

        test.printDeque();

        LinkedListDeque<Integer> test2 = new LinkedListDeque<>();

        assertFalse(test.equals(test2));

        test2.addFirst(2);
        test2.addFirst(1);
        test2.addFirst(0);

        assertTrue(test2.equals(test));

        test.removeFirst();
        test.removeFirst();
        test.removeFirst();

        assertFalse(test2.equals(test));

        test2.removeLast();
        test2.removeLast();
        test2.removeLast();

        assertTrue(test.equals(test2));
    }

    @Test
    public void timeTest() {
        int testCount1 = 10000;
        int testCount2 = 1000000;

        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        ArrayDeque<Integer> test2 = new ArrayDeque<>();

        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i < testCount1; i++) {
            test1.addFirst(i);
        }
        double time1 = sw1.elapsedTime();
        double avg1 = time1 * 1000000 / testCount1;

        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < testCount2; i++) {
            test2.addFirst(i);
        }
        double time2 = sw2.elapsedTime();
        double avg2 = time2 * 1000000 / testCount2;

        assertTrue("addFirst not within .5 microseconds/op", Math.abs(avg1-avg2) < .5);

        sw1 = new Stopwatch();
        for (int i = 0; i < testCount1; i++) {
            test1.addLast(i);
        }
        time1 = sw1.elapsedTime();
        avg1 = time1 * 1000000 / testCount1;

        sw2 = new Stopwatch();
        for (int i = 0; i < testCount2; i++) {
            test2.addLast(i);
        }
        time2 = sw2.elapsedTime();
        avg2 = time2 * 1000000 / testCount2;

        assertTrue("addLast not within .5 microseconds/op", Math.abs(avg1-avg2) < .5);

        sw1 = new Stopwatch();
        for (int i = 0; i < testCount1; i++) {
            test1.removeFirst();
        }
        time1 = sw1.elapsedTime();
        avg1 = time1 * 1000000 / testCount1;

        sw2 = new Stopwatch();
        for (int i = 0; i < testCount2; i++) {
            test2.removeFirst();
        }
        time2 = sw2.elapsedTime();
        avg2 = time2 * 1000000 / testCount2;

        assertTrue("removeFirst not within .5 microseconds/op", Math.abs(avg1-avg2) < .5);

        sw1 = new Stopwatch();
        for (int i = 0; i < testCount1; i++) {
            test1.removeLast();
        }
        time1 = sw1.elapsedTime();
        avg1 = time1 * 1000000 / testCount1;

        sw2 = new Stopwatch();
        for (int i = 0; i < testCount2; i++) {
            test2.removeLast();
        }
        time2 = sw2.elapsedTime();
        avg2 = time2 * 1000000 / testCount2;

        assertTrue("removeLast not within .5 microseconds/op", Math.abs(avg1-avg2) < .5);
    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        ArrayDeque<Integer> test2 = new ArrayDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                test1.addLast(randVal);
                test2.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertEquals(test1.size(), test2.size());
            } else if (operationNumber == 2) {
                // getLast
                if (test2.size() == 0) {
                    continue;
                }
                assertEquals(test1.get(0), test2.get(0));
            } else if (operationNumber == 3) {
                // removeLast
                if (test1.size() == 0) {
                    continue;
                }
                assertEquals(test2.removeLast(), test1.removeLast());
            } else if (operationNumber == 4) {
                int randVal = StdRandom.uniform(0, 100);
                test1.addFirst(randVal);
                test2.addFirst(randVal);
            } else if (operationNumber == 5) {
                // removeLast
                if (test1.size() == 0) {
                    continue;
                }
                assertEquals(test2.removeFirst(), test1.removeFirst());
            }
        }
    }

}
