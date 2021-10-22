package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> base = new AListNoResizing<>();
        BuggyAList<Integer> bug = new BuggyAList<>();

        for (int i = 0; i < 3; i+=1) {
            base.addLast(i);
            bug.addLast(i);
            assertEquals(base.size(), bug.size());
        }

        for (int i = 2; i < base.size(); i+=1) {
            assertEquals(bug.removeLast(), base.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> base = new AListNoResizing<>();
        BuggyAList<Integer> bug = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                base.addLast(randVal);
                bug.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertEquals(base.size(), bug.size());
            } else if ( operationNumber == 2) {
                // getLast
                if (base.size() == 0) {
                    continue;
                }
                assertEquals(base.getLast(), bug.getLast());
            } else if ( operationNumber == 3) {
                // removeLast
                if (base.size() == 0) {
                    continue;
                }
                assertEquals(bug.removeLast(), base.removeLast());
            }
        }
    }
}
