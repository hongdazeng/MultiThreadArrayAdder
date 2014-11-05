import java.util.*;

public class MultiAdder {
    public static void main(String[] args) throws InterruptedException {
        int[] thelist = new int[100];
        for (int i = 0; i < thelist.length; i++) {
            thelist[i] = i;
        }
        int dividepoint = thelist.length / 2;

        theAdder t1 = new theAdder (thelist, 0, dividepoint);
        theAdder t2 = new theAdder (thelist, dividepoint, thelist.length);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(t1.getSum() + t2.getSum());
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
    }
}
