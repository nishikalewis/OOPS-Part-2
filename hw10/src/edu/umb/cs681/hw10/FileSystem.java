package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;


public class FileSystem implements Runnable{
    private static AtomicReference<FileSystem> fs = new AtomicReference<>();
    private AtomicBoolean running = new AtomicBoolean(true);

    private ReentrantLock lock=new ReentrantLock();
    private AtomicBoolean done=new AtomicBoolean(false);

    private LinkedList<Directory>root=new LinkedList<>();

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        fs.compareAndSet(null, new FileSystem());
        return fs.get();
    }

    public void setDone() {
        done.set(true);
    }


    public LinkedList<Directory> getRootDir(){
        lock.lock();
        try{
        return this.root;
    } finally {
            lock.unlock();
        }
    }
    public void appendRoot(Directory root){
        lock.lock();
        try{
        this.root.add(root);
        }finally {
            lock.unlock();
        }
    }
    @Override
    public void run() {
        while (true) {
            if (done.get()) {
                System.out.println("Flag called");
                return;
            } else {
                System.out.println("Thread #" + Thread.currentThread().threadId() +
                        " - FileSystem instance: " + FileSystem.getFileSystem().hashCode());
                FileSystem fs = TestFixtureInitializer.createFS();
                System.out.println("Root is " + fs.getRootDir().getFirst().getName());
                System.out.println("Directory inside root is " + fs.getRootDir().getFirst().getSubDirectories().getFirst().getName());
                System.out.println("Directory inside root is " + fs.getRootDir().getFirst().getSubDirectories().get(1).getName());
                System.out.println("Files inside root " + fs.getRootDir().getFirst().getSubDirectories().get(1).getName() + "is " + fs.getRootDir().getFirst().getSubDirectories().getFirst().getFiles().getFirst().getName());
                try{
                    Thread.sleep(5000);
                }catch (InterruptedException ex){
                    System.out.println("Interrupt called is "+ex.getMessage());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {

        FileSystem fileSystem1 = new FileSystem();
        FileSystem fileSystem2 = new FileSystem();
        FileSystem fileSystem3 = new FileSystem();
        FileSystem fileSystem4 = new FileSystem();
        FileSystem fileSystem5 = new FileSystem();
        FileSystem fileSystem6 = new FileSystem();
        FileSystem fileSystem7 = new FileSystem();
        FileSystem fileSystem8 = new FileSystem();
        FileSystem fileSystem9 = new FileSystem();
        FileSystem fileSystem10 = new FileSystem();
        FileSystem fileSystem11 = new FileSystem();

        Thread thread1 = new Thread(fileSystem1);
        Thread thread2 = new Thread(fileSystem2);
        Thread thread3 = new Thread(fileSystem3);
        Thread thread4 = new Thread(fileSystem4);
        Thread thread5 = new Thread(fileSystem5);
        Thread thread6 = new Thread(fileSystem6);
        Thread thread7 = new Thread(fileSystem7);
        Thread thread8 = new Thread(fileSystem8);
        Thread thread9 = new Thread(fileSystem9);
        Thread thread10 = new Thread(fileSystem10);
        Thread thread11 = new Thread(fileSystem11);


        thread1.start();
        try {
            Thread.sleep(500);
            fileSystem1.setDone();
            Thread.sleep(2000);
            thread1.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread2.start();
        try {
            Thread.sleep(500);
            fileSystem2.setDone();
            Thread.sleep(2000);
            thread2.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread3.start();
        try {
            Thread.sleep(500);
            fileSystem3.setDone();
            Thread.sleep(2000);
            thread3.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread4.start();
        try {
            Thread.sleep(500);
            fileSystem4.setDone();
            Thread.sleep(2000);
            thread4.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread5.start();
        try {
            Thread.sleep(500);
            fileSystem5.setDone();
            Thread.sleep(2000);
            thread5.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread6.start();
        try {
            Thread.sleep(500);
            fileSystem6.setDone();
            Thread.sleep(2000);
            thread6.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread7.start();
        try {
            Thread.sleep(500);
            fileSystem7.setDone();
            Thread.sleep(2000);
            thread7.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread8.start();
        try {
            Thread.sleep(500);
            fileSystem8.setDone();
            Thread.sleep(2000);
            thread8.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread9.start();
        try {
            Thread.sleep(500);
            fileSystem9.setDone();
            Thread.sleep(2000);
            thread9.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        thread10.start();
        try {
            Thread.sleep(500);
            fileSystem10.setDone();
            Thread.sleep(2000);
            thread10.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        thread11.start();
        try {
            Thread.sleep(500);
            fileSystem11.setDone();
            Thread.sleep(2000);
            thread11.interrupt();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }


}

