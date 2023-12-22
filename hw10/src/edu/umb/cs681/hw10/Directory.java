package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isLink() {
        return false;
    }



    private LinkedList<FSElement> children = new LinkedList<>();

    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try{
            return this.children;
        }
        finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
            this.children.add(child);
            child.setParent(this);
        } finally {
            lock.unlock();
        }
    }


    public int countChildren() {
        lock.lock();
        try{
        int childcount = 0;
        for (FSElement f : this.children) {
                childcount++;
            }
        return childcount;}
        finally {
                lock.unlock();
            }
    }


    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
        LinkedList<Directory> subDirectory = new LinkedList<>();
        for (FSElement child : children) {
                if (child.isDirectory()) {
                    subDirectory.add((Directory) child);
                }
        }return subDirectory;
            }finally {
                lock.unlock();
            }
        }

    public LinkedList<File> getFiles() {
            lock.lock();
            try{
        LinkedList<File> file = new LinkedList<>();
        for (FSElement child : children) {
                if (child.isFile()) {
                    file.add((File) child);
                }
            }return file;}
        finally {
                lock.unlock();
            }
    }

    public int getTotalSize() {
        lock.lock();
        try {
            int ts = 0;
            for (int i = 0; i < this.children.size(); i++) {
                    if (this.children.get(i) instanceof Directory) {
                        Directory a = (Directory) this.children.get(i);
                        ts = ts + a.getTotalSize();
                    } else {
                        ts = ts + this.children.get(i).getSize();
                    }
                } return ts;
        }
            finally {
                    lock.unlock();
                }
            }
}




