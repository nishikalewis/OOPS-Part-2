package edu.umb.cs681.hw08;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem implements Runnable{
    private static FileSystem fs=null;
    private static final ReentrantLock lock = new ReentrantLock();
    LinkedList<Directory>root=new LinkedList<>();

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        lock.lock();
        try {
            if (fs == null) {
                fs = new FileSystem();
            }
            return fs;
        } finally {
            lock.unlock();
        }
    }


    public LinkedList<Directory> getRootDir(){
        return this.root;
    }
    public void appendRoot(Directory root){
        this.root.add(root);
    }

    @Override
    public void run() {
        System.out.println("Thread #" + Thread.currentThread().threadId() +
                " - FileSystem instance: " + FileSystem.getFileSystem().hashCode());

    }


    public static void main(String[] args) {

        FileSystem fs1 = new FileSystem();
        FileSystem fs2 = new FileSystem();
        FileSystem fs3 = new FileSystem();
        Thread thread1 = new Thread(fs1);
        Thread thread2 = new Thread(fs2);
        Thread thread3 = new Thread(fs3);
        System.out.println("Starting Thread #" + thread1.threadId());
        thread1.start();
        System.out.println("Starting Thread #" + thread2.threadId());
        thread2.start();
        System.out.println("Starting Thread #" + thread2.threadId());
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


