package edu.umb.cs681.hw08;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
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

    LinkedList<FSElement>children=new LinkedList<>();
    LinkedList<FSElement>getChildren(){
        return this.children;
    }
    public void appendChild(FSElement child){
        this.children.add(child);
        child.setParent(this);
    }

    public int countChildren(){
        int childcount=0;
        for(FSElement f:this.children){
            childcount++;
        }
        return childcount;
    }
    public LinkedList<Directory> getSubDirectories(){
        LinkedList<Directory> subDirectory=new LinkedList<>();
        for(FSElement child:children){
            if(child.isDirectory()){
                subDirectory.add((Directory) child);
            }
        }
        return subDirectory;
    }
    public LinkedList<File> getFiles(){
        LinkedList<File> file=new LinkedList<>();
        for(FSElement child:children){
            if(child.isFile()){
                file.add((File) child);
            }
        }
        return file;
    }
    public int getTotalSize(){
        int ts=0;
        for(int i=0;i<this.children.size();i++){
            if(this.children.get(i)instanceof Directory){
                Directory a=(Directory) this.children.get(i);
                ts=ts+a.getTotalSize();
            }
            else {
                ts=ts+this.children.get(i).getSize();
            }
        }
        return ts;
    }

}