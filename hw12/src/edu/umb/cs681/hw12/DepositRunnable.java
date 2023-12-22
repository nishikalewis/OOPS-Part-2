package edu.umb.cs681.hw12;

public class DepositRunnable implements Runnable {

    private ThreadSafeBankAccount2 account;
    private volatile boolean done = false;
    public DepositRunnable(ThreadSafeBankAccount2 bankAccount) {
        this.account = bankAccount;
    }
    public void setDone() {
        done = true;
    }

    public void run(){
        while(true){
            if(done){
                System.out.println("Process Halted");return;}
            account.deposit(100);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interruption thrown");
            break;
        }
        }
    }



    }
