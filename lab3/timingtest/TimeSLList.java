package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> N = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> ops = new AList<>();
        int testOps = 10000;

        SLList <Integer> test = new SLList<>();

        for (int i = 1000; i <= 128000; i *= 2){

            while (test.size() < i) {
                test.addLast(1);
            }

            int j = testOps;
            Stopwatch sw = new Stopwatch();
            while (j > 0) {
                test.addLast(1);
                j -= 1;
            }
            double timeInSeconds = sw.elapsedTime();

            N.addLast(i);
            times.addLast(timeInSeconds);
            ops.addLast(testOps);
        }
        printTimingTable(N, times, ops);
    }
}
