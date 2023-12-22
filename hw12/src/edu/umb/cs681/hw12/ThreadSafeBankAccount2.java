package edu.umb.cs681.hw12;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount {
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();

    public void deposit(double amount) {
        lock.lock();
        try {
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().threadId() +
                    " (d): current balance: " + balance);
            while (balance >= 300) {
                System.out.println(Thread.currentThread().threadId() +
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().threadId() +
                    " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().threadId() +
                    " (w): current balance: " + balance);
            while (balance <= 0) {
                System.out.println(Thread.currentThread().threadId() +
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().threadId() +
                    " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public double getBalance() {
        return this.balance;
    }

//    public static void main(String[] args){
//        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
//        for(int i = 0; i < 5; i++){
//            new Thread( new DepositRunnable(bankAccount) ).start();
//            new Thread( new WithdrawRunnable(bankAccount) ).start();
//        }
//    }
//}

    public static void main(String[] args) {
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();

        // Create runnable tasks
        DepositRunnable depositTask = new DepositRunnable(bankAccount);
        WithdrawRunnable withdrawTask = new WithdrawRunnable(bankAccount);

        Thread thread1=new Thread(depositTask);
        Thread thread2=new Thread(withdrawTask);

        thread2.start();
        thread1.start();

        try {
            Thread.sleep(1000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Signal all threads to stop
        depositTask.setDone();
        withdrawTask.setDone();

        // Simulate operation for a certain period
        try {
            Thread.sleep(1000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
        thread2.interrupt();
//        // Start 5 deposit and 5 withdraw threads
//        for (int i = 0; i < 5; i++) {
//            depositThreads[i] = new Thread(depositTask);
//            withdrawThreads[i] = new Thread(withdrawTask);
//            depositThreads[i].start();
//            withdrawThreads[i].start();

    }
}
