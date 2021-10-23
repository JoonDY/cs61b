package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time * 1e6 / opCount ;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> N = new AList<Integer>();
        AList<Double> times = new AList<Double>();


        for (int i = 1000; i <= 1200000; i *= 2){
            AList<Integer> test = new AList<Integer>();

            int j = i;
            Stopwatch sw = new Stopwatch();
            while (j > 0) {
                test.addLast(1);
                j -= 1;
            }
            double timeInSeconds = sw.elapsedTime();

            N.addLast(i);
            times.addLast(timeInSeconds);
        }
        printTimingTable(N, times, N);
    }
}
