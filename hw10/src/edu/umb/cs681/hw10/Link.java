package edu.umb.cs681.hw10;

import java.time.LocalDateTime;

public class Link extends FSElement {
    protected FSElement target;
    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target=target;
    }

    public FSElement getTarget() {
        lock.lock();
        try{
            return this.target;
        }finally {
            lock.unlock();
        }
    }
    public boolean isLink(){
        return true;
    }


    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isFile() {
        return false;
    }
}
