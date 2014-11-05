import java.util.*;

public class MultiAdder {
    public static void main(String[] args) throws InterruptedException {
        int[] thelist = new int[101];
        for (int i = 0; i < thelist.length; i++) {
            thelist[i] = i;
        }
        if (thelist.length > 4) {
            int dividepoint = thelist.length / 2;

            advStat t1 = new advStat (thelist, 0, dividepoint);
            advStat t2 = new advStat (thelist, dividepoint, thelist.length);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(dividepoint);
            System.out.println(t1.getAvg());
            System.out.println(t2.getMax());
            System.out.println(t1.getSum() + t2.getSum());
        } else {
            System.out.println("Please set a longer array");
        }
    }

    public static class theAdder extends Thread {
        int[] thearray;
        int start;
        int end;

        int sum = 0;

        public theAdder (int[] data, int a, int b) {
            this.thearray = data;
            this.start = a;
            this.end = b;
        }

        public int getSum() {
            return sum;
        }

        public void run() {
            for (int i = start; i < end; i++) {
                sum += thearray[i];
            }
        }
    } //end theAdder

    public static class advStat extends Thread {
        int[] thearray;
        int start;
        int end;

        private int sum = 0;
        private double average = 0;
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;
        private int num = 0;

        boolean didrun = false;

        public advStat(int[] data, int a, int b) {
            this.thearray = data;
            start = a;
            end = b;
            num = thearray.length;
        }

        public void run() {
            didrun = true;
            for (int i = start; i < end; i++) {
                sum += thearray[i];
                if (thearray[i] < min) {
                    min = thearray[i];
                }
                if (thearray[i] > max) {
                    max = thearray[i];
                }
            }
            average =  sum / num;
        }

        public int getSum() {
            return sum;
        }

        public int getNum() {
            return num;
        }

        public double getAvg() {
            return average;
        }

        public int getMax() {
            return max;
        }

        public int getMin() {
            return min;
        }
    }
}
