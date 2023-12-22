package edu.umb.cs681.hw09;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }




    @Override
    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            lock.lock();
            try {
                if (getDone()) {
                    System.out.println("Stopped factorizing prime numbers");
                    this.getPrimeFactors().clear();
                    break;
                }
                if (divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
                if (dividend % divisor == 0) {
                    this.getPrimeFactors().add(divisor);
                    dividend /= divisor;
                } else {
                    if (divisor == 2) {
                        divisor++;
                    } else {
                        divisor += 2;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
// Factorization of 36 with a separate thread
        System.out.println("Factorization of 36");
        RunnableCancellableInterruptiblePrimeFactorizer runnable = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long) Math.sqrt(36));
        Thread thread = new Thread(runnable);
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
        thread.start();
        runnable.setDone();
        try{
            Thread.sleep(2000);
            thread.interrupt();
            Thread.sleep(1000);
            System.out.println("Is isInterrupted value: "+thread.isInterrupted());
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }


        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");

    }
}
