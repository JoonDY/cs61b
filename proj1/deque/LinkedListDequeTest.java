package deque;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void getTest() {
        LinkedListDeque<Integer> test = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            test.addLast(i);
        }

        assertEquals(50, (int) test.get(50));
        assertEquals(50, (int) test.getRecursive(50));    }

    @Test
    public void timeTest() {
        int testCount1 = 10000;
        int testCount2 = 1000000;

        LinkedListDeque<Integer> test1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> test2 = new LinkedListDeque<>();

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

        System.out.println(avg1);
        System.out.println(avg2);
        assertTrue("addFirst not within .5 microseconds/op", Math.abs(avg1 - avg2) < .5);

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

        System.out.println(avg1);
        System.out.println(avg2);
        assertTrue("addLast not within .5 microseconds/op", Math.abs(avg1 - avg2) < .5);

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

        System.out.println(avg1);
        System.out.println(avg2);
        assertTrue("removeFirst not within .5 microseconds/op", Math.abs(avg1 - avg2) < .5);

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

        System.out.println(avg1);
        System.out.println(avg2);
        assertTrue("removeLast not within .5 microseconds/op", Math.abs(avg1 - avg2) < .5);
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> test1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> test2 = new LinkedListDeque<>();

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
