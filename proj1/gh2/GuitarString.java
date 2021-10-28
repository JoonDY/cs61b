package gh2;

  import deque.ArrayDeque;
  import deque.LinkedListDeque;
  import java.lang.reflect.Array;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private ArrayDeque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity  = (int) Math.round(SR/frequency);
        buffer = new ArrayDeque<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.size()-1 ; i++) {
            buffer.removeLast();
            buffer.addFirst(Math.random() - 0.5);
        }

    }

    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        double newDouble = (buffer.removeFirst() + buffer.get(0)) / 2 * DECAY;
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return this.buffer.get(0);
    }
}
