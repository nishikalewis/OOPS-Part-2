package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
    private Directory parent;
    private String name;
    private int size;
    private LocalDateTime creationTime;

    protected ReentrantLock lock = new ReentrantLock();
    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        this.parent=parent;
        this.name=name;
        this.size=size;
        this.creationTime=creationTime;
        if(this.parent!=null){
            parent.appendChild(this);
        }

    }

    public Directory getParent() {
        lock.lock();
        try {
            return this.parent;
        }
        finally{
         lock.unlock();
        }
    }
    public int getSize(){

        lock.lock();
        try {
            return this.size;
        }
        finally{
            lock.unlock();
        }
    }
    public String getName(){

        lock.lock();
        try {
            return this.name;
        }
        finally{
            lock.unlock();
        }
    }
    public LocalDateTime getCreationTime(){
        lock.lock();
        try {
            return this.creationTime;
        }
        finally{
            lock.unlock();
        }
    }
    public abstract boolean isDirectory();
    public abstract boolean isFile();
    public abstract boolean isLink();


    public static void main(String args[]){
        System.out.println("");
    }

    protected void setParent(Directory parent) {
        lock.lock();
        try {
            this.parent = parent;
        } finally {
            lock.unlock();
        }

    }




}